package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

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
 * Integration tests for the Prompts API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PromptsApiTest extends AbstractLangfuseClientTest {

    private static final String TEXT_PROMPT_NAME = "test-text-prompt-" + UUID.randomUUID();
    private static final String CHAT_PROMPT_NAME = "test-chat-prompt-" + UUID.randomUUID();

    @Test
    @Order(1)
    void createTextPrompt() {
        var prompt = client.prompts().promptsCreate(
                PromptsApi.APIPromptsCreateRequest.newBuilder()
                        .createPromptRequest(new CreatePromptRequest(
                                CreateTextPromptRequest.builder()
                                        .name(TEXT_PROMPT_NAME)
                                        .prompt("Hello {{name}}, welcome to Langfuse!")
                                        .type(CreateTextPromptType.TEXT)
                                        .labels(List.of("production"))
                                        .build()))
                        .build());

        assertThat(prompt)
                .isNotNull();

    }

    @Test
    @Order(1)
    void createChatPrompt() {
        var prompt = client.prompts().promptsCreate(
                PromptsApi.APIPromptsCreateRequest.newBuilder()
                        .createPromptRequest(new CreatePromptRequest(
                                CreateChatPromptRequest.builder()
                                        .name(CHAT_PROMPT_NAME)
                                        .type(CreateChatPromptType.CHAT)
                                        .prompt(List.of(new ChatMessageWithPlaceholders(
                                                ChatMessage.builder()
                                                        .role("system")
                                                        .content("You are a helpful assistant.")
                                                        .build())))
                                        .labels(List.of("production"))
                                        .build()))
                        .build());

        assertThat(prompt)
                .isNotNull();

    }

    @Test
    @Order(2)
    void fetchTextPrompt() {
        var prompt = client.prompts().promptsGet(
                PromptsApi.APIPromptsGetRequest.newBuilder()
                        .promptName(TEXT_PROMPT_NAME)
                        .build());

        assertThat(prompt)
                .isNotNull();

    }

    @Test
    @Order(2)
    void fetchChatPrompt() {
        var prompt = client.prompts().promptsGet(
                PromptsApi.APIPromptsGetRequest.newBuilder()
                        .promptName(CHAT_PROMPT_NAME)
                        .build());

        assertThat(prompt)
                .isNotNull();

    }

    @Test
    @Order(2)
    void listPromptsContainsCreatedPrompts() {
        var prompts = client.prompts().promptsList(
                PromptsApi.APIPromptsListRequest.newBuilder()
                        .build());

        assertThat(prompts.getData())
                .hasSizeGreaterThanOrEqualTo(2);

        assertThat(prompts.getMeta().getTotalItems())
                .isGreaterThanOrEqualTo(2);

        assertThat(prompts.getData())
                .anyMatch(p -> TEXT_PROMPT_NAME.equals(p.getName()))
                .anyMatch(p -> CHAT_PROMPT_NAME.equals(p.getName()));
    }
}
