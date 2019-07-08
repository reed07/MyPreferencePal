package io.opencensus.metrics;

import io.opencensus.metrics.export.ExportComponent;

public abstract class MetricsComponent {

    private static final class NoopMetricsComponent extends MetricsComponent {
        private static final ExportComponent EXPORT_COMPONENT = ExportComponent.newNoopExportComponent();
        private static final MetricRegistry METRIC_REGISTRY = MetricRegistry.newNoopMetricRegistry();

        private NoopMetricsComponent() {
        }
    }

    static MetricsComponent newNoopMetricsComponent() {
        return new NoopMetricsComponent();
    }
}
