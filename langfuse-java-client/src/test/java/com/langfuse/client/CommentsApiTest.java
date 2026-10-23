package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.comments.CommentsApi.APICommentsCreateRequest;
import com.langfuse.api.comments.CommentsApi.APICommentsGetByIdRequest;
import com.langfuse.api.comments.CommentsApi.APICommentsGetRequest;
import com.langfuse.api.model.Comment;
import com.langfuse.api.model.CommentObjectType;
import com.langfuse.api.model.CreateCommentRequest;

/**
 * Integration tests for the Comments API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class CommentsApiTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String commentId;

    @Test
    @Order(1)
    void ingestTrace() {
        ingestTrace(TRACE_ID, SPAN_ID, "comments-test-trace");
    }

    @Test
    @Order(2)
    void createComment() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() -> {
                    var projectId = client.projects().projectsGet().getData().get(0).getId();

                    var response = client.comments().commentsCreate(
                            APICommentsCreateRequest.newBuilder()
                                    .createCommentRequest(CreateCommentRequest.builder()
                                            .projectId(projectId)
                                            .objectType("TRACE")
                                            .objectId(TRACE_ID)
                                            .content("This is a test comment")
                                            .build())
                                    .build());

                    assertThat(response.getId())
                            .isNotBlank();

                    commentId = response.getId();
                });
    }

    @Test
    @Order(3)
    void getCommentById() {
        assertThat(client.comments().commentsGetById(
                APICommentsGetByIdRequest.newBuilder()
                        .commentId(commentId)
                        .build()))
                .satisfies(comment -> assertThat(comment.getCreatedAt()).isNotNull())
                .extracting(Comment::getId, Comment::getContent, Comment::getObjectType, Comment::getObjectId)
                .containsExactly(commentId, "This is a test comment", CommentObjectType.TRACE, TRACE_ID);
    }

    @Test
    @Order(3)
    void listComments() {
        var comments = client.comments().commentsGet(
                APICommentsGetRequest.newBuilder()
                        .objectType("TRACE")
                        .objectId(TRACE_ID)
                        .build());

        assertThat(comments.getData())
                .hasSize(1)
                .first()
                .extracting(Comment::getId, Comment::getContent, Comment::getObjectType, Comment::getObjectId)
                .containsExactly(commentId, "This is a test comment", CommentObjectType.TRACE, TRACE_ID);
    }
}
