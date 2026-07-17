package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.CreateModelRequest;
import com.langfuse.api.model.Model;
import com.langfuse.api.model.ModelUsageUnit;
import com.langfuse.api.models.ModelsApi;

/**
 * Async integration tests for the Models API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModelsApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String MODEL_NAME = "async-model-" + UUID.randomUUID().toString().substring(0, 8);
    private static String modelId;

    @Test
    @Order(1)
    void createModel() {
        assertThat(client.asyncModels().modelsCreate(
                ModelsApi.APIModelsCreateRequest.newBuilder()
                        .createModelRequest(CreateModelRequest.builder()
                                .modelName(MODEL_NAME)
                                .matchPattern("(?i)^(%s)(-.+)?$".formatted(MODEL_NAME))
                                .unit(ModelUsageUnit.TOKENS)
                                .inputPrice(0.001)
                                .outputPrice(0.002)
                                .tokenizerId("openai")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(model -> {
                    assertThat(model.getId()).isNotBlank();
                    assertThat(model.getModelName()).isEqualTo(MODEL_NAME);
                    modelId = model.getId();
                });
    }

    @Test
    @Order(2)
    void getModel() {
        assertThat(client.asyncModels().modelsGet(
                ModelsApi.APIModelsGetRequest.newBuilder()
                        .id(modelId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .extracting(Model::getModelName, Model::getUnit)
                .containsExactly(MODEL_NAME, ModelUsageUnit.TOKENS);
    }

    @Test
    @Order(2)
    void listModels() {
        assertThat(client.asyncModels().modelsList(
                ModelsApi.APIModelsListRequest.newBuilder()
                        .limit(100)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(models -> {
                    assertThat(models.getData()).isNotEmpty();
                    assertThat(models.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }

    @Test
    @Order(3)
    void deleteModel() {
        assertThat(client.asyncModels().modelsDelete(
                ModelsApi.APIModelsDeleteRequest.newBuilder()
                        .id(modelId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5));
    }
}
