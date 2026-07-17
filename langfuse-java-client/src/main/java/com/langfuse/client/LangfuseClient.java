package com.langfuse.client;

import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.langfuse.api.LangfuseApi;
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
import com.langfuse.api.trace.TraceApi;
import com.langfuse.api.unstableEvaluationRules.UnstableEvaluationRulesApi;
import com.langfuse.api.unstableEvaluators.UnstableEvaluatorsApi;

/**
 * Abstract base implementation of {@link LangfuseApi}.
 * Extends {@link ApiClient} to provide HTTP infrastructure and delegates
 * JSON serialization to Jackson-version-specific subclasses.
 *
 * <p>Use {@link LangfuseApi#builder()} for automatic Jackson version detection,
 * or instantiate a specific subclass directly.
 *
 * @author Eric Deandrea
 * @see LangfuseJackson2Client
 * @see LangfuseJackson3Client
 */
public abstract class LangfuseClient extends ApiClient implements LangfuseApi {
  private final AnnotationQueuesApi annotationQueuesApi;
  private final com.langfuse.api.annotationQueues.async.AnnotationQueuesApi asyncAnnotationQueuesApi;
  private final BlobStorageIntegrationsApi blobStorageIntegrationsApi;
  private final com.langfuse.api.blobStorageIntegrations.async.BlobStorageIntegrationsApi asyncBlobStorageIntegrationsApi;
  private final CommentsApi commentsApi;
  private final com.langfuse.api.comments.async.CommentsApi asyncCommentsApi;
  private final DatasetItemsApi datasetItemsApi;
  private final com.langfuse.api.datasetItems.async.DatasetItemsApi asyncDatasetItemsApi;
  private final DatasetRunItemsApi datasetRunItemsApi;
  private final com.langfuse.api.datasetRunItems.async.DatasetRunItemsApi asyncDatasetRunItemsApi;
  private final DatasetsApi datasetsApi;
  private final com.langfuse.api.datasets.async.DatasetsApi asyncDatasetsApi;
  private final HealthApi healthApi;
  private final com.langfuse.api.health.async.HealthApi asyncHealthApi;
  private final IngestionApi ingestionApi;
  private final com.langfuse.api.ingestion.async.IngestionApi asyncIngestionApi;
  private final LegacyMetricsV1Api legacyMetricsV1Api;
  private final com.langfuse.api.legacyMetricsV1.async.LegacyMetricsV1Api asyncLegacyMetricsV1Api;
  private final LegacyObservationsV1Api legacyObservationsV1Api;
  private final com.langfuse.api.legacyObservationsV1.async.LegacyObservationsV1Api asyncLegacyObservationsV1Api;
  private final LegacyScoreV1Api legacyScoreV1Api;
  private final com.langfuse.api.legacyScoreV1.async.LegacyScoreV1Api asyncLegacyScoreV1Api;
  private final LlmConnectionsApi llmConnectionsApi;
  private final com.langfuse.api.llmConnections.async.LlmConnectionsApi asyncLlmConnectionsApi;
  private final MediaApi mediaApi;
  private final com.langfuse.api.media.async.MediaApi asyncMediaApi;
  private final MetricsApi metricsApi;
  private final com.langfuse.api.metrics.async.MetricsApi asyncMetricsApi;
  private final ModelsApi modelsApi;
  private final com.langfuse.api.models.async.ModelsApi asyncModelsApi;
  private final ObservationsApi observationsApi;
  private final com.langfuse.api.observations.async.ObservationsApi asyncObservationsApi;
  private final OpentelemetryApi opentelemetryApi;
  private final com.langfuse.api.opentelemetry.async.OpentelemetryApi asyncOpentelemetryApi;
  private final OrganizationsApi organizationsApi;
  private final com.langfuse.api.organizations.async.OrganizationsApi asyncOrganizationsApi;
  private final ProjectsApi projectsApi;
  private final com.langfuse.api.projects.async.ProjectsApi asyncProjectsApi;
  private final PromptVersionApi promptVersionApi;
  private final com.langfuse.api.promptVersion.async.PromptVersionApi asyncPromptVersionApi;
  private final PromptsApi promptsApi;
  private final com.langfuse.api.prompts.async.PromptsApi asyncPromptsApi;
  private final ScimApi scimApi;
  private final com.langfuse.api.scim.async.ScimApi asyncScimApi;
  private final ScoreConfigsApi scoreConfigsApi;
  private final com.langfuse.api.scoreConfigs.async.ScoreConfigsApi asyncScoreConfigsApi;
  private final ScoresApi scoresApi;
  private final com.langfuse.api.scores.async.ScoresApi asyncScoresApi;
  private final SessionsApi sessionsApi;
  private final com.langfuse.api.sessions.async.SessionsApi asyncSessionsApi;
  private final TraceApi traceApi;
  private final com.langfuse.api.trace.async.TraceApi asyncTraceApi;
  private final UnstableEvaluationRulesApi unstableEvaluationRulesApi;
  private final com.langfuse.api.unstableEvaluationRules.async.UnstableEvaluationRulesApi asyncUnstableEvaluationRulesApi;
  private final UnstableEvaluatorsApi unstableEvaluatorsApi;
  private final com.langfuse.api.unstableEvaluators.async.UnstableEvaluatorsApi asyncUnstableEvaluatorsApi;

