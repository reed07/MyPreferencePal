package com.google.type;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class DateProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_type_Date_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_type_Date_fieldAccessorTable = new FieldAccessorTable(internal_static_google_type_Date_descriptor, new String[]{"Year", "Month", "Day"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DateProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/type/date.proto\u0012\u000bgoogle.type\"0\n\u0004Date\u0012\f\n\u0004year\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005month\u0018\u0002 \u0001(\u0005\u0012\u000b\n\u0003day\u0018\u0003 \u0001(\u0005B]\n\u000fcom.google.typeB\tDateProtoP\u0001Z4google.golang.org/genproto/googleapis/type/date;dateø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                DateProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
