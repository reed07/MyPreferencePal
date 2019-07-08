package com.google.longrunning;

import com.google.api.AnnotationsProto;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.rpc.StatusProto;

public final class OperationsProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_longrunning_CancelOperationRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    static final FieldAccessorTable internal_static_google_longrunning_CancelOperationRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_google_longrunning_CancelOperationRequest_descriptor, new String[]{"Name"});
    static final Descriptor internal_static_google_longrunning_DeleteOperationRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    static final FieldAccessorTable internal_static_google_longrunning_DeleteOperationRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_google_longrunning_DeleteOperationRequest_descriptor, new String[]{"Name"});
    static final Descriptor internal_static_google_longrunning_GetOperationRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_longrunning_GetOperationRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_google_longrunning_GetOperationRequest_descriptor, new String[]{"Name"});
    static final Descriptor internal_static_google_longrunning_ListOperationsRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_longrunning_ListOperationsRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_google_longrunning_ListOperationsRequest_descriptor, new String[]{"Name", "Filter", "PageSize", "PageToken"});
    static final Descriptor internal_static_google_longrunning_ListOperationsResponse_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    static final FieldAccessorTable internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable = new FieldAccessorTable(internal_static_google_longrunning_ListOperationsResponse_descriptor, new String[]{"Operations", "NextPageToken"});
    static final Descriptor internal_static_google_longrunning_Operation_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_longrunning_Operation_fieldAccessorTable = new FieldAccessorTable(internal_static_google_longrunning_Operation_descriptor, new String[]{"Name", "Metadata", "Done", "Error", "Response", "Result"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private OperationsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor(), AnyProto.getDescriptor(), EmptyProto.getDescriptor(), StatusProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n#google/longrunning/operations.proto\u0012\u0012google.longrunning\u001a\u001cgoogle/api/annotations.proto\u001a\u0019google/protobuf/any.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a\u0017google/rpc/status.proto\"¨\u0001\n\tOperation\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012&\n\bmetadata\u0018\u0002 \u0001(\u000b2\u0014.google.protobuf.Any\u0012\f\n\u0004done\u0018\u0003 \u0001(\b\u0012#\n\u0005error\u0018\u0004 \u0001(\u000b2\u0012.google.rpc.StatusH\u0000\u0012(\n\bresponse\u0018\u0005 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000B\b\n\u0006result\"#\n\u0013GetOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\\\n\u0015ListOperationsRequest\u0012\f\n\u0004name\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"d\n\u0016ListOperationsResponse\u00121\n\noperations\u0018\u0001 \u0003(\u000b2\u001d.google.longrunning.Operation\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"&\n\u0016CancelOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"&\n\u0016DeleteOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t2\u0004\n\nOperations\u0012\u0001\n\u000eListOperations\u0012).google.longrunning.ListOperationsRequest\u001a*.google.longrunning.ListOperationsResponse\"\u001dÓä\u0002\u0017\u0012\u0015/v1/{name=operations}\u0012x\n\fGetOperation\u0012'.google.longrunning.GetOperationRequest\u001a\u001d.google.longrunning.Operation\" Óä\u0002\u001a\u0012\u0018/v1/{name=operations/**}\u0012w\n\u000fDeleteOperation\u0012*.google.longrunning.DeleteOperationRequest\u001a\u0016.google.protobuf.Empty\" Óä\u0002\u001a*\u0018/v1/{name=operations/**}\u0012\u0001\n\u000fCancelOperation\u0012*.google.longrunning.CancelOperationRequest\u001a\u0016.google.protobuf.Empty\"*Óä\u0002$\"\u001f/v1/{name=operations/**}:cancel:\u0001*B\u0001\n\u0016com.google.longrunningB\u000fOperationsProtoP\u0001Z=google.golang.org/genproto/googleapis/longrunning;longrunningª\u0002\u0012Google.LongRunningÊ\u0002\u0012Google\\LongRunningb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                OperationsProto.descriptor = fileDescriptor;
                return null;
            }
        });
        ExtensionRegistry newInstance = ExtensionRegistry.newInstance();
        newInstance.add(AnnotationsProto.http);
        FileDescriptor.internalUpdateFileDescriptor(descriptor, newInstance);
        AnnotationsProto.getDescriptor();
        AnyProto.getDescriptor();
        EmptyProto.getDescriptor();
        StatusProto.getDescriptor();
    }
}
