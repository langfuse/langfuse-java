package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsCreateRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsDeleteRunRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsGetRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsGetRunRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsGetRunsRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsListRequest;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.model.CreateDatasetRunItemRequest;
import com.langfuse.api.model.Dataset;

/**
 * Async integration tests for the Datasets API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class DatasetsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String DATASET_NAME = "async-test-dataset-" + UUID.randomUUID();
    private final String RUN_NAME = "async-test-run-" + UUID.randomUUID().toString().substring(0, 8);
    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String datasetId;

    @Test
    @Order(1)
    void createDataset() {
        assertThat(client.asyncDatasets().datasetsCreate(
                APIDatasetsCreateRequest.newBuilder()
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
                APIDatasetsGetRequest.newBuilder()
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
                APIDatasetsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(datasets ->
                        assertThat(datasets.getData())
                                .isNotEmpty()
                                .anyMatch(d -> DATASET_NAME.equals(d.getName())));
    }

    @Test
    @Order(3)
    void createRunForDataset() {
        var item = client.datasetItems().datasetItemsCreate(
                APIDatasetItemsCreateRequest.newBuilder()
                        .createDatasetItemRequest(CreateDatasetItemRequest.builder()
                                .datasetName(DATASET_NAME)
                                .input(Map.of("question", "async test"))
                                .build())
                        .build());

        ingestTrace(TRACE_ID, SPAN_ID, "async-datasets-run-test-trace");

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
        assertThat(client.asyncDatasets().datasetsGetRuns(
                APIDatasetsGetRunsRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }

    @Test
    @Order(4)
    void getRunByNameReturns404InEventsOnlyMode() {
        assertThat(client.asyncDatasets().datasetsGetRun(
                APIDatasetsGetRunRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .runName(RUN_NAME)
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }

    @Test
    @Order(5)
    void deleteRunReturns404InEventsOnlyMode() {
        assertThat(client.asyncDatasets().datasetsDeleteRun(
                APIDatasetsDeleteRunRequest.newBuilder()
                        .datasetName(DATASET_NAME)
                        .runName(RUN_NAME)
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }
}
