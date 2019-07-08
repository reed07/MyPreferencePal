package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class LabelProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_LabelDescriptor_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_LabelDescriptor_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_LabelDescriptor_descriptor, new String[]{"Key", "ValueType", "Description"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LabelProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/api/label.proto\u0012\ngoogle.api\"\u0001\n\u000fLabelDescriptor\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u00129\n\nvalue_type\u0018\u0002 \u0001(\u000e2%.google.api.LabelDescriptor.ValueType\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\",\n\tValueType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004BOOL\u0010\u0001\u0012\t\n\u0005INT64\u0010\u0002B_\n\u000ecom.google.apiB\nLabelProtoP\u0001Z5google.golang.org/genproto/googleapis/api/label;labelø\u0001\u0001¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                LabelProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
