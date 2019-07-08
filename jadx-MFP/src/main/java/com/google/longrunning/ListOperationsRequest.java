package com.google.longrunning;

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

public final class ListOperationsRequest extends GeneratedMessageV3 implements ListOperationsRequestOrBuilder {
    private static final ListOperationsRequest DEFAULT_INSTANCE = new ListOperationsRequest();
    public static final int FILTER_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 4;
    public static final int PAGE_SIZE_FIELD_NUMBER = 2;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<ListOperationsRequest> PARSER = new AbstractParser<ListOperationsRequest>() {
        public ListOperationsRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListOperationsRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object filter_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public int pageSize_;
    /* access modifiers changed from: private */
    public volatile Object pageToken_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ListOperationsRequestOrBuilder {
        private Object filter_;
        private Object name_;
        private int pageSize_;
        private Object pageToken_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsRequest_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListOperationsRequest.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.filter_ = "";
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.filter_ = "";
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            ListOperationsRequest.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.filter_ = "";
            this.pageSize_ = 0;
            this.pageToken_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsRequest_descriptor;
        }

        public ListOperationsRequest getDefaultInstanceForType() {
            return ListOperationsRequest.getDefaultInstance();
        }

        public ListOperationsRequest build() {
            ListOperationsRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ListOperationsRequest buildPartial() {
            ListOperationsRequest listOperationsRequest = new ListOperationsRequest((com.google.protobuf.GeneratedMessageV3.Builder) this);
            listOperationsRequest.name_ = this.name_;
            listOperationsRequest.filter_ = this.filter_;
            listOperationsRequest.pageSize_ = this.pageSize_;
            listOperationsRequest.pageToken_ = this.pageToken_;
            onBuilt();
            return listOperationsRequest;
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
            if (message instanceof ListOperationsRequest) {
                return mergeFrom((ListOperationsRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListOperationsRequest listOperationsRequest) {
            if (listOperationsRequest == ListOperationsRequest.getDefaultInstance()) {
                return this;
            }
            if (!listOperationsRequest.getName().isEmpty()) {
                this.name_ = listOperationsRequest.name_;
                onChanged();
            }
            if (!listOperationsRequest.getFilter().isEmpty()) {
                this.filter_ = listOperationsRequest.filter_;
                onChanged();
            }
            if (listOperationsRequest.getPageSize() != 0) {
                setPageSize(listOperationsRequest.getPageSize());
            }
            if (!listOperationsRequest.getPageToken().isEmpty()) {
                this.pageToken_ = listOperationsRequest.pageToken_;
                onChanged();
            }
            mergeUnknownFields(listOperationsRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.longrunning.ListOperationsRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.ListOperationsRequest.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.longrunning.ListOperationsRequest r3 = (com.google.longrunning.ListOperationsRequest) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.longrunning.ListOperationsRequest r4 = (com.google.longrunning.ListOperationsRequest) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.ListOperationsRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.ListOperationsRequest$Builder");
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
            this.name_ = ListOperationsRequest.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                ListOperationsRequest.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getFilter() {
            Object obj = this.filter_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.filter_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getFilterBytes() {
            Object obj = this.filter_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.filter_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setFilter(String str) {
            if (str != null) {
                this.filter_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearFilter() {
            this.filter_ = ListOperationsRequest.getDefaultInstance().getFilter();
            onChanged();
            return this;
        }

        public Builder setFilterBytes(ByteString byteString) {
            if (byteString != null) {
                ListOperationsRequest.checkByteStringIsUtf8(byteString);
                this.filter_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public int getPageSize() {
            return this.pageSize_;
        }

        public Builder setPageSize(int i) {
            this.pageSize_ = i;
            onChanged();
            return this;
        }

        public Builder clearPageSize() {
            this.pageSize_ = 0;
            onChanged();
            return this;
        }

        public String getPageToken() {
            Object obj = this.pageToken_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.pageToken_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getPageTokenBytes() {
            Object obj = this.pageToken_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.pageToken_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPageToken(String str) {
            if (str != null) {
                this.pageToken_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPageToken() {
            this.pageToken_ = ListOperationsRequest.getDefaultInstance().getPageToken();
            onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListOperationsRequest.checkByteStringIsUtf8(byteString);
                this.pageToken_ = byteString;
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

    private ListOperationsRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ListOperationsRequest() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.filter_ = "";
        this.pageSize_ = 0;
        this.pageToken_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListOperationsRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.filter_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 16) {
                        this.pageSize_ = codedInputStream.readInt32();
                    } else if (readTag == 26) {
                        this.pageToken_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 34) {
                        this.name_ = codedInputStream.readStringRequireUtf8();
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
        return OperationsProto.internal_static_google_longrunning_ListOperationsRequest_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.internal_static_google_longrunning_ListOperationsRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ListOperationsRequest.class, Builder.class);
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

    public String getFilter() {
        Object obj = this.filter_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.filter_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getFilterBytes() {
        Object obj = this.filter_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.filter_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public int getPageSize() {
        return this.pageSize_;
    }

    public String getPageToken() {
        Object obj = this.pageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.pageToken_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getPageTokenBytes() {
        Object obj = this.pageToken_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.pageToken_ = copyFromUtf8;
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
        if (!getFilterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.filter_);
        }
        int i = this.pageSize_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (!getPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.pageToken_);
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.name_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getFilterBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.filter_);
        }
        int i3 = this.pageSize_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeInt32Size(2, i3);
        }
        if (!getPageTokenBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.pageToken_);
        }
        if (!getNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.name_);
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
        if (!(obj instanceof ListOperationsRequest)) {
            return super.equals(obj);
        }
        ListOperationsRequest listOperationsRequest = (ListOperationsRequest) obj;
        if (!((((getName().equals(listOperationsRequest.getName())) && getFilter().equals(listOperationsRequest.getFilter())) && getPageSize() == listOperationsRequest.getPageSize()) && getPageToken().equals(listOperationsRequest.getPageToken())) || !this.unknownFields.equals(listOperationsRequest.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 4) * 53) + getName().hashCode()) * 37) + 1) * 53) + getFilter().hashCode()) * 37) + 2) * 53) + getPageSize()) * 37) + 3) * 53) + getPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static ListOperationsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListOperationsRequest) PARSER.parseFrom(byteBuffer);
    }

    public static ListOperationsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListOperationsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListOperationsRequest) PARSER.parseFrom(byteString);
    }

    public static ListOperationsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListOperationsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListOperationsRequest) PARSER.parseFrom(bArr);
    }

    public static ListOperationsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListOperationsRequest parseFrom(InputStream inputStream) throws IOException {
        return (ListOperationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListOperationsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListOperationsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListOperationsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListOperationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListOperationsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListOperationsRequest listOperationsRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listOperationsRequest);
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

    public static ListOperationsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListOperationsRequest> parser() {
        return PARSER;
    }

    public Parser<ListOperationsRequest> getParserForType() {
        return PARSER;
    }

    public ListOperationsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
