package com.langfuse.testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Verifies that {@link LangfuseContainer} starts all services correctly
 * and the Langfuse health endpoint is reachable.
 *
 * @author Eric Deandrea
 */
@Testcontainers
class LangfuseContainerTest {

    @Container
    static final LangfuseContainer langfuse = new LangfuseContainer();

    @Test
    void containerIsRunning() {
        assertThat(langfuse.isRunning())
                .isTrue();
    }

    @Test
    void langfuseUrlIsAvailable() {
        assertThat(langfuse.getLangfuseUrl())
                .isNotBlank()
                .startsWith("http://");
    }

    @Test
    void publicKeyMatchesDefault() {
        assertThat(langfuse.getPublicKey())
                .isEqualTo("pk-lf-dev");
    }

    @Test
    void secretKeyMatchesDefault() {
        assertThat(langfuse.getSecretKey())
                .isEqualTo("sk-lf-dev");
    }

    @Test
    void healthEndpointReturnsOk() throws Exception {
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create(langfuse.getLangfuseUrl() + "/api/public/health"))
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode())
                .isEqualTo(200);

        assertThat(response.body())
                .contains("\"status\"")
                .contains("\"version\"");
    }
}
