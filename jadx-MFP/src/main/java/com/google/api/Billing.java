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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Billing extends GeneratedMessageV3 implements BillingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 8;
    private static final Billing DEFAULT_INSTANCE = new Billing();
    /* access modifiers changed from: private */
    public static final Parser<Billing> PARSER = new AbstractParser<Billing>() {
        public Billing parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Billing(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<BillingDestination> consumerDestinations_;
    private byte memoizedIsInitialized;

    public static final class BillingDestination extends GeneratedMessageV3 implements BillingDestinationOrBuilder {
        private static final BillingDestination DEFAULT_INSTANCE = new BillingDestination();
        public static final int METRICS_FIELD_NUMBER = 2;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Parser<BillingDestination> PARSER = new AbstractParser<BillingDestination>() {
            public BillingDestination parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BillingDestination(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public LazyStringList metrics_;
        /* access modifiers changed from: private */
        public volatile Object monitoredResource_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements BillingDestinationOrBuilder {
            private int bitField0_;
            private LazyStringList metrics_;
            private Object monitoredResource_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return BillingProto.internal_static_google_api_Billing_BillingDestination_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return BillingProto.internal_static_google_api_Billing_BillingDestination_fieldAccessorTable.ensureFieldAccessorsInitialized(BillingDestination.class, Builder.class);
            }

            private Builder() {
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                BillingDestination.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return BillingProto.internal_static_google_api_Billing_BillingDestination_descriptor;
            }

            public BillingDestination getDefaultInstanceForType() {
                return BillingDestination.getDefaultInstance();
            }

            public BillingDestination build() {
                BillingDestination buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public BillingDestination buildPartial() {
                BillingDestination billingDestination = new BillingDestination((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i = this.bitField0_;
                billingDestination.monitoredResource_ = this.monitoredResource_;
                if ((this.bitField0_ & 2) == 2) {
                    this.metrics_ = this.metrics_.getUnmodifiableView();
                    this.bitField0_ &= -3;
                }
                billingDestination.metrics_ = this.metrics_;
                billingDestination.bitField0_ = 0;
                onBuilt();
                return billingDestination;
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
                if (message instanceof BillingDestination) {
                    return mergeFrom((BillingDestination) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BillingDestination billingDestination) {
                if (billingDestination == BillingDestination.getDefaultInstance()) {
                    return this;
                }
                if (!billingDestination.getMonitoredResource().isEmpty()) {
                    this.monitoredResource_ = billingDestination.monitoredResource_;
                    onChanged();
                }
                if (!billingDestination.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = billingDestination.metrics_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMetricsIsMutable();
                        this.metrics_.addAll(billingDestination.metrics_);
                    }
                    onChanged();
                }
                mergeUnknownFields(billingDestination.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.api.Billing.BillingDestination.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Billing.BillingDestination.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.api.Billing$BillingDestination r3 = (com.google.api.Billing.BillingDestination) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                    com.google.api.Billing$BillingDestination r4 = (com.google.api.Billing.BillingDestination) r4     // Catch:{ all -> 0x0011 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Billing.BillingDestination.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Billing$BillingDestination$Builder");
            }

            public String getMonitoredResource() {
                Object obj = this.monitoredResource_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.monitoredResource_ = stringUtf8;
                return stringUtf8;
            }

            public ByteString getMonitoredResourceBytes() {
                Object obj = this.monitoredResource_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.monitoredResource_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setMonitoredResource(String str) {
                if (str != null) {
                    this.monitoredResource_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMonitoredResource() {
                this.monitoredResource_ = BillingDestination.getDefaultInstance().getMonitoredResource();
                onChanged();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                if (byteString != null) {
                    BillingDestination.checkByteStringIsUtf8(byteString);
                    this.monitoredResource_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureMetricsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.metrics_ = new LazyStringArrayList(this.metrics_);
                    this.bitField0_ |= 2;
                }
            }

            public ProtocolStringList getMetricsList() {
                return this.metrics_.getUnmodifiableView();
            }

            public int getMetricsCount() {
                return this.metrics_.size();
            }

            public String getMetrics(int i) {
                return (String) this.metrics_.get(i);
            }

            public ByteString getMetricsBytes(int i) {
                return this.metrics_.getByteString(i);
            }

            public Builder setMetrics(int i, String str) {
                if (str != null) {
                    ensureMetricsIsMutable();
                    this.metrics_.set(i, str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addMetrics(String str) {
                if (str != null) {
                    ensureMetricsIsMutable();
                    this.metrics_.add(str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllMetrics(Iterable<String> iterable) {
                ensureMetricsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.metrics_);
                onChanged();
                return this;
            }

            public Builder clearMetrics() {
                this.metrics_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public Builder addMetricsBytes(ByteString byteString) {
                if (byteString != null) {
                    BillingDestination.checkByteStringIsUtf8(byteString);
                    ensureMetricsIsMutable();
                    this.metrics_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        private BillingDestination(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private BillingDestination() {
            this.memoizedIsInitialized = -1;
            this.monitoredResource_ = "";
            this.metrics_ = LazyStringArrayList.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BillingDestination(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 10) {
                            this.monitoredResource_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.metrics_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.metrics_.add(readStringRequireUtf8);
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.metrics_ = this.metrics_.getUnmodifiableView();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.metrics_ = this.metrics_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return BillingProto.internal_static_google_api_Billing_BillingDestination_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return BillingProto.internal_static_google_api_Billing_BillingDestination_fieldAccessorTable.ensureFieldAccessorsInitialized(BillingDestination.class, Builder.class);
        }

        public String getMonitoredResource() {
            Object obj = this.monitoredResource_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.monitoredResource_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getMonitoredResourceBytes() {
            Object obj = this.monitoredResource_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.monitoredResource_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public ProtocolStringList getMetricsList() {
            return this.metrics_;
        }

        public int getMetricsCount() {
            return this.metrics_.size();
        }

        public String getMetrics(int i) {
            return (String) this.metrics_.get(i);
        }

        public ByteString getMetricsBytes(int i) {
            return this.metrics_.getByteString(i);
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
            if (!getMonitoredResourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.monitoredResource_);
            }
            for (int i = 0; i < this.metrics_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.metrics_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !getMonitoredResourceBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.monitoredResource_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.metrics_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.metrics_.getRaw(i3));
            }
            int size = computeStringSize + i2 + (getMetricsList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BillingDestination)) {
                return super.equals(obj);
            }
            BillingDestination billingDestination = (BillingDestination) obj;
            if (!((getMonitoredResource().equals(billingDestination.getMonitoredResource())) && getMetricsList().equals(billingDestination.getMetricsList())) || !this.unknownFields.equals(billingDestination.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMonitoredResource().hashCode();
            if (getMetricsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getMetricsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static BillingDestination parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BillingDestination) PARSER.parseFrom(byteBuffer);
        }

        public static BillingDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BillingDestination) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BillingDestination parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BillingDestination) PARSER.parseFrom(byteString);
        }

        public static BillingDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BillingDestination) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BillingDestination parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BillingDestination) PARSER.parseFrom(bArr);
        }

        public static BillingDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BillingDestination) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BillingDestination parseFrom(InputStream inputStream) throws IOException {
            return (BillingDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BillingDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BillingDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BillingDestination parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BillingDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BillingDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BillingDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BillingDestination parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BillingDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BillingDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BillingDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BillingDestination billingDestination) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(billingDestination);
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

        public static BillingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BillingDestination> parser() {
            return PARSER;
        }

        public Parser<BillingDestination> getParserForType() {
            return PARSER;
        }

        public BillingDestination getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface BillingDestinationOrBuilder extends MessageOrBuilder {
        String getMetrics(int i);

        ByteString getMetricsBytes(int i);

        int getMetricsCount();

        List<String> getMetricsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements BillingOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> consumerDestinationsBuilder_;
        private List<BillingDestination> consumerDestinations_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return BillingProto.internal_static_google_api_Billing_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return BillingProto.internal_static_google_api_Billing_fieldAccessorTable.ensureFieldAccessorsInitialized(Billing.class, Builder.class);
        }

        private Builder() {
            this.consumerDestinations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.consumerDestinations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Billing.alwaysUseFieldBuilders) {
                getConsumerDestinationsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return BillingProto.internal_static_google_api_Billing_descriptor;
        }

        public Billing getDefaultInstanceForType() {
            return Billing.getDefaultInstance();
        }

        public Billing build() {
            Billing buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Billing buildPartial() {
            Billing billing = new Billing((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    this.bitField0_ &= -2;
                }
                billing.consumerDestinations_ = this.consumerDestinations_;
            } else {
                billing.consumerDestinations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return billing;
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
            if (message instanceof Billing) {
                return mergeFrom((Billing) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Billing billing) {
            if (billing == Billing.getDefaultInstance()) {
                return this;
            }
            if (this.consumerDestinationsBuilder_ == null) {
                if (!billing.consumerDestinations_.isEmpty()) {
                    if (this.consumerDestinations_.isEmpty()) {
                        this.consumerDestinations_ = billing.consumerDestinations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureConsumerDestinationsIsMutable();
                        this.consumerDestinations_.addAll(billing.consumerDestinations_);
                    }
                    onChanged();
                }
            } else if (!billing.consumerDestinations_.isEmpty()) {
                if (this.consumerDestinationsBuilder_.isEmpty()) {
                    this.consumerDestinationsBuilder_.dispose();
                    RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = null;
                    this.consumerDestinationsBuilder_ = null;
                    this.consumerDestinations_ = billing.consumerDestinations_;
                    this.bitField0_ &= -2;
                    if (Billing.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getConsumerDestinationsFieldBuilder();
                    }
                    this.consumerDestinationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.consumerDestinationsBuilder_.addAllMessages(billing.consumerDestinations_);
                }
            }
            mergeUnknownFields(billing.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Billing.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Billing.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Billing r3 = (com.google.api.Billing) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Billing r4 = (com.google.api.Billing) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Billing.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Billing$Builder");
        }

        private void ensureConsumerDestinationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.consumerDestinations_ = new ArrayList(this.consumerDestinations_);
                this.bitField0_ |= 1;
            }
        }

        public List<BillingDestination> getConsumerDestinationsList() {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.consumerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getConsumerDestinationsCount() {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public BillingDestination getConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (BillingDestination) this.consumerDestinations_.get(i);
            }
            return (BillingDestination) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setConsumerDestinations(int i, BillingDestination billingDestination) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, billingDestination);
            } else if (billingDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, billingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setConsumerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(BillingDestination billingDestination) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(billingDestination);
            } else if (billingDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(billingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, BillingDestination billingDestination) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, billingDestination);
            } else if (billingDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, billingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addConsumerDestinations(Builder builder) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends BillingDestination> iterable) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.consumerDestinations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearConsumerDestinations() {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getConsumerDestinationsBuilder(int i) {
            return (Builder) getConsumerDestinationsFieldBuilder().getBuilder(i);
        }

        public BillingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (BillingDestinationOrBuilder) this.consumerDestinations_.get(i);
            }
            return (BillingDestinationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends BillingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.consumerDestinations_);
        }

        public Builder addConsumerDestinationsBuilder() {
            return (Builder) getConsumerDestinationsFieldBuilder().addBuilder(BillingDestination.getDefaultInstance());
        }

        public Builder addConsumerDestinationsBuilder(int i) {
            return (Builder) getConsumerDestinationsFieldBuilder().addBuilder(i, BillingDestination.getDefaultInstance());
        }

        public List<Builder> getConsumerDestinationsBuilderList() {
            return getConsumerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<BillingDestination, Builder, BillingDestinationOrBuilder> getConsumerDestinationsFieldBuilder() {
            if (this.consumerDestinationsBuilder_ == null) {
                List<BillingDestination> list = this.consumerDestinations_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.consumerDestinationsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.consumerDestinations_ = null;
            }
            return this.consumerDestinationsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Billing(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Billing() {
        this.memoizedIsInitialized = -1;
        this.consumerDestinations_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Billing(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 66) {
                        if (!z2 || !true) {
                            this.consumerDestinations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.consumerDestinations_.add(codedInputStream.readMessage(BillingDestination.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return BillingProto.internal_static_google_api_Billing_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return BillingProto.internal_static_google_api_Billing_fieldAccessorTable.ensureFieldAccessorsInitialized(Billing.class, Builder.class);
    }

    public List<BillingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends BillingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    public BillingDestination getConsumerDestinations(int i) {
        return (BillingDestination) this.consumerDestinations_.get(i);
    }

    public BillingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
        return (BillingDestinationOrBuilder) this.consumerDestinations_.get(i);
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
        for (int i = 0; i < this.consumerDestinations_.size(); i++) {
            codedOutputStream.writeMessage(8, (MessageLite) this.consumerDestinations_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.consumerDestinations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(8, (MessageLite) this.consumerDestinations_.get(i3));
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
        if (!(obj instanceof Billing)) {
            return super.equals(obj);
        }
        Billing billing = (Billing) obj;
        if (!(getConsumerDestinationsList().equals(billing.getConsumerDestinationsList())) || !this.unknownFields.equals(billing.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getConsumerDestinationsCount() > 0) {
            hashCode = (((hashCode * 37) + 8) * 53) + getConsumerDestinationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Billing parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Billing) PARSER.parseFrom(byteBuffer);
    }

    public static Billing parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Billing) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Billing parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Billing) PARSER.parseFrom(byteString);
    }

    public static Billing parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Billing) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Billing parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Billing) PARSER.parseFrom(bArr);
    }

    public static Billing parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Billing) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Billing parseFrom(InputStream inputStream) throws IOException {
        return (Billing) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Billing parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Billing) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Billing parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Billing) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Billing parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Billing) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Billing parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Billing) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Billing parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Billing) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Billing billing) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(billing);
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

    public static Billing getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Billing> parser() {
        return PARSER;
    }

    public Parser<Billing> getParserForType() {
        return PARSER;
    }

    public Billing getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
