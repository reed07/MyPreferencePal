package io.grpc;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ServerCallHandler<RequestT, ResponseT> {
}
