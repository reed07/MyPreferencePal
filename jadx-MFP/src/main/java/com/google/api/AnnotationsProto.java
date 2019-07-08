package com.google.api;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.DescriptorProtos.MethodOptions;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionLite;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;

public final class AnnotationsProto {
    public static final int HTTP_FIELD_NUMBER = 72295728;
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    public static final GeneratedExtension<MethodOptions, HttpRule> http = GeneratedMessage.newFileScopedGeneratedExtension(HttpRule.class, HttpRule.getDefaultInstance());

    private AnnotationsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add((ExtensionLite<?, ?>) http);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    static {
        FileDescriptor[] fileDescriptorArr = {HttpProto.getDescriptor(), DescriptorProtos.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cgoogle/api/annotations.proto\u0012\ngoogle.api\u001a\u0015google/api/http.proto\u001a google/protobuf/descriptor.proto:E\n\u0004http\u0012\u001e.google.protobuf.MethodOptions\u0018°Ê¼\" \u0001(\u000b2\u0014.google.api.HttpRuleBn\n\u000ecom.google.apiB\u0010AnnotationsProtoP\u0001ZAgoogle.golang.org/genproto/googleapis/api/annotations;annotations¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                AnnotationsProto.descriptor = fileDescriptor;
                return null;
            }
        });
        http.internalInit((FieldDescriptor) descriptor.getExtensions().get(0));
        HttpProto.getDescriptor();
        DescriptorProtos.getDescriptor();
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }
}
