package com.langfuse.client.prompt;

import com.langfuse.client.resources.prompts.types.ChatMessage;
import com.langfuse.client.resources.prompts.types.ChatMessageWithPlaceholders;
import com.langfuse.client.resources.prompts.types.ChatPrompt;
import com.langfuse.client.resources.prompts.types.PlaceholderMessage;
import com.langfuse.client.resources.prompts.types.TextPrompt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class PromptCompiler {

    private PromptCompiler() {
    }

    public static String compile(TextPrompt prompt, Map<String, ?> variables) {
        return PromptTemplate.compile(prompt.getPrompt(), variables);
    }

    public static List<ChatMessage> compile(ChatPrompt prompt, Map<String, ?> variables) {
        if (prompt.getPrompt() == null || prompt.getPrompt().isEmpty()) {
            return Collections.emptyList();
        }

        List<ChatMessage> compiled = new ArrayList<>();

        for (ChatMessageWithPlaceholders messageOrPlaceholder : prompt.getPrompt()) {
            messageOrPlaceholder.visit(new ChatMessageWithPlaceholders.Visitor<Void>() {
                @Override
                public Void visit(ChatMessage chatMessage) {
                    String compiledContent = PromptTemplate.compile(chatMessage.getContent(), variables);
                    compiled.add(ChatMessage.builder()
                            .role(chatMessage.getRole())
                            .content(compiledContent)
                            .build());
                    return null;
                }

                @Override
                public Void visit(PlaceholderMessage placeholderMessage) {
                    // Placeholder messages are skipped in this version.
                    // A future version may support placeholder expansion.
                    return null;
                }
            });
        }

        return Collections.unmodifiableList(compiled);
    }
}
