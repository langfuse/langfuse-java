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
 * Async integration tests for the Scores API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoresApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String TRACE_ID = UUID.randomUUID().toString();
    private static final String SCORE_NAME = "async-test-score";

    @Test
    @Order(1)
    void ingestTrace() {
        var traceEvent = IngestionEventOneOf.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(OffsetDateTime.now().toString())
                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                .body(TraceBody.builder()
                        .id(TRACE_ID)
                        .name("async-score-test-trace")
                        .build())
                .build();

        assertThat(client.asyncIngestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(traceEvent)))
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> assertThat(response.getSuccesses()).isNotEmpty());
    }

    @Test
    @Order(1)
    void createScore() {
        assertThat(client.asyncLegacyScoreV1().legacyScoreV1Create(
                LegacyScoreV1Api.APILegacyScoreV1CreateRequest.newBuilder()
                        .legacyCreateScoreRequest(LegacyCreateScoreRequest.builder()
                                .traceId(TRACE_ID)
                                .name(SCORE_NAME)
                                .value(new CreateScoreValue(0.85))
                                .dataType(ScoreDataType.NUMERIC)
                                .source(LegacyCreateScoreSource.API)
                                .environment("default")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> assertThat(response.getId()).isNotBlank());
    }

    @Test
    @Order(2)
    void listScoresForTrace() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(e -> e instanceof LangfuseApiException)
                .untilAsserted(() ->
                        assertThat(client.asyncScores().scoresGetMany(
                                ScoresApi.APIScoresGetManyRequest.newBuilder()
                                        .name(SCORE_NAME)
                                        .traceId(TRACE_ID)
                                        .build()))
                                .succeedsWithin(Duration.ofSeconds(5))
                                .satisfies(scores ->
                                        assertThat(scores.getData()).isNotEmpty()));
    }
}
