package com.google.type;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class TimeOfDayProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_type_TimeOfDay_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_type_TimeOfDay_fieldAccessorTable = new FieldAccessorTable(internal_static_google_type_TimeOfDay_descriptor, new String[]{"Hours", "Minutes", "Seconds", "Nanos"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private TimeOfDayProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgoogle/type/timeofday.proto\u0012\u000bgoogle.type\"K\n\tTimeOfDay\u0012\r\n\u0005hours\u0018\u0001 \u0001(\u0005\u0012\u000f\n\u0007minutes\u0018\u0002 \u0001(\u0005\u0012\u000f\n\u0007seconds\u0018\u0003 \u0001(\u0005\u0012\r\n\u0005nanos\u0018\u0004 \u0001(\u0005Bi\n\u000fcom.google.typeB\u000eTimeOfDayProtoP\u0001Z>google.golang.org/genproto/googleapis/type/timeofday;timeofday¢\u0002\u0003GTPb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                TimeOfDayProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
