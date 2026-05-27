package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.annotationQueues.AnnotationQueuesApi;
import com.langfuse.api.model.AnnotationQueue;
import com.langfuse.api.model.CreateAnnotationQueueRequest;
import com.langfuse.api.model.CreateScoreConfigRequest;
import com.langfuse.api.model.ScoreConfigDataType;
import com.langfuse.api.scoreConfigs.ScoreConfigsApi;

/**
 * Integration tests for the Annotation Queues API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnnotationQueuesApiTest extends AbstractLangfuseClientTest {

    private static final String QUEUE_NAME = "test-queue-" + UUID.randomUUID().toString().substring(0, 8);
    private static String queueId;

    @Test
    @Order(1)
    void createQueue() {
        var scoreConfig = client.scoreConfigs().scoreConfigsCreate(
                ScoreConfigsApi.APIScoreConfigsCreateRequest.newBuilder()
                        .createScoreConfigRequest(CreateScoreConfigRequest.builder()
                                .name("queue-cfg-" + UUID.randomUUID().toString().substring(0, 6))
                                .dataType(ScoreConfigDataType.NUMERIC)
                                .minValue(0.0)
                                .maxValue(1.0)
                                .build())
                        .build());

        assertThat(client.annotationQueues().annotationQueuesCreateQueue(
                AnnotationQueuesApi.APIAnnotationQueuesCreateQueueRequest.newBuilder()
                        .createAnnotationQueueRequest(CreateAnnotationQueueRequest.builder()
                                .name(QUEUE_NAME)
                                .description("Test annotation queue")
                                .scoreConfigIds(List.of(scoreConfig.getId()))
                                .build())
                        .build()))
                .satisfies(queue -> {
                    assertThat(queue.getId()).isNotBlank();
                    assertThat(queue.getName()).isEqualTo(QUEUE_NAME);
                    assertThat(queue.getDescription()).isEqualTo("Test annotation queue");
                    assertThat(queue.getCreatedAt()).isNotNull();
                    queueId = queue.getId();
                });
    }

    @Test
    @Order(2)
    void getQueue() {
        assertThat(client.annotationQueues().annotationQueuesGetQueue(
                AnnotationQueuesApi.APIAnnotationQueuesGetQueueRequest.newBuilder()
                        .queueId(queueId)
                        .build()))
                .extracting(AnnotationQueue::getId, AnnotationQueue::getName)
                .containsExactly(queueId, QUEUE_NAME);
    }

    @Test
    @Order(2)
    void listQueues() {
        assertThat(client.annotationQueues().annotationQueuesListQueues(
                AnnotationQueuesApi.APIAnnotationQueuesListQueuesRequest.newBuilder()
                        .build()))
                .satisfies(queues -> {
                    assertThat(queues.getData())
                            .isNotEmpty()
                            .anyMatch(q -> QUEUE_NAME.equals(q.getName()));
                    assertThat(queues.getMeta().getTotalItems()).isGreaterThan(0);
                });
    }
}
