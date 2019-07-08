package com.google.api;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class SourceInfoProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_SourceInfo_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_SourceInfo_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_SourceInfo_descriptor, new String[]{"SourceFiles"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SourceInfoProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnyProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cgoogle/api/source_info.proto\u0012\ngoogle.api\u001a\u0019google/protobuf/any.proto\"8\n\nSourceInfo\u0012*\n\fsource_files\u0018\u0001 \u0003(\u000b2\u0014.google.protobuf.AnyBq\n\u000ecom.google.apiB\u000fSourceInfoProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                SourceInfoProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnyProto.getDescriptor();
    }
}
