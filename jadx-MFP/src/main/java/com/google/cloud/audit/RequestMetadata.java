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

public final class RequestMetadata extends GeneratedMessageV3 implements RequestMetadataOrBuilder {
    public static final int CALLER_IP_FIELD_NUMBER = 1;
    public static final int CALLER_SUPPLIED_USER_AGENT_FIELD_NUMBER = 2;
    private static final RequestMetadata DEFAULT_INSTANCE = new RequestMetadata();
    /* access modifiers changed from: private */
    public static final Parser<RequestMetadata> PARSER = new AbstractParser<RequestMetadata>() {
        public RequestMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RequestMetadata(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object callerIp_;
    /* access modifiers changed from: private */
    public volatile Object callerSuppliedUserAgent_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements RequestMetadataOrBuilder {
        private Object callerIp_;
        private Object callerSuppliedUserAgent_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestMetadata.class, Builder.class);
        }

        private Builder() {
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            RequestMetadata.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
        }

        public RequestMetadata getDefaultInstanceForType() {
            return RequestMetadata.getDefaultInstance();
        }

        public RequestMetadata build() {
            RequestMetadata buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public RequestMetadata buildPartial() {
            RequestMetadata requestMetadata = new RequestMetadata((com.google.protobuf.GeneratedMessageV3.Builder) this);
            requestMetadata.callerIp_ = this.callerIp_;
            requestMetadata.callerSuppliedUserAgent_ = this.callerSuppliedUserAgent_;
            onBuilt();
            return requestMetadata;
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
            if (message instanceof RequestMetadata) {
                return mergeFrom((RequestMetadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RequestMetadata requestMetadata) {
            if (requestMetadata == RequestMetadata.getDefaultInstance()) {
                return this;
            }
            if (!requestMetadata.getCallerIp().isEmpty()) {
                this.callerIp_ = requestMetadata.callerIp_;
                onChanged();
            }
            if (!requestMetadata.getCallerSuppliedUserAgent().isEmpty()) {
                this.callerSuppliedUserAgent_ = requestMetadata.callerSuppliedUserAgent_;
                onChanged();
            }
            mergeUnknownFields(requestMetadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.cloud.audit.RequestMetadata.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.RequestMetadata.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.cloud.audit.RequestMetadata r3 = (com.google.cloud.audit.RequestMetadata) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.cloud.audit.RequestMetadata r4 = (com.google.cloud.audit.RequestMetadata) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.RequestMetadata.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.RequestMetadata$Builder");
        }

        public String getCallerIp() {
            Object obj = this.callerIp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.callerIp_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getCallerIpBytes() {
            Object obj = this.callerIp_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.callerIp_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setCallerIp(String str) {
            if (str != null) {
                this.callerIp_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearCallerIp() {
            this.callerIp_ = RequestMetadata.getDefaultInstance().getCallerIp();
            onChanged();
            return this;
        }

        public Builder setCallerIpBytes(ByteString byteString) {
            if (byteString != null) {
                RequestMetadata.checkByteStringIsUtf8(byteString);
                this.callerIp_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getCallerSuppliedUserAgent() {
            Object obj = this.callerSuppliedUserAgent_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.callerSuppliedUserAgent_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getCallerSuppliedUserAgentBytes() {
            Object obj = this.callerSuppliedUserAgent_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.callerSuppliedUserAgent_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setCallerSuppliedUserAgent(String str) {
            if (str != null) {
                this.callerSuppliedUserAgent_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearCallerSuppliedUserAgent() {
            this.callerSuppliedUserAgent_ = RequestMetadata.getDefaultInstance().getCallerSuppliedUserAgent();
            onChanged();
            return this;
        }

        public Builder setCallerSuppliedUserAgentBytes(ByteString byteString) {
            if (byteString != null) {
                RequestMetadata.checkByteStringIsUtf8(byteString);
                this.callerSuppliedUserAgent_ = byteString;
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

    private RequestMetadata(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private RequestMetadata() {
        this.memoizedIsInitialized = -1;
        this.callerIp_ = "";
        this.callerSuppliedUserAgent_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RequestMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.callerIp_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.callerSuppliedUserAgent_ = codedInputStream.readStringRequireUtf8();
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
        return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestMetadata.class, Builder.class);
    }

    public String getCallerIp() {
        Object obj = this.callerIp_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.callerIp_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getCallerIpBytes() {
        Object obj = this.callerIp_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.callerIp_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getCallerSuppliedUserAgent() {
        Object obj = this.callerSuppliedUserAgent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.callerSuppliedUserAgent_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getCallerSuppliedUserAgentBytes() {
        Object obj = this.callerSuppliedUserAgent_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.callerSuppliedUserAgent_ = copyFromUtf8;
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
        if (!getCallerIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.callerIp_);
        }
        if (!getCallerSuppliedUserAgentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.callerSuppliedUserAgent_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getCallerIpBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.callerIp_);
        }
        if (!getCallerSuppliedUserAgentBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.callerSuppliedUserAgent_);
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
        if (!(obj instanceof RequestMetadata)) {
            return super.equals(obj);
        }
        RequestMetadata requestMetadata = (RequestMetadata) obj;
        if (!((getCallerIp().equals(requestMetadata.getCallerIp())) && getCallerSuppliedUserAgent().equals(requestMetadata.getCallerSuppliedUserAgent())) || !this.unknownFields.equals(requestMetadata.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCallerIp().hashCode()) * 37) + 2) * 53) + getCallerSuppliedUserAgent().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static RequestMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RequestMetadata) PARSER.parseFrom(byteBuffer);
    }

    public static RequestMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestMetadata) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RequestMetadata) PARSER.parseFrom(byteString);
    }

    public static RequestMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestMetadata) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RequestMetadata) PARSER.parseFrom(bArr);
    }

    public static RequestMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestMetadata) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(InputStream inputStream) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RequestMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RequestMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RequestMetadata requestMetadata) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(requestMetadata);
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

    public static RequestMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestMetadata> parser() {
        return PARSER;
    }

    public Parser<RequestMetadata> getParserForType() {
        return PARSER;
    }

    public RequestMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
