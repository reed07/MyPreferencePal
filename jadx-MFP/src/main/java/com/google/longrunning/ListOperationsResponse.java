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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListOperationsResponse extends GeneratedMessageV3 implements ListOperationsResponseOrBuilder {
    private static final ListOperationsResponse DEFAULT_INSTANCE = new ListOperationsResponse();
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    public static final int OPERATIONS_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<ListOperationsResponse> PARSER = new AbstractParser<ListOperationsResponse>() {
        public ListOperationsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListOperationsResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object nextPageToken_;
    /* access modifiers changed from: private */
    public List<Operation> operations_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ListOperationsResponseOrBuilder {
        private int bitField0_;
        private Object nextPageToken_;
        private RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> operationsBuilder_;
        private List<Operation> operations_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListOperationsResponse.class, Builder.class);
        }

        private Builder() {
            this.operations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.operations_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListOperationsResponse.alwaysUseFieldBuilders) {
                getOperationsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.operations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_descriptor;
        }

        public ListOperationsResponse getDefaultInstanceForType() {
            return ListOperationsResponse.getDefaultInstance();
        }

        public ListOperationsResponse build() {
            ListOperationsResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ListOperationsResponse buildPartial() {
            ListOperationsResponse listOperationsResponse = new ListOperationsResponse((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.operations_ = Collections.unmodifiableList(this.operations_);
                    this.bitField0_ &= -2;
                }
                listOperationsResponse.operations_ = this.operations_;
            } else {
                listOperationsResponse.operations_ = repeatedFieldBuilderV3.build();
            }
            listOperationsResponse.nextPageToken_ = this.nextPageToken_;
            listOperationsResponse.bitField0_ = 0;
            onBuilt();
            return listOperationsResponse;
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
            if (message instanceof ListOperationsResponse) {
                return mergeFrom((ListOperationsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListOperationsResponse listOperationsResponse) {
            if (listOperationsResponse == ListOperationsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.operationsBuilder_ == null) {
                if (!listOperationsResponse.operations_.isEmpty()) {
                    if (this.operations_.isEmpty()) {
                        this.operations_ = listOperationsResponse.operations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureOperationsIsMutable();
                        this.operations_.addAll(listOperationsResponse.operations_);
                    }
                    onChanged();
                }
            } else if (!listOperationsResponse.operations_.isEmpty()) {
                if (this.operationsBuilder_.isEmpty()) {
                    this.operationsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = null;
                    this.operationsBuilder_ = null;
                    this.operations_ = listOperationsResponse.operations_;
                    this.bitField0_ &= -2;
                    if (ListOperationsResponse.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getOperationsFieldBuilder();
                    }
                    this.operationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.operationsBuilder_.addAllMessages(listOperationsResponse.operations_);
                }
            }
            if (!listOperationsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listOperationsResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listOperationsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.longrunning.ListOperationsResponse.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.longrunning.ListOperationsResponse.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.longrunning.ListOperationsResponse r3 = (com.google.longrunning.ListOperationsResponse) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.longrunning.ListOperationsResponse r4 = (com.google.longrunning.ListOperationsResponse) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.ListOperationsResponse.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.longrunning.ListOperationsResponse$Builder");
        }

        private void ensureOperationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.operations_ = new ArrayList(this.operations_);
                this.bitField0_ |= 1;
            }
        }

        public List<Operation> getOperationsList() {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.operations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getOperationsCount() {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.operations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Operation getOperations(int i) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Operation) this.operations_.get(i);
            }
            return (Operation) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setOperations(int i, Operation operation) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, operation);
            } else if (operation != null) {
                ensureOperationsIsMutable();
                this.operations_.set(i, operation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setOperations(int i, com.google.longrunning.Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addOperations(Operation operation) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(operation);
            } else if (operation != null) {
                ensureOperationsIsMutable();
                this.operations_.add(operation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addOperations(int i, Operation operation) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, operation);
            } else if (operation != null) {
                ensureOperationsIsMutable();
                this.operations_.add(i, operation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addOperations(com.google.longrunning.Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addOperations(int i, com.google.longrunning.Operation.Builder builder) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllOperations(Iterable<? extends Operation> iterable) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.operations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOperations() {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.operations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeOperations(int i) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOperationsIsMutable();
                this.operations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.longrunning.Operation.Builder getOperationsBuilder(int i) {
            return (com.google.longrunning.Operation.Builder) getOperationsFieldBuilder().getBuilder(i);
        }

        public OperationOrBuilder getOperationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (OperationOrBuilder) this.operations_.get(i);
            }
            return (OperationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
            RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> repeatedFieldBuilderV3 = this.operationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.operations_);
        }

        public com.google.longrunning.Operation.Builder addOperationsBuilder() {
            return (com.google.longrunning.Operation.Builder) getOperationsFieldBuilder().addBuilder(Operation.getDefaultInstance());
        }

        public com.google.longrunning.Operation.Builder addOperationsBuilder(int i) {
            return (com.google.longrunning.Operation.Builder) getOperationsFieldBuilder().addBuilder(i, Operation.getDefaultInstance());
        }

        public List<com.google.longrunning.Operation.Builder> getOperationsBuilderList() {
            return getOperationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Operation, com.google.longrunning.Operation.Builder, OperationOrBuilder> getOperationsFieldBuilder() {
            if (this.operationsBuilder_ == null) {
                List<Operation> list = this.operations_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.operationsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.operations_ = null;
            }
            return this.operationsBuilder_;
        }

        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.nextPageToken_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNextPageTokenBytes() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nextPageToken_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setNextPageToken(String str) {
            if (str != null) {
                this.nextPageToken_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearNextPageToken() {
            this.nextPageToken_ = ListOperationsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListOperationsResponse.checkByteStringIsUtf8(byteString);
                this.nextPageToken_ = byteString;
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

    private ListOperationsResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ListOperationsResponse() {
        this.memoizedIsInitialized = -1;
        this.operations_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListOperationsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 10) {
                        if (!z2 || !true) {
                            this.operations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.operations_.add(codedInputStream.readMessage(Operation.parser(), extensionRegistryLite));
                    } else if (readTag == 18) {
                        this.nextPageToken_ = codedInputStream.readStringRequireUtf8();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.operations_ = Collections.unmodifiableList(this.operations_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.operations_ = Collections.unmodifiableList(this.operations_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return OperationsProto.internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ListOperationsResponse.class, Builder.class);
    }

    public List<Operation> getOperationsList() {
        return this.operations_;
    }

    public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
        return this.operations_;
    }

    public int getOperationsCount() {
        return this.operations_.size();
    }

    public Operation getOperations(int i) {
        return (Operation) this.operations_.get(i);
    }

    public OperationOrBuilder getOperationsOrBuilder(int i) {
        return (OperationOrBuilder) this.operations_.get(i);
    }

    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNextPageTokenBytes() {
        Object obj = this.nextPageToken_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.nextPageToken_ = copyFromUtf8;
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
        for (int i = 0; i < this.operations_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.operations_.get(i));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.nextPageToken_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.operations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.operations_.get(i3));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
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
        if (!(obj instanceof ListOperationsResponse)) {
            return super.equals(obj);
        }
        ListOperationsResponse listOperationsResponse = (ListOperationsResponse) obj;
        if (!((getOperationsList().equals(listOperationsResponse.getOperationsList())) && getNextPageToken().equals(listOperationsResponse.getNextPageToken())) || !this.unknownFields.equals(listOperationsResponse.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getOperationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getOperationsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListOperationsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteBuffer);
    }

    public static ListOperationsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteString);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(bArr);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListOperationsResponse listOperationsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listOperationsResponse);
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

    public static ListOperationsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListOperationsResponse> parser() {
        return PARSER;
    }

    public Parser<ListOperationsResponse> getParserForType() {
        return PARSER;
    }

    public ListOperationsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
