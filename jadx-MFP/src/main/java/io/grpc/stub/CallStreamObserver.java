package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import io.grpc.ExperimentalApi;

@ExperimentalApi
@DoNotMock
public abstract class CallStreamObserver<V> implements StreamObserver<V> {
}
