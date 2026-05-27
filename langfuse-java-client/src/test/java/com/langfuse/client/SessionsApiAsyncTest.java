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
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.TraceBody;
import com.langfuse.api.sessions.SessionsApi;

/**
 * Async integration tests for the Sessions API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionsApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String SESSION_ID = "async-test-session-" + UUID.randomUUID();

    @Test
    @Order(1)
    void ingestTraceWithSession() {
        var traceEvent = IngestionEventOneOf.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(OffsetDateTime.now().toString())
                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                .body(TraceBody.builder()
                        .id(UUID.randomUUID().toString())
                        .name("async-sessions-test-trace")
                        .sessionId(SESSION_ID)
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
    @Order(2)
    void getSession() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.asyncSessions().sessionsGet(
                                SessionsApi.APISessionsGetRequest.newBuilder()
                                        .sessionId(SESSION_ID)
                                        .build()))
                                .succeedsWithin(Duration.ofSeconds(5))
                                .satisfies(session ->
                                        assertThat(session.getId()).isEqualTo(SESSION_ID)));
    }

    @Test
    @Order(2)
    void listSessions() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.asyncSessions().sessionsList(
                                SessionsApi.APISessionsListRequest.newBuilder()
                                        .build()))
                                .succeedsWithin(Duration.ofSeconds(5))
                                .satisfies(sessions ->
                                        assertThat(sessions.getData())
                                                .isNotEmpty()
                                                .anyMatch(s -> SESSION_ID.equals(s.getId()))));
    }
}
