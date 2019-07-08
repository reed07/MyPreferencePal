package com.google.api;

import com.google.common.net.HttpHeaders;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.TimestampProto;

public final class DistributionProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor = ((Descriptor) internal_static_google_api_Distribution_BucketOptions_descriptor.getNestedTypes().get(2));
    static final FieldAccessorTable internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor, new String[]{"Bounds"});
    static final Descriptor internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor = ((Descriptor) internal_static_google_api_Distribution_BucketOptions_descriptor.getNestedTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor, new String[]{"NumFiniteBuckets", "GrowthFactor", "Scale"});
    static final Descriptor internal_static_google_api_Distribution_BucketOptions_Linear_descriptor = ((Descriptor) internal_static_google_api_Distribution_BucketOptions_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Distribution_BucketOptions_Linear_descriptor, new String[]{"NumFiniteBuckets", "Width", "Offset"});
    static final Descriptor internal_static_google_api_Distribution_BucketOptions_descriptor = ((Descriptor) internal_static_google_api_Distribution_descriptor.getNestedTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Distribution_BucketOptions_descriptor, new String[]{"LinearBuckets", "ExponentialBuckets", "ExplicitBuckets", "Options"});
    static final Descriptor internal_static_google_api_Distribution_Range_descriptor = ((Descriptor) internal_static_google_api_Distribution_descriptor.getNestedTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Distribution_Range_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Distribution_Range_descriptor, new String[]{"Min", "Max"});
    static final Descriptor internal_static_google_api_Distribution_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Distribution_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Distribution_descriptor, new String[]{"Count", "Mean", "SumOfSquaredDeviation", HttpHeaders.RANGE, "BucketOptions", "BucketCounts"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DistributionProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor(), AnyProto.getDescriptor(), TimestampProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001dgoogle/api/distribution.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\u001a\u0019google/protobuf/any.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"®\u0005\n\fDistribution\u0012\r\n\u0005count\u0018\u0001 \u0001(\u0003\u0012\f\n\u0004mean\u0018\u0002 \u0001(\u0001\u0012 \n\u0018sum_of_squared_deviation\u0018\u0003 \u0001(\u0001\u0012-\n\u0005range\u0018\u0004 \u0001(\u000b2\u001e.google.api.Distribution.Range\u0012>\n\u000ebucket_options\u0018\u0006 \u0001(\u000b2&.google.api.Distribution.BucketOptions\u0012\u0015\n\rbucket_counts\u0018\u0007 \u0003(\u0003\u001a!\n\u0005Range\u0012\u000b\n\u0003min\u0018\u0001 \u0001(\u0001\u0012\u000b\n\u0003max\u0018\u0002 \u0001(\u0001\u001aµ\u0003\n\rBucketOptions\u0012G\n\u000elinear_buckets\u0018\u0001 \u0001(\u000b2-.google.api.Distribution.BucketOptions.LinearH\u0000\u0012Q\n\u0013exponential_buckets\u0018\u0002 \u0001(\u000b22.google.api.Distribution.BucketOptions.ExponentialH\u0000\u0012K\n\u0010explicit_buckets\u0018\u0003 \u0001(\u000b2/.google.api.Distribution.BucketOptions.ExplicitH\u0000\u001aC\n\u0006Linear\u0012\u001a\n\u0012num_finite_buckets\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005width\u0018\u0002 \u0001(\u0001\u0012\u000e\n\u0006offset\u0018\u0003 \u0001(\u0001\u001aO\n\u000bExponential\u0012\u001a\n\u0012num_finite_buckets\u0018\u0001 \u0001(\u0005\u0012\u0015\n\rgrowth_factor\u0018\u0002 \u0001(\u0001\u0012\r\n\u0005scale\u0018\u0003 \u0001(\u0001\u001a\u001a\n\bExplicit\u0012\u000e\n\u0006bounds\u0018\u0001 \u0003(\u0001B\t\n\u0007optionsBj\n\u000ecom.google.apiB\u0011DistributionProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/api/distribution;distributionb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                DistributionProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
        AnyProto.getDescriptor();
        TimestampProto.getDescriptor();
    }
}
