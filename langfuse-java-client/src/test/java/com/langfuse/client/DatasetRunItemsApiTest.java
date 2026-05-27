package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasetItems.DatasetItemsApi;
import com.langfuse.api.datasetRunItems.DatasetRunItemsApi;
import com.langfuse.api.datasets.DatasetsApi;
import com.langfuse.api.ingestion.IngestionApi;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.model.CreateDatasetRunItemRequest;
import com.langfuse.api.model.DatasetRunItem;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.TraceBody;

/**
 * Integration tests for the Dataset Run Items API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatasetRunItemsApiTest extends AbstractLangfuseClientTest {

    private static final String DATASET_NAME = "test-run-items-dataset-" + UUID.randomUUID();
    private static final String RUN_NAME = "test-run-" + UUID.randomUUID().toString().substring(0, 8);
    private static final String TRACE_ID = UUID.randomUUID().toString();
    private static String datasetItemId;

    @Test
    @Order(1)
    void setupDatasetAndTrace() {
        client.datasets().datasetsCreate(
                DatasetsApi.APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .build())
                        .build());

        assertThat(client.datasetItems().datasetItemsCreate(
                DatasetItemsApi.APIDatasetItemsCreateRequest.newBuilder()
                        .createDatasetItemRequest(CreateDatasetItemRequest.builder()
                                .datasetName(DATASET_NAME)
                                .input(Map.of("question", "What is testing?"))
                                .build())
                        .build()))
                .satisfies(item -> {
                    assertThat(item.getId()).isNotBlank();
                    datasetItemId = item.getId();
                });

        client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(IngestionEventOneOf.builder()
                                        .id(UUID.randomUUID().toString())
                                        .timestamp(OffsetDateTime.now().toString())
                                        .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                                        .body(TraceBody.builder()
                                                .id(TRACE_ID)
                                                .name("run-items-test-trace")
                                                .build())
                                        .build())))
                                .build())
                        .build());
    }

    @Test
    @Order(2)
    void createDatasetRunItem() {
        assertThat(client.datasetRunItems().datasetRunItemsCreate(
                DatasetRunItemsApi.APIDatasetRunItemsCreateRequest.newBuilder()
                        .createDatasetRunItemRequest(CreateDatasetRunItemRequest.builder()
                                .runName(RUN_NAME)
                                .datasetItemId(datasetItemId)
                                .traceId(TRACE_ID)
                                .build())
                        .build()))
                .satisfies(runItem -> {
                    assertThat(runItem.getId()).isNotBlank();
                    assertThat(runItem.getCreatedAt()).isNotNull();
                })
                .extracting(DatasetRunItem::getDatasetRunName, DatasetRunItem::getDatasetItemId, DatasetRunItem::getTraceId)
                .containsExactly(RUN_NAME, datasetItemId, TRACE_ID);
    }
}
