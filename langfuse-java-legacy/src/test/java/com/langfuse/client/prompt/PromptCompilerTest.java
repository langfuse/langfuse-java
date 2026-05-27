package com.langfuse.client.prompt;

import com.langfuse.client.resources.prompts.types.ChatMessage;
import com.langfuse.client.resources.prompts.types.ChatMessageWithPlaceholders;
import com.langfuse.client.resources.prompts.types.ChatPrompt;
import com.langfuse.client.resources.prompts.types.PlaceholderMessage;
import com.langfuse.client.resources.prompts.types.TextPrompt;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PromptCompilerTest {

    @Test
    void compileTextPrompt() {
        TextPrompt prompt = TextPrompt.builder()
                .name("test")
                .version(1)
                .config(Map.of())
                .prompt("Hello {{name}}, you are a {{role}}")
                .build();

        String result = PromptCompiler.compile(prompt, Map.of("name", "Alice", "role", "developer"));
        assertEquals("Hello Alice, you are a developer", result);
    }

    @Test
    void compileTextPromptMissingVariable() {
        TextPrompt prompt = TextPrompt.builder()
                .name("test")
                .version(1)
                .config(Map.of())
                .prompt("Hello {{name}}, age {{age}}")
                .build();

        String result = PromptCompiler.compile(prompt, Map.of("name", "Alice"));
        assertEquals("Hello Alice, age {{age}}", result);
    }

    @Test
    void compileChatPrompt() {
        ChatPrompt prompt = ChatPrompt.builder()
                .name("test")
                .version(1)
                .config(Map.of())
                .prompt(List.of(
                        ChatMessageWithPlaceholders.of(
                                ChatMessage.builder().role("system").content("You are a {{role}}").build()),
                        ChatMessageWithPlaceholders.of(
                                ChatMessage.builder().role("user").content("{{user_input}}").build())
                ))
                .build();

        List<ChatMessage> result = PromptCompiler.compile(prompt, Map.of("role", "helpful assistant", "user_input", "Hi"));

        assertEquals(2, result.size());
        assertEquals("system", result.get(0).getRole());
        assertEquals("You are a helpful assistant", result.get(0).getContent());
        assertEquals("user", result.get(1).getRole());
        assertEquals("Hi", result.get(1).getContent());
    }

    @Test
    void compileChatPromptSkipsPlaceholders() {
        ChatPrompt prompt = ChatPrompt.builder()
                .name("test")
                .version(1)
                .config(Map.of())
                .prompt(List.of(
                        ChatMessageWithPlaceholders.of(
                                ChatMessage.builder().role("system").content("You are a {{role}}").build()),
                        ChatMessageWithPlaceholders.of(
                                PlaceholderMessage.builder().name("examples").build()),
                        ChatMessageWithPlaceholders.of(
                                ChatMessage.builder().role("user").content("{{question}}").build())
                ))
                .build();

        List<ChatMessage> result = PromptCompiler.compile(prompt, Map.of("role", "tutor", "question", "What is Java?"));

        assertEquals(2, result.size());
        assertEquals("You are a tutor", result.get(0).getContent());
        assertEquals("What is Java?", result.get(1).getContent());
    }

    @Test
    void compileChatPromptEmpty() {
        ChatPrompt prompt = ChatPrompt.builder()
                .name("test")
                .version(1)
                .config(Map.of())
                .build();

        List<ChatMessage> result = PromptCompiler.compile(prompt, Map.of("x", "y"));
        assertTrue(result.isEmpty());
    }
}
