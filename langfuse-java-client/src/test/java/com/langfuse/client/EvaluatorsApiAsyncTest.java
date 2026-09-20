package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.langfuse.api.evaluators.EvaluatorsApi.APIEvaluatorsListRequest;

/**
 * Async integration tests for the Evaluators API.
 *
 * @author Eric Deandrea
 */
abstract class EvaluatorsApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void listEvaluators() {
        assertThat(client.asyncEvaluators().evaluatorsList(
                APIEvaluatorsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }
}
