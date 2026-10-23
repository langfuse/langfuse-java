package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsCreateRequest;
import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsDeleteRequest;
import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsGetRequest;
import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsListRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsCreateRequest;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;

/**
 * Async integration tests for the Dataset Items API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class DatasetItemsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String DATASET_NAME = "async-test-dataset-items-" + UUID.randomUUID();
    private static String datasetItemId;

    @Test
    @Order(1)
    void createDatasetAndItem() {
        client.datasets().datasetsCreate(
                APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .build())
                        .build());

        assertThat(client.asyncDatasetItems().datasetItemsCreate(
                APIDatasetItemsCreateRequest.newBuilder()
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
                APIDatasetItemsGetRequest.newBuilder()
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
                APIDatasetItemsListRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(items ->
                        assertThat(items.getData())
                                .isNotEmpty()
                                .anyMatch(i -> datasetItemId.equals(i.getId())));
    }

    @Test
    @Order(3)
    void deleteDatasetItem() {
        assertThat(client.asyncDatasetItems().datasetItemsDelete(
                APIDatasetItemsDeleteRequest.newBuilder()
                        .id(datasetItemId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
