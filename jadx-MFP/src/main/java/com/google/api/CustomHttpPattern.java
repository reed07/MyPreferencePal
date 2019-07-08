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
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class CustomHttpPattern extends GeneratedMessageV3 implements CustomHttpPatternOrBuilder {
    private static final CustomHttpPattern DEFAULT_INSTANCE = new CustomHttpPattern();
    public static final int KIND_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<CustomHttpPattern> PARSER = new AbstractParser<CustomHttpPattern>() {
        public CustomHttpPattern parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CustomHttpPattern(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PATH_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object kind_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object path_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements CustomHttpPatternOrBuilder {
        private Object kind_;
        private Object path_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_CustomHttpPattern_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_CustomHttpPattern_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomHttpPattern.class, Builder.class);
        }

        private Builder() {
            this.kind_ = "";
            this.path_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.kind_ = "";
            this.path_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            CustomHttpPattern.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.kind_ = "";
            this.path_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_CustomHttpPattern_descriptor;
        }

        public CustomHttpPattern getDefaultInstanceForType() {
            return CustomHttpPattern.getDefaultInstance();
        }

        public CustomHttpPattern build() {
            CustomHttpPattern buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public CustomHttpPattern buildPartial() {
            CustomHttpPattern customHttpPattern = new CustomHttpPattern((com.google.protobuf.GeneratedMessageV3.Builder) this);
            customHttpPattern.kind_ = this.kind_;
            customHttpPattern.path_ = this.path_;
            onBuilt();
            return customHttpPattern;
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
            if (message instanceof CustomHttpPattern) {
                return mergeFrom((CustomHttpPattern) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CustomHttpPattern customHttpPattern) {
            if (customHttpPattern == CustomHttpPattern.getDefaultInstance()) {
                return this;
            }
            if (!customHttpPattern.getKind().isEmpty()) {
                this.kind_ = customHttpPattern.kind_;
                onChanged();
            }
            if (!customHttpPattern.getPath().isEmpty()) {
                this.path_ = customHttpPattern.path_;
                onChanged();
            }
            mergeUnknownFields(customHttpPattern.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.CustomHttpPattern.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.CustomHttpPattern.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.CustomHttpPattern r3 = (com.google.api.CustomHttpPattern) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.CustomHttpPattern r4 = (com.google.api.CustomHttpPattern) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.CustomHttpPattern.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.CustomHttpPattern$Builder");
        }

        public String getKind() {
            Object obj = this.kind_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.kind_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getKindBytes() {
            Object obj = this.kind_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.kind_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setKind(String str) {
            if (str != null) {
                this.kind_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearKind() {
            this.kind_ = CustomHttpPattern.getDefaultInstance().getKind();
            onChanged();
            return this;
        }

        public Builder setKindBytes(ByteString byteString) {
            if (byteString != null) {
                CustomHttpPattern.checkByteStringIsUtf8(byteString);
                this.kind_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getPath() {
            Object obj = this.path_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.path_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getPathBytes() {
            Object obj = this.path_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.path_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPath(String str) {
            if (str != null) {
                this.path_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPath() {
            this.path_ = CustomHttpPattern.getDefaultInstance().getPath();
            onChanged();
            return this;
        }

        public Builder setPathBytes(ByteString byteString) {
            if (byteString != null) {
                CustomHttpPattern.checkByteStringIsUtf8(byteString);
                this.path_ = byteString;
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

    private CustomHttpPattern(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private CustomHttpPattern() {
        this.memoizedIsInitialized = -1;
        this.kind_ = "";
        this.path_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CustomHttpPattern(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.kind_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.path_ = codedInputStream.readStringRequireUtf8();
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
        return HttpProto.internal_static_google_api_CustomHttpPattern_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpProto.internal_static_google_api_CustomHttpPattern_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomHttpPattern.class, Builder.class);
    }

    public String getKind() {
        Object obj = this.kind_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.kind_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getKindBytes() {
        Object obj = this.kind_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.kind_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getPath() {
        Object obj = this.path_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.path_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getPathBytes() {
        Object obj = this.path_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.path_ = copyFromUtf8;
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
        if (!getKindBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.kind_);
        }
        if (!getPathBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.path_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getKindBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.kind_);
        }
        if (!getPathBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.path_);
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
        if (!(obj instanceof CustomHttpPattern)) {
            return super.equals(obj);
        }
        CustomHttpPattern customHttpPattern = (CustomHttpPattern) obj;
        if (!((getKind().equals(customHttpPattern.getKind())) && getPath().equals(customHttpPattern.getPath())) || !this.unknownFields.equals(customHttpPattern.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKind().hashCode()) * 37) + 2) * 53) + getPath().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static CustomHttpPattern parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CustomHttpPattern) PARSER.parseFrom(byteBuffer);
    }

    public static CustomHttpPattern parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CustomHttpPattern) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CustomHttpPattern parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CustomHttpPattern) PARSER.parseFrom(byteString);
    }

    public static CustomHttpPattern parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CustomHttpPattern) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CustomHttpPattern parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CustomHttpPattern) PARSER.parseFrom(bArr);
    }

    public static CustomHttpPattern parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CustomHttpPattern) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CustomHttpPattern parseFrom(InputStream inputStream) throws IOException {
        return (CustomHttpPattern) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CustomHttpPattern parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CustomHttpPattern) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CustomHttpPattern parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CustomHttpPattern) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CustomHttpPattern parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CustomHttpPattern) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CustomHttpPattern parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CustomHttpPattern) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CustomHttpPattern parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CustomHttpPattern) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CustomHttpPattern customHttpPattern) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(customHttpPattern);
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

    public static CustomHttpPattern getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CustomHttpPattern> parser() {
        return PARSER;
    }

    public Parser<CustomHttpPattern> getParserForType() {
        return PARSER;
    }

    public CustomHttpPattern getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
