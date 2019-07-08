package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

public final class TransportTracer {
    private static final Factory DEFAULT_FACTORY = new Factory(TimeProvider.SYSTEM_TIME_PROVIDER);
    private FlowControlReader flowControlWindowReader;
    private long keepAlivesSent;
    private long lastLocalStreamCreatedTimeNanos;
    private volatile long lastMessageReceivedTimeNanos;
    private long lastMessageSentTimeNanos;
    private final LongCounter messagesReceived;
    private long messagesSent;
    private long streamsFailed;
    private long streamsStarted;
    private long streamsSucceeded;
    private final TimeProvider timeProvider;

    public static final class Factory {
        private TimeProvider timeProvider;

        @VisibleForTesting
        public Factory(TimeProvider timeProvider2) {
            this.timeProvider = timeProvider2;
        }

        public TransportTracer create() {
            return new TransportTracer(this.timeProvider);
        }
    }

    public interface FlowControlReader {
    }

    public static final class FlowControlWindows {
    }

    public TransportTracer() {
        this.messagesReceived = LongCounterFactory.create();
        this.timeProvider = TimeProvider.SYSTEM_TIME_PROVIDER;
    }

    private TransportTracer(TimeProvider timeProvider2) {
        this.messagesReceived = LongCounterFactory.create();
        this.timeProvider = timeProvider2;
    }

    public void reportLocalStreamStarted() {
        this.streamsStarted++;
        this.lastLocalStreamCreatedTimeNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportStreamClosed(boolean z) {
        if (z) {
            this.streamsSucceeded++;
        } else {
            this.streamsFailed++;
        }
    }

    public void reportMessageSent(int i) {
        if (i != 0) {
            this.messagesSent += (long) i;
            this.lastMessageSentTimeNanos = this.timeProvider.currentTimeNanos();
        }
    }

    public void reportMessageReceived() {
        this.messagesReceived.add(1);
        this.lastMessageReceivedTimeNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportKeepAliveSent() {
        this.keepAlivesSent++;
    }

    public void setFlowControlWindowReader(FlowControlReader flowControlReader) {
        this.flowControlWindowReader = (FlowControlReader) Preconditions.checkNotNull(flowControlReader);
    }

    public static Factory getDefaultFactory() {
        return DEFAULT_FACTORY;
    }
}
