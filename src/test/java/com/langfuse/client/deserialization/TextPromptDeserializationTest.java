package com.langfuse.client.deserialization;

import static org.assertj.core.api.Assertions.assertThat;

import com.langfuse.client.core.ObjectMappers;
import com.langfuse.client.resources.prompts.types.Prompt;
import com.langfuse.client.resources.prompts.types.TextPrompt;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for text prompt deserialization through
 * {@code ObjectMappers.JSON_MAPPER.readValue(..., Prompt.class)}.
 *
 * <p>Uses a hardcoded JSON fixture — no credentials or network access required.
 */
class TextPromptDeserializationTest {

    private static final String FIXTURE = "/fixtures/text_prompt_response.json";

    @Test
    void deserializeTextPrompt_hasCorrectTopLevelFields() throws Exception {
        Prompt prompt = deserializeFixture();

        assertThat(prompt.isText()).isTrue();
        assertThat(prompt.isChat()).isFalse();

        TextPrompt textPrompt = prompt.getText().orElseThrow();
        assertThat(textPrompt.getName()).isEqualTo("test-text-prompt");
        assertThat(textPrompt.getVersion()).isEqualTo(1);
    }

    @Test
    void deserializeTextPrompt_promptIsNonNullAndNonEmpty() throws Exception {
        Prompt prompt = deserializeFixture();
        TextPrompt textPrompt = prompt.getText().orElseThrow();

        assertThat(textPrompt.getPrompt()).isNotNull().isNotEmpty();
        assertThat(textPrompt.getPrompt()).isEqualTo("Hello World");
    }

    private Prompt deserializeFixture() throws Exception {
        try (InputStream is = getClass().getResourceAsStream(FIXTURE)) {
            assertThat(is).as("Fixture %s must be on the classpath", FIXTURE).isNotNull();
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return ObjectMappers.JSON_MAPPER.readValue(json, Prompt.class);
        }
    }
}
