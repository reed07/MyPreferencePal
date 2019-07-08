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

public final class ResourceInfo extends GeneratedMessageV3 implements ResourceInfoOrBuilder {
    private static final ResourceInfo DEFAULT_INSTANCE = new ResourceInfo();
    public static final int DESCRIPTION_FIELD_NUMBER = 4;
    public static final int OWNER_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<ResourceInfo> PARSER = new AbstractParser<ResourceInfo>() {
        public ResourceInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ResourceInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RESOURCE_NAME_FIELD_NUMBER = 2;
    public static final int RESOURCE_TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object description_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object owner_;
    /* access modifiers changed from: private */
    public volatile Object resourceName_;
    /* access modifiers changed from: private */
    public volatile Object resourceType_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ResourceInfoOrBuilder {
        private Object description_;
        private Object owner_;
        private Object resourceName_;
        private Object resourceType_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
        }

        private Builder() {
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            ResourceInfo.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.resourceType_ = "";
            this.resourceName_ = "";
            this.owner_ = "";
            this.description_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
        }

        public ResourceInfo getDefaultInstanceForType() {
            return ResourceInfo.getDefaultInstance();
        }

        public ResourceInfo build() {
            ResourceInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ResourceInfo buildPartial() {
            ResourceInfo resourceInfo = new ResourceInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            resourceInfo.resourceType_ = this.resourceType_;
            resourceInfo.resourceName_ = this.resourceName_;
            resourceInfo.owner_ = this.owner_;
            resourceInfo.description_ = this.description_;
            onBuilt();
            return resourceInfo;
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
            if (message instanceof ResourceInfo) {
                return mergeFrom((ResourceInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ResourceInfo resourceInfo) {
            if (resourceInfo == ResourceInfo.getDefaultInstance()) {
                return this;
            }
            if (!resourceInfo.getResourceType().isEmpty()) {
                this.resourceType_ = resourceInfo.resourceType_;
                onChanged();
            }
            if (!resourceInfo.getResourceName().isEmpty()) {
                this.resourceName_ = resourceInfo.resourceName_;
                onChanged();
            }
            if (!resourceInfo.getOwner().isEmpty()) {
                this.owner_ = resourceInfo.owner_;
                onChanged();
            }
            if (!resourceInfo.getDescription().isEmpty()) {
                this.description_ = resourceInfo.description_;
                onChanged();
            }
            mergeUnknownFields(resourceInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.ResourceInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.ResourceInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.ResourceInfo r3 = (com.google.rpc.ResourceInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.ResourceInfo r4 = (com.google.rpc.ResourceInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.ResourceInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.ResourceInfo$Builder");
        }

        public String getResourceType() {
            Object obj = this.resourceType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.resourceType_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getResourceTypeBytes() {
            Object obj = this.resourceType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.resourceType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setResourceType(String str) {
            if (str != null) {
                this.resourceType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearResourceType() {
            this.resourceType_ = ResourceInfo.getDefaultInstance().getResourceType();
            onChanged();
            return this;
        }

        public Builder setResourceTypeBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.resourceType_ = byteString;
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
            this.resourceName_ = ResourceInfo.getDefaultInstance().getResourceName();
            onChanged();
            return this;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.resourceName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getOwner() {
            Object obj = this.owner_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.owner_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getOwnerBytes() {
            Object obj = this.owner_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.owner_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setOwner(String str) {
            if (str != null) {
                this.owner_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearOwner() {
            this.owner_ = ResourceInfo.getDefaultInstance().getOwner();
            onChanged();
            return this;
        }

        public Builder setOwnerBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.owner_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDescription(String str) {
            if (str != null) {
                this.description_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDescription() {
            this.description_ = ResourceInfo.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                ResourceInfo.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
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

    private ResourceInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ResourceInfo() {
        this.memoizedIsInitialized = -1;
        this.resourceType_ = "";
        this.resourceName_ = "";
        this.owner_ = "";
        this.description_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ResourceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.resourceType_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.resourceName_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        this.owner_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 34) {
                        this.description_ = codedInputStream.readStringRequireUtf8();
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
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_ResourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ResourceInfo.class, Builder.class);
    }

    public String getResourceType() {
        Object obj = this.resourceType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.resourceType_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getResourceTypeBytes() {
        Object obj = this.resourceType_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.resourceType_ = copyFromUtf8;
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

    public String getOwner() {
        Object obj = this.owner_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.owner_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getOwnerBytes() {
        Object obj = this.owner_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.owner_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.description_ = copyFromUtf8;
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
        if (!getResourceTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.resourceType_);
        }
        if (!getResourceNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.resourceName_);
        }
        if (!getOwnerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.owner_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.description_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getResourceTypeBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.resourceType_);
        }
        if (!getResourceNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.resourceName_);
        }
        if (!getOwnerBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.owner_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.description_);
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
        if (!(obj instanceof ResourceInfo)) {
            return super.equals(obj);
        }
        ResourceInfo resourceInfo = (ResourceInfo) obj;
        if (!((((getResourceType().equals(resourceInfo.getResourceType())) && getResourceName().equals(resourceInfo.getResourceName())) && getOwner().equals(resourceInfo.getOwner())) && getDescription().equals(resourceInfo.getDescription())) || !this.unknownFields.equals(resourceInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getResourceType().hashCode()) * 37) + 2) * 53) + getResourceName().hashCode()) * 37) + 3) * 53) + getOwner().hashCode()) * 37) + 4) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteBuffer);
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteString);
    }

    public static ResourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(bArr);
    }

    public static ResourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(InputStream inputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ResourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResourceInfo resourceInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(resourceInfo);
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

    public static ResourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceInfo> parser() {
        return PARSER;
    }

    public Parser<ResourceInfo> getParserForType() {
        return PARSER;
    }

    public ResourceInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
