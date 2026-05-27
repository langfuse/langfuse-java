package com.langfuse.api;

import java.time.Duration;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.langfuse.api.annotationQueues.AnnotationQueuesApi;
import com.langfuse.api.blobStorageIntegrations.BlobStorageIntegrationsApi;
import com.langfuse.api.comments.CommentsApi;
import com.langfuse.api.datasetItems.DatasetItemsApi;
import com.langfuse.api.datasetRunItems.DatasetRunItemsApi;
import com.langfuse.api.datasets.DatasetsApi;
import com.langfuse.api.health.HealthApi;
import com.langfuse.api.ingestion.IngestionApi;
import com.langfuse.api.legacyMetricsV1.LegacyMetricsV1Api;
import com.langfuse.api.legacyObservationsV1.LegacyObservationsV1Api;
import com.langfuse.api.legacyScoreV1.LegacyScoreV1Api;
import com.langfuse.api.llmConnections.LlmConnectionsApi;
import com.langfuse.api.media.MediaApi;
import com.langfuse.api.metrics.MetricsApi;
import com.langfuse.api.models.ModelsApi;
import com.langfuse.api.observations.ObservationsApi;
import com.langfuse.api.opentelemetry.OpentelemetryApi;
import com.langfuse.api.organizations.OrganizationsApi;
import com.langfuse.api.projects.ProjectsApi;
import com.langfuse.api.promptVersion.PromptVersionApi;
import com.langfuse.api.prompts.PromptsApi;
import com.langfuse.api.scim.ScimApi;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi;
import com.langfuse.api.scores.ScoresApi;
import com.langfuse.api.sessions.SessionsApi;
import com.langfuse.api.spi.LangfuseApiBuilderFactory;
import com.langfuse.api.spi.ServiceLoaderHelper;
import com.langfuse.api.trace.TraceApi;
import com.langfuse.api.unstableEvaluationRules.UnstableEvaluationRulesApi;
import com.langfuse.api.unstableEvaluators.UnstableEvaluatorsApi;

/**
 * Langfuse API interface. Entry point for all Langfuse operations.
 *
 * <p>Obtain an instance via the {@link #builder()} method, which uses {@link ServiceLoader}
 * to discover the implementation on the classpath:
 *
 * <pre>{@code
 * LangfuseApi api = LangfuseApi.builder()
 *     .username("pk-lf-...")
 *     .password("sk-lf-...")
 *     .url("https://cloud.langfuse.com")
 *     .build();
 *
 * HealthResponse health = api.health().healthHealth();
 * }</pre>
 *
 * @author Eric Deandrea
 * @see LangfuseApiBuilder
 * @see LangfuseApiBuilderFactory
 */
public interface LangfuseApi {

    /**
     * Returns the Health API for checking the status of the Langfuse server.
     *
     * @return the {@link HealthApi} instance
     */
    HealthApi health();

    /** @return the async Health API */
    com.langfuse.api.health.async.HealthApi asyncHealth();

    /** @return the Annotation Queues API */
    AnnotationQueuesApi annotationQueues();

    /** @return the async Annotation Queues API */
    com.langfuse.api.annotationQueues.async.AnnotationQueuesApi asyncAnnotationQueues();

    /** @return the Blob Storage Integrations API */
    BlobStorageIntegrationsApi blobStorageIntegrations();

    /** @return the async Blob Storage Integrations API */
    com.langfuse.api.blobStorageIntegrations.async.BlobStorageIntegrationsApi asyncBlobStorageIntegrations();

    /** @return the Comments API */
    CommentsApi comments();

    /** @return the async Comments API */
    com.langfuse.api.comments.async.CommentsApi asyncComments();

    /** @return the Dataset Items API */
    DatasetItemsApi datasetItems();

    /** @return the async Dataset Items API */
    com.langfuse.api.datasetItems.async.DatasetItemsApi asyncDatasetItems();

    /** @return the Dataset Run Items API */
    DatasetRunItemsApi datasetRunItems();

    /** @return the async Dataset Run Items API */
    com.langfuse.api.datasetRunItems.async.DatasetRunItemsApi asyncDatasetRunItems();

    /** @return the Datasets API */
    DatasetsApi datasets();

    /** @return the async Datasets API */
    com.langfuse.api.datasets.async.DatasetsApi asyncDatasets();

    /** @return the Ingestion API */
    IngestionApi ingestion();

    /** @return the async Ingestion API */
    com.langfuse.api.ingestion.async.IngestionApi asyncIngestion();

