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
import com.langfuse.api.model.UpdateScoreConfigRequest;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi.APIScoreConfigsCreateRequest;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi.APIScoreConfigsGetByIdRequest;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi.APIScoreConfigsGetRequest;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi.APIScoreConfigsUpdateRequest;

/**
 * Async integration tests for the Score Configs API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ScoreConfigsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String CONFIG_NAME = "async-cfg-" + UUID.randomUUID().toString().substring(0, 8);
    private static String configId;

    @Test
    @Order(1)
    void createNumericScoreConfig() {
        assertThat(client.asyncScoreConfigs().scoreConfigsCreate(
                APIScoreConfigsCreateRequest.newBuilder()
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
                APIScoreConfigsGetByIdRequest.newBuilder()
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
                APIScoreConfigsGetRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(configs ->
                        assertThat(configs.getData())
                                .isNotEmpty()
                                .anyMatch(c -> CONFIG_NAME.equals(c.getName())));
    }

    @Test
    @Order(3)
    void updateScoreConfig() {
        assertThat(client.asyncScoreConfigs().scoreConfigsUpdate(
                APIScoreConfigsUpdateRequest.newBuilder()
                        .configId(configId)
                        .updateScoreConfigRequest(UpdateScoreConfigRequest.builder()
                                .description("Updated async description")
                                .isArchived(true)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(config -> {
                    assertThat(config.getId()).isEqualTo(configId);
                    assertThat(config.getDescription()).isEqualTo("Updated async description");
                    assertThat(config.getIsArchived()).isTrue();
                });
    }
}
