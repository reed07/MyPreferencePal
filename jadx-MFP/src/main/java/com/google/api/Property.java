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

public final class Property extends GeneratedMessageV3 implements PropertyOrBuilder {
    private static final Property DEFAULT_INSTANCE = new Property();
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Property> PARSER = new AbstractParser<Property>() {
        public Property parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Property(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object description_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public int type_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements PropertyOrBuilder {
        private Object description_;
        private Object name_;
        private int type_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ConsumerProto.internal_static_google_api_Property_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ConsumerProto.internal_static_google_api_Property_fieldAccessorTable.ensureFieldAccessorsInitialized(Property.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.type_ = 0;
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.type_ = 0;
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Property.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.type_ = 0;
            this.description_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ConsumerProto.internal_static_google_api_Property_descriptor;
        }

        public Property getDefaultInstanceForType() {
            return Property.getDefaultInstance();
        }

        public Property build() {
            Property buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Property buildPartial() {
            Property property = new Property((com.google.protobuf.GeneratedMessageV3.Builder) this);
            property.name_ = this.name_;
            property.type_ = this.type_;
            property.description_ = this.description_;
            onBuilt();
            return property;
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
            if (message instanceof Property) {
                return mergeFrom((Property) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Property property) {
            if (property == Property.getDefaultInstance()) {
                return this;
            }
            if (!property.getName().isEmpty()) {
                this.name_ = property.name_;
                onChanged();
            }
            if (property.type_ != 0) {
                setTypeValue(property.getTypeValue());
            }
            if (!property.getDescription().isEmpty()) {
                this.description_ = property.description_;
                onChanged();
            }
            mergeUnknownFields(property.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Property.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Property.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Property r3 = (com.google.api.Property) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Property r4 = (com.google.api.Property) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Property.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Property$Builder");
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

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = Property.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Property.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public int getTypeValue() {
            return this.type_;
        }

        public Builder setTypeValue(int i) {
            this.type_ = i;
            onChanged();
            return this;
        }

        public PropertyType getType() {
            PropertyType valueOf = PropertyType.valueOf(this.type_);
            return valueOf == null ? PropertyType.UNRECOGNIZED : valueOf;
        }

        public Builder setType(PropertyType propertyType) {
            if (propertyType != null) {
                this.type_ = propertyType.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearType() {
            this.type_ = 0;
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
            this.description_ = Property.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                Property.checkByteStringIsUtf8(byteString);
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

    public enum PropertyType implements ProtocolMessageEnum {
        UNSPECIFIED(0),
        INT64(1),
        BOOL(2),
        STRING(3),
        DOUBLE(4),
        UNRECOGNIZED(-1);
        
        public static final int BOOL_VALUE = 2;
        public static final int DOUBLE_VALUE = 4;
        public static final int INT64_VALUE = 1;
        public static final int STRING_VALUE = 3;
        public static final int UNSPECIFIED_VALUE = 0;
        private static final PropertyType[] VALUES = null;
        private static final EnumLiteMap<PropertyType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<PropertyType>() {
                public PropertyType findValueByNumber(int i) {
                    return PropertyType.forNumber(i);
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
        public static PropertyType valueOf(int i) {
            return forNumber(i);
        }

        public static PropertyType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNSPECIFIED;
                case 1:
                    return INT64;
                case 2:
                    return BOOL;
                case 3:
                    return STRING;
                case 4:
                    return DOUBLE;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<PropertyType> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) Property.getDescriptor().getEnumTypes().get(0);
        }

        public static PropertyType valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private PropertyType(int i) {
            this.value = i;
        }
    }

    private Property(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Property() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.type_ = 0;
        this.description_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Property(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.type_ = codedInputStream.readEnum();
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
        return ConsumerProto.internal_static_google_api_Property_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ConsumerProto.internal_static_google_api_Property_fieldAccessorTable.ensureFieldAccessorsInitialized(Property.class, Builder.class);
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

    public int getTypeValue() {
        return this.type_;
    }

    public PropertyType getType() {
        PropertyType valueOf = PropertyType.valueOf(this.type_);
        return valueOf == null ? PropertyType.UNRECOGNIZED : valueOf;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.type_ != PropertyType.UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(2, this.type_);
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
        if (!getNameBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (this.type_ != PropertyType.UNSPECIFIED.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(2, this.type_);
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
        if (!(obj instanceof Property)) {
            return super.equals(obj);
        }
        Property property = (Property) obj;
        if (!(((getName().equals(property.getName())) && this.type_ == property.type_) && getDescription().equals(property.getDescription())) || !this.unknownFields.equals(property.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + this.type_) * 37) + 3) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Property parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Property) PARSER.parseFrom(byteBuffer);
    }

    public static Property parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Property) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Property parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Property) PARSER.parseFrom(byteString);
    }

    public static Property parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Property) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Property parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Property) PARSER.parseFrom(bArr);
    }

    public static Property parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Property) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Property parseFrom(InputStream inputStream) throws IOException {
        return (Property) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Property parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Property) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Property parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Property) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Property parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Property) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Property parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Property) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Property parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Property) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Property property) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(property);
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

    public static Property getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Property> parser() {
        return PARSER;
    }

    public Parser<Property> getParserForType() {
        return PARSER;
    }

    public Property getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
