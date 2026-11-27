package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.langfuse.api.LangfuseApi;
import com.langfuse.api.model.OpentelemetryExportTracesRequest;
import com.langfuse.api.model.OtelAttribute;
import com.langfuse.api.model.OtelAttributeValue;
import com.langfuse.api.model.OtelResource;
import com.langfuse.api.model.OtelResourceSpan;
import com.langfuse.api.model.OtelScope;
import com.langfuse.api.model.OtelScopeSpan;
import com.langfuse.api.model.OtelSpan;
import com.langfuse.api.opentelemetry.OpentelemetryApi.APIOpentelemetryExportTracesRequest;
import com.langfuse.testcontainers.LangfuseContainer;

/**
 * Abstract base class for Langfuse client integration tests.
 *
 * <p>Uses the Testcontainers singleton container pattern to start a single
 * {@link LangfuseContainer} instance that is shared across all test classes.
 * The container starts once per JVM and is cleaned up automatically by Ryuk.
 *
 * <p>Subclasses must implement {@link #createClient()} to select the Jackson version.
 * Two shared client instances (Jackson 2 and Jackson 3) are available via
 * {@link #jackson2Client()} and {@link #jackson3Client()}.
 *
 * @author Eric Deandrea
 * @see <a href="https://testcontainers.com/guides/testcontainers-container-lifecycle/#_using_singleton_containers">Singleton Containers</a>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractLangfuseClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractLangfuseClientTest.class);

    static LangfuseContainer langfuse = new LangfuseContainer();
    private static LangfuseApi jackson2Client;
    private static LangfuseApi jackson3Client;

    static {
        langfuse.start();
        jackson2Client = createJackson2Client();
        jackson3Client = createJackson3Client();
    }

    LangfuseApi client;

    @BeforeAll
    void initClient() {
        client = createClient();
    }

    /**
     * Returns the {@link LangfuseApi} client to use for this test class.
     * Concrete subclasses select the Jackson version by returning either
     * {@link #jackson2Client()} or {@link #jackson3Client()}.
     *
     * @return the client instance
     */
    abstract LangfuseApi createClient();

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
     * Ingests a trace with a single root span via the OTel endpoint.
     *
     * @param traceId   32-char hex trace ID
     * @param spanId    16-char hex span ID
     * @param traceName the trace name (set as {@code langfuse.trace.name} resource attribute)
     */
    void ingestTrace(String traceId, String spanId, String traceName) {
        ingestTrace(traceId, spanId, traceName, Map.of());
    }

    /**
     * Ingests a trace with a single root span and additional resource attributes via the OTel endpoint.
     *
     * @param traceId         32-char hex trace ID
     * @param spanId          16-char hex span ID
     * @param traceName       the trace name (set as {@code langfuse.trace.name} resource attribute)
     * @param traceAttributes additional {@code langfuse.trace.*} attributes (e.g. {@code user.id}, {@code session.id})
     */
    void ingestTrace(String traceId, String spanId, String traceName, Map<String, String> traceAttributes) {
        var nowNanos = String.valueOf(System.currentTimeMillis() * 1_000_000L);

        var span = OtelSpan.builder()
                .traceId(traceId)
                .spanId(spanId)
                .name("root-span")
                .kind(1)
                .startTimeUnixNano(nowNanos)
                .endTimeUnixNano(String.valueOf(Long.parseLong(nowNanos) + 1_000_000_000L))
                .build();

        ingestTraceWithSpans(traceName, traceAttributes, span);
    }

    /**
     * Ingests a trace with the given spans via the OTel endpoint.
     *
     * @param traceName       the trace name (set as {@code langfuse.trace.name} resource attribute)
     * @param traceAttributes additional {@code langfuse.trace.*} attributes
     * @param spans           one or more spans to include
     */
    void ingestTraceWithSpans(String traceName, Map<String, String> traceAttributes, OtelSpan... spans) {
        var attributes = new ArrayList<OtelAttribute>();

        attributes.add(OtelAttribute.builder()
                .key("langfuse.trace.name")
                .value(OtelAttributeValue.builder()
                        .stringValue(traceName)
                        .build())
                .build());

        traceAttributes.forEach((key, value) ->
                attributes.add(OtelAttribute.builder()
                        .key("langfuse.trace." + key)
                        .value(OtelAttributeValue.builder()
                                .stringValue(value)
                                .build())
                        .build()));

        var resourceSpan = OtelResourceSpan.builder()
                .resource(OtelResource.builder()
                        .attributes(attributes)
                        .build())
                .scopeSpans(List.of(OtelScopeSpan.builder()
                        .scope(OtelScope.builder()
                                .name("test")
                                .build())
                        .spans(List.of(spans))
                        .build()))
                .build();

        var response = client.opentelemetry().opentelemetryExportTraces(
                APIOpentelemetryExportTracesRequest.newBuilder()
                        .opentelemetryExportTracesRequest(OpentelemetryExportTracesRequest.builder()
                                .resourceSpans(List.of(resourceSpan))
                                .build())
                        .build());

        assertThat(response)
                .isNotNull();
    }

    /**
     * Returns the shared Jackson 2 client instance.
     *
     * @return the Jackson 2 client
     */
    static LangfuseApi jackson2Client() {
        return jackson2Client;
    }

    /**
     * Returns the shared Jackson 3 client instance.
     *
     * @return the Jackson 3 client
     */
    static LangfuseApi jackson3Client() {
        return jackson3Client;
    }

    private static LangfuseApi createJackson2Client() {
        return LangfuseJackson2Client.builder()
                .username(langfuse.getPublicKey())
                .password(langfuse.getSecretKey())
                .url(langfuse.getLangfuseUrl())
                .logRequests()
                .logResponses()
                .prettyPrint()
                .build();
    }

    private static LangfuseApi createJackson3Client() {
        return LangfuseJackson3Client.builder()
                .username(langfuse.getPublicKey())
                .password(langfuse.getSecretKey())
                .url(langfuse.getLangfuseUrl())
                .logRequests()
                .logResponses()
                .prettyPrint()
                .build();
    }
}
