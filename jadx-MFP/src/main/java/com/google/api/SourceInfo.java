package com.google.api;

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

public final class SourceInfo extends GeneratedMessageV3 implements SourceInfoOrBuilder {
    private static final SourceInfo DEFAULT_INSTANCE = new SourceInfo();
    /* access modifiers changed from: private */
    public static final Parser<SourceInfo> PARSER = new AbstractParser<SourceInfo>() {
        public SourceInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SourceInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_FILES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<Any> sourceFiles_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SourceInfoOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> sourceFilesBuilder_;
        private List<Any> sourceFiles_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
        }

        private Builder() {
            this.sourceFiles_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.sourceFiles_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SourceInfo.alwaysUseFieldBuilders) {
                getSourceFilesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sourceFiles_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
        }

        public SourceInfo getDefaultInstanceForType() {
            return SourceInfo.getDefaultInstance();
        }

        public SourceInfo build() {
            SourceInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public SourceInfo buildPartial() {
            SourceInfo sourceInfo = new SourceInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
                    this.bitField0_ &= -2;
                }
                sourceInfo.sourceFiles_ = this.sourceFiles_;
            } else {
                sourceInfo.sourceFiles_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return sourceInfo;
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
            if (message instanceof SourceInfo) {
                return mergeFrom((SourceInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SourceInfo sourceInfo) {
            if (sourceInfo == SourceInfo.getDefaultInstance()) {
                return this;
            }
            if (this.sourceFilesBuilder_ == null) {
                if (!sourceInfo.sourceFiles_.isEmpty()) {
                    if (this.sourceFiles_.isEmpty()) {
                        this.sourceFiles_ = sourceInfo.sourceFiles_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSourceFilesIsMutable();
                        this.sourceFiles_.addAll(sourceInfo.sourceFiles_);
                    }
                    onChanged();
                }
            } else if (!sourceInfo.sourceFiles_.isEmpty()) {
                if (this.sourceFilesBuilder_.isEmpty()) {
                    this.sourceFilesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = null;
                    this.sourceFilesBuilder_ = null;
                    this.sourceFiles_ = sourceInfo.sourceFiles_;
                    this.bitField0_ &= -2;
                    if (SourceInfo.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getSourceFilesFieldBuilder();
                    }
                    this.sourceFilesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.sourceFilesBuilder_.addAllMessages(sourceInfo.sourceFiles_);
                }
            }
            mergeUnknownFields(sourceInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.SourceInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.SourceInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.SourceInfo r3 = (com.google.api.SourceInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.SourceInfo r4 = (com.google.api.SourceInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.SourceInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.SourceInfo$Builder");
        }

        private void ensureSourceFilesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.sourceFiles_ = new ArrayList(this.sourceFiles_);
                this.bitField0_ |= 1;
            }
        }

        public List<Any> getSourceFilesList() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.sourceFiles_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getSourceFilesCount() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourceFiles_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Any getSourceFiles(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Any) this.sourceFiles_.get(i);
            }
            return (Any) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSourceFiles(int i, Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, any);
            } else if (any != null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.set(i, any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSourceFiles(int i, com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSourceFiles(Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(any);
            } else if (any != null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addSourceFiles(int i, Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, any);
            } else if (any != null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(i, any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addSourceFiles(com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSourceFiles(int i, com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSourceFiles(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.sourceFiles_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSourceFiles() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sourceFiles_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSourceFiles(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getSourceFilesBuilder(int i) {
            return (com.google.protobuf.Any.Builder) getSourceFilesFieldBuilder().getBuilder(i);
        }

        public AnyOrBuilder getSourceFilesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AnyOrBuilder) this.sourceFiles_.get(i);
            }
            return (AnyOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sourceFiles_);
        }

        public com.google.protobuf.Any.Builder addSourceFilesBuilder() {
            return (com.google.protobuf.Any.Builder) getSourceFilesFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public com.google.protobuf.Any.Builder addSourceFilesBuilder(int i) {
            return (com.google.protobuf.Any.Builder) getSourceFilesFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<com.google.protobuf.Any.Builder> getSourceFilesBuilderList() {
            return getSourceFilesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getSourceFilesFieldBuilder() {
            if (this.sourceFilesBuilder_ == null) {
                List<Any> list = this.sourceFiles_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.sourceFilesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.sourceFiles_ = null;
            }
            return this.sourceFilesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private SourceInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private SourceInfo() {
        this.memoizedIsInitialized = -1;
        this.sourceFiles_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SourceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.sourceFiles_ = new ArrayList();
                            z2 |= true;
                        }
                        this.sourceFiles_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return SourceInfoProto.internal_static_google_api_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
    }

    public List<Any> getSourceFilesList() {
        return this.sourceFiles_;
    }

    public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
        return this.sourceFiles_;
    }

    public int getSourceFilesCount() {
        return this.sourceFiles_.size();
    }

    public Any getSourceFiles(int i) {
        return (Any) this.sourceFiles_.get(i);
    }

    public AnyOrBuilder getSourceFilesOrBuilder(int i) {
        return (AnyOrBuilder) this.sourceFiles_.get(i);
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
        for (int i = 0; i < this.sourceFiles_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.sourceFiles_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.sourceFiles_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.sourceFiles_.get(i3));
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
        if (!(obj instanceof SourceInfo)) {
            return super.equals(obj);
        }
        SourceInfo sourceInfo = (SourceInfo) obj;
        if (!(getSourceFilesList().equals(sourceInfo.getSourceFilesList())) || !this.unknownFields.equals(sourceInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getSourceFilesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSourceFilesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteBuffer);
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteString);
    }

    public static SourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(bArr);
    }

    public static SourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SourceInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(InputStream inputStream) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SourceInfo sourceInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(sourceInfo);
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

    public static SourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SourceInfo> parser() {
        return PARSER;
    }

    public Parser<SourceInfo> getParserForType() {
        return PARSER;
    }

    public SourceInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
