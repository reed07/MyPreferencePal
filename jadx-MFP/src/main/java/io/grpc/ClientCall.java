package io.grpc;

import javax.annotation.Nullable;

public abstract class ClientCall<ReqT, RespT> {

    public static abstract class Listener<T> {
        public void onClose(Status status, Metadata metadata) {
        }

        public void onHeaders(Metadata metadata) {
        }

        public void onMessage(T t) {
        }

        public void onReady() {
        }
    }

    public abstract void cancel(@Nullable String str, @Nullable Throwable th);

    public abstract void halfClose();

    public abstract void request(int i);

    public abstract void sendMessage(ReqT reqt);

    public abstract void start(Listener<RespT> listener, Metadata metadata);
}
