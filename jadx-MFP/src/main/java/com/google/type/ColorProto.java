package com.google.type;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.WrappersProto;

public final class ColorProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_type_Color_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_type_Color_fieldAccessorTable = new FieldAccessorTable(internal_static_google_type_Color_descriptor, new String[]{"Red", "Green", "Blue", "Alpha"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ColorProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {WrappersProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017google/type/color.proto\u0012\u000bgoogle.type\u001a\u001egoogle/protobuf/wrappers.proto\"]\n\u0005Color\u0012\u000b\n\u0003red\u0018\u0001 \u0001(\u0002\u0012\r\n\u0005green\u0018\u0002 \u0001(\u0002\u0012\f\n\u0004blue\u0018\u0003 \u0001(\u0002\u0012*\n\u0005alpha\u0018\u0004 \u0001(\u000b2\u001b.google.protobuf.FloatValueB]\n\u000fcom.google.typeB\nColorProtoP\u0001Z6google.golang.org/genproto/googleapis/type/color;color¢\u0002\u0003GTPb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ColorProto.descriptor = fileDescriptor;
                return null;
            }
        });
        WrappersProto.getDescriptor();
    }
}
