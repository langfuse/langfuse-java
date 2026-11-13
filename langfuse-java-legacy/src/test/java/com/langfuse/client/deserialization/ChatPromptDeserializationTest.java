package com.langfuse.client.deserialization;

import static org.assertj.core.api.Assertions.assertThat;

import com.langfuse.client.core.ObjectMappers;
import com.langfuse.client.resources.prompts.types.ChatMessage;
import com.langfuse.client.resources.prompts.types.ChatMessageWithPlaceholders;
import com.langfuse.client.resources.prompts.types.ChatPrompt;
import com.langfuse.client.resources.prompts.types.PlaceholderMessage;
import com.langfuse.client.resources.prompts.types.Prompt;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for chat prompt deserialization through the same code path as
 * {@code PromptsClient.get()} — i.e. {@code ObjectMappers.JSON_MAPPER.readValue(..., Prompt.class)}.
 *
 * <p>These tests use a hardcoded JSON fixture and require no credentials or network access.
 * They exist primarily to catch the regression where {@code ChatMessage.getRole()} and
 * {@code ChatMessage.getContent()} return {@code null} after deserialization.
 */
class ChatPromptDeserializationTest {

    private static final String FIXTURE = "/fixtures/chat_prompt_response.json";

    @Test
    void deserializeChatPrompt_hasCorrectTopLevelFields() throws Exception {
        Prompt prompt = deserializeFixture();

        assertThat(prompt.isChat()).isTrue();
        assertThat(prompt.isText()).isFalse();

        ChatPrompt chatPrompt = prompt.getChat().orElseThrow();
        assertThat(chatPrompt.getName()).isEqualTo("test-chat-prompt");
        assertThat(chatPrompt.getVersion()).isEqualTo(1);
        assertThat(chatPrompt.getLabels()).contains("production");
    }

    @Test
    void deserializeChatPrompt_chatMessageHasNonNullRoleAndContent() throws Exception {
        Prompt prompt = deserializeFixture();
        ChatPrompt chatPrompt = prompt.getChat().orElseThrow();
        List<ChatMessageWithPlaceholders> messages = chatPrompt.getPrompt();

        assertThat(messages).hasSize(2);

        // First entry — chat message (no "type" discriminator in real API response)
        assertThat(messages.get(0).get()).isInstanceOf(ChatMessage.class);
        ChatMessage system = (ChatMessage) messages.get(0).get();
        assertThat(system.getRole()).isNotNull().isEqualTo("system");
        assertThat(system.getContent()).isNotNull().isEqualTo("Hello World");

        // Second entry — placeholder (has "type": "placeholder")
        assertThat(messages.get(1).get()).isInstanceOf(PlaceholderMessage.class);
        PlaceholderMessage placeholder = (PlaceholderMessage) messages.get(1).get();
        assertThat(placeholder.getName()).isEqualTo("username");
    }

    @Test
    void chatMessageRoundTripSerialization() throws Exception {
        Prompt prompt = deserializeFixture();
        ChatPrompt chatPrompt = prompt.getChat().orElseThrow();
        ChatMessage original = (ChatMessage) chatPrompt.getPrompt().get(0).get();

        // Serialize to JSON and back
        String json = ObjectMappers.JSON_MAPPER.writeValueAsString(original);
        ChatMessage roundTripped = ObjectMappers.JSON_MAPPER.readValue(json, ChatMessage.class);

        assertThat(roundTripped.getRole()).isEqualTo(original.getRole());
        assertThat(roundTripped.getContent()).isEqualTo(original.getContent());
    }

    private Prompt deserializeFixture() throws Exception {
        try (InputStream is = getClass().getResourceAsStream(FIXTURE)) {
            assertThat(is).as("Fixture %s must be on the classpath", FIXTURE).isNotNull();
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return ObjectMappers.JSON_MAPPER.readValue(json, Prompt.class);
        }
    }
}
