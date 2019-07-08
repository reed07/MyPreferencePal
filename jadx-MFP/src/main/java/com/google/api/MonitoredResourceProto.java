package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.StructProto;

public final class MonitoredResourceProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_MonitoredResourceDescriptor_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_MonitoredResourceDescriptor_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MonitoredResourceDescriptor_descriptor, new String[]{"Name", "Type", "DisplayName", "Description", "Labels"});
    static final Descriptor internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_descriptor = ((Descriptor) internal_static_google_api_MonitoredResourceMetadata_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_descriptor, new String[]{"Key", "Value"});
    static final Descriptor internal_static_google_api_MonitoredResourceMetadata_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MonitoredResourceMetadata_descriptor, new String[]{"SystemLabels", "UserLabels"});
    static final Descriptor internal_static_google_api_MonitoredResource_LabelsEntry_descriptor = ((Descriptor) internal_static_google_api_MonitoredResource_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_MonitoredResource_LabelsEntry_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MonitoredResource_LabelsEntry_descriptor, new String[]{"Key", "Value"});
    static final Descriptor internal_static_google_api_MonitoredResource_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_MonitoredResource_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MonitoredResource_descriptor, new String[]{"Type", "Labels"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MonitoredResourceProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {LabelProto.getDescriptor(), StructProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n#google/api/monitored_resource.proto\u0012\ngoogle.api\u001a\u0016google/api/label.proto\u001a\u001cgoogle/protobuf/struct.proto\"\u0001\n\u001bMonitoredResourceDescriptor\u0012\f\n\u0004name\u0018\u0005 \u0001(\t\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012+\n\u0006labels\u0018\u0004 \u0003(\u000b2\u001b.google.api.LabelDescriptor\"\u0001\n\u0011MonitoredResource\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u00129\n\u0006labels\u0018\u0002 \u0003(\u000b2).google.api.MonitoredResource.LabelsEntry\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001\"Ê\u0001\n\u0019MonitoredResourceMetadata\u0012.\n\rsystem_labels\u0018\u0001 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012J\n\u000buser_labels\u0018\u0002 \u0003(\u000b25.google.api.MonitoredResourceMetadata.UserLabelsEntry\u001a1\n\u000fUserLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001By\n\u000ecom.google.apiB\u0016MonitoredResourceProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/api/monitoredres;monitoredresø\u0001\u0001¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                MonitoredResourceProto.descriptor = fileDescriptor;
                return null;
            }
        });
        LabelProto.getDescriptor();
        StructProto.getDescriptor();
    }
}
