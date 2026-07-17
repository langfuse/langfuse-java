package com.langfuse.api;

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

public interface LangfuseApis extends
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
