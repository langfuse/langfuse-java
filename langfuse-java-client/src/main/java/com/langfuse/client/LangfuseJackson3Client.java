package com.langfuse.client;

import java.lang.reflect.Type;

import tools.jackson.databind.JavaType;
import tools.jackson.databind.json.JsonMapper;

/**
 * {@link LangfuseClient} implementation using Jackson 3 ({@code tools.jackson}) for JSON serialization.
 *
 * <p>Jackson 3 defaults already disable {@code FAIL_ON_UNKNOWN_PROPERTIES} and
 * {@code WRITE_DATES_AS_TIMESTAMPS}, so no additional configuration is needed.
 *
 * <pre>{@code
 * LangfuseJackson3Client client = LangfuseJackson3Client.builder()
 *     .username("pk-lf-...")
 *     .password("sk-lf-...")
 *     .url("https://cloud.langfuse.com")
 *     .build();
 * }</pre>
 *
 * @author Eric Deandrea
 */
public final class LangfuseJackson3Client extends LangfuseClient {

    private final JsonMapper jsonMapper;

    LangfuseJackson3Client(Builder builder) {
        super(builder);
        this.jsonMapper = builder.jsonMapper != null ? builder.jsonMapper : createDefaultJsonMapper();
    }

    @Override
    public <T> T readValue(String body, Type type) {
        JavaType javaType = jsonMapper.getTypeFactory().constructType(type);
        return jsonMapper.readValue(body, javaType);
    }

    @Override
    public byte[] writeValueAsBytes(Object value) {
        return jsonMapper.writeValueAsBytes(value);
    }

    @Override
    public String writeValueAsString(Object value) {
        return jsonMapper.writeValueAsString(value);
    }

    @Override
    public String writeValueAsPrettyString(Object value) {
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    }

    private static JsonMapper createDefaultJsonMapper() {
        return JsonMapper.builder().build();
    }

    /**
     * Creates a new builder for constructing a {@link LangfuseJackson3Client}.
     *
     * @return a new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for constructing {@link LangfuseJackson3Client} instances.
     *
     * @author Eric Deandrea
     */
    public static class Builder
            extends LangfuseClientBuilder<LangfuseJackson3Client, Builder> {

        private JsonMapper jsonMapper;

        private Builder() {
        }

        /**
         * Sets a custom {@link JsonMapper} to use for JSON serialization.
         * If not set, a default mapper is created.
         *
         * @param jsonMapper the custom JsonMapper
         * @return this builder for method chaining
         */
        public Builder jsonMapper(JsonMapper jsonMapper) {
            this.jsonMapper = jsonMapper;
            return this;
        }

        @Override
        public LangfuseJackson3Client build() {
            return new LangfuseJackson3Client(this);
        }
    }
}
