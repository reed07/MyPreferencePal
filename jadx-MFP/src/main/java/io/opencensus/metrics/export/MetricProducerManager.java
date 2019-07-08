package io.opencensus.metrics.export;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MetricProducerManager {

    private static final class NoopMetricProducerManager extends MetricProducerManager {
        private NoopMetricProducerManager() {
        }
    }

    static MetricProducerManager newNoopMetricProducerManager() {
        return new NoopMetricProducerManager();
    }
}
