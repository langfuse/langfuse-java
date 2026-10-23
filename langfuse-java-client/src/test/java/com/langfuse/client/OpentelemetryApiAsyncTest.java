package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.langfuse.api.model.OpentelemetryExportTracesRequest;
import com.langfuse.api.model.OtelAttribute;
import com.langfuse.api.model.OtelAttributeValue;
import com.langfuse.api.model.OtelResource;
import com.langfuse.api.model.OtelResourceSpan;
import com.langfuse.api.model.OtelScope;
import com.langfuse.api.model.OtelScopeSpan;
import com.langfuse.api.model.OtelSpan;
import com.langfuse.api.opentelemetry.OpentelemetryApi.APIOpentelemetryExportTracesRequest;

abstract class OpentelemetryApiAsyncTest extends AbstractLangfuseClientTest {

    @Test
    void exportTraces() {
        var traceId = UUID.randomUUID().toString().replace("-", "");
        var spanId = traceId.substring(0, 16);
        var nowNanos = String.valueOf(System.currentTimeMillis() * 1_000_000L);

        var span = OtelSpan.builder()
                .traceId(traceId)
                .spanId(spanId)
                .name("async-otel-test-span")
                .kind(1)
                .startTimeUnixNano(nowNanos)
                .endTimeUnixNano(String.valueOf(Long.parseLong(nowNanos) + 1_000_000_000L))
                .attributes(List.of(
                        OtelAttribute.builder()
                                .key("test.attribute")
                                .value(OtelAttributeValue.builder()
                                        .stringValue("async-test-value")
                                        .build())
                                .build()))
                .build();

        var resourceSpan = OtelResourceSpan.builder()
                .resource(OtelResource.builder()
                        .attributes(List.of(
                                OtelAttribute.builder()
                                        .key("langfuse.trace.name")
                                        .value(OtelAttributeValue.builder()
                                                .stringValue("async-otel-export-test")
                                                .build())
                                        .build(),
                                OtelAttribute.builder()
                                        .key("service.name")
                                        .value(OtelAttributeValue.builder()
                                                .stringValue("langfuse-java-async-test")
                                                .build())
                                        .build()))
                        .build())
                .scopeSpans(List.of(OtelScopeSpan.builder()
                        .scope(OtelScope.builder()
                                .name("langfuse-java-async-test")
                                .version("1.0.0")
                                .build())
                        .spans(List.of(span))
                        .build()))
                .build();

        assertThat(client.asyncOpentelemetry().opentelemetryExportTraces(
                APIOpentelemetryExportTracesRequest.newBuilder()
                        .opentelemetryExportTracesRequest(OpentelemetryExportTracesRequest.builder()
                                .resourceSpans(List.of(resourceSpan))
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }
}
