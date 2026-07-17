package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Integration tests for the Projects API.
 *
 * @author Eric Deandrea
 */
class ProjectsApiTest extends AbstractLangfuseClientTest {

    @Test
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
}
