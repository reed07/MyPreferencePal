package com.google.type;

import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;

public final class DayOfWeekProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DayOfWeekProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgoogle/type/dayofweek.proto\u0012\u000bgoogle.type*\u0001\n\tDayOfWeek\u0012\u001b\n\u0017DAY_OF_WEEK_UNSPECIFIED\u0010\u0000\u0012\n\n\u0006MONDAY\u0010\u0001\u0012\u000b\n\u0007TUESDAY\u0010\u0002\u0012\r\n\tWEDNESDAY\u0010\u0003\u0012\f\n\bTHURSDAY\u0010\u0004\u0012\n\n\u0006FRIDAY\u0010\u0005\u0012\f\n\bSATURDAY\u0010\u0006\u0012\n\n\u0006SUNDAY\u0010\u0007Bi\n\u000fcom.google.typeB\u000eDayOfWeekProtoP\u0001Z>google.golang.org/genproto/googleapis/type/dayofweek;dayofweek¢\u0002\u0003GTPb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                DayOfWeekProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
