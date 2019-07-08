package com.google.rpc;

import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;

public final class CodeProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private CodeProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0015google/rpc/code.proto\u0012\ngoogle.rpc*·\u0002\n\u0004Code\u0012\u0006\n\u0002OK\u0010\u0000\u0012\r\n\tCANCELLED\u0010\u0001\u0012\u000b\n\u0007UNKNOWN\u0010\u0002\u0012\u0014\n\u0010INVALID_ARGUMENT\u0010\u0003\u0012\u0015\n\u0011DEADLINE_EXCEEDED\u0010\u0004\u0012\r\n\tNOT_FOUND\u0010\u0005\u0012\u0012\n\u000eALREADY_EXISTS\u0010\u0006\u0012\u0015\n\u0011PERMISSION_DENIED\u0010\u0007\u0012\u0013\n\u000fUNAUTHENTICATED\u0010\u0010\u0012\u0016\n\u0012RESOURCE_EXHAUSTED\u0010\b\u0012\u0017\n\u0013FAILED_PRECONDITION\u0010\t\u0012\u000b\n\u0007ABORTED\u0010\n\u0012\u0010\n\fOUT_OF_RANGE\u0010\u000b\u0012\u0011\n\rUNIMPLEMENTED\u0010\f\u0012\f\n\bINTERNAL\u0010\r\u0012\u000f\n\u000bUNAVAILABLE\u0010\u000e\u0012\r\n\tDATA_LOSS\u0010\u000fBX\n\u000ecom.google.rpcB\tCodeProtoP\u0001Z3google.golang.org/genproto/googleapis/rpc/code;code¢\u0002\u0003RPCb\u0006proto3"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                CodeProto.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
