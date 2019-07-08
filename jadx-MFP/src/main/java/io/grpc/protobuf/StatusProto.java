package io.grpc.protobuf;

import com.google.rpc.Status;
import io.grpc.ExperimentalApi;
import io.grpc.Metadata.Key;
import io.grpc.protobuf.lite.ProtoLiteUtils;

@ExperimentalApi
public final class StatusProto {
    private static final Key<Status> STATUS_DETAILS_KEY = Key.of("grpc-status-details-bin", ProtoLiteUtils.metadataMarshaller(Status.getDefaultInstance()));

    private StatusProto() {
    }
}
