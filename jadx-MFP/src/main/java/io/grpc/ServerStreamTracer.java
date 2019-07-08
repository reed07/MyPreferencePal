package io.grpc;

import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi
@ThreadSafe
public abstract class ServerStreamTracer extends StreamTracer {

    public static abstract class Factory {
        public abstract ServerStreamTracer newServerStreamTracer(String str, Metadata metadata);
    }

    public static abstract class ServerCallInfo<ReqT, RespT> {
    }
}
