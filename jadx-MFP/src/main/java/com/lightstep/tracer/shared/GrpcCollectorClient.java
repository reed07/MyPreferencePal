package com.lightstep.tracer.shared;

import com.lightstep.tracer.grpc.CollectorServiceGrpc;
import com.lightstep.tracer.grpc.CollectorServiceGrpc.CollectorServiceBlockingStub;
import com.lightstep.tracer.grpc.ReportRequest;
import com.lightstep.tracer.grpc.ReportResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;

class GrpcCollectorClient extends CollectorClient {
    private CollectorServiceBlockingStub blockingStub;
    private ManagedChannel channel;
    private final ManagedChannelBuilder<?> channelBuilder;
    private final long deadlineMillis;
    private final AbstractTracer tracer;

    GrpcCollectorClient(AbstractTracer abstractTracer, ManagedChannelBuilder managedChannelBuilder, long j) {
        this.tracer = abstractTracer;
        this.channelBuilder = managedChannelBuilder;
        this.deadlineMillis = j;
        connect();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void shutdown() {
        /*
            r4 = this;
            monitor-enter(r4)
            io.grpc.ManagedChannel r0 = r4.channel     // Catch:{ InterruptedException -> 0x0011 }
            io.grpc.ManagedChannel r0 = r0.shutdown()     // Catch:{ InterruptedException -> 0x0011 }
            r1 = 5
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0011 }
            r0.awaitTermination(r1, r3)     // Catch:{ InterruptedException -> 0x0011 }
            goto L_0x001d
        L_0x000f:
            r0 = move-exception
            goto L_0x001f
        L_0x0011:
            com.lightstep.tracer.shared.AbstractTracer r0 = r4.tracer     // Catch:{ all -> 0x000f }
            java.lang.String r1 = "Interrupted while shutting down client, force shutdown now!"
            r0.warn(r1)     // Catch:{ all -> 0x000f }
            io.grpc.ManagedChannel r0 = r4.channel     // Catch:{ all -> 0x000f }
            r0.shutdownNow()     // Catch:{ all -> 0x000f }
        L_0x001d:
            monitor-exit(r4)
            return
        L_0x001f:
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.shared.GrpcCollectorClient.shutdown():void");
    }

    /* access modifiers changed from: 0000 */
    public synchronized ReportResponse report(ReportRequest reportRequest) {
        try {
            return ((CollectorServiceBlockingStub) this.blockingStub.withDeadlineAfter(this.deadlineMillis, TimeUnit.MILLISECONDS)).report(reportRequest);
        } catch (StatusRuntimeException e) {
            this.tracer.error("Status runtime exception (likely malformed spans): ", e);
            return null;
        } catch (Exception e2) {
            this.tracer.error("Exception sending report to collector: ", e2);
            return null;
        }
    }

    private synchronized void connect() {
        this.channel = this.channelBuilder.build();
        this.blockingStub = CollectorServiceGrpc.newBlockingStub(this.channel);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void reconnect() {
        shutdown();
        connect();
    }
}
