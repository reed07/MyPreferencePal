package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class ControlProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Control_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Control_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Control_descriptor, new String[]{"Environment"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ControlProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/control.proto\u0012\ngoogle.api\"\u001e\n\u0007Control\u0012\u0013\n\u000benvironment\u0018\u0001 \u0001(\tBn\n\u000ecom.google.apiB\fControlProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ControlProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