  protected LangfuseClient(LangfuseClientBuilder<?, ?> builder) {
    super();

    if (builder.url != null) {
      updateBaseUri(builder.url);
    }

    if (builder.readTimeout != null) {
      setReadTimeout(builder.readTimeout);
    }

    setRequestInterceptor(requestBuilder -> {
      if ((builder.username != null) && (builder.password != null)) {
        String encoded = Base64.getEncoder().encodeToString("%s:%s".formatted(builder.username, builder.password).getBytes());
        requestBuilder.header("Authorization", "Basic " + encoded);
      }

      for (var entry : builder.headers.entrySet()) {
        requestBuilder.header(entry.getKey(), entry.getValue());
      }
    });

    setLogRequests(builder.logRequests);
    setLogResponses(builder.logResponses);
    setPrettyPrintJson(builder.prettyPrintJson);

    this.annotationQueuesApi = new com.langfuse.client.annotationQueues.AnnotationQueuesApi(this);
    this.asyncAnnotationQueuesApi = new com.langfuse.client.annotationQueues.async.AnnotationQueuesApi(this);
    this.blobStorageIntegrationsApi = new com.langfuse.client.blobStorageIntegrations.BlobStorageIntegrationsApi(this);
    this.asyncBlobStorageIntegrationsApi = new com.langfuse.client.blobStorageIntegrations.async.BlobStorageIntegrationsApi(this);
    this.commentsApi = new com.langfuse.client.comments.CommentsApi(this);
    this.asyncCommentsApi = new com.langfuse.client.comments.async.CommentsApi(this);
    this.datasetItemsApi = new com.langfuse.client.datasetItems.DatasetItemsApi(this);
    this.asyncDatasetItemsApi = new com.langfuse.client.datasetItems.async.DatasetItemsApi(this);
    this.datasetRunItemsApi = new com.langfuse.client.datasetRunItems.DatasetRunItemsApi(this);
    this.asyncDatasetRunItemsApi = new com.langfuse.client.datasetRunItems.async.DatasetRunItemsApi(this);
    this.datasetsApi = new com.langfuse.client.datasets.DatasetsApi(this);
    this.asyncDatasetsApi = new com.langfuse.client.datasets.async.DatasetsApi(this);
    this.healthApi = new com.langfuse.client.health.HealthApi(this);
    this.asyncHealthApi = new com.langfuse.client.health.async.HealthApi(this);
    this.ingestionApi = new com.langfuse.client.ingestion.IngestionApi(this);
    this.asyncIngestionApi = new com.langfuse.client.ingestion.async.IngestionApi(this);
    this.legacyMetricsV1Api = new com.langfuse.client.legacyMetricsV1.LegacyMetricsV1Api(this);
    this.asyncLegacyMetricsV1Api = new com.langfuse.client.legacyMetricsV1.async.LegacyMetricsV1Api(this);
    this.legacyObservationsV1Api = new com.langfuse.client.legacyObservationsV1.LegacyObservationsV1Api(this);
    this.asyncLegacyObservationsV1Api = new com.langfuse.client.legacyObservationsV1.async.LegacyObservationsV1Api(this);
    this.legacyScoreV1Api = new com.langfuse.client.legacyScoreV1.LegacyScoreV1Api(this);
    this.asyncLegacyScoreV1Api = new com.langfuse.client.legacyScoreV1.async.LegacyScoreV1Api(this);
    this.llmConnectionsApi = new com.langfuse.client.llmConnections.LlmConnectionsApi(this);
    this.asyncLlmConnectionsApi = new com.langfuse.client.llmConnections.async.LlmConnectionsApi(this);
    this.mediaApi = new com.langfuse.client.media.MediaApi(this);
    this.asyncMediaApi = new com.langfuse.client.media.async.MediaApi(this);
    this.metricsApi = new com.langfuse.client.metrics.MetricsApi(this);
    this.asyncMetricsApi = new com.langfuse.client.metrics.async.MetricsApi(this);
    this.modelsApi = new com.langfuse.client.models.ModelsApi(this);
    this.asyncModelsApi = new com.langfuse.client.models.async.ModelsApi(this);
    this.observationsApi = new com.langfuse.client.observations.ObservationsApi(this);
    this.asyncObservationsApi = new com.langfuse.client.observations.async.ObservationsApi(this);
    this.opentelemetryApi = new com.langfuse.client.opentelemetry.OpentelemetryApi(this);
    this.asyncOpentelemetryApi = new com.langfuse.client.opentelemetry.async.OpentelemetryApi(this);
    this.organizationsApi = new com.langfuse.client.organizations.OrganizationsApi(this);
    this.asyncOrganizationsApi = new com.langfuse.client.organizations.async.OrganizationsApi(this);
    this.projectsApi = new com.langfuse.client.projects.ProjectsApi(this);
    this.asyncProjectsApi = new com.langfuse.client.projects.async.ProjectsApi(this);
    this.promptVersionApi = new com.langfuse.client.promptVersion.PromptVersionApi(this);
    this.asyncPromptVersionApi = new com.langfuse.client.promptVersion.async.PromptVersionApi(this);
    this.promptsApi = new com.langfuse.client.prompts.PromptsApi(this);
    this.asyncPromptsApi = new com.langfuse.client.prompts.async.PromptsApi(this);
    this.scimApi = new com.langfuse.client.scim.ScimApi(this);
    this.asyncScimApi = new com.langfuse.client.scim.async.ScimApi(this);
    this.scoreConfigsApi = new com.langfuse.client.scoreConfigs.ScoreConfigsApi(this);
    this.asyncScoreConfigsApi = new com.langfuse.client.scoreConfigs.async.ScoreConfigsApi(this);
    this.scoresApi = new com.langfuse.client.scores.ScoresApi(this);
    this.asyncScoresApi = new com.langfuse.client.scores.async.ScoresApi(this);
    this.sessionsApi = new com.langfuse.client.sessions.SessionsApi(this);
    this.asyncSessionsApi = new com.langfuse.client.sessions.async.SessionsApi(this);
    this.traceApi = new com.langfuse.client.trace.TraceApi(this);
    this.asyncTraceApi = new com.langfuse.client.trace.async.TraceApi(this);
    this.unstableEvaluationRulesApi = new com.langfuse.client.unstableEvaluationRules.UnstableEvaluationRulesApi(this);
    this.asyncUnstableEvaluationRulesApi = new com.langfuse.client.unstableEvaluationRules.async.UnstableEvaluationRulesApi(this);
    this.unstableEvaluatorsApi = new com.langfuse.client.unstableEvaluators.UnstableEvaluatorsApi(this);
    this.asyncUnstableEvaluatorsApi = new com.langfuse.client.unstableEvaluators.async.UnstableEvaluatorsApi(this);
  }

