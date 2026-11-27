package com.langfuse.client;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Utility for integration tests: loads Langfuse credentials from a {@code .env}
 * file (or system/environment variables) and builds a {@link LangfuseClient}.
 */
public final class TestClientFactory {

    private static final Dotenv DOTENV = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private TestClientFactory() {}

    public static String getEnv(String key) {
        // dotenv-java checks .env first, then falls back to system env
        return DOTENV.get(key);
    }

    public static boolean hasCredentials() {
        String publicKey = getEnv("LANGFUSE_PUBLIC_KEY");
        String secretKey = getEnv("LANGFUSE_SECRET_KEY");
        String host = getEnv("LANGFUSE_HOST");
        return publicKey != null && !publicKey.isEmpty()
                && secretKey != null && !secretKey.isEmpty()
                && host != null && !host.isEmpty();
    }

    public static LangfuseClient createClient() {
        return LangfuseClient.builder()
                .credentials(getEnv("LANGFUSE_PUBLIC_KEY"), getEnv("LANGFUSE_SECRET_KEY"))
                .url(getEnv("LANGFUSE_HOST"))
                .build();
    }
}
