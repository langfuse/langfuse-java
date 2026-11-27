package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.langfuse.api.model.UnstableCreateDashboardPlacementRequest;
import com.langfuse.api.model.UnstableCreateDashboardRequest;
import com.langfuse.api.model.UnstableCreateDashboardWidgetRequest;
import com.langfuse.api.model.UnstableCreateWidgetPlacement;
import com.langfuse.api.model.UnstableDashboard;
import com.langfuse.api.model.UnstableDashboardWidgetChartType;
import com.langfuse.api.model.UnstableDashboardWidgetMetric;
import com.langfuse.api.model.UnstableDashboardWidgetMetricAggregation;
import com.langfuse.api.model.UnstableDashboardWidgetView;
import com.langfuse.api.model.UnstableUpdateDashboardPlacementRequest;
import com.langfuse.api.model.UnstableUpdateDashboardRequest;
import com.langfuse.api.unstableDashboardWidgets.UnstableDashboardWidgetsApi.APIUnstableDashboardWidgetsCreateRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsAddPlacementRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsCreateRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsDeletePlacementRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsDeleteRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsGetRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsListRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsUpdatePlacementRequest;
import com.langfuse.api.unstableDashboards.UnstableDashboardsApi.APIUnstableDashboardsUpdateRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class UnstableDashboardsApiAsyncTest extends AbstractLangfuseClientTest {

    private final String DASHBOARD_NAME = "async-test-dashboard-" + UUID.randomUUID().toString().substring(0, 8);
    private static String dashboardId;
    private static String widgetId;
    private static String placementId;

    @Test
    @Order(1)
    void createDashboard() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsCreate(
                APIUnstableDashboardsCreateRequest.newBuilder()
                        .unstableCreateDashboardRequest(UnstableCreateDashboardRequest.builder()
                                .name(DASHBOARD_NAME)
                                .description("Async test dashboard for integration tests")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(dashboard -> {
                    assertThat(dashboard.getId()).isNotBlank();
                    assertThat(dashboard.getName()).isEqualTo(DASHBOARD_NAME);
                    dashboardId = dashboard.getId();
                });
    }

    @Test
    @Order(1)
    void createWidgetForPlacement() {
        assertThat(client.unstableDashboardWidgets().unstableDashboardWidgetsCreate(
                APIUnstableDashboardWidgetsCreateRequest.newBuilder()
                        .unstableCreateDashboardWidgetRequest(UnstableCreateDashboardWidgetRequest.builder()
                                .name("async-placement-widget-" + UUID.randomUUID().toString().substring(0, 8))
                                .view(UnstableDashboardWidgetView.OBSERVATIONS)
                                .metrics(List.of(UnstableDashboardWidgetMetric.builder()
                                        .measure("count")
                                        .agg(UnstableDashboardWidgetMetricAggregation.COUNT)
                                        .build()))
                                .chartType(UnstableDashboardWidgetChartType.NUMBER)
                                .build())
                        .build()))
                .satisfies(widget -> {
                    assertThat(widget.getId()).isNotBlank();
                    widgetId = widget.getId();
                });
    }

    @Test
    @Order(2)
    void getDashboard() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsGet(
                APIUnstableDashboardsGetRequest.newBuilder()
                        .dashboardId(dashboardId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .extracting(UnstableDashboard::getId, UnstableDashboard::getName)
                .containsExactly(dashboardId, DASHBOARD_NAME);
    }

    @Test
    @Order(2)
    void listDashboards() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsList(
                APIUnstableDashboardsListRequest.newBuilder()
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(dashboards ->
                        assertThat(dashboards.getData())
                                .isNotEmpty()
                                .anyMatch(d -> DASHBOARD_NAME.equals(d.getName())));
    }

    @Test
    @Order(3)
    void updateDashboard() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsUpdate(
                APIUnstableDashboardsUpdateRequest.newBuilder()
                        .dashboardId(dashboardId)
                        .unstableUpdateDashboardRequest(UnstableUpdateDashboardRequest.builder()
                                .name(DASHBOARD_NAME + "-updated")
                                .description("Updated async description")
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(dashboard -> {
                    assertThat(dashboard.getId()).isEqualTo(dashboardId);
                    assertThat(dashboard.getName()).isEqualTo(DASHBOARD_NAME + "-updated");
                });
    }

    @Test
    @Order(4)
    void addPlacement() {
        var placement = client.unstableDashboards().unstableDashboardsAddPlacement(
                APIUnstableDashboardsAddPlacementRequest.newBuilder()
                        .dashboardId(dashboardId)
                        .unstableCreateDashboardPlacementRequest(
                                new UnstableCreateDashboardPlacementRequest(
                                        UnstableCreateWidgetPlacement.builder()
                                                .widgetId(widgetId)
                                                .type(UnstableCreateWidgetPlacement.TypeEnum.WIDGET)
                                                .x(0)
                                                .y(0)
                                                .width(6)
                                                .height(6)
                                                .build()))
                        .build());

        assertThat(placement).isNotNull();
        placementId = placement.getUnstableWidgetPlacement().getId();
        assertThat(placementId).isNotBlank();
    }

    @Test
    @Order(5)
    void updatePlacement() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsUpdatePlacement(
                APIUnstableDashboardsUpdatePlacementRequest.newBuilder()
                        .dashboardId(dashboardId)
                        .placementId(placementId)
                        .unstableUpdateDashboardPlacementRequest(UnstableUpdateDashboardPlacementRequest.builder()
                                .x(2)
                                .y(2)
                                .width(8)
                                .height(4)
                                .build())
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }

    @Test
    @Order(6)
    void deletePlacement() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsDeletePlacement(
                APIUnstableDashboardsDeletePlacementRequest.newBuilder()
                        .dashboardId(dashboardId)
                        .placementId(placementId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .isNotNull();
    }

    @Test
    @Order(7)
    void deleteDashboard() {
        assertThat(client.asyncUnstableDashboards().unstableDashboardsDelete(
                APIUnstableDashboardsDeleteRequest.newBuilder()
                        .dashboardId(dashboardId)
                        .build()))
                .succeedsWithin(Duration.ofSeconds(5))
                .satisfies(response ->
                        assertThat(response.getMessage()).isNotBlank());
    }
}
