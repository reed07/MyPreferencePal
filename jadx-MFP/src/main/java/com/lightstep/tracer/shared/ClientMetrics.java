package com.lightstep.tracer.shared;

import com.lightstep.tracer.grpc.InternalMetrics;
import com.lightstep.tracer.grpc.MetricsSample;
import java.util.concurrent.atomic.AtomicLong;

class ClientMetrics {
    private final AtomicLong spansDropped = new AtomicLong(0);

    ClientMetrics() {
    }

    /* access modifiers changed from: 0000 */
    public void addSpansDropped(int i) {
        if (i != 0) {
            this.spansDropped.addAndGet((long) i);
        }
    }

    /* access modifiers changed from: 0000 */
    public InternalMetrics toInternalMetricsAndReset() {
        return InternalMetrics.newBuilder().addCounts(MetricsSample.newBuilder().setName("spans.dropped").setIntValue(getAndResetSpansDropped()).build()).build();
    }

    private long getAndResetSpansDropped() {
        return this.spansDropped.getAndSet(0);
    }
}
