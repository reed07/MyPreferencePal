package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class EndpointProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Endpoint_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Endpoint_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Endpoint_descriptor, new String[]{"Name", "Aliases", "Features", "Target", "AllowCors"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private EndpointProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0019google/api/endpoint.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\"_\n\bEndpoint\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007aliases\u0018\u0002 \u0003(\t\u0012\u0010\n\bfeatures\u0018\u0004 \u0003(\t\u0012\u000e\n\u0006target\u0018e \u0001(\t\u0012\u0012\n\nallow_cors\u0018\u0005 \u0001(\bBo\n\u000ecom.google.apiB\rEndpointProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                EndpointProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
    }
}
