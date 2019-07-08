package com.google.cloud.audit;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.Status;
import com.google.rpc.StatusOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AuditLog extends GeneratedMessageV3 implements AuditLogOrBuilder {
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    private static final AuditLog DEFAULT_INSTANCE = new AuditLog();
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    /* access modifiers changed from: private */
    public static final Parser<AuditLog> PARSER = new AbstractParser<AuditLog>() {
        public AuditLog parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuditLog(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int REQUEST_FIELD_NUMBER = 16;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public AuthenticationInfo authenticationInfo_;
    /* access modifiers changed from: private */
    public List<AuthorizationInfo> authorizationInfo_;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object methodName_;
    /* access modifiers changed from: private */
    public long numResponseItems_;
    /* access modifiers changed from: private */
    public RequestMetadata requestMetadata_;
    /* access modifiers changed from: private */
    public Struct request_;
    /* access modifiers changed from: private */
    public volatile Object resourceName_;
    /* access modifiers changed from: private */
    public Struct response_;
    /* access modifiers changed from: private */
    public Any serviceData_;
    /* access modifiers changed from: private */
    public volatile Object serviceName_;
    /* access modifiers changed from: private */
    public Status status_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuditLogOrBuilder {
        private SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> authenticationInfoBuilder_;
        private AuthenticationInfo authenticationInfo_;
        private RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> authorizationInfoBuilder_;
        private List<AuthorizationInfo> authorizationInfo_;
        private int bitField0_;
        private Object methodName_;
        private long numResponseItems_;
        private SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> requestBuilder_;
        private SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> requestMetadataBuilder_;
        private RequestMetadata requestMetadata_;
        private Struct request_;
        private Object resourceName_;
        private SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> responseBuilder_;
        private Struct response_;
        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> serviceDataBuilder_;
        private Any serviceData_;
        private Object serviceName_;
        private SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> statusBuilder_;
        private Status status_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
        }

        private Builder() {
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.status_ = null;
            this.authenticationInfo_ = null;
            this.authorizationInfo_ = Collections.emptyList();
            this.requestMetadata_ = null;
            this.request_ = null;
            this.response_ = null;
            this.serviceData_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.status_ = null;
            this.authenticationInfo_ = null;
            this.authorizationInfo_ = Collections.emptyList();
            this.requestMetadata_ = null;
            this.request_ = null;
            this.response_ = null;
            this.serviceData_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (AuditLog.alwaysUseFieldBuilders) {
                getAuthorizationInfoFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.serviceName_ = "";
            this.methodName_ = "";
            this.resourceName_ = "";
            this.numResponseItems_ = 0;
            if (this.statusBuilder_ == null) {
                this.status_ = null;
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            if (this.requestBuilder_ == null) {
                this.request_ = null;
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            if (this.responseBuilder_ == null) {
                this.response_ = null;
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
        }

        public AuditLog getDefaultInstanceForType() {
            return AuditLog.getDefaultInstance();
        }

        public AuditLog build() {
            AuditLog buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuditLog buildPartial() {
            AuditLog auditLog = new AuditLog((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            auditLog.serviceName_ = this.serviceName_;
            auditLog.methodName_ = this.methodName_;
            auditLog.resourceName_ = this.resourceName_;
            auditLog.numResponseItems_ = this.numResponseItems_;
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                auditLog.status_ = this.status_;
            } else {
                auditLog.status_ = (Status) singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV32 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV32 == null) {
                auditLog.authenticationInfo_ = this.authenticationInfo_;
            } else {
                auditLog.authenticationInfo_ = (AuthenticationInfo) singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 64) == 64) {
                    this.authorizationInfo_ = Collections.unmodifiableList(this.authorizationInfo_);
                    this.bitField0_ &= -65;
                }
                auditLog.authorizationInfo_ = this.authorizationInfo_;
            } else {
                auditLog.authorizationInfo_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV33 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV33 == null) {
                auditLog.requestMetadata_ = this.requestMetadata_;
            } else {
                auditLog.requestMetadata_ = (RequestMetadata) singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV34 = this.requestBuilder_;
            if (singleFieldBuilderV34 == null) {
                auditLog.request_ = this.request_;
            } else {
                auditLog.request_ = (Struct) singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV35 = this.responseBuilder_;
            if (singleFieldBuilderV35 == null) {
                auditLog.response_ = this.response_;
            } else {
                auditLog.response_ = (Struct) singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV36 = this.serviceDataBuilder_;
            if (singleFieldBuilderV36 == null) {
                auditLog.serviceData_ = this.serviceData_;
            } else {
                auditLog.serviceData_ = (Any) singleFieldBuilderV36.build();
            }
            auditLog.bitField0_ = 0;
            onBuilt();
            return auditLog;
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
            if (message instanceof AuditLog) {
                return mergeFrom((AuditLog) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuditLog auditLog) {
            if (auditLog == AuditLog.getDefaultInstance()) {
                return this;
            }
            if (!auditLog.getServiceName().isEmpty()) {
                this.serviceName_ = auditLog.serviceName_;
                onChanged();
            }
            if (!auditLog.getMethodName().isEmpty()) {
                this.methodName_ = auditLog.methodName_;
                onChanged();
            }
            if (!auditLog.getResourceName().isEmpty()) {
                this.resourceName_ = auditLog.resourceName_;
                onChanged();
            }
            if (auditLog.getNumResponseItems() != 0) {
                setNumResponseItems(auditLog.getNumResponseItems());
            }
            if (auditLog.hasStatus()) {
                mergeStatus(auditLog.getStatus());
            }
            if (auditLog.hasAuthenticationInfo()) {
                mergeAuthenticationInfo(auditLog.getAuthenticationInfo());
            }
            if (this.authorizationInfoBuilder_ == null) {
                if (!auditLog.authorizationInfo_.isEmpty()) {
                    if (this.authorizationInfo_.isEmpty()) {
                        this.authorizationInfo_ = auditLog.authorizationInfo_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureAuthorizationInfoIsMutable();
                        this.authorizationInfo_.addAll(auditLog.authorizationInfo_);
                    }
                    onChanged();
                }
            } else if (!auditLog.authorizationInfo_.isEmpty()) {
                if (this.authorizationInfoBuilder_.isEmpty()) {
                    this.authorizationInfoBuilder_.dispose();
                    RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = null;
                    this.authorizationInfoBuilder_ = null;
                    this.authorizationInfo_ = auditLog.authorizationInfo_;
                    this.bitField0_ &= -65;
                    if (AuditLog.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getAuthorizationInfoFieldBuilder();
                    }
                    this.authorizationInfoBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.authorizationInfoBuilder_.addAllMessages(auditLog.authorizationInfo_);
                }
            }
            if (auditLog.hasRequestMetadata()) {
                mergeRequestMetadata(auditLog.getRequestMetadata());
            }
            if (auditLog.hasRequest()) {
                mergeRequest(auditLog.getRequest());
            }
            if (auditLog.hasResponse()) {
                mergeResponse(auditLog.getResponse());
            }
            if (auditLog.hasServiceData()) {
                mergeServiceData(auditLog.getServiceData());
            }
            mergeUnknownFields(auditLog.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.cloud.audit.AuditLog.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.AuditLog.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.cloud.audit.AuditLog r3 = (com.google.cloud.audit.AuditLog) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.cloud.audit.AuditLog r4 = (com.google.cloud.audit.AuditLog) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.AuditLog.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.AuditLog$Builder");
        }

        public String getServiceName() {
            Object obj = this.serviceName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.serviceName_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getServiceNameBytes() {
            Object obj = this.serviceName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.serviceName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setServiceName(String str) {
            if (str != null) {
                this.serviceName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearServiceName() {
            this.serviceName_ = AuditLog.getDefaultInstance().getServiceName();
            onChanged();
            return this;
        }

        public Builder setServiceNameBytes(ByteString byteString) {
            if (byteString != null) {
                AuditLog.checkByteStringIsUtf8(byteString);
                this.serviceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getMethodName() {
            Object obj = this.methodName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.methodName_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getMethodNameBytes() {
            Object obj = this.methodName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.methodName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setMethodName(String str) {
            if (str != null) {
                this.methodName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearMethodName() {
            this.methodName_ = AuditLog.getDefaultInstance().getMethodName();
            onChanged();
            return this;
        }

        public Builder setMethodNameBytes(ByteString byteString) {
            if (byteString != null) {
                AuditLog.checkByteStringIsUtf8(byteString);
                this.methodName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getResourceName() {
            Object obj = this.resourceName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.resourceName_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getResourceNameBytes() {
            Object obj = this.resourceName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setResourceName(String str) {
            if (str != null) {
                this.resourceName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearResourceName() {
            this.resourceName_ = AuditLog.getDefaultInstance().getResourceName();
            onChanged();
            return this;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            if (byteString != null) {
                AuditLog.checkByteStringIsUtf8(byteString);
                this.resourceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public long getNumResponseItems() {
            return this.numResponseItems_;
        }

        public Builder setNumResponseItems(long j) {
            this.numResponseItems_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumResponseItems() {
            this.numResponseItems_ = 0;
            onChanged();
            return this;
        }

        public boolean hasStatus() {
            return (this.statusBuilder_ == null && this.status_ == null) ? false : true;
        }

        public Status getStatus() {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Status) singleFieldBuilderV3.getMessage();
            }
            Status status = this.status_;
            if (status == null) {
                status = Status.getDefaultInstance();
            }
            return status;
        }

        public Builder setStatus(Status status) {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(status);
            } else if (status != null) {
                this.status_ = status;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setStatus(com.google.rpc.Status.Builder builder) {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.status_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeStatus(Status status) {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 == null) {
                Status status2 = this.status_;
                if (status2 != null) {
                    this.status_ = Status.newBuilder(status2).mergeFrom(status).buildPartial();
                } else {
                    this.status_ = status;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(status);
            }
            return this;
        }

        public Builder clearStatus() {
            if (this.statusBuilder_ == null) {
                this.status_ = null;
                onChanged();
            } else {
                this.status_ = null;
                this.statusBuilder_ = null;
            }
            return this;
        }

        public com.google.rpc.Status.Builder getStatusBuilder() {
            onChanged();
            return (com.google.rpc.Status.Builder) getStatusFieldBuilder().getBuilder();
        }

        public StatusOrBuilder getStatusOrBuilder() {
            SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> singleFieldBuilderV3 = this.statusBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (StatusOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Status status = this.status_;
            if (status == null) {
                status = Status.getDefaultInstance();
            }
            return status;
        }

        private SingleFieldBuilderV3<Status, com.google.rpc.Status.Builder, StatusOrBuilder> getStatusFieldBuilder() {
            if (this.statusBuilder_ == null) {
                this.statusBuilder_ = new SingleFieldBuilderV3<>(getStatus(), getParentForChildren(), isClean());
                this.status_ = null;
            }
            return this.statusBuilder_;
        }

        public boolean hasAuthenticationInfo() {
            return (this.authenticationInfoBuilder_ == null && this.authenticationInfo_ == null) ? false : true;
        }

        public AuthenticationInfo getAuthenticationInfo() {
            SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AuthenticationInfo) singleFieldBuilderV3.getMessage();
            }
            AuthenticationInfo authenticationInfo = this.authenticationInfo_;
            if (authenticationInfo == null) {
                authenticationInfo = AuthenticationInfo.getDefaultInstance();
            }
            return authenticationInfo;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(authenticationInfo);
            } else if (authenticationInfo != null) {
                this.authenticationInfo_ = authenticationInfo;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAuthenticationInfo(com.google.cloud.audit.AuthenticationInfo.Builder builder) {
            SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authenticationInfo_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo authenticationInfo) {
            SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                AuthenticationInfo authenticationInfo2 = this.authenticationInfo_;
                if (authenticationInfo2 != null) {
                    this.authenticationInfo_ = AuthenticationInfo.newBuilder(authenticationInfo2).mergeFrom(authenticationInfo).buildPartial();
                } else {
                    this.authenticationInfo_ = authenticationInfo;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(authenticationInfo);
            }
            return this;
        }

        public Builder clearAuthenticationInfo() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfo_ = null;
                onChanged();
            } else {
                this.authenticationInfo_ = null;
                this.authenticationInfoBuilder_ = null;
            }
            return this;
        }

        public com.google.cloud.audit.AuthenticationInfo.Builder getAuthenticationInfoBuilder() {
            onChanged();
            return (com.google.cloud.audit.AuthenticationInfo.Builder) getAuthenticationInfoFieldBuilder().getBuilder();
        }

        public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
            SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> singleFieldBuilderV3 = this.authenticationInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AuthenticationInfoOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            AuthenticationInfo authenticationInfo = this.authenticationInfo_;
            if (authenticationInfo == null) {
                authenticationInfo = AuthenticationInfo.getDefaultInstance();
            }
            return authenticationInfo;
        }

        private SingleFieldBuilderV3<AuthenticationInfo, com.google.cloud.audit.AuthenticationInfo.Builder, AuthenticationInfoOrBuilder> getAuthenticationInfoFieldBuilder() {
            if (this.authenticationInfoBuilder_ == null) {
                this.authenticationInfoBuilder_ = new SingleFieldBuilderV3<>(getAuthenticationInfo(), getParentForChildren(), isClean());
                this.authenticationInfo_ = null;
            }
            return this.authenticationInfoBuilder_;
        }

        private void ensureAuthorizationInfoIsMutable() {
            if ((this.bitField0_ & 64) != 64) {
                this.authorizationInfo_ = new ArrayList(this.authorizationInfo_);
                this.bitField0_ |= 64;
            }
        }

        public List<AuthorizationInfo> getAuthorizationInfoList() {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.authorizationInfo_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getAuthorizationInfoCount() {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.authorizationInfo_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public AuthorizationInfo getAuthorizationInfo(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthorizationInfo) this.authorizationInfo_.get(i);
            }
            return (AuthorizationInfo) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authorizationInfo);
            } else if (authorizationInfo != null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(i, authorizationInfo);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAuthorizationInfo(int i, com.google.cloud.audit.AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authorizationInfo);
            } else if (authorizationInfo != null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(authorizationInfo);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addAuthorizationInfo(int i, AuthorizationInfo authorizationInfo) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authorizationInfo);
            } else if (authorizationInfo != null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(i, authorizationInfo);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addAuthorizationInfo(com.google.cloud.audit.AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAuthorizationInfo(int i, com.google.cloud.audit.AuthorizationInfo.Builder builder) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> iterable) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.authorizationInfo_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAuthorizationInfo() {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.authorizationInfo_ = Collections.emptyList();
                this.bitField0_ &= -65;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAuthorizationInfo(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAuthorizationInfoIsMutable();
                this.authorizationInfo_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.cloud.audit.AuthorizationInfo.Builder getAuthorizationInfoBuilder(int i) {
            return (com.google.cloud.audit.AuthorizationInfo.Builder) getAuthorizationInfoFieldBuilder().getBuilder(i);
        }

        public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthorizationInfoOrBuilder) this.authorizationInfo_.get(i);
            }
            return (AuthorizationInfoOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
            RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> repeatedFieldBuilderV3 = this.authorizationInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.authorizationInfo_);
        }

        public com.google.cloud.audit.AuthorizationInfo.Builder addAuthorizationInfoBuilder() {
            return (com.google.cloud.audit.AuthorizationInfo.Builder) getAuthorizationInfoFieldBuilder().addBuilder(AuthorizationInfo.getDefaultInstance());
        }

        public com.google.cloud.audit.AuthorizationInfo.Builder addAuthorizationInfoBuilder(int i) {
            return (com.google.cloud.audit.AuthorizationInfo.Builder) getAuthorizationInfoFieldBuilder().addBuilder(i, AuthorizationInfo.getDefaultInstance());
        }

        public List<com.google.cloud.audit.AuthorizationInfo.Builder> getAuthorizationInfoBuilderList() {
            return getAuthorizationInfoFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthorizationInfo, com.google.cloud.audit.AuthorizationInfo.Builder, AuthorizationInfoOrBuilder> getAuthorizationInfoFieldBuilder() {
            if (this.authorizationInfoBuilder_ == null) {
                this.authorizationInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.authorizationInfo_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                this.authorizationInfo_ = null;
            }
            return this.authorizationInfoBuilder_;
        }

        public boolean hasRequestMetadata() {
            return (this.requestMetadataBuilder_ == null && this.requestMetadata_ == null) ? false : true;
        }

        public RequestMetadata getRequestMetadata() {
            SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RequestMetadata) singleFieldBuilderV3.getMessage();
            }
            RequestMetadata requestMetadata = this.requestMetadata_;
            if (requestMetadata == null) {
                requestMetadata = RequestMetadata.getDefaultInstance();
            }
            return requestMetadata;
        }

        public Builder setRequestMetadata(RequestMetadata requestMetadata) {
            SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(requestMetadata);
            } else if (requestMetadata != null) {
                this.requestMetadata_ = requestMetadata;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRequestMetadata(com.google.cloud.audit.RequestMetadata.Builder builder) {
            SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requestMetadata_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata requestMetadata) {
            SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 == null) {
                RequestMetadata requestMetadata2 = this.requestMetadata_;
                if (requestMetadata2 != null) {
                    this.requestMetadata_ = RequestMetadata.newBuilder(requestMetadata2).mergeFrom(requestMetadata).buildPartial();
                } else {
                    this.requestMetadata_ = requestMetadata;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(requestMetadata);
            }
            return this;
        }

        public Builder clearRequestMetadata() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadata_ = null;
                onChanged();
            } else {
                this.requestMetadata_ = null;
                this.requestMetadataBuilder_ = null;
            }
            return this;
        }

        public com.google.cloud.audit.RequestMetadata.Builder getRequestMetadataBuilder() {
            onChanged();
            return (com.google.cloud.audit.RequestMetadata.Builder) getRequestMetadataFieldBuilder().getBuilder();
        }

        public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
            SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> singleFieldBuilderV3 = this.requestMetadataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (RequestMetadataOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            RequestMetadata requestMetadata = this.requestMetadata_;
            if (requestMetadata == null) {
                requestMetadata = RequestMetadata.getDefaultInstance();
            }
            return requestMetadata;
        }

        private SingleFieldBuilderV3<RequestMetadata, com.google.cloud.audit.RequestMetadata.Builder, RequestMetadataOrBuilder> getRequestMetadataFieldBuilder() {
            if (this.requestMetadataBuilder_ == null) {
                this.requestMetadataBuilder_ = new SingleFieldBuilderV3<>(getRequestMetadata(), getParentForChildren(), isClean());
                this.requestMetadata_ = null;
            }
            return this.requestMetadataBuilder_;
        }

        public boolean hasRequest() {
            return (this.requestBuilder_ == null && this.request_ == null) ? false : true;
        }

        public Struct getRequest() {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Struct) singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.request_;
            if (struct == null) {
                struct = Struct.getDefaultInstance();
            }
            return struct;
        }

        public Builder setRequest(Struct struct) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else if (struct != null) {
                this.request_ = struct;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRequest(com.google.protobuf.Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.request_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequest(Struct struct) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.request_;
                if (struct2 != null) {
                    this.request_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.request_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearRequest() {
            if (this.requestBuilder_ == null) {
                this.request_ = null;
                onChanged();
            } else {
                this.request_ = null;
                this.requestBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Struct.Builder getRequestBuilder() {
            onChanged();
            return (com.google.protobuf.Struct.Builder) getRequestFieldBuilder().getBuilder();
        }

        public StructOrBuilder getRequestOrBuilder() {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.requestBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (StructOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.request_;
            if (struct == null) {
                struct = Struct.getDefaultInstance();
            }
            return struct;
        }

        private SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> getRequestFieldBuilder() {
            if (this.requestBuilder_ == null) {
                this.requestBuilder_ = new SingleFieldBuilderV3<>(getRequest(), getParentForChildren(), isClean());
                this.request_ = null;
            }
            return this.requestBuilder_;
        }

        public boolean hasResponse() {
            return (this.responseBuilder_ == null && this.response_ == null) ? false : true;
        }

        public Struct getResponse() {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Struct) singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.response_;
            if (struct == null) {
                struct = Struct.getDefaultInstance();
            }
            return struct;
        }

        public Builder setResponse(Struct struct) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else if (struct != null) {
                this.response_ = struct;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setResponse(com.google.protobuf.Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.response_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeResponse(Struct struct) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.response_;
                if (struct2 != null) {
                    this.response_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.response_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearResponse() {
            if (this.responseBuilder_ == null) {
                this.response_ = null;
                onChanged();
            } else {
                this.response_ = null;
                this.responseBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Struct.Builder getResponseBuilder() {
            onChanged();
            return (com.google.protobuf.Struct.Builder) getResponseFieldBuilder().getBuilder();
        }

        public StructOrBuilder getResponseOrBuilder() {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.responseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (StructOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.response_;
            if (struct == null) {
                struct = Struct.getDefaultInstance();
            }
            return struct;
        }

        private SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> getResponseFieldBuilder() {
            if (this.responseBuilder_ == null) {
                this.responseBuilder_ = new SingleFieldBuilderV3<>(getResponse(), getParentForChildren(), isClean());
                this.response_ = null;
            }
            return this.responseBuilder_;
        }

        public boolean hasServiceData() {
            return (this.serviceDataBuilder_ == null && this.serviceData_ == null) ? false : true;
        }

        public Any getServiceData() {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Any) singleFieldBuilderV3.getMessage();
            }
            Any any = this.serviceData_;
            if (any == null) {
                any = Any.getDefaultInstance();
            }
            return any;
        }

        public Builder setServiceData(Any any) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(any);
            } else if (any != null) {
                this.serviceData_ = any;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setServiceData(com.google.protobuf.Any.Builder builder) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.serviceData_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeServiceData(Any any) {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 == null) {
                Any any2 = this.serviceData_;
                if (any2 != null) {
                    this.serviceData_ = Any.newBuilder(any2).mergeFrom(any).buildPartial();
                } else {
                    this.serviceData_ = any;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(any);
            }
            return this;
        }

        public Builder clearServiceData() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceData_ = null;
                onChanged();
            } else {
                this.serviceData_ = null;
                this.serviceDataBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getServiceDataBuilder() {
            onChanged();
            return (com.google.protobuf.Any.Builder) getServiceDataFieldBuilder().getBuilder();
        }

        public AnyOrBuilder getServiceDataOrBuilder() {
            SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.serviceDataBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AnyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Any any = this.serviceData_;
            if (any == null) {
                any = Any.getDefaultInstance();
            }
            return any;
        }

        private SingleFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getServiceDataFieldBuilder() {
            if (this.serviceDataBuilder_ == null) {
                this.serviceDataBuilder_ = new SingleFieldBuilderV3<>(getServiceData(), getParentForChildren(), isClean());
                this.serviceData_ = null;
            }
            return this.serviceDataBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private AuditLog(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuditLog() {
        this.memoizedIsInitialized = -1;
        this.serviceName_ = "";
        this.methodName_ = "";
        this.resourceName_ = "";
        this.numResponseItems_ = 0;
        this.authorizationInfo_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [com.google.rpc.Status$Builder] */
    /* JADX WARNING: type inference failed for: r6v2, types: [com.google.rpc.Status$Builder] */
    /* JADX WARNING: type inference failed for: r6v3, types: [com.google.cloud.audit.AuthenticationInfo$Builder] */
    /* JADX WARNING: type inference failed for: r6v4, types: [com.google.cloud.audit.AuthenticationInfo$Builder] */
    /* JADX WARNING: type inference failed for: r6v5, types: [com.google.cloud.audit.RequestMetadata$Builder] */
    /* JADX WARNING: type inference failed for: r6v6, types: [com.google.cloud.audit.RequestMetadata$Builder] */
    /* JADX WARNING: type inference failed for: r6v7, types: [com.google.protobuf.Any$Builder] */
    /* JADX WARNING: type inference failed for: r6v8, types: [com.google.protobuf.Any$Builder] */
    /* JADX WARNING: type inference failed for: r6v9, types: [com.google.protobuf.Struct$Builder] */
    /* JADX WARNING: type inference failed for: r6v10, types: [com.google.protobuf.Struct$Builder] */
    /* JADX WARNING: type inference failed for: r6v11, types: [com.google.protobuf.Struct$Builder] */
    /* JADX WARNING: type inference failed for: r6v12, types: [com.google.protobuf.Struct$Builder] */
    /* JADX WARNING: type inference failed for: r6v13 */
    /* JADX WARNING: type inference failed for: r6v14 */
    /* JADX WARNING: type inference failed for: r6v15 */
    /* JADX WARNING: type inference failed for: r6v16 */
    /* JADX WARNING: type inference failed for: r6v17 */
    /* JADX WARNING: type inference failed for: r6v18 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.cloud.audit.AuthenticationInfo$Builder, com.google.rpc.Status$Builder, com.google.cloud.audit.RequestMetadata$Builder, com.google.protobuf.Any$Builder, com.google.protobuf.Struct$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.google.rpc.Status$Builder, com.google.cloud.audit.AuthenticationInfo$Builder, com.google.cloud.audit.RequestMetadata$Builder, com.google.protobuf.Any$Builder, com.google.protobuf.Struct$Builder]
  mth insns count: 153
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private AuditLog(com.google.protobuf.CodedInputStream r8, com.google.protobuf.ExtensionRegistryLite r9) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r7 = this;
            r7.<init>()
            if (r9 == 0) goto L_0x017c
            com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
            r1 = 0
            r2 = 0
        L_0x000b:
            r3 = 64
            if (r1 != 0) goto L_0x0166
            int r4 = r8.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r5 = 1
            r6 = 0
            switch(r4) {
                case 0: goto L_0x0135;
                case 18: goto L_0x0110;
                case 26: goto L_0x00eb;
                case 34: goto L_0x00c6;
                case 58: goto L_0x00be;
                case 66: goto L_0x00b6;
                case 74: goto L_0x009a;
                case 90: goto L_0x0092;
                case 96: goto L_0x008a;
                case 122: goto L_0x0066;
                case 130: goto L_0x0042;
                case 138: goto L_0x001e;
                default: goto L_0x0018;
            }     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x0018:
            boolean r3 = r7.parseUnknownFieldProto3(r8, r0, r9, r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x0138
        L_0x001e:
            com.google.protobuf.Struct r4 = r7.response_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r4 == 0) goto L_0x0028
            com.google.protobuf.Struct r4 = r7.response_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Struct$Builder r6 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x0028:
            com.google.protobuf.Parser r4 = com.google.protobuf.Struct.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r4 = r8.readMessage(r4, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Struct r4 = (com.google.protobuf.Struct) r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.response_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r6 == 0) goto L_0x000b
            com.google.protobuf.Struct r4 = r7.response_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r6.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Struct r4 = r6.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.response_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x0042:
            com.google.protobuf.Struct r4 = r7.request_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r4 == 0) goto L_0x004c
            com.google.protobuf.Struct r4 = r7.request_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Struct$Builder r6 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x004c:
            com.google.protobuf.Parser r4 = com.google.protobuf.Struct.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r4 = r8.readMessage(r4, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Struct r4 = (com.google.protobuf.Struct) r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.request_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r6 == 0) goto L_0x000b
            com.google.protobuf.Struct r4 = r7.request_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r6.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Struct r4 = r6.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.request_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x0066:
            com.google.protobuf.Any r4 = r7.serviceData_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r4 == 0) goto L_0x0070
            com.google.protobuf.Any r4 = r7.serviceData_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Any$Builder r6 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x0070:
            com.google.protobuf.Parser r4 = com.google.protobuf.Any.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r4 = r8.readMessage(r4, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Any r4 = (com.google.protobuf.Any) r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.serviceData_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r6 == 0) goto L_0x000b
            com.google.protobuf.Any r4 = r7.serviceData_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r6.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Any r4 = r6.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.serviceData_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x008a:
            long r4 = r8.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.numResponseItems_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x0092:
            java.lang.String r4 = r8.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.resourceName_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x009a:
            r4 = r2 & 64
            if (r4 == r3) goto L_0x00a7
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r4.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.authorizationInfo_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r2 = r2 | 64
        L_0x00a7:
            java.util.List<com.google.cloud.audit.AuthorizationInfo> r4 = r7.authorizationInfo_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.Parser r5 = com.google.cloud.audit.AuthorizationInfo.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r5 = r8.readMessage(r5, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r4.add(r5)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x00b6:
            java.lang.String r4 = r8.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.methodName_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x00be:
            java.lang.String r4 = r8.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.serviceName_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x00c6:
            com.google.cloud.audit.RequestMetadata r4 = r7.requestMetadata_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r4 == 0) goto L_0x00d0
            com.google.cloud.audit.RequestMetadata r4 = r7.requestMetadata_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.cloud.audit.RequestMetadata$Builder r6 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x00d0:
            com.google.protobuf.Parser r4 = com.google.cloud.audit.RequestMetadata.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r4 = r8.readMessage(r4, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.cloud.audit.RequestMetadata r4 = (com.google.cloud.audit.RequestMetadata) r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.requestMetadata_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r6 == 0) goto L_0x000b
            com.google.cloud.audit.RequestMetadata r4 = r7.requestMetadata_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r6.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.cloud.audit.RequestMetadata r4 = r6.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.requestMetadata_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x00eb:
            com.google.cloud.audit.AuthenticationInfo r4 = r7.authenticationInfo_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r4 == 0) goto L_0x00f5
            com.google.cloud.audit.AuthenticationInfo r4 = r7.authenticationInfo_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.cloud.audit.AuthenticationInfo$Builder r6 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x00f5:
            com.google.protobuf.Parser r4 = com.google.cloud.audit.AuthenticationInfo.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r4 = r8.readMessage(r4, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.cloud.audit.AuthenticationInfo r4 = (com.google.cloud.audit.AuthenticationInfo) r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.authenticationInfo_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r6 == 0) goto L_0x000b
            com.google.cloud.audit.AuthenticationInfo r4 = r7.authenticationInfo_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r6.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.cloud.audit.AuthenticationInfo r4 = r6.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.authenticationInfo_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x0110:
            com.google.rpc.Status r4 = r7.status_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r4 == 0) goto L_0x011a
            com.google.rpc.Status r4 = r7.status_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.rpc.Status$Builder r6 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
        L_0x011a:
            com.google.protobuf.Parser r4 = com.google.rpc.Status.parser()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.protobuf.MessageLite r4 = r8.readMessage(r4, r9)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.rpc.Status r4 = (com.google.rpc.Status) r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.status_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            if (r6 == 0) goto L_0x000b
            com.google.rpc.Status r4 = r7.status_     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r6.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            com.google.rpc.Status r4 = r6.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            r7.status_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014a, IOException -> 0x013f }
            goto L_0x000b
        L_0x0135:
            r1 = 1
            goto L_0x000b
        L_0x0138:
            if (r3 != 0) goto L_0x000b
            r1 = 1
            goto L_0x000b
        L_0x013d:
            r8 = move-exception
            goto L_0x0150
        L_0x013f:
            r8 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r9 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x013d }
            r9.<init>(r8)     // Catch:{ all -> 0x013d }
            com.google.protobuf.InvalidProtocolBufferException r8 = r9.setUnfinishedMessage(r7)     // Catch:{ all -> 0x013d }
            throw r8     // Catch:{ all -> 0x013d }
        L_0x014a:
            r8 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r8 = r8.setUnfinishedMessage(r7)     // Catch:{ all -> 0x013d }
            throw r8     // Catch:{ all -> 0x013d }
        L_0x0150:
            r9 = r2 & 64
            if (r9 != r3) goto L_0x015c
            java.util.List<com.google.cloud.audit.AuthorizationInfo> r9 = r7.authorizationInfo_
            java.util.List r9 = java.util.Collections.unmodifiableList(r9)
            r7.authorizationInfo_ = r9
        L_0x015c:
            com.google.protobuf.UnknownFieldSet r9 = r0.build()
            r7.unknownFields = r9
            r7.makeExtensionsImmutable()
            throw r8
        L_0x0166:
            r8 = r2 & 64
            if (r8 != r3) goto L_0x0172
            java.util.List<com.google.cloud.audit.AuthorizationInfo> r8 = r7.authorizationInfo_
            java.util.List r8 = java.util.Collections.unmodifiableList(r8)
            r7.authorizationInfo_ = r8
        L_0x0172:
            com.google.protobuf.UnknownFieldSet r8 = r0.build()
            r7.unknownFields = r8
            r7.makeExtensionsImmutable()
            return
        L_0x017c:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.AuditLog.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_AuditLog_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditLog.class, Builder.class);
    }

    public String getServiceName() {
        Object obj = this.serviceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.serviceName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getServiceNameBytes() {
        Object obj = this.serviceName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.serviceName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getMethodName() {
        Object obj = this.methodName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.methodName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getMethodNameBytes() {
        Object obj = this.methodName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.methodName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getResourceName() {
        Object obj = this.resourceName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getResourceNameBytes() {
        Object obj = this.resourceName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.resourceName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    public boolean hasStatus() {
        return this.status_ != null;
    }

    public Status getStatus() {
        Status status = this.status_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    public StatusOrBuilder getStatusOrBuilder() {
        return getStatus();
    }

    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    public AuthenticationInfo getAuthenticationInfo() {
        AuthenticationInfo authenticationInfo = this.authenticationInfo_;
        return authenticationInfo == null ? AuthenticationInfo.getDefaultInstance() : authenticationInfo;
    }

    public AuthenticationInfoOrBuilder getAuthenticationInfoOrBuilder() {
        return getAuthenticationInfo();
    }

    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    public AuthorizationInfo getAuthorizationInfo(int i) {
        return (AuthorizationInfo) this.authorizationInfo_.get(i);
    }

    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int i) {
        return (AuthorizationInfoOrBuilder) this.authorizationInfo_.get(i);
    }

    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    public RequestMetadata getRequestMetadata() {
        RequestMetadata requestMetadata = this.requestMetadata_;
        return requestMetadata == null ? RequestMetadata.getDefaultInstance() : requestMetadata;
    }

    public RequestMetadataOrBuilder getRequestMetadataOrBuilder() {
        return getRequestMetadata();
    }

    public boolean hasRequest() {
        return this.request_ != null;
    }

    public Struct getRequest() {
        Struct struct = this.request_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    public StructOrBuilder getRequestOrBuilder() {
        return getRequest();
    }

    public boolean hasResponse() {
        return this.response_ != null;
    }

    public Struct getResponse() {
        Struct struct = this.response_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    public StructOrBuilder getResponseOrBuilder() {
        return getResponse();
    }

    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    public Any getServiceData() {
        Any any = this.serviceData_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    public AnyOrBuilder getServiceDataOrBuilder() {
        return getServiceData();
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
        if (this.status_ != null) {
            codedOutputStream.writeMessage(2, getStatus());
        }
        if (this.authenticationInfo_ != null) {
            codedOutputStream.writeMessage(3, getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            codedOutputStream.writeMessage(4, getRequestMetadata());
        }
        if (!getServiceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.serviceName_);
        }
        if (!getMethodNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.methodName_);
        }
        for (int i = 0; i < this.authorizationInfo_.size(); i++) {
            codedOutputStream.writeMessage(9, (MessageLite) this.authorizationInfo_.get(i));
        }
        if (!getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.resourceName_);
        }
        long j = this.numResponseItems_;
        if (j != 0) {
            codedOutputStream.writeInt64(12, j);
        }
        if (this.serviceData_ != null) {
            codedOutputStream.writeMessage(15, getServiceData());
        }
        if (this.request_ != null) {
            codedOutputStream.writeMessage(16, getRequest());
        }
        if (this.response_ != null) {
            codedOutputStream.writeMessage(17, getResponse());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.status_ != null ? CodedOutputStream.computeMessageSize(2, getStatus()) + 0 : 0;
        if (this.authenticationInfo_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, getAuthenticationInfo());
        }
        if (this.requestMetadata_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(4, getRequestMetadata());
        }
        if (!getServiceNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(7, this.serviceName_);
        }
        if (!getMethodNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(8, this.methodName_);
        }
        for (int i2 = 0; i2 < this.authorizationInfo_.size(); i2++) {
            computeMessageSize += CodedOutputStream.computeMessageSize(9, (MessageLite) this.authorizationInfo_.get(i2));
        }
        if (!getResourceNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(11, this.resourceName_);
        }
        long j = this.numResponseItems_;
        if (j != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(12, j);
        }
        if (this.serviceData_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(15, getServiceData());
        }
        if (this.request_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(16, getRequest());
        }
        if (this.response_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(17, getResponse());
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuditLog)) {
            return super.equals(obj);
        }
        AuditLog auditLog = (AuditLog) obj;
        boolean z2 = ((((getServiceName().equals(auditLog.getServiceName())) && getMethodName().equals(auditLog.getMethodName())) && getResourceName().equals(auditLog.getResourceName())) && (getNumResponseItems() > auditLog.getNumResponseItems() ? 1 : (getNumResponseItems() == auditLog.getNumResponseItems() ? 0 : -1)) == 0) && hasStatus() == auditLog.hasStatus();
        if (hasStatus()) {
            z2 = z2 && getStatus().equals(auditLog.getStatus());
        }
        boolean z3 = z2 && hasAuthenticationInfo() == auditLog.hasAuthenticationInfo();
        if (hasAuthenticationInfo()) {
            z3 = z3 && getAuthenticationInfo().equals(auditLog.getAuthenticationInfo());
        }
        boolean z4 = (z3 && getAuthorizationInfoList().equals(auditLog.getAuthorizationInfoList())) && hasRequestMetadata() == auditLog.hasRequestMetadata();
        if (hasRequestMetadata()) {
            z4 = z4 && getRequestMetadata().equals(auditLog.getRequestMetadata());
        }
        boolean z5 = z4 && hasRequest() == auditLog.hasRequest();
        if (hasRequest()) {
            z5 = z5 && getRequest().equals(auditLog.getRequest());
        }
        boolean z6 = z5 && hasResponse() == auditLog.hasResponse();
        if (hasResponse()) {
            z6 = z6 && getResponse().equals(auditLog.getResponse());
        }
        boolean z7 = z6 && hasServiceData() == auditLog.hasServiceData();
        if (hasServiceData()) {
            z7 = z7 && getServiceData().equals(auditLog.getServiceData());
        }
        if (!z7 || !this.unknownFields.equals(auditLog.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 7) * 53) + getServiceName().hashCode()) * 37) + 8) * 53) + getMethodName().hashCode()) * 37) + 11) * 53) + getResourceName().hashCode()) * 37) + 12) * 53) + Internal.hashLong(getNumResponseItems());
        if (hasStatus()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getStatus().hashCode();
        }
        if (hasAuthenticationInfo()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getAuthenticationInfo().hashCode();
        }
        if (getAuthorizationInfoCount() > 0) {
            hashCode = (((hashCode * 37) + 9) * 53) + getAuthorizationInfoList().hashCode();
        }
        if (hasRequestMetadata()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getRequestMetadata().hashCode();
        }
        if (hasRequest()) {
            hashCode = (((hashCode * 37) + 16) * 53) + getRequest().hashCode();
        }
        if (hasResponse()) {
            hashCode = (((hashCode * 37) + 17) * 53) + getResponse().hashCode();
        }
        if (hasServiceData()) {
            hashCode = (((hashCode * 37) + 15) * 53) + getServiceData().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteBuffer);
    }

    public static AuditLog parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuditLog parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteString);
    }

    public static AuditLog parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuditLog parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(bArr);
    }

    public static AuditLog parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuditLog) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuditLog parseFrom(InputStream inputStream) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuditLog parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuditLog parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuditLog parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuditLog) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuditLog auditLog) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(auditLog);
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

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditLog> parser() {
        return PARSER;
    }

    public Parser<AuditLog> getParserForType() {
        return PARSER;
    }

    public AuditLog getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
