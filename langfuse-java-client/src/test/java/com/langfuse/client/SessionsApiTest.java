package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.observations.ObservationsApi.APIObservationsGetManyRequest;
import com.langfuse.api.sessions.SessionsApi.APISessionsGetRequest;
import com.langfuse.api.sessions.SessionsApi.APISessionsListRequest;

/**
 * Integration tests for the Sessions API.
 *
 * <p>In Langfuse v4 {@code events_only} mode, legacy sessions endpoints return 404.
 * This test verifies the 404 behavior and uses the v2 observations API with
 * {@code traceId} filter as the replacement.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class SessionsApiTest extends AbstractLangfuseClientTest {

    private final String SESSION_ID = "test-session-" + UUID.randomUUID();
    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);

    @Test
    void sessionsGetReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.sessions().sessionsGet(
                        APISessionsGetRequest.newBuilder()
                                .sessionId("nonexistent-" + UUID.randomUUID())
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    void sessionsListReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.sessions().sessionsList(
                        APISessionsListRequest.newBuilder()
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    @Order(1)
    void ingestTraceWithSession() {
        ingestTrace(TRACE_ID, SPAN_ID, "sessions-test-trace", Map.of("session.id", SESSION_ID));
    }

    @Test
    @Order(2)
    void querySessionDataViaV2ObservationsWithSessionIdFilter() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.observations().observationsGetMany(
                                APIObservationsGetManyRequest.newBuilder()
                                        .traceId(TRACE_ID)
                                        .build()))
                                .satisfies(response ->
                                        assertThat(response.getData())
                                                .isNotEmpty()
                                                .anyMatch(o -> "root-span".equals(o.getName()))));
    }
}
