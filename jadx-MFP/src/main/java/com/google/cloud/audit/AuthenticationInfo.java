package com.google.cloud.audit;

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

public final class AuthenticationInfo extends GeneratedMessageV3 implements AuthenticationInfoOrBuilder {
    private static final AuthenticationInfo DEFAULT_INSTANCE = new AuthenticationInfo();
    /* access modifiers changed from: private */
    public static final Parser<AuthenticationInfo> PARSER = new AbstractParser<AuthenticationInfo>() {
        public AuthenticationInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthenticationInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRINCIPAL_EMAIL_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object principalEmail_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthenticationInfoOrBuilder {
        private Object principalEmail_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthenticationInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthenticationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationInfo.class, Builder.class);
        }

        private Builder() {
            this.principalEmail_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.principalEmail_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            AuthenticationInfo.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.principalEmail_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthenticationInfo_descriptor;
        }

        public AuthenticationInfo getDefaultInstanceForType() {
            return AuthenticationInfo.getDefaultInstance();
        }

        public AuthenticationInfo build() {
            AuthenticationInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuthenticationInfo buildPartial() {
            AuthenticationInfo authenticationInfo = new AuthenticationInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            authenticationInfo.principalEmail_ = this.principalEmail_;
            onBuilt();
            return authenticationInfo;
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
            if (message instanceof AuthenticationInfo) {
                return mergeFrom((AuthenticationInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthenticationInfo authenticationInfo) {
            if (authenticationInfo == AuthenticationInfo.getDefaultInstance()) {
                return this;
            }
            if (!authenticationInfo.getPrincipalEmail().isEmpty()) {
                this.principalEmail_ = authenticationInfo.principalEmail_;
                onChanged();
            }
            mergeUnknownFields(authenticationInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.cloud.audit.AuthenticationInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.AuthenticationInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.cloud.audit.AuthenticationInfo r3 = (com.google.cloud.audit.AuthenticationInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.cloud.audit.AuthenticationInfo r4 = (com.google.cloud.audit.AuthenticationInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.AuthenticationInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.AuthenticationInfo$Builder");
        }

        public String getPrincipalEmail() {
            Object obj = this.principalEmail_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.principalEmail_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getPrincipalEmailBytes() {
            Object obj = this.principalEmail_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.principalEmail_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPrincipalEmail(String str) {
            if (str != null) {
                this.principalEmail_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPrincipalEmail() {
            this.principalEmail_ = AuthenticationInfo.getDefaultInstance().getPrincipalEmail();
            onChanged();
            return this;
        }

        public Builder setPrincipalEmailBytes(ByteString byteString) {
            if (byteString != null) {
                AuthenticationInfo.checkByteStringIsUtf8(byteString);
                this.principalEmail_ = byteString;
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

    private AuthenticationInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuthenticationInfo() {
        this.memoizedIsInitialized = -1;
        this.principalEmail_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthenticationInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.principalEmail_ = codedInputStream.readStringRequireUtf8();
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
        return AuditLogProto.internal_static_google_cloud_audit_AuthenticationInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_AuthenticationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationInfo.class, Builder.class);
    }

    public String getPrincipalEmail() {
        Object obj = this.principalEmail_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.principalEmail_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getPrincipalEmailBytes() {
        Object obj = this.principalEmail_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.principalEmail_ = copyFromUtf8;
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
        if (!getPrincipalEmailBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.principalEmail_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getPrincipalEmailBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.principalEmail_);
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
        if (!(obj instanceof AuthenticationInfo)) {
            return super.equals(obj);
        }
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) obj;
        if (!(getPrincipalEmail().equals(authenticationInfo.getPrincipalEmail())) || !this.unknownFields.equals(authenticationInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getPrincipalEmail().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static AuthenticationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthenticationInfo) PARSER.parseFrom(byteBuffer);
    }

    public static AuthenticationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthenticationInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthenticationInfo) PARSER.parseFrom(byteString);
    }

    public static AuthenticationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthenticationInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthenticationInfo) PARSER.parseFrom(bArr);
    }

    public static AuthenticationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthenticationInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(InputStream inputStream) throws IOException {
        return (AuthenticationInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthenticationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthenticationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthenticationInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthenticationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthenticationInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthenticationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthenticationInfo authenticationInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authenticationInfo);
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

    public static AuthenticationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthenticationInfo> parser() {
        return PARSER;
    }

    public Parser<AuthenticationInfo> getParserForType() {
        return PARSER;
    }

    public AuthenticationInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
