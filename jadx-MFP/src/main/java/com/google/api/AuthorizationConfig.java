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

public final class AuthorizationConfig extends GeneratedMessageV3 implements AuthorizationConfigOrBuilder {
    private static final AuthorizationConfig DEFAULT_INSTANCE = new AuthorizationConfig();
    /* access modifiers changed from: private */
    public static final Parser<AuthorizationConfig> PARSER = new AbstractParser<AuthorizationConfig>() {
        public AuthorizationConfig parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthorizationConfig(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROVIDER_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object provider_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthorizationConfigOrBuilder {
        private Object provider_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationConfig.class, Builder.class);
        }

        private Builder() {
            this.provider_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.provider_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            AuthorizationConfig.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.provider_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_descriptor;
        }

        public AuthorizationConfig getDefaultInstanceForType() {
            return AuthorizationConfig.getDefaultInstance();
        }

        public AuthorizationConfig build() {
            AuthorizationConfig buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuthorizationConfig buildPartial() {
            AuthorizationConfig authorizationConfig = new AuthorizationConfig((com.google.protobuf.GeneratedMessageV3.Builder) this);
            authorizationConfig.provider_ = this.provider_;
            onBuilt();
            return authorizationConfig;
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
            if (message instanceof AuthorizationConfig) {
                return mergeFrom((AuthorizationConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthorizationConfig authorizationConfig) {
            if (authorizationConfig == AuthorizationConfig.getDefaultInstance()) {
                return this;
            }
            if (!authorizationConfig.getProvider().isEmpty()) {
                this.provider_ = authorizationConfig.provider_;
                onChanged();
            }
            mergeUnknownFields(authorizationConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.AuthorizationConfig.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.AuthorizationConfig.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.AuthorizationConfig r3 = (com.google.api.AuthorizationConfig) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.AuthorizationConfig r4 = (com.google.api.AuthorizationConfig) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.AuthorizationConfig.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.AuthorizationConfig$Builder");
        }

        public String getProvider() {
            Object obj = this.provider_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.provider_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getProviderBytes() {
            Object obj = this.provider_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.provider_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setProvider(String str) {
            if (str != null) {
                this.provider_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearProvider() {
            this.provider_ = AuthorizationConfig.getDefaultInstance().getProvider();
            onChanged();
            return this;
        }

        public Builder setProviderBytes(ByteString byteString) {
            if (byteString != null) {
                AuthorizationConfig.checkByteStringIsUtf8(byteString);
                this.provider_ = byteString;
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

    private AuthorizationConfig(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuthorizationConfig() {
        this.memoizedIsInitialized = -1;
        this.provider_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthorizationConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.provider_ = codedInputStream.readStringRequireUtf8();
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
        return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthorizationConfigProto.internal_static_google_api_AuthorizationConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationConfig.class, Builder.class);
    }

    public String getProvider() {
        Object obj = this.provider_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.provider_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getProviderBytes() {
        Object obj = this.provider_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.provider_ = copyFromUtf8;
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
        if (!getProviderBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.provider_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getProviderBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.provider_);
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
        if (!(obj instanceof AuthorizationConfig)) {
            return super.equals(obj);
        }
        AuthorizationConfig authorizationConfig = (AuthorizationConfig) obj;
        if (!(getProvider().equals(authorizationConfig.getProvider())) || !this.unknownFields.equals(authorizationConfig.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getProvider().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static AuthorizationConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthorizationConfig) PARSER.parseFrom(byteBuffer);
    }

    public static AuthorizationConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationConfig) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthorizationConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthorizationConfig) PARSER.parseFrom(byteString);
    }

    public static AuthorizationConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationConfig) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthorizationConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthorizationConfig) PARSER.parseFrom(bArr);
    }

    public static AuthorizationConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationConfig) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthorizationConfig parseFrom(InputStream inputStream) throws IOException {
        return (AuthorizationConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthorizationConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthorizationConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthorizationConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthorizationConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthorizationConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthorizationConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthorizationConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthorizationConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthorizationConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthorizationConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthorizationConfig authorizationConfig) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authorizationConfig);
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

    public static AuthorizationConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthorizationConfig> parser() {
        return PARSER;
    }

    public Parser<AuthorizationConfig> getParserForType() {
        return PARSER;
    }

    public AuthorizationConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
