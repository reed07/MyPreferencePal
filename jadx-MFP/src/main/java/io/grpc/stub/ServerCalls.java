package io.grpc.stub;

import com.google.common.base.Preconditions;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCallHandler;
import io.grpc.Status;

public final class ServerCalls {

    public interface BidiStreamingMethod<ReqT, RespT> {
    }

    public interface ClientStreamingMethod<ReqT, RespT> {
    }

    public interface ServerStreamingMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
    }

    public interface UnaryMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
    }

    private interface UnaryRequestMethod<ReqT, RespT> {
    }

    private static final class UnaryServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        private final UnaryRequestMethod<ReqT, RespT> method;

        UnaryServerCallHandler(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod) {
            this.method = unaryRequestMethod;
        }
    }

    private ServerCalls() {
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryCall(UnaryMethod<ReqT, RespT> unaryMethod) {
        return asyncUnaryRequestCall(unaryMethod);
    }

    private static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryRequestCall(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod) {
        return new UnaryServerCallHandler(unaryRequestMethod);
    }

    public static void asyncUnimplementedUnaryCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        Preconditions.checkNotNull(methodDescriptor, "methodDescriptor");
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        streamObserver.onError(Status.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented", new Object[]{methodDescriptor.getFullMethodName()})).asRuntimeException());
    }
}
