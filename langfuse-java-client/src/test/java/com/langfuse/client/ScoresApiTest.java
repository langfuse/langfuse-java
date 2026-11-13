package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
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
import com.langfuse.api.scores.ScoresApi.APIScoresGetByIdRequest;
import com.langfuse.api.scores.ScoresApi.APIScoresGetManyRequest;
import com.langfuse.api.scoresV3.ScoresV3Api.APIScoresV3GetManyV3Request;

/**
 * Integration tests for the Scores API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ScoresApiTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private final String SCORE_NAME = "test-score";

    @Test
    @Order(1)
    void ingestTrace() {
        ingestTrace(TRACE_ID, SPAN_ID, "score-test-trace");
    }

    @Test
    @Order(1)
    void createScore() {
        assertThat(client.scores().scoresCreate(
                APIScoresCreateRequest.newBuilder()
                        .createScoreRequest(CreateScoreRequest.builder()
                                .traceId(TRACE_ID)
                                .name(SCORE_NAME)
                                .value(new CreateScoreValue(0.95))
                                .dataType(ScoreDataType.NUMERIC)
                                .source(CreateScoreSource.API)
                                .environment("default")
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getId()).isNotBlank());
    }

    @Test
    void scoresV2GetManyReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.scores().scoresGetMany(
                        APIScoresGetManyRequest.newBuilder()
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    @Order(2)
    void listScoresViaV3() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.scoresV3().scoresV3GetManyV3(
                                APIScoresV3GetManyV3Request.newBuilder()
                                        .name(SCORE_NAME)
                                        .traceId(TRACE_ID)
                                        .build()))
                                .satisfies(scores ->
                                        assertThat(scores.getData()).isNotEmpty()));
    }

    @Test
    void scoresV2GetByIdReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.scores().scoresGetById(
                        APIScoresGetByIdRequest.newBuilder()
                                .scoreId(UUID.randomUUID().toString())
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }
}
