package com.google.api;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class HttpBodyProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_HttpBody_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_HttpBody_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_HttpBody_descriptor, new String[]{"ContentType", "Data", "Extensions"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private HttpBodyProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnyProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0019google/api/httpbody.proto\u0012\ngoogle.api\u001a\u0019google/protobuf/any.proto\"X\n\bHttpBody\u0012\u0014\n\fcontent_type\u0018\u0001 \u0001(\t\u0012\f\n\u0004data\u0018\u0002 \u0001(\f\u0012(\n\nextensions\u0018\u0003 \u0003(\u000b2\u0014.google.protobuf.AnyBe\n\u000ecom.google.apiB\rHttpBodyProtoP\u0001Z;google.golang.org/genproto/googleapis/api/httpbody;httpbody¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                HttpBodyProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnyProto.getDescriptor();
    }
}
