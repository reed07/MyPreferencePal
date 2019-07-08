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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class UsageRule extends GeneratedMessageV3 implements UsageRuleOrBuilder {
    public static final int ALLOW_UNREGISTERED_CALLS_FIELD_NUMBER = 2;
    private static final UsageRule DEFAULT_INSTANCE = new UsageRule();
    /* access modifiers changed from: private */
    public static final Parser<UsageRule> PARSER = new AbstractParser<UsageRule>() {
        public UsageRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UsageRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public static final int SKIP_SERVICE_CONTROL_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public boolean allowUnregisteredCalls_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object selector_;
    /* access modifiers changed from: private */
    public boolean skipServiceControl_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements UsageRuleOrBuilder {
        private boolean allowUnregisteredCalls_;
        private Object selector_;
        private boolean skipServiceControl_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return UsageProto.internal_static_google_api_UsageRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return UsageProto.internal_static_google_api_UsageRule_fieldAccessorTable.ensureFieldAccessorsInitialized(UsageRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            UsageRule.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.allowUnregisteredCalls_ = false;
            this.skipServiceControl_ = false;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return UsageProto.internal_static_google_api_UsageRule_descriptor;
        }

        public UsageRule getDefaultInstanceForType() {
            return UsageRule.getDefaultInstance();
        }

        public UsageRule build() {
            UsageRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public UsageRule buildPartial() {
            UsageRule usageRule = new UsageRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            usageRule.selector_ = this.selector_;
            usageRule.allowUnregisteredCalls_ = this.allowUnregisteredCalls_;
            usageRule.skipServiceControl_ = this.skipServiceControl_;
            onBuilt();
            return usageRule;
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
            if (message instanceof UsageRule) {
                return mergeFrom((UsageRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UsageRule usageRule) {
            if (usageRule == UsageRule.getDefaultInstance()) {
                return this;
            }
            if (!usageRule.getSelector().isEmpty()) {
                this.selector_ = usageRule.selector_;
                onChanged();
            }
            if (usageRule.getAllowUnregisteredCalls()) {
                setAllowUnregisteredCalls(usageRule.getAllowUnregisteredCalls());
            }
            if (usageRule.getSkipServiceControl()) {
                setSkipServiceControl(usageRule.getSkipServiceControl());
            }
            mergeUnknownFields(usageRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.UsageRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.UsageRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.UsageRule r3 = (com.google.api.UsageRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.UsageRule r4 = (com.google.api.UsageRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.UsageRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.UsageRule$Builder");
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
            this.selector_ = UsageRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                UsageRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean getAllowUnregisteredCalls() {
            return this.allowUnregisteredCalls_;
        }

        public Builder setAllowUnregisteredCalls(boolean z) {
            this.allowUnregisteredCalls_ = z;
            onChanged();
            return this;
        }

        public Builder clearAllowUnregisteredCalls() {
            this.allowUnregisteredCalls_ = false;
            onChanged();
            return this;
        }

        public boolean getSkipServiceControl() {
            return this.skipServiceControl_;
        }

        public Builder setSkipServiceControl(boolean z) {
            this.skipServiceControl_ = z;
            onChanged();
            return this;
        }

        public Builder clearSkipServiceControl() {
            this.skipServiceControl_ = false;
            onChanged();
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private UsageRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private UsageRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.allowUnregisteredCalls_ = false;
        this.skipServiceControl_ = false;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UsageRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.selector_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 16) {
                        this.allowUnregisteredCalls_ = codedInputStream.readBool();
                    } else if (readTag == 24) {
                        this.skipServiceControl_ = codedInputStream.readBool();
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
        return UsageProto.internal_static_google_api_UsageRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return UsageProto.internal_static_google_api_UsageRule_fieldAccessorTable.ensureFieldAccessorsInitialized(UsageRule.class, Builder.class);
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

    public boolean getAllowUnregisteredCalls() {
        return this.allowUnregisteredCalls_;
    }

    public boolean getSkipServiceControl() {
        return this.skipServiceControl_;
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
        boolean z = this.allowUnregisteredCalls_;
        if (z) {
            codedOutputStream.writeBool(2, z);
        }
        boolean z2 = this.skipServiceControl_;
        if (z2) {
            codedOutputStream.writeBool(3, z2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getSelectorBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.selector_);
        }
        boolean z = this.allowUnregisteredCalls_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(2, z);
        }
        boolean z2 = this.skipServiceControl_;
        if (z2) {
            i2 += CodedOutputStream.computeBoolSize(3, z2);
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
        if (!(obj instanceof UsageRule)) {
            return super.equals(obj);
        }
        UsageRule usageRule = (UsageRule) obj;
        if (!(((getSelector().equals(usageRule.getSelector())) && getAllowUnregisteredCalls() == usageRule.getAllowUnregisteredCalls()) && getSkipServiceControl() == usageRule.getSkipServiceControl()) || !this.unknownFields.equals(usageRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 2) * 53) + Internal.hashBoolean(getAllowUnregisteredCalls())) * 37) + 3) * 53) + Internal.hashBoolean(getSkipServiceControl())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static UsageRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (UsageRule) PARSER.parseFrom(byteBuffer);
    }

    public static UsageRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UsageRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UsageRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UsageRule) PARSER.parseFrom(byteString);
    }

    public static UsageRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UsageRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UsageRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UsageRule) PARSER.parseFrom(bArr);
    }

    public static UsageRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UsageRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UsageRule parseFrom(InputStream inputStream) throws IOException {
        return (UsageRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UsageRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UsageRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UsageRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UsageRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UsageRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UsageRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UsageRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UsageRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UsageRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UsageRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsageRule usageRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(usageRule);
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

    public static UsageRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsageRule> parser() {
        return PARSER;
    }

    public Parser<UsageRule> getParserForType() {
        return PARSER;
    }

    public UsageRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
