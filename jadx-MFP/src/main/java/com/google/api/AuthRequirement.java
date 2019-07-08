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

public final class AuthRequirement extends GeneratedMessageV3 implements AuthRequirementOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 2;
    private static final AuthRequirement DEFAULT_INSTANCE = new AuthRequirement();
    /* access modifiers changed from: private */
    public static final Parser<AuthRequirement> PARSER = new AbstractParser<AuthRequirement>() {
        public AuthRequirement parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthRequirement(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROVIDER_ID_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object audiences_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object providerId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthRequirementOrBuilder {
        private Object audiences_;
        private Object providerId_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthRequirement_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthRequirement_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthRequirement.class, Builder.class);
        }

        private Builder() {
            this.providerId_ = "";
            this.audiences_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.providerId_ = "";
            this.audiences_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            AuthRequirement.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.providerId_ = "";
            this.audiences_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthRequirement_descriptor;
        }

        public AuthRequirement getDefaultInstanceForType() {
            return AuthRequirement.getDefaultInstance();
        }

        public AuthRequirement build() {
            AuthRequirement buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuthRequirement buildPartial() {
            AuthRequirement authRequirement = new AuthRequirement((com.google.protobuf.GeneratedMessageV3.Builder) this);
            authRequirement.providerId_ = this.providerId_;
            authRequirement.audiences_ = this.audiences_;
            onBuilt();
            return authRequirement;
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
            if (message instanceof AuthRequirement) {
                return mergeFrom((AuthRequirement) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthRequirement authRequirement) {
            if (authRequirement == AuthRequirement.getDefaultInstance()) {
                return this;
            }
            if (!authRequirement.getProviderId().isEmpty()) {
                this.providerId_ = authRequirement.providerId_;
                onChanged();
            }
            if (!authRequirement.getAudiences().isEmpty()) {
                this.audiences_ = authRequirement.audiences_;
                onChanged();
            }
            mergeUnknownFields(authRequirement.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.AuthRequirement.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.AuthRequirement.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.AuthRequirement r3 = (com.google.api.AuthRequirement) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.AuthRequirement r4 = (com.google.api.AuthRequirement) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.AuthRequirement.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.AuthRequirement$Builder");
        }

        public String getProviderId() {
            Object obj = this.providerId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.providerId_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getProviderIdBytes() {
            Object obj = this.providerId_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.providerId_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setProviderId(String str) {
            if (str != null) {
                this.providerId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearProviderId() {
            this.providerId_ = AuthRequirement.getDefaultInstance().getProviderId();
            onChanged();
            return this;
        }

        public Builder setProviderIdBytes(ByteString byteString) {
            if (byteString != null) {
                AuthRequirement.checkByteStringIsUtf8(byteString);
                this.providerId_ = byteString;
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
            this.audiences_ = AuthRequirement.getDefaultInstance().getAudiences();
            onChanged();
            return this;
        }

        public Builder setAudiencesBytes(ByteString byteString) {
            if (byteString != null) {
                AuthRequirement.checkByteStringIsUtf8(byteString);
                this.audiences_ = byteString;
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

    private AuthRequirement(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuthRequirement() {
        this.memoizedIsInitialized = -1;
        this.providerId_ = "";
        this.audiences_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthRequirement(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.providerId_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.audiences_ = codedInputStream.readStringRequireUtf8();
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
        return AuthProto.internal_static_google_api_AuthRequirement_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthRequirement_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthRequirement.class, Builder.class);
    }

    public String getProviderId() {
        Object obj = this.providerId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.providerId_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getProviderIdBytes() {
        Object obj = this.providerId_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.providerId_ = copyFromUtf8;
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
        if (!getProviderIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.providerId_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.audiences_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getProviderIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.providerId_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.audiences_);
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
        if (!(obj instanceof AuthRequirement)) {
            return super.equals(obj);
        }
        AuthRequirement authRequirement = (AuthRequirement) obj;
        if (!((getProviderId().equals(authRequirement.getProviderId())) && getAudiences().equals(authRequirement.getAudiences())) || !this.unknownFields.equals(authRequirement.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getProviderId().hashCode()) * 37) + 2) * 53) + getAudiences().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static AuthRequirement parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthRequirement) PARSER.parseFrom(byteBuffer);
    }

    public static AuthRequirement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthRequirement) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthRequirement) PARSER.parseFrom(byteString);
    }

    public static AuthRequirement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthRequirement) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthRequirement) PARSER.parseFrom(bArr);
    }

    public static AuthRequirement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthRequirement) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(InputStream inputStream) throws IOException {
        return (AuthRequirement) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthRequirement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthRequirement) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthRequirement parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthRequirement) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthRequirement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthRequirement) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthRequirement) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthRequirement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthRequirement) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthRequirement authRequirement) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authRequirement);
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

    public static AuthRequirement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthRequirement> parser() {
        return PARSER;
    }

    public Parser<AuthRequirement> getParserForType() {
        return PARSER;
    }

    public AuthRequirement getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
