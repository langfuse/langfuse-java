package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasetItems.DatasetItemsApi;
import com.langfuse.api.datasets.DatasetsApi;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;

/**
 * Async integration tests for the Dataset Items API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatasetItemsApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String DATASET_NAME = "async-test-dataset-items-" + UUID.randomUUID();
    private static String datasetItemId;

    @Test
    @Order(1)
    void createDatasetAndItem() {
        client.datasets().datasetsCreate(
                DatasetsApi.APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .build())
                        .build());

        assertThat(client.asyncDatasetItems().datasetItemsCreate(
                DatasetItemsApi.APIDatasetItemsCreateRequest.newBuilder()
                        .createDatasetItemRequest(CreateDatasetItemRequest.builder()
                                .datasetName(DATASET_NAME)
                                .input(Map.of("question", "What is async?"))
                                .expectedOutput(Map.of("answer", "Non-blocking execution"))
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(item -> {
                    assertThat(item.getId()).isNotBlank();
                    datasetItemId = item.getId();
                });
    }

    @Test
    @Order(2)
    void getDatasetItem() {
        assertThat(client.asyncDatasetItems().datasetItemsGet(
                DatasetItemsApi.APIDatasetItemsGetRequest.newBuilder()
                        .id(datasetItemId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(item ->
                        assertThat(item.getDatasetName()).isEqualTo(DATASET_NAME));
    }

    @Test
    @Order(2)
    void listDatasetItems() {
        assertThat(client.asyncDatasetItems().datasetItemsList(
                DatasetItemsApi.APIDatasetItemsListRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(items ->
                        assertThat(items.getData())
                                .isNotEmpty()
                                .anyMatch(i -> datasetItemId.equals(i.getId())));
    }
}
