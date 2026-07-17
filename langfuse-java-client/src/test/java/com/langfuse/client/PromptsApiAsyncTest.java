package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.ChatMessage;
import com.langfuse.api.model.ChatMessageWithPlaceholders;
import com.langfuse.api.model.CreateChatPromptRequest;
import com.langfuse.api.model.CreateChatPromptType;
import com.langfuse.api.model.CreatePromptRequest;
import com.langfuse.api.model.CreateTextPromptRequest;
import com.langfuse.api.model.CreateTextPromptType;
import com.langfuse.api.prompts.PromptsApi;

/**
 * Async integration tests for the Prompts API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PromptsApiAsyncTest extends AbstractLangfuseClientTest {

    private static final String TEXT_PROMPT_NAME = "async-test-text-prompt-" + UUID.randomUUID();
    private static final String CHAT_PROMPT_NAME = "async-test-chat-prompt-" + UUID.randomUUID();

    @Test
    @Order(1)
    void createTextPrompt() {
        assertThat(client.asyncPrompts().promptsCreate(
                PromptsApi.APIPromptsCreateRequest.newBuilder()
                        .createPromptRequest(new CreatePromptRequest(
                                CreateTextPromptRequest.builder()
                                        .name(TEXT_PROMPT_NAME)
                                        .prompt("Hello {{name}}, welcome to async Langfuse!")
                                        .type(CreateTextPromptType.TEXT)
                                        .labels(List.of("production"))
                                        .build()))
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }

    @Test
    @Order(1)
    void createChatPrompt() {
        assertThat(client.asyncPrompts().promptsCreate(
                PromptsApi.APIPromptsCreateRequest.newBuilder()
                        .createPromptRequest(new CreatePromptRequest(
                                CreateChatPromptRequest.builder()
                                        .name(CHAT_PROMPT_NAME)
                                        .type(CreateChatPromptType.CHAT)
                                        .prompt(List.of(new ChatMessageWithPlaceholders(
                                                ChatMessage.builder()
                                                        .role("system")
                                                        .content("You are an async assistant.")
                                                        .build())))
                                        .labels(List.of("production"))
                                        .build()))
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }

    @Test
    @Order(2)
    void fetchTextPrompt() {
        assertThat(client.asyncPrompts().promptsGet(
                PromptsApi.APIPromptsGetRequest.newBuilder()
                        .promptName(TEXT_PROMPT_NAME)
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }

    @Test
    @Order(2)
    void fetchChatPrompt() {
        assertThat(client.asyncPrompts().promptsGet(
                PromptsApi.APIPromptsGetRequest.newBuilder()
                        .promptName(CHAT_PROMPT_NAME)
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }

    @Test
    @Order(2)
    void listPromptsContainsCreatedPrompts() {
        assertThat(client.asyncPrompts().promptsList(
                PromptsApi.APIPromptsListRequest.newBuilder()
                        .build())
                )
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(prompts ->
                        assertThat(prompts.getData()).isNotEmpty());
    }
}
