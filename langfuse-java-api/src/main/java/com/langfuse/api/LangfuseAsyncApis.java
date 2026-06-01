package com.langfuse.api;

import com.langfuse.api.annotationQueues.async.AnnotationQueuesApi;
import com.langfuse.api.blobStorageIntegrations.async.BlobStorageIntegrationsApi;
import com.langfuse.api.comments.async.CommentsApi;
import com.langfuse.api.datasetItems.async.DatasetItemsApi;
import com.langfuse.api.datasetRunItems.async.DatasetRunItemsApi;
import com.langfuse.api.datasets.async.DatasetsApi;
import com.langfuse.api.health.async.HealthApi;
import com.langfuse.api.ingestion.async.IngestionApi;
import com.langfuse.api.legacyMetricsV1.async.LegacyMetricsV1Api;
import com.langfuse.api.legacyObservationsV1.async.LegacyObservationsV1Api;
import com.langfuse.api.legacyScoreV1.async.LegacyScoreV1Api;
import com.langfuse.api.llmConnections.async.LlmConnectionsApi;
import com.langfuse.api.media.async.MediaApi;
import com.langfuse.api.metrics.async.MetricsApi;
import com.langfuse.api.models.async.ModelsApi;
import com.langfuse.api.observations.async.ObservationsApi;
import com.langfuse.api.opentelemetry.async.OpentelemetryApi;
import com.langfuse.api.organizations.async.OrganizationsApi;
import com.langfuse.api.projects.async.ProjectsApi;
import com.langfuse.api.promptVersion.async.PromptVersionApi;
import com.langfuse.api.prompts.async.PromptsApi;
import com.langfuse.api.scim.async.ScimApi;
import com.langfuse.api.scoreConfigs.async.ScoreConfigsApi;
import com.langfuse.api.scores.async.ScoresApi;
import com.langfuse.api.sessions.async.SessionsApi;
import com.langfuse.api.trace.async.TraceApi;
import com.langfuse.api.unstableEvaluationRules.async.UnstableEvaluationRulesApi;
import com.langfuse.api.unstableEvaluators.async.UnstableEvaluatorsApi;

public interface LangfuseAsyncApis extends
        AnnotationQueuesApi,
        BlobStorageIntegrationsApi,
        CommentsApi,
        DatasetItemsApi,
        DatasetRunItemsApi,
        DatasetsApi,
        HealthApi,
        IngestionApi,
        LegacyMetricsV1Api,
        LegacyObservationsV1Api,
        LegacyScoreV1Api,
        LlmConnectionsApi,
        MediaApi,
        MetricsApi,
        ModelsApi,
        ObservationsApi,
        OpentelemetryApi,
        OrganizationsApi,
        ProjectsApi,
        PromptVersionApi,
        PromptsApi,
        ScimApi,
        ScoreConfigsApi,
        ScoresApi,
        SessionsApi,
        TraceApi,
        UnstableEvaluationRulesApi,
        UnstableEvaluatorsApi {
}
