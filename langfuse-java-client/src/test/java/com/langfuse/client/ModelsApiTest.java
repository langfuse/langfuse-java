package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

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
 * Integration tests for the Models API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModelsApiTest extends AbstractLangfuseClientTest {

    private static final String MODEL_NAME = "test-model-" + UUID.randomUUID().toString().substring(0, 8);
    private static String modelId;

    @Test
    @Order(1)
    void createModel() {
        assertThat(client.models().modelsCreate(
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
                .satisfies(model -> {
                    assertThat(model.getId()).isNotBlank();
                    assertThat(model.getModelName()).isEqualTo(MODEL_NAME);
                    assertThat(model.getMatchPattern()).isEqualTo("(?i)^(%s)(-.+)?$".formatted(MODEL_NAME));
                    assertThat(model.getUnit()).isEqualTo(ModelUsageUnit.TOKENS);
                    assertThat(model.getTokenizerId()).isEqualTo("openai");
                    assertThat(model.getIsLangfuseManaged()).isFalse();
                    modelId = model.getId();
                });
    }

    @Test
    @Order(2)
    void getModel() {
        assertThat(client.models().modelsGet(
                ModelsApi.APIModelsGetRequest.newBuilder()
                        .id(modelId)
                        .build()))
                .satisfies(model -> assertThat(model.getCreatedAt()).isNotNull())
                .extracting(Model::getId, Model::getModelName, Model::getUnit)
                .containsExactly(modelId, MODEL_NAME, ModelUsageUnit.TOKENS);
    }

    @Test
    @Order(2)
    void listModels() {
        assertThat(client.models().modelsList(
                ModelsApi.APIModelsListRequest.newBuilder()
                        .limit(100)
                        .build()))
                .satisfies(models -> {
                    assertThat(models.getData()).isNotEmpty();
                    assertThat(models.getMeta().getTotalItems()).isGreaterThan(0);
                    assertThat(models.getMeta().getPage()).isEqualTo(1);
                });
    }

    @Test
    @Order(3)
    void deleteModel() {
        client.models().modelsDelete(
                ModelsApi.APIModelsDeleteRequest.newBuilder()
                        .id(modelId)
                        .build());

        assertThat(client.models().modelsList(
                ModelsApi.APIModelsListRequest.newBuilder()
                        .build()))
                .satisfies(models ->
                        assertThat(models.getData())
                                .noneMatch(m -> modelId.equals(m.getId())));
    }
}
