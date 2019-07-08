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

public final class SystemParameterRule extends GeneratedMessageV3 implements SystemParameterRuleOrBuilder {
    private static final SystemParameterRule DEFAULT_INSTANCE = new SystemParameterRule();
    public static final int PARAMETERS_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<SystemParameterRule> PARSER = new AbstractParser<SystemParameterRule>() {
        public SystemParameterRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SystemParameterRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<SystemParameter> parameters_;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SystemParameterRuleOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> parametersBuilder_;
        private List<SystemParameter> parameters_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return SystemParameterProto.internal_static_google_api_SystemParameterRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return SystemParameterProto.internal_static_google_api_SystemParameterRule_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameterRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.parameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.parameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SystemParameterRule.alwaysUseFieldBuilders) {
                getParametersFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.parameters_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return SystemParameterProto.internal_static_google_api_SystemParameterRule_descriptor;
        }

        public SystemParameterRule getDefaultInstanceForType() {
            return SystemParameterRule.getDefaultInstance();
        }

        public SystemParameterRule build() {
            SystemParameterRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public SystemParameterRule buildPartial() {
            SystemParameterRule systemParameterRule = new SystemParameterRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            systemParameterRule.selector_ = this.selector_;
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.parameters_ = Collections.unmodifiableList(this.parameters_);
                    this.bitField0_ &= -3;
                }
                systemParameterRule.parameters_ = this.parameters_;
            } else {
                systemParameterRule.parameters_ = repeatedFieldBuilderV3.build();
            }
            systemParameterRule.bitField0_ = 0;
            onBuilt();
            return systemParameterRule;
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
            if (message instanceof SystemParameterRule) {
                return mergeFrom((SystemParameterRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SystemParameterRule systemParameterRule) {
            if (systemParameterRule == SystemParameterRule.getDefaultInstance()) {
                return this;
            }
            if (!systemParameterRule.getSelector().isEmpty()) {
                this.selector_ = systemParameterRule.selector_;
                onChanged();
            }
            if (this.parametersBuilder_ == null) {
                if (!systemParameterRule.parameters_.isEmpty()) {
                    if (this.parameters_.isEmpty()) {
                        this.parameters_ = systemParameterRule.parameters_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureParametersIsMutable();
                        this.parameters_.addAll(systemParameterRule.parameters_);
                    }
                    onChanged();
                }
            } else if (!systemParameterRule.parameters_.isEmpty()) {
                if (this.parametersBuilder_.isEmpty()) {
                    this.parametersBuilder_.dispose();
                    RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = null;
                    this.parametersBuilder_ = null;
                    this.parameters_ = systemParameterRule.parameters_;
                    this.bitField0_ &= -3;
                    if (SystemParameterRule.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getParametersFieldBuilder();
                    }
                    this.parametersBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.parametersBuilder_.addAllMessages(systemParameterRule.parameters_);
                }
            }
            mergeUnknownFields(systemParameterRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.SystemParameterRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.SystemParameterRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.SystemParameterRule r3 = (com.google.api.SystemParameterRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.SystemParameterRule r4 = (com.google.api.SystemParameterRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.SystemParameterRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.SystemParameterRule$Builder");
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
            this.selector_ = SystemParameterRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                SystemParameterRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureParametersIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.parameters_ = new ArrayList(this.parameters_);
                this.bitField0_ |= 2;
            }
        }

        public List<SystemParameter> getParametersList() {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.parameters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getParametersCount() {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public SystemParameter getParameters(int i) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (SystemParameter) this.parameters_.get(i);
            }
            return (SystemParameter) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setParameters(int i, SystemParameter systemParameter) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, systemParameter);
            } else if (systemParameter != null) {
                ensureParametersIsMutable();
                this.parameters_.set(i, systemParameter);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setParameters(int i, com.google.api.SystemParameter.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addParameters(SystemParameter systemParameter) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(systemParameter);
            } else if (systemParameter != null) {
                ensureParametersIsMutable();
                this.parameters_.add(systemParameter);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addParameters(int i, SystemParameter systemParameter) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, systemParameter);
            } else if (systemParameter != null) {
                ensureParametersIsMutable();
                this.parameters_.add(i, systemParameter);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addParameters(com.google.api.SystemParameter.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addParameters(int i, com.google.api.SystemParameter.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllParameters(Iterable<? extends SystemParameter> iterable) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.parameters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearParameters() {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.parameters_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeParameters(int i) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.SystemParameter.Builder getParametersBuilder(int i) {
            return (com.google.api.SystemParameter.Builder) getParametersFieldBuilder().getBuilder(i);
        }

        public SystemParameterOrBuilder getParametersOrBuilder(int i) {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (SystemParameterOrBuilder) this.parameters_.get(i);
            }
            return (SystemParameterOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends SystemParameterOrBuilder> getParametersOrBuilderList() {
            RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.parameters_);
        }

        public com.google.api.SystemParameter.Builder addParametersBuilder() {
            return (com.google.api.SystemParameter.Builder) getParametersFieldBuilder().addBuilder(SystemParameter.getDefaultInstance());
        }

        public com.google.api.SystemParameter.Builder addParametersBuilder(int i) {
            return (com.google.api.SystemParameter.Builder) getParametersFieldBuilder().addBuilder(i, SystemParameter.getDefaultInstance());
        }

        public List<com.google.api.SystemParameter.Builder> getParametersBuilderList() {
            return getParametersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SystemParameter, com.google.api.SystemParameter.Builder, SystemParameterOrBuilder> getParametersFieldBuilder() {
            if (this.parametersBuilder_ == null) {
                this.parametersBuilder_ = new RepeatedFieldBuilderV3<>(this.parameters_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.parameters_ = null;
            }
            return this.parametersBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private SystemParameterRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private SystemParameterRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.parameters_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SystemParameterRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!(z2 & true)) {
                            this.parameters_ = new ArrayList();
                            z2 |= true;
                        }
                        this.parameters_.add(codedInputStream.readMessage(SystemParameter.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.parameters_ = Collections.unmodifiableList(this.parameters_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.parameters_ = Collections.unmodifiableList(this.parameters_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return SystemParameterProto.internal_static_google_api_SystemParameterRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return SystemParameterProto.internal_static_google_api_SystemParameterRule_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameterRule.class, Builder.class);
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

    public List<SystemParameter> getParametersList() {
        return this.parameters_;
    }

    public List<? extends SystemParameterOrBuilder> getParametersOrBuilderList() {
        return this.parameters_;
    }

    public int getParametersCount() {
        return this.parameters_.size();
    }

    public SystemParameter getParameters(int i) {
        return (SystemParameter) this.parameters_.get(i);
    }

    public SystemParameterOrBuilder getParametersOrBuilder(int i) {
        return (SystemParameterOrBuilder) this.parameters_.get(i);
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
        for (int i = 0; i < this.parameters_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.parameters_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        for (int i2 = 0; i2 < this.parameters_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.parameters_.get(i2));
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SystemParameterRule)) {
            return super.equals(obj);
        }
        SystemParameterRule systemParameterRule = (SystemParameterRule) obj;
        if (!((getSelector().equals(systemParameterRule.getSelector())) && getParametersList().equals(systemParameterRule.getParametersList())) || !this.unknownFields.equals(systemParameterRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (getParametersCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getParametersList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SystemParameterRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SystemParameterRule) PARSER.parseFrom(byteBuffer);
    }

    public static SystemParameterRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameterRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SystemParameterRule) PARSER.parseFrom(byteString);
    }

    public static SystemParameterRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameterRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SystemParameterRule) PARSER.parseFrom(bArr);
    }

    public static SystemParameterRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameterRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(InputStream inputStream) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SystemParameterRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SystemParameterRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SystemParameterRule systemParameterRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(systemParameterRule);
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

    public static SystemParameterRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemParameterRule> parser() {
        return PARSER;
    }

    public Parser<SystemParameterRule> getParserForType() {
        return PARSER;
    }

    public SystemParameterRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
