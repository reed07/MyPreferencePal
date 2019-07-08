package com.lightstep.tracer.grpc;

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
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;

public final class MetricsSample extends GeneratedMessageV3 implements MetricsSampleOrBuilder {
    private static final MetricsSample DEFAULT_INSTANCE = new MetricsSample();
    /* access modifiers changed from: private */
    public static final Parser<MetricsSample> PARSER = new AbstractParser<MetricsSample>() {
        public MetricsSample parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetricsSample(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public int valueCase_;
    /* access modifiers changed from: private */
    public Object value_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MetricsSampleOrBuilder {
        private Object name_;
        private int valueCase_;
        private Object value_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_MetricsSample_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricsSample.class, Builder.class);
        }

        private Builder() {
            this.valueCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.valueCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            MetricsSample.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_MetricsSample_descriptor;
        }

        public MetricsSample getDefaultInstanceForType() {
            return MetricsSample.getDefaultInstance();
        }

        public MetricsSample build() {
            MetricsSample buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public MetricsSample buildPartial() {
            MetricsSample metricsSample = new MetricsSample((com.google.protobuf.GeneratedMessageV3.Builder) this);
            metricsSample.name_ = this.name_;
            if (this.valueCase_ == 2) {
                metricsSample.value_ = this.value_;
            }
            if (this.valueCase_ == 3) {
                metricsSample.value_ = this.value_;
            }
            metricsSample.valueCase_ = this.valueCase_;
            onBuilt();
            return metricsSample;
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
            if (message instanceof MetricsSample) {
                return mergeFrom((MetricsSample) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetricsSample metricsSample) {
            if (metricsSample == MetricsSample.getDefaultInstance()) {
                return this;
            }
            if (!metricsSample.getName().isEmpty()) {
                this.name_ = metricsSample.name_;
                onChanged();
            }
            switch (metricsSample.getValueCase()) {
                case INT_VALUE:
                    setIntValue(metricsSample.getIntValue());
                    break;
                case DOUBLE_VALUE:
                    setDoubleValue(metricsSample.getDoubleValue());
                    break;
            }
            mergeUnknownFields(metricsSample.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.MetricsSample.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.MetricsSample.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.MetricsSample r3 = (com.lightstep.tracer.grpc.MetricsSample) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.MetricsSample r4 = (com.lightstep.tracer.grpc.MetricsSample) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.MetricsSample.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.MetricsSample$Builder");
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setIntValue(long j) {
            this.valueCase_ = 2;
            this.value_ = Long.valueOf(j);
            onChanged();
            return this;
        }

        public Builder setDoubleValue(double d) {
            this.valueCase_ = 3;
            this.value_ = Double.valueOf(d);
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

    public enum ValueCase implements EnumLite {
        INT_VALUE(2),
        DOUBLE_VALUE(3),
        VALUE_NOT_SET(0);
        
        private final int value;

        private ValueCase(int i) {
            this.value = i;
        }

        public static ValueCase forNumber(int i) {
            if (i == 0) {
                return VALUE_NOT_SET;
            }
            switch (i) {
                case 2:
                    return INT_VALUE;
                case 3:
                    return DOUBLE_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    private MetricsSample(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = -1;
    }

    private MetricsSample() {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = -1;
        this.name_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MetricsSample(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 10) {
                        this.name_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 16) {
                        this.valueCase_ = 2;
                        this.value_ = Long.valueOf(codedInputStream.readInt64());
                    } else if (readTag == 25) {
                        this.valueCase_ = 3;
                        this.value_ = Double.valueOf(codedInputStream.readDouble());
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
        return Collector.internal_static_lightstep_collector_MetricsSample_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_MetricsSample_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricsSample.class, Builder.class);
    }

    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public long getIntValue() {
        if (this.valueCase_ == 2) {
            return ((Long) this.value_).longValue();
        }
        return 0;
    }

    public double getDoubleValue() {
        if (this.valueCase_ == 3) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.valueCase_ == 2) {
            codedOutputStream.writeInt64(2, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 3) {
            codedOutputStream.writeDouble(3, ((Double) this.value_).doubleValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getNameBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (this.valueCase_ == 2) {
            i2 += CodedOutputStream.computeInt64Size(2, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 3) {
            i2 += CodedOutputStream.computeDoubleSize(3, ((Double) this.value_).doubleValue());
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
        if (!(obj instanceof MetricsSample)) {
            return super.equals(obj);
        }
        MetricsSample metricsSample = (MetricsSample) obj;
        boolean z2 = (getName().equals(metricsSample.getName())) && getValueCase().equals(metricsSample.getValueCase());
        if (!z2) {
            return false;
        }
        switch (this.valueCase_) {
            case 2:
                if (z2 && getIntValue() == metricsSample.getIntValue()) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 3:
                if (z2 && Double.doubleToLongBits(getDoubleValue()) == Double.doubleToLongBits(metricsSample.getDoubleValue())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
        }
        if (!z2 || !this.unknownFields.equals(metricsSample.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        switch (this.valueCase_) {
            case 2:
                hashCode = (((hashCode * 37) + 2) * 53) + Internal.hashLong(getIntValue());
                break;
            case 3:
                hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getDoubleValue()));
                break;
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
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

    public static MetricsSample getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricsSample> parser() {
        return PARSER;
    }

    public Parser<MetricsSample> getParserForType() {
        return PARSER;
    }

    public MetricsSample getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
