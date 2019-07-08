package io.grpc;

public abstract class ForwardingServerCall<ReqT, RespT> extends PartialForwardingServerCall<ReqT, RespT> {

    public static abstract class SimpleForwardingServerCall<ReqT, RespT> extends ForwardingServerCall<ReqT, RespT> {
        private final ServerCall<ReqT, RespT> delegate;

        public /* bridge */ /* synthetic */ String toString() {
            return ForwardingServerCall.super.toString();
        }

        /* access modifiers changed from: protected */
        public ServerCall<ReqT, RespT> delegate() {
            return this.delegate;
        }
    }

    /* access modifiers changed from: protected */
    public abstract ServerCall<ReqT, RespT> delegate();

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
