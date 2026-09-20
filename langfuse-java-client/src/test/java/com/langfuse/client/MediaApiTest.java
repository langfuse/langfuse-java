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
import com.langfuse.api.media.MediaApi.APIMediaGetUploadUrlRequest;
import com.langfuse.api.media.MediaApi.APIMediaPatchRequest;
import com.langfuse.api.model.GetMediaUploadUrlRequest;
import com.langfuse.api.model.MediaContentType;
import com.langfuse.api.model.PatchMediaBody;

/**
 * Integration tests for the Media API.
 *
 * @author Eric Deandrea
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class MediaApiTest extends AbstractLangfuseClientTest {

    private final String TRACE_ID = UUID.randomUUID().toString().replace("-", "");
    private final String SPAN_ID = TRACE_ID.substring(0, 16);
    private static String mediaId;

    @Test
    @Order(1)
    void ingestTrace() {
        ingestTrace(TRACE_ID, SPAN_ID, "media-test-trace");
    }

    @Test
    @Order(2)
    void getUploadUrl() {
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofSeconds(1))
                .ignoreExceptionsMatching(LangfuseApiException.class::isInstance)
                .untilAsserted(() ->
                        assertThat(client.media().mediaGetUploadUrl(
                                APIMediaGetUploadUrlRequest.newBuilder()
                                        .getMediaUploadUrlRequest(GetMediaUploadUrlRequest.builder()
                                                .traceId(TRACE_ID)
                                                .observationId(SPAN_ID)
                                                .contentType(MediaContentType.TEXT_PLAIN)
                                                .contentLength(13)
                                                .sha256Hash("n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg=")
                                                .field("input")
                                                .build())
                                        .build()))
                                .satisfies(response -> {
                                        assertThat(response.getMediaId()).isNotBlank();
                                        mediaId = response.getMediaId();
                                }));
    }

    @Test
    @Order(3)
    void patchMedia() {
        client.media().mediaPatch(
                APIMediaPatchRequest.newBuilder()
                        .mediaId(mediaId)
                        .patchMediaBody(PatchMediaBody.builder()
                                .uploadedAt(java.time.OffsetDateTime.now())
                                .uploadHttpStatus(200)
                                .build())
                        .build());
    }
}
