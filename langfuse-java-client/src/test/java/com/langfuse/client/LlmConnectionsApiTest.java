package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.llmConnections.LlmConnectionsApi.APILlmConnectionsDeleteRequest;
import com.langfuse.api.llmConnections.LlmConnectionsApi.APILlmConnectionsListRequest;
import com.langfuse.api.llmConnections.LlmConnectionsApi.APILlmConnectionsUpsertRequest;
import com.langfuse.api.model.LlmAdapter;
import com.langfuse.api.model.UpsertLlmConnectionRequest;

/**
 * Integration tests for the LLM Connections API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class LlmConnectionsApiTest extends AbstractLangfuseClientTest {

    private static String connectionId;

    @Test
    @Order(1)
    void listLlmConnections() {
        assertThat(client.llmConnections().llmConnectionsList(
                APILlmConnectionsListRequest.newBuilder()
                        .build()))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }

    @Test
    @Order(2)
    void upsertLlmConnection() {
        assertThat(client.llmConnections().llmConnectionsUpsert(
                APILlmConnectionsUpsertRequest.newBuilder()
                        .upsertLlmConnectionRequest(UpsertLlmConnectionRequest.builder()
                                .provider("test-provider")
                                .adapter(LlmAdapter.OPENAI)
                                .secretKey("sk-test-placeholder")
                                .build())
                        .build()))
                .satisfies(connection -> {
                    assertThat(connection.getId()).isNotBlank();
                    assertThat(connection.getProvider()).isEqualTo("test-provider");
                    assertThat(connection.getAdapter()).isEqualTo(LlmAdapter.OPENAI.getValue());
                    connectionId = connection.getId();
                });
    }

    @Test
    @Order(3)
    void deleteLlmConnection() {
        assertThat(client.llmConnections().llmConnectionsDelete(
                APILlmConnectionsDeleteRequest.newBuilder()
                        .id(connectionId)
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
