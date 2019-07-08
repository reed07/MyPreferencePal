package io.grpc;

import io.grpc.ClientCall.Listener;
import javax.annotation.Nullable;

public abstract class ForwardingClientCall<ReqT, RespT> extends PartialForwardingClientCall<ReqT, RespT> {

    public static abstract class SimpleForwardingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final ClientCall<ReqT, RespT> delegate;

        public /* bridge */ /* synthetic */ void cancel(@Nullable String str, @Nullable Throwable th) {
            ForwardingClientCall.super.cancel(str, th);
        }

        public /* bridge */ /* synthetic */ void halfClose() {
            ForwardingClientCall.super.halfClose();
        }

        public /* bridge */ /* synthetic */ void request(int i) {
            ForwardingClientCall.super.request(i);
        }

        public /* bridge */ /* synthetic */ String toString() {
            return ForwardingClientCall.super.toString();
        }

        protected SimpleForwardingClientCall(ClientCall<ReqT, RespT> clientCall) {
            this.delegate = clientCall;
        }

        /* access modifiers changed from: protected */
        public ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }
    }

    /* access modifiers changed from: protected */
    public abstract ClientCall<ReqT, RespT> delegate();

    public /* bridge */ /* synthetic */ void cancel(@Nullable String str, @Nullable Throwable th) {
        super.cancel(str, th);
    }

    public /* bridge */ /* synthetic */ void halfClose() {
        super.halfClose();
    }

    public /* bridge */ /* synthetic */ void request(int i) {
        super.request(i);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public void start(Listener<RespT> listener, Metadata metadata) {
        delegate().start(listener, metadata);
    }

    public void sendMessage(ReqT reqt) {
        delegate().sendMessage(reqt);
    }
}
