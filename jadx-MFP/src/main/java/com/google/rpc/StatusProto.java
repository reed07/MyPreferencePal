package com.google.rpc;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class StatusProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_rpc_Status_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_rpc_Status_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_Status_descriptor, new String[]{"Code", "Message", "Details"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private StatusProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnyProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017google/rpc/status.proto\u0012\ngoogle.rpc\u001a\u0019google/protobuf/any.proto\"N\n\u0006Status\u0012\f\n\u0004code\u0018\u0001 \u0001(\u0005\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\u0012%\n\u0007details\u0018\u0003 \u0003(\u000b2\u0014.google.protobuf.AnyB^\n\u000ecom.google.rpcB\u000bStatusProtoP\u0001Z7google.golang.org/genproto/googleapis/rpc/status;status¢\u0002\u0003RPCb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                StatusProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnyProto.getDescriptor();
    }
}
