package com.google.type;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class MoneyProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_type_Money_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_type_Money_fieldAccessorTable = new FieldAccessorTable(internal_static_google_type_Money_descriptor, new String[]{"CurrencyCode", "Units", "Nanos"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MoneyProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017google/type/money.proto\u0012\u000bgoogle.type\"<\n\u0005Money\u0012\u0015\n\rcurrency_code\u0018\u0001 \u0001(\t\u0012\r\n\u0005units\u0018\u0002 \u0001(\u0003\u0012\r\n\u0005nanos\u0018\u0003 \u0001(\u0005B]\n\u000fcom.google.typeB\nMoneyProtoP\u0001Z6google.golang.org/genproto/googleapis/type/money;money¢\u0002\u0003GTPb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                MoneyProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
