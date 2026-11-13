package com.langfuse.client;

import com.langfuse.api.LangfuseApi;
import com.langfuse.api.LangfuseApi.LangfuseApiBuilder;
import com.langfuse.api.spi.LangfuseApiBuilderFactory;
import com.langfuse.client.LangfuseClient.LangfuseClientBuilder;

/**
 * {@link LangfuseApiBuilderFactory} implementation that auto-detects the Jackson version
 * on the classpath and returns the appropriate builder.
 *
 * <p>Jackson 3 is preferred when both versions are present.
 *
 * @author Eric Deandrea
 */
public final class LangfuseClientBuilderFactory implements LangfuseApiBuilderFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends LangfuseApi, B extends LangfuseApiBuilder<T, B>> B getBuilder() {
        return (B) newBuilder(Thread.currentThread().getContextClassLoader());
    }

    private static LangfuseClientBuilder<?, ?> newBuilder(ClassLoader classLoader) {
        if (JacksonVersion.JACKSON_3.isOnClasspath(classLoader)) {
            return LangfuseJackson3Client.builder();
        }

        if (JacksonVersion.JACKSON_2.isOnClasspath(classLoader)) {
            return LangfuseJackson2Client.builder();
        }

        throw new IllegalStateException(
                "Neither Jackson 2 nor Jackson 3 found on the classpath. "
                + "Add com.fasterxml.jackson.core:jackson-databind (Jackson 2) "
                + "or tools.jackson.core:jackson-databind (Jackson 3) to your dependencies.");
    }

    /**
     * Detects Jackson versions by probing for well-known classes on the classpath.
     *
     * @author Eric Deandrea
     */
    private enum JacksonVersion {
        JACKSON_3("tools.jackson.databind.json.JsonMapper"),
        JACKSON_2("com.fasterxml.jackson.databind.json.JsonMapper");

        private final String className;

        JacksonVersion(String className) {
            this.className = className;
        }

        /**
         * Checks whether this Jackson version is available on the given classloader.
         * Uses {@link Class#forName(String, boolean, ClassLoader)} with {@code initialize=false}
         * to avoid triggering static initializers.
         *
         * @param classLoader the classloader to check
         * @return {@code true} if the Jackson version is available
         */
        boolean isOnClasspath(ClassLoader classLoader) {
            try {
                Class.forName(className, false, classLoader);
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }
    }
}
