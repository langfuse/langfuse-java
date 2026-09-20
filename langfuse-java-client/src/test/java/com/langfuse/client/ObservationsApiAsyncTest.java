package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.model.ObservationV2;
import com.langfuse.api.model.OtelSpan;
import com.langfuse.api.observations.ObservationsApi.APIObservationsGetManyRequest;

/**
 * Async integration tests for the Observations API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ObservationsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String ROOT_SPAN_ID = TRACE_ID.substring(0, 16);
    private final String CHILD_SPAN_ID = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    private final String CHILD_SPAN_NAME = "async-observations-test-span-" + UUID.randomUUID();

    @Test
    @Order(1)
    void ingestTraceWithSpan() {
        var nowNanos = String.valueOf(System.currentTimeMillis() * 1_000_000L);
        var endNanos = String.valueOf(Long.parseLong(nowNanos) + 1_000_000_000L);

        var rootSpan = OtelSpan.builder()
                .traceId(TRACE_ID)
                .spanId(ROOT_SPAN_ID)
                .name("root-span")
                .kind(1)
                .startTimeUnixNano(nowNanos)
                .endTimeUnixNano(endNanos)
                .build();

        var childSpan = OtelSpan.builder()
                .traceId(TRACE_ID)
                .spanId(CHILD_SPAN_ID)
                .parentSpanId(ROOT_SPAN_ID)
                .name(CHILD_SPAN_NAME)
                .kind(2)
                .startTimeUnixNano(nowNanos)
                .endTimeUnixNano(endNanos)
                .build();

        ingestTraceWithSpans("async-observations-test-trace", Map.of(), rootSpan, childSpan);
    }

    @Test
    @Order(2)
    void listObservationsViaV2Api() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.asyncObservations().observationsGetMany(
                                APIObservationsGetManyRequest.newBuilder()
                                        .traceId(TRACE_ID)
                                        .build()))
                                .succeedsWithin(Duration.ofSeconds(5))
                                .satisfies(observations -> {
                                    assertThat(observations.getData())
                                            .isNotEmpty()
                                            .anyMatch(o -> CHILD_SPAN_NAME.equals(o.getName()));

                                    var span = observations.getData().stream()
                                            .filter(o -> CHILD_SPAN_NAME.equals(o.getName()))
                                            .findFirst()
                                            .orElseThrow();

                                    assertThat(span)
                                            .satisfies(s -> assertThat(s.getStartTime()).isNotNull())
                                            .extracting(ObservationV2::getTraceId, ObservationV2::getType)
                                            .containsExactly(TRACE_ID, "SPAN");
                                }));
    }
}
