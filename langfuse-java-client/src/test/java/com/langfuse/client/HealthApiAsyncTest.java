package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;


/**
 * Async integration tests for the Health API.
 *
 * @author Eric Deandrea
 */
class HealthApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void healthEndpointReturnsOk() {
        assertThat(client.asyncHealth().healthHealth())
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(health -> {
                    assertThat(health.getStatus())
                            .isEqualTo("OK");

                    assertThat(health.getVersion())
                            .isNotBlank();
                });
    }
}
