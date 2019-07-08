package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Distribution extends GeneratedMessageV3 implements DistributionOrBuilder {
    public static final int BUCKET_COUNTS_FIELD_NUMBER = 7;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 6;
    public static final int COUNT_FIELD_NUMBER = 1;
    private static final Distribution DEFAULT_INSTANCE = new Distribution();
    public static final int MEAN_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<Distribution> PARSER = new AbstractParser<Distribution>() {
        public Distribution parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Distribution(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RANGE_FIELD_NUMBER = 4;
    public static final int SUM_OF_SQUARED_DEVIATION_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private int bucketCountsMemoizedSerializedSize;
    /* access modifiers changed from: private */
    public List<Long> bucketCounts_;
    /* access modifiers changed from: private */
    public BucketOptions bucketOptions_;
    /* access modifiers changed from: private */
    public long count_;
    /* access modifiers changed from: private */
    public double mean_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Range range_;
    /* access modifiers changed from: private */
    public double sumOfSquaredDeviation_;

    public static final class BucketOptions extends GeneratedMessageV3 implements BucketOptionsOrBuilder {
        private static final BucketOptions DEFAULT_INSTANCE = new BucketOptions();
        public static final int EXPLICIT_BUCKETS_FIELD_NUMBER = 3;
        public static final int EXPONENTIAL_BUCKETS_FIELD_NUMBER = 2;
        public static final int LINEAR_BUCKETS_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Parser<BucketOptions> PARSER = new AbstractParser<BucketOptions>() {
            public BucketOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BucketOptions(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public int optionsCase_;
        /* access modifiers changed from: private */
        public Object options_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements BucketOptionsOrBuilder {
            private SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> explicitBucketsBuilder_;
            private SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> exponentialBucketsBuilder_;
            private SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> linearBucketsBuilder_;
            private int optionsCase_;
            private Object options_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
            }

            private Builder() {
                this.optionsCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.optionsCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                BucketOptions.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.optionsCase_ = 0;
                this.options_ = null;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
            }

            public BucketOptions getDefaultInstanceForType() {
                return BucketOptions.getDefaultInstance();
            }

            public BucketOptions build() {
                BucketOptions buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public BucketOptions buildPartial() {
                BucketOptions bucketOptions = new BucketOptions((com.google.protobuf.GeneratedMessageV3.Builder) this);
                if (this.optionsCase_ == 1) {
                    SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV3.build();
                    }
                }
                if (this.optionsCase_ == 2) {
                    SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> singleFieldBuilderV32 = this.exponentialBucketsBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV32.build();
                    }
                }
                if (this.optionsCase_ == 3) {
                    SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> singleFieldBuilderV33 = this.explicitBucketsBuilder_;
                    if (singleFieldBuilderV33 == null) {
                        bucketOptions.options_ = this.options_;
                    } else {
                        bucketOptions.options_ = singleFieldBuilderV33.build();
                    }
                }
                bucketOptions.optionsCase_ = this.optionsCase_;
                onBuilt();
                return bucketOptions;
            }

            public Builder clone() {
                return (Builder) super.clone();
            }

            public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            public Builder clearField(FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            public Builder clearOneof(OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof BucketOptions) {
                    return mergeFrom((BucketOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BucketOptions bucketOptions) {
                if (bucketOptions == BucketOptions.getDefaultInstance()) {
                    return this;
                }
                switch (bucketOptions.getOptionsCase()) {
                    case LINEAR_BUCKETS:
                        mergeLinearBuckets(bucketOptions.getLinearBuckets());
                        break;
                    case EXPONENTIAL_BUCKETS:
                        mergeExponentialBuckets(bucketOptions.getExponentialBuckets());
                        break;
                    case EXPLICIT_BUCKETS:
                        mergeExplicitBuckets(bucketOptions.getExplicitBuckets());
                        break;
                }
                mergeUnknownFields(bucketOptions.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.api.Distribution.BucketOptions.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.api.Distribution$BucketOptions r3 = (com.google.api.Distribution.BucketOptions) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    if (r3 == 0) goto L_0x0010
                    r2.mergeFrom(r3)
                L_0x0010:
                    return r2
                L_0x0011:
                    r3 = move-exception
                    goto L_0x0021
                L_0x0013:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                    com.google.api.Distribution$BucketOptions r4 = (com.google.api.Distribution.BucketOptions) r4     // Catch:{ all -> 0x0011 }
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                    throw r3     // Catch:{ all -> 0x001f }
                L_0x001f:
                    r3 = move-exception
                    r0 = r4
                L_0x0021:
                    if (r0 == 0) goto L_0x0026
                    r2.mergeFrom(r0)
                L_0x0026:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Builder");
            }

            public OptionsCase getOptionsCase() {
                return OptionsCase.forNumber(this.optionsCase_);
            }

            public Builder clearOptions() {
                this.optionsCase_ = 0;
                this.options_ = null;
                onChanged();
                return this;
            }

            public boolean hasLinearBuckets() {
                return this.optionsCase_ == 1;
            }

            public Linear getLinearBuckets() {
                SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 1) {
                        return (Linear) this.options_;
                    }
                    return Linear.getDefaultInstance();
                } else if (this.optionsCase_ == 1) {
                    return (Linear) singleFieldBuilderV3.getMessage();
                } else {
                    return Linear.getDefaultInstance();
                }
            }

            public Builder setLinearBuckets(Linear linear) {
                SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(linear);
                } else if (linear != null) {
                    this.options_ = linear;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder setLinearBuckets(Builder builder) {
                SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder mergeLinearBuckets(Linear linear) {
                SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ != 1 || this.options_ == Linear.getDefaultInstance()) {
                        this.options_ = linear;
                    } else {
                        this.options_ = Linear.newBuilder((Linear) this.options_).mergeFrom(linear).buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 1) {
                        singleFieldBuilderV3.mergeFrom(linear);
                    }
                    this.linearBucketsBuilder_.setMessage(linear);
                }
                this.optionsCase_ = 1;
                return this;
            }

            public Builder clearLinearBuckets() {
                if (this.linearBucketsBuilder_ != null) {
                    if (this.optionsCase_ == 1) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.linearBucketsBuilder_.clear();
                } else if (this.optionsCase_ == 1) {
                    this.optionsCase_ = 0;
                    this.options_ = null;
                    onChanged();
                }
                return this;
            }

            public Builder getLinearBucketsBuilder() {
                return (Builder) getLinearBucketsFieldBuilder().getBuilder();
            }

            public LinearOrBuilder getLinearBucketsOrBuilder() {
                if (this.optionsCase_ == 1) {
                    SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> singleFieldBuilderV3 = this.linearBucketsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (LinearOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                }
                if (this.optionsCase_ == 1) {
                    return (Linear) this.options_;
                }
                return Linear.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Linear, Builder, LinearOrBuilder> getLinearBucketsFieldBuilder() {
                if (this.linearBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 1) {
                        this.options_ = Linear.getDefaultInstance();
                    }
                    this.linearBucketsBuilder_ = new SingleFieldBuilderV3<>((Linear) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 1;
                onChanged();
                return this.linearBucketsBuilder_;
            }

            public boolean hasExponentialBuckets() {
                return this.optionsCase_ == 2;
            }

            public Exponential getExponentialBuckets() {
                SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 2) {
                        return (Exponential) this.options_;
                    }
                    return Exponential.getDefaultInstance();
                } else if (this.optionsCase_ == 2) {
                    return (Exponential) singleFieldBuilderV3.getMessage();
                } else {
                    return Exponential.getDefaultInstance();
                }
            }

            public Builder setExponentialBuckets(Exponential exponential) {
                SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(exponential);
                } else if (exponential != null) {
                    this.options_ = exponential;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder setExponentialBuckets(Builder builder) {
                SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder mergeExponentialBuckets(Exponential exponential) {
                SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ != 2 || this.options_ == Exponential.getDefaultInstance()) {
                        this.options_ = exponential;
                    } else {
                        this.options_ = Exponential.newBuilder((Exponential) this.options_).mergeFrom(exponential).buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 2) {
                        singleFieldBuilderV3.mergeFrom(exponential);
                    }
                    this.exponentialBucketsBuilder_.setMessage(exponential);
                }
                this.optionsCase_ = 2;
                return this;
            }

            public Builder clearExponentialBuckets() {
                if (this.exponentialBucketsBuilder_ != null) {
                    if (this.optionsCase_ == 2) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.exponentialBucketsBuilder_.clear();
                } else if (this.optionsCase_ == 2) {
                    this.optionsCase_ = 0;
                    this.options_ = null;
                    onChanged();
                }
                return this;
            }

            public Builder getExponentialBucketsBuilder() {
                return (Builder) getExponentialBucketsFieldBuilder().getBuilder();
            }

            public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
                if (this.optionsCase_ == 2) {
                    SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> singleFieldBuilderV3 = this.exponentialBucketsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (ExponentialOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                }
                if (this.optionsCase_ == 2) {
                    return (Exponential) this.options_;
                }
                return Exponential.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Exponential, Builder, ExponentialOrBuilder> getExponentialBucketsFieldBuilder() {
                if (this.exponentialBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 2) {
                        this.options_ = Exponential.getDefaultInstance();
                    }
                    this.exponentialBucketsBuilder_ = new SingleFieldBuilderV3<>((Exponential) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 2;
                onChanged();
                return this.exponentialBucketsBuilder_;
            }

            public boolean hasExplicitBuckets() {
                return this.optionsCase_ == 3;
            }

            public Explicit getExplicitBuckets() {
                SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ == 3) {
                        return (Explicit) this.options_;
                    }
                    return Explicit.getDefaultInstance();
                } else if (this.optionsCase_ == 3) {
                    return (Explicit) singleFieldBuilderV3.getMessage();
                } else {
                    return Explicit.getDefaultInstance();
                }
            }

            public Builder setExplicitBuckets(Explicit explicit) {
                SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(explicit);
                } else if (explicit != null) {
                    this.options_ = explicit;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder setExplicitBuckets(Builder builder) {
                SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder mergeExplicitBuckets(Explicit explicit) {
                SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.optionsCase_ != 3 || this.options_ == Explicit.getDefaultInstance()) {
                        this.options_ = explicit;
                    } else {
                        this.options_ = Explicit.newBuilder((Explicit) this.options_).mergeFrom(explicit).buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.optionsCase_ == 3) {
                        singleFieldBuilderV3.mergeFrom(explicit);
                    }
                    this.explicitBucketsBuilder_.setMessage(explicit);
                }
                this.optionsCase_ = 3;
                return this;
            }

            public Builder clearExplicitBuckets() {
                if (this.explicitBucketsBuilder_ != null) {
                    if (this.optionsCase_ == 3) {
                        this.optionsCase_ = 0;
                        this.options_ = null;
                    }
                    this.explicitBucketsBuilder_.clear();
                } else if (this.optionsCase_ == 3) {
                    this.optionsCase_ = 0;
                    this.options_ = null;
                    onChanged();
                }
                return this;
            }

            public Builder getExplicitBucketsBuilder() {
                return (Builder) getExplicitBucketsFieldBuilder().getBuilder();
            }

            public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
                if (this.optionsCase_ == 3) {
                    SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> singleFieldBuilderV3 = this.explicitBucketsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (ExplicitOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                }
                if (this.optionsCase_ == 3) {
                    return (Explicit) this.options_;
                }
                return Explicit.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Explicit, Builder, ExplicitOrBuilder> getExplicitBucketsFieldBuilder() {
                if (this.explicitBucketsBuilder_ == null) {
                    if (this.optionsCase_ != 3) {
                        this.options_ = Explicit.getDefaultInstance();
                    }
                    this.explicitBucketsBuilder_ = new SingleFieldBuilderV3<>((Explicit) this.options_, getParentForChildren(), isClean());
                    this.options_ = null;
                }
                this.optionsCase_ = 3;
                onChanged();
                return this.explicitBucketsBuilder_;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static final class Explicit extends GeneratedMessageV3 implements ExplicitOrBuilder {
            public static final int BOUNDS_FIELD_NUMBER = 1;
            private static final Explicit DEFAULT_INSTANCE = new Explicit();
            /* access modifiers changed from: private */
            public static final Parser<Explicit> PARSER = new AbstractParser<Explicit>() {
                public Explicit parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Explicit(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private int boundsMemoizedSerializedSize;
            /* access modifiers changed from: private */
            public List<Double> bounds_;
            private byte memoizedIsInitialized;

            public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ExplicitOrBuilder {
                private int bitField0_;
                private List<Double> bounds_;

                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
                }

                /* access modifiers changed from: protected */
                public FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
                }

                private Builder() {
                    this.bounds_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    this.bounds_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    Explicit.alwaysUseFieldBuilders;
                }

                public Builder clear() {
                    super.clear();
                    this.bounds_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    return this;
                }

                public Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
                }

                public Explicit getDefaultInstanceForType() {
                    return Explicit.getDefaultInstance();
                }

                public Explicit build() {
                    Explicit buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Explicit buildPartial() {
                    Explicit explicit = new Explicit((com.google.protobuf.GeneratedMessageV3.Builder) this);
                    if ((this.bitField0_ & 1) == 1) {
                        this.bounds_ = Collections.unmodifiableList(this.bounds_);
                        this.bitField0_ &= -2;
                    }
                    explicit.bounds_ = this.bounds_;
                    onBuilt();
                    return explicit;
                }

                public Builder clone() {
                    return (Builder) super.clone();
                }

                public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                public Builder clearField(FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                public Builder clearOneof(OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Explicit) {
                        return mergeFrom((Explicit) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Explicit explicit) {
                    if (explicit == Explicit.getDefaultInstance()) {
                        return this;
                    }
                    if (!explicit.bounds_.isEmpty()) {
                        if (this.bounds_.isEmpty()) {
                            this.bounds_ = explicit.bounds_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureBoundsIsMutable();
                            this.bounds_.addAll(explicit.bounds_);
                        }
                        onChanged();
                    }
                    mergeUnknownFields(explicit.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.api.Distribution.BucketOptions.Explicit.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.Explicit.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        com.google.api.Distribution$BucketOptions$Explicit r3 = (com.google.api.Distribution.BucketOptions.Explicit) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        if (r3 == 0) goto L_0x0010
                        r2.mergeFrom(r3)
                    L_0x0010:
                        return r2
                    L_0x0011:
                        r3 = move-exception
                        goto L_0x0021
                    L_0x0013:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                        com.google.api.Distribution$BucketOptions$Explicit r4 = (com.google.api.Distribution.BucketOptions.Explicit) r4     // Catch:{ all -> 0x0011 }
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                        throw r3     // Catch:{ all -> 0x001f }
                    L_0x001f:
                        r3 = move-exception
                        r0 = r4
                    L_0x0021:
                        if (r0 == 0) goto L_0x0026
                        r2.mergeFrom(r0)
                    L_0x0026:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Explicit.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Explicit$Builder");
                }

                private void ensureBoundsIsMutable() {
                    if ((this.bitField0_ & 1) != 1) {
                        this.bounds_ = new ArrayList(this.bounds_);
                        this.bitField0_ |= 1;
                    }
                }

                public List<Double> getBoundsList() {
                    return Collections.unmodifiableList(this.bounds_);
                }

                public int getBoundsCount() {
                    return this.bounds_.size();
                }

                public double getBounds(int i) {
                    return ((Double) this.bounds_.get(i)).doubleValue();
                }

                public Builder setBounds(int i, double d) {
                    ensureBoundsIsMutable();
                    this.bounds_.set(i, Double.valueOf(d));
                    onChanged();
                    return this;
                }

                public Builder addBounds(double d) {
                    ensureBoundsIsMutable();
                    this.bounds_.add(Double.valueOf(d));
                    onChanged();
                    return this;
                }

                public Builder addAllBounds(Iterable<? extends Double> iterable) {
                    ensureBoundsIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.bounds_);
                    onChanged();
                    return this;
                }

                public Builder clearBounds() {
                    this.bounds_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
                }

                public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            private Explicit(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.boundsMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = -1;
            }

            private Explicit() {
                this.boundsMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = -1;
                this.bounds_ = Collections.emptyList();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Explicit(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    boolean z2 = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                switch (readTag) {
                                    case 9:
                                        if (!z2 || !true) {
                                            this.bounds_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        this.bounds_.add(Double.valueOf(codedInputStream.readDouble()));
                                        break;
                                    case 10:
                                        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                        if ((!z2 || !true) && codedInputStream.getBytesUntilLimit() > 0) {
                                            this.bounds_ = new ArrayList();
                                            z2 |= true;
                                        }
                                        while (codedInputStream.getBytesUntilLimit() > 0) {
                                            this.bounds_.add(Double.valueOf(codedInputStream.readDouble()));
                                        }
                                        codedInputStream.popLimit(pushLimit);
                                        break;
                                    default:
                                        if (parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            break;
                                        } else {
                                            z = true;
                                            break;
                                        }
                                }
                            } else {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        } catch (Throwable th) {
                            if (z2 && true) {
                                this.bounds_ = Collections.unmodifiableList(this.bounds_);
                            }
                            this.unknownFields = newBuilder.build();
                            makeExtensionsImmutable();
                            throw th;
                        }
                    }
                    if (z2 && true) {
                        this.bounds_ = Collections.unmodifiableList(this.bounds_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    return;
                }
                throw new NullPointerException();
            }

            public static final Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable.ensureFieldAccessorsInitialized(Explicit.class, Builder.class);
            }

            public List<Double> getBoundsList() {
                return this.bounds_;
            }

            public int getBoundsCount() {
                return this.bounds_.size();
            }

            public double getBounds(int i) {
                return ((Double) this.bounds_.get(i)).doubleValue();
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                getSerializedSize();
                if (getBoundsList().size() > 0) {
                    codedOutputStream.writeUInt32NoTag(10);
                    codedOutputStream.writeUInt32NoTag(this.boundsMemoizedSerializedSize);
                }
                for (int i = 0; i < this.bounds_.size(); i++) {
                    codedOutputStream.writeDoubleNoTag(((Double) this.bounds_.get(i)).doubleValue());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int size = getBoundsList().size() * 8;
                int i2 = size + 0;
                if (!getBoundsList().isEmpty()) {
                    i2 = i2 + 1 + CodedOutputStream.computeInt32SizeNoTag(size);
                }
                this.boundsMemoizedSerializedSize = size;
                int serializedSize = i2 + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                boolean z = true;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Explicit)) {
                    return super.equals(obj);
                }
                Explicit explicit = (Explicit) obj;
                if (!(getBoundsList().equals(explicit.getBoundsList())) || !this.unknownFields.equals(explicit.unknownFields)) {
                    z = false;
                }
                return z;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + getDescriptor().hashCode();
                if (getBoundsCount() > 0) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getBoundsList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteBuffer);
            }

            public static Explicit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Explicit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteString);
            }

            public static Explicit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Explicit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(bArr);
            }

            public static Explicit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Explicit) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Explicit parseFrom(InputStream inputStream) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Explicit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Explicit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Explicit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Explicit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Explicit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Explicit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Explicit explicit) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(explicit);
            }

            public Builder toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Explicit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Explicit> parser() {
                return PARSER;
            }

            public Parser<Explicit> getParserForType() {
                return PARSER;
            }

            public Explicit getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public interface ExplicitOrBuilder extends MessageOrBuilder {
            double getBounds(int i);

            int getBoundsCount();

            List<Double> getBoundsList();
        }

        public static final class Exponential extends GeneratedMessageV3 implements ExponentialOrBuilder {
            private static final Exponential DEFAULT_INSTANCE = new Exponential();
            public static final int GROWTH_FACTOR_FIELD_NUMBER = 2;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            /* access modifiers changed from: private */
            public static final Parser<Exponential> PARSER = new AbstractParser<Exponential>() {
                public Exponential parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Exponential(codedInputStream, extensionRegistryLite);
                }
            };
            public static final int SCALE_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            /* access modifiers changed from: private */
            public double growthFactor_;
            private byte memoizedIsInitialized;
            /* access modifiers changed from: private */
            public int numFiniteBuckets_;
            /* access modifiers changed from: private */
            public double scale_;

            public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ExponentialOrBuilder {
                private double growthFactor_;
                private int numFiniteBuckets_;
                private double scale_;

                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
                }

                /* access modifiers changed from: protected */
                public FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    Exponential.alwaysUseFieldBuilders;
                }

                public Builder clear() {
                    super.clear();
                    this.numFiniteBuckets_ = 0;
                    this.growthFactor_ = 0.0d;
                    this.scale_ = 0.0d;
                    return this;
                }

                public Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
                }

                public Exponential getDefaultInstanceForType() {
                    return Exponential.getDefaultInstance();
                }

                public Exponential build() {
                    Exponential buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Exponential buildPartial() {
                    Exponential exponential = new Exponential((com.google.protobuf.GeneratedMessageV3.Builder) this);
                    exponential.numFiniteBuckets_ = this.numFiniteBuckets_;
                    exponential.growthFactor_ = this.growthFactor_;
                    exponential.scale_ = this.scale_;
                    onBuilt();
                    return exponential;
                }

                public Builder clone() {
                    return (Builder) super.clone();
                }

                public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                public Builder clearField(FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                public Builder clearOneof(OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Exponential) {
                        return mergeFrom((Exponential) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Exponential exponential) {
                    if (exponential == Exponential.getDefaultInstance()) {
                        return this;
                    }
                    if (exponential.getNumFiniteBuckets() != 0) {
                        setNumFiniteBuckets(exponential.getNumFiniteBuckets());
                    }
                    if (exponential.getGrowthFactor() != 0.0d) {
                        setGrowthFactor(exponential.getGrowthFactor());
                    }
                    if (exponential.getScale() != 0.0d) {
                        setScale(exponential.getScale());
                    }
                    mergeUnknownFields(exponential.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.api.Distribution.BucketOptions.Exponential.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.Exponential.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        com.google.api.Distribution$BucketOptions$Exponential r3 = (com.google.api.Distribution.BucketOptions.Exponential) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        if (r3 == 0) goto L_0x0010
                        r2.mergeFrom(r3)
                    L_0x0010:
                        return r2
                    L_0x0011:
                        r3 = move-exception
                        goto L_0x0021
                    L_0x0013:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                        com.google.api.Distribution$BucketOptions$Exponential r4 = (com.google.api.Distribution.BucketOptions.Exponential) r4     // Catch:{ all -> 0x0011 }
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                        throw r3     // Catch:{ all -> 0x001f }
                    L_0x001f:
                        r3 = move-exception
                        r0 = r4
                    L_0x0021:
                        if (r0 == 0) goto L_0x0026
                        r2.mergeFrom(r0)
                    L_0x0026:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Exponential.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Exponential$Builder");
                }

                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int i) {
                    this.numFiniteBuckets_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    onChanged();
                    return this;
                }

                public double getGrowthFactor() {
                    return this.growthFactor_;
                }

                public Builder setGrowthFactor(double d) {
                    this.growthFactor_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearGrowthFactor() {
                    this.growthFactor_ = 0.0d;
                    onChanged();
                    return this;
                }

                public double getScale() {
                    return this.scale_;
                }

                public Builder setScale(double d) {
                    this.scale_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearScale() {
                    this.scale_ = 0.0d;
                    onChanged();
                    return this;
                }

                public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
                }

                public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            private Exponential(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = -1;
            }

            private Exponential() {
                this.memoizedIsInitialized = -1;
                this.numFiniteBuckets_ = 0;
                this.growthFactor_ = 0.0d;
                this.scale_ = 0.0d;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Exponential(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0) {
                                z = true;
                            } else if (readTag == 8) {
                                this.numFiniteBuckets_ = codedInputStream.readInt32();
                            } else if (readTag == 17) {
                                this.growthFactor_ = codedInputStream.readDouble();
                            } else if (readTag == 25) {
                                this.scale_ = codedInputStream.readDouble();
                            } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        } catch (Throwable th) {
                            this.unknownFields = newBuilder.build();
                            makeExtensionsImmutable();
                            throw th;
                        }
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    return;
                }
                throw new NullPointerException();
            }

            public static final Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable.ensureFieldAccessorsInitialized(Exponential.class, Builder.class);
            }

            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            public double getGrowthFactor() {
                return this.growthFactor_;
            }

            public double getScale() {
                return this.scale_;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                int i = this.numFiniteBuckets_;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                double d = this.growthFactor_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.scale_;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                int i3 = this.numFiniteBuckets_;
                if (i3 != 0) {
                    i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
                }
                double d = this.growthFactor_;
                if (d != 0.0d) {
                    i2 += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.scale_;
                if (d2 != 0.0d) {
                    i2 += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = i2 + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                boolean z = true;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Exponential)) {
                    return super.equals(obj);
                }
                Exponential exponential = (Exponential) obj;
                if (!(((getNumFiniteBuckets() == exponential.getNumFiniteBuckets()) && (Double.doubleToLongBits(getGrowthFactor()) > Double.doubleToLongBits(exponential.getGrowthFactor()) ? 1 : (Double.doubleToLongBits(getGrowthFactor()) == Double.doubleToLongBits(exponential.getGrowthFactor()) ? 0 : -1)) == 0) && Double.doubleToLongBits(getScale()) == Double.doubleToLongBits(exponential.getScale())) || !this.unknownFields.equals(exponential.unknownFields)) {
                    z = false;
                }
                return z;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getNumFiniteBuckets()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getGrowthFactor()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getScale()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteBuffer);
            }

            public static Exponential parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Exponential parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteString);
            }

            public static Exponential parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Exponential parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(bArr);
            }

            public static Exponential parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Exponential) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Exponential parseFrom(InputStream inputStream) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Exponential parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Exponential) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Exponential parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Exponential) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Exponential parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Exponential) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Exponential exponential) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(exponential);
            }

            public Builder toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Exponential getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Exponential> parser() {
                return PARSER;
            }

            public Parser<Exponential> getParserForType() {
                return PARSER;
            }

            public Exponential getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public interface ExponentialOrBuilder extends MessageOrBuilder {
            double getGrowthFactor();

            int getNumFiniteBuckets();

            double getScale();
        }

        public static final class Linear extends GeneratedMessageV3 implements LinearOrBuilder {
            private static final Linear DEFAULT_INSTANCE = new Linear();
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int OFFSET_FIELD_NUMBER = 3;
            /* access modifiers changed from: private */
            public static final Parser<Linear> PARSER = new AbstractParser<Linear>() {
                public Linear parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Linear(codedInputStream, extensionRegistryLite);
                }
            };
            public static final int WIDTH_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            /* access modifiers changed from: private */
            public int numFiniteBuckets_;
            /* access modifiers changed from: private */
            public double offset_;
            /* access modifiers changed from: private */
            public double width_;

            public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LinearOrBuilder {
                private int numFiniteBuckets_;
                private double offset_;
                private double width_;

                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptor getDescriptor() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
                }

                /* access modifiers changed from: protected */
                public FieldAccessorTable internalGetFieldAccessorTable() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    Linear.alwaysUseFieldBuilders;
                }

                public Builder clear() {
                    super.clear();
                    this.numFiniteBuckets_ = 0;
                    this.width_ = 0.0d;
                    this.offset_ = 0.0d;
                    return this;
                }

                public Descriptor getDescriptorForType() {
                    return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
                }

                public Linear getDefaultInstanceForType() {
                    return Linear.getDefaultInstance();
                }

                public Linear build() {
                    Linear buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Linear buildPartial() {
                    Linear linear = new Linear((com.google.protobuf.GeneratedMessageV3.Builder) this);
                    linear.numFiniteBuckets_ = this.numFiniteBuckets_;
                    linear.width_ = this.width_;
                    linear.offset_ = this.offset_;
                    onBuilt();
                    return linear;
                }

                public Builder clone() {
                    return (Builder) super.clone();
                }

                public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                public Builder clearField(FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                public Builder clearOneof(OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Linear) {
                        return mergeFrom((Linear) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Linear linear) {
                    if (linear == Linear.getDefaultInstance()) {
                        return this;
                    }
                    if (linear.getNumFiniteBuckets() != 0) {
                        setNumFiniteBuckets(linear.getNumFiniteBuckets());
                    }
                    if (linear.getWidth() != 0.0d) {
                        setWidth(linear.getWidth());
                    }
                    if (linear.getOffset() != 0.0d) {
                        setOffset(linear.getOffset());
                    }
                    mergeUnknownFields(linear.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.api.Distribution.BucketOptions.Linear.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = com.google.api.Distribution.BucketOptions.Linear.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        com.google.api.Distribution$BucketOptions$Linear r3 = (com.google.api.Distribution.BucketOptions.Linear) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                        if (r3 == 0) goto L_0x0010
                        r2.mergeFrom(r3)
                    L_0x0010:
                        return r2
                    L_0x0011:
                        r3 = move-exception
                        goto L_0x0021
                    L_0x0013:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                        com.google.api.Distribution$BucketOptions$Linear r4 = (com.google.api.Distribution.BucketOptions.Linear) r4     // Catch:{ all -> 0x0011 }
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                        throw r3     // Catch:{ all -> 0x001f }
                    L_0x001f:
                        r3 = move-exception
                        r0 = r4
                    L_0x0021:
                        if (r0 == 0) goto L_0x0026
                        r2.mergeFrom(r0)
                    L_0x0026:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.Linear.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$BucketOptions$Linear$Builder");
                }

                public int getNumFiniteBuckets() {
                    return this.numFiniteBuckets_;
                }

                public Builder setNumFiniteBuckets(int i) {
                    this.numFiniteBuckets_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    this.numFiniteBuckets_ = 0;
                    onChanged();
                    return this;
                }

                public double getWidth() {
                    return this.width_;
                }

                public Builder setWidth(double d) {
                    this.width_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearWidth() {
                    this.width_ = 0.0d;
                    onChanged();
                    return this;
                }

                public double getOffset() {
                    return this.offset_;
                }

                public Builder setOffset(double d) {
                    this.offset_ = d;
                    onChanged();
                    return this;
                }

                public Builder clearOffset() {
                    this.offset_ = 0.0d;
                    onChanged();
                    return this;
                }

                public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
                }

                public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            private Linear(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = -1;
            }

            private Linear() {
                this.memoizedIsInitialized = -1;
                this.numFiniteBuckets_ = 0;
                this.width_ = 0.0d;
                this.offset_ = 0.0d;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Linear(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                if (extensionRegistryLite != null) {
                    com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0) {
                                z = true;
                            } else if (readTag == 8) {
                                this.numFiniteBuckets_ = codedInputStream.readInt32();
                            } else if (readTag == 17) {
                                this.width_ = codedInputStream.readDouble();
                            } else if (readTag == 25) {
                                this.offset_ = codedInputStream.readDouble();
                            } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        } catch (Throwable th) {
                            this.unknownFields = newBuilder.build();
                            makeExtensionsImmutable();
                            throw th;
                        }
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    return;
                }
                throw new NullPointerException();
            }

            public static final Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable.ensureFieldAccessorsInitialized(Linear.class, Builder.class);
            }

            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            public double getWidth() {
                return this.width_;
            }

            public double getOffset() {
                return this.offset_;
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                int i = this.numFiniteBuckets_;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                double d = this.width_;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.offset_;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                int i3 = this.numFiniteBuckets_;
                if (i3 != 0) {
                    i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
                }
                double d = this.width_;
                if (d != 0.0d) {
                    i2 += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.offset_;
                if (d2 != 0.0d) {
                    i2 += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = i2 + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                boolean z = true;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Linear)) {
                    return super.equals(obj);
                }
                Linear linear = (Linear) obj;
                if (!(((getNumFiniteBuckets() == linear.getNumFiniteBuckets()) && (Double.doubleToLongBits(getWidth()) > Double.doubleToLongBits(linear.getWidth()) ? 1 : (Double.doubleToLongBits(getWidth()) == Double.doubleToLongBits(linear.getWidth()) ? 0 : -1)) == 0) && Double.doubleToLongBits(getOffset()) == Double.doubleToLongBits(linear.getOffset())) || !this.unknownFields.equals(linear.unknownFields)) {
                    z = false;
                }
                return z;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getNumFiniteBuckets()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getWidth()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getOffset()))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            public static Linear parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteBuffer);
            }

            public static Linear parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Linear parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteString);
            }

            public static Linear parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Linear parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(bArr);
            }

            public static Linear parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Linear) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Linear parseFrom(InputStream inputStream) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Linear parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Linear) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Linear parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Linear) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Linear parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Linear) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Linear linear) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(linear);
            }

            public Builder toBuilder() {
                if (this == DEFAULT_INSTANCE) {
                    return new Builder();
                }
                return new Builder().mergeFrom(this);
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static Linear getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Linear> parser() {
                return PARSER;
            }

            public Parser<Linear> getParserForType() {
                return PARSER;
            }

            public Linear getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public interface LinearOrBuilder extends MessageOrBuilder {
            int getNumFiniteBuckets();

            double getOffset();

            double getWidth();
        }

        public enum OptionsCase implements EnumLite {
            LINEAR_BUCKETS(1),
            EXPONENTIAL_BUCKETS(2),
            EXPLICIT_BUCKETS(3),
            OPTIONS_NOT_SET(0);
            
            private final int value;

            private OptionsCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static OptionsCase valueOf(int i) {
                return forNumber(i);
            }

            public static OptionsCase forNumber(int i) {
                switch (i) {
                    case 0:
                        return OPTIONS_NOT_SET;
                    case 1:
                        return LINEAR_BUCKETS;
                    case 2:
                        return EXPONENTIAL_BUCKETS;
                    case 3:
                        return EXPLICIT_BUCKETS;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        private BucketOptions(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.optionsCase_ = 0;
            this.memoizedIsInitialized = -1;
        }

        private BucketOptions() {
            this.optionsCase_ = 0;
            this.memoizedIsInitialized = -1;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0 */
        /* JADX WARNING: type inference failed for: r5v1, types: [com.google.api.Distribution$BucketOptions$Linear$Builder] */
        /* JADX WARNING: type inference failed for: r5v2, types: [com.google.api.Distribution$BucketOptions$Linear$Builder] */
        /* JADX WARNING: type inference failed for: r5v3, types: [com.google.api.Distribution$BucketOptions$Exponential$Builder] */
        /* JADX WARNING: type inference failed for: r5v4, types: [com.google.api.Distribution$BucketOptions$Exponential$Builder] */
        /* JADX WARNING: type inference failed for: r5v5, types: [com.google.api.Distribution$BucketOptions$Explicit$Builder] */
        /* JADX WARNING: type inference failed for: r5v6, types: [com.google.api.Distribution$BucketOptions$Explicit$Builder] */
        /* JADX WARNING: type inference failed for: r5v7 */
        /* JADX WARNING: type inference failed for: r5v8 */
        /* JADX WARNING: type inference failed for: r5v9 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r5v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.api.Distribution$BucketOptions$Exponential$Builder, com.google.api.Distribution$BucketOptions$Linear$Builder, com.google.api.Distribution$BucketOptions$Explicit$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.google.api.Distribution$BucketOptions$Linear$Builder, com.google.api.Distribution$BucketOptions$Exponential$Builder, com.google.api.Distribution$BucketOptions$Explicit$Builder]
  mth insns count: 84
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 4 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private BucketOptions(com.google.protobuf.CodedInputStream r7, com.google.protobuf.ExtensionRegistryLite r8) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r6 = this;
                r6.<init>()
                if (r8 == 0) goto L_0x00cd
                com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
                r1 = 0
            L_0x000a:
                if (r1 != 0) goto L_0x00c3
                int r2 = r7.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r3 = 1
                if (r2 == 0) goto L_0x00a3
                r4 = 10
                r5 = 0
                if (r2 == r4) goto L_0x007a
                r4 = 18
                if (r2 == r4) goto L_0x0051
                r4 = 26
                if (r2 == r4) goto L_0x0028
                boolean r2 = r6.parseUnknownFieldProto3(r7, r0, r8, r2)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                if (r2 != 0) goto L_0x000a
                r1 = 1
                goto L_0x000a
            L_0x0028:
                int r2 = r6.optionsCase_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r3 = 3
                if (r2 != r3) goto L_0x0035
                java.lang.Object r2 = r6.options_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Explicit r2 = (com.google.api.Distribution.BucketOptions.Explicit) r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Explicit$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
            L_0x0035:
                com.google.protobuf.Parser r2 = com.google.api.Distribution.BucketOptions.Explicit.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r6.options_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                if (r5 == 0) goto L_0x004e
                java.lang.Object r2 = r6.options_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Explicit r2 = (com.google.api.Distribution.BucketOptions.Explicit) r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Explicit r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r6.options_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
            L_0x004e:
                r6.optionsCase_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                goto L_0x000a
            L_0x0051:
                int r2 = r6.optionsCase_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r3 = 2
                if (r2 != r3) goto L_0x005e
                java.lang.Object r2 = r6.options_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Exponential r2 = (com.google.api.Distribution.BucketOptions.Exponential) r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Exponential$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
            L_0x005e:
                com.google.protobuf.Parser r2 = com.google.api.Distribution.BucketOptions.Exponential.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r6.options_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                if (r5 == 0) goto L_0x0077
                java.lang.Object r2 = r6.options_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Exponential r2 = (com.google.api.Distribution.BucketOptions.Exponential) r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Exponential r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r6.options_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
            L_0x0077:
                r6.optionsCase_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                goto L_0x000a
            L_0x007a:
                int r2 = r6.optionsCase_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                if (r2 != r3) goto L_0x0086
                java.lang.Object r2 = r6.options_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Linear r2 = (com.google.api.Distribution.BucketOptions.Linear) r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Linear$Builder r5 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
            L_0x0086:
                com.google.protobuf.Parser r2 = com.google.api.Distribution.BucketOptions.Linear.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.protobuf.MessageLite r2 = r7.readMessage(r2, r8)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r6.options_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                if (r5 == 0) goto L_0x009f
                java.lang.Object r2 = r6.options_     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Linear r2 = (com.google.api.Distribution.BucketOptions.Linear) r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r5.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                com.google.api.Distribution$BucketOptions$Linear r2 = r5.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                r6.options_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
            L_0x009f:
                r6.optionsCase_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x00b3, IOException -> 0x00a8 }
                goto L_0x000a
            L_0x00a3:
                r1 = 1
                goto L_0x000a
            L_0x00a6:
                r7 = move-exception
                goto L_0x00b9
            L_0x00a8:
                r7 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r8 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00a6 }
                r8.<init>(r7)     // Catch:{ all -> 0x00a6 }
                com.google.protobuf.InvalidProtocolBufferException r7 = r8.setUnfinishedMessage(r6)     // Catch:{ all -> 0x00a6 }
                throw r7     // Catch:{ all -> 0x00a6 }
            L_0x00b3:
                r7 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r7 = r7.setUnfinishedMessage(r6)     // Catch:{ all -> 0x00a6 }
                throw r7     // Catch:{ all -> 0x00a6 }
            L_0x00b9:
                com.google.protobuf.UnknownFieldSet r8 = r0.build()
                r6.unknownFields = r8
                r6.makeExtensionsImmutable()
                throw r7
            L_0x00c3:
                com.google.protobuf.UnknownFieldSet r7 = r0.build()
                r6.unknownFields = r7
                r6.makeExtensionsImmutable()
                return
            L_0x00cd:
                java.lang.NullPointerException r7 = new java.lang.NullPointerException
                r7.<init>()
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.BucketOptions.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        public static final Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(BucketOptions.class, Builder.class);
        }

        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(this.optionsCase_);
        }

        public boolean hasLinearBuckets() {
            return this.optionsCase_ == 1;
        }

        public Linear getLinearBuckets() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        public LinearOrBuilder getLinearBucketsOrBuilder() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        public boolean hasExponentialBuckets() {
            return this.optionsCase_ == 2;
        }

        public Exponential getExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        public ExponentialOrBuilder getExponentialBucketsOrBuilder() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        public boolean hasExplicitBuckets() {
            return this.optionsCase_ == 3;
        }

        public Explicit getExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        public ExplicitOrBuilder getExplicitBucketsOrBuilder() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.optionsCase_ == 1) {
                codedOutputStream.writeMessage(1, (Linear) this.options_);
            }
            if (this.optionsCase_ == 2) {
                codedOutputStream.writeMessage(2, (Exponential) this.options_);
            }
            if (this.optionsCase_ == 3) {
                codedOutputStream.writeMessage(3, (Explicit) this.options_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.optionsCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, (Linear) this.options_);
            }
            if (this.optionsCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (Exponential) this.options_);
            }
            if (this.optionsCase_ == 3) {
                i2 += CodedOutputStream.computeMessageSize(3, (Explicit) this.options_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BucketOptions)) {
                return super.equals(obj);
            }
            BucketOptions bucketOptions = (BucketOptions) obj;
            boolean z2 = getOptionsCase().equals(bucketOptions.getOptionsCase());
            if (!z2) {
                return false;
            }
            switch (this.optionsCase_) {
                case 1:
                    if (z2 && getLinearBuckets().equals(bucketOptions.getLinearBuckets())) {
                        z2 = true;
                        break;
                    } else {
                        z2 = false;
                        break;
                    }
                    break;
                case 2:
                    if (z2 && getExponentialBuckets().equals(bucketOptions.getExponentialBuckets())) {
                        z2 = true;
                        break;
                    } else {
                        z2 = false;
                        break;
                    }
                    break;
                case 3:
                    if (z2 && getExplicitBuckets().equals(bucketOptions.getExplicitBuckets())) {
                        z2 = true;
                        break;
                    } else {
                        z2 = false;
                        break;
                    }
                    break;
            }
            if (!z2 || !this.unknownFields.equals(bucketOptions.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            switch (this.optionsCase_) {
                case 1:
                    hashCode = (((hashCode * 37) + 1) * 53) + getLinearBuckets().hashCode();
                    break;
                case 2:
                    hashCode = (((hashCode * 37) + 2) * 53) + getExponentialBuckets().hashCode();
                    break;
                case 3:
                    hashCode = (((hashCode * 37) + 3) * 53) + getExplicitBuckets().hashCode();
                    break;
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteBuffer);
        }

        public static BucketOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteString);
        }

        public static BucketOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(bArr);
        }

        public static BucketOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BucketOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(InputStream inputStream) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BucketOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BucketOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BucketOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BucketOptions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BucketOptions bucketOptions) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(bucketOptions);
        }

        public Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static BucketOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BucketOptions> parser() {
            return PARSER;
        }

        public Parser<BucketOptions> getParserForType() {
            return PARSER;
        }

        public BucketOptions getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface BucketOptionsOrBuilder extends MessageOrBuilder {
        Explicit getExplicitBuckets();

        ExplicitOrBuilder getExplicitBucketsOrBuilder();

        Exponential getExponentialBuckets();

        ExponentialOrBuilder getExponentialBucketsOrBuilder();

        Linear getLinearBuckets();

        LinearOrBuilder getLinearBucketsOrBuilder();

        OptionsCase getOptionsCase();

        boolean hasExplicitBuckets();

        boolean hasExponentialBuckets();

        boolean hasLinearBuckets();
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements DistributionOrBuilder {
        private int bitField0_;
        private List<Long> bucketCounts_;
        private SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> bucketOptionsBuilder_;
        private BucketOptions bucketOptions_;
        private long count_;
        private double mean_;
        private SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> rangeBuilder_;
        private Range range_;
        private double sumOfSquaredDeviation_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
        }

        private Builder() {
            this.range_ = null;
            this.bucketOptions_ = null;
            this.bucketCounts_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.range_ = null;
            this.bucketOptions_ = null;
            this.bucketCounts_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Distribution.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.count_ = 0;
            this.mean_ = 0.0d;
            this.sumOfSquaredDeviation_ = 0.0d;
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            this.bucketCounts_ = Collections.emptyList();
            this.bitField0_ &= -33;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return DistributionProto.internal_static_google_api_Distribution_descriptor;
        }

        public Distribution getDefaultInstanceForType() {
            return Distribution.getDefaultInstance();
        }

        public Distribution build() {
            Distribution buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Distribution buildPartial() {
            Distribution distribution = new Distribution((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            distribution.count_ = this.count_;
            distribution.mean_ = this.mean_;
            distribution.sumOfSquaredDeviation_ = this.sumOfSquaredDeviation_;
            SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                distribution.range_ = this.range_;
            } else {
                distribution.range_ = (Range) singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> singleFieldBuilderV32 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV32 == null) {
                distribution.bucketOptions_ = this.bucketOptions_;
            } else {
                distribution.bucketOptions_ = (BucketOptions) singleFieldBuilderV32.build();
            }
            if ((this.bitField0_ & 32) == 32) {
                this.bucketCounts_ = Collections.unmodifiableList(this.bucketCounts_);
                this.bitField0_ &= -33;
            }
            distribution.bucketCounts_ = this.bucketCounts_;
            distribution.bitField0_ = 0;
            onBuilt();
            return distribution;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        public Builder mergeFrom(Message message) {
            if (message instanceof Distribution) {
                return mergeFrom((Distribution) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Distribution distribution) {
            if (distribution == Distribution.getDefaultInstance()) {
                return this;
            }
            if (distribution.getCount() != 0) {
                setCount(distribution.getCount());
            }
            if (distribution.getMean() != 0.0d) {
                setMean(distribution.getMean());
            }
            if (distribution.getSumOfSquaredDeviation() != 0.0d) {
                setSumOfSquaredDeviation(distribution.getSumOfSquaredDeviation());
            }
            if (distribution.hasRange()) {
                mergeRange(distribution.getRange());
            }
            if (distribution.hasBucketOptions()) {
                mergeBucketOptions(distribution.getBucketOptions());
            }
            if (!distribution.bucketCounts_.isEmpty()) {
                if (this.bucketCounts_.isEmpty()) {
                    this.bucketCounts_ = distribution.bucketCounts_;
                    this.bitField0_ &= -33;
                } else {
                    ensureBucketCountsIsMutable();
                    this.bucketCounts_.addAll(distribution.bucketCounts_);
                }
                onChanged();
            }
            mergeUnknownFields(distribution.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Distribution.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Distribution.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Distribution r3 = (com.google.api.Distribution) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                if (r3 == 0) goto L_0x0010
                r2.mergeFrom(r3)
            L_0x0010:
                return r2
            L_0x0011:
                r3 = move-exception
                goto L_0x0021
            L_0x0013:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                com.google.api.Distribution r4 = (com.google.api.Distribution) r4     // Catch:{ all -> 0x0011 }
                java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                throw r3     // Catch:{ all -> 0x001f }
            L_0x001f:
                r3 = move-exception
                r0 = r4
            L_0x0021:
                if (r0 == 0) goto L_0x0026
                r2.mergeFrom(r0)
            L_0x0026:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$Builder");
        }

        public long getCount() {
            return this.count_;
        }

        public Builder setCount(long j) {
            this.count_ = j;
            onChanged();
            return this;
        }

        public Builder clearCount() {
            this.count_ = 0;
            onChanged();
            return this;
        }

        public double getMean() {
            return this.mean_;
        }

        public Builder setMean(double d) {
            this.mean_ = d;
            onChanged();
            return this;
        }

        public Builder clearMean() {
            this.mean_ = 0.0d;
            onChanged();
            return this;
        }

        public double getSumOfSquaredDeviation() {
            return this.sumOfSquaredDeviation_;
        }

        public Builder setSumOfSquaredDeviation(double d) {
            this.sumOfSquaredDeviation_ = d;
            onChanged();
            return this;
        }

        public Builder clearSumOfSquaredDeviation() {
            this.sumOfSquaredDeviation_ = 0.0d;
            onChanged();
            return this;
        }

        public boolean hasRange() {
            return (this.rangeBuilder_ == null && this.range_ == null) ? false : true;
        }

        public Range getRange() {
            SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Range) singleFieldBuilderV3.getMessage();
            }
            Range range = this.range_;
            if (range == null) {
                range = Range.getDefaultInstance();
            }
            return range;
        }

        public Builder setRange(Range range) {
            SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(range);
            } else if (range != null) {
                this.range_ = range;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRange(Builder builder) {
            SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.range_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRange(Range range) {
            SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Range range2 = this.range_;
                if (range2 != null) {
                    this.range_ = Range.newBuilder(range2).mergeFrom(range).buildPartial();
                } else {
                    this.range_ = range;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(range);
            }
            return this;
        }

        public Builder clearRange() {
            if (this.rangeBuilder_ == null) {
                this.range_ = null;
                onChanged();
            } else {
                this.range_ = null;
                this.rangeBuilder_ = null;
            }
            return this;
        }

        public Builder getRangeBuilder() {
            onChanged();
            return (Builder) getRangeFieldBuilder().getBuilder();
        }

        public RangeOrBuilder getRangeOrBuilder() {
            SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> singleFieldBuilderV3 = this.rangeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RangeOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Range range = this.range_;
            if (range == null) {
                range = Range.getDefaultInstance();
            }
            return range;
        }

        private SingleFieldBuilderV3<Range, Builder, RangeOrBuilder> getRangeFieldBuilder() {
            if (this.rangeBuilder_ == null) {
                this.rangeBuilder_ = new SingleFieldBuilderV3<>(getRange(), getParentForChildren(), isClean());
                this.range_ = null;
            }
            return this.rangeBuilder_;
        }

        public boolean hasBucketOptions() {
            return (this.bucketOptionsBuilder_ == null && this.bucketOptions_ == null) ? false : true;
        }

        public BucketOptions getBucketOptions() {
            SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (BucketOptions) singleFieldBuilderV3.getMessage();
            }
            BucketOptions bucketOptions = this.bucketOptions_;
            if (bucketOptions == null) {
                bucketOptions = BucketOptions.getDefaultInstance();
            }
            return bucketOptions;
        }

        public Builder setBucketOptions(BucketOptions bucketOptions) {
            SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(bucketOptions);
            } else if (bucketOptions != null) {
                this.bucketOptions_ = bucketOptions;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setBucketOptions(Builder builder) {
            SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.bucketOptions_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBucketOptions(BucketOptions bucketOptions) {
            SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                BucketOptions bucketOptions2 = this.bucketOptions_;
                if (bucketOptions2 != null) {
                    this.bucketOptions_ = BucketOptions.newBuilder(bucketOptions2).mergeFrom(bucketOptions).buildPartial();
                } else {
                    this.bucketOptions_ = bucketOptions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(bucketOptions);
            }
            return this;
        }

        public Builder clearBucketOptions() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptions_ = null;
                onChanged();
            } else {
                this.bucketOptions_ = null;
                this.bucketOptionsBuilder_ = null;
            }
            return this;
        }

        public Builder getBucketOptionsBuilder() {
            onChanged();
            return (Builder) getBucketOptionsFieldBuilder().getBuilder();
        }

        public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
            SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> singleFieldBuilderV3 = this.bucketOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (BucketOptionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            BucketOptions bucketOptions = this.bucketOptions_;
            if (bucketOptions == null) {
                bucketOptions = BucketOptions.getDefaultInstance();
            }
            return bucketOptions;
        }

        private SingleFieldBuilderV3<BucketOptions, Builder, BucketOptionsOrBuilder> getBucketOptionsFieldBuilder() {
            if (this.bucketOptionsBuilder_ == null) {
                this.bucketOptionsBuilder_ = new SingleFieldBuilderV3<>(getBucketOptions(), getParentForChildren(), isClean());
                this.bucketOptions_ = null;
            }
            return this.bucketOptionsBuilder_;
        }

        private void ensureBucketCountsIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.bucketCounts_ = new ArrayList(this.bucketCounts_);
                this.bitField0_ |= 32;
            }
        }

        public List<Long> getBucketCountsList() {
            return Collections.unmodifiableList(this.bucketCounts_);
        }

        public int getBucketCountsCount() {
            return this.bucketCounts_.size();
        }

        public long getBucketCounts(int i) {
            return ((Long) this.bucketCounts_.get(i)).longValue();
        }

        public Builder setBucketCounts(int i, long j) {
            ensureBucketCountsIsMutable();
            this.bucketCounts_.set(i, Long.valueOf(j));
            onChanged();
            return this;
        }

        public Builder addBucketCounts(long j) {
            ensureBucketCountsIsMutable();
            this.bucketCounts_.add(Long.valueOf(j));
            onChanged();
            return this;
        }

        public Builder addAllBucketCounts(Iterable<? extends Long> iterable) {
            ensureBucketCountsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.bucketCounts_);
            onChanged();
            return this;
        }

        public Builder clearBucketCounts() {
            this.bucketCounts_ = Collections.emptyList();
            this.bitField0_ &= -33;
            onChanged();
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static final class Range extends GeneratedMessageV3 implements RangeOrBuilder {
        private static final Range DEFAULT_INSTANCE = new Range();
        public static final int MAX_FIELD_NUMBER = 2;
        public static final int MIN_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Parser<Range> PARSER = new AbstractParser<Range>() {
            public Range parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Range(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public double max_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public double min_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements RangeOrBuilder {
            private double max_;
            private double min_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Range.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.min_ = 0.0d;
                this.max_ = 0.0d;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
            }

            public Range getDefaultInstanceForType() {
                return Range.getDefaultInstance();
            }

            public Range build() {
                Range buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Range buildPartial() {
                Range range = new Range((com.google.protobuf.GeneratedMessageV3.Builder) this);
                range.min_ = this.min_;
                range.max_ = this.max_;
                onBuilt();
                return range;
            }

            public Builder clone() {
                return (Builder) super.clone();
            }

            public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            public Builder clearField(FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            public Builder clearOneof(OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Range) {
                    return mergeFrom((Range) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Range range) {
                if (range == Range.getDefaultInstance()) {
                    return this;
                }
                if (range.getMin() != 0.0d) {
                    setMin(range.getMin());
                }
                if (range.getMax() != 0.0d) {
                    setMax(range.getMax());
                }
                mergeUnknownFields(range.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.api.Distribution.Range.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Distribution.Range.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.api.Distribution$Range r3 = (com.google.api.Distribution.Range) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    if (r3 == 0) goto L_0x0010
                    r2.mergeFrom(r3)
                L_0x0010:
                    return r2
                L_0x0011:
                    r3 = move-exception
                    goto L_0x0021
                L_0x0013:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                    com.google.api.Distribution$Range r4 = (com.google.api.Distribution.Range) r4     // Catch:{ all -> 0x0011 }
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                    throw r3     // Catch:{ all -> 0x001f }
                L_0x001f:
                    r3 = move-exception
                    r0 = r4
                L_0x0021:
                    if (r0 == 0) goto L_0x0026
                    r2.mergeFrom(r0)
                L_0x0026:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.Range.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Distribution$Range$Builder");
            }

            public double getMin() {
                return this.min_;
            }

            public Builder setMin(double d) {
                this.min_ = d;
                onChanged();
                return this;
            }

            public Builder clearMin() {
                this.min_ = 0.0d;
                onChanged();
                return this;
            }

            public double getMax() {
                return this.max_;
            }

            public Builder setMax(double d) {
                this.max_ = d;
                onChanged();
                return this;
            }

            public Builder clearMax() {
                this.max_ = 0.0d;
                onChanged();
                return this;
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        private Range(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private Range() {
            this.memoizedIsInitialized = -1;
            this.min_ = 0.0d;
            this.max_ = 0.0d;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Range(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 9) {
                            this.min_ = codedInputStream.readDouble();
                        } else if (readTag == 17) {
                            this.max_ = codedInputStream.readDouble();
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return DistributionProto.internal_static_google_api_Distribution_Range_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return DistributionProto.internal_static_google_api_Distribution_Range_fieldAccessorTable.ensureFieldAccessorsInitialized(Range.class, Builder.class);
        }

        public double getMin() {
            return this.min_;
        }

        public double getMax() {
            return this.max_;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            double d = this.min_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            double d2 = this.max_;
            if (d2 != 0.0d) {
                codedOutputStream.writeDouble(2, d2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            double d = this.min_;
            if (d != 0.0d) {
                i2 = 0 + CodedOutputStream.computeDoubleSize(1, d);
            }
            double d2 = this.max_;
            if (d2 != 0.0d) {
                i2 += CodedOutputStream.computeDoubleSize(2, d2);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return super.equals(obj);
            }
            Range range = (Range) obj;
            if (!(((Double.doubleToLongBits(getMin()) > Double.doubleToLongBits(range.getMin()) ? 1 : (Double.doubleToLongBits(getMin()) == Double.doubleToLongBits(range.getMin()) ? 0 : -1)) == 0) && Double.doubleToLongBits(getMax()) == Double.doubleToLongBits(range.getMax())) || !this.unknownFields.equals(range.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getMin()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMax()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Range parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteBuffer);
        }

        public static Range parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Range parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteString);
        }

        public static Range parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Range parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(bArr);
        }

        public static Range parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Range) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Range parseFrom(InputStream inputStream) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Range parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Range parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Range) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Range parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Range) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Range parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Range parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Range) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Range range) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(range);
        }

        public Builder toBuilder() {
            if (this == DEFAULT_INSTANCE) {
                return new Builder();
            }
            return new Builder().mergeFrom(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static Range getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Range> parser() {
            return PARSER;
        }

        public Parser<Range> getParserForType() {
            return PARSER;
        }

        public Range getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface RangeOrBuilder extends MessageOrBuilder {
        double getMax();

        double getMin();
    }

    private Distribution(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.bucketCountsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = -1;
    }

    private Distribution() {
        this.bucketCountsMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = -1;
        this.count_ = 0;
        this.mean_ = 0.0d;
        this.sumOfSquaredDeviation_ = 0.0d;
        this.bucketCounts_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.google.api.Distribution$Range$Builder] */
    /* JADX WARNING: type inference failed for: r7v2, types: [com.google.api.Distribution$Range$Builder] */
    /* JADX WARNING: type inference failed for: r7v3, types: [com.google.api.Distribution$BucketOptions$Builder] */
    /* JADX WARNING: type inference failed for: r7v4, types: [com.google.api.Distribution$BucketOptions$Builder] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.api.Distribution$BucketOptions$Builder, com.google.api.Distribution$Range$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.google.api.Distribution$Range$Builder, com.google.api.Distribution$BucketOptions$Builder]
  mth insns count: 115
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Distribution(com.google.protobuf.CodedInputStream r9, com.google.protobuf.ExtensionRegistryLite r10) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r8 = this;
            r8.<init>()
            if (r10 == 0) goto L_0x012d
            com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
            r1 = 0
            r2 = 0
        L_0x000b:
            r3 = 32
            if (r1 != 0) goto L_0x0117
            int r4 = r9.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r5 = 1
            if (r4 == 0) goto L_0x00eb
            r6 = 8
            if (r4 == r6) goto L_0x00e3
            r6 = 17
            if (r4 == r6) goto L_0x00db
            r6 = 25
            if (r4 == r6) goto L_0x00d3
            r6 = 34
            r7 = 0
            if (r4 == r6) goto L_0x00ae
            r6 = 50
            if (r4 == r6) goto L_0x0089
            r6 = 56
            if (r4 == r6) goto L_0x006e
            r6 = 58
            if (r4 == r6) goto L_0x003b
            boolean r3 = r8.parseUnknownFieldProto3(r9, r0, r10, r4)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r3 != 0) goto L_0x000b
            r1 = 1
            goto L_0x000b
        L_0x003b:
            int r4 = r9.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            int r4 = r9.pushLimit(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r5 = r2 & 32
            if (r5 == r3) goto L_0x0056
            int r5 = r9.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r5 <= 0) goto L_0x0056
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r5.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.bucketCounts_ = r5     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r2 = r2 | 32
        L_0x0056:
            int r5 = r9.getBytesUntilLimit()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r5 <= 0) goto L_0x006a
            java.util.List<java.lang.Long> r5 = r8.bucketCounts_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            long r6 = r9.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r5.add(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x0056
        L_0x006a:
            r9.popLimit(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x006e:
            r4 = r2 & 32
            if (r4 == r3) goto L_0x007b
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r4.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.bucketCounts_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r2 = r2 | 32
        L_0x007b:
            java.util.List<java.lang.Long> r4 = r8.bucketCounts_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            long r5 = r9.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r4.add(r5)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x0089:
            com.google.api.Distribution$BucketOptions r4 = r8.bucketOptions_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r4 == 0) goto L_0x0093
            com.google.api.Distribution$BucketOptions r4 = r8.bucketOptions_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.api.Distribution$BucketOptions$Builder r7 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
        L_0x0093:
            com.google.protobuf.Parser r4 = com.google.api.Distribution.BucketOptions.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.protobuf.MessageLite r4 = r9.readMessage(r4, r10)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.api.Distribution$BucketOptions r4 = (com.google.api.Distribution.BucketOptions) r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.bucketOptions_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r7 == 0) goto L_0x000b
            com.google.api.Distribution$BucketOptions r4 = r8.bucketOptions_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r7.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.api.Distribution$BucketOptions r4 = r7.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.bucketOptions_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x00ae:
            com.google.api.Distribution$Range r4 = r8.range_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r4 == 0) goto L_0x00b8
            com.google.api.Distribution$Range r4 = r8.range_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.api.Distribution$Range$Builder r7 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
        L_0x00b8:
            com.google.protobuf.Parser r4 = com.google.api.Distribution.Range.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.protobuf.MessageLite r4 = r9.readMessage(r4, r10)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.api.Distribution$Range r4 = (com.google.api.Distribution.Range) r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.range_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            if (r7 == 0) goto L_0x000b
            com.google.api.Distribution$Range r4 = r8.range_     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r7.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            com.google.api.Distribution$Range r4 = r7.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.range_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x00d3:
            double r4 = r9.readDouble()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.sumOfSquaredDeviation_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x00db:
            double r4 = r9.readDouble()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.mean_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x00e3:
            long r4 = r9.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            r8.count_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00fb, IOException -> 0x00f0 }
            goto L_0x000b
        L_0x00eb:
            r1 = 1
            goto L_0x000b
        L_0x00ee:
            r9 = move-exception
            goto L_0x0101
        L_0x00f0:
            r9 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r10 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00ee }
            r10.<init>(r9)     // Catch:{ all -> 0x00ee }
            com.google.protobuf.InvalidProtocolBufferException r9 = r10.setUnfinishedMessage(r8)     // Catch:{ all -> 0x00ee }
            throw r9     // Catch:{ all -> 0x00ee }
        L_0x00fb:
            r9 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r9 = r9.setUnfinishedMessage(r8)     // Catch:{ all -> 0x00ee }
            throw r9     // Catch:{ all -> 0x00ee }
        L_0x0101:
            r10 = r2 & 32
            if (r10 != r3) goto L_0x010d
            java.util.List<java.lang.Long> r10 = r8.bucketCounts_
            java.util.List r10 = java.util.Collections.unmodifiableList(r10)
            r8.bucketCounts_ = r10
        L_0x010d:
            com.google.protobuf.UnknownFieldSet r10 = r0.build()
            r8.unknownFields = r10
            r8.makeExtensionsImmutable()
            throw r9
        L_0x0117:
            r9 = r2 & 32
            if (r9 != r3) goto L_0x0123
            java.util.List<java.lang.Long> r9 = r8.bucketCounts_
            java.util.List r9 = java.util.Collections.unmodifiableList(r9)
            r8.bucketCounts_ = r9
        L_0x0123:
            com.google.protobuf.UnknownFieldSet r9 = r0.build()
            r8.unknownFields = r9
            r8.makeExtensionsImmutable()
            return
        L_0x012d:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.Distribution.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return DistributionProto.internal_static_google_api_Distribution_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return DistributionProto.internal_static_google_api_Distribution_fieldAccessorTable.ensureFieldAccessorsInitialized(Distribution.class, Builder.class);
    }

    public long getCount() {
        return this.count_;
    }

    public double getMean() {
        return this.mean_;
    }

    public double getSumOfSquaredDeviation() {
        return this.sumOfSquaredDeviation_;
    }

    public boolean hasRange() {
        return this.range_ != null;
    }

    public Range getRange() {
        Range range = this.range_;
        return range == null ? Range.getDefaultInstance() : range;
    }

    public RangeOrBuilder getRangeOrBuilder() {
        return getRange();
    }

    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    public BucketOptions getBucketOptions() {
        BucketOptions bucketOptions = this.bucketOptions_;
        return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
    }

    public BucketOptionsOrBuilder getBucketOptionsOrBuilder() {
        return getBucketOptions();
    }

    public List<Long> getBucketCountsList() {
        return this.bucketCounts_;
    }

    public int getBucketCountsCount() {
        return this.bucketCounts_.size();
    }

    public long getBucketCounts(int i) {
        return ((Long) this.bucketCounts_.get(i)).longValue();
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        long j = this.count_;
        if (j != 0) {
            codedOutputStream.writeInt64(1, j);
        }
        double d = this.mean_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(2, d);
        }
        double d2 = this.sumOfSquaredDeviation_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(3, d2);
        }
        if (this.range_ != null) {
            codedOutputStream.writeMessage(4, getRange());
        }
        if (this.bucketOptions_ != null) {
            codedOutputStream.writeMessage(6, getBucketOptions());
        }
        if (getBucketCountsList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(58);
            codedOutputStream.writeUInt32NoTag(this.bucketCountsMemoizedSerializedSize);
        }
        for (int i = 0; i < this.bucketCounts_.size(); i++) {
            codedOutputStream.writeInt64NoTag(((Long) this.bucketCounts_.get(i)).longValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.count_;
        int computeInt64Size = j != 0 ? CodedOutputStream.computeInt64Size(1, j) + 0 : 0;
        double d = this.mean_;
        if (d != 0.0d) {
            computeInt64Size += CodedOutputStream.computeDoubleSize(2, d);
        }
        double d2 = this.sumOfSquaredDeviation_;
        if (d2 != 0.0d) {
            computeInt64Size += CodedOutputStream.computeDoubleSize(3, d2);
        }
        if (this.range_ != null) {
            computeInt64Size += CodedOutputStream.computeMessageSize(4, getRange());
        }
        if (this.bucketOptions_ != null) {
            computeInt64Size += CodedOutputStream.computeMessageSize(6, getBucketOptions());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.bucketCounts_.size(); i3++) {
            i2 += CodedOutputStream.computeInt64SizeNoTag(((Long) this.bucketCounts_.get(i3)).longValue());
        }
        int i4 = computeInt64Size + i2;
        if (!getBucketCountsList().isEmpty()) {
            i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(i2);
        }
        this.bucketCountsMemoizedSerializedSize = i2;
        int serializedSize = i4 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution)) {
            return super.equals(obj);
        }
        Distribution distribution = (Distribution) obj;
        boolean z2 = ((((getCount() > distribution.getCount() ? 1 : (getCount() == distribution.getCount() ? 0 : -1)) == 0) && (Double.doubleToLongBits(getMean()) > Double.doubleToLongBits(distribution.getMean()) ? 1 : (Double.doubleToLongBits(getMean()) == Double.doubleToLongBits(distribution.getMean()) ? 0 : -1)) == 0) && (Double.doubleToLongBits(getSumOfSquaredDeviation()) > Double.doubleToLongBits(distribution.getSumOfSquaredDeviation()) ? 1 : (Double.doubleToLongBits(getSumOfSquaredDeviation()) == Double.doubleToLongBits(distribution.getSumOfSquaredDeviation()) ? 0 : -1)) == 0) && hasRange() == distribution.hasRange();
        if (hasRange()) {
            z2 = z2 && getRange().equals(distribution.getRange());
        }
        boolean z3 = z2 && hasBucketOptions() == distribution.hasBucketOptions();
        if (hasBucketOptions()) {
            z3 = z3 && getBucketOptions().equals(distribution.getBucketOptions());
        }
        if (!(z3 && getBucketCountsList().equals(distribution.getBucketCountsList())) || !this.unknownFields.equals(distribution.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getCount())) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getMean()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getSumOfSquaredDeviation()));
        if (hasRange()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getRange().hashCode();
        }
        if (hasBucketOptions()) {
            hashCode = (((hashCode * 37) + 6) * 53) + getBucketOptions().hashCode();
        }
        if (getBucketCountsCount() > 0) {
            hashCode = (((hashCode * 37) + 7) * 53) + getBucketCountsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteBuffer);
    }

    public static Distribution parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Distribution parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteString);
    }

    public static Distribution parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Distribution parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(bArr);
    }

    public static Distribution parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Distribution) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Distribution parseFrom(InputStream inputStream) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Distribution parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Distribution) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Distribution parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Distribution) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Distribution parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Distribution) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Distribution distribution) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(distribution);
    }

    public Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* access modifiers changed from: protected */
    public Builder newBuilderForType(BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static Distribution getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Distribution> parser() {
        return PARSER;
    }

    public Parser<Distribution> getParserForType() {
        return PARSER;
    }

    public Distribution getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
