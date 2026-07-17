package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.langfuse.api.ingestion.IngestionApi;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.TraceBody;

/**
 * Async integration tests for the Ingestion API.
 *
 * @author Eric Deandrea
 */
class IngestionApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void ingestSingleTrace() {
        var traceEvent = IngestionEventOneOf.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(OffsetDateTime.now().toString())
                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                .body(TraceBody.builder()
                        .id(UUID.randomUUID().toString())
                        .name("async-test-trace")
                        .build())
                .build();

        assertThat(client.asyncIngestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(traceEvent)))
                                .build())
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> {
                    assertThat(response.getSuccesses())
                            .isNotEmpty();

                    assertThat(response.getErrors())
                            .isEmpty();
                });
    }

    @Test
    void ingestMultipleTraces() {
        var events = List.of(
                new IngestionEvent(IngestionEventOneOf.builder()
                        .id(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                        .body(TraceBody.builder()
                                .id(UUID.randomUUID().toString())
                                .name("async-batch-trace-1")
                                .build())
                        .build()),
                new IngestionEvent(IngestionEventOneOf.builder()
                        .id(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                        .body(TraceBody.builder()
                                .id(UUID.randomUUID().toString())
                                .name("async-batch-trace-2")
                                .build())
                        .build())
        );

        assertThat(client.asyncIngestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(events)
                                .build())
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> {
                    assertThat(response.getSuccesses())
                            .hasSize(2);

                    assertThat(response.getErrors())
                            .isEmpty();
                });
    }
}
