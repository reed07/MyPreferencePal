package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class BillingProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Billing_BillingDestination_descriptor = ((Descriptor) internal_static_google_api_Billing_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Billing_BillingDestination_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Billing_BillingDestination_descriptor, new String[]{"MonitoredResource", "Metrics"});
    static final Descriptor internal_static_google_api_Billing_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Billing_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Billing_descriptor, new String[]{"ConsumerDestinations"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private BillingProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor(), MetricProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/billing.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\u001a\u0017google/api/metric.proto\"\u0001\n\u0007Billing\u0012E\n\u0015consumer_destinations\u0018\b \u0003(\u000b2&.google.api.Billing.BillingDestination\u001aA\n\u0012BillingDestination\u0012\u001a\n\u0012monitored_resource\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007metrics\u0018\u0002 \u0003(\tBn\n\u000ecom.google.apiB\fBillingProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                BillingProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
        MetricProto.getDescriptor();
    }
}
