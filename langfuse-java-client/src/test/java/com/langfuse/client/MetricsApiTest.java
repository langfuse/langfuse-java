package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.langfuse.api.metrics.MetricsApi.APIMetricsMetricsRequest;

/**
 * Integration tests for the Metrics V2 API.
 *
 * @author Eric Deandrea
 */
abstract class MetricsApiTest extends AbstractLangfuseClientTest {

    @Test
    void queryObservationCount() {
        var query = """
                {
                  "view": "observations",
                  "metrics": [{"measure": "count", "aggregation": "count"}],
                  "fromTimestamp": "2020-01-01T00:00:00.000Z",
                  "toTimestamp": "2099-01-01T00:00:00.000Z"
                }""";

        assertThat(client.metrics().metricsMetrics(
                APIMetricsMetricsRequest.newBuilder()
                        .query(query)
                        .build()))
                .isNotNull()
                .satisfies(response ->
                        assertThat(response.getData()).isNotNull());
    }
}
