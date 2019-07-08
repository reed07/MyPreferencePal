package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;

@ExperimentalApi
@DoNotMock
public abstract class ServerCallStreamObserver<V> extends CallStreamObserver<V> {
}
