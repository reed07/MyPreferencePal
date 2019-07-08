package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class DocumentationProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_DocumentationRule_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_DocumentationRule_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_DocumentationRule_descriptor, new String[]{"Selector", "Description", "DeprecationDescription"});
    static final Descriptor internal_static_google_api_Documentation_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Documentation_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Documentation_descriptor, new String[]{"Summary", "Pages", "Rules", "DocumentationRootUrl", "Overview"});
    static final Descriptor internal_static_google_api_Page_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_api_Page_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Page_descriptor, new String[]{"Name", "Content", "Subpages"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DocumentationProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/api/documentation.proto\u0012\ngoogle.api\"¡\u0001\n\rDocumentation\u0012\u000f\n\u0007summary\u0018\u0001 \u0001(\t\u0012\u001f\n\u0005pages\u0018\u0005 \u0003(\u000b2\u0010.google.api.Page\u0012,\n\u0005rules\u0018\u0003 \u0003(\u000b2\u001d.google.api.DocumentationRule\u0012\u001e\n\u0016documentation_root_url\u0018\u0004 \u0001(\t\u0012\u0010\n\boverview\u0018\u0002 \u0001(\t\"[\n\u0011DocumentationRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u001f\n\u0017deprecation_description\u0018\u0003 \u0001(\t\"I\n\u0004Page\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007content\u0018\u0002 \u0001(\t\u0012\"\n\bsubpages\u0018\u0003 \u0003(\u000b2\u0010.google.api.PageBt\n\u000ecom.google.apiB\u0012DocumentationProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                DocumentationProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
