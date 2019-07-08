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

public final class Context extends GeneratedMessageV3 implements ContextOrBuilder {
    private static final Context DEFAULT_INSTANCE = new Context();
    /* access modifiers changed from: private */
    public static final Parser<Context> PARSER = new AbstractParser<Context>() {
        public Context parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Context(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RULES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<ContextRule> rules_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ContextOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> rulesBuilder_;
        private List<ContextRule> rules_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ContextProto.internal_static_google_api_Context_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ContextProto.internal_static_google_api_Context_fieldAccessorTable.ensureFieldAccessorsInitialized(Context.class, Builder.class);
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
            if (Context.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ContextProto.internal_static_google_api_Context_descriptor;
        }

        public Context getDefaultInstanceForType() {
            return Context.getDefaultInstance();
        }

        public Context build() {
            Context buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Context buildPartial() {
            Context context = new Context((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -2;
                }
                context.rules_ = this.rules_;
            } else {
                context.rules_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return context;
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
            if (message instanceof Context) {
                return mergeFrom((Context) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Context context) {
            if (context == Context.getDefaultInstance()) {
                return this;
            }
            if (this.rulesBuilder_ == null) {
                if (!context.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = context.rules_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(context.rules_);
                    }
                    onChanged();
                }
            } else if (!context.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = null;
                    this.rulesBuilder_ = null;
                    this.rules_ = context.rules_;
                    this.bitField0_ &= -2;
                    if (Context.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getRulesFieldBuilder();
                    }
                    this.rulesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.rulesBuilder_.addAllMessages(context.rules_);
                }
            }
            mergeUnknownFields(context.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Context.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Context.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Context r3 = (com.google.api.Context) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Context r4 = (com.google.api.Context) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Context.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Context$Builder");
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        public List<ContextRule> getRulesList() {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRulesCount() {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public ContextRule getRules(int i) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (ContextRule) this.rules_.get(i);
            }
            return (ContextRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, ContextRule contextRule) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, contextRule);
            } else if (contextRule != null) {
                ensureRulesIsMutable();
                this.rules_.set(i, contextRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRules(int i, com.google.api.ContextRule.Builder builder) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(ContextRule contextRule) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(contextRule);
            } else if (contextRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(contextRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(int i, ContextRule contextRule) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, contextRule);
            } else if (contextRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(i, contextRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(com.google.api.ContextRule.Builder builder) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, com.google.api.ContextRule.Builder builder) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends ContextRule> iterable) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
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
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
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
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.ContextRule.Builder getRulesBuilder(int i) {
            return (com.google.api.ContextRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        public ContextRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (ContextRuleOrBuilder) this.rules_.get(i);
            }
            return (ContextRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends ContextRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public com.google.api.ContextRule.Builder addRulesBuilder() {
            return (com.google.api.ContextRule.Builder) getRulesFieldBuilder().addBuilder(ContextRule.getDefaultInstance());
        }

        public com.google.api.ContextRule.Builder addRulesBuilder(int i) {
            return (com.google.api.ContextRule.Builder) getRulesFieldBuilder().addBuilder(i, ContextRule.getDefaultInstance());
        }

        public List<com.google.api.ContextRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ContextRule, com.google.api.ContextRule.Builder, ContextRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                List<ContextRule> list = this.rules_;
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

    private Context(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Context() {
        this.memoizedIsInitialized = -1;
        this.rules_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Context(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.rules_.add(codedInputStream.readMessage(ContextRule.parser(), extensionRegistryLite));
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
        return ContextProto.internal_static_google_api_Context_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ContextProto.internal_static_google_api_Context_fieldAccessorTable.ensureFieldAccessorsInitialized(Context.class, Builder.class);
    }

    public List<ContextRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends ContextRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public ContextRule getRules(int i) {
        return (ContextRule) this.rules_.get(i);
    }

    public ContextRuleOrBuilder getRulesOrBuilder(int i) {
        return (ContextRuleOrBuilder) this.rules_.get(i);
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
        if (!(obj instanceof Context)) {
            return super.equals(obj);
        }
        Context context = (Context) obj;
        if (!(getRulesList().equals(context.getRulesList())) || !this.unknownFields.equals(context.unknownFields)) {
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

    public static Context parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Context) PARSER.parseFrom(byteBuffer);
    }

    public static Context parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Context) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Context parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Context) PARSER.parseFrom(byteString);
    }

    public static Context parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Context) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Context parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Context) PARSER.parseFrom(bArr);
    }

    public static Context parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Context) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Context parseFrom(InputStream inputStream) throws IOException {
        return (Context) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Context parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Context) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Context parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Context) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Context parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Context) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Context parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Context) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Context parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Context) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Context context) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(context);
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

    public static Context getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Context> parser() {
        return PARSER;
    }

    public Parser<Context> getParserForType() {
        return PARSER;
    }

    public Context getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}