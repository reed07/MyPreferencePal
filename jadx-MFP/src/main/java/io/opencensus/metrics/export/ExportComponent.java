package io.opencensus.metrics.export;

public abstract class ExportComponent {

    private static final class NoopExportComponent extends ExportComponent {
        private static final MetricProducerManager METRIC_PRODUCER_MANAGER = MetricProducerManager.newNoopMetricProducerManager();

        private NoopExportComponent() {
        }
    }

    public static ExportComponent newNoopExportComponent() {
        return new NoopExportComponent();
    }
}
