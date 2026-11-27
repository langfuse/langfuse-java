package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
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
 * Async integration tests for the Scores API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ScoresApiAsyncTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private final String SCORE_NAME = "async-test-score";

    @Test
    @Order(1)
    void ingestTrace() {
        ingestTrace(TRACE_ID, SPAN_ID, "async-score-test-trace");
    }

    @Test
    @Order(1)
    void createScore() {
        assertThat(client.asyncScores().scoresCreate(
                APIScoresCreateRequest.newBuilder()
                        .createScoreRequest(CreateScoreRequest.builder()
                                .traceId(TRACE_ID)
                                .name(SCORE_NAME)
                                .value(new CreateScoreValue(0.85))
                                .dataType(ScoreDataType.NUMERIC)
                                .source(CreateScoreSource.API)
                                .environment("default")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> assertThat(response.getId()).isNotBlank());
    }

    @Test
    void scoresGetByIdReturns404InEventsOnlyMode() {
        assertThat(client.asyncScores().scoresGetById(
                APIScoresGetByIdRequest.newBuilder()
                        .scoreId(UUID.randomUUID().toString())
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }

    @Test
    void scoresV2GetManyReturns404InEventsOnlyMode() {
        assertThat(client.asyncScores().scoresGetMany(
                APIScoresGetManyRequest.newBuilder()
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }

    @Test
    @Order(2)
    void listScoresViaV3() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(e -> e instanceof LangfuseApiException)
                .untilAsserted(() ->
                        assertThat(client.asyncScoresV3().scoresV3GetManyV3(
                                APIScoresV3GetManyV3Request.newBuilder()
                                        .name(SCORE_NAME)
                                        .traceId(TRACE_ID)
                                        .build()))
                                .succeedsWithin(Duration.ofSeconds(5))
                                .satisfies(scores ->
                                        assertThat(scores.getData()).isNotEmpty()));
    }
}
