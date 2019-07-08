package io.grpc.protobuf;

import com.google.protobuf.Message;
import io.grpc.MethodDescriptor.Marshaller;
import io.grpc.protobuf.lite.ProtoLiteUtils;

public class ProtoUtils {
    public static <T extends Message> Marshaller<T> marshaller(T t) {
        return ProtoLiteUtils.marshaller(t);
    }

    private ProtoUtils() {
    }
}
