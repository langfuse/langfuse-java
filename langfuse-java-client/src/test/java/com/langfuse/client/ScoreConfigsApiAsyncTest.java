package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
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
 * Async integration tests for the Score Configs API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoreConfigsApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String CONFIG_NAME = "async-cfg-" + UUID.randomUUID().toString().substring(0, 8);
    private static String configId;

    @Test
    @Order(1)
    void createNumericScoreConfig() {
        assertThat(client.asyncScoreConfigs().scoreConfigsCreate(
                ScoreConfigsApi.APIScoreConfigsCreateRequest.newBuilder()
                        .createScoreConfigRequest(CreateScoreConfigRequest.builder()
                                .name(CONFIG_NAME)
                                .dataType(ScoreConfigDataType.NUMERIC)
                                .minValue(0.0)
                                .maxValue(1.0)
                                .description("Async numeric score config")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(config -> {
                    assertThat(config.getId()).isNotBlank();
                    assertThat(config.getName()).isEqualTo(CONFIG_NAME);
                    assertThat(config.getDataType()).isEqualTo(ScoreConfigDataType.NUMERIC);
                    configId = config.getId();
                });
    }

    @Test
    @Order(2)
    void getScoreConfigById() {
        assertThat(client.asyncScoreConfigs().scoreConfigsGetById(
                ScoreConfigsApi.APIScoreConfigsGetByIdRequest.newBuilder()
                        .configId(configId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .extracting(ScoreConfig::getName, ScoreConfig::getMinValue, ScoreConfig::getMaxValue)
                .containsExactly(CONFIG_NAME, 0.0, 1.0);
    }

    @Test
    @Order(2)
    void listScoreConfigs() {
        assertThat(client.asyncScoreConfigs().scoreConfigsGet(
                ScoreConfigsApi.APIScoreConfigsGetRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(configs ->
                        assertThat(configs.getData())
                                .isNotEmpty()
                                .anyMatch(c -> CONFIG_NAME.equals(c.getName())));
    }
}