  @Override
  public AnnotationQueuesApi annotationQueues() {
    return annotationQueuesApi;
  }

  @Override
  public com.langfuse.api.annotationQueues.async.AnnotationQueuesApi asyncAnnotationQueues() {
    return asyncAnnotationQueuesApi;
  }

  @Override
  public BlobStorageIntegrationsApi blobStorageIntegrations() {
    return blobStorageIntegrationsApi;
  }

  @Override
  public com.langfuse.api.blobStorageIntegrations.async.BlobStorageIntegrationsApi asyncBlobStorageIntegrations() {
    return asyncBlobStorageIntegrationsApi;
  }

  @Override
  public CommentsApi comments() {
    return commentsApi;
  }

  @Override
  public com.langfuse.api.comments.async.CommentsApi asyncComments() {
    return asyncCommentsApi;
  }

  @Override
  public DatasetItemsApi datasetItems() {
    return datasetItemsApi;
  }

  @Override
  public com.langfuse.api.datasetItems.async.DatasetItemsApi asyncDatasetItems() {
    return asyncDatasetItemsApi;
  }

  @Override
  public DatasetRunItemsApi datasetRunItems() {
    return datasetRunItemsApi;
  }

  @Override
  public com.langfuse.api.datasetRunItems.async.DatasetRunItemsApi asyncDatasetRunItems() {
    return asyncDatasetRunItemsApi;
  }

