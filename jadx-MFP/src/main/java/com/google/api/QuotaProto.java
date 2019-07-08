package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class QuotaProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_MetricRule_MetricCostsEntry_descriptor = ((Descriptor) internal_static_google_api_MetricRule_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_MetricRule_MetricCostsEntry_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MetricRule_MetricCostsEntry_descriptor, new String[]{"Key", "Value"});
    static final Descriptor internal_static_google_api_MetricRule_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_MetricRule_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_MetricRule_descriptor, new String[]{"Selector", "MetricCosts"});
    static final Descriptor internal_static_google_api_QuotaLimit_ValuesEntry_descriptor = ((Descriptor) internal_static_google_api_QuotaLimit_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_QuotaLimit_ValuesEntry_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_QuotaLimit_ValuesEntry_descriptor, new String[]{"Key", "Value"});
    static final Descriptor internal_static_google_api_QuotaLimit_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_api_QuotaLimit_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_QuotaLimit_descriptor, new String[]{"Name", "Description", "DefaultLimit", "MaxLimit", "FreeTier", "Duration", "Metric", "Unit", "Values", "DisplayName"});
    static final Descriptor internal_static_google_api_Quota_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Quota_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Quota_descriptor, new String[]{"Limits", "MetricRules"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private QuotaProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/api/quota.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\"]\n\u0005Quota\u0012&\n\u0006limits\u0018\u0003 \u0003(\u000b2\u0016.google.api.QuotaLimit\u0012,\n\fmetric_rules\u0018\u0004 \u0003(\u000b2\u0016.google.api.MetricRule\"\u0001\n\nMetricRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012=\n\fmetric_costs\u0018\u0002 \u0003(\u000b2'.google.api.MetricRule.MetricCostsEntry\u001a2\n\u0010MetricCostsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0003:\u00028\u0001\"\u0002\n\nQuotaLimit\u0012\f\n\u0004name\u0018\u0006 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_limit\u0018\u0003 \u0001(\u0003\u0012\u0011\n\tmax_limit\u0018\u0004 \u0001(\u0003\u0012\u0011\n\tfree_tier\u0018\u0007 \u0001(\u0003\u0012\u0010\n\bduration\u0018\u0005 \u0001(\t\u0012\u000e\n\u0006metric\u0018\b \u0001(\t\u0012\f\n\u0004unit\u0018\t \u0001(\t\u00122\n\u0006values\u0018\n \u0003(\u000b2\".google.api.QuotaLimit.ValuesEntry\u0012\u0014\n\fdisplay_name\u0018\f \u0001(\t\u001a-\n\u000bValuesEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0003:\u00028\u0001Bl\n\u000ecom.google.apiB\nQuotaProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                QuotaProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
    }
}
