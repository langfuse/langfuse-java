package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

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
 * Integration tests for the Organizations API.
 *
 * @author Eric Deandrea
 */
@Disabled("Requires org-admin role")
abstract class OrganizationsApiTest extends AbstractLangfuseClientTest {

    @Test
    void listOrganizationApiKeys() {
        assertThat(client.organizations().organizationsGetOrganizationApiKeys())
                .satisfies(response ->
                        assertThat(response.getApiKeys()).isNotNull());
    }

    @Test
    void listOrganizationMemberships() {
        assertThat(client.organizations().organizationsGetOrganizationMemberships())
                .satisfies(response ->
                        assertThat(response.getMemberships()).isNotNull());
    }

    @Test
    void listOrganizationProjects() {
        assertThat(client.organizations().organizationsGetOrganizationProjects())
                .satisfies(response ->
                        assertThat(response.getProjects()).isNotNull());
    }

    @Test
    void getProjectMemberships() {
        assertThat(client.organizations().organizationsGetProjectMemberships(
                APIOrganizationsGetProjectMembershipsRequest.newBuilder()
                        .projectId("langfuse-dev-project")
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMemberships()).isNotNull());
    }

    @Test
    void updateOrganizationMembership() {
        assertThat(client.organizations().organizationsUpdateOrganizationMembership(
                APIOrganizationsUpdateOrganizationMembershipRequest.newBuilder()
                        .membershipRequest(MembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .role(MembershipRole.MEMBER)
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getUserId()).isNotBlank());
    }

    @Test
    void updateProjectMembership() {
        assertThat(client.organizations().organizationsUpdateProjectMembership(
                APIOrganizationsUpdateProjectMembershipRequest.newBuilder()
                        .projectId("langfuse-dev-project")
                        .membershipRequest(MembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .role(MembershipRole.MEMBER)
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getUserId()).isNotBlank());
    }

    @Test
    void deleteOrganizationMembership() {
        assertThat(client.organizations().organizationsDeleteOrganizationMembership(
                APIOrganizationsDeleteOrganizationMembershipRequest.newBuilder()
                        .deleteMembershipRequest(DeleteMembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }

    @Test
    void deleteProjectMembership() {
        assertThat(client.organizations().organizationsDeleteProjectMembership(
                APIOrganizationsDeleteProjectMembershipRequest.newBuilder()
                        .projectId("langfuse-dev-project")
                        .deleteMembershipRequest(DeleteMembershipRequest.builder()
                                .userId("placeholder-user-id")
                                .build())
                        .build()))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
