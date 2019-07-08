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

public final class ContextRule extends GeneratedMessageV3 implements ContextRuleOrBuilder {
    private static final ContextRule DEFAULT_INSTANCE = new ContextRule();
    /* access modifiers changed from: private */
    public static final Parser<ContextRule> PARSER = new AbstractParser<ContextRule>() {
        public ContextRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ContextRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROVIDED_FIELD_NUMBER = 3;
    public static final int REQUESTED_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public LazyStringList provided_;
    /* access modifiers changed from: private */
    public LazyStringList requested_;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ContextRuleOrBuilder {
        private int bitField0_;
        private LazyStringList provided_;
        private LazyStringList requested_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ContextProto.internal_static_google_api_ContextRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ContextProto.internal_static_google_api_ContextRule_fieldAccessorTable.ensureFieldAccessorsInitialized(ContextRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.requested_ = LazyStringArrayList.EMPTY;
            this.provided_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.requested_ = LazyStringArrayList.EMPTY;
            this.provided_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            ContextRule.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.requested_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            this.provided_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ContextProto.internal_static_google_api_ContextRule_descriptor;
        }

        public ContextRule getDefaultInstanceForType() {
            return ContextRule.getDefaultInstance();
        }

        public ContextRule build() {
            ContextRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ContextRule buildPartial() {
            ContextRule contextRule = new ContextRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            contextRule.selector_ = this.selector_;
            if ((this.bitField0_ & 2) == 2) {
                this.requested_ = this.requested_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            contextRule.requested_ = this.requested_;
            if ((this.bitField0_ & 4) == 4) {
                this.provided_ = this.provided_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            contextRule.provided_ = this.provided_;
            contextRule.bitField0_ = 0;
            onBuilt();
            return contextRule;
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
            if (message instanceof ContextRule) {
                return mergeFrom((ContextRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ContextRule contextRule) {
            if (contextRule == ContextRule.getDefaultInstance()) {
                return this;
            }
            if (!contextRule.getSelector().isEmpty()) {
                this.selector_ = contextRule.selector_;
                onChanged();
            }
            if (!contextRule.requested_.isEmpty()) {
                if (this.requested_.isEmpty()) {
                    this.requested_ = contextRule.requested_;
                    this.bitField0_ &= -3;
                } else {
                    ensureRequestedIsMutable();
                    this.requested_.addAll(contextRule.requested_);
                }
                onChanged();
            }
            if (!contextRule.provided_.isEmpty()) {
                if (this.provided_.isEmpty()) {
                    this.provided_ = contextRule.provided_;
                    this.bitField0_ &= -5;
                } else {
                    ensureProvidedIsMutable();
                    this.provided_.addAll(contextRule.provided_);
                }
                onChanged();
            }
            mergeUnknownFields(contextRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.ContextRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.ContextRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.ContextRule r3 = (com.google.api.ContextRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.ContextRule r4 = (com.google.api.ContextRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ContextRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.ContextRule$Builder");
        }

        public String getSelector() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.selector_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSelector(String str) {
            if (str != null) {
                this.selector_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSelector() {
            this.selector_ = ContextRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                ContextRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureRequestedIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.requested_ = new LazyStringArrayList(this.requested_);
                this.bitField0_ |= 2;
            }
        }

        public ProtocolStringList getRequestedList() {
            return this.requested_.getUnmodifiableView();
        }

        public int getRequestedCount() {
            return this.requested_.size();
        }

        public String getRequested(int i) {
            return (String) this.requested_.get(i);
        }

        public ByteString getRequestedBytes(int i) {
            return this.requested_.getByteString(i);
        }

        public Builder setRequested(int i, String str) {
            if (str != null) {
                ensureRequestedIsMutable();
                this.requested_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addRequested(String str) {
            if (str != null) {
                ensureRequestedIsMutable();
                this.requested_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllRequested(Iterable<String> iterable) {
            ensureRequestedIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.requested_);
            onChanged();
            return this;
        }

        public Builder clearRequested() {
            this.requested_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addRequestedBytes(ByteString byteString) {
            if (byteString != null) {
                ContextRule.checkByteStringIsUtf8(byteString);
                ensureRequestedIsMutable();
                this.requested_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureProvidedIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.provided_ = new LazyStringArrayList(this.provided_);
                this.bitField0_ |= 4;
            }
        }

        public ProtocolStringList getProvidedList() {
            return this.provided_.getUnmodifiableView();
        }

        public int getProvidedCount() {
            return this.provided_.size();
        }

        public String getProvided(int i) {
            return (String) this.provided_.get(i);
        }

        public ByteString getProvidedBytes(int i) {
            return this.provided_.getByteString(i);
        }

        public Builder setProvided(int i, String str) {
            if (str != null) {
                ensureProvidedIsMutable();
                this.provided_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addProvided(String str) {
            if (str != null) {
                ensureProvidedIsMutable();
                this.provided_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllProvided(Iterable<String> iterable) {
            ensureProvidedIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.provided_);
            onChanged();
            return this;
        }

        public Builder clearProvided() {
            this.provided_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addProvidedBytes(ByteString byteString) {
            if (byteString != null) {
                ContextRule.checkByteStringIsUtf8(byteString);
                ensureProvidedIsMutable();
                this.provided_.add(byteString);
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

    private ContextRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ContextRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.requested_ = LazyStringArrayList.EMPTY;
        this.provided_ = LazyStringArrayList.EMPTY;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ContextRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.selector_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                        if (!(z2 & true)) {
                            this.requested_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.requested_.add(readStringRequireUtf8);
                    } else if (readTag == 26) {
                        String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                        if (!(z2 & true)) {
                            this.provided_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.provided_.add(readStringRequireUtf82);
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.requested_ = this.requested_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.provided_ = this.provided_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.requested_ = this.requested_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.provided_ = this.provided_.getUnmodifiableView();
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ContextProto.internal_static_google_api_ContextRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ContextProto.internal_static_google_api_ContextRule_fieldAccessorTable.ensureFieldAccessorsInitialized(ContextRule.class, Builder.class);
    }

    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.selector_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public ProtocolStringList getRequestedList() {
        return this.requested_;
    }

    public int getRequestedCount() {
        return this.requested_.size();
    }

    public String getRequested(int i) {
        return (String) this.requested_.get(i);
    }

    public ByteString getRequestedBytes(int i) {
        return this.requested_.getByteString(i);
    }

    public ProtocolStringList getProvidedList() {
        return this.provided_;
    }

    public int getProvidedCount() {
        return this.provided_.size();
    }

    public String getProvided(int i) {
        return (String) this.provided_.get(i);
    }

    public ByteString getProvidedBytes(int i) {
        return this.provided_.getByteString(i);
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
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        for (int i = 0; i < this.requested_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.requested_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.provided_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.provided_.getRaw(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.requested_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.requested_.getRaw(i3));
        }
        int size = computeStringSize + i2 + (getRequestedList().size() * 1);
        int i4 = 0;
        for (int i5 = 0; i5 < this.provided_.size(); i5++) {
            i4 += computeStringSizeNoTag(this.provided_.getRaw(i5));
        }
        int size2 = size + i4 + (getProvidedList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size2;
        return size2;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextRule)) {
            return super.equals(obj);
        }
        ContextRule contextRule = (ContextRule) obj;
        if (!(((getSelector().equals(contextRule.getSelector())) && getRequestedList().equals(contextRule.getRequestedList())) && getProvidedList().equals(contextRule.getProvidedList())) || !this.unknownFields.equals(contextRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (getRequestedCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getRequestedList().hashCode();
        }
        if (getProvidedCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getProvidedList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ContextRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteBuffer);
    }

    public static ContextRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ContextRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteString);
    }

    public static ContextRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ContextRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(bArr);
    }

    public static ContextRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ContextRule parseFrom(InputStream inputStream) throws IOException {
        return (ContextRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ContextRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ContextRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ContextRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ContextRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ContextRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ContextRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ContextRule contextRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(contextRule);
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

    public static ContextRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ContextRule> parser() {
        return PARSER;
    }

    public Parser<ContextRule> getParserForType() {
        return PARSER;
    }

    public ContextRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
