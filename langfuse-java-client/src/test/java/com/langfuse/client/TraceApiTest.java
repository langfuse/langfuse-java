package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.model.TraceDeleteMultipleRequest;
import com.langfuse.api.observations.ObservationsApi.APIObservationsGetManyRequest;
import com.langfuse.api.trace.TraceApi.APITraceDeleteMultipleRequest;
import com.langfuse.api.trace.TraceApi.APITraceDeleteRequest;
import com.langfuse.api.trace.TraceApi.APITraceGetRequest;
import com.langfuse.api.trace.TraceApi.APITraceListRequest;

/**
 * Integration tests for the Trace API.
 *
 * <p>In Langfuse v4 {@code events_only} mode, legacy trace read endpoints return 404.
 * This test verifies the 404 behavior and uses the v2 observations API as the replacement.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class TraceApiTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private final String TRACE_NAME = "trace-api-test-" + UUID.randomUUID();

    @Test
    void traceGetReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.trace().traceGet(
                        APITraceGetRequest.newBuilder()
                                .traceId(UUID.randomUUID().toString())
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    void traceListReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.trace().traceList(
                        APITraceListRequest.newBuilder()
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    @Order(1)
    void ingestTraceViaOtel() {
        ingestTrace(TRACE_ID, SPAN_ID, TRACE_NAME, Map.of("user.id", "test-user"));
    }

    @Test
    @Order(2)
    void queryTraceDataViaV2Observations() {
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

    @Test
    @Order(3)
    void traceDelete() {
        assertThat(client.trace().traceDelete(
                APITraceDeleteRequest.newBuilder()
                        .traceId(TRACE_ID)
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }

    @Test
    void traceDeleteMultiple() {
        assertThat(client.trace().traceDeleteMultiple(
                APITraceDeleteMultipleRequest.newBuilder()
                        .traceDeleteMultipleRequest(TraceDeleteMultipleRequest.builder()
                                .traceIds(List.of(UUID.randomUUID().toString().replace("-", "")))
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
