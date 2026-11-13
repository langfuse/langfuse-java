package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.langfuse.api.evaluationRules.EvaluationRulesApi.APIEvaluationRulesListRequest;

/**
 * Async integration tests for the Evaluation Rules API.
 *
 * @author Eric Deandrea
 */
abstract class EvaluationRulesApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void listEvaluationRules() {
        assertThat(client.asyncEvaluationRules().evaluationRulesList(
                APIEvaluationRulesListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }
}
