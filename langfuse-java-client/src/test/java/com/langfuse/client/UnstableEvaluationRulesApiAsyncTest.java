package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.unstableEvaluationRules.UnstableEvaluationRulesApi.APIUnstableEvaluationRulesDeleteRequest;
import com.langfuse.api.unstableEvaluationRules.UnstableEvaluationRulesApi.APIUnstableEvaluationRulesGetRequest;
import com.langfuse.api.unstableEvaluationRules.UnstableEvaluationRulesApi.APIUnstableEvaluationRulesListRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class UnstableEvaluationRulesApiAsyncTest extends AbstractLangfuseClientTest {

    private static String evaluationRuleId;

    @Test
    @Order(1)
    void listEvaluationRules() {
        assertThat(client.asyncUnstableEvaluationRules().unstableEvaluationRulesList(
                APIUnstableEvaluationRulesListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }

    @Test
    @Order(2)
    @Disabled("Requires an evaluator to be created first via UnstableEvaluatorsApi")
    void unstableEvaluationRulesCreate() {
        // Creating an evaluation rule requires an evaluator reference (evaluatorId).
        // The create request is a oneOf: unstableCreateLlmAsJudgeEvaluationRuleRequest
        // or unstableCreateCodeEvaluationRuleRequest. Both require an existing evaluator.
    }

    @Test
    @Order(3)
    @Disabled("Requires evaluation rule creation in previous step")
    void unstableEvaluationRulesUpdate() {
        // Updating an evaluation rule requires an existing rule ID.
    }

    @Test
    @Order(3)
    @Disabled("Requires evaluation rule creation in previous step")
    void getEvaluationRule() {
        assertThat(client.asyncUnstableEvaluationRules().unstableEvaluationRulesGet(
                APIUnstableEvaluationRulesGetRequest.newBuilder()
                        .evaluationRuleId(evaluationRuleId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(rule ->
                        assertThat(rule.getActualInstance()).isNotNull());
    }

    @Test
    @Order(4)
    @Disabled("Requires evaluation rule creation in previous step")
    void deleteEvaluationRule() {
        assertThat(client.asyncUnstableEvaluationRules().unstableEvaluationRulesDelete(
                APIUnstableEvaluationRulesDeleteRequest.newBuilder()
                        .evaluationRuleId(evaluationRuleId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
