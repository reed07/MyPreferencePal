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

public final class Type extends GeneratedMessageV3 implements TypeOrBuilder {
    private static final Type DEFAULT_INSTANCE = new Type();
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ONEOFS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Parser<Type> PARSER = new AbstractParser<Type>() {
        public Type parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Type(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public List<Field> fields_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public LazyStringList oneofs_;
    /* access modifiers changed from: private */
    public List<Option> options_;
    /* access modifiers changed from: private */
    public SourceContext sourceContext_;
    /* access modifiers changed from: private */
    public int syntax_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements TypeOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> fieldsBuilder_;
        private List<Field> fields_;
        private Object name_;
        private LazyStringList oneofs_;
        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
        private SourceContext sourceContext_;
        private int syntax_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_Type_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.fields_ = Collections.emptyList();
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.fields_ = Collections.emptyList();
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getFieldsFieldBuilder();
                getOptionsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV32 = this.optionsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            if (this.sourceContextBuilder_ == null) {
                this.sourceContext_ = null;
            } else {
                this.sourceContext_ = null;
                this.sourceContextBuilder_ = null;
            }
            this.syntax_ = 0;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return TypeProto.internal_static_google_protobuf_Type_descriptor;
        }

        public Type getDefaultInstanceForType() {
            return Type.getDefaultInstance();
        }

        public Type build() {
            Type buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Type buildPartial() {
            Type type = new Type((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            type.name_ = this.name_;
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.fields_ = Collections.unmodifiableList(this.fields_);
                    this.bitField0_ &= -3;
                }
                type.fields_ = this.fields_;
            } else {
                type.fields_ = repeatedFieldBuilderV3.build();
            }
            if ((this.bitField0_ & 4) == 4) {
                this.oneofs_ = this.oneofs_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            type.oneofs_ = this.oneofs_;
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV32 = this.optionsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -9;
                }
                type.options_ = this.options_;
            } else {
                type.options_ = repeatedFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<SourceContext, com.google.protobuf.SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                type.sourceContext_ = this.sourceContext_;
            } else {
                type.sourceContext_ = (SourceContext) singleFieldBuilderV3.build();
            }
            type.syntax_ = this.syntax_;
            type.bitField0_ = 0;
            onBuilt();
            return type;
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
            if (message instanceof Type) {
                return mergeFrom((Type) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Type type) {
            if (type == Type.getDefaultInstance()) {
                return this;
            }
            if (!type.getName().isEmpty()) {
                this.name_ = type.name_;
                onChanged();
            }
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.fieldsBuilder_ == null) {
                if (!type.fields_.isEmpty()) {
                    if (this.fields_.isEmpty()) {
                        this.fields_ = type.fields_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureFieldsIsMutable();
                        this.fields_.addAll(type.fields_);
                    }
                    onChanged();
                }
            } else if (!type.fields_.isEmpty()) {
                if (this.fieldsBuilder_.isEmpty()) {
                    this.fieldsBuilder_.dispose();
                    this.fieldsBuilder_ = null;
                    this.fields_ = type.fields_;
                    this.bitField0_ &= -3;
                    this.fieldsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getFieldsFieldBuilder() : null;
                } else {
                    this.fieldsBuilder_.addAllMessages(type.fields_);
                }
            }
            if (!type.oneofs_.isEmpty()) {
                if (this.oneofs_.isEmpty()) {
                    this.oneofs_ = type.oneofs_;
                    this.bitField0_ &= -5;
                } else {
                    ensureOneofsIsMutable();
                    this.oneofs_.addAll(type.oneofs_);
                }
                onChanged();
            }
            if (this.optionsBuilder_ == null) {
                if (!type.options_.isEmpty()) {
                    if (this.options_.isEmpty()) {
                        this.options_ = type.options_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureOptionsIsMutable();
                        this.options_.addAll(type.options_);
                    }
                    onChanged();
                }
            } else if (!type.options_.isEmpty()) {
                if (this.optionsBuilder_.isEmpty()) {
                    this.optionsBuilder_.dispose();
                    this.optionsBuilder_ = null;
                    this.options_ = type.options_;
                    this.bitField0_ &= -9;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getOptionsFieldBuilder();
                    }
                    this.optionsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.optionsBuilder_.addAllMessages(type.options_);
                }
            }
            if (type.hasSourceContext()) {
                mergeSourceContext(type.getSourceContext());
            }
            if (type.syntax_ != 0) {
                setSyntaxValue(type.getSyntaxValue());
            }
            mergeUnknownFields(type.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.protobuf.Type.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Type.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.protobuf.Type r3 = (com.google.protobuf.Type) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.protobuf.Type r4 = (com.google.protobuf.Type) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Type.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Type$Builder");
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
            this.name_ = Type.getDefaultInstance().getName();
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

        private void ensureFieldsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.fields_ = new ArrayList(this.fields_);
                this.bitField0_ |= 2;
            }
        }

        public List<Field> getFieldsList() {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.fields_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getFieldsCount() {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fields_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Field getFields(int i) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Field) this.fields_.get(i);
            }
            return (Field) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFields(int i, Field field) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, field);
            } else if (field != null) {
                ensureFieldsIsMutable();
                this.fields_.set(i, field);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setFields(int i, com.google.protobuf.Field.Builder builder) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addFields(Field field) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(field);
            } else if (field != null) {
                ensureFieldsIsMutable();
                this.fields_.add(field);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addFields(int i, Field field) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, field);
            } else if (field != null) {
                ensureFieldsIsMutable();
                this.fields_.add(i, field);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addFields(com.google.protobuf.Field.Builder builder) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addFields(int i, com.google.protobuf.Field.Builder builder) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllFields(Iterable<? extends Field> iterable) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.fields_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFields() {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFields(int i) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Field.Builder getFieldsBuilder(int i) {
            return (com.google.protobuf.Field.Builder) getFieldsFieldBuilder().getBuilder(i);
        }

        public FieldOrBuilder getFieldsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (FieldOrBuilder) this.fields_.get(i);
            }
            return (FieldOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
            RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fields_);
        }

        public com.google.protobuf.Field.Builder addFieldsBuilder() {
            return (com.google.protobuf.Field.Builder) getFieldsFieldBuilder().addBuilder(Field.getDefaultInstance());
        }

        public com.google.protobuf.Field.Builder addFieldsBuilder(int i) {
            return (com.google.protobuf.Field.Builder) getFieldsFieldBuilder().addBuilder(i, Field.getDefaultInstance());
        }

        public List<com.google.protobuf.Field.Builder> getFieldsBuilderList() {
            return getFieldsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Field, com.google.protobuf.Field.Builder, FieldOrBuilder> getFieldsFieldBuilder() {
            if (this.fieldsBuilder_ == null) {
                this.fieldsBuilder_ = new RepeatedFieldBuilderV3<>(this.fields_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.fields_ = null;
            }
            return this.fieldsBuilder_;
        }

        private void ensureOneofsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.oneofs_ = new LazyStringArrayList(this.oneofs_);
                this.bitField0_ |= 4;
            }
        }

        public ProtocolStringList getOneofsList() {
            return this.oneofs_.getUnmodifiableView();
        }

        public int getOneofsCount() {
            return this.oneofs_.size();
        }

        public String getOneofs(int i) {
            return (String) this.oneofs_.get(i);
        }

        public ByteString getOneofsBytes(int i) {
            return this.oneofs_.getByteString(i);
        }

        public Builder setOneofs(int i, String str) {
            if (str != null) {
                ensureOneofsIsMutable();
                this.oneofs_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addOneofs(String str) {
            if (str != null) {
                ensureOneofsIsMutable();
                this.oneofs_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllOneofs(Iterable<String> iterable) {
            ensureOneofsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.oneofs_);
            onChanged();
            return this;
        }

        public Builder clearOneofs() {
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addOneofsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureOneofsIsMutable();
                this.oneofs_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 8;
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
                this.bitField0_ &= -9;
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
                this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                this.options_ = null;
            }
            return this.optionsBuilder_;
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

    private Type(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Type() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.fields_ = Collections.emptyList();
        this.oneofs_ = LazyStringArrayList.EMPTY;
        this.options_ = Collections.emptyList();
        this.syntax_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Type(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.fields_ = new ArrayList();
                            z2 |= true;
                        }
                        this.fields_.add(codedInputStream.readMessage(Field.parser(), extensionRegistryLite));
                    } else if (readTag == 26) {
                        String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                        if (!(z2 & true)) {
                            this.oneofs_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.oneofs_.add(readStringRequireUtf8);
                    } else if (readTag == 34) {
                        if (!(z2 & true)) {
                            this.options_ = new ArrayList();
                            z2 |= true;
                        }
                        this.options_.add(codedInputStream.readMessage(Option.parser(), extensionRegistryLite));
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
                    } else if (readTag == 48) {
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
                        this.fields_ = Collections.unmodifiableList(this.fields_);
                    }
                    if (z2 & true) {
                        this.oneofs_ = this.oneofs_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.options_ = Collections.unmodifiableList(this.options_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.fields_ = Collections.unmodifiableList(this.fields_);
            }
            if (z2 & true) {
                this.oneofs_ = this.oneofs_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.options_ = Collections.unmodifiableList(this.options_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return TypeProto.internal_static_google_protobuf_Type_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
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

    public List<Field> getFieldsList() {
        return this.fields_;
    }

    public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
        return this.fields_;
    }

    public int getFieldsCount() {
        return this.fields_.size();
    }

    public Field getFields(int i) {
        return (Field) this.fields_.get(i);
    }

    public FieldOrBuilder getFieldsOrBuilder(int i) {
        return (FieldOrBuilder) this.fields_.get(i);
    }

    public ProtocolStringList getOneofsList() {
        return this.oneofs_;
    }

    public int getOneofsCount() {
        return this.oneofs_.size();
    }

    public String getOneofs(int i) {
        return (String) this.oneofs_.get(i);
    }

    public ByteString getOneofsBytes(int i) {
        return this.oneofs_.getByteString(i);
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
        for (int i = 0; i < this.fields_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.fields_.get(i));
        }
        for (int i2 = 0; i2 < this.oneofs_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.oneofs_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.options_.size(); i3++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.options_.get(i3));
        }
        if (this.sourceContext_ != null) {
            codedOutputStream.writeMessage(5, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            codedOutputStream.writeEnum(6, this.syntax_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        for (int i2 = 0; i2 < this.fields_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.fields_.get(i2));
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.oneofs_.size(); i4++) {
            i3 += computeStringSizeNoTag(this.oneofs_.getRaw(i4));
        }
        int size = computeStringSize + i3 + (getOneofsList().size() * 1);
        for (int i5 = 0; i5 < this.options_.size(); i5++) {
            size += CodedOutputStream.computeMessageSize(4, (MessageLite) this.options_.get(i5));
        }
        if (this.sourceContext_ != null) {
            size += CodedOutputStream.computeMessageSize(5, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            size += CodedOutputStream.computeEnumSize(6, this.syntax_);
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
        if (!(obj instanceof Type)) {
            return super.equals(obj);
        }
        Type type = (Type) obj;
        boolean z2 = ((((getName().equals(type.getName())) && getFieldsList().equals(type.getFieldsList())) && getOneofsList().equals(type.getOneofsList())) && getOptionsList().equals(type.getOptionsList())) && hasSourceContext() == type.hasSourceContext();
        if (hasSourceContext()) {
            z2 = z2 && getSourceContext().equals(type.getSourceContext());
        }
        if (!(z2 && this.syntax_ == type.syntax_) || !this.unknownFields.equals(type.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getFieldsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getFieldsList().hashCode();
        }
        if (getOneofsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getOneofsList().hashCode();
        }
        if (getOptionsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getOptionsList().hashCode();
        }
        if (hasSourceContext()) {
            hashCode = (((hashCode * 37) + 5) * 53) + getSourceContext().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 6) * 53) + this.syntax_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Type parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteBuffer);
    }

    public static Type parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Type parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteString);
    }

    public static Type parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Type parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(bArr);
    }

    public static Type parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Type) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Type parseFrom(InputStream inputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Type parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Type parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Type parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Type parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Type parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Type type) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(type);
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

    public static Type getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Type> parser() {
        return PARSER;
    }

    public Parser<Type> getParserForType() {
        return PARSER;
    }

    public Type getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
