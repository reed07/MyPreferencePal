package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.InternalChannelz.ChannelStats;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class OobChannel extends ManagedChannel implements InternalInstrumented<ChannelStats> {
    private static final Logger log = Logger.getLogger(OobChannel.class.getName());
    private final String authority;
    private final CallTracer channelCallsTracer;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private final DelayedClientTransport delayedTransport;
    private final Executor executor;
    private final InternalLogId logId;
    private volatile boolean shutdown;
    private InternalSubchannel subchannel;
    private final CountDownLatch terminatedLatch;
    private final ClientTransportProvider transportProvider;

    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        ClientCallImpl clientCallImpl = new ClientCallImpl(methodDescriptor, callOptions.getExecutor() == null ? this.executor : callOptions.getExecutor(), callOptions, this.transportProvider, this.deadlineCancellationExecutor, this.channelCallsTracer, false);
        return clientCallImpl;
    }

    public String authority() {
        return this.authority;
    }

    public boolean isTerminated() {
        return this.terminatedLatch.getCount() == 0;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    public ManagedChannel shutdown() {
        this.shutdown = true;
        this.delayedTransport.shutdown(Status.UNAVAILABLE.withDescription("OobChannel.shutdown() called"));
        return this;
    }

    public ManagedChannel shutdownNow() {
        this.shutdown = true;
        this.delayedTransport.shutdownNow(Status.UNAVAILABLE.withDescription("OobChannel.shutdownNow() called"));
        return this;
    }

    /* access modifiers changed from: 0000 */
    public InternalSubchannel getInternalSubchannel() {
        return this.subchannel;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("authority", (Object) this.authority).toString();
    }
}
