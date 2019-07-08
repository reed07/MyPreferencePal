package io.grpc.internal;

import io.grpc.InternalChannelz.SocketStats;
import io.grpc.InternalInstrumented;

public interface ServerTransport extends InternalInstrumented<SocketStats> {
}
