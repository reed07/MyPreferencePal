package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class ConsumerProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_ProjectProperties_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_ProjectProperties_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_ProjectProperties_descriptor, new String[]{"Properties"});
    static final Descriptor internal_static_google_api_Property_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_Property_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Property_descriptor, new String[]{"Name", "Type", "Description"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ConsumerProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0019google/api/consumer.proto\u0012\ngoogle.api\"=\n\u0011ProjectProperties\u0012(\n\nproperties\u0018\u0001 \u0003(\u000b2\u0014.google.api.Property\"¬\u0001\n\bProperty\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012/\n\u0004type\u0018\u0002 \u0001(\u000e2!.google.api.Property.PropertyType\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\"L\n\fPropertyType\u0012\u000f\n\u000bUNSPECIFIED\u0010\u0000\u0012\t\n\u0005INT64\u0010\u0001\u0012\b\n\u0004BOOL\u0010\u0002\u0012\n\n\u0006STRING\u0010\u0003\u0012\n\n\u0006DOUBLE\u0010\u0004Bh\n\u000ecom.google.apiB\rConsumerProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfigb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ConsumerProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
