package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.feedback.FeedbackApi.APIFeedbackSubmitRequest;
import com.langfuse.api.model.FeedbackTargetType;
import com.langfuse.api.model.SubmitFeedbackRequest;

/**
 * Integration tests for the Feedback API.
 *
 * @author Eric Deandrea
 */
abstract class FeedbackApiTest extends AbstractLangfuseClientTest {

    @Test
    void submitFeedback() {
        assertThatThrownBy(() -> client.feedback().feedbackSubmit(
                APIFeedbackSubmitRequest.newBuilder()
                        .submitFeedbackRequest(SubmitFeedbackRequest.builder()
                                .targetType(FeedbackTargetType.PUBLIC_API)
                                .target("scores-create")
                                .feedback("Integration test feedback")
                                .build())
                        .build()))
                .isInstanceOf(LangfuseApiException.class);
    }
}
