package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LabelDescriptor extends GeneratedMessageV3 implements LabelDescriptorOrBuilder {
    private static final LabelDescriptor DEFAULT_INSTANCE = new LabelDescriptor();
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int KEY_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<LabelDescriptor> PARSER = new AbstractParser<LabelDescriptor>() {
        public LabelDescriptor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LabelDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUE_TYPE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object description_;
    /* access modifiers changed from: private */
    public volatile Object key_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public int valueType_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LabelDescriptorOrBuilder {
        private Object description_;
        private Object key_;
        private int valueType_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return LabelProto.internal_static_google_api_LabelDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LabelDescriptor.class, Builder.class);
        }

        private Builder() {
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            LabelDescriptor.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.key_ = "";
            this.valueType_ = 0;
            this.description_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
        }

        public LabelDescriptor getDefaultInstanceForType() {
            return LabelDescriptor.getDefaultInstance();
        }

        public LabelDescriptor build() {
            LabelDescriptor buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public LabelDescriptor buildPartial() {
            LabelDescriptor labelDescriptor = new LabelDescriptor((com.google.protobuf.GeneratedMessageV3.Builder) this);
            labelDescriptor.key_ = this.key_;
            labelDescriptor.valueType_ = this.valueType_;
            labelDescriptor.description_ = this.description_;
            onBuilt();
            return labelDescriptor;
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
            if (message instanceof LabelDescriptor) {
                return mergeFrom((LabelDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LabelDescriptor labelDescriptor) {
            if (labelDescriptor == LabelDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!labelDescriptor.getKey().isEmpty()) {
                this.key_ = labelDescriptor.key_;
                onChanged();
            }
            if (labelDescriptor.valueType_ != 0) {
                setValueTypeValue(labelDescriptor.getValueTypeValue());
            }
            if (!labelDescriptor.getDescription().isEmpty()) {
                this.description_ = labelDescriptor.description_;
                onChanged();
            }
            mergeUnknownFields(labelDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.LabelDescriptor.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.LabelDescriptor.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.LabelDescriptor r3 = (com.google.api.LabelDescriptor) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.LabelDescriptor r4 = (com.google.api.LabelDescriptor) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.LabelDescriptor.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.LabelDescriptor$Builder");
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

        public Builder setKey(String str) {
            if (str != null) {
                this.key_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearKey() {
            this.key_ = LabelDescriptor.getDefaultInstance().getKey();
            onChanged();
            return this;
        }

        public Builder setKeyBytes(ByteString byteString) {
            if (byteString != null) {
                LabelDescriptor.checkByteStringIsUtf8(byteString);
                this.key_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public int getValueTypeValue() {
            return this.valueType_;
        }

        public Builder setValueTypeValue(int i) {
            this.valueType_ = i;
            onChanged();
            return this;
        }

        public ValueType getValueType() {
            ValueType valueOf = ValueType.valueOf(this.valueType_);
            return valueOf == null ? ValueType.UNRECOGNIZED : valueOf;
        }

        public Builder setValueType(ValueType valueType) {
            if (valueType != null) {
                this.valueType_ = valueType.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearValueType() {
            this.valueType_ = 0;
            onChanged();
            return this;
        }

        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDescription(String str) {
            if (str != null) {
                this.description_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDescription() {
            this.description_ = LabelDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                LabelDescriptor.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
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

    public enum ValueType implements ProtocolMessageEnum {
        STRING(0),
        BOOL(1),
        INT64(2),
        UNRECOGNIZED(-1);
        
        public static final int BOOL_VALUE = 1;
        public static final int INT64_VALUE = 2;
        public static final int STRING_VALUE = 0;
        private static final ValueType[] VALUES = null;
        private static final EnumLiteMap<ValueType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<ValueType>() {
                public ValueType findValueByNumber(int i) {
                    return ValueType.forNumber(i);
                }
            };
            VALUES = values();
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static ValueType valueOf(int i) {
            return forNumber(i);
        }

        public static ValueType forNumber(int i) {
            switch (i) {
                case 0:
                    return STRING;
                case 1:
                    return BOOL;
                case 2:
                    return INT64;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<ValueType> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) LabelDescriptor.getDescriptor().getEnumTypes().get(0);
        }

        public static ValueType valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private ValueType(int i) {
            this.value = i;
        }
    }

    private LabelDescriptor(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private LabelDescriptor() {
        this.memoizedIsInitialized = -1;
        this.key_ = "";
        this.valueType_ = 0;
        this.description_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LabelDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 16) {
                        this.valueType_ = codedInputStream.readEnum();
                    } else if (readTag == 26) {
                        this.description_ = codedInputStream.readStringRequireUtf8();
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
        return LabelProto.internal_static_google_api_LabelDescriptor_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return LabelProto.internal_static_google_api_LabelDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(LabelDescriptor.class, Builder.class);
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

    public int getValueTypeValue() {
        return this.valueType_;
    }

    public ValueType getValueType() {
        ValueType valueOf = ValueType.valueOf(this.valueType_);
        return valueOf == null ? ValueType.UNRECOGNIZED : valueOf;
    }

    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.description_ = copyFromUtf8;
        return copyFromUtf8;
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
        if (this.valueType_ != ValueType.STRING.getNumber()) {
            codedOutputStream.writeEnum(2, this.valueType_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
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
        if (this.valueType_ != ValueType.STRING.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(2, this.valueType_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.description_);
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
        if (!(obj instanceof LabelDescriptor)) {
            return super.equals(obj);
        }
        LabelDescriptor labelDescriptor = (LabelDescriptor) obj;
        if (!(((getKey().equals(labelDescriptor.getKey())) && this.valueType_ == labelDescriptor.valueType_) && getDescription().equals(labelDescriptor.getDescription())) || !this.unknownFields.equals(labelDescriptor.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKey().hashCode()) * 37) + 2) * 53) + this.valueType_) * 37) + 3) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static LabelDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static LabelDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteString);
    }

    public static LabelDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(bArr);
    }

    public static LabelDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LabelDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(InputStream inputStream) throws IOException {
        return (LabelDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LabelDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LabelDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LabelDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (LabelDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LabelDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LabelDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LabelDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (LabelDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LabelDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LabelDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LabelDescriptor labelDescriptor) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(labelDescriptor);
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

    public static LabelDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LabelDescriptor> parser() {
        return PARSER;
    }

    public Parser<LabelDescriptor> getParserForType() {
        return PARSER;
    }

    public LabelDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
