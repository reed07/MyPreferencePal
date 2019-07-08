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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Experimental extends GeneratedMessageV3 implements ExperimentalOrBuilder {
    public static final int AUTHORIZATION_FIELD_NUMBER = 8;
    private static final Experimental DEFAULT_INSTANCE = new Experimental();
    /* access modifiers changed from: private */
    public static final Parser<Experimental> PARSER = new AbstractParser<Experimental>() {
        public Experimental parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Experimental(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public AuthorizationConfig authorization_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ExperimentalOrBuilder {
        private SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> authorizationBuilder_;
        private AuthorizationConfig authorization_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ExperimentalProto.internal_static_google_api_Experimental_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ExperimentalProto.internal_static_google_api_Experimental_fieldAccessorTable.ensureFieldAccessorsInitialized(Experimental.class, Builder.class);
        }

        private Builder() {
            this.authorization_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.authorization_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Experimental.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            if (this.authorizationBuilder_ == null) {
                this.authorization_ = null;
            } else {
                this.authorization_ = null;
                this.authorizationBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ExperimentalProto.internal_static_google_api_Experimental_descriptor;
        }

        public Experimental getDefaultInstanceForType() {
            return Experimental.getDefaultInstance();
        }

        public Experimental build() {
            Experimental buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Experimental buildPartial() {
            Experimental experimental = new Experimental((com.google.protobuf.GeneratedMessageV3.Builder) this);
            SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> singleFieldBuilderV3 = this.authorizationBuilder_;
            if (singleFieldBuilderV3 == null) {
                experimental.authorization_ = this.authorization_;
            } else {
                experimental.authorization_ = (AuthorizationConfig) singleFieldBuilderV3.build();
            }
            onBuilt();
            return experimental;
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
            if (message instanceof Experimental) {
                return mergeFrom((Experimental) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Experimental experimental) {
            if (experimental == Experimental.getDefaultInstance()) {
                return this;
            }
            if (experimental.hasAuthorization()) {
                mergeAuthorization(experimental.getAuthorization());
            }
            mergeUnknownFields(experimental.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Experimental.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Experimental.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Experimental r3 = (com.google.api.Experimental) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Experimental r4 = (com.google.api.Experimental) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Experimental.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Experimental$Builder");
        }

        public boolean hasAuthorization() {
            return (this.authorizationBuilder_ == null && this.authorization_ == null) ? false : true;
        }

        public AuthorizationConfig getAuthorization() {
            SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> singleFieldBuilderV3 = this.authorizationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AuthorizationConfig) singleFieldBuilderV3.getMessage();
            }
            AuthorizationConfig authorizationConfig = this.authorization_;
            if (authorizationConfig == null) {
                authorizationConfig = AuthorizationConfig.getDefaultInstance();
            }
            return authorizationConfig;
        }

        public Builder setAuthorization(AuthorizationConfig authorizationConfig) {
            SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> singleFieldBuilderV3 = this.authorizationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(authorizationConfig);
            } else if (authorizationConfig != null) {
                this.authorization_ = authorizationConfig;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAuthorization(com.google.api.AuthorizationConfig.Builder builder) {
            SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> singleFieldBuilderV3 = this.authorizationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authorization_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAuthorization(AuthorizationConfig authorizationConfig) {
            SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> singleFieldBuilderV3 = this.authorizationBuilder_;
            if (singleFieldBuilderV3 == null) {
                AuthorizationConfig authorizationConfig2 = this.authorization_;
                if (authorizationConfig2 != null) {
                    this.authorization_ = AuthorizationConfig.newBuilder(authorizationConfig2).mergeFrom(authorizationConfig).buildPartial();
                } else {
                    this.authorization_ = authorizationConfig;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(authorizationConfig);
            }
            return this;
        }

        public Builder clearAuthorization() {
            if (this.authorizationBuilder_ == null) {
                this.authorization_ = null;
                onChanged();
            } else {
                this.authorization_ = null;
                this.authorizationBuilder_ = null;
            }
            return this;
        }

        public com.google.api.AuthorizationConfig.Builder getAuthorizationBuilder() {
            onChanged();
            return (com.google.api.AuthorizationConfig.Builder) getAuthorizationFieldBuilder().getBuilder();
        }

        public AuthorizationConfigOrBuilder getAuthorizationOrBuilder() {
            SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> singleFieldBuilderV3 = this.authorizationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AuthorizationConfigOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            AuthorizationConfig authorizationConfig = this.authorization_;
            if (authorizationConfig == null) {
                authorizationConfig = AuthorizationConfig.getDefaultInstance();
            }
            return authorizationConfig;
        }

        private SingleFieldBuilderV3<AuthorizationConfig, com.google.api.AuthorizationConfig.Builder, AuthorizationConfigOrBuilder> getAuthorizationFieldBuilder() {
            if (this.authorizationBuilder_ == null) {
                this.authorizationBuilder_ = new SingleFieldBuilderV3<>(getAuthorization(), getParentForChildren(), isClean());
                this.authorization_ = null;
            }
            return this.authorizationBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Experimental(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Experimental() {
        this.memoizedIsInitialized = -1;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Experimental(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 66) {
                        com.google.api.AuthorizationConfig.Builder builder = null;
                        if (this.authorization_ != null) {
                            builder = this.authorization_.toBuilder();
                        }
                        this.authorization_ = (AuthorizationConfig) codedInputStream.readMessage(AuthorizationConfig.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.authorization_);
                            this.authorization_ = builder.buildPartial();
                        }
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
        return ExperimentalProto.internal_static_google_api_Experimental_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ExperimentalProto.internal_static_google_api_Experimental_fieldAccessorTable.ensureFieldAccessorsInitialized(Experimental.class, Builder.class);
    }

    public boolean hasAuthorization() {
        return this.authorization_ != null;
    }

    public AuthorizationConfig getAuthorization() {
        AuthorizationConfig authorizationConfig = this.authorization_;
        return authorizationConfig == null ? AuthorizationConfig.getDefaultInstance() : authorizationConfig;
    }

    public AuthorizationConfigOrBuilder getAuthorizationOrBuilder() {
        return getAuthorization();
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
        if (this.authorization_ != null) {
            codedOutputStream.writeMessage(8, getAuthorization());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.authorization_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(8, getAuthorization());
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
        if (!(obj instanceof Experimental)) {
            return super.equals(obj);
        }
        Experimental experimental = (Experimental) obj;
        boolean z2 = hasAuthorization() == experimental.hasAuthorization();
        if (hasAuthorization()) {
            z2 = z2 && getAuthorization().equals(experimental.getAuthorization());
        }
        if (!z2 || !this.unknownFields.equals(experimental.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasAuthorization()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getAuthorization().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Experimental parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Experimental) PARSER.parseFrom(byteBuffer);
    }

    public static Experimental parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Experimental) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Experimental parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Experimental) PARSER.parseFrom(byteString);
    }

    public static Experimental parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Experimental) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Experimental parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Experimental) PARSER.parseFrom(bArr);
    }

    public static Experimental parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Experimental) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Experimental parseFrom(InputStream inputStream) throws IOException {
        return (Experimental) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Experimental parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Experimental) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Experimental parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Experimental) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Experimental parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Experimental) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Experimental parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Experimental) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Experimental parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Experimental) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Experimental experimental) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(experimental);
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

    public static Experimental getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Experimental> parser() {
        return PARSER;
    }

    public Parser<Experimental> getParserForType() {
        return PARSER;
    }

    public Experimental getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
