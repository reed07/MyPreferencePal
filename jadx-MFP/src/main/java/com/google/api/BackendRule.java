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

public final class BackendRule extends GeneratedMessageV3 implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    private static final BackendRule DEFAULT_INSTANCE = new BackendRule();
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Parser<BackendRule> PARSER = new AbstractParser<BackendRule>() {
        public BackendRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BackendRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object address_;
    /* access modifiers changed from: private */
    public double deadline_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public double minDeadline_;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements BackendRuleOrBuilder {
        private Object address_;
        private double deadline_;
        private double minDeadline_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.address_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.address_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            BackendRule.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.address_ = "";
            this.deadline_ = 0.0d;
            this.minDeadline_ = 0.0d;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        public BackendRule getDefaultInstanceForType() {
            return BackendRule.getDefaultInstance();
        }

        public BackendRule build() {
            BackendRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public BackendRule buildPartial() {
            BackendRule backendRule = new BackendRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            backendRule.selector_ = this.selector_;
            backendRule.address_ = this.address_;
            backendRule.deadline_ = this.deadline_;
            backendRule.minDeadline_ = this.minDeadline_;
            onBuilt();
            return backendRule;
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
            if (message instanceof BackendRule) {
                return mergeFrom((BackendRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BackendRule backendRule) {
            if (backendRule == BackendRule.getDefaultInstance()) {
                return this;
            }
            if (!backendRule.getSelector().isEmpty()) {
                this.selector_ = backendRule.selector_;
                onChanged();
            }
            if (!backendRule.getAddress().isEmpty()) {
                this.address_ = backendRule.address_;
                onChanged();
            }
            if (backendRule.getDeadline() != 0.0d) {
                setDeadline(backendRule.getDeadline());
            }
            if (backendRule.getMinDeadline() != 0.0d) {
                setMinDeadline(backendRule.getMinDeadline());
            }
            mergeUnknownFields(backendRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.BackendRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.BackendRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.BackendRule r3 = (com.google.api.BackendRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.BackendRule r4 = (com.google.api.BackendRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.BackendRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.BackendRule$Builder");
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
            this.selector_ = BackendRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                BackendRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getAddress() {
            Object obj = this.address_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.address_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.address_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setAddress(String str) {
            if (str != null) {
                this.address_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearAddress() {
            this.address_ = BackendRule.getDefaultInstance().getAddress();
            onChanged();
            return this;
        }

        public Builder setAddressBytes(ByteString byteString) {
            if (byteString != null) {
                BackendRule.checkByteStringIsUtf8(byteString);
                this.address_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public double getDeadline() {
            return this.deadline_;
        }

        public Builder setDeadline(double d) {
            this.deadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearDeadline() {
            this.deadline_ = 0.0d;
            onChanged();
            return this;
        }

        public double getMinDeadline() {
            return this.minDeadline_;
        }

        public Builder setMinDeadline(double d) {
            this.minDeadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearMinDeadline() {
            this.minDeadline_ = 0.0d;
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

    private BackendRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private BackendRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.address_ = "";
        this.deadline_ = 0.0d;
        this.minDeadline_ = 0.0d;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BackendRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 18) {
                        this.address_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 25) {
                        this.deadline_ = codedInputStream.readDouble();
                    } else if (readTag == 33) {
                        this.minDeadline_ = codedInputStream.readDouble();
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
        return BackendProto.internal_static_google_api_BackendRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
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

    public String getAddress() {
        Object obj = this.address_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.address_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getAddressBytes() {
        Object obj = this.address_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.address_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public double getDeadline() {
        return this.deadline_;
    }

    public double getMinDeadline() {
        return this.minDeadline_;
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
        if (!getAddressBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.address_);
        }
        double d = this.deadline_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(3, d);
        }
        double d2 = this.minDeadline_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(4, d2);
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
        if (!getAddressBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.address_);
        }
        double d = this.deadline_;
        if (d != 0.0d) {
            i2 += CodedOutputStream.computeDoubleSize(3, d);
        }
        double d2 = this.minDeadline_;
        if (d2 != 0.0d) {
            i2 += CodedOutputStream.computeDoubleSize(4, d2);
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
        if (!(obj instanceof BackendRule)) {
            return super.equals(obj);
        }
        BackendRule backendRule = (BackendRule) obj;
        if (!((((getSelector().equals(backendRule.getSelector())) && getAddress().equals(backendRule.getAddress())) && (Double.doubleToLongBits(getDeadline()) > Double.doubleToLongBits(backendRule.getDeadline()) ? 1 : (Double.doubleToLongBits(getDeadline()) == Double.doubleToLongBits(backendRule.getDeadline()) ? 0 : -1)) == 0) && Double.doubleToLongBits(getMinDeadline()) == Double.doubleToLongBits(backendRule.getMinDeadline())) || !this.unknownFields.equals(backendRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 2) * 53) + getAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getDeadline()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getMinDeadline()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteBuffer);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteString);
    }

    public static BackendRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BackendRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(bArr);
    }

    public static BackendRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BackendRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BackendRule parseFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BackendRule backendRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(backendRule);
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

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackendRule> parser() {
        return PARSER;
    }

    public Parser<BackendRule> getParserForType() {
        return PARSER;
    }

    public BackendRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
