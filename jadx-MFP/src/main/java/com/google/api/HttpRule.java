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
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HttpRule extends GeneratedMessageV3 implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    private static final HttpRule DEFAULT_INSTANCE = new HttpRule();
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<HttpRule> PARSER = new AbstractParser<HttpRule>() {
        public HttpRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<HttpRule> additionalBindings_;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object body_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public int patternCase_;
    /* access modifiers changed from: private */
    public Object pattern_;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements HttpRuleOrBuilder {
        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> additionalBindingsBuilder_;
        private List<HttpRule> additionalBindings_;
        private int bitField0_;
        private Object body_;
        private SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> customBuilder_;
        private int patternCase_;
        private Object pattern_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
        }

        private Builder() {
            this.patternCase_ = 0;
            this.selector_ = "";
            this.body_ = "";
            this.additionalBindings_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.patternCase_ = 0;
            this.selector_ = "";
            this.body_ = "";
            this.additionalBindings_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (HttpRule.alwaysUseFieldBuilders) {
                getAdditionalBindingsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.body_ = "";
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= -257;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.patternCase_ = 0;
            this.pattern_ = null;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return HttpProto.internal_static_google_api_HttpRule_descriptor;
        }

        public HttpRule getDefaultInstanceForType() {
            return HttpRule.getDefaultInstance();
        }

        public HttpRule build() {
            HttpRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public HttpRule buildPartial() {
            HttpRule httpRule = new HttpRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            httpRule.selector_ = this.selector_;
            if (this.patternCase_ == 2) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 3) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 4) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 5) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 6) {
                httpRule.pattern_ = this.pattern_;
            }
            if (this.patternCase_ == 8) {
                SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
                if (singleFieldBuilderV3 == null) {
                    httpRule.pattern_ = this.pattern_;
                } else {
                    httpRule.pattern_ = singleFieldBuilderV3.build();
                }
            }
            httpRule.body_ = this.body_;
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 256) == 256) {
                    this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                    this.bitField0_ &= -257;
                }
                httpRule.additionalBindings_ = this.additionalBindings_;
            } else {
                httpRule.additionalBindings_ = repeatedFieldBuilderV3.build();
            }
            httpRule.bitField0_ = 0;
            httpRule.patternCase_ = this.patternCase_;
            onBuilt();
            return httpRule;
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
            if (message instanceof HttpRule) {
                return mergeFrom((HttpRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpRule httpRule) {
            if (httpRule == HttpRule.getDefaultInstance()) {
                return this;
            }
            if (!httpRule.getSelector().isEmpty()) {
                this.selector_ = httpRule.selector_;
                onChanged();
            }
            if (!httpRule.getBody().isEmpty()) {
                this.body_ = httpRule.body_;
                onChanged();
            }
            if (this.additionalBindingsBuilder_ == null) {
                if (!httpRule.additionalBindings_.isEmpty()) {
                    if (this.additionalBindings_.isEmpty()) {
                        this.additionalBindings_ = httpRule.additionalBindings_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureAdditionalBindingsIsMutable();
                        this.additionalBindings_.addAll(httpRule.additionalBindings_);
                    }
                    onChanged();
                }
            } else if (!httpRule.additionalBindings_.isEmpty()) {
                if (this.additionalBindingsBuilder_.isEmpty()) {
                    this.additionalBindingsBuilder_.dispose();
                    RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = null;
                    this.additionalBindingsBuilder_ = null;
                    this.additionalBindings_ = httpRule.additionalBindings_;
                    this.bitField0_ &= -257;
                    if (HttpRule.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getAdditionalBindingsFieldBuilder();
                    }
                    this.additionalBindingsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.additionalBindingsBuilder_.addAllMessages(httpRule.additionalBindings_);
                }
            }
            switch (httpRule.getPatternCase()) {
                case GET:
                    this.patternCase_ = 2;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case PUT:
                    this.patternCase_ = 3;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case POST:
                    this.patternCase_ = 4;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case DELETE:
                    this.patternCase_ = 5;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case PATCH:
                    this.patternCase_ = 6;
                    this.pattern_ = httpRule.pattern_;
                    onChanged();
                    break;
                case CUSTOM:
                    mergeCustom(httpRule.getCustom());
                    break;
            }
            mergeUnknownFields(httpRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.HttpRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.HttpRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.HttpRule r3 = (com.google.api.HttpRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.HttpRule r4 = (com.google.api.HttpRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.HttpRule$Builder");
        }

        public PatternCase getPatternCase() {
            return PatternCase.forNumber(this.patternCase_);
        }

        public Builder clearPattern() {
            this.patternCase_ = 0;
            this.pattern_ = null;
            onChanged();
            return this;
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
            this.selector_ = HttpRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getGet() {
            Object obj = "";
            if (this.patternCase_ == 2) {
                obj = this.pattern_;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            if (this.patternCase_ == 2) {
                this.pattern_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getGetBytes() {
            Object obj = "";
            if (this.patternCase_ == 2) {
                obj = this.pattern_;
            }
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            if (this.patternCase_ == 2) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }

        public Builder setGet(String str) {
            if (str != null) {
                this.patternCase_ = 2;
                this.pattern_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearGet() {
            if (this.patternCase_ == 2) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setGetBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 2;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getPut() {
            Object obj = "";
            if (this.patternCase_ == 3) {
                obj = this.pattern_;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            if (this.patternCase_ == 3) {
                this.pattern_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPutBytes() {
            Object obj = "";
            if (this.patternCase_ == 3) {
                obj = this.pattern_;
            }
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            if (this.patternCase_ == 3) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }

        public Builder setPut(String str) {
            if (str != null) {
                this.patternCase_ = 3;
                this.pattern_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPut() {
            if (this.patternCase_ == 3) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setPutBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 3;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getPost() {
            Object obj = "";
            if (this.patternCase_ == 4) {
                obj = this.pattern_;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            if (this.patternCase_ == 4) {
                this.pattern_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPostBytes() {
            Object obj = "";
            if (this.patternCase_ == 4) {
                obj = this.pattern_;
            }
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            if (this.patternCase_ == 4) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }

        public Builder setPost(String str) {
            if (str != null) {
                this.patternCase_ = 4;
                this.pattern_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPost() {
            if (this.patternCase_ == 4) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setPostBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 4;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDelete() {
            Object obj = "";
            if (this.patternCase_ == 5) {
                obj = this.pattern_;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            if (this.patternCase_ == 5) {
                this.pattern_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getDeleteBytes() {
            Object obj = "";
            if (this.patternCase_ == 5) {
                obj = this.pattern_;
            }
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            if (this.patternCase_ == 5) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }

        public Builder setDelete(String str) {
            if (str != null) {
                this.patternCase_ = 5;
                this.pattern_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDelete() {
            if (this.patternCase_ == 5) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 5;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getPatch() {
            Object obj = "";
            if (this.patternCase_ == 6) {
                obj = this.pattern_;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            if (this.patternCase_ == 6) {
                this.pattern_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPatchBytes() {
            Object obj = "";
            if (this.patternCase_ == 6) {
                obj = this.pattern_;
            }
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            if (this.patternCase_ == 6) {
                this.pattern_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }

        public Builder setPatch(String str) {
            if (str != null) {
                this.patternCase_ = 6;
                this.pattern_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPatch() {
            if (this.patternCase_ == 6) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setPatchBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.patternCase_ = 6;
                this.pattern_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean hasCustom() {
            return this.patternCase_ == 8;
        }

        public CustomHttpPattern getCustom() {
            SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.patternCase_ == 8) {
                    return (CustomHttpPattern) this.pattern_;
                }
                return CustomHttpPattern.getDefaultInstance();
            } else if (this.patternCase_ == 8) {
                return (CustomHttpPattern) singleFieldBuilderV3.getMessage();
            } else {
                return CustomHttpPattern.getDefaultInstance();
            }
        }

        public Builder setCustom(CustomHttpPattern customHttpPattern) {
            SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(customHttpPattern);
            } else if (customHttpPattern != null) {
                this.pattern_ = customHttpPattern;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder setCustom(com.google.api.CustomHttpPattern.Builder builder) {
            SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.pattern_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern customHttpPattern) {
            SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.patternCase_ != 8 || this.pattern_ == CustomHttpPattern.getDefaultInstance()) {
                    this.pattern_ = customHttpPattern;
                } else {
                    this.pattern_ = CustomHttpPattern.newBuilder((CustomHttpPattern) this.pattern_).mergeFrom(customHttpPattern).buildPartial();
                }
                onChanged();
            } else {
                if (this.patternCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(customHttpPattern);
                }
                this.customBuilder_.setMessage(customHttpPattern);
            }
            this.patternCase_ = 8;
            return this;
        }

        public Builder clearCustom() {
            if (this.customBuilder_ != null) {
                if (this.patternCase_ == 8) {
                    this.patternCase_ = 0;
                    this.pattern_ = null;
                }
                this.customBuilder_.clear();
            } else if (this.patternCase_ == 8) {
                this.patternCase_ = 0;
                this.pattern_ = null;
                onChanged();
            }
            return this;
        }

        public com.google.api.CustomHttpPattern.Builder getCustomBuilder() {
            return (com.google.api.CustomHttpPattern.Builder) getCustomFieldBuilder().getBuilder();
        }

        public CustomHttpPatternOrBuilder getCustomOrBuilder() {
            if (this.patternCase_ == 8) {
                SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> singleFieldBuilderV3 = this.customBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (CustomHttpPatternOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
            }
            if (this.patternCase_ == 8) {
                return (CustomHttpPattern) this.pattern_;
            }
            return CustomHttpPattern.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CustomHttpPattern, com.google.api.CustomHttpPattern.Builder, CustomHttpPatternOrBuilder> getCustomFieldBuilder() {
            if (this.customBuilder_ == null) {
                if (this.patternCase_ != 8) {
                    this.pattern_ = CustomHttpPattern.getDefaultInstance();
                }
                this.customBuilder_ = new SingleFieldBuilderV3<>((CustomHttpPattern) this.pattern_, getParentForChildren(), isClean());
                this.pattern_ = null;
            }
            this.patternCase_ = 8;
            onChanged();
            return this.customBuilder_;
        }

        public String getBody() {
            Object obj = this.body_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.body_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getBodyBytes() {
            Object obj = this.body_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.body_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setBody(String str) {
            if (str != null) {
                this.body_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearBody() {
            this.body_ = HttpRule.getDefaultInstance().getBody();
            onChanged();
            return this;
        }

        public Builder setBodyBytes(ByteString byteString) {
            if (byteString != null) {
                HttpRule.checkByteStringIsUtf8(byteString);
                this.body_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureAdditionalBindingsIsMutable() {
            if ((this.bitField0_ & 256) != 256) {
                this.additionalBindings_ = new ArrayList(this.additionalBindings_);
                this.bitField0_ |= 256;
            }
        }

        public List<HttpRule> getAdditionalBindingsList() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.additionalBindings_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getAdditionalBindingsCount() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.additionalBindings_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public HttpRule getAdditionalBindings(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (HttpRule) this.additionalBindings_.get(i);
            }
            return (HttpRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAdditionalBindings(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, httpRule);
            } else if (httpRule != null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(i, httpRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAdditionalBindings(int i, Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAdditionalBindings(HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(httpRule);
            } else if (httpRule != null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(httpRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addAdditionalBindings(int i, HttpRule httpRule) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, httpRule);
            } else if (httpRule != null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(i, httpRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addAdditionalBindings(Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAdditionalBindings(int i, Builder builder) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.additionalBindings_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAdditionalBindings() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.additionalBindings_ = Collections.emptyList();
                this.bitField0_ &= -257;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAdditionalBindings(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdditionalBindingsIsMutable();
                this.additionalBindings_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getAdditionalBindingsBuilder(int i) {
            return (Builder) getAdditionalBindingsFieldBuilder().getBuilder(i);
        }

        public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (HttpRuleOrBuilder) this.additionalBindings_.get(i);
            }
            return (HttpRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
            RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> repeatedFieldBuilderV3 = this.additionalBindingsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.additionalBindings_);
        }

        public Builder addAdditionalBindingsBuilder() {
            return (Builder) getAdditionalBindingsFieldBuilder().addBuilder(HttpRule.getDefaultInstance());
        }

        public Builder addAdditionalBindingsBuilder(int i) {
            return (Builder) getAdditionalBindingsFieldBuilder().addBuilder(i, HttpRule.getDefaultInstance());
        }

        public List<Builder> getAdditionalBindingsBuilderList() {
            return getAdditionalBindingsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HttpRule, Builder, HttpRuleOrBuilder> getAdditionalBindingsFieldBuilder() {
            if (this.additionalBindingsBuilder_ == null) {
                this.additionalBindingsBuilder_ = new RepeatedFieldBuilderV3<>(this.additionalBindings_, (this.bitField0_ & 256) == 256, getParentForChildren(), isClean());
                this.additionalBindings_ = null;
            }
            return this.additionalBindingsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public enum PatternCase implements EnumLite {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);
        
        private final int value;

        private PatternCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static PatternCase valueOf(int i) {
            return forNumber(i);
        }

        public static PatternCase forNumber(int i) {
            if (i == 0) {
                return PATTERN_NOT_SET;
            }
            if (i == 8) {
                return CUSTOM;
            }
            switch (i) {
                case 2:
                    return GET;
                case 3:
                    return PUT;
                case 4:
                    return POST;
                case 5:
                    return DELETE;
                case 6:
                    return PATCH;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    private HttpRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.patternCase_ = 0;
        this.memoizedIsInitialized = -1;
    }

    private HttpRule() {
        this.patternCase_ = 0;
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.body_ = "";
        this.additionalBindings_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HttpRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.patternCase_ = 2;
                        this.pattern_ = readStringRequireUtf8;
                    } else if (readTag == 26) {
                        String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                        this.patternCase_ = 3;
                        this.pattern_ = readStringRequireUtf82;
                    } else if (readTag == 34) {
                        String readStringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                        this.patternCase_ = 4;
                        this.pattern_ = readStringRequireUtf83;
                    } else if (readTag == 42) {
                        String readStringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                        this.patternCase_ = 5;
                        this.pattern_ = readStringRequireUtf84;
                    } else if (readTag == 50) {
                        String readStringRequireUtf85 = codedInputStream.readStringRequireUtf8();
                        this.patternCase_ = 6;
                        this.pattern_ = readStringRequireUtf85;
                    } else if (readTag == 58) {
                        this.body_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 66) {
                        com.google.api.CustomHttpPattern.Builder builder = null;
                        if (this.patternCase_ == 8) {
                            builder = ((CustomHttpPattern) this.pattern_).toBuilder();
                        }
                        this.pattern_ = codedInputStream.readMessage(CustomHttpPattern.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom((CustomHttpPattern) this.pattern_);
                            this.pattern_ = builder.buildPartial();
                        }
                        this.patternCase_ = 8;
                    } else if (readTag == 90) {
                        if (!(z2 & true)) {
                            this.additionalBindings_ = new ArrayList();
                            z2 |= true;
                        }
                        this.additionalBindings_.add(codedInputStream.readMessage(parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.additionalBindings_ = Collections.unmodifiableList(this.additionalBindings_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return HttpProto.internal_static_google_api_HttpRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpProto.internal_static_google_api_HttpRule_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpRule.class, Builder.class);
    }

    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
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

    public String getGet() {
        Object obj = "";
        if (this.patternCase_ == 2) {
            obj = this.pattern_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.patternCase_ == 2) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    public ByteString getGetBytes() {
        Object obj = "";
        if (this.patternCase_ == 2) {
            obj = this.pattern_;
        }
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        if (this.patternCase_ == 2) {
            this.pattern_ = copyFromUtf8;
        }
        return copyFromUtf8;
    }

    public String getPut() {
        Object obj = "";
        if (this.patternCase_ == 3) {
            obj = this.pattern_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.patternCase_ == 3) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    public ByteString getPutBytes() {
        Object obj = "";
        if (this.patternCase_ == 3) {
            obj = this.pattern_;
        }
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        if (this.patternCase_ == 3) {
            this.pattern_ = copyFromUtf8;
        }
        return copyFromUtf8;
    }

    public String getPost() {
        Object obj = "";
        if (this.patternCase_ == 4) {
            obj = this.pattern_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.patternCase_ == 4) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    public ByteString getPostBytes() {
        Object obj = "";
        if (this.patternCase_ == 4) {
            obj = this.pattern_;
        }
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        if (this.patternCase_ == 4) {
            this.pattern_ = copyFromUtf8;
        }
        return copyFromUtf8;
    }

    public String getDelete() {
        Object obj = "";
        if (this.patternCase_ == 5) {
            obj = this.pattern_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.patternCase_ == 5) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    public ByteString getDeleteBytes() {
        Object obj = "";
        if (this.patternCase_ == 5) {
            obj = this.pattern_;
        }
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        if (this.patternCase_ == 5) {
            this.pattern_ = copyFromUtf8;
        }
        return copyFromUtf8;
    }

    public String getPatch() {
        Object obj = "";
        if (this.patternCase_ == 6) {
            obj = this.pattern_;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        if (this.patternCase_ == 6) {
            this.pattern_ = stringUtf8;
        }
        return stringUtf8;
    }

    public ByteString getPatchBytes() {
        Object obj = "";
        if (this.patternCase_ == 6) {
            obj = this.pattern_;
        }
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        if (this.patternCase_ == 6) {
            this.pattern_ = copyFromUtf8;
        }
        return copyFromUtf8;
    }

    public boolean hasCustom() {
        return this.patternCase_ == 8;
    }

    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    public CustomHttpPatternOrBuilder getCustomOrBuilder() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    public String getBody() {
        Object obj = this.body_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.body_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getBodyBytes() {
        Object obj = this.body_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.body_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    public HttpRule getAdditionalBindings(int i) {
        return (HttpRule) this.additionalBindings_.get(i);
    }

    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
        return (HttpRuleOrBuilder) this.additionalBindings_.get(i);
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
        if (this.patternCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.pattern_);
        }
        if (!getBodyBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.body_);
        }
        if (this.patternCase_ == 8) {
            codedOutputStream.writeMessage(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i = 0; i < this.additionalBindings_.size(); i++) {
            codedOutputStream.writeMessage(11, (MessageLite) this.additionalBindings_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        if (this.patternCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.pattern_);
        }
        if (this.patternCase_ == 3) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.pattern_);
        }
        if (this.patternCase_ == 4) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.pattern_);
        }
        if (this.patternCase_ == 5) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.pattern_);
        }
        if (this.patternCase_ == 6) {
            computeStringSize += GeneratedMessageV3.computeStringSize(6, this.pattern_);
        }
        if (!getBodyBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(7, this.body_);
        }
        if (this.patternCase_ == 8) {
            computeStringSize += CodedOutputStream.computeMessageSize(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i2 = 0; i2 < this.additionalBindings_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(11, (MessageLite) this.additionalBindings_.get(i2));
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
        if (!(obj instanceof HttpRule)) {
            return super.equals(obj);
        }
        HttpRule httpRule = (HttpRule) obj;
        boolean z2 = (((getSelector().equals(httpRule.getSelector())) && getBody().equals(httpRule.getBody())) && getAdditionalBindingsList().equals(httpRule.getAdditionalBindingsList())) && getPatternCase().equals(httpRule.getPatternCase());
        if (!z2) {
            return false;
        }
        switch (this.patternCase_) {
            case 2:
                if (z2 && getGet().equals(httpRule.getGet())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 3:
                if (z2 && getPut().equals(httpRule.getPut())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 4:
                if (z2 && getPost().equals(httpRule.getPost())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 5:
                if (z2 && getDelete().equals(httpRule.getDelete())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 6:
                if (z2 && getPatch().equals(httpRule.getPatch())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
            case 8:
                if (z2 && getCustom().equals(httpRule.getCustom())) {
                    z2 = true;
                    break;
                } else {
                    z2 = false;
                    break;
                }
                break;
        }
        if (!z2 || !this.unknownFields.equals(httpRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 7) * 53) + getBody().hashCode();
        if (getAdditionalBindingsCount() > 0) {
            hashCode = (((hashCode * 37) + 11) * 53) + getAdditionalBindingsList().hashCode();
        }
        switch (this.patternCase_) {
            case 2:
                hashCode = (((hashCode * 37) + 2) * 53) + getGet().hashCode();
                break;
            case 3:
                hashCode = (((hashCode * 37) + 3) * 53) + getPut().hashCode();
                break;
            case 4:
                hashCode = (((hashCode * 37) + 4) * 53) + getPost().hashCode();
                break;
            case 5:
                hashCode = (((hashCode * 37) + 5) * 53) + getDelete().hashCode();
                break;
            case 6:
                hashCode = (((hashCode * 37) + 6) * 53) + getPatch().hashCode();
                break;
            case 8:
                hashCode = (((hashCode * 37) + 8) * 53) + getCustom().hashCode();
                break;
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteBuffer);
    }

    public static HttpRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteString);
    }

    public static HttpRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(bArr);
    }

    public static HttpRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpRule parseFrom(InputStream inputStream) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpRule httpRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(httpRule);
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

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return PARSER;
    }

    public Parser<HttpRule> getParserForType() {
        return PARSER;
    }

    public HttpRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
