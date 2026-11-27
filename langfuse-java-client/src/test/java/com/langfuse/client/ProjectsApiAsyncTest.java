package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Async integration tests for the Projects API.
 *
 * @author Eric Deandrea
 */
abstract class ProjectsApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void getProjects() {
        assertThat(client.asyncProjects().projectsGet())
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(projects ->
                        assertThat(projects.getData()).isNotEmpty());
    }

    @Test
    @Disabled("Requires org-admin role")
    void createProject() {
    }

    @Test
    @Disabled("Requires project creation in previous step")
    void updateProject() {
    }

    @Test
    @Disabled("Requires project creation in previous step")
    void createApiKey() {
    }

    @Test
    @Disabled("Requires API key creation in previous step")
    void getApiKeys() {
    }

    @Test
    @Disabled("Requires API key creation in previous step")
    void deleteApiKey() {
    }

    @Test
    @Disabled("Requires project creation in previous step")
    void deleteProject() {
    }
}