    /** @return the Legacy Metrics V1 API */
    LegacyMetricsV1Api legacyMetricsV1();

    /** @return the async Legacy Metrics V1 API */
    com.langfuse.api.legacyMetricsV1.async.LegacyMetricsV1Api asyncLegacyMetricsV1();

    /** @return the Legacy Observations V1 API */
    LegacyObservationsV1Api legacyObservationsV1();

    /** @return the async Legacy Observations V1 API */
    com.langfuse.api.legacyObservationsV1.async.LegacyObservationsV1Api asyncLegacyObservationsV1();

    /** @return the Legacy Score V1 API */
    LegacyScoreV1Api legacyScoreV1();

    /** @return the async Legacy Score V1 API */
    com.langfuse.api.legacyScoreV1.async.LegacyScoreV1Api asyncLegacyScoreV1();

    /** @return the LLM Connections API */
    LlmConnectionsApi llmConnections();

    /** @return the async LLM Connections API */
    com.langfuse.api.llmConnections.async.LlmConnectionsApi asyncLlmConnections();

    /** @return the Media API */
    MediaApi media();

    /** @return the async Media API */
    com.langfuse.api.media.async.MediaApi asyncMedia();

    /** @return the Metrics API */
    MetricsApi metrics();

    /** @return the async Metrics API */
    com.langfuse.api.metrics.async.MetricsApi asyncMetrics();

    /** @return the Models API */
    ModelsApi models();

    /** @return the async Models API */
    com.langfuse.api.models.async.ModelsApi asyncModels();

    /** @return the Observations API */
    ObservationsApi observations();

    /** @return the async Observations API */
    com.langfuse.api.observations.async.ObservationsApi asyncObservations();

    /** @return the OpenTelemetry API */
    OpentelemetryApi opentelemetry();

    /** @return the async OpenTelemetry API */
    com.langfuse.api.opentelemetry.async.OpentelemetryApi asyncOpentelemetry();

    /** @return the Organizations API */
    OrganizationsApi organizations();

    /** @return the async Organizations API */
    com.langfuse.api.organizations.async.OrganizationsApi asyncOrganizations();

    /** @return the Projects API */
    ProjectsApi projects();

    /** @return the async Projects API */
    com.langfuse.api.projects.async.ProjectsApi asyncProjects();

    /** @return the Prompt Version API */
    PromptVersionApi promptVersion();

    /** @return the async Prompt Version API */
    com.langfuse.api.promptVersion.async.PromptVersionApi asyncPromptVersion();

    /** @return the Prompts API */
    PromptsApi prompts();

    /** @return the async Prompts API */
    com.langfuse.api.prompts.async.PromptsApi asyncPrompts();

    /** @return the SCIM API */
    ScimApi scim();

    /** @return the async SCIM API */
    com.langfuse.api.scim.async.ScimApi asyncScim();

    /** @return the Score Configs API */
    ScoreConfigsApi scoreConfigs();

    /** @return the async Score Configs API */
    com.langfuse.api.scoreConfigs.async.ScoreConfigsApi asyncScoreConfigs();

    /** @return the Scores API */
    ScoresApi scores();

    /** @return the async Scores API */
    com.langfuse.api.scores.async.ScoresApi asyncScores();

    /** @return the Sessions API */
    SessionsApi sessions();

    /** @return the async Sessions API */
    com.langfuse.api.sessions.async.SessionsApi asyncSessions();

    /** @return the Trace API */
    TraceApi trace();

    /** @return the async Trace API */
    com.langfuse.api.trace.async.TraceApi asyncTrace();

    /** @return the Unstable Evaluation Rules API */
    UnstableEvaluationRulesApi unstableEvaluationRules();

    /** @return the async Unstable Evaluation Rules API */
    com.langfuse.api.unstableEvaluationRules.async.UnstableEvaluationRulesApi asyncUnstableEvaluationRules();

    /** @return the Unstable Evaluators API */
    UnstableEvaluatorsApi unstableEvaluators();

    /** @return the async Unstable Evaluators API */
    com.langfuse.api.unstableEvaluators.async.UnstableEvaluatorsApi asyncUnstableEvaluators();

