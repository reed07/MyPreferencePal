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

public final class SystemParameter extends GeneratedMessageV3 implements SystemParameterOrBuilder {
    private static final SystemParameter DEFAULT_INSTANCE = new SystemParameter();
    public static final int HTTP_HEADER_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<SystemParameter> PARSER = new AbstractParser<SystemParameter>() {
        public SystemParameter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SystemParameter(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int URL_QUERY_PARAMETER_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object httpHeader_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public volatile Object urlQueryParameter_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SystemParameterOrBuilder {
        private Object httpHeader_;
        private Object name_;
        private Object urlQueryParameter_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return SystemParameterProto.internal_static_google_api_SystemParameter_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return SystemParameterProto.internal_static_google_api_SystemParameter_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameter.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.httpHeader_ = "";
            this.urlQueryParameter_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.httpHeader_ = "";
            this.urlQueryParameter_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            SystemParameter.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.httpHeader_ = "";
            this.urlQueryParameter_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return SystemParameterProto.internal_static_google_api_SystemParameter_descriptor;
        }

        public SystemParameter getDefaultInstanceForType() {
            return SystemParameter.getDefaultInstance();
        }

        public SystemParameter build() {
            SystemParameter buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public SystemParameter buildPartial() {
            SystemParameter systemParameter = new SystemParameter((com.google.protobuf.GeneratedMessageV3.Builder) this);
            systemParameter.name_ = this.name_;
            systemParameter.httpHeader_ = this.httpHeader_;
            systemParameter.urlQueryParameter_ = this.urlQueryParameter_;
            onBuilt();
            return systemParameter;
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
            if (message instanceof SystemParameter) {
                return mergeFrom((SystemParameter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SystemParameter systemParameter) {
            if (systemParameter == SystemParameter.getDefaultInstance()) {
                return this;
            }
            if (!systemParameter.getName().isEmpty()) {
                this.name_ = systemParameter.name_;
                onChanged();
            }
            if (!systemParameter.getHttpHeader().isEmpty()) {
                this.httpHeader_ = systemParameter.httpHeader_;
                onChanged();
            }
            if (!systemParameter.getUrlQueryParameter().isEmpty()) {
                this.urlQueryParameter_ = systemParameter.urlQueryParameter_;
                onChanged();
            }
            mergeUnknownFields(systemParameter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.SystemParameter.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.SystemParameter.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.SystemParameter r3 = (com.google.api.SystemParameter) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.SystemParameter r4 = (com.google.api.SystemParameter) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.SystemParameter.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.SystemParameter$Builder");
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = SystemParameter.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                SystemParameter.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getHttpHeader() {
            Object obj = this.httpHeader_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.httpHeader_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getHttpHeaderBytes() {
            Object obj = this.httpHeader_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.httpHeader_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setHttpHeader(String str) {
            if (str != null) {
                this.httpHeader_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearHttpHeader() {
            this.httpHeader_ = SystemParameter.getDefaultInstance().getHttpHeader();
            onChanged();
            return this;
        }

        public Builder setHttpHeaderBytes(ByteString byteString) {
            if (byteString != null) {
                SystemParameter.checkByteStringIsUtf8(byteString);
                this.httpHeader_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getUrlQueryParameter() {
            Object obj = this.urlQueryParameter_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.urlQueryParameter_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getUrlQueryParameterBytes() {
            Object obj = this.urlQueryParameter_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.urlQueryParameter_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setUrlQueryParameter(String str) {
            if (str != null) {
                this.urlQueryParameter_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearUrlQueryParameter() {
            this.urlQueryParameter_ = SystemParameter.getDefaultInstance().getUrlQueryParameter();
            onChanged();
            return this;
        }

        public Builder setUrlQueryParameterBytes(ByteString byteString) {
            if (byteString != null) {
                SystemParameter.checkByteStringIsUtf8(byteString);
                this.urlQueryParameter_ = byteString;
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

    private SystemParameter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private SystemParameter() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.httpHeader_ = "";
        this.urlQueryParameter_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SystemParameter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.name_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.httpHeader_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        this.urlQueryParameter_ = codedInputStream.readStringRequireUtf8();
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
        return SystemParameterProto.internal_static_google_api_SystemParameter_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return SystemParameterProto.internal_static_google_api_SystemParameter_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameter.class, Builder.class);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getHttpHeader() {
        Object obj = this.httpHeader_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.httpHeader_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getHttpHeaderBytes() {
        Object obj = this.httpHeader_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.httpHeader_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getUrlQueryParameter() {
        Object obj = this.urlQueryParameter_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.urlQueryParameter_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getUrlQueryParameterBytes() {
        Object obj = this.urlQueryParameter_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.urlQueryParameter_ = copyFromUtf8;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getHttpHeaderBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.httpHeader_);
        }
        if (!getUrlQueryParameterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.urlQueryParameter_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getNameBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        if (!getHttpHeaderBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.httpHeader_);
        }
        if (!getUrlQueryParameterBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.urlQueryParameter_);
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
        if (!(obj instanceof SystemParameter)) {
            return super.equals(obj);
        }
        SystemParameter systemParameter = (SystemParameter) obj;
        if (!(((getName().equals(systemParameter.getName())) && getHttpHeader().equals(systemParameter.getHttpHeader())) && getUrlQueryParameter().equals(systemParameter.getUrlQueryParameter())) || !this.unknownFields.equals(systemParameter.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getHttpHeader().hashCode()) * 37) + 3) * 53) + getUrlQueryParameter().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static SystemParameter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SystemParameter) PARSER.parseFrom(byteBuffer);
    }

    public static SystemParameter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameter) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SystemParameter) PARSER.parseFrom(byteString);
    }

    public static SystemParameter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameter) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SystemParameter) PARSER.parseFrom(bArr);
    }

    public static SystemParameter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameter) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(InputStream inputStream) throws IOException {
        return (SystemParameter) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SystemParameter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameter) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SystemParameter) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SystemParameter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameter) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SystemParameter) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SystemParameter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameter) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SystemParameter systemParameter) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(systemParameter);
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

    public static SystemParameter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemParameter> parser() {
        return PARSER;
    }

    public Parser<SystemParameter> getParserForType() {
        return PARSER;
    }

    public SystemParameter getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
