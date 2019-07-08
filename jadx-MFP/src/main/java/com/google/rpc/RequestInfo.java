package com.google.rpc;

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

public final class RequestInfo extends GeneratedMessageV3 implements RequestInfoOrBuilder {
    private static final RequestInfo DEFAULT_INSTANCE = new RequestInfo();
    /* access modifiers changed from: private */
    public static final Parser<RequestInfo> PARSER = new AbstractParser<RequestInfo>() {
        public RequestInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RequestInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    public static final int SERVING_DATA_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object requestId_;
    /* access modifiers changed from: private */
    public volatile Object servingData_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements RequestInfoOrBuilder {
        private Object requestId_;
        private Object servingData_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestInfo.class, Builder.class);
        }

        private Builder() {
            this.requestId_ = "";
            this.servingData_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.requestId_ = "";
            this.servingData_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            RequestInfo.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.requestId_ = "";
            this.servingData_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_descriptor;
        }

        public RequestInfo getDefaultInstanceForType() {
            return RequestInfo.getDefaultInstance();
        }

        public RequestInfo build() {
            RequestInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public RequestInfo buildPartial() {
            RequestInfo requestInfo = new RequestInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            requestInfo.requestId_ = this.requestId_;
            requestInfo.servingData_ = this.servingData_;
            onBuilt();
            return requestInfo;
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
            if (message instanceof RequestInfo) {
                return mergeFrom((RequestInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RequestInfo requestInfo) {
            if (requestInfo == RequestInfo.getDefaultInstance()) {
                return this;
            }
            if (!requestInfo.getRequestId().isEmpty()) {
                this.requestId_ = requestInfo.requestId_;
                onChanged();
            }
            if (!requestInfo.getServingData().isEmpty()) {
                this.servingData_ = requestInfo.servingData_;
                onChanged();
            }
            mergeUnknownFields(requestInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.RequestInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.RequestInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.RequestInfo r3 = (com.google.rpc.RequestInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.RequestInfo r4 = (com.google.rpc.RequestInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.RequestInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.RequestInfo$Builder");
        }

        public String getRequestId() {
            Object obj = this.requestId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.requestId_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getRequestIdBytes() {
            Object obj = this.requestId_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.requestId_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setRequestId(String str) {
            if (str != null) {
                this.requestId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearRequestId() {
            this.requestId_ = RequestInfo.getDefaultInstance().getRequestId();
            onChanged();
            return this;
        }

        public Builder setRequestIdBytes(ByteString byteString) {
            if (byteString != null) {
                RequestInfo.checkByteStringIsUtf8(byteString);
                this.requestId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getServingData() {
            Object obj = this.servingData_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.servingData_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getServingDataBytes() {
            Object obj = this.servingData_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.servingData_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setServingData(String str) {
            if (str != null) {
                this.servingData_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearServingData() {
            this.servingData_ = RequestInfo.getDefaultInstance().getServingData();
            onChanged();
            return this;
        }

        public Builder setServingDataBytes(ByteString byteString) {
            if (byteString != null) {
                RequestInfo.checkByteStringIsUtf8(byteString);
                this.servingData_ = byteString;
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

    private RequestInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private RequestInfo() {
        this.memoizedIsInitialized = -1;
        this.requestId_ = "";
        this.servingData_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RequestInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.requestId_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.servingData_ = codedInputStream.readStringRequireUtf8();
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
        return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_RequestInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RequestInfo.class, Builder.class);
    }

    public String getRequestId() {
        Object obj = this.requestId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.requestId_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getRequestIdBytes() {
        Object obj = this.requestId_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.requestId_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getServingData() {
        Object obj = this.servingData_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.servingData_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getServingDataBytes() {
        Object obj = this.servingData_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.servingData_ = copyFromUtf8;
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
        if (!getRequestIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requestId_);
        }
        if (!getServingDataBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.servingData_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getRequestIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.requestId_);
        }
        if (!getServingDataBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.servingData_);
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
        if (!(obj instanceof RequestInfo)) {
            return super.equals(obj);
        }
        RequestInfo requestInfo = (RequestInfo) obj;
        if (!((getRequestId().equals(requestInfo.getRequestId())) && getServingData().equals(requestInfo.getServingData())) || !this.unknownFields.equals(requestInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRequestId().hashCode()) * 37) + 2) * 53) + getServingData().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static RequestInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteBuffer);
    }

    public static RequestInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteString);
    }

    public static RequestInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(bArr);
    }

    public static RequestInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(InputStream inputStream) throws IOException {
        return (RequestInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RequestInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RequestInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RequestInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RequestInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RequestInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RequestInfo requestInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(requestInfo);
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

    public static RequestInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestInfo> parser() {
        return PARSER;
    }

    public Parser<RequestInfo> getParserForType() {
        return PARSER;
    }

    public RequestInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
