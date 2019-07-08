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

public final class Http extends GeneratedMessageV3 implements HttpOrBuilder {
    private static final Http DEFAULT_INSTANCE = new Http();
    public static final int FULLY_DECODE_RESERVED_EXPANSION_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<Http> PARSER = new AbstractParser<Http>() {
        public Http parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Http(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RULES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public boolean fullyDecodeReservedExpansion_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<HttpRule> rules_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements HttpOrBuilder {
        private int bitField0_;
        private boolean fullyDecodeReservedExpansion_;
        private RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> rulesBuilder_;
        private List<HttpRule> rules_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_Http_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_Http_fieldAccessorTable.ensureFieldAccessorsInitialized(Http.class, Builder.class);
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
            if (Http.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.fullyDecodeReservedExpansion_ = false;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_Http_descriptor;
        }

        public Http getDefaultInstanceForType() {
            return Http.getDefaultInstance();
        }

        public Http build() {
            Http buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Http buildPartial() {
            Http http = new Http((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -2;
                }
                http.rules_ = this.rules_;
            } else {
                http.rules_ = repeatedFieldBuilderV3.build();
            }
            http.fullyDecodeReservedExpansion_ = this.fullyDecodeReservedExpansion_;
            http.bitField0_ = 0;
            onBuilt();
            return http;
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
            if (message instanceof Http) {
                return mergeFrom((Http) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Http http) {
            if (http == Http.getDefaultInstance()) {
                return this;
            }
            if (this.rulesBuilder_ == null) {
                if (!http.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = http.rules_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(http.rules_);
                    }
                    onChanged();
                }
            } else if (!http.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = null;
                    this.rulesBuilder_ = null;
                    this.rules_ = http.rules_;
                    this.bitField0_ &= -2;
                    if (Http.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getRulesFieldBuilder();
                    }
                    this.rulesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.rulesBuilder_.addAllMessages(http.rules_);
                }
            }
            if (http.getFullyDecodeReservedExpansion()) {
                setFullyDecodeReservedExpansion(http.getFullyDecodeReservedExpansion());
            }
            mergeUnknownFields(http.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Http.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Http.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Http r3 = (com.google.api.Http) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Http r4 = (com.google.api.Http) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Http.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Http$Builder");
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        public List<HttpRule> getRulesList() {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRulesCount() {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public HttpRule getRules(int i) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (HttpRule) this.rules_.get(i);
            }
            return (HttpRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, httpRule);
            } else if (httpRule != null) {
                ensureRulesIsMutable();
                this.rules_.set(i, httpRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRules(int i, com.google.api.HttpRule.Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(httpRule);
            } else if (httpRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(httpRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, httpRule);
            } else if (httpRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(i, httpRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(com.google.api.HttpRule.Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, com.google.api.HttpRule.Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends HttpRule> iterable) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
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
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
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
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.HttpRule.Builder getRulesBuilder(int i) {
            return (com.google.api.HttpRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        public HttpRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (HttpRuleOrBuilder) this.rules_.get(i);
            }
            return (HttpRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends HttpRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public com.google.api.HttpRule.Builder addRulesBuilder() {
            return (com.google.api.HttpRule.Builder) getRulesFieldBuilder().addBuilder(HttpRule.getDefaultInstance());
        }

        public com.google.api.HttpRule.Builder addRulesBuilder(int i) {
            return (com.google.api.HttpRule.Builder) getRulesFieldBuilder().addBuilder(i, HttpRule.getDefaultInstance());
        }

        public List<com.google.api.HttpRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HttpRule, com.google.api.HttpRule.Builder, HttpRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                List<HttpRule> list = this.rules_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        public boolean getFullyDecodeReservedExpansion() {
            return this.fullyDecodeReservedExpansion_;
        }

        public Builder setFullyDecodeReservedExpansion(boolean z) {
            this.fullyDecodeReservedExpansion_ = z;
            onChanged();
            return this;
        }

        public Builder clearFullyDecodeReservedExpansion() {
            this.fullyDecodeReservedExpansion_ = false;
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

    private Http(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Http() {
        this.memoizedIsInitialized = -1;
        this.rules_ = Collections.emptyList();
        this.fullyDecodeReservedExpansion_ = false;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Http(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.rules_.add(codedInputStream.readMessage(HttpRule.parser(), extensionRegistryLite));
                    } else if (readTag == 16) {
                        this.fullyDecodeReservedExpansion_ = codedInputStream.readBool();
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
        return HttpProto.internal_static_google_api_Http_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpProto.internal_static_google_api_Http_fieldAccessorTable.ensureFieldAccessorsInitialized(Http.class, Builder.class);
    }

    public List<HttpRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends HttpRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public HttpRule getRules(int i) {
        return (HttpRule) this.rules_.get(i);
    }

    public HttpRuleOrBuilder getRulesOrBuilder(int i) {
        return (HttpRuleOrBuilder) this.rules_.get(i);
    }

    public boolean getFullyDecodeReservedExpansion() {
        return this.fullyDecodeReservedExpansion_;
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
        boolean z = this.fullyDecodeReservedExpansion_;
        if (z) {
            codedOutputStream.writeBool(2, z);
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
        boolean z = this.fullyDecodeReservedExpansion_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(2, z);
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
        if (!(obj instanceof Http)) {
            return super.equals(obj);
        }
        Http http = (Http) obj;
        if (!((getRulesList().equals(http.getRulesList())) && getFullyDecodeReservedExpansion() == http.getFullyDecodeReservedExpansion()) || !this.unknownFields.equals(http.unknownFields)) {
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
        int hashBoolean = (((((hashCode * 37) + 2) * 53) + Internal.hashBoolean(getFullyDecodeReservedExpansion())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashBoolean;
        return hashBoolean;
    }

    public static Http parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Http) PARSER.parseFrom(byteBuffer);
    }

    public static Http parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Http) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Http parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Http) PARSER.parseFrom(byteString);
    }

    public static Http parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Http) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Http parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Http) PARSER.parseFrom(bArr);
    }

    public static Http parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Http) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Http parseFrom(InputStream inputStream) throws IOException {
        return (Http) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Http parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Http) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Http parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Http) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Http parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Http) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Http parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Http) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Http parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Http) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Http http) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(http);
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

    public static Http getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Http> parser() {
        return PARSER;
    }

    public Parser<Http> getParserForType() {
        return PARSER;
    }

    public Http getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
