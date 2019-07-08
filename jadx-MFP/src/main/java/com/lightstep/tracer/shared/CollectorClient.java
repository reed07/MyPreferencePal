package com.lightstep.tracer.shared;

import com.lightstep.tracer.grpc.ReportRequest;
import com.lightstep.tracer.grpc.ReportResponse;

abstract class CollectorClient {
    /* access modifiers changed from: 0000 */
    public abstract void reconnect();

    /* access modifiers changed from: 0000 */
    public abstract ReportResponse report(ReportRequest reportRequest);

    /* access modifiers changed from: 0000 */
    public abstract void shutdown();

    CollectorClient() {
    }
}
