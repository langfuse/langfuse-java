package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.UnstableCreateDashboardWidgetRequest;
import com.langfuse.api.model.UnstableDashboardWidget;
import com.langfuse.api.model.UnstableDashboardWidgetChartType;
import com.langfuse.api.model.UnstableDashboardWidgetMetric;
import com.langfuse.api.model.UnstableDashboardWidgetMetricAggregation;
import com.langfuse.api.model.UnstableDashboardWidgetView;
import com.langfuse.api.model.UnstableUpdateDashboardWidgetRequest;
import com.langfuse.api.unstableDashboardWidgets.UnstableDashboardWidgetsApi.APIUnstableDashboardWidgetsCreateRequest;
import com.langfuse.api.unstableDashboardWidgets.UnstableDashboardWidgetsApi.APIUnstableDashboardWidgetsDeleteRequest;
import com.langfuse.api.unstableDashboardWidgets.UnstableDashboardWidgetsApi.APIUnstableDashboardWidgetsGetRequest;
import com.langfuse.api.unstableDashboardWidgets.UnstableDashboardWidgetsApi.APIUnstableDashboardWidgetsListRequest;
import com.langfuse.api.unstableDashboardWidgets.UnstableDashboardWidgetsApi.APIUnstableDashboardWidgetsUpdateRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class UnstableDashboardWidgetsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String WIDGET_NAME = "async-test-widget-" + UUID.randomUUID().toString().substring(0, 8);
    private static String widgetId;

    @Test
    @Order(1)
    void createWidget() {
        assertThat(client.asyncUnstableDashboardWidgets().unstableDashboardWidgetsCreate(
                APIUnstableDashboardWidgetsCreateRequest.newBuilder()
                        .unstableCreateDashboardWidgetRequest(UnstableCreateDashboardWidgetRequest.builder()
                                .name(WIDGET_NAME)
                                .description("Async test widget")
                                .view(UnstableDashboardWidgetView.OBSERVATIONS)
                                .metrics(List.of(UnstableDashboardWidgetMetric.builder()
                                        .measure("count")
                                        .agg(UnstableDashboardWidgetMetricAggregation.COUNT)
                                        .build()))
                                .chartType(UnstableDashboardWidgetChartType.NUMBER)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(widget -> {
                    assertThat(widget.getId()).isNotBlank();
                    assertThat(widget.getName()).isEqualTo(WIDGET_NAME);
                    widgetId = widget.getId();
                });
    }

    @Test
    @Order(2)
    void getWidget() {
        assertThat(client.asyncUnstableDashboardWidgets().unstableDashboardWidgetsGet(
                APIUnstableDashboardWidgetsGetRequest.newBuilder()
                        .widgetId(widgetId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .extracting(UnstableDashboardWidget::getId, UnstableDashboardWidget::getName)
                .containsExactly(widgetId, WIDGET_NAME);
    }

    @Test
    @Order(2)
    void listWidgets() {
        assertThat(client.asyncUnstableDashboardWidgets().unstableDashboardWidgetsList(
                APIUnstableDashboardWidgetsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(widgets ->
                        assertThat(widgets.getData())
                                .isNotEmpty()
                                .anyMatch(w -> WIDGET_NAME.equals(w.getName())));
    }

    @Test
    @Order(3)
    void updateWidget() {
        assertThat(client.asyncUnstableDashboardWidgets().unstableDashboardWidgetsUpdate(
                APIUnstableDashboardWidgetsUpdateRequest.newBuilder()
                        .widgetId(widgetId)
                        .unstableUpdateDashboardWidgetRequest(UnstableUpdateDashboardWidgetRequest.builder()
                                .name(WIDGET_NAME + "-updated")
                                .description("Updated async test widget")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(widget -> {
                    assertThat(widget.getId()).isEqualTo(widgetId);
                    assertThat(widget.getName()).isEqualTo(WIDGET_NAME + "-updated");
                });
    }

    @Test
    @Order(4)
    void deleteWidget() {
        assertThat(client.asyncUnstableDashboardWidgets().unstableDashboardWidgetsDelete(
                APIUnstableDashboardWidgetsDeleteRequest.newBuilder()
                        .widgetId(widgetId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
