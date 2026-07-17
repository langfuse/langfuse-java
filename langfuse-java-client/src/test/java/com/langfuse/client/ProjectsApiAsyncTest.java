package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

/**
 * Async integration tests for the Projects API.
 *
 * @author Eric Deandrea
 */
class ProjectsApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void getProjects() {
        assertThat(client.asyncProjects().projectsGet())
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(projects ->
                        assertThat(projects.getData()).isNotEmpty());
    }
}
