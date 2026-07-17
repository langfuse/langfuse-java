package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.ingestion.IngestionApi;
import com.langfuse.api.legacyScoreV1.LegacyScoreV1Api;
import com.langfuse.api.model.CreateScoreValue;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.LegacyCreateScoreRequest;
import com.langfuse.api.model.LegacyCreateScoreSource;
import com.langfuse.api.model.ScoreDataType;
import com.langfuse.api.model.TraceBody;
import com.langfuse.api.scores.ScoresApi;

/**
 * Integration tests for the Scores API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoresApiTest extends AbstractLangfuseClientTest {

    private static final String TRACE_ID = UUID.randomUUID().toString();
    private static final String SCORE_NAME = "test-score";

    @Test
    @Order(1)
    void ingestTrace() {
        assertThat(client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(IngestionEventOneOf.builder()
                                        .id(UUID.randomUUID().toString())
                                        .timestamp(OffsetDateTime.now().toString())
                                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                                        .body(TraceBody.builder()
                                                .id(TRACE_ID)
                                                .name("score-test-trace")
                                                .build())
                                        .build())))
                                .build())
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getSuccesses()).hasSize(1);
                    assertThat(response.getErrors()).isEmpty();
                });
    }

    @Test
    @Order(1)
    void createScore() {
        assertThat(client.legacyScoreV1().legacyScoreV1Create(
                LegacyScoreV1Api.APILegacyScoreV1CreateRequest.newBuilder()
                        .legacyCreateScoreRequest(LegacyCreateScoreRequest.builder()
                                .traceId(TRACE_ID)
                                .name(SCORE_NAME)
                                .value(new CreateScoreValue(0.95))
                                .dataType(ScoreDataType.NUMERIC)
                                .source(LegacyCreateScoreSource.API)
                                .environment("default")
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getId()).isNotBlank());
    }

    @Test
    @Order(2)
    void listScoresForTrace() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.scores().scoresGetMany(
                                ScoresApi.APIScoresGetManyRequest.newBuilder()
                                        .name(SCORE_NAME)
                                        .traceId(TRACE_ID)
                                        .build()))
                                .satisfies(scores -> {
                                    assertThat(scores.getData()).isNotEmpty();
                                    assertThat(scores.getMeta().getTotalItems()).isGreaterThan(0);
                                }));
    }
}
