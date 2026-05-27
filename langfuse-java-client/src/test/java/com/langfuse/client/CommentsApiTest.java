package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.LangfuseApiException;
import com.langfuse.api.comments.CommentsApi;
import com.langfuse.api.ingestion.IngestionApi;
import com.langfuse.api.model.Comment;
import com.langfuse.api.model.CommentObjectType;
import com.langfuse.api.model.CreateCommentRequest;
import com.langfuse.api.model.IngestionBatchRequest;
import com.langfuse.api.model.IngestionEvent;
import com.langfuse.api.model.IngestionEventOneOf;
import com.langfuse.api.model.TraceBody;

/**
 * Integration tests for the Comments API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentsApiTest extends AbstractLangfuseClientTest {

    private static final String TRACE_ID = UUID.randomUUID().toString();
    private static String commentId;

    @Test
    @Order(1)
    void ingestTrace() {
        var traceEvent = IngestionEventOneOf.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(OffsetDateTime.now().toString())
                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                .body(TraceBody.builder()
                        .id(TRACE_ID)
                        .name("comments-test-trace")
                        .build())
                .build();

        var response = client.ingestion().ingestionBatch(
                IngestionApi.APIIngestionBatchRequest.newBuilder()
                        .ingestionBatchRequest(IngestionBatchRequest.builder()
                                .batch(List.of(new IngestionEvent(traceEvent)))
                                .build())
                        .build());

        assertThat(response.getSuccesses())
                .hasSize(1)
                .first()
                .satisfies(s -> assertThat(s.getStatus()).isEqualTo(201));
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
                            CommentsApi.APICommentsCreateRequest.newBuilder()
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
                CommentsApi.APICommentsGetByIdRequest.newBuilder()
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
                CommentsApi.APICommentsGetRequest.newBuilder()
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
