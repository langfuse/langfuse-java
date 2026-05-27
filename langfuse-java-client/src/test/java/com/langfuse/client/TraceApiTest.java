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
import com.langfuse.api.model.TraceWithFullDetails;
import com.langfuse.api.trace.TraceApi;

/**
 * Integration tests for the Trace API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TraceApiTest extends AbstractLangfuseClientTest {

    private static final String TRACE_ID = UUID.randomUUID().toString();
    private static final String TRACE_NAME = "trace-api-test-" + UUID.randomUUID();

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
                                                .name(TRACE_NAME)
                                                .userId("test-user")
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
    void getTraceById() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.trace().traceGet(
                                TraceApi.APITraceGetRequest.newBuilder()
                                        .traceId(TRACE_ID)
                                        .build()))
                                .satisfies(trace -> assertThat(trace.getTimestamp()).isNotNull())
                                .extracting(TraceWithFullDetails::getId, TraceWithFullDetails::getName, TraceWithFullDetails::getUserId)
                                .containsExactly(TRACE_ID, TRACE_NAME, "test-user"));
    }

    @Test
    @Order(2)
    void listTracesContainsIngestedTrace() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.trace().traceList(
                                TraceApi.APITraceListRequest.newBuilder()
                                        .name(TRACE_NAME)
                                        .build()))
                                .satisfies(traces -> {
                                    assertThat(traces.getData())
                                            .isNotEmpty()
                                            .anyMatch(t -> TRACE_ID.equals(t.getId()));
                                    assertThat(traces.getMeta().getTotalItems()).isGreaterThan(0);
                                }));
    }
}
