package io.opencensus.metrics.export;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class MetricDescriptor {

    public enum Type {
        GAUGE_INT64,
        GAUGE_DOUBLE,
        GAUGE_DISTRIBUTION,
        CUMULATIVE_INT64,
        CUMULATIVE_DOUBLE,
        CUMULATIVE_DISTRIBUTION,
        SUMMARY
    }

    MetricDescriptor() {
    }
}