    /**
     * Creates a builder for constructing a {@link LangfuseApi} instance.
     *
     * <p>Uses {@link ServiceLoader} to discover exactly one {@link LangfuseApiBuilderFactory}
     * on the classpath. If no factory is found, an {@link IllegalStateException} is thrown indicating
     * a missing implementation dependency. If multiple factories are found, an
     * {@link IllegalStateException} is thrown listing the conflicting implementations.
     *
     * @param <T> the concrete {@link LangfuseApi} implementation type
     * @param <B> the concrete builder type
     * @return a builder instance for constructing a {@link LangfuseApi}
     * @throws IllegalStateException if zero or more than one factory is found on the classpath
     */
    static <T extends LangfuseApi, B extends LangfuseApiBuilder<T, B>> B builder() {
        var factories = ServiceLoaderHelper.loadFactories(LangfuseApiBuilderFactory.class);

        if (factories.isEmpty()) {
            throw new IllegalStateException(
                    "No instance of %s found to build a %s instance. You are probably missing a library on your classpath."
                            .formatted(LangfuseApiBuilderFactory.class.getName(), LangfuseApiBuilder.class.getName()));
        }

        if (factories.size() > 1) {
            throw new IllegalStateException(
                    "Multiple instances of %s found to build a %s instance: [%s]"
                            .formatted(
                                    LangfuseApiBuilderFactory.class.getName(),
                                    LangfuseApiBuilder.class.getName(),
                                    factories.stream().map(f -> f.getClass().getName()).collect(Collectors.joining(", "))));
        }

        return factories.iterator()
                        .next()
                        .getBuilder();
    }

    /**
     * Builder interface for constructing {@link LangfuseApi} implementations.
     *
     * <p>Implementations of this interface are provided by client libraries (e.g. {@code langfuse-java-client}).
     * Downstream frameworks such as Spring or Quarkus may provide their own implementations using
     * their preferred HTTP and serialization stacks.
     *
     * @author Eric Deandrea
     * @param <T> the concrete {@link LangfuseApi} implementation type being built
     * @param <B> the concrete builder type (for fluent method chaining)
     */
    interface LangfuseApiBuilder<T extends LangfuseApi, B extends LangfuseApiBuilder<T, B>> {
        @SuppressWarnings("unchecked")
        default B self() {
          return (B) this;
        }

        /**
         * Sets the username (Langfuse public key) for Basic authentication.
         *
         * @param username the Langfuse public key
         * @return this builder for method chaining
         */
        B username(String username);

        /**
         * Sets the password (Langfuse secret key) for Basic authentication.
         *
         * @param password the Langfuse secret key
         * @return this builder for method chaining
         */
        B password(String password);

        /**
         * Sets the base URL of the Langfuse server.
         *
         * @param url the base URL (e.g. {@code "https://cloud.langfuse.com"})
         * @return this builder for method chaining
         */
        B url(String url);

        /**
         * Sets the read timeout for HTTP requests.
         *
         * @param readTimeout the read timeout duration
         * @return this builder for method chaining
         */
        B readTimeout(Duration readTimeout);

        /**
         * Adds a custom HTTP header to be sent with every request.
         *
         * @param name the header name
         * @param value the header value
         * @return this builder for method chaining
         */
        B addHeader(String name, String value);

        /**
         * Enables logging of HTTP requests. Equivalent to {@code logRequests(true)}.
         *
         * @return this builder for method chaining
         */
        default B logRequests() {
            return logRequests(true);
        }

        /**
         * Configures whether HTTP request logging is enabled.
         *
         * @param logRequests {@code true} to enable request logging; {@code false} to disable it
         * @return this builder for method chaining
         */
        B logRequests(boolean logRequests);

        /**
         * Enables logging of HTTP responses. Equivalent to {@code logResponses(true)}.
         *
         * @return this builder for method chaining
         */
        default B logResponses() {
            return logResponses(true);
        }

        /**
         * Configures whether HTTP response logging is enabled.
         *
         * @param logResponses {@code true} to enable response logging; {@code false} to disable it
         * @return this builder for method chaining
         */
        B logResponses(boolean logResponses);

        /**
         * Enables pretty-printing of JSON in logged requests and responses.
         * Equivalent to {@code prettyPrint(true)}.
         *
         * @return this builder for method chaining
         */
        default B prettyPrint() {
            return prettyPrint(true);
        }

        /**
         * Configures whether JSON in logged requests and responses is pretty-printed.
         *
         * @param prettyPrint {@code true} to enable pretty-printing; {@code false} for compact output
         * @return this builder for method chaining
         */
        B prettyPrint(boolean prettyPrint);

        /**
         * Builds and returns a configured {@link LangfuseApi} instance.
         *
         * @return a new {@link LangfuseApi} instance
         */
        T build();
    }
}
