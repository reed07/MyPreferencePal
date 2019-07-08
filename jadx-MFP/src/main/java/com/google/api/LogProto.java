package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class LogProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_LogDescriptor_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_LogDescriptor_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_LogDescriptor_descriptor, new String[]{"Name", "Labels", "Description", "DisplayName"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LogProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {LabelProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0014google/api/log.proto\u0012\ngoogle.api\u001a\u0016google/api/label.proto\"u\n\rLogDescriptor\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012+\n\u0006labels\u0018\u0002 \u0003(\u000b2\u001b.google.api.LabelDescriptor\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0004 \u0001(\tBj\n\u000ecom.google.apiB\bLogProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                LogProto.descriptor = fileDescriptor;
                return null;
            }
        });
        LabelProto.getDescriptor();
    }
}
