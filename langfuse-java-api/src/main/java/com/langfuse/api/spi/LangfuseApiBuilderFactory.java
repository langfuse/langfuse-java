package com.langfuse.api.spi;

import com.langfuse.api.LangfuseApi;
import com.langfuse.api.LangfuseApi.LangfuseApiBuilder;

/**
 * SPI factory interface for creating {@link LangfuseApiBuilder} instances.
 *
 * <p>Implementations are discovered via {@link java.util.ServiceLoader} and must be registered
 * in {@code META-INF/services/com.langfuse.api.spi.LangfuseApiBuilderFactory}.
 *
 * @author Eric Deandrea
 * @see LangfuseApi#builder()
 */
public interface LangfuseApiBuilderFactory {

    /**
     * Creates a new builder for constructing a {@link LangfuseApi} implementation.
     *
     * @param <T> the concrete {@link LangfuseApi} implementation type
     * @param <B> the concrete builder type
     * @return a new builder instance
     */
    <T extends LangfuseApi, B extends LangfuseApiBuilder<T, B>> B getBuilder();
}
