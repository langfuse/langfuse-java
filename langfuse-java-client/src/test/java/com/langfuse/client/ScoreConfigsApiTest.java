package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.CreateScoreConfigRequest;
import com.langfuse.api.model.ScoreConfig;
import com.langfuse.api.model.ScoreConfigDataType;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi;

/**
 * Integration tests for the Score Configs API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoreConfigsApiTest extends AbstractLangfuseClientTest {

    private static final String CONFIG_NAME = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
    private static String configId;

    @Test
    @Order(1)
    void createNumericScoreConfig() {
        assertThat(client.scoreConfigs().scoreConfigsCreate(
                ScoreConfigsApi.APIScoreConfigsCreateRequest.newBuilder()
                        .createScoreConfigRequest(CreateScoreConfigRequest.builder()
                                .name(CONFIG_NAME)
                                .dataType(ScoreConfigDataType.NUMERIC)
                                .minValue(0.0)
                                .maxValue(1.0)
                                .description("Numeric score for integration test")
                                .build())
                        .build()))
                .satisfies(config -> {
                    assertThat(config.getId()).isNotBlank();
                    assertThat(config.getName()).isEqualTo(CONFIG_NAME);
                    assertThat(config.getDataType()).isEqualTo(ScoreConfigDataType.NUMERIC);
                    assertThat(config.getDescription()).isEqualTo("Numeric score for integration test");
                    assertThat(config.getIsArchived()).isFalse();
                    assertThat(config.getCreatedAt()).isNotNull();
                    configId = config.getId();
                });
    }

    @Test
    @Order(2)
    void getScoreConfigById() {
        assertThat(client.scoreConfigs().scoreConfigsGetById(
                ScoreConfigsApi.APIScoreConfigsGetByIdRequest.newBuilder()
                        .configId(configId)
                        .build()))
                .extracting(ScoreConfig::getId, ScoreConfig::getName, ScoreConfig::getMinValue, ScoreConfig::getMaxValue, ScoreConfig::getDataType)
                .containsExactly(configId, CONFIG_NAME, 0.0, 1.0, ScoreConfigDataType.NUMERIC);
    }

    @Test
    @Order(2)
    void listScoreConfigs() {
        assertThat(client.scoreConfigs().scoreConfigsGet(
                ScoreConfigsApi.APIScoreConfigsGetRequest.newBuilder()
                        .build()))
                .satisfies(configs -> {
                    assertThat(configs.getData())
                            .isNotEmpty()
                            .anyMatch(c -> CONFIG_NAME.equals(c.getName()));
                    assertThat(configs.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }
}
