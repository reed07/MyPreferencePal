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

public final class AuthProvider extends GeneratedMessageV3 implements AuthProviderOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 4;
    public static final int AUTHORIZATION_URL_FIELD_NUMBER = 5;
    private static final AuthProvider DEFAULT_INSTANCE = new AuthProvider();
    public static final int ID_FIELD_NUMBER = 1;
    public static final int ISSUER_FIELD_NUMBER = 2;
    public static final int JWKS_URI_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<AuthProvider> PARSER = new AbstractParser<AuthProvider>() {
        public AuthProvider parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthProvider(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object audiences_;
    /* access modifiers changed from: private */
    public volatile Object authorizationUrl_;
    /* access modifiers changed from: private */
    public volatile Object id_;
    /* access modifiers changed from: private */
    public volatile Object issuer_;
    /* access modifiers changed from: private */
    public volatile Object jwksUri_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthProviderOrBuilder {
        private Object audiences_;
        private Object authorizationUrl_;
        private Object id_;
        private Object issuer_;
        private Object jwksUri_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
        }

        private Builder() {
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            AuthProvider.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        public AuthProvider getDefaultInstanceForType() {
            return AuthProvider.getDefaultInstance();
        }

        public AuthProvider build() {
            AuthProvider buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuthProvider buildPartial() {
            AuthProvider authProvider = new AuthProvider((com.google.protobuf.GeneratedMessageV3.Builder) this);
            authProvider.id_ = this.id_;
            authProvider.issuer_ = this.issuer_;
            authProvider.jwksUri_ = this.jwksUri_;
            authProvider.audiences_ = this.audiences_;
            authProvider.authorizationUrl_ = this.authorizationUrl_;
            onBuilt();
            return authProvider;
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
            if (message instanceof AuthProvider) {
                return mergeFrom((AuthProvider) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthProvider authProvider) {
            if (authProvider == AuthProvider.getDefaultInstance()) {
                return this;
            }
            if (!authProvider.getId().isEmpty()) {
                this.id_ = authProvider.id_;
                onChanged();
            }
            if (!authProvider.getIssuer().isEmpty()) {
                this.issuer_ = authProvider.issuer_;
                onChanged();
            }
            if (!authProvider.getJwksUri().isEmpty()) {
                this.jwksUri_ = authProvider.jwksUri_;
                onChanged();
            }
            if (!authProvider.getAudiences().isEmpty()) {
                this.audiences_ = authProvider.audiences_;
                onChanged();
            }
            if (!authProvider.getAuthorizationUrl().isEmpty()) {
                this.authorizationUrl_ = authProvider.authorizationUrl_;
                onChanged();
            }
            mergeUnknownFields(authProvider.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.AuthProvider.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.AuthProvider.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.AuthProvider r3 = (com.google.api.AuthProvider) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.AuthProvider r4 = (com.google.api.AuthProvider) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.AuthProvider.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.AuthProvider$Builder");
        }

        public String getId() {
            Object obj = this.id_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.id_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.id_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setId(String str) {
            if (str != null) {
                this.id_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearId() {
            this.id_ = AuthProvider.getDefaultInstance().getId();
            onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.id_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getIssuer() {
            Object obj = this.issuer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.issuer_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getIssuerBytes() {
            Object obj = this.issuer_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.issuer_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setIssuer(String str) {
            if (str != null) {
                this.issuer_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearIssuer() {
            this.issuer_ = AuthProvider.getDefaultInstance().getIssuer();
            onChanged();
            return this;
        }

        public Builder setIssuerBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.issuer_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getJwksUri() {
            Object obj = this.jwksUri_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.jwksUri_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getJwksUriBytes() {
            Object obj = this.jwksUri_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.jwksUri_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setJwksUri(String str) {
            if (str != null) {
                this.jwksUri_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearJwksUri() {
            this.jwksUri_ = AuthProvider.getDefaultInstance().getJwksUri();
            onChanged();
            return this;
        }

        public Builder setJwksUriBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.jwksUri_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getAudiences() {
            Object obj = this.audiences_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.audiences_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getAudiencesBytes() {
            Object obj = this.audiences_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.audiences_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setAudiences(String str) {
            if (str != null) {
                this.audiences_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearAudiences() {
            this.audiences_ = AuthProvider.getDefaultInstance().getAudiences();
            onChanged();
            return this;
        }

        public Builder setAudiencesBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.audiences_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getAuthorizationUrl() {
            Object obj = this.authorizationUrl_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.authorizationUrl_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getAuthorizationUrlBytes() {
            Object obj = this.authorizationUrl_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.authorizationUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setAuthorizationUrl(String str) {
            if (str != null) {
                this.authorizationUrl_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearAuthorizationUrl() {
            this.authorizationUrl_ = AuthProvider.getDefaultInstance().getAuthorizationUrl();
            onChanged();
            return this;
        }

        public Builder setAuthorizationUrlBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.authorizationUrl_ = byteString;
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

    private AuthProvider(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuthProvider() {
        this.memoizedIsInitialized = -1;
        this.id_ = "";
        this.issuer_ = "";
        this.jwksUri_ = "";
        this.audiences_ = "";
        this.authorizationUrl_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthProvider(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.id_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.issuer_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        this.jwksUri_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 34) {
                        this.audiences_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 42) {
                        this.authorizationUrl_ = codedInputStream.readStringRequireUtf8();
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
        return AuthProto.internal_static_google_api_AuthProvider_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
    }

    public String getId() {
        Object obj = this.id_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.id_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getIdBytes() {
        Object obj = this.id_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.id_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getIssuer() {
        Object obj = this.issuer_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.issuer_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getIssuerBytes() {
        Object obj = this.issuer_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.issuer_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getJwksUri() {
        Object obj = this.jwksUri_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.jwksUri_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getJwksUriBytes() {
        Object obj = this.jwksUri_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.jwksUri_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getAudiences() {
        Object obj = this.audiences_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.audiences_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getAudiencesBytes() {
        Object obj = this.audiences_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.audiences_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getAuthorizationUrl() {
        Object obj = this.authorizationUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.authorizationUrl_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getAuthorizationUrlBytes() {
        Object obj = this.authorizationUrl_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.authorizationUrl_ = copyFromUtf8;
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
        if (!getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.id_);
        }
        if (!getIssuerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.issuer_);
        }
        if (!getJwksUriBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.jwksUri_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.audiences_);
        }
        if (!getAuthorizationUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.authorizationUrl_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.id_);
        }
        if (!getIssuerBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.issuer_);
        }
        if (!getJwksUriBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.jwksUri_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.audiences_);
        }
        if (!getAuthorizationUrlBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(5, this.authorizationUrl_);
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
        if (!(obj instanceof AuthProvider)) {
            return super.equals(obj);
        }
        AuthProvider authProvider = (AuthProvider) obj;
        if (!(((((getId().equals(authProvider.getId())) && getIssuer().equals(authProvider.getIssuer())) && getJwksUri().equals(authProvider.getJwksUri())) && getAudiences().equals(authProvider.getAudiences())) && getAuthorizationUrl().equals(authProvider.getAuthorizationUrl())) || !this.unknownFields.equals(authProvider.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getId().hashCode()) * 37) + 2) * 53) + getIssuer().hashCode()) * 37) + 3) * 53) + getJwksUri().hashCode()) * 37) + 4) * 53) + getAudiences().hashCode()) * 37) + 5) * 53) + getAuthorizationUrl().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static AuthProvider parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteBuffer);
    }

    public static AuthProvider parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteString);
    }

    public static AuthProvider parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(bArr);
    }

    public static AuthProvider parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(InputStream inputStream) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthProvider parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthProvider authProvider) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authProvider);
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

    public static AuthProvider getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthProvider> parser() {
        return PARSER;
    }

    public Parser<AuthProvider> getParserForType() {
        return PARSER;
    }

    public AuthProvider getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
