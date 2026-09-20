package com.langfuse.client;

import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.langfuse.api.LangfuseApiException;

/**
 * {@link LangfuseClient} implementation using Jackson 2 ({@code com.fasterxml.jackson}) for JSON serialization.
 *
 * <p>Configures a default {@link ObjectMapper} with {@link JavaTimeModule},
 * {@code FAIL_ON_UNKNOWN_PROPERTIES} disabled, and {@code WRITE_DATES_AS_TIMESTAMPS} disabled
 * to match Jackson 3 defaults.
 *
 * <pre>{@code
 * LangfuseJackson2Client client = LangfuseJackson2Client.builder()
 *     .username("pk-lf-...")
 *     .password("sk-lf-...")
 *     .url("https://cloud.langfuse.com")
 *     .build();
 * }</pre>
 *
 * @author Eric Deandrea
 */
public final class LangfuseJackson2Client extends LangfuseClient {

    private final ObjectMapper objectMapper;

    LangfuseJackson2Client(Builder builder) {
        super(builder);
        this.objectMapper = builder.objectMapper != null ? builder.objectMapper : createDefaultObjectMapper();
    }

    @Override
    public <T> T readValue(String body, Type type) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructType(type);
            return objectMapper.readValue(body, javaType);
        } catch (Exception e) {
            throw new LangfuseApiException("Failed to deserialize response", e);
        }
    }

    @Override
    public byte[] writeValueAsBytes(Object value) {
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (Exception e) {
            throw new LangfuseApiException("Failed to serialize request", e);
        }
    }

    @Override
    public String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new LangfuseApiException("Failed to serialize value", e);
        }
    }

    @Override
    public String writeValueAsPrettyString(Object value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            throw new LangfuseApiException("Failed to serialize value", e);
        }
    }

    private static ObjectMapper createDefaultObjectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }

    /**
     * Creates a new builder for constructing a {@link LangfuseJackson2Client}.
     *
     * @return a new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for constructing {@link LangfuseJackson2Client} instances.
     *
     * @author Eric Deandrea
     */
    public static class Builder
            extends LangfuseClientBuilder<LangfuseJackson2Client, Builder> {

        private ObjectMapper objectMapper;

        private Builder() {
        }

        /**
         * Sets a custom {@link ObjectMapper} to use for JSON serialization.
         * If not set, a default mapper is created.
         *
         * @param objectMapper the custom ObjectMapper
         * @return this builder for method chaining
         */
        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        @Override
        public LangfuseJackson2Client build() {
            return new LangfuseJackson2Client(this);
        }
    }
}
