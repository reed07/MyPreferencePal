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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class AuthorizationInfo extends GeneratedMessageV3 implements AuthorizationInfoOrBuilder {
    private static final AuthorizationInfo DEFAULT_INSTANCE = new AuthorizationInfo();
    public static final int GRANTED_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<AuthorizationInfo> PARSER = new AbstractParser<AuthorizationInfo>() {
        public AuthorizationInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthorizationInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PERMISSION_FIELD_NUMBER = 2;
    public static final int RESOURCE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public boolean granted_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object permission_;
    /* access modifiers changed from: private */
    public volatile Object resource_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthorizationInfoOrBuilder {
        private boolean granted_;
        private Object permission_;
        private Object resource_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationInfo.class, Builder.class);
        }

        private Builder() {
            this.resource_ = "";
            this.permission_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.resource_ = "";
            this.permission_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            AuthorizationInfo.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.resource_ = "";
            this.permission_ = "";
            this.granted_ = false;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_descriptor;
        }

        public AuthorizationInfo getDefaultInstanceForType() {
            return AuthorizationInfo.getDefaultInstance();
        }

        public AuthorizationInfo build() {
            AuthorizationInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuthorizationInfo buildPartial() {
            AuthorizationInfo authorizationInfo = new AuthorizationInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            authorizationInfo.resource_ = this.resource_;
            authorizationInfo.permission_ = this.permission_;
            authorizationInfo.granted_ = this.granted_;
            onBuilt();
            return authorizationInfo;
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
            if (message instanceof AuthorizationInfo) {
                return mergeFrom((AuthorizationInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthorizationInfo authorizationInfo) {
            if (authorizationInfo == AuthorizationInfo.getDefaultInstance()) {
                return this;
            }
            if (!authorizationInfo.getResource().isEmpty()) {
                this.resource_ = authorizationInfo.resource_;
                onChanged();
            }
            if (!authorizationInfo.getPermission().isEmpty()) {
                this.permission_ = authorizationInfo.permission_;
                onChanged();
            }
            if (authorizationInfo.getGranted()) {
                setGranted(authorizationInfo.getGranted());
            }
            mergeUnknownFields(authorizationInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.cloud.audit.AuthorizationInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.AuthorizationInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.cloud.audit.AuthorizationInfo r3 = (com.google.cloud.audit.AuthorizationInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.cloud.audit.AuthorizationInfo r4 = (com.google.cloud.audit.AuthorizationInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.AuthorizationInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.AuthorizationInfo$Builder");
        }

        public String getResource() {
            Object obj = this.resource_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.resource_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getResourceBytes() {
            Object obj = this.resource_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resource_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setResource(String str) {
            if (str != null) {
                this.resource_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearResource() {
            this.resource_ = AuthorizationInfo.getDefaultInstance().getResource();
            onChanged();
            return this;
        }

        public Builder setResourceBytes(ByteString byteString) {
            if (byteString != null) {
                AuthorizationInfo.checkByteStringIsUtf8(byteString);
                this.resource_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getPermission() {
            Object obj = this.permission_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.permission_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getPermissionBytes() {
            Object obj = this.permission_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.permission_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPermission(String str) {
            if (str != null) {
                this.permission_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPermission() {
            this.permission_ = AuthorizationInfo.getDefaultInstance().getPermission();
            onChanged();
            return this;
        }

        public Builder setPermissionBytes(ByteString byteString) {
            if (byteString != null) {
                AuthorizationInfo.checkByteStringIsUtf8(byteString);
                this.permission_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean getGranted() {
            return this.granted_;
        }

        public Builder setGranted(boolean z) {
            this.granted_ = z;
            onChanged();
            return this;
        }

        public Builder clearGranted() {
            this.granted_ = false;
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

    private AuthorizationInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuthorizationInfo() {
        this.memoizedIsInitialized = -1;
        this.resource_ = "";
        this.permission_ = "";
        this.granted_ = false;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthorizationInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.resource_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.permission_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 24) {
                        this.granted_ = codedInputStream.readBool();
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
        return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_AuthorizationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthorizationInfo.class, Builder.class);
    }

    public String getResource() {
        Object obj = this.resource_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resource_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getResourceBytes() {
        Object obj = this.resource_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.resource_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getPermission() {
        Object obj = this.permission_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.permission_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getPermissionBytes() {
        Object obj = this.permission_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.permission_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean getGranted() {
        return this.granted_;
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
        if (!getResourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.resource_);
        }
        if (!getPermissionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.permission_);
        }
        boolean z = this.granted_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getResourceBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.resource_);
        }
        if (!getPermissionBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.permission_);
        }
        boolean z = this.granted_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(3, z);
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
        if (!(obj instanceof AuthorizationInfo)) {
            return super.equals(obj);
        }
        AuthorizationInfo authorizationInfo = (AuthorizationInfo) obj;
        if (!(((getResource().equals(authorizationInfo.getResource())) && getPermission().equals(authorizationInfo.getPermission())) && getGranted() == authorizationInfo.getGranted()) || !this.unknownFields.equals(authorizationInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResource().hashCode()) * 37) + 2) * 53) + getPermission().hashCode()) * 37) + 3) * 53) + Internal.hashBoolean(getGranted())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static AuthorizationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteBuffer);
    }

    public static AuthorizationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteString);
    }

    public static AuthorizationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(bArr);
    }

    public static AuthorizationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthorizationInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(InputStream inputStream) throws IOException {
        return (AuthorizationInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthorizationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthorizationInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthorizationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthorizationInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthorizationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthorizationInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthorizationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthorizationInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthorizationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthorizationInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthorizationInfo authorizationInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authorizationInfo);
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

    public static AuthorizationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthorizationInfo> parser() {
        return PARSER;
    }

    public Parser<AuthorizationInfo> getParserForType() {
        return PARSER;
    }

    public AuthorizationInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
