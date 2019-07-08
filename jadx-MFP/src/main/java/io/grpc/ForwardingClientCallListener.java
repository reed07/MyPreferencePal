package io.grpc;

import io.grpc.ClientCall.Listener;

public abstract class ForwardingClientCallListener<RespT> extends PartialForwardingClientCallListener<RespT> {

    public static abstract class SimpleForwardingClientCallListener<RespT> extends ForwardingClientCallListener<RespT> {
        private final Listener<RespT> delegate;

        public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
            ForwardingClientCallListener.super.onClose(status, metadata);
        }

        public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
            ForwardingClientCallListener.super.onHeaders(metadata);
        }

        public /* bridge */ /* synthetic */ void onReady() {
            ForwardingClientCallListener.super.onReady();
        }

        public /* bridge */ /* synthetic */ String toString() {
            return ForwardingClientCallListener.super.toString();
        }

        protected SimpleForwardingClientCallListener(Listener<RespT> listener) {
            this.delegate = listener;
        }

        /* access modifiers changed from: protected */
        public Listener<RespT> delegate() {
            return this.delegate;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Listener<RespT> delegate();

    public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
        super.onClose(status, metadata);
    }

    public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
        super.onHeaders(metadata);
    }

    public /* bridge */ /* synthetic */ void onReady() {
        super.onReady();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public void onMessage(RespT respt) {
        delegate().onMessage(respt);
    }
}
