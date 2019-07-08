package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import io.grpc.ServerStreamTracer.Factory;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerTransportListener;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class InProcessServer implements InternalServer {
    private static final ConcurrentMap<String, InProcessServer> registry = new ConcurrentHashMap();
    private ServerListener listener;
    private final int maxInboundMetadataSize;
    private final String name;
    private final ObjectPool<ScheduledExecutorService> schedulerPool;
    private boolean shutdown;
    private final List<Factory> streamTracerFactories;

    static InProcessServer findServer(String str) {
        return (InProcessServer) registry.get(str);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("name", (Object) this.name).toString();
    }

    /* access modifiers changed from: 0000 */
    public synchronized ServerTransportListener register(InProcessTransport inProcessTransport) {
        if (this.shutdown) {
            return null;
        }
        return this.listener.transportCreated(inProcessTransport);
    }

    /* access modifiers changed from: 0000 */
    public ObjectPool<ScheduledExecutorService> getScheduledExecutorServicePool() {
        return this.schedulerPool;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxInboundMetadataSize() {
        return this.maxInboundMetadataSize;
    }

    /* access modifiers changed from: 0000 */
    public List<Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }
}
