package io.opencensus.metrics;

public abstract class MetricRegistry {

    private static final class NoopMetricRegistry extends MetricRegistry {
        private NoopMetricRegistry() {
        }
    }

    static MetricRegistry newNoopMetricRegistry() {
        return new NoopMetricRegistry();
    }
}
