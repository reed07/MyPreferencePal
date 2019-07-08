package com.google.rpc;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class ErrorDetailsProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_rpc_BadRequest_FieldViolation_descriptor = ((Descriptor) internal_static_google_rpc_BadRequest_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_BadRequest_FieldViolation_descriptor, new String[]{"Field", "Description"});
    static final Descriptor internal_static_google_rpc_BadRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    static final FieldAccessorTable internal_static_google_rpc_BadRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_BadRequest_descriptor, new String[]{"FieldViolations"});
    static final Descriptor internal_static_google_rpc_DebugInfo_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_rpc_DebugInfo_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_DebugInfo_descriptor, new String[]{"StackEntries", "Detail"});
    static final Descriptor internal_static_google_rpc_Help_Link_descriptor = ((Descriptor) internal_static_google_rpc_Help_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_rpc_Help_Link_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_Help_Link_descriptor, new String[]{"Description", "Url"});
    static final Descriptor internal_static_google_rpc_Help_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(7));
    static final FieldAccessorTable internal_static_google_rpc_Help_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_Help_descriptor, new String[]{"Links"});
    static final Descriptor internal_static_google_rpc_LocalizedMessage_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(8));
    static final FieldAccessorTable internal_static_google_rpc_LocalizedMessage_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_LocalizedMessage_descriptor, new String[]{"Locale", "Message"});
    static final Descriptor internal_static_google_rpc_PreconditionFailure_Violation_descriptor = ((Descriptor) internal_static_google_rpc_PreconditionFailure_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_PreconditionFailure_Violation_descriptor, new String[]{"Type", "Subject", "Description"});
    static final Descriptor internal_static_google_rpc_PreconditionFailure_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    static final FieldAccessorTable internal_static_google_rpc_PreconditionFailure_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_PreconditionFailure_descriptor, new String[]{"Violations"});
    static final Descriptor internal_static_google_rpc_QuotaFailure_Violation_descriptor = ((Descriptor) internal_static_google_rpc_QuotaFailure_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_rpc_QuotaFailure_Violation_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_QuotaFailure_Violation_descriptor, new String[]{"Subject", "Description"});
    static final Descriptor internal_static_google_rpc_QuotaFailure_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_rpc_QuotaFailure_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_QuotaFailure_descriptor, new String[]{"Violations"});
    static final Descriptor internal_static_google_rpc_RequestInfo_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    static final FieldAccessorTable internal_static_google_rpc_RequestInfo_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_RequestInfo_descriptor, new String[]{"RequestId", "ServingData"});
    static final Descriptor internal_static_google_rpc_ResourceInfo_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(6));
    static final FieldAccessorTable internal_static_google_rpc_ResourceInfo_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_ResourceInfo_descriptor, new String[]{"ResourceType", "ResourceName", "Owner", "Description"});
    static final Descriptor internal_static_google_rpc_RetryInfo_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_rpc_RetryInfo_fieldAccessorTable = new FieldAccessorTable(internal_static_google_rpc_RetryInfo_descriptor, new String[]{"RetryDelay"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ErrorDetailsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {DurationProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/rpc/error_details.proto\u0012\ngoogle.rpc\u001a\u001egoogle/protobuf/duration.proto\";\n\tRetryInfo\u0012.\n\u000bretry_delay\u0018\u0001 \u0001(\u000b2\u0019.google.protobuf.Duration\"2\n\tDebugInfo\u0012\u0015\n\rstack_entries\u0018\u0001 \u0003(\t\u0012\u000e\n\u0006detail\u0018\u0002 \u0001(\t\"y\n\fQuotaFailure\u00126\n\nviolations\u0018\u0001 \u0003(\u000b2\".google.rpc.QuotaFailure.Violation\u001a1\n\tViolation\u0012\u000f\n\u0007subject\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\"\u0001\n\u0013PreconditionFailure\u0012=\n\nviolations\u0018\u0001 \u0003(\u000b2).google.rpc.PreconditionFailure.Violation\u001a?\n\tViolation\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007subject\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\"\u0001\n\nBadRequest\u0012?\n\u0010field_violations\u0018\u0001 \u0003(\u000b2%.google.rpc.BadRequest.FieldViolation\u001a4\n\u000eFieldViolation\u0012\r\n\u0005field\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\"7\n\u000bRequestInfo\u0012\u0012\n\nrequest_id\u0018\u0001 \u0001(\t\u0012\u0014\n\fserving_data\u0018\u0002 \u0001(\t\"`\n\fResourceInfo\u0012\u0015\n\rresource_type\u0018\u0001 \u0001(\t\u0012\u0015\n\rresource_name\u0018\u0002 \u0001(\t\u0012\r\n\u0005owner\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0004 \u0001(\t\"V\n\u0004Help\u0012$\n\u0005links\u0018\u0001 \u0003(\u000b2\u0015.google.rpc.Help.Link\u001a(\n\u0004Link\u0012\u0013\n\u000bdescription\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003url\u0018\u0002 \u0001(\t\"3\n\u0010LocalizedMessage\u0012\u000e\n\u0006locale\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\tBl\n\u000ecom.google.rpcB\u0011ErrorDetailsProtoP\u0001Z?google.golang.org/genproto/googleapis/rpc/errdetails;errdetails¢\u0002\u0003RPCb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ErrorDetailsProto.descriptor = fileDescriptor;
                return null;
            }
        });
        DurationProto.getDescriptor();
    }
}
