package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasets.DatasetsApi;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.model.Dataset;

/**
 * Integration tests for the Datasets API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatasetsApiTest extends AbstractLangfuseClientTest {

    private static final String DATASET_NAME = "test-dataset-" + UUID.randomUUID();
    private static String datasetId;

    @Test
    @Order(1)
    void createDataset() {
        assertThat(client.datasets().datasetsCreate(
                DatasetsApi.APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .description("Test dataset for integration tests")
                                .build())
                        .build()))
                .satisfies(dataset -> {
                    assertThat(dataset.getId()).isNotBlank();
                    assertThat(dataset.getName()).isEqualTo(DATASET_NAME);
                    assertThat(dataset.getDescription()).isEqualTo("Test dataset for integration tests");
                    assertThat(dataset.getProjectId()).isNotBlank();
                    assertThat(dataset.getCreatedAt()).isNotNull();
                    assertThat(dataset.getUpdatedAt()).isNotNull();
                    datasetId = dataset.getId();
                });
    }

    @Test
    @Order(2)
    void getDatasetByName() {
        assertThat(client.datasets().datasetsGet(
                DatasetsApi.APIDatasetsGetRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .build()))
                .extracting(Dataset::getId, Dataset::getName, Dataset::getDescription)
                .containsExactly(datasetId, DATASET_NAME, "Test dataset for integration tests");
    }

    @Test
    @Order(2)
    void listDatasetsContainsCreated() {
        assertThat(client.datasets().datasetsList(
                DatasetsApi.APIDatasetsListRequest.newBuilder()
                        .build()))
                .satisfies(datasets -> {
                    assertThat(datasets.getData())
                            .isNotEmpty()
                            .anyMatch(d -> DATASET_NAME.equals(d.getName()));
                    assertThat(datasets.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }
}
