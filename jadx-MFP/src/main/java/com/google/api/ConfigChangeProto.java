package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class ConfigChangeProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Advice_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_Advice_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Advice_descriptor, new String[]{"Description"});
    static final Descriptor internal_static_google_api_ConfigChange_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_ConfigChange_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_ConfigChange_descriptor, new String[]{"Element", "OldValue", "NewValue", "ChangeType", "Advices"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ConfigChangeProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/api/config_change.proto\u0012\ngoogle.api\"\u0001\n\fConfigChange\u0012\u000f\n\u0007element\u0018\u0001 \u0001(\t\u0012\u0011\n\told_value\u0018\u0002 \u0001(\t\u0012\u0011\n\tnew_value\u0018\u0003 \u0001(\t\u0012+\n\u000bchange_type\u0018\u0004 \u0001(\u000e2\u0016.google.api.ChangeType\u0012#\n\u0007advices\u0018\u0005 \u0003(\u000b2\u0012.google.api.Advice\"\u001d\n\u0006Advice\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t*O\n\nChangeType\u0012\u001b\n\u0017CHANGE_TYPE_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005ADDED\u0010\u0001\u0012\u000b\n\u0007REMOVED\u0010\u0002\u0012\f\n\bMODIFIED\u0010\u0003Bq\n\u000ecom.google.apiB\u0011ConfigChangeProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/api/configchange;configchange¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ConfigChangeProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
