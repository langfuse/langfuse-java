package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasets.DatasetsApi;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.model.Dataset;

/**
 * Async integration tests for the Datasets API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatasetsApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String DATASET_NAME = "async-test-dataset-" + UUID.randomUUID();
    private static String datasetId;

    @Test
    @Order(1)
    void createDataset() {
        assertThat(client.asyncDatasets().datasetsCreate(
                DatasetsApi.APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .description("Async test dataset")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(dataset -> {
                    assertThat(dataset.getId()).isNotBlank();
                    assertThat(dataset.getName()).isEqualTo(DATASET_NAME);
                    datasetId = dataset.getId();
                });
    }

    @Test
    @Order(2)
    void getDatasetByName() {
        assertThat(client.asyncDatasets().datasetsGet(
                DatasetsApi.APIDatasetsGetRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .extracting(Dataset::getId, Dataset::getDescription)
                .containsExactly(datasetId, "Async test dataset");
    }

    @Test
    @Order(2)
    void listDatasetsContainsCreated() {
        assertThat(client.asyncDatasets().datasetsList(
                DatasetsApi.APIDatasetsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(datasets ->
                        assertThat(datasets.getData())
                                .isNotEmpty()
                                .anyMatch(d -> DATASET_NAME.equals(d.getName())));
    }
}
