package io.grpc;

import io.grpc.ServerCall.Listener;

public abstract class ForwardingServerCallListener<ReqT> extends PartialForwardingServerCallListener<ReqT> {

    public static abstract class SimpleForwardingServerCallListener<ReqT> extends ForwardingServerCallListener<ReqT> {
        private final Listener<ReqT> delegate;

        public /* bridge */ /* synthetic */ String toString() {
            return ForwardingServerCallListener.super.toString();
        }

        /* access modifiers changed from: protected */
        public Listener<ReqT> delegate() {
            return this.delegate;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Listener<ReqT> delegate();

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
