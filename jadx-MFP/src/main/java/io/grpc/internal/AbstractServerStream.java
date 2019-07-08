package io.grpc.internal;

public abstract class AbstractServerStream extends AbstractStream implements io.grpc.internal.MessageFramer.Sink, ServerStream {

    protected interface Sink {
    }

    protected static abstract class TransportState extends io.grpc.internal.AbstractStream.TransportState {
    }
}
