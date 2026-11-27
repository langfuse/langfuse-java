package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsCreateRequest;
import com.langfuse.api.datasetRunItems.DatasetRunItemsApi.APIDatasetRunItemsCreateRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsCreateRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsDeleteRunRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsGetRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsGetRunRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsGetRunsRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsListRequest;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.model.CreateDatasetRunItemRequest;
import com.langfuse.api.model.Dataset;

/**
 * Integration tests for the Datasets API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class DatasetsApiTest extends AbstractLangfuseClientTest {

    private final String DATASET_NAME = "test-dataset-" + UUID.randomUUID();
    private final String RUN_NAME = "test-run-" + UUID.randomUUID().toString().substring(0, 8);
    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String datasetId;

    @Test
    @Order(1)
    void createDataset() {
        assertThat(client.datasets().datasetsCreate(
                APIDatasetsCreateRequest.newBuilder()
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
                APIDatasetsGetRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .build()))
                .extracting(Dataset::getId, Dataset::getName, Dataset::getDescription)
                .containsExactly(datasetId, DATASET_NAME, "Test dataset for integration tests");
    }

    @Test
    @Order(2)
    void listDatasetsContainsCreated() {
        assertThat(client.datasets().datasetsList(
                APIDatasetsListRequest.newBuilder()
                        .build()))
                .satisfies(datasets -> {
                    assertThat(datasets.getData())
                            .isNotEmpty()
                            .anyMatch(d -> DATASET_NAME.equals(d.getName()));
                    assertThat(datasets.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }

    @Test
    @Order(3)
    void createRunForDataset() {
        var item = client.datasetItems().datasetItemsCreate(
                APIDatasetItemsCreateRequest.newBuilder()
                        .createDatasetItemRequest(CreateDatasetItemRequest.builder()
                                .datasetName(DATASET_NAME)
                                .input(Map.of("question", "test"))
                                .build())
                        .build());

        ingestTrace(TRACE_ID, SPAN_ID, "datasets-run-test-trace");

        client.datasetRunItems().datasetRunItemsCreate(
                APIDatasetRunItemsCreateRequest.newBuilder()
                        .createDatasetRunItemRequest(CreateDatasetRunItemRequest.builder()
                                .runName(RUN_NAME)
                                .datasetItemId(item.getId())
                                .traceId(TRACE_ID)
                                .build())
                        .build());
    }

    @Test
    @Order(4)
    void getRunsReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.datasets().datasetsGetRuns(
                        APIDatasetsGetRunsRequest.newBuilder()
                                .datasetName(DATASET_NAME)
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    @Order(4)
    void getRunByNameReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.datasets().datasetsGetRun(
                        APIDatasetsGetRunRequest.newBuilder()
                                .datasetName(DATASET_NAME)
                                .runName(RUN_NAME)
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }

    @Test
    @Order(5)
    void deleteRunReturns404InEventsOnlyMode() {
        assertThatThrownBy(() ->
                client.datasets().datasetsDeleteRun(
                        APIDatasetsDeleteRunRequest.newBuilder()
                                .datasetName(DATASET_NAME)
                                .runName(RUN_NAME)
                                .build()))
                .isInstanceOf(LangfuseApiException.class)
                .satisfies(e ->
                        assertThat(((LangfuseApiException) e).getStatusCode())
                                .isEqualTo(404));
    }
}
