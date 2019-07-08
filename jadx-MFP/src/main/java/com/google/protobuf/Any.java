package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Any extends GeneratedMessageV3 implements AnyOrBuilder {
    private static final Any DEFAULT_INSTANCE = new Any();
    /* access modifiers changed from: private */
    public static final Parser<Any> PARSER = new AbstractParser<Any>() {
        public Any parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Any(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private volatile Message cachedUnpackValue;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object typeUrl_;
    /* access modifiers changed from: private */
    public ByteString value_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AnyOrBuilder {
        private Object typeUrl_;
        private ByteString value_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AnyProto.internal_static_google_protobuf_Any_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AnyProto.internal_static_google_protobuf_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
        }

        private Builder() {
            this.typeUrl_ = "";
            this.value_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.typeUrl_ = "";
            this.value_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.typeUrl_ = "";
            this.value_ = ByteString.EMPTY;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AnyProto.internal_static_google_protobuf_Any_descriptor;
        }

        public Any getDefaultInstanceForType() {
            return Any.getDefaultInstance();
        }

        public Any build() {
            Any buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Any buildPartial() {
            Any any = new Any((com.google.protobuf.GeneratedMessageV3.Builder) this);
            any.typeUrl_ = this.typeUrl_;
            any.value_ = this.value_;
            onBuilt();
            return any;
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
            if (message instanceof Any) {
                return mergeFrom((Any) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Any any) {
            if (any == Any.getDefaultInstance()) {
                return this;
            }
            if (!any.getTypeUrl().isEmpty()) {
                this.typeUrl_ = any.typeUrl_;
                onChanged();
            }
            if (any.getValue() != ByteString.EMPTY) {
                setValue(any.getValue());
            }
            mergeUnknownFields(any.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.protobuf.Any.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Any.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.protobuf.Any r3 = (com.google.protobuf.Any) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.protobuf.Any r4 = (com.google.protobuf.Any) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Any.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Any$Builder");
        }

        public String getTypeUrl() {
            Object obj = this.typeUrl_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.typeUrl_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getTypeUrlBytes() {
            Object obj = this.typeUrl_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.typeUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setTypeUrl(String str) {
            if (str != null) {
                this.typeUrl_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearTypeUrl() {
            this.typeUrl_ = Any.getDefaultInstance().getTypeUrl();
            onChanged();
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.typeUrl_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public ByteString getValue() {
            return this.value_;
        }

        public Builder setValue(ByteString byteString) {
            if (byteString != null) {
                this.value_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearValue() {
            this.value_ = Any.getDefaultInstance().getValue();
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

    private Any(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Any() {
        this.memoizedIsInitialized = -1;
        this.typeUrl_ = "";
        this.value_ = ByteString.EMPTY;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Any(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.typeUrl_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.value_ = codedInputStream.readBytes();
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
        return AnyProto.internal_static_google_protobuf_Any_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AnyProto.internal_static_google_protobuf_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
    }

    private static String getTypeUrl(String str, Descriptor descriptor) {
        if (str.endsWith("/")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(descriptor.getFullName());
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("/");
        sb2.append(descriptor.getFullName());
        return sb2.toString();
    }

    private static String getTypeNameFromTypeUrl(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf == -1) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }

    public static <T extends Message> Any pack(T t) {
        return newBuilder().setTypeUrl(getTypeUrl("type.googleapis.com", t.getDescriptorForType())).setValue(t.toByteString()).build();
    }

    public static <T extends Message> Any pack(T t, String str) {
        return newBuilder().setTypeUrl(getTypeUrl(str, t.getDescriptorForType())).setValue(t.toByteString()).build();
    }

    public <T extends Message> boolean is(Class<T> cls) {
        return getTypeNameFromTypeUrl(getTypeUrl()).equals(((Message) Internal.getDefaultInstance(cls)).getDescriptorForType().getFullName());
    }

    public <T extends Message> T unpack(Class<T> cls) throws InvalidProtocolBufferException {
        if (!is(cls)) {
            throw new InvalidProtocolBufferException("Type of the Any message does not match the given class.");
        } else if (this.cachedUnpackValue != null) {
            return this.cachedUnpackValue;
        } else {
            T t = (Message) ((Message) Internal.getDefaultInstance(cls)).getParserForType().parseFrom(getValue());
            this.cachedUnpackValue = t;
            return t;
        }
    }

    public String getTypeUrl() {
        Object obj = this.typeUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.typeUrl_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getTypeUrlBytes() {
        Object obj = this.typeUrl_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.typeUrl_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public ByteString getValue() {
        return this.value_;
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
        if (!getTypeUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.typeUrl_);
        }
        if (!this.value_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.value_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getTypeUrlBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.typeUrl_);
        }
        if (!this.value_.isEmpty()) {
            i2 += CodedOutputStream.computeBytesSize(2, this.value_);
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
        if (!(obj instanceof Any)) {
            return super.equals(obj);
        }
        Any any = (Any) obj;
        if (!((getTypeUrl().equals(any.getTypeUrl())) && getValue().equals(any.getValue())) || !this.unknownFields.equals(any.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTypeUrl().hashCode()) * 37) + 2) * 53) + getValue().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Any parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Any) PARSER.parseFrom(byteBuffer);
    }

    public static Any parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Any) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Any parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Any) PARSER.parseFrom(byteString);
    }

    public static Any parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Any) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Any parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Any) PARSER.parseFrom(bArr);
    }

    public static Any parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Any) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Any parseFrom(InputStream inputStream) throws IOException {
        return (Any) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Any parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Any) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Any parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Any) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Any parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Any) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Any parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Any) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Any parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Any) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Any any) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(any);
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

    public static Any getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Any> parser() {
        return PARSER;
    }

    public Parser<Any> getParserForType() {
        return PARSER;
    }

    public Any getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
