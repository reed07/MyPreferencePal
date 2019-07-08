package io.grpc.stub;

import io.grpc.ExperimentalApi;

@ExperimentalApi
public interface ClientResponseObserver<ReqT, RespT> extends StreamObserver<RespT> {
    void beforeStart(ClientCallStreamObserver<ReqT> clientCallStreamObserver);
}
