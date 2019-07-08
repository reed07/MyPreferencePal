package io.grpc;

import io.grpc.Attributes.Key;
import java.util.concurrent.Executor;

public interface CallCredentials {
    @ExperimentalApi
    @Deprecated
    public static final Key<String> ATTR_AUTHORITY = Key.create("io.grpc.CallCredentials.authority");
    @ExperimentalApi
    @Deprecated
    public static final Key<SecurityLevel> ATTR_SECURITY_LEVEL = Key.create("io.grpc.internal.GrpcAttributes.securityLevel");

    @ExperimentalApi
    @Deprecated
    public interface MetadataApplier {
    }

    @ExperimentalApi
    public static abstract class RequestInfo {
    }

    @ExperimentalApi
    @Deprecated
    void applyRequestMetadata(MethodDescriptor<?, ?> methodDescriptor, Attributes attributes, Executor executor, MetadataApplier metadataApplier);
}
