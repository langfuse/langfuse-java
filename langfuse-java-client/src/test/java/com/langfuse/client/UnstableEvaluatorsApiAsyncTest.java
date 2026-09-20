package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.model.UnstableCodeEvaluatorSourceCodeLanguage;
import com.langfuse.api.model.UnstableCreateCodeEvaluatorRequest;
import com.langfuse.api.model.UnstableCreateEvaluatorRequest;
import com.langfuse.api.unstableEvaluators.UnstableEvaluatorsApi.APIUnstableEvaluatorsCreateRequest;
import com.langfuse.api.unstableEvaluators.UnstableEvaluatorsApi.APIUnstableEvaluatorsListRequest;

/**
 * Async integration tests for the Unstable Evaluators API.
 *
 * @author Eric Deandrea
 */
abstract class UnstableEvaluatorsApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void listEvaluators() {
        assertThat(client.asyncUnstableEvaluators().unstableEvaluatorsList(
                APIUnstableEvaluatorsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }

    @Test
    void createCodeEvaluatorRequiresEnterprisePlan() {
        var createRequest = new UnstableCreateEvaluatorRequest(
                UnstableCreateCodeEvaluatorRequest.builder()
                        .name("async-test-evaluator-" + UUID.randomUUID().toString().substring(0, 8))
                        .sourceCode("def evaluate(output, expected_output, input, metadata):\n  return 1.0")
                        .sourceCodeLanguage(UnstableCodeEvaluatorSourceCodeLanguage.PYTHON)
                        .type(UnstableCreateCodeEvaluatorRequest.TypeEnum.CODE)
                        .build());

        assertThat(client.asyncUnstableEvaluators().unstableEvaluatorsCreate(
                APIUnstableEvaluatorsCreateRequest.newBuilder()
                        .unstableCreateEvaluatorRequest(createRequest)
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }
}
