package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.CreateModelRequest;
import com.langfuse.api.model.Model;
import com.langfuse.api.model.ModelTokenizerId;
import com.langfuse.api.model.ModelUsageUnit;
import com.langfuse.api.models.ModelsApi.APIModelsCreateRequest;
import com.langfuse.api.models.ModelsApi.APIModelsDeleteRequest;
import com.langfuse.api.models.ModelsApi.APIModelsGetRequest;
import com.langfuse.api.models.ModelsApi.APIModelsListRequest;
import com.langfuse.api.models.ModelsApi.APIModelsUpsertRequest;

/**
 * Integration tests for the Models API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ModelsApiTest extends AbstractLangfuseClientTest {

    private final String MODEL_NAME = "test-model-" + UUID.randomUUID().toString().substring(0, 8);
    private static String modelId;

    @Test
    @Order(1)
    void createModel() {
        assertThat(client.models().modelsCreate(
                APIModelsCreateRequest.newBuilder()
                        .createModelRequest(CreateModelRequest.builder()
                                .modelName(MODEL_NAME)
                                .matchPattern("(?i)^(%s)(-.+)?$".formatted(MODEL_NAME))
                                .unit(ModelUsageUnit.TOKENS)
                                .inputPrice(0.001)
                                .outputPrice(0.002)
                                .tokenizerId(ModelTokenizerId.OPENAI)
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
    @Order(1)
    void upsertModel() {
        var upsertId = UUID.randomUUID().toString();
        var upsertModelName = "upsert-model-" + UUID.randomUUID().toString().substring(0, 8);

        assertThat(client.models().modelsUpsert(
                APIModelsUpsertRequest.newBuilder()
                        .id(upsertId)
                        .createModelRequest(CreateModelRequest.builder()
                                .modelName(upsertModelName)
                                .matchPattern("(?i)^(%s)(-.+)?$".formatted(upsertModelName))
                                .unit(ModelUsageUnit.TOKENS)
                                .inputPrice(0.001)
                                .outputPrice(0.002)
                                .tokenizerId(ModelTokenizerId.OPENAI)
                                .build())
                        .build()))
                .satisfies(model -> {
                    assertThat(model.getId()).isEqualTo(upsertId);
                    assertThat(model.getModelName()).isEqualTo(upsertModelName);
                    assertThat(model.getInputPrice()).isEqualTo(0.001);
                });

        assertThat(client.models().modelsUpsert(
                APIModelsUpsertRequest.newBuilder()
                        .id(upsertId)
                        .createModelRequest(CreateModelRequest.builder()
                                .modelName(upsertModelName)
                                .matchPattern("(?i)^(%s)(-.+)?$".formatted(upsertModelName))
                                .unit(ModelUsageUnit.TOKENS)
                                .inputPrice(0.005)
                                .outputPrice(0.010)
                                .tokenizerId(ModelTokenizerId.OPENAI)
                                .build())
                        .build()))
                .satisfies(model -> {
                    assertThat(model.getId()).isEqualTo(upsertId);
                    assertThat(model.getInputPrice()).isEqualTo(0.005);
                    assertThat(model.getOutputPrice()).isEqualTo(0.010);
                });

        client.models().modelsDelete(
                APIModelsDeleteRequest.newBuilder()
                        .id(upsertId)
                        .build());
    }

    @Test
    @Order(2)
    void getModel() {
        assertThat(client.models().modelsGet(
                APIModelsGetRequest.newBuilder()
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
                APIModelsListRequest.newBuilder()
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
                APIModelsDeleteRequest.newBuilder()
                        .id(modelId)
                        .build());

        assertThat(client.models().modelsList(
                APIModelsListRequest.newBuilder()
                        .build()))
                .satisfies(models ->
                        assertThat(models.getData())
                                .noneMatch(m -> modelId.equals(m.getId())));
    }
}
