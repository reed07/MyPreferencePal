package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class ContextProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_ContextRule_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_ContextRule_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_ContextRule_descriptor, new String[]{"Selector", "Requested", "Provided"});
    static final Descriptor internal_static_google_api_Context_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Context_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Context_descriptor, new String[]{"Rules"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ContextProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/context.proto\u0012\ngoogle.api\"1\n\u0007Context\u0012&\n\u0005rules\u0018\u0001 \u0003(\u000b2\u0017.google.api.ContextRule\"D\n\u000bContextRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u0011\n\trequested\u0018\u0002 \u0003(\t\u0012\u0010\n\bprovided\u0018\u0003 \u0003(\tBn\n\u000ecom.google.apiB\fContextProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ContextProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
