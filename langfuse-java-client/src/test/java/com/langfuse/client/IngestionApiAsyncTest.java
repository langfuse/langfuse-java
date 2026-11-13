package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.langfuse.api.ingestion.IngestionApi.APIIngestionBatchRequest;
import com.langfuse.api.model.CreateScoreValue;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionSuccess;
import com.langfuse.api.model.ScoreBody;
import com.langfuse.api.model.ScoreDataType;
import com.langfuse.api.model.ScoreEvent1;
import com.langfuse.api.model.TraceBody;
import com.langfuse.api.model.TraceEvent1;

/**
 * Async integration tests for the Ingestion API.
 *
 * @author Eric Deandrea
 */
abstract class IngestionApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void traceCreateReturnsErrorInEventsOnlyMode() {
        var eventId = UUID.randomUUID().toString();

        var traceEvent = TraceEvent1.builder()
                .id(eventId)
                .timestamp(OffsetDateTime.now().toString())
                .type(TraceEvent1.TypeEnum.TRACE_CREATE)
                .body(TraceBody.builder()
                        .id(UUID.randomUUID().toString())
                        .name("async-test-trace")
                        .build())
                .build();

        assertThat(client.asyncIngestion().ingestionBatch(
                APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(traceEvent)))
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> {
                    assertThat(response.getErrors())
                            .isNotEmpty()
                            .first()
                            .satisfies(error -> assertThat(error.getId()).isEqualTo(eventId));
                    assertThat(response.getSuccesses()).isEmpty();
                });
    }

    @Test
    void multipleTraceCreatesReturnErrorsInEventsOnlyMode() {
        var eventId1 = UUID.randomUUID().toString();
        var eventId2 = UUID.randomUUID().toString();

        List<IngestionEvent> events = List.of(
                new IngestionEvent(TraceEvent1.builder()
                        .id(eventId1)
                        .timestamp(OffsetDateTime.now().toString())
                        .type(TraceEvent1.TypeEnum.TRACE_CREATE)
                        .body(TraceBody.builder()
                                .id(UUID.randomUUID().toString())
                                .name("async-batch-trace-1")
                                .build())
                        .build()),
                new IngestionEvent(TraceEvent1.builder()
                        .id(eventId2)
                        .timestamp(OffsetDateTime.now().toString())
                        .type(TraceEvent1.TypeEnum.TRACE_CREATE)
                        .body(TraceBody.builder()
                                .id(UUID.randomUUID().toString())
                                .name("async-batch-trace-2")
                                .build())
                        .build()));

        assertThat(client.asyncIngestion().ingestionBatch(
                APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(events)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> {
                    assertThat(response.getErrors())
                            .hasSize(2)
                            .extracting("id")
                            .containsExactlyInAnyOrder(eventId1, eventId2);
                    assertThat(response.getSuccesses()).isEmpty();
                });
    }

    @Test
    void scoreCreateSucceedsInEventsOnlyMode() {
        var traceId = UUID.randomUUID().toString().replace("-", "");
        var spanId = traceId.substring(0, 16);

        ingestTrace(traceId, spanId, "async-score-ingestion-test-trace");

        var eventId = UUID.randomUUID().toString();

        var scoreEvent = ScoreEvent1.builder()
                .id(eventId)
                .timestamp(OffsetDateTime.now().toString())
                .type(ScoreEvent1.TypeEnum.SCORE_CREATE)
                .body(ScoreBody.builder()
                        .traceId(traceId)
                        .name("async-ingestion-test-score")
                        .value(new CreateScoreValue(0.75))
                        .dataType(ScoreDataType.NUMERIC)
                        .build())
                .build();

        assertThat(client.asyncIngestion().ingestionBatch(
                APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(scoreEvent)))
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> {
                    assertThat(response.getSuccesses())
                            .hasSize(1)
                            .first()
                            .extracting(IngestionSuccess::getId, IngestionSuccess::getStatus)
                            .containsExactly(eventId, 201);
                    assertThat(response.getErrors()).isEmpty();
                });
    }
}
