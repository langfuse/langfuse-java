package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.datasetItems.DatasetItemsApi.APIDatasetItemsCreateRequest;
import com.langfuse.api.datasetRunItems.DatasetRunItemsApi.APIDatasetRunItemsCreateRequest;
import com.langfuse.api.datasets.DatasetsApi.APIDatasetsCreateRequest;
import com.langfuse.api.experiments.ExperimentsApi.APIExperimentsListItemsRequest;
import com.langfuse.api.experiments.ExperimentsApi.APIExperimentsListRequest;
import com.langfuse.api.model.CreateDatasetItemRequest;
import com.langfuse.api.model.CreateDatasetRequest;
import com.langfuse.api.model.CreateDatasetRunItemRequest;

/**
 * Integration tests for the Experiments API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ExperimentsApiTest extends AbstractLangfuseClientTest {

    private final OffsetDateTime TEST_START = OffsetDateTime.now();
    private final String DATASET_NAME = "test-experiments-dataset-" + UUID.randomUUID();
    private final String RUN_NAME = "test-experiment-run-" + UUID.randomUUID().toString().substring(0, 8);
    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String datasetItemId;

    @Test
    @Order(1)
    void setupDatasetAndExperiment() {
        client.datasets().datasetsCreate(
                APIDatasetsCreateRequest.newBuilder()
                        .createDatasetRequest(CreateDatasetRequest.builder()
                                .name(DATASET_NAME)
                                .build())
                        .build());

        var item = client.datasetItems().datasetItemsCreate(
                APIDatasetItemsCreateRequest.newBuilder()
                        .createDatasetItemRequest(CreateDatasetItemRequest.builder()
                                .datasetName(DATASET_NAME)
                                .input(Map.of("question", "What is an experiment?"))
                                .build())
                        .build());

        assertThat(item.getId()).isNotBlank();
        datasetItemId = item.getId();

        ingestTrace(TRACE_ID, SPAN_ID, "experiment-test-trace");

        client.datasetRunItems().datasetRunItemsCreate(
                APIDatasetRunItemsCreateRequest.newBuilder()
                        .createDatasetRunItemRequest(CreateDatasetRunItemRequest.builder()
                                .runName(RUN_NAME)
                                .datasetItemId(datasetItemId)
                                .traceId(TRACE_ID)
                                .build())
                        .build());
    }

    @Test
    @Order(2)
    void listExperiments() {
        assertThat(client.experiments().experimentsList(
                APIExperimentsListRequest.newBuilder()
                        .fromStartTime(TEST_START.minusMinutes(1))
                        .limit(50)
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getData()).isNotNull();
                    assertThat(response.getMeta()).isNotNull();
                });
    }

    @Test
    @Order(2)
    void listExperimentItems() {
        assertThat(client.experiments().experimentsListItems(
                APIExperimentsListItemsRequest.newBuilder()
                        .fromStartTime(TEST_START.minusMinutes(1))
                        .limit(50)
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getData()).isNotNull();
                    assertThat(response.getMeta()).isNotNull();
                });
    }
}
