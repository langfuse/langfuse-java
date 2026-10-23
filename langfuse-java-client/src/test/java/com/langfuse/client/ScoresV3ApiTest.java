package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.model.CreateScoreRequest;
import com.langfuse.api.model.CreateScoreSource;
import com.langfuse.api.model.CreateScoreValue;
import com.langfuse.api.model.ScoreDataType;
import com.langfuse.api.scores.ScoresApi.APIScoresCreateRequest;
import com.langfuse.api.scoresV3.ScoresV3Api.APIScoresV3GetManyV3Request;

/**
 * Integration tests for the Scores V3 API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ScoresV3ApiTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private final String SCORE_NAME = "v3-score-" + UUID.randomUUID().toString().substring(0, 8);

    @Test
    @Order(1)
    void ingestTraceViaOtel() {
        ingestTrace(TRACE_ID, SPAN_ID, "scores-v3-test-trace");
    }

    @Test
    @Order(2)
    void createScore() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.scores().scoresCreate(
                                APIScoresCreateRequest.newBuilder()
                                        .createScoreRequest(CreateScoreRequest.builder()
                                                .traceId(TRACE_ID)
                                                .name(SCORE_NAME)
                                                .value(new CreateScoreValue(0.75))
                                                .dataType(ScoreDataType.NUMERIC)
                                                .source(CreateScoreSource.API)
                                                .build())
                                        .build()))
                                .satisfies(response ->
                                        assertThat(response.getId()).isNotBlank()));
    }

    @Test
    @Order(3)
    void listScoresViaV3() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.scoresV3().scoresV3GetManyV3(
                                APIScoresV3GetManyV3Request.newBuilder()
                                        .name(SCORE_NAME)
                                        .fromTimestamp(OffsetDateTime.parse("2020-01-01T00:00:00Z"))
                                        .build()))
                                .satisfies(response ->
                                        assertThat(response.getData())
                                                .hasSize(1)));
    }
}
