package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.langfuse.api.evaluators.EvaluatorsApi.APIEvaluatorsListRequest;

/**
 * Integration tests for the Evaluators API.
 *
 * @author Eric Deandrea
 */
abstract class EvaluatorsApiTest extends AbstractLangfuseClientTest {

    @Test
    void listEvaluators() {
        assertThat(client.evaluators().evaluatorsList(
                APIEvaluatorsListRequest.newBuilder()
                        .build()))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }
}
