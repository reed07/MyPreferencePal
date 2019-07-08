package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Api extends GeneratedMessageV3 implements ApiOrBuilder {
    private static final Api DEFAULT_INSTANCE = new Api();
    public static final int METHODS_FIELD_NUMBER = 2;
    public static final int MIXINS_FIELD_NUMBER = 6;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<Api> PARSER = new AbstractParser<Api>() {
        public Api parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Api(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 7;
    public static final int VERSION_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<Method> methods_;
    /* access modifiers changed from: private */
    public List<Mixin> mixins_;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public List<Option> options_;
    /* access modifiers changed from: private */
    public SourceContext sourceContext_;
    /* access modifiers changed from: private */
    public int syntax_;
    /* access modifiers changed from: private */
    public volatile Object version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ApiOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> methodsBuilder_;
        private List<Method> methods_;
        private RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> mixinsBuilder_;
        private List<Mixin> mixins_;
        private Object name_;
        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
        private SourceContext sourceContext_;
        private int syntax_;
        private Object version_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ApiProto.internal_static_google_protobuf_Api_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized(Api.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.methods_ = Collections.emptyList();
            this.options_ = Collections.emptyList();
            this.version_ = "";
            this.sourceContext_ = null;
            this.mixins_ = Collections.emptyList();
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.methods_ = Collections.emptyList();
            this.options_ = Collections.emptyList();
            this.version_ = "";
            this.sourceContext_ = null;
            this.mixins_ = Collections.emptyList();
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getMethodsFieldBuilder();
                getOptionsFieldBuilder();
                getMixinsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.methods_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV32 = this.optionsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            this.version_ = "";
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = null;
            } else {
                this.sourceContext_ = null;
                this.sourceContextBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV33 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.mixins_ = Collections.emptyList();
                this.bitField0_ &= -33;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            this.syntax_ = 0;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ApiProto.internal_static_google_protobuf_Api_descriptor;
        }

        public Api getDefaultInstanceForType() {
            return Api.getDefaultInstance();
        }

        public Api build() {
            Api buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Api buildPartial() {
            Api api = new Api((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            api.name_ = this.name_;
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.methods_ = Collections.unmodifiableList(this.methods_);
                    this.bitField0_ &= -3;
                }
                api.methods_ = this.methods_;
            } else {
                api.methods_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV32 = this.optionsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -5;
                }
                api.options_ = this.options_;
            } else {
                api.options_ = repeatedFieldBuilderV32.build();
            }
            api.version_ = this.version_;
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                api.sourceContext_ = this.sourceContext_;
            } else {
                api.sourceContext_ = (SourceContext) singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV33 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                if ((this.bitField0_ & 32) == 32) {
                    this.mixins_ = Collections.unmodifiableList(this.mixins_);
                    this.bitField0_ &= -33;
                }
                api.mixins_ = this.mixins_;
            } else {
                api.mixins_ = repeatedFieldBuilderV33.build();
            }
            api.syntax_ = this.syntax_;
            api.bitField0_ = 0;
            onBuilt();
            return api;
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
            if (message instanceof Api) {
                return mergeFrom((Api) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Api api) {
            if (api == Api.getDefaultInstance()) {
                return this;
            }
            if (!api.getName().isEmpty()) {
                this.name_ = api.name_;
                onChanged();
            }
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.methodsBuilder_ == null) {
                if (!api.methods_.isEmpty()) {
                    if (this.methods_.isEmpty()) {
                        this.methods_ = api.methods_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMethodsIsMutable();
                        this.methods_.addAll(api.methods_);
                    }
                    onChanged();
                }
            } else if (!api.methods_.isEmpty()) {
                if (this.methodsBuilder_.isEmpty()) {
                    this.methodsBuilder_.dispose();
                    this.methodsBuilder_ = null;
                    this.methods_ = api.methods_;
                    this.bitField0_ &= -3;
                    this.methodsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMethodsFieldBuilder() : null;
                } else {
                    this.methodsBuilder_.addAllMessages(api.methods_);
                }
            }
            if (this.optionsBuilder_ == null) {
                if (!api.options_.isEmpty()) {
                    if (this.options_.isEmpty()) {
                        this.options_ = api.options_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureOptionsIsMutable();
                        this.options_.addAll(api.options_);
                    }
                    onChanged();
                }
            } else if (!api.options_.isEmpty()) {
                if (this.optionsBuilder_.isEmpty()) {
                    this.optionsBuilder_.dispose();
                    this.optionsBuilder_ = null;
                    this.options_ = api.options_;
                    this.bitField0_ &= -5;
                    this.optionsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getOptionsFieldBuilder() : null;
                } else {
                    this.optionsBuilder_.addAllMessages(api.options_);
                }
            }
            if (!api.getVersion().isEmpty()) {
                this.version_ = api.version_;
                onChanged();
            }
            if (api.hasSourceContext()) {
                mergeSourceContext(api.getSourceContext());
            }
            if (this.mixinsBuilder_ == null) {
                if (!api.mixins_.isEmpty()) {
                    if (this.mixins_.isEmpty()) {
                        this.mixins_ = api.mixins_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureMixinsIsMutable();
                        this.mixins_.addAll(api.mixins_);
                    }
                    onChanged();
                }
            } else if (!api.mixins_.isEmpty()) {
                if (this.mixinsBuilder_.isEmpty()) {
                    this.mixinsBuilder_.dispose();
                    this.mixinsBuilder_ = null;
                    this.mixins_ = api.mixins_;
                    this.bitField0_ &= -33;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getMixinsFieldBuilder();
                    }
                    this.mixinsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.mixinsBuilder_.addAllMessages(api.mixins_);
                }
            }
            if (api.syntax_ != 0) {
                setSyntaxValue(api.getSyntaxValue());
            }
            mergeUnknownFields(api.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.protobuf.Api.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Api.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.protobuf.Api r3 = (com.google.protobuf.Api) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.protobuf.Api r4 = (com.google.protobuf.Api) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Api.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Api$Builder");
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = Api.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureMethodsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.methods_ = new ArrayList(this.methods_);
                this.bitField0_ |= 2;
            }
        }

        public List<Method> getMethodsList() {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.methods_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getMethodsCount() {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.methods_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Method getMethods(int i) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Method) this.methods_.get(i);
            }
            return (Method) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMethods(int i, Method method) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, method);
            } else if (method != null) {
                ensureMethodsIsMutable();
                this.methods_.set(i, method);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMethods(int i, com.google.protobuf.Method.Builder builder) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMethodsIsMutable();
                this.methods_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMethods(Method method) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(method);
            } else if (method != null) {
                ensureMethodsIsMutable();
                this.methods_.add(method);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMethods(int i, Method method) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, method);
            } else if (method != null) {
                ensureMethodsIsMutable();
                this.methods_.add(i, method);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMethods(com.google.protobuf.Method.Builder builder) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMethodsIsMutable();
                this.methods_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMethods(int i, com.google.protobuf.Method.Builder builder) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMethodsIsMutable();
                this.methods_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMethods(Iterable<? extends Method> iterable) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMethodsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.methods_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMethods() {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.methods_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMethods(int i) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMethodsIsMutable();
                this.methods_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Method.Builder getMethodsBuilder(int i) {
            return (com.google.protobuf.Method.Builder) getMethodsFieldBuilder().getBuilder(i);
        }

        public MethodOrBuilder getMethodsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MethodOrBuilder) this.methods_.get(i);
            }
            return (MethodOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
            RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> repeatedFieldBuilderV3 = this.methodsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.methods_);
        }

        public com.google.protobuf.Method.Builder addMethodsBuilder() {
            return (com.google.protobuf.Method.Builder) getMethodsFieldBuilder().addBuilder(Method.getDefaultInstance());
        }

        public com.google.protobuf.Method.Builder addMethodsBuilder(int i) {
            return (com.google.protobuf.Method.Builder) getMethodsFieldBuilder().addBuilder(i, Method.getDefaultInstance());
        }

        public List<com.google.protobuf.Method.Builder> getMethodsBuilderList() {
            return getMethodsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Method, com.google.protobuf.Method.Builder, MethodOrBuilder> getMethodsFieldBuilder() {
            if (this.methodsBuilder_ == null) {
                this.methodsBuilder_ = new RepeatedFieldBuilderV3<>(this.methods_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.methods_ = null;
            }
            return this.methodsBuilder_;
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 4;
            }
        }

        public List<Option> getOptionsList() {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.options_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getOptionsCount() {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.options_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Option getOptions(int i) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Option) this.options_.get(i);
            }
            return (Option) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setOptions(int i, Option option) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, option);
            } else if (option != null) {
                ensureOptionsIsMutable();
                this.options_.set(i, option);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setOptions(int i, com.google.protobuf.Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addOptions(Option option) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(option);
            } else if (option != null) {
                ensureOptionsIsMutable();
                this.options_.add(option);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addOptions(int i, Option option) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, option);
            } else if (option != null) {
                ensureOptionsIsMutable();
                this.options_.add(i, option);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addOptions(com.google.protobuf.Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addOptions(int i, com.google.protobuf.Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllOptions(Iterable<? extends Option> iterable) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.options_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearOptions() {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeOptions(int i) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Option.Builder getOptionsBuilder(int i) {
            return (com.google.protobuf.Option.Builder) getOptionsFieldBuilder().getBuilder(i);
        }

        public OptionOrBuilder getOptionsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (OptionOrBuilder) this.options_.get(i);
            }
            return (OptionOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.options_);
        }

        public com.google.protobuf.Option.Builder addOptionsBuilder() {
            return (com.google.protobuf.Option.Builder) getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
        }

        public com.google.protobuf.Option.Builder addOptionsBuilder(int i) {
            return (com.google.protobuf.Option.Builder) getOptionsFieldBuilder().addBuilder(i, Option.getDefaultInstance());
        }

        public List<com.google.protobuf.Option.Builder> getOptionsBuilderList() {
            return getOptionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() {
            if (this.optionsBuilder_ == null) {
                this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.options_ = null;
            }
            return this.optionsBuilder_;
        }

        public String getVersion() {
            Object obj = this.version_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.version_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getVersionBytes() {
            Object obj = this.version_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.version_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setVersion(String str) {
            if (str != null) {
                this.version_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearVersion() {
            this.version_ = Api.getDefaultInstance().getVersion();
            onChanged();
            return this;
        }

        public Builder setVersionBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.version_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean hasSourceContext() {
            return (this.sourceContextBuilder_ == null && this.sourceContext_ == null) ? false : true;
        }

        public SourceContext getSourceContext() {
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SourceContext) singleFieldBuilderV3.getMessage();
            }
            SourceContext sourceContext = this.sourceContext_;
            if (sourceContext == null) {
                sourceContext = SourceContext.getDefaultInstance();
            }
            return sourceContext;
        }

        public Builder setSourceContext(SourceContext sourceContext) {
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sourceContext);
            } else if (sourceContext != null) {
                this.sourceContext_ = sourceContext;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSourceContext(com.google.protobuf.SourceContext.Builder builder) {
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sourceContext_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSourceContext(SourceContext sourceContext) {
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceContext sourceContext2 = this.sourceContext_;
                if (sourceContext2 != null) {
                    this.sourceContext_ = SourceContext.newBuilder(sourceContext2).mergeFrom(sourceContext).buildPartial();
                } else {
                    this.sourceContext_ = sourceContext;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sourceContext);
            }
            return this;
        }

        public Builder clearSourceContext() {
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = null;
                onChanged();
            } else {
                this.sourceContext_ = null;
                this.sourceContextBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.SourceContext.Builder getSourceContextBuilder() {
            onChanged();
            return (com.google.protobuf.SourceContext.Builder) getSourceContextFieldBuilder().getBuilder();
        }

        public SourceContextOrBuilder getSourceContextOrBuilder() {
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SourceContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SourceContext sourceContext = this.sourceContext_;
            if (sourceContext == null) {
                sourceContext = SourceContext.getDefaultInstance();
            }
            return sourceContext;
        }

        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> getSourceContextFieldBuilder() {
            if (this.sourceContextBuilder_ == null) {
                this.sourceContextBuilder_ = new SingleFieldBuilderV3<>(getSourceContext(), getParentForChildren(), isClean());
                this.sourceContext_ = null;
            }
            return this.sourceContextBuilder_;
        }

        private void ensureMixinsIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.mixins_ = new ArrayList(this.mixins_);
                this.bitField0_ |= 32;
            }
        }

        public List<Mixin> getMixinsList() {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.mixins_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getMixinsCount() {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.mixins_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Mixin getMixins(int i) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Mixin) this.mixins_.get(i);
            }
            return (Mixin) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMixins(int i, Mixin mixin) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, mixin);
            } else if (mixin != null) {
                ensureMixinsIsMutable();
                this.mixins_.set(i, mixin);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMixins(int i, com.google.protobuf.Mixin.Builder builder) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMixinsIsMutable();
                this.mixins_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMixins(Mixin mixin) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(mixin);
            } else if (mixin != null) {
                ensureMixinsIsMutable();
                this.mixins_.add(mixin);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMixins(int i, Mixin mixin) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, mixin);
            } else if (mixin != null) {
                ensureMixinsIsMutable();
                this.mixins_.add(i, mixin);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMixins(com.google.protobuf.Mixin.Builder builder) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMixinsIsMutable();
                this.mixins_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMixins(int i, com.google.protobuf.Mixin.Builder builder) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMixinsIsMutable();
                this.mixins_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMixins(Iterable<? extends Mixin> iterable) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMixinsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.mixins_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMixins() {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.mixins_ = Collections.emptyList();
                this.bitField0_ &= -33;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMixins(int i) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMixinsIsMutable();
                this.mixins_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Mixin.Builder getMixinsBuilder(int i) {
            return (com.google.protobuf.Mixin.Builder) getMixinsFieldBuilder().getBuilder(i);
        }

        public MixinOrBuilder getMixinsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MixinOrBuilder) this.mixins_.get(i);
            }
            return (MixinOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
            RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> repeatedFieldBuilderV3 = this.mixinsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.mixins_);
        }

        public com.google.protobuf.Mixin.Builder addMixinsBuilder() {
            return (com.google.protobuf.Mixin.Builder) getMixinsFieldBuilder().addBuilder(Mixin.getDefaultInstance());
        }

        public com.google.protobuf.Mixin.Builder addMixinsBuilder(int i) {
            return (com.google.protobuf.Mixin.Builder) getMixinsFieldBuilder().addBuilder(i, Mixin.getDefaultInstance());
        }

        public List<com.google.protobuf.Mixin.Builder> getMixinsBuilderList() {
            return getMixinsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Mixin, com.google.protobuf.Mixin.Builder, MixinOrBuilder> getMixinsFieldBuilder() {
            if (this.mixinsBuilder_ == null) {
                this.mixinsBuilder_ = new RepeatedFieldBuilderV3<>(this.mixins_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                this.mixins_ = null;
            }
            return this.mixinsBuilder_;
        }

        public int getSyntaxValue() {
            return this.syntax_;
        }

        public Builder setSyntaxValue(int i) {
            this.syntax_ = i;
            onChanged();
            return this;
        }

        public Syntax getSyntax() {
            Syntax valueOf = Syntax.valueOf(this.syntax_);
            return valueOf == null ? Syntax.UNRECOGNIZED : valueOf;
        }

        public Builder setSyntax(Syntax syntax) {
            if (syntax != null) {
                this.syntax_ = syntax.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSyntax() {
            this.syntax_ = 0;
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

    private Api(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Api() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.methods_ = Collections.emptyList();
        this.options_ = Collections.emptyList();
        this.version_ = "";
        this.mixins_ = Collections.emptyList();
        this.syntax_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Api(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.name_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.methods_ = new ArrayList();
                            z2 |= true;
                        }
                        this.methods_.add(codedInputStream.readMessage(Method.parser(), extensionRegistryLite));
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.options_ = new ArrayList();
                            z2 |= true;
                        }
                        this.options_.add(codedInputStream.readMessage(Option.parser(), extensionRegistryLite));
                    } else if (readTag == 34) {
                        this.version_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 42) {
                        com.google.protobuf.SourceContext.Builder builder = null;
                        if (this.sourceContext_ != null) {
                            builder = this.sourceContext_.toBuilder();
                        }
                        this.sourceContext_ = (SourceContext) codedInputStream.readMessage(SourceContext.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.sourceContext_);
                            this.sourceContext_ = builder.buildPartial();
                        }
                    } else if (readTag == 50) {
                        if (!(z2 & true)) {
                            this.mixins_ = new ArrayList();
                            z2 |= true;
                        }
                        this.mixins_.add(codedInputStream.readMessage(Mixin.parser(), extensionRegistryLite));
                    } else if (readTag == 56) {
                        this.syntax_ = codedInputStream.readEnum();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.methods_ = Collections.unmodifiableList(this.methods_);
                    }
                    if (z2 & true) {
                        this.options_ = Collections.unmodifiableList(this.options_);
                    }
                    if (z2 & true) {
                        this.mixins_ = Collections.unmodifiableList(this.mixins_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.methods_ = Collections.unmodifiableList(this.methods_);
            }
            if (z2 & true) {
                this.options_ = Collections.unmodifiableList(this.options_);
            }
            if (z2 & true) {
                this.mixins_ = Collections.unmodifiableList(this.mixins_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ApiProto.internal_static_google_protobuf_Api_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized(Api.class, Builder.class);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<Method> getMethodsList() {
        return this.methods_;
    }

    public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
        return this.methods_;
    }

    public int getMethodsCount() {
        return this.methods_.size();
    }

    public Method getMethods(int i) {
        return (Method) this.methods_.get(i);
    }

    public MethodOrBuilder getMethodsOrBuilder(int i) {
        return (MethodOrBuilder) this.methods_.get(i);
    }

    public List<Option> getOptionsList() {
        return this.options_;
    }

    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
        return this.options_;
    }

    public int getOptionsCount() {
        return this.options_.size();
    }

    public Option getOptions(int i) {
        return (Option) this.options_.get(i);
    }

    public OptionOrBuilder getOptionsOrBuilder(int i) {
        return (OptionOrBuilder) this.options_.get(i);
    }

    public String getVersion() {
        Object obj = this.version_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.version_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getVersionBytes() {
        Object obj = this.version_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.version_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasSourceContext() {
        return this.sourceContext_ != null;
    }

    public SourceContext getSourceContext() {
        SourceContext sourceContext = this.sourceContext_;
        return sourceContext == null ? SourceContext.getDefaultInstance() : sourceContext;
    }

    public SourceContextOrBuilder getSourceContextOrBuilder() {
        return getSourceContext();
    }

    public List<Mixin> getMixinsList() {
        return this.mixins_;
    }

    public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
        return this.mixins_;
    }

    public int getMixinsCount() {
        return this.mixins_.size();
    }

    public Mixin getMixins(int i) {
        return (Mixin) this.mixins_.get(i);
    }

    public MixinOrBuilder getMixinsOrBuilder(int i) {
        return (MixinOrBuilder) this.mixins_.get(i);
    }

    public int getSyntaxValue() {
        return this.syntax_;
    }

    public Syntax getSyntax() {
        Syntax valueOf = Syntax.valueOf(this.syntax_);
        return valueOf == null ? Syntax.UNRECOGNIZED : valueOf;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.methods_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.methods_.get(i));
        }
        for (int i2 = 0; i2 < this.options_.size(); i2++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.options_.get(i2));
        }
        if (!getVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.version_);
        }
        if (this.sourceContext_ != null) {
            codedOutputStream.writeMessage(5, getSourceContext());
        }
        for (int i3 = 0; i3 < this.mixins_.size(); i3++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.mixins_.get(i3));
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            codedOutputStream.writeEnum(7, this.syntax_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        for (int i2 = 0; i2 < this.methods_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.methods_.get(i2));
        }
        for (int i3 = 0; i3 < this.options_.size(); i3++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.options_.get(i3));
        }
        if (!getVersionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.version_);
        }
        if (this.sourceContext_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, getSourceContext());
        }
        for (int i4 = 0; i4 < this.mixins_.size(); i4++) {
            computeStringSize += CodedOutputStream.computeMessageSize(6, (MessageLite) this.mixins_.get(i4));
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(7, this.syntax_);
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
        if (!(obj instanceof Api)) {
            return super.equals(obj);
        }
        Api api = (Api) obj;
        boolean z2 = ((((getName().equals(api.getName())) && getMethodsList().equals(api.getMethodsList())) && getOptionsList().equals(api.getOptionsList())) && getVersion().equals(api.getVersion())) && hasSourceContext() == api.hasSourceContext();
        if (hasSourceContext()) {
            z2 = z2 && getSourceContext().equals(api.getSourceContext());
        }
        if (!((z2 && getMixinsList().equals(api.getMixinsList())) && this.syntax_ == api.syntax_) || !this.unknownFields.equals(api.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getMethodsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getMethodsList().hashCode();
        }
        if (getOptionsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOptionsList().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 4) * 53) + getVersion().hashCode();
        if (hasSourceContext()) {
            hashCode2 = (((hashCode2 * 37) + 5) * 53) + getSourceContext().hashCode();
        }
        if (getMixinsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 6) * 53) + getMixinsList().hashCode();
        }
        int hashCode3 = (((((hashCode2 * 37) + 7) * 53) + this.syntax_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static Api parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteBuffer);
    }

    public static Api parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Api parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteString);
    }

    public static Api parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Api parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(bArr);
    }

    public static Api parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Api) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Api parseFrom(InputStream inputStream) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Api parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Api parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Api) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Api parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Api) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Api parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Api parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Api) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Api api) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(api);
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

    public static Api getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Api> parser() {
        return PARSER;
    }

    public Parser<Api> getParserForType() {
        return PARSER;
    }

    public Api getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
