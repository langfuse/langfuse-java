package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.langfuse.api.ingestion.IngestionApi;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionSuccess;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.TraceBody;

/**
 * Integration tests for the Ingestion API.
 *
 * @author Eric Deandrea
 */
class IngestionApiTest extends AbstractLangfuseClientTest {

    @Test
    void ingestSingleTrace() {
        var eventId = UUID.randomUUID().toString();

        var traceEvent = IngestionEventOneOf.builder()
                .id(eventId)
                .timestamp(OffsetDateTime.now().toString())
                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                .body(TraceBody.builder()
                        .id(UUID.randomUUID().toString())
                        .name("test-trace")
                        .build())
                .build();

        assertThat(client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(traceEvent)))
                                .build())
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getSuccesses())
                            .hasSize(1)
                            .first()
                            .extracting(IngestionSuccess::getId, IngestionSuccess::getStatus)
                            .containsExactly(eventId, 201);
                    assertThat(response.getErrors()).isEmpty();
                });
    }

    @Test
    void ingestMultipleTraces() {
        var eventId1 = UUID.randomUUID().toString();
        var eventId2 = UUID.randomUUID().toString();

        List<IngestionEvent> events = List.of(
                new IngestionEvent(IngestionEventOneOf.builder()
                        .id(eventId1)
                        .timestamp(OffsetDateTime.now().toString())
                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                        .body(TraceBody.builder()
                                .id(UUID.randomUUID().toString())
                                .name("batch-trace-1")
                                .build())
                        .build()),
                new IngestionEvent(IngestionEventOneOf.builder()
                        .id(eventId2)
                        .timestamp(OffsetDateTime.now().toString())
                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                        .body(TraceBody.builder()
                                .id(UUID.randomUUID().toString())
                                .name("batch-trace-2")
                                .build())
                        .build())
        );

        assertThat(client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(events)
                                .build())
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getSuccesses())
                            .hasSize(2)
                            .extracting("id")
                            .containsExactlyInAnyOrder(eventId1, eventId2);
                    assertThat(response.getErrors()).isEmpty();
                });
    }
}