  @Override
  public DatasetsApi datasets() {
    return datasetsApi;
  }

  @Override
  public com.langfuse.api.datasets.async.DatasetsApi asyncDatasets() {
    return asyncDatasetsApi;
  }

  @Override
  public HealthApi health() {
    return healthApi;
  }

  @Override
  public com.langfuse.api.health.async.HealthApi asyncHealth() {
    return asyncHealthApi;
  }

  @Override
  public IngestionApi ingestion() {
    return ingestionApi;
  }

  @Override
  public com.langfuse.api.ingestion.async.IngestionApi asyncIngestion() {
    return asyncIngestionApi;
  }

  @Override
  public LegacyMetricsV1Api legacyMetricsV1() {
    return legacyMetricsV1Api;
  }

  @Override
  public com.langfuse.api.legacyMetricsV1.async.LegacyMetricsV1Api asyncLegacyMetricsV1() {
    return asyncLegacyMetricsV1Api;
  }

  @Override
  public LegacyObservationsV1Api legacyObservationsV1() {
    return legacyObservationsV1Api;
  }

  @Override
  public com.langfuse.api.legacyObservationsV1.async.LegacyObservationsV1Api asyncLegacyObservationsV1() {
    return asyncLegacyObservationsV1Api;
  }

  @Override
  public LegacyScoreV1Api legacyScoreV1() {
    return legacyScoreV1Api;
  }

  @Override
  public com.langfuse.api.legacyScoreV1.async.LegacyScoreV1Api asyncLegacyScoreV1() {
    return asyncLegacyScoreV1Api;
  }

  @Override
  public LlmConnectionsApi llmConnections() {
    return llmConnectionsApi;
  }

  @Override
  public com.langfuse.api.llmConnections.async.LlmConnectionsApi asyncLlmConnections() {
    return asyncLlmConnectionsApi;
  }

  @Override
  public MediaApi media() {
    return mediaApi;
  }

  @Override
  public com.langfuse.api.media.async.MediaApi asyncMedia() {
    return asyncMediaApi;
  }

  @Override
  public MetricsApi metrics() {
    return metricsApi;
  }

  @Override
  public com.langfuse.api.metrics.async.MetricsApi asyncMetrics() {
    return asyncMetricsApi;
  }

  @Override
  public ModelsApi models() {
    return modelsApi;
  }

  @Override
  public com.langfuse.api.models.async.ModelsApi asyncModels() {
    return asyncModelsApi;
  }

  @Override
  public ObservationsApi observations() {
    return observationsApi;
  }

  @Override
  public com.langfuse.api.observations.async.ObservationsApi asyncObservations() {
    return asyncObservationsApi;
  }

  @Override
  public OpentelemetryApi opentelemetry() {
    return opentelemetryApi;
  }

  @Override
  public com.langfuse.api.opentelemetry.async.OpentelemetryApi asyncOpentelemetry() {
    return asyncOpentelemetryApi;
  }

  @Override
  public OrganizationsApi organizations() {
    return organizationsApi;
  }

