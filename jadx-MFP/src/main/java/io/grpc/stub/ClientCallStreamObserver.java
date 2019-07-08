package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;

@ExperimentalApi
@DoNotMock
public abstract class ClientCallStreamObserver<V> extends CallStreamObserver<V> {
}
