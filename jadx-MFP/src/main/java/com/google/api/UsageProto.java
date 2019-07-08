package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class UsageProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_UsageRule_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_UsageRule_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_UsageRule_descriptor, new String[]{"Selector", "AllowUnregisteredCalls", "SkipServiceControl"});
    static final Descriptor internal_static_google_api_Usage_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Usage_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Usage_descriptor, new String[]{"Requirements", "Rules", "ProducerNotificationChannel"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private UsageProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/api/usage.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\"j\n\u0005Usage\u0012\u0014\n\frequirements\u0018\u0001 \u0003(\t\u0012$\n\u0005rules\u0018\u0006 \u0003(\u000b2\u0015.google.api.UsageRule\u0012%\n\u001dproducer_notification_channel\u0018\u0007 \u0001(\t\"]\n\tUsageRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012 \n\u0018allow_unregistered_calls\u0018\u0002 \u0001(\b\u0012\u001c\n\u0014skip_service_control\u0018\u0003 \u0001(\bBl\n\u000ecom.google.apiB\nUsageProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                UsageProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
    }
}
