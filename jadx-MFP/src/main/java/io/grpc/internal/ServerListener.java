package io.grpc.internal;

public interface ServerListener {
    ServerTransportListener transportCreated(ServerTransport serverTransport);
}
