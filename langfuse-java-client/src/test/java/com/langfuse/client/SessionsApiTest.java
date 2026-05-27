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
 * Integration tests for the Sessions API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionsApiTest extends AbstractLangfuseClientTest {

    private static final String SESSION_ID = "test-session-" + UUID.randomUUID();

    @Test
    @Order(1)
    void ingestTraceWithSession() {
        assertThat(client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(IngestionEventOneOf.builder()
                                        .id(UUID.randomUUID().toString())
                                        .timestamp(OffsetDateTime.now().toString())
                                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                                        .body(TraceBody.builder()
                                                .id(UUID.randomUUID().toString())
                                                .name("sessions-test-trace")
                                                .sessionId(SESSION_ID)
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
    @Order(2)
    void getSession() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.sessions().sessionsGet(
                                SessionsApi.APISessionsGetRequest.newBuilder()
                                        .sessionId(SESSION_ID)
                                        .build()))
                                .satisfies(session -> {
                                    assertThat(session.getId()).isEqualTo(SESSION_ID);
                                    assertThat(session.getCreatedAt()).isNotNull();
                                    assertThat(session.getTraces()).isNotEmpty();
                                }));
    }

    @Test
    @Order(2)
    void listSessions() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.sessions().sessionsList(
                                SessionsApi.APISessionsListRequest.newBuilder()
                                        .build()))
                                .satisfies(sessions -> {
                                    assertThat(sessions.getData())
                                            .isNotEmpty()
                                            .anyMatch(s -> SESSION_ID.equals(s.getId()));
                                    assertThat(sessions.getMeta().getTotalItems()).isGreaterThan(0);
                                }));
    }
}
