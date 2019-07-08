package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class ExperimentalProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Experimental_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Experimental_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Experimental_descriptor, new String[]{"Authorization"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ExperimentalProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor(), AuthorizationConfigProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n*google/api/experimental/experimental.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\u001a2google/api/experimental/authorization_config.proto\"F\n\fExperimental\u00126\n\rauthorization\u0018\b \u0001(\u000b2\u001f.google.api.AuthorizationConfigB[\n\u000ecom.google.apiB\u0011ExperimentalProtoP\u0001Z-google.golang.org/genproto/googleapis/api;api¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ExperimentalProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
        AuthorizationConfigProto.getDescriptor();
    }
}
