package com.langfuse.client.prompt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class PromptTemplate {

    private static final String OPENING = "{{";
    private static final String CLOSING = "}}";

    private PromptTemplate() {
    }

    public static String compile(String template, Map<String, ?> variables) {
        if (template == null || template.isEmpty()) {
            return template;
        }
        if (variables == null || variables.isEmpty()) {
            return template;
        }

        StringBuilder result = new StringBuilder();
        int currentIndex = 0;

        while (currentIndex < template.length()) {
            int openIndex = template.indexOf(OPENING, currentIndex);
            if (openIndex == -1) {
                result.append(template, currentIndex, template.length());
                break;
            }

            int closeIndex = template.indexOf(CLOSING, openIndex + OPENING.length());
            if (closeIndex == -1) {
                result.append(template, currentIndex, template.length());
                break;
            }

            result.append(template, currentIndex, openIndex);

            String variableName = template.substring(openIndex + OPENING.length(), closeIndex).trim();

            if (variables.containsKey(variableName)) {
                Object value = variables.get(variableName);
                result.append(value != null ? String.valueOf(value) : "");
            } else {
                result.append(template, openIndex, closeIndex + CLOSING.length());
            }

            currentIndex = closeIndex + CLOSING.length();
        }

        return result.toString();
    }

    public static List<String> extractVariables(String template) {
        if (template == null || template.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> variables = new ArrayList<>();
        int currentIndex = 0;

        while (currentIndex < template.length()) {
            int openIndex = template.indexOf(OPENING, currentIndex);
            if (openIndex == -1) {
                break;
            }

            int closeIndex = template.indexOf(CLOSING, openIndex + OPENING.length());
            if (closeIndex == -1) {
                break;
            }

            String variableName = template.substring(openIndex + OPENING.length(), closeIndex).trim();
            if (!variableName.isEmpty() && !variables.contains(variableName)) {
                variables.add(variableName);
            }

            currentIndex = closeIndex + CLOSING.length();
        }

        return Collections.unmodifiableList(variables);
    }
}
