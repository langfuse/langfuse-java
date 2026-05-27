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
import com.langfuse.api.legacyObservationsV1.LegacyObservationsV1Api;
import com.langfuse.api.model.CreateSpanBody;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.ObservationsView;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.IngestionEventOneOf2;
import com.langfuse.api.model.TraceBody;

/**
 * Integration tests for the Observations API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ObservationsApiTest extends AbstractLangfuseClientTest {

    private static final String TRACE_ID = UUID.randomUUID().toString();
    private static final String SPAN_ID = UUID.randomUUID().toString();
    private static final String SPAN_NAME = "observations-test-span-" + UUID.randomUUID();

    @Test
    @Order(1)
    void ingestTraceWithSpan() {
        assertThat(client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(
                                        new IngestionEvent(IngestionEventOneOf.builder()
                                                .id(UUID.randomUUID().toString())
                                                .timestamp(OffsetDateTime.now().toString())
                                                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                                                .body(TraceBody.builder()
                                                        .id(TRACE_ID)
                                                        .name("observations-test-trace")
                                                        .build())
                                                .build()),
                                        new IngestionEvent(IngestionEventOneOf2.builder()
                                                .id(UUID.randomUUID().toString())
                                                .timestamp(OffsetDateTime.now().toString())
                                                .type(IngestionEventOneOf2.TypeEnum.SPAN_CREATE)
                                                .body(CreateSpanBody.builder()
                                                        .id(SPAN_ID)
                                                        .traceId(TRACE_ID)
                                                        .name(SPAN_NAME)
                                                        .build())
                                                .build())))
                                .build())
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getSuccesses()).hasSize(2)
                            .allSatisfy(s -> assertThat(s.getStatus()).isEqualTo(201));
                    assertThat(response.getErrors()).isEmpty();
                });
    }

    @Test
    @Order(2)
    void listObservationsViaLegacyApi() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.legacyObservationsV1().legacyObservationsV1GetMany(
                                LegacyObservationsV1Api.APILegacyObservationsV1GetManyRequest.newBuilder()
                                        .traceId(TRACE_ID)
                                        .build()))
                                .satisfies(observations -> {
                                    assertThat(observations.getData())
                                            .isNotEmpty()
                                            .anyMatch(o -> SPAN_NAME.equals(o.getName()));

                                    var span = observations.getData().stream()
                                            .filter(o -> SPAN_NAME.equals(o.getName()))
                                            .findFirst()
                                            .orElseThrow();

                                    assertThat(span)
                                            .satisfies(s -> assertThat(s.getStartTime()).isNotNull())
                                            .extracting(ObservationsView::getTraceId, ObservationsView::getType)
                                            .containsExactly(TRACE_ID, "SPAN");
                                }));
    }
}
