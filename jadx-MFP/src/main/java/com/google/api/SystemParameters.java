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

public final class SystemParameters extends GeneratedMessageV3 implements SystemParametersOrBuilder {
    private static final SystemParameters DEFAULT_INSTANCE = new SystemParameters();
    /* access modifiers changed from: private */
    public static final Parser<SystemParameters> PARSER = new AbstractParser<SystemParameters>() {
        public SystemParameters parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SystemParameters(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RULES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<SystemParameterRule> rules_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SystemParametersOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> rulesBuilder_;
        private List<SystemParameterRule> rules_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return SystemParameterProto.internal_static_google_api_SystemParameters_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return SystemParameterProto.internal_static_google_api_SystemParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameters.class, Builder.class);
        }

        private Builder() {
            this.rules_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.rules_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SystemParameters.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return SystemParameterProto.internal_static_google_api_SystemParameters_descriptor;
        }

        public SystemParameters getDefaultInstanceForType() {
            return SystemParameters.getDefaultInstance();
        }

        public SystemParameters build() {
            SystemParameters buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public SystemParameters buildPartial() {
            SystemParameters systemParameters = new SystemParameters((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -2;
                }
                systemParameters.rules_ = this.rules_;
            } else {
                systemParameters.rules_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return systemParameters;
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
            if (message instanceof SystemParameters) {
                return mergeFrom((SystemParameters) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SystemParameters systemParameters) {
            if (systemParameters == SystemParameters.getDefaultInstance()) {
                return this;
            }
            if (this.rulesBuilder_ == null) {
                if (!systemParameters.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = systemParameters.rules_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(systemParameters.rules_);
                    }
                    onChanged();
                }
            } else if (!systemParameters.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = null;
                    this.rulesBuilder_ = null;
                    this.rules_ = systemParameters.rules_;
                    this.bitField0_ &= -2;
                    if (SystemParameters.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getRulesFieldBuilder();
                    }
                    this.rulesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.rulesBuilder_.addAllMessages(systemParameters.rules_);
                }
            }
            mergeUnknownFields(systemParameters.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.SystemParameters.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.SystemParameters.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.SystemParameters r3 = (com.google.api.SystemParameters) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.SystemParameters r4 = (com.google.api.SystemParameters) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.SystemParameters.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.SystemParameters$Builder");
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        public List<SystemParameterRule> getRulesList() {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRulesCount() {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public SystemParameterRule getRules(int i) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (SystemParameterRule) this.rules_.get(i);
            }
            return (SystemParameterRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, SystemParameterRule systemParameterRule) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, systemParameterRule);
            } else if (systemParameterRule != null) {
                ensureRulesIsMutable();
                this.rules_.set(i, systemParameterRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRules(int i, com.google.api.SystemParameterRule.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(SystemParameterRule systemParameterRule) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(systemParameterRule);
            } else if (systemParameterRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(systemParameterRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(int i, SystemParameterRule systemParameterRule) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, systemParameterRule);
            } else if (systemParameterRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(i, systemParameterRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(com.google.api.SystemParameterRule.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, com.google.api.SystemParameterRule.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends SystemParameterRule> iterable) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.SystemParameterRule.Builder getRulesBuilder(int i) {
            return (com.google.api.SystemParameterRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        public SystemParameterRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (SystemParameterRuleOrBuilder) this.rules_.get(i);
            }
            return (SystemParameterRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends SystemParameterRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public com.google.api.SystemParameterRule.Builder addRulesBuilder() {
            return (com.google.api.SystemParameterRule.Builder) getRulesFieldBuilder().addBuilder(SystemParameterRule.getDefaultInstance());
        }

        public com.google.api.SystemParameterRule.Builder addRulesBuilder(int i) {
            return (com.google.api.SystemParameterRule.Builder) getRulesFieldBuilder().addBuilder(i, SystemParameterRule.getDefaultInstance());
        }

        public List<com.google.api.SystemParameterRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SystemParameterRule, com.google.api.SystemParameterRule.Builder, SystemParameterRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                List<SystemParameterRule> list = this.rules_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private SystemParameters(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private SystemParameters() {
        this.memoizedIsInitialized = -1;
        this.rules_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SystemParameters(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.rules_ = new ArrayList();
                            z2 |= true;
                        }
                        this.rules_.add(codedInputStream.readMessage(SystemParameterRule.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.rules_ = Collections.unmodifiableList(this.rules_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return SystemParameterProto.internal_static_google_api_SystemParameters_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return SystemParameterProto.internal_static_google_api_SystemParameters_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameters.class, Builder.class);
    }

    public List<SystemParameterRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends SystemParameterRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public SystemParameterRule getRules(int i) {
        return (SystemParameterRule) this.rules_.get(i);
    }

    public SystemParameterRuleOrBuilder getRulesOrBuilder(int i) {
        return (SystemParameterRuleOrBuilder) this.rules_.get(i);
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
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.rules_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.rules_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.rules_.get(i3));
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
        if (!(obj instanceof SystemParameters)) {
            return super.equals(obj);
        }
        SystemParameters systemParameters = (SystemParameters) obj;
        if (!(getRulesList().equals(systemParameters.getRulesList())) || !this.unknownFields.equals(systemParameters.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getRulesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SystemParameters parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (SystemParameters) PARSER.parseFrom(byteBuffer);
    }

    public static SystemParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameters) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SystemParameters) PARSER.parseFrom(byteString);
    }

    public static SystemParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameters) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SystemParameters) PARSER.parseFrom(bArr);
    }

    public static SystemParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SystemParameters) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(InputStream inputStream) throws IOException {
        return (SystemParameters) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SystemParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameters) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameters parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SystemParameters) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SystemParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameters) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SystemParameters) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SystemParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameters) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SystemParameters systemParameters) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(systemParameters);
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

    public static SystemParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemParameters> parser() {
        return PARSER;
    }

    public Parser<SystemParameters> getParserForType() {
        return PARSER;
    }

    public SystemParameters getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
