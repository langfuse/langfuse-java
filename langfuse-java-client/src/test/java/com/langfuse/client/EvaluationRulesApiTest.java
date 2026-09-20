package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.langfuse.api.evaluationRules.EvaluationRulesApi.APIEvaluationRulesListRequest;

/**
 * Integration tests for the Evaluation Rules API.
 *
 * @author Eric Deandrea
 */
abstract class EvaluationRulesApiTest extends AbstractLangfuseClientTest {

    @Test
    void listEvaluationRules() {
        assertThat(client.evaluationRules().evaluationRulesList(
                APIEvaluationRulesListRequest.newBuilder()
                        .build()))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }
}
