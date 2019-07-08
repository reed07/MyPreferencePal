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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class DebugInfo extends GeneratedMessageV3 implements DebugInfoOrBuilder {
    private static final DebugInfo DEFAULT_INSTANCE = new DebugInfo();
    public static final int DETAIL_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<DebugInfo> PARSER = new AbstractParser<DebugInfo>() {
        public DebugInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DebugInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object detail_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public LazyStringList stackEntries_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements DebugInfoOrBuilder {
        private int bitField0_;
        private Object detail_;
        private LazyStringList stackEntries_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
        }

        private Builder() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.detail_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.detail_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            DebugInfo.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.detail_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        public DebugInfo getDefaultInstanceForType() {
            return DebugInfo.getDefaultInstance();
        }

        public DebugInfo build() {
            DebugInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public DebugInfo buildPartial() {
            DebugInfo debugInfo = new DebugInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            if ((this.bitField0_ & 1) == 1) {
                this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            debugInfo.stackEntries_ = this.stackEntries_;
            debugInfo.detail_ = this.detail_;
            debugInfo.bitField0_ = 0;
            onBuilt();
            return debugInfo;
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
            if (message instanceof DebugInfo) {
                return mergeFrom((DebugInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DebugInfo debugInfo) {
            if (debugInfo == DebugInfo.getDefaultInstance()) {
                return this;
            }
            if (!debugInfo.stackEntries_.isEmpty()) {
                if (this.stackEntries_.isEmpty()) {
                    this.stackEntries_ = debugInfo.stackEntries_;
                    this.bitField0_ &= -2;
                } else {
                    ensureStackEntriesIsMutable();
                    this.stackEntries_.addAll(debugInfo.stackEntries_);
                }
                onChanged();
            }
            if (!debugInfo.getDetail().isEmpty()) {
                this.detail_ = debugInfo.detail_;
                onChanged();
            }
            mergeUnknownFields(debugInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.DebugInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.DebugInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.DebugInfo r3 = (com.google.rpc.DebugInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.DebugInfo r4 = (com.google.rpc.DebugInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.DebugInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.DebugInfo$Builder");
        }

        private void ensureStackEntriesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.stackEntries_ = new LazyStringArrayList(this.stackEntries_);
                this.bitField0_ |= 1;
            }
        }

        public ProtocolStringList getStackEntriesList() {
            return this.stackEntries_.getUnmodifiableView();
        }

        public int getStackEntriesCount() {
            return this.stackEntries_.size();
        }

        public String getStackEntries(int i) {
            return (String) this.stackEntries_.get(i);
        }

        public ByteString getStackEntriesBytes(int i) {
            return this.stackEntries_.getByteString(i);
        }

        public Builder setStackEntries(int i, String str) {
            if (str != null) {
                ensureStackEntriesIsMutable();
                this.stackEntries_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addStackEntries(String str) {
            if (str != null) {
                ensureStackEntriesIsMutable();
                this.stackEntries_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllStackEntries(Iterable<String> iterable) {
            ensureStackEntriesIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.stackEntries_);
            onChanged();
            return this;
        }

        public Builder clearStackEntries() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addStackEntriesBytes(ByteString byteString) {
            if (byteString != null) {
                DebugInfo.checkByteStringIsUtf8(byteString);
                ensureStackEntriesIsMutable();
                this.stackEntries_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDetail() {
            Object obj = this.detail_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.detail_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDetailBytes() {
            Object obj = this.detail_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.detail_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDetail(String str) {
            if (str != null) {
                this.detail_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDetail() {
            this.detail_ = DebugInfo.getDefaultInstance().getDetail();
            onChanged();
            return this;
        }

        public Builder setDetailBytes(ByteString byteString) {
            if (byteString != null) {
                DebugInfo.checkByteStringIsUtf8(byteString);
                this.detail_ = byteString;
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

    private DebugInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private DebugInfo() {
        this.memoizedIsInitialized = -1;
        this.stackEntries_ = LazyStringArrayList.EMPTY;
        this.detail_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DebugInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                        if (!z2 || !true) {
                            this.stackEntries_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.stackEntries_.add(readStringRequireUtf8);
                    } else if (readTag == 18) {
                        this.detail_ = codedInputStream.readStringRequireUtf8();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
    }

    public ProtocolStringList getStackEntriesList() {
        return this.stackEntries_;
    }

    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    public String getStackEntries(int i) {
        return (String) this.stackEntries_.get(i);
    }

    public ByteString getStackEntriesBytes(int i) {
        return this.stackEntries_.getByteString(i);
    }

    public String getDetail() {
        Object obj = this.detail_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.detail_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDetailBytes() {
        Object obj = this.detail_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.detail_ = copyFromUtf8;
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
        for (int i = 0; i < this.stackEntries_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.stackEntries_.getRaw(i));
        }
        if (!getDetailBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.detail_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.stackEntries_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.stackEntries_.getRaw(i3));
        }
        int size = 0 + i2 + (getStackEntriesList().size() * 1);
        if (!getDetailBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(2, this.detail_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DebugInfo)) {
            return super.equals(obj);
        }
        DebugInfo debugInfo = (DebugInfo) obj;
        if (!((getStackEntriesList().equals(debugInfo.getStackEntriesList())) && getDetail().equals(debugInfo.getDetail())) || !this.unknownFields.equals(debugInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getStackEntriesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getStackEntriesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getDetail().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteBuffer);
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteString);
    }

    public static DebugInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(bArr);
    }

    public static DebugInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(InputStream inputStream) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DebugInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DebugInfo debugInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(debugInfo);
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

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugInfo> parser() {
        return PARSER;
    }

    public Parser<DebugInfo> getParserForType() {
        return PARSER;
    }

    public DebugInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
