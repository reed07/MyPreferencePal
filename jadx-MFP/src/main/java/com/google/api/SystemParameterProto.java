package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class SystemParameterProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_SystemParameterRule_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_SystemParameterRule_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_SystemParameterRule_descriptor, new String[]{"Selector", "Parameters"});
    static final Descriptor internal_static_google_api_SystemParameter_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_api_SystemParameter_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_SystemParameter_descriptor, new String[]{"Name", "HttpHeader", "UrlQueryParameter"});
    static final Descriptor internal_static_google_api_SystemParameters_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_SystemParameters_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_SystemParameters_descriptor, new String[]{"Rules"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SystemParameterProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!google/api/system_parameter.proto\u0012\ngoogle.api\"B\n\u0010SystemParameters\u0012.\n\u0005rules\u0018\u0001 \u0003(\u000b2\u001f.google.api.SystemParameterRule\"X\n\u0013SystemParameterRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012/\n\nparameters\u0018\u0002 \u0003(\u000b2\u001b.google.api.SystemParameter\"Q\n\u000fSystemParameter\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bhttp_header\u0018\u0002 \u0001(\t\u0012\u001b\n\u0013url_query_parameter\u0018\u0003 \u0001(\tBv\n\u000ecom.google.apiB\u0014SystemParameterProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                SystemParameterProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
