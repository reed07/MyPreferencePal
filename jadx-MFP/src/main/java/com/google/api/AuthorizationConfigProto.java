package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class AuthorizationConfigProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_AuthorizationConfig_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_AuthorizationConfig_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_AuthorizationConfig_descriptor, new String[]{"Provider"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AuthorizationConfigProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n2google/api/experimental/authorization_config.proto\u0012\ngoogle.api\"'\n\u0013AuthorizationConfig\u0012\u0010\n\bprovider\u0018\u0001 \u0001(\tBb\n\u000ecom.google.apiB\u0018AuthorizationConfigProtoP\u0001Z-google.golang.org/genproto/googleapis/api;api¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                AuthorizationConfigProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
