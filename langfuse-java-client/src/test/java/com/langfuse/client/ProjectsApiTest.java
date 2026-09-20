package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.ProjectsCreateApiKeyRequest;
import com.langfuse.api.model.ProjectsCreateRequest;
import com.langfuse.api.model.ProjectsUpdateRequest;
import com.langfuse.api.projects.ProjectsApi.APIProjectsCreateApiKeyRequest;
import com.langfuse.api.projects.ProjectsApi.APIProjectsCreateRequest;
import com.langfuse.api.projects.ProjectsApi.APIProjectsDeleteApiKeyRequest;
import com.langfuse.api.projects.ProjectsApi.APIProjectsDeleteRequest;
import com.langfuse.api.projects.ProjectsApi.APIProjectsGetApiKeysRequest;
import com.langfuse.api.projects.ProjectsApi.APIProjectsUpdateRequest;

/**
 * Integration tests for the Projects API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class ProjectsApiTest extends AbstractLangfuseClientTest {

    private static String createdProjectId;
    private static String apiKeyId;

    @Test
    @Order(1)
    void getProjects() {
        assertThat(client.projects().projectsGet())
                .satisfies(projects -> {
                    assertThat(projects.getData())
                            .isNotEmpty()
                            .first()
                            .satisfies(project -> {
                                assertThat(project.getId()).isNotBlank();
                                assertThat(project.getName()).isNotBlank();
                                assertThat(project.getOrganization()).isNotNull();
                            });
                });
    }

    @Test
    @Order(2)
    @Disabled("Requires org-admin role")
    void createProject() {
        assertThat(client.projects().projectsCreate(
                APIProjectsCreateRequest.newBuilder()
                        .projectsCreateRequest(ProjectsCreateRequest.builder()
                                .name("test-project-" + UUID.randomUUID().toString().substring(0, 8))
                                .build())
                        .build()))
                .satisfies(project -> {
                    assertThat(project.getId()).isNotBlank();
                    assertThat(project.getName()).isNotBlank();
                    createdProjectId = project.getId();
                });
    }

    @Test
    @Order(3)
    @Disabled("Requires project creation in previous step")
    void updateProject() {
        assertThat(client.projects().projectsUpdate(
                APIProjectsUpdateRequest.newBuilder()
                        .projectId(createdProjectId)
                        .projectsUpdateRequest(ProjectsUpdateRequest.builder()
                                .name("updated-project-" + UUID.randomUUID().toString().substring(0, 8))
                                .build())
                        .build()))
                .satisfies(project -> {
                    assertThat(project.getId()).isEqualTo(createdProjectId);
                    assertThat(project.getName()).startsWith("updated-project-");
                });
    }

    @Test
    @Order(4)
    @Disabled("Requires project creation in previous step")
    void createApiKey() {
        assertThat(client.projects().projectsCreateApiKey(
                APIProjectsCreateApiKeyRequest.newBuilder()
                        .projectId(createdProjectId)
                        .projectsCreateApiKeyRequest(ProjectsCreateApiKeyRequest.builder()
                                .note("Integration test API key")
                                .build())
                        .build()))
                .satisfies(response -> {
                    assertThat(response.getId()).isNotBlank();
                    assertThat(response.getPublicKey()).isNotBlank();
                    assertThat(response.getSecretKey()).isNotBlank();
                    apiKeyId = response.getId();
                });
    }

    @Test
    @Order(5)
    @Disabled("Requires API key creation in previous step")
    void getApiKeys() {
        assertThat(client.projects().projectsGetApiKeys(
                APIProjectsGetApiKeysRequest.newBuilder()
                        .projectId(createdProjectId)
                        .build()))
                .satisfies(keys ->
                        assertThat(keys.getApiKeys())
                                .isNotEmpty()
                                .anyMatch(k -> apiKeyId.equals(k.getId())));
    }

    @Test
    @Order(6)
    @Disabled("Requires API key creation in previous step")
    void deleteApiKey() {
        assertThat(client.projects().projectsDeleteApiKey(
                APIProjectsDeleteApiKeyRequest.newBuilder()
                        .projectId(createdProjectId)
                        .apiKeyId(apiKeyId)
                        .build()))
                .satisfies(response ->
                        assertThat(response.getSuccess()).isTrue());
    }

    @Test
    @Order(7)
    @Disabled("Requires project creation in previous step")
    void deleteProject() {
        assertThat(client.projects().projectsDelete(
                APIProjectsDeleteRequest.newBuilder()
                        .projectId(createdProjectId)
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
