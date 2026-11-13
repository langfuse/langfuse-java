package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.feedback.FeedbackApi.APIFeedbackSubmitRequest;
import com.langfuse.api.model.FeedbackTargetType;
import com.langfuse.api.model.SubmitFeedbackRequest;

/**
 * Async integration tests for the Feedback API.
 *
 * @author Eric Deandrea
 */
abstract class FeedbackApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void submitFeedback() {
        assertThat(client.asyncFeedback().feedbackSubmit(
                APIFeedbackSubmitRequest.newBuilder()
                        .submitFeedbackRequest(SubmitFeedbackRequest.builder()
                                .targetType(FeedbackTargetType.PUBLIC_API)
                                .target("scores-create")
                                .feedback("Async integration test feedback")
                                .build())
                        .build()))
                .failsWithin(Duration.ofSeconds(5))
                .withThrowableThat()
                .withCauseInstanceOf(LangfuseApiException.class);
    }
}
