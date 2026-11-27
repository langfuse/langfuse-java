package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsCreateRequest;
import com.langfuse.api.datasetRunItems.DatasetRunItemsApi.APIDatasetRunItemsCreateRequest;
import com.langfuse.api.datasetRunItems.DatasetRunItemsApi.APIDatasetRunItemsListRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsCreateRequest;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.model.CreateDatasetRunItemRequest;
import com.langfuse.api.model.DatasetRunItem;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class DatasetRunItemsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String DATASET_NAME = "async-test-run-items-dataset-" + UUID.randomUUID();
    private final String RUN_NAME = "async-test-run-" + UUID.randomUUID().toString().substring(0, 8);
    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String datasetId;
    private static String datasetItemId;

    @Test
    @Order(1)
    void setupDatasetAndTrace() {
        var dataset = client.datasets().datasetsCreate(
                APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .build())
                        .build());

        datasetId = dataset.getId();

        assertThat(client.datasetItems().datasetItemsCreate(
                APIDatasetItemsCreateRequest.newBuilder()
                        .createDatasetItemRequest(CreateDatasetItemRequest.builder()
                                .datasetName(DATASET_NAME)
                                .input(Map.of("question", "What is testing?"))
                                .build())
                        .build()))
                .satisfies(item -> {
                    assertThat(item.getId()).isNotBlank();
                    datasetItemId = item.getId();
                });

        ingestTrace(TRACE_ID, SPAN_ID, "async-run-items-test-trace");
    }

    @Test
    @Order(2)
    void createDatasetRunItem() {
        assertThat(client.asyncDatasetRunItems().datasetRunItemsCreate(
                APIDatasetRunItemsCreateRequest.newBuilder()
                        .createDatasetRunItemRequest(CreateDatasetRunItemRequest.builder()
                                .runName(RUN_NAME)
                                .datasetItemId(datasetItemId)
                                .traceId(TRACE_ID)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(runItem -> {
                    assertThat(runItem.getId()).isNotBlank();
                    assertThat(runItem.getCreatedAt()).isNotNull();
                })
                .extracting(DatasetRunItem::getDatasetRunName, DatasetRunItem::getDatasetItemId, DatasetRunItem::getTraceId)
                .containsExactly(RUN_NAME, datasetItemId, TRACE_ID);
    }

    @Test
    @Order(3)
    void listDatasetRunItemsReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.datasetRunItems().datasetRunItemsList(
                        APIDatasetRunItemsListRequest.newBuilder()
                                .datasetId(datasetId)
                                .runName(RUN_NAME)
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }
}
