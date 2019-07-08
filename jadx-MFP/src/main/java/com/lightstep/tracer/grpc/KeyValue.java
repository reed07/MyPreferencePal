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

public final class KeyValue extends GeneratedMessageV3 implements KeyValueOrBuilder {
    private static final KeyValue DEFAULT_INSTANCE = new KeyValue();
    /* access modifiers changed from: private */
    public static final Parser<KeyValue> PARSER = new AbstractParser<KeyValue>() {
        public KeyValue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new KeyValue(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object key_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public int valueCase_;
    /* access modifiers changed from: private */
    public Object value_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements KeyValueOrBuilder {
        private Object key_;
        private int valueCase_;
        private Object value_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_KeyValue_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyValue.class, Builder.class);
        }

        private Builder() {
            this.valueCase_ = 0;
            this.key_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.valueCase_ = 0;
            this.key_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            KeyValue.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.key_ = "";
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_KeyValue_descriptor;
        }

        public KeyValue getDefaultInstanceForType() {
            return KeyValue.getDefaultInstance();
        }

        public KeyValue build() {
            KeyValue buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public KeyValue buildPartial() {
            KeyValue keyValue = new KeyValue((com.google.protobuf.GeneratedMessageV3.Builder) this);
            keyValue.key_ = this.key_;
            if (this.valueCase_ == 2) {
                keyValue.value_ = this.value_;
            }
            if (this.valueCase_ == 3) {
                keyValue.value_ = this.value_;
            }
            if (this.valueCase_ == 4) {
                keyValue.value_ = this.value_;
            }
            if (this.valueCase_ == 5) {
                keyValue.value_ = this.value_;
            }
            if (this.valueCase_ == 6) {
                keyValue.value_ = this.value_;
            }
            keyValue.valueCase_ = this.valueCase_;
            onBuilt();
            return keyValue;
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
            if (message instanceof KeyValue) {
                return mergeFrom((KeyValue) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(KeyValue keyValue) {
            if (keyValue == KeyValue.getDefaultInstance()) {
                return this;
            }
            if (!keyValue.getKey().isEmpty()) {
                this.key_ = keyValue.key_;
                onChanged();
            }
            switch (keyValue.getValueCase()) {
                case STRING_VALUE:
                    this.valueCase_ = 2;
                    this.value_ = keyValue.value_;
                    onChanged();
                    break;
                case INT_VALUE:
                    setIntValue(keyValue.getIntValue());
                    break;
                case DOUBLE_VALUE:
                    setDoubleValue(keyValue.getDoubleValue());
                    break;
                case BOOL_VALUE:
                    setBoolValue(keyValue.getBoolValue());
                    break;
                case JSON_VALUE:
                    this.valueCase_ = 6;
                    this.value_ = keyValue.value_;
                    onChanged();
                    break;
            }
            mergeUnknownFields(keyValue.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.KeyValue.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.KeyValue.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.KeyValue r3 = (com.lightstep.tracer.grpc.KeyValue) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.KeyValue r4 = (com.lightstep.tracer.grpc.KeyValue) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.KeyValue.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.KeyValue$Builder");
        }

        public Builder setKey(String str) {
            if (str != null) {
                this.key_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setStringValue(String str) {
            if (str != null) {
                this.valueCase_ = 2;
                this.value_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setIntValue(long j) {
            this.valueCase_ = 3;
            this.value_ = Long.valueOf(j);
            onChanged();
            return this;
        }

        public Builder setDoubleValue(double d) {
            this.valueCase_ = 4;
            this.value_ = Double.valueOf(d);
            onChanged();
            return this;
        }

        public Builder setBoolValue(boolean z) {
            this.valueCase_ = 5;
            this.value_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder setJsonValue(String str) {
            if (str != null) {
                this.valueCase_ = 6;
                this.value_ = str;
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

    public enum ValueCase implements EnumLite {
        STRING_VALUE(2),
        INT_VALUE(3),
        DOUBLE_VALUE(4),
        BOOL_VALUE(5),
        JSON_VALUE(6),
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
                    return STRING_VALUE;
                case 3:
                    return INT_VALUE;
                case 4:
                    return DOUBLE_VALUE;
                case 5:
                    return BOOL_VALUE;
                case 6:
                    return JSON_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    private KeyValue(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = -1;
    }

    private KeyValue() {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = -1;
        this.key_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private KeyValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.key_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                        this.valueCase_ = 2;
                        this.value_ = readStringRequireUtf8;
                    } else if (readTag == 24) {
                        this.valueCase_ = 3;
                        this.value_ = Long.valueOf(codedInputStream.readInt64());
                    } else if (readTag == 33) {
                        this.valueCase_ = 4;
                        this.value_ = Double.valueOf(codedInputStream.readDouble());
                    } else if (readTag == 40) {
                        this.valueCase_ = 5;
                        this.value_ = Boolean.valueOf(codedInputStream.readBool());
                    } else if (readTag == 50) {
                        String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                        this.valueCase_ = 6;
                        this.value_ = readStringRequireUtf82;
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
        return Collector.internal_static_lightstep_collector_KeyValue_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_KeyValue_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyValue.class, Builder.class);
    }

    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    public String getKey() {
        Object obj = this.key_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.key_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getKeyBytes() {
        Object obj = this.key_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.key_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getStringValue() {
        Object obj = "";
        if (this.valueCase_ == 2) {
            obj = this.value_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.valueCase_ == 2) {
            this.value_ = stringUtf8;
        }
        return stringUtf8;
    }

    public long getIntValue() {
        if (this.valueCase_ == 3) {
            return ((Long) this.value_).longValue();
        }
        return 0;
    }

    public double getDoubleValue() {
        if (this.valueCase_ == 4) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    public boolean getBoolValue() {
        if (this.valueCase_ == 5) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public String getJsonValue() {
        Object obj = "";
        if (this.valueCase_ == 6) {
            obj = this.value_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.valueCase_ == 6) {
            this.value_ = stringUtf8;
        }
        return stringUtf8;
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
        if (!getKeyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
        }
        if (this.valueCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
        }
        if (this.valueCase_ == 3) {
            codedOutputStream.writeInt64(3, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 4) {
            codedOutputStream.writeDouble(4, ((Double) this.value_).doubleValue());
        }
        if (this.valueCase_ == 5) {
            codedOutputStream.writeBool(5, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 6) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.value_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getKeyBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.key_);
        }
        if (this.valueCase_ == 2) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.value_);
        }
        if (this.valueCase_ == 3) {
            i2 += CodedOutputStream.computeInt64Size(3, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 4) {
            i2 += CodedOutputStream.computeDoubleSize(4, ((Double) this.value_).doubleValue());
        }
        if (this.valueCase_ == 5) {
            i2 += CodedOutputStream.computeBoolSize(5, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 6) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.value_);
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
        if (!(obj instanceof KeyValue)) {
            return super.equals(obj);
        }
        KeyValue keyValue = (KeyValue) obj;
        boolean z2 = (getKey().equals(keyValue.getKey())) && getValueCase().equals(keyValue.getValueCase());
        if (!z2) {
            return false;
        }
        switch (this.valueCase_) {
            case 2:
                if (z2 && getStringValue().equals(keyValue.getStringValue())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 3:
                if (z2 && getIntValue() == keyValue.getIntValue()) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 4:
                if (z2 && Double.doubleToLongBits(getDoubleValue()) == Double.doubleToLongBits(keyValue.getDoubleValue())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 5:
                if (z2 && getBoolValue() == keyValue.getBoolValue()) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 6:
                if (z2 && getJsonValue().equals(keyValue.getJsonValue())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
        }
        if (!z2 || !this.unknownFields.equals(keyValue.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKey().hashCode();
        switch (this.valueCase_) {
            case 2:
                hashCode = (((hashCode * 37) + 2) * 53) + getStringValue().hashCode();
                break;
            case 3:
                hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashLong(getIntValue());
                break;
            case 4:
                hashCode = (((hashCode * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getDoubleValue()));
                break;
            case 5:
                hashCode = (((hashCode * 37) + 5) * 53) + Internal.hashBoolean(getBoolValue());
                break;
            case 6:
                hashCode = (((hashCode * 37) + 6) * 53) + getJsonValue().hashCode();
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

    public static KeyValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyValue> parser() {
        return PARSER;
    }

    public Parser<KeyValue> getParserForType() {
        return PARSER;
    }

    public KeyValue getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
