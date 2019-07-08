package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class MonitoringProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Monitoring_MonitoringDestination_descriptor = ((Descriptor) internal_static_google_api_Monitoring_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Monitoring_MonitoringDestination_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Monitoring_MonitoringDestination_descriptor, new String[]{"MonitoredResource", "Metrics"});
    static final Descriptor internal_static_google_api_Monitoring_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Monitoring_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Monitoring_descriptor, new String[]{"ProducerDestinations", "ConsumerDestinations"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MonitoringProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgoogle/api/monitoring.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\"ì\u0001\n\nMonitoring\u0012K\n\u0015producer_destinations\u0018\u0001 \u0003(\u000b2,.google.api.Monitoring.MonitoringDestination\u0012K\n\u0015consumer_destinations\u0018\u0002 \u0003(\u000b2,.google.api.Monitoring.MonitoringDestination\u001aD\n\u0015MonitoringDestination\u0012\u001a\n\u0012monitored_resource\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007metrics\u0018\u0002 \u0003(\tBq\n\u000ecom.google.apiB\u000fMonitoringProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                MonitoringProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
    }
}
