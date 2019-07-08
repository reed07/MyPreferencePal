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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Usage extends GeneratedMessageV3 implements UsageOrBuilder {
    private static final Usage DEFAULT_INSTANCE = new Usage();
    /* access modifiers changed from: private */
    public static final Parser<Usage> PARSER = new AbstractParser<Usage>() {
        public Usage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Usage(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_NOTIFICATION_CHANNEL_FIELD_NUMBER = 7;
    public static final int REQUIREMENTS_FIELD_NUMBER = 1;
    public static final int RULES_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object producerNotificationChannel_;
    /* access modifiers changed from: private */
    public LazyStringList requirements_;
    /* access modifiers changed from: private */
    public List<UsageRule> rules_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements UsageOrBuilder {
        private int bitField0_;
        private Object producerNotificationChannel_;
        private LazyStringList requirements_;
        private RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> rulesBuilder_;
        private List<UsageRule> rules_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
        }

        private Builder() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.rules_ = Collections.emptyList();
            this.producerNotificationChannel_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.rules_ = Collections.emptyList();
            this.producerNotificationChannel_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Usage.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.producerNotificationChannel_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        public Usage getDefaultInstanceForType() {
            return Usage.getDefaultInstance();
        }

        public Usage build() {
            Usage buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Usage buildPartial() {
            Usage usage = new Usage((com.google.protobuf.GeneratedMessageV3.Builder) this);
            if ((this.bitField0_ & 1) == 1) {
                this.requirements_ = this.requirements_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            usage.requirements_ = this.requirements_;
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -3;
                }
                usage.rules_ = this.rules_;
            } else {
                usage.rules_ = repeatedFieldBuilderV3.build();
            }
            usage.producerNotificationChannel_ = this.producerNotificationChannel_;
            usage.bitField0_ = 0;
            onBuilt();
            return usage;
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
            if (message instanceof Usage) {
                return mergeFrom((Usage) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Usage usage) {
            if (usage == Usage.getDefaultInstance()) {
                return this;
            }
            if (!usage.requirements_.isEmpty()) {
                if (this.requirements_.isEmpty()) {
                    this.requirements_ = usage.requirements_;
                    this.bitField0_ &= -2;
                } else {
                    ensureRequirementsIsMutable();
                    this.requirements_.addAll(usage.requirements_);
                }
                onChanged();
            }
            if (this.rulesBuilder_ == null) {
                if (!usage.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = usage.rules_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(usage.rules_);
                    }
                    onChanged();
                }
            } else if (!usage.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = null;
                    this.rulesBuilder_ = null;
                    this.rules_ = usage.rules_;
                    this.bitField0_ &= -3;
                    if (Usage.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getRulesFieldBuilder();
                    }
                    this.rulesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.rulesBuilder_.addAllMessages(usage.rules_);
                }
            }
            if (!usage.getProducerNotificationChannel().isEmpty()) {
                this.producerNotificationChannel_ = usage.producerNotificationChannel_;
                onChanged();
            }
            mergeUnknownFields(usage.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Usage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Usage.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Usage r3 = (com.google.api.Usage) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Usage r4 = (com.google.api.Usage) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Usage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Usage$Builder");
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.requirements_ = new LazyStringArrayList(this.requirements_);
                this.bitField0_ |= 1;
            }
        }

        public ProtocolStringList getRequirementsList() {
            return this.requirements_.getUnmodifiableView();
        }

        public int getRequirementsCount() {
            return this.requirements_.size();
        }

        public String getRequirements(int i) {
            return (String) this.requirements_.get(i);
        }

        public ByteString getRequirementsBytes(int i) {
            return this.requirements_.getByteString(i);
        }

        public Builder setRequirements(int i, String str) {
            if (str != null) {
                ensureRequirementsIsMutable();
                this.requirements_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addRequirements(String str) {
            if (str != null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllRequirements(Iterable<String> iterable) {
            ensureRequirementsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.requirements_);
            onChanged();
            return this;
        }

        public Builder clearRequirements() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addRequirementsBytes(ByteString byteString) {
            if (byteString != null) {
                Usage.checkByteStringIsUtf8(byteString);
                ensureRequirementsIsMutable();
                this.requirements_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 2;
            }
        }

        public List<UsageRule> getRulesList() {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRulesCount() {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public UsageRule getRules(int i) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (UsageRule) this.rules_.get(i);
            }
            return (UsageRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, usageRule);
            } else if (usageRule != null) {
                ensureRulesIsMutable();
                this.rules_.set(i, usageRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRules(int i, com.google.api.UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(usageRule);
            } else if (usageRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(usageRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(int i, UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, usageRule);
            } else if (usageRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(i, usageRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(com.google.api.UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, com.google.api.UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends UsageRule> iterable) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
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
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.UsageRule.Builder getRulesBuilder(int i) {
            return (com.google.api.UsageRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        public UsageRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (UsageRuleOrBuilder) this.rules_.get(i);
            }
            return (UsageRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public com.google.api.UsageRule.Builder addRulesBuilder() {
            return (com.google.api.UsageRule.Builder) getRulesFieldBuilder().addBuilder(UsageRule.getDefaultInstance());
        }

        public com.google.api.UsageRule.Builder addRulesBuilder(int i) {
            return (com.google.api.UsageRule.Builder) getRulesFieldBuilder().addBuilder(i, UsageRule.getDefaultInstance());
        }

        public List<com.google.api.UsageRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<UsageRule, com.google.api.UsageRule.Builder, UsageRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        public String getProducerNotificationChannel() {
            Object obj = this.producerNotificationChannel_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.producerNotificationChannel_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getProducerNotificationChannelBytes() {
            Object obj = this.producerNotificationChannel_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.producerNotificationChannel_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setProducerNotificationChannel(String str) {
            if (str != null) {
                this.producerNotificationChannel_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearProducerNotificationChannel() {
            this.producerNotificationChannel_ = Usage.getDefaultInstance().getProducerNotificationChannel();
            onChanged();
            return this;
        }

        public Builder setProducerNotificationChannelBytes(ByteString byteString) {
            if (byteString != null) {
                Usage.checkByteStringIsUtf8(byteString);
                this.producerNotificationChannel_ = byteString;
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

    private Usage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Usage() {
        this.memoizedIsInitialized = -1;
        this.requirements_ = LazyStringArrayList.EMPTY;
        this.rules_ = Collections.emptyList();
        this.producerNotificationChannel_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Usage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.requirements_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.requirements_.add(readStringRequireUtf8);
                    } else if (readTag == 50) {
                        if (!(z2 & true)) {
                            this.rules_ = new ArrayList();
                            z2 |= true;
                        }
                        this.rules_.add(codedInputStream.readMessage(UsageRule.parser(), extensionRegistryLite));
                    } else if (readTag == 58) {
                        this.producerNotificationChannel_ = codedInputStream.readStringRequireUtf8();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.requirements_ = this.requirements_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.rules_ = Collections.unmodifiableList(this.rules_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.requirements_ = this.requirements_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return UsageProto.internal_static_google_api_Usage_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
    }

    public ProtocolStringList getRequirementsList() {
        return this.requirements_;
    }

    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    public String getRequirements(int i) {
        return (String) this.requirements_.get(i);
    }

    public ByteString getRequirementsBytes(int i) {
        return this.requirements_.getByteString(i);
    }

    public List<UsageRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public UsageRule getRules(int i) {
        return (UsageRule) this.rules_.get(i);
    }

    public UsageRuleOrBuilder getRulesOrBuilder(int i) {
        return (UsageRuleOrBuilder) this.rules_.get(i);
    }

    public String getProducerNotificationChannel() {
        Object obj = this.producerNotificationChannel_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.producerNotificationChannel_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getProducerNotificationChannelBytes() {
        Object obj = this.producerNotificationChannel_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.producerNotificationChannel_ = copyFromUtf8;
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
        for (int i = 0; i < this.requirements_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requirements_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.rules_.size(); i2++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.rules_.get(i2));
        }
        if (!getProducerNotificationChannelBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.producerNotificationChannel_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.requirements_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.requirements_.getRaw(i3));
        }
        int size = i2 + 0 + (getRequirementsList().size() * 1);
        for (int i4 = 0; i4 < this.rules_.size(); i4++) {
            size += CodedOutputStream.computeMessageSize(6, (MessageLite) this.rules_.get(i4));
        }
        if (!getProducerNotificationChannelBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(7, this.producerNotificationChannel_);
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
        if (!(obj instanceof Usage)) {
            return super.equals(obj);
        }
        Usage usage = (Usage) obj;
        if (!(((getRequirementsList().equals(usage.getRequirementsList())) && getRulesList().equals(usage.getRulesList())) && getProducerNotificationChannel().equals(usage.getProducerNotificationChannel())) || !this.unknownFields.equals(usage.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getRequirementsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getRequirementsList().hashCode();
        }
        if (getRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getRulesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 7) * 53) + getProducerNotificationChannel().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Usage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Usage) PARSER.parseFrom(byteBuffer);
    }

    public static Usage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Usage) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Usage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Usage) PARSER.parseFrom(byteString);
    }

    public static Usage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Usage) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Usage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Usage) PARSER.parseFrom(bArr);
    }

    public static Usage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Usage) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Usage parseFrom(InputStream inputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Usage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Usage parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Usage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Usage parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Usage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Usage usage) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(usage);
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

    public static Usage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Usage> parser() {
        return PARSER;
    }

    public Parser<Usage> getParserForType() {
        return PARSER;
    }

    public Usage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
