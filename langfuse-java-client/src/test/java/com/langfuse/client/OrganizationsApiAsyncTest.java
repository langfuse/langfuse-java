package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.langfuse.api.model.DeleteMembershipRequest;
import com.langfuse.api.model.MembershipRequest;
import com.langfuse.api.model.MembershipRole;
import com.langfuse.api.organizations.OrganizationsApi.APIOrganizationsDeleteOrganizationMembershipRequest;
import com.langfuse.api.organizations.OrganizationsApi.APIOrganizationsDeleteProjectMembershipRequest;
import com.langfuse.api.organizations.OrganizationsApi.APIOrganizationsGetProjectMembershipsRequest;
import com.langfuse.api.organizations.OrganizationsApi.APIOrganizationsUpdateOrganizationMembershipRequest;
import com.langfuse.api.organizations.OrganizationsApi.APIOrganizationsUpdateProjectMembershipRequest;

/**
 * Async integration tests for the Organizations API.
 *
 * @author Eric Deandrea
 */
@Disabled("Requires org-admin role")
abstract class OrganizationsApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void listOrganizationApiKeys() {
        assertThat(client.asyncOrganizations().organizationsGetOrganizationApiKeys())
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getApiKeys()).isNotNull());
    }

    @Test
    void listOrganizationMemberships() {
        assertThat(client.asyncOrganizations().organizationsGetOrganizationMemberships())
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMemberships()).isNotNull());
    }

    @Test
    void listOrganizationProjects() {
        assertThat(client.asyncOrganizations().organizationsGetOrganizationProjects())
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getProjects()).isNotNull());
    }

    @Test
    void getProjectMemberships() {
        assertThat(client.asyncOrganizations().organizationsGetProjectMemberships(
                APIOrganizationsGetProjectMembershipsRequest.newBuilder()
                        .projectId("langfuse-dev-project")
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMemberships()).isNotNull());
    }

    @Test
    void updateOrganizationMembership() {
        assertThat(client.asyncOrganizations().organizationsUpdateOrganizationMembership(
                APIOrganizationsUpdateOrganizationMembershipRequest.newBuilder()
                        .membershipRequest(MembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .role(MembershipRole.MEMBER)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getUserId()).isNotBlank());
    }

    @Test
    void updateProjectMembership() {
        assertThat(client.asyncOrganizations().organizationsUpdateProjectMembership(
                APIOrganizationsUpdateProjectMembershipRequest.newBuilder()
                        .projectId("langfuse-dev-project")
                        .membershipRequest(MembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .role(MembershipRole.MEMBER)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getUserId()).isNotBlank());
    }

    @Test
    void deleteOrganizationMembership() {
        assertThat(client.asyncOrganizations().organizationsDeleteOrganizationMembership(
                APIOrganizationsDeleteOrganizationMembershipRequest.newBuilder()
                        .deleteMembershipRequest(DeleteMembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }

    @Test
    void deleteProjectMembership() {
        assertThat(client.asyncOrganizations().organizationsDeleteProjectMembership(
                APIOrganizationsDeleteProjectMembershipRequest.newBuilder()
                        .projectId("langfuse-dev-project")
                        .deleteMembershipRequest(DeleteMembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
