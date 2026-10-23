package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.blobStorageIntegrations.BlobStorageIntegrationsApi.APIBlobStorageIntegrationsDeleteBlobStorageIntegrationRequest;
import com.langfuse.api.blobStorageIntegrations.BlobStorageIntegrationsApi.APIBlobStorageIntegrationsGetBlobStorageIntegrationStatusRequest;
import com.langfuse.api.blobStorageIntegrations.BlobStorageIntegrationsApi.APIBlobStorageIntegrationsUpsertBlobStorageIntegrationRequest;
import com.langfuse.api.model.CreateBlobStorageIntegrationRequest;

/**
 * Integration tests for the Blob Storage Integrations API.
 *
 * @author Eric Deandrea
 */
@Disabled("Requires org-admin role")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class BlobStorageIntegrationsApiTest extends AbstractLangfuseClientTest {

    private static String integrationId;

    @Test
    @Order(1)
    void listBlobStorageIntegrations() {
        assertThat(client.blobStorageIntegrations().blobStorageIntegrationsGetBlobStorageIntegrations())
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }

    @Test
    @Order(2)
    void upsertBlobStorageIntegration() {
        assertThat(client.blobStorageIntegrations().blobStorageIntegrationsUpsertBlobStorageIntegration(
                APIBlobStorageIntegrationsUpsertBlobStorageIntegrationRequest.newBuilder()
                        .createBlobStorageIntegrationRequest(CreateBlobStorageIntegrationRequest.builder()
                                .bucketName("test-bucket")
                                .region("us-east-1")
                                .accessKeyId("test-key")
                                .secretAccessKey("test-secret")
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getId()).isNotBlank());
    }

    @Test
    @Order(3)
    void getIntegrationStatus() {
        assertThat(client.blobStorageIntegrations().blobStorageIntegrationsGetBlobStorageIntegrationStatus(
                APIBlobStorageIntegrationsGetBlobStorageIntegrationStatusRequest.newBuilder()
                        .id(integrationId)
                        .build()))
                .satisfies(response ->
                        assertThat(response.getId()).isNotBlank());
    }

    @Test
    @Order(4)
    void deleteIntegration() {
        assertThat(client.blobStorageIntegrations().blobStorageIntegrationsDeleteBlobStorageIntegration(
                APIBlobStorageIntegrationsDeleteBlobStorageIntegrationRequest.newBuilder()
                        .id(integrationId)
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
