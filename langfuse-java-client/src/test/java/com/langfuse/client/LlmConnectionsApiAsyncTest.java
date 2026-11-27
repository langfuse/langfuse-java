package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.llmConnections.LlmConnectionsApi.APILlmConnectionsDeleteRequest;
import com.langfuse.api.llmConnections.LlmConnectionsApi.APILlmConnectionsListRequest;
import com.langfuse.api.llmConnections.LlmConnectionsApi.APILlmConnectionsUpsertRequest;
import com.langfuse.api.model.LlmAdapter;
import com.langfuse.api.model.UpsertLlmConnectionRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class LlmConnectionsApiAsyncTest extends AbstractLangfuseClientTest {

    private static String connectionId;

    @Test
    @Order(1)
    void listLlmConnections() {
        assertThat(client.asyncLlmConnections().llmConnectionsList(
                APILlmConnectionsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }

    @Test
    @Order(2)
    void upsertLlmConnection() {
        assertThat(client.asyncLlmConnections().llmConnectionsUpsert(
                APILlmConnectionsUpsertRequest.newBuilder()
                        .upsertLlmConnectionRequest(UpsertLlmConnectionRequest.builder()
                                .provider("async-test-provider")
                                .adapter(LlmAdapter.OPENAI)
                                .secretKey("sk-async-test-placeholder")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(connection -> {
                    assertThat(connection.getId()).isNotBlank();
                    assertThat(connection.getProvider()).isEqualTo("async-test-provider");
                    assertThat(connection.getAdapter()).isEqualTo(LlmAdapter.OPENAI.getValue());
                    connectionId = connection.getId();
                });
    }

    @Test
    @Order(3)
    void deleteLlmConnection() {
        assertThat(client.asyncLlmConnections().llmConnectionsDelete(
                APILlmConnectionsDeleteRequest.newBuilder()
                        .id(connectionId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
