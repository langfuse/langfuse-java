package com.langfuse.client;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.langfuse.api.LangfuseApi;
import com.langfuse.testcontainers.LangfuseContainer;

/**
 * Abstract base class for Langfuse client integration tests.
 *
 * <p>Uses the Testcontainers singleton container pattern to start a single
 * {@link LangfuseContainer} instance that is shared across all test classes.
 * The container starts once per JVM and is cleaned up automatically by Ryuk.
 *
 * @author Eric Deandrea
 * @see <a href="https://testcontainers.com/guides/testcontainers-container-lifecycle/#_using_singleton_containers">Singleton Containers</a>
 */
abstract class AbstractLangfuseClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractLangfuseClientTest.class);

    static LangfuseContainer langfuse = new LangfuseContainer();
    static LangfuseApi client;

    static {
        langfuse.start();
        client = createClient();
    }

    @RegisterExtension
    TestWatcher watcher = new TestWatcher() {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            LOG.error("Test {}.{} failed: {}",
                    context.getTestClass().map(Class::getName).orElse(""),
                    context.getTestMethod().map(Method::getName).orElse(""),
                    Optional.ofNullable(cause).map(Throwable::getMessage).orElse(""));

            langfuse.getAllLogs().forEach((container, logs) ->
                    LOG.error("=== {} ===\n{}", container, logs));
        }
    };

    /**
     * Creates a new {@link LangfuseApi} client configured to connect to the shared container.
     *
     * @return a configured client instance
     */
    static LangfuseApi createClient() {
        return LangfuseApi.builder()
                .username(langfuse.getPublicKey())
                .password(langfuse.getSecretKey())
                .url(langfuse.getLangfuseUrl())
                .logRequests()
                .logResponses()
                .prettyPrint()
                .build();
    }
}
