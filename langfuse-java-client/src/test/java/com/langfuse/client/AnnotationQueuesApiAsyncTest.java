package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesCreateQueueAssignmentRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesCreateQueueItemRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesCreateQueueRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesDeleteQueueAssignmentRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesDeleteQueueItemRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesGetQueueItemRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesGetQueueRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesListQueueItemsRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesListQueuesRequest;
import com.langfuse.api.annotationQueues.AnnotationQueuesApi.APIAnnotationQueuesUpdateQueueItemRequest;
import com.langfuse.api.model.AnnotationQueue;
import com.langfuse.api.model.AnnotationQueueAssignmentRequest;
import com.langfuse.api.model.AnnotationQueueObjectType;
import com.langfuse.api.model.AnnotationQueueStatus;
import com.langfuse.api.model.CreateAnnotationQueueItemRequest;
import com.langfuse.api.model.CreateAnnotationQueueRequest;
import com.langfuse.api.model.CreateScoreConfigRequest;
import com.langfuse.api.model.ScoreConfigDataType;
import com.langfuse.api.model.UpdateAnnotationQueueItemRequest;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi.APIScoreConfigsCreateRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class AnnotationQueuesApiAsyncTest extends AbstractLangfuseClientTest {

    private final String QUEUE_NAME = "async-test-queue-" + UUID.randomUUID().toString().substring(0, 8);
    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String queueId;
    private static String queueItemId;

    @Test
    @Order(1)
    void createQueue() {
        var scoreConfig = client.scoreConfigs().scoreConfigsCreate(
                APIScoreConfigsCreateRequest.newBuilder()
                        .createScoreConfigRequest(CreateScoreConfigRequest.builder()
                                .name("async-queue-cfg-" + UUID.randomUUID().toString().substring(0, 6))
                                .dataType(ScoreConfigDataType.NUMERIC)
                                .minValue(0.0)
                                .maxValue(1.0)
                                .build())
                        .build());

        assertThat(client.asyncAnnotationQueues().annotationQueuesCreateQueue(
                APIAnnotationQueuesCreateQueueRequest.newBuilder()
                        .createAnnotationQueueRequest(CreateAnnotationQueueRequest.builder()
                                .name(QUEUE_NAME)
                                .description("Async test annotation queue")
                                .scoreConfigIds(List.of(scoreConfig.getId()))
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(queue -> {
                    assertThat(queue.getId()).isNotBlank();
                    assertThat(queue.getName()).isEqualTo(QUEUE_NAME);
                    assertThat(queue.getDescription()).isEqualTo("Async test annotation queue");
                    assertThat(queue.getCreatedAt()).isNotNull();
                    queueId = queue.getId();
                });
    }

    @Test
    @Order(1)
    void ingestTrace() {
        ingestTrace(TRACE_ID, SPAN_ID, "async-annotation-queue-test-trace");
    }

    @Test
    @Order(2)
    void getQueue() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesGetQueue(
                APIAnnotationQueuesGetQueueRequest.newBuilder()
                        .queueId(queueId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .extracting(AnnotationQueue::getId, AnnotationQueue::getName)
                .containsExactly(queueId, QUEUE_NAME);
    }

    @Test
    @Order(2)
    void listQueues() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesListQueues(
                APIAnnotationQueuesListQueuesRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(queues -> {
                    assertThat(queues.getData())
                            .isNotEmpty()
                            .anyMatch(q -> QUEUE_NAME.equals(q.getName()));
                    assertThat(queues.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }

    @Test
    @Order(3)
    void createQueueItem() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesCreateQueueItem(
                APIAnnotationQueuesCreateQueueItemRequest.newBuilder()
                        .queueId(queueId)
                        .createAnnotationQueueItemRequest(CreateAnnotationQueueItemRequest.builder()
                                .objectId(TRACE_ID)
                                .objectType(AnnotationQueueObjectType.TRACE)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(item -> {
                    assertThat(item.getId()).isNotBlank();
                    assertThat(item.getQueueId()).isEqualTo(queueId);
                    assertThat(item.getObjectId()).isEqualTo(TRACE_ID);
                    assertThat(item.getObjectType()).isEqualTo(AnnotationQueueObjectType.TRACE);
                    assertThat(item.getStatus()).isNotNull();
                    assertThat(item.getCreatedAt()).isNotNull();
                    queueItemId = item.getId();
                });
    }

    @Test
    @Order(4)
    void getQueueItem() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesGetQueueItem(
                APIAnnotationQueuesGetQueueItemRequest.newBuilder()
                        .queueId(queueId)
                        .itemId(queueItemId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(item -> {
                    assertThat(item.getId()).isEqualTo(queueItemId);
                    assertThat(item.getQueueId()).isEqualTo(queueId);
                    assertThat(item.getObjectId()).isEqualTo(TRACE_ID);
                });
    }

    @Test
    @Order(4)
    void listQueueItems() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesListQueueItems(
                APIAnnotationQueuesListQueueItemsRequest.newBuilder()
                        .queueId(queueId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(items -> {
                    assertThat(items.getData())
                            .isNotEmpty()
                            .anyMatch(i -> queueItemId.equals(i.getId()));
                    assertThat(items.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }

    @Test
    @Order(5)
    void updateQueueItem() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesUpdateQueueItem(
                APIAnnotationQueuesUpdateQueueItemRequest.newBuilder()
                        .queueId(queueId)
                        .itemId(queueItemId)
                        .updateAnnotationQueueItemRequest(UpdateAnnotationQueueItemRequest.builder()
                                .status(AnnotationQueueStatus.COMPLETED)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(item -> {
                    assertThat(item.getId()).isEqualTo(queueItemId);
                    assertThat(item.getStatus()).isEqualTo(AnnotationQueueStatus.COMPLETED);
                });
    }

    @Disabled("Requires a valid user ID not available via API key auth")
    @Test
    @Order(6)
    void createAndDeleteQueueAssignment() {
        var userId = client.projects().projectsGet().getData().get(0).getOrganization().getId();

        assertThat(client.asyncAnnotationQueues().annotationQueuesCreateQueueAssignment(
                APIAnnotationQueuesCreateQueueAssignmentRequest.newBuilder()
                        .queueId(queueId)
                        .annotationQueueAssignmentRequest(AnnotationQueueAssignmentRequest.builder()
                                .userId(userId)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response -> {
                    assertThat(response.getQueueId()).isEqualTo(queueId);
                    assertThat(response.getUserId()).isEqualTo(userId);
                });

        assertThat(client.asyncAnnotationQueues().annotationQueuesDeleteQueueAssignment(
                APIAnnotationQueuesDeleteQueueAssignmentRequest.newBuilder()
                        .queueId(queueId)
                        .annotationQueueAssignmentRequest(AnnotationQueueAssignmentRequest.builder()
                                .userId(userId)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getSuccess()).isTrue());
    }

    @Test
    @Order(7)
    void deleteQueueItem() {
        assertThat(client.asyncAnnotationQueues().annotationQueuesDeleteQueueItem(
                APIAnnotationQueuesDeleteQueueItemRequest.newBuilder()
                        .queueId(queueId)
                        .itemId(queueItemId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
