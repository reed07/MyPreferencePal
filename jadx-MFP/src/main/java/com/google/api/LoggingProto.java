package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class LoggingProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Logging_LoggingDestination_descriptor = ((Descriptor) internal_static_google_api_Logging_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Logging_LoggingDestination_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Logging_LoggingDestination_descriptor, new String[]{"MonitoredResource", "Logs"});
    static final Descriptor internal_static_google_api_Logging_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Logging_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Logging_descriptor, new String[]{"ProducerDestinations", "ConsumerDestinations"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LoggingProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/logging.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\"×\u0001\n\u0007Logging\u0012E\n\u0015producer_destinations\u0018\u0001 \u0003(\u000b2&.google.api.Logging.LoggingDestination\u0012E\n\u0015consumer_destinations\u0018\u0002 \u0003(\u000b2&.google.api.Logging.LoggingDestination\u001a>\n\u0012LoggingDestination\u0012\u001a\n\u0012monitored_resource\u0018\u0003 \u0001(\t\u0012\f\n\u0004logs\u0018\u0001 \u0003(\tBn\n\u000ecom.google.apiB\fLoggingProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                LoggingProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
    }
}