  @Override
  public com.langfuse.api.organizations.async.OrganizationsApi asyncOrganizations() {
    return asyncOrganizationsApi;
  }

  @Override
  public ProjectsApi projects() {
    return projectsApi;
  }

  @Override
  public com.langfuse.api.projects.async.ProjectsApi asyncProjects() {
    return asyncProjectsApi;
  }

  @Override
  public PromptVersionApi promptVersion() {
    return promptVersionApi;
  }

  @Override
  public com.langfuse.api.promptVersion.async.PromptVersionApi asyncPromptVersion() {
    return asyncPromptVersionApi;
  }

  @Override
  public PromptsApi prompts() {
    return promptsApi;
  }

  @Override
  public com.langfuse.api.prompts.async.PromptsApi asyncPrompts() {
    return asyncPromptsApi;
  }

  @Override
  public ScimApi scim() {
    return scimApi;
  }

  @Override
  public com.langfuse.api.scim.async.ScimApi asyncScim() {
    return asyncScimApi;
  }

  @Override
  public ScoreConfigsApi scoreConfigs() {
    return scoreConfigsApi;
  }

  @Override
  public com.langfuse.api.scoreConfigs.async.ScoreConfigsApi asyncScoreConfigs() {
    return asyncScoreConfigsApi;
  }

  @Override
  public ScoresApi scores() {
    return scoresApi;
  }

  @Override
  public com.langfuse.api.scores.async.ScoresApi asyncScores() {
    return asyncScoresApi;
  }

  @Override
  public SessionsApi sessions() {
    return sessionsApi;
  }

  @Override
  public com.langfuse.api.sessions.async.SessionsApi asyncSessions() {
    return asyncSessionsApi;
  }

  @Override
  public TraceApi trace() {
    return traceApi;
  }

  @Override
  public com.langfuse.api.trace.async.TraceApi asyncTrace() {
    return asyncTraceApi;
  }

  @Override
  public UnstableEvaluationRulesApi unstableEvaluationRules() {
    return unstableEvaluationRulesApi;
  }

  @Override
  public com.langfuse.api.unstableEvaluationRules.async.UnstableEvaluationRulesApi asyncUnstableEvaluationRules() {
    return asyncUnstableEvaluationRulesApi;
  }

  @Override
  public UnstableEvaluatorsApi unstableEvaluators() {
    return unstableEvaluatorsApi;
  }

  @Override
  public com.langfuse.api.unstableEvaluators.async.UnstableEvaluatorsApi asyncUnstableEvaluators() {
    return asyncUnstableEvaluatorsApi;
  }

  /**
   * Abstract builder for constructing {@link LangfuseClient} instances.
   * Implements {@link LangfuseApiBuilder} for Langfuse-specific settings
   * and configures the generated {@link ApiClient} via its setter methods.
   *
   * @param <T> the concrete client type
   * @param <B> the concrete builder type
   * @author Eric Deandrea
   */
  public abstract static class LangfuseClientBuilder<T extends LangfuseClient, B extends LangfuseClientBuilder<T, B>>
    implements LangfuseApiBuilder<T, B> {

    private String username;
    private String password;
    private String url;
    private Duration readTimeout;
    private boolean logRequests;
    private boolean logResponses;
    private boolean prettyPrintJson;
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public B username(String username) {
      this.username = username;
      return self();
    }

    @Override
    public B password(String password) {
      this.password = password;
      return self();
    }

    @Override
    public B url(String url) {
      this.url = url;
      return self();
    }

    @Override
    public B readTimeout(Duration readTimeout) {
      this.readTimeout = readTimeout;
      return self();
    }

    @Override
    public B addHeader(String name, String value) {
      this.headers.put(name, value);
      return self();
    }

    @Override
    public B logRequests(boolean logRequests) {
      this.logRequests = logRequests;
      return self();
    }

    @Override
    public B logResponses(boolean logResponses) {
      this.logResponses = logResponses;
      return self();
    }

    @Override
    public B prettyPrint(boolean prettyPrint) {
      this.prettyPrintJson = prettyPrint;
      return self();
    }
  }
}
