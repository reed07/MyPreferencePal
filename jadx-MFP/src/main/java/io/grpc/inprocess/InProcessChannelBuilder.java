package io.grpc.inprocess;

import io.grpc.ExperimentalApi;
import io.grpc.Internal;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ClientTransportFactory.ClientTransportOptions;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourceHolder;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

@ExperimentalApi
public final class InProcessChannelBuilder extends AbstractManagedChannelImplBuilder<InProcessChannelBuilder> {
    private int maxInboundMetadataSize;
    private final String name;
    private ScheduledExecutorService scheduledExecutorService;

    static final class InProcessClientTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final int maxInboundMetadataSize;
        private final String name;
        private final ScheduledExecutorService timerService;
        private final boolean useSharedTimer;

        private InProcessClientTransportFactory(String str, @Nullable ScheduledExecutorService scheduledExecutorService, int i) {
            this.name = str;
            this.useSharedTimer = scheduledExecutorService == null;
            if (this.useSharedTimer) {
                scheduledExecutorService = (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE);
            }
            this.timerService = scheduledExecutorService;
            this.maxInboundMetadataSize = i;
        }

        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions) {
            if (!this.closed) {
                return new InProcessTransport(this.name, this.maxInboundMetadataSize, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent());
            }
            throw new IllegalStateException("The transport factory is closed.");
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timerService;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.useSharedTimer) {
                    SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timerService);
                }
            }
        }
    }

    @Deprecated
    public InProcessChannelBuilder usePlaintext(boolean z) {
        return this;
    }

    /* access modifiers changed from: protected */
    @Internal
    public ClientTransportFactory buildTransportFactory() {
        return new InProcessClientTransportFactory(this.name, this.scheduledExecutorService, this.maxInboundMetadataSize);
    }
}
