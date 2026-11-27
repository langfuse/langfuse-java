package com.langfuse.client.prompt;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PromptTemplateTest {

    @Test
    void basicReplacement() {
        String result = PromptTemplate.compile("Hello {{name}}", Map.of("name", "World"));
        assertEquals("Hello World", result);
    }

    @Test
    void multipleVariables() {
        String template = "{{greeting}}, {{name}}! Welcome to {{place}}.";
        Map<String, String> vars = Map.of("greeting", "Hi", "name", "Alice", "place", "Langfuse");
        assertEquals("Hi, Alice! Welcome to Langfuse.", PromptTemplate.compile(template, vars));
    }

    @Test
    void duplicateVariables() {
        String result = PromptTemplate.compile("{{x}} and {{x}}", Map.of("x", "same"));
        assertEquals("same and same", result);
    }

    @Test
    void missingVariableKeepsOriginal() {
        String result = PromptTemplate.compile("Hello {{name}}, age {{age}}", Map.of("name", "Alice"));
        assertEquals("Hello Alice, age {{age}}", result);
    }

    @Test
    void extraVariablesIgnored() {
        String result = PromptTemplate.compile("Hello {{name}}", Map.of("name", "Alice", "extra", "ignored"));
        assertEquals("Hello Alice", result);
    }

    @Test
    void nullValueBecomesEmpty() {
        Map<String, Object> vars = new HashMap<>();
        vars.put("name", null);
        assertEquals("Hello ", PromptTemplate.compile("Hello {{name}}", vars));
    }

    @Test
    void noVariablesReturnsOriginal() {
        assertEquals("No variables here", PromptTemplate.compile("No variables here", Map.of("x", "y")));
    }

    @Test
    void emptyTemplate() {
        assertEquals("", PromptTemplate.compile("", Map.of("x", "y")));
    }

    @Test
    void nullTemplate() {
        assertNull(PromptTemplate.compile(null, Map.of("x", "y")));
    }

    @Test
    void nullVariables() {
        assertEquals("Hello {{name}}", PromptTemplate.compile("Hello {{name}}", null));
    }

    @Test
    void emptyVariables() {
        assertEquals("Hello {{name}}", PromptTemplate.compile("Hello {{name}}", Collections.emptyMap()));
    }

    @Test
    void whitespaceInVariableName() {
        String result = PromptTemplate.compile("Hello {{ name }}", Map.of("name", "Alice"));
        assertEquals("Hello Alice", result);
    }

    @Test
    void unclosedBracketKeepsOriginal() {
        assertEquals("Hello {{name", PromptTemplate.compile("Hello {{name", Map.of("name", "Alice")));
    }

    @Test
    void singleCurlyBracesIgnored() {
        assertEquals("Hello {name}", PromptTemplate.compile("Hello {name}", Map.of("name", "Alice")));
    }

    @Test
    void caseSensitive() {
        String result = PromptTemplate.compile("{{Name}} and {{name}}", Map.of("Name", "A", "name", "b"));
        assertEquals("A and b", result);
    }

    @Test
    void objectValueUsesToString() {
        String result = PromptTemplate.compile("Count: {{n}}", Map.of("n", 42));
        assertEquals("Count: 42", result);
    }

    @Test
    void jsonInTemplate() {
        String template = "Config: {\"key\": \"{{value}}\"}";
        assertEquals("Config: {\"key\": \"test\"}", PromptTemplate.compile(template, Map.of("value", "test")));
    }

    @Test
    void extractVariablesBasic() {
        List<String> vars = PromptTemplate.extractVariables("Hello {{name}}, welcome to {{place}}");
        assertEquals(List.of("name", "place"), vars);
    }

    @Test
    void extractVariablesDeduplicates() {
        List<String> vars = PromptTemplate.extractVariables("{{x}} and {{x}} and {{y}}");
        assertEquals(List.of("x", "y"), vars);
    }

    @Test
    void extractVariablesEmpty() {
        assertTrue(PromptTemplate.extractVariables("no variables").isEmpty());
        assertTrue(PromptTemplate.extractVariables("").isEmpty());
        assertTrue(PromptTemplate.extractVariables(null).isEmpty());
    }
}
