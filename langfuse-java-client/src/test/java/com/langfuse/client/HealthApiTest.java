package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Integration tests for the Health API.
 *
 * @author Eric Deandrea
 */
class HealthApiTest extends AbstractLangfuseClientTest {

    @Test
    void healthEndpointReturnsOk() {
        assertThat(client.health().healthHealth())
                .satisfies(health -> {
                    assertThat(health.getStatus()).isEqualTo("OK");
                    assertThat(health.getVersion()).isNotBlank().matches("\\d+\\.\\d+\\.\\d+.*");
                });
    }
}
