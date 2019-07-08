package com.google.type;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class LatLngProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_type_LatLng_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_type_LatLng_fieldAccessorTable = new FieldAccessorTable(internal_static_google_type_LatLng_descriptor, new String[]{"Latitude", "Longitude"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LatLngProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/type/latlng.proto\u0012\u000bgoogle.type\"-\n\u0006LatLng\u0012\u0010\n\blatitude\u0018\u0001 \u0001(\u0001\u0012\u0011\n\tlongitude\u0018\u0002 \u0001(\u0001B`\n\u000fcom.google.typeB\u000bLatLngProtoP\u0001Z8google.golang.org/genproto/googleapis/type/latlng;latlng¢\u0002\u0003GTPb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                LatLngProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
