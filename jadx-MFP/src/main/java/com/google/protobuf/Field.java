package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Field extends GeneratedMessageV3 implements FieldOrBuilder {
    public static final int CARDINALITY_FIELD_NUMBER = 2;
    private static final Field DEFAULT_INSTANCE = new Field();
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 11;
    public static final int JSON_NAME_FIELD_NUMBER = 10;
    public static final int KIND_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 4;
    public static final int NUMBER_FIELD_NUMBER = 3;
    public static final int ONEOF_INDEX_FIELD_NUMBER = 7;
    public static final int OPTIONS_FIELD_NUMBER = 9;
    public static final int PACKED_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final Parser<Field> PARSER = new AbstractParser<Field>() {
        public Field parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Field(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPE_URL_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public int cardinality_;
    /* access modifiers changed from: private */
    public volatile Object defaultValue_;
    /* access modifiers changed from: private */
    public volatile Object jsonName_;
    /* access modifiers changed from: private */
    public int kind_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public int number_;
    /* access modifiers changed from: private */
    public int oneofIndex_;
    /* access modifiers changed from: private */
    public List<Option> options_;
    /* access modifiers changed from: private */
    public boolean packed_;
    /* access modifiers changed from: private */
    public volatile Object typeUrl_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements FieldOrBuilder {
        private int bitField0_;
        private int cardinality_;
        private Object defaultValue_;
        private Object jsonName_;
        private int kind_;
        private Object name_;
        private int number_;
        private int oneofIndex_;
        private RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private boolean packed_;
        private Object typeUrl_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_Field_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_Field_fieldAccessorTable.ensureFieldAccessorsInitialized(Field.class, Builder.class);
        }

        private Builder() {
            this.kind_ = 0;
            this.cardinality_ = 0;
            this.name_ = "";
            this.typeUrl_ = "";
            this.options_ = Collections.emptyList();
            this.jsonName_ = "";
            this.defaultValue_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.kind_ = 0;
            this.cardinality_ = 0;
            this.name_ = "";
            this.typeUrl_ = "";
            this.options_ = Collections.emptyList();
            this.jsonName_ = "";
            this.defaultValue_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getOptionsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.kind_ = 0;
            this.cardinality_ = 0;
            this.number_ = 0;
            this.name_ = "";
            this.typeUrl_ = "";
            this.oneofIndex_ = 0;
            this.packed_ = false;
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -129;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.jsonName_ = "";
            this.defaultValue_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return TypeProto.internal_static_google_protobuf_Field_descriptor;
        }

        public Field getDefaultInstanceForType() {
            return Field.getDefaultInstance();
        }

        public Field build() {
            Field buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Field buildPartial() {
            Field field = new Field((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            field.kind_ = this.kind_;
            field.cardinality_ = this.cardinality_;
            field.number_ = this.number_;
            field.name_ = this.name_;
            field.typeUrl_ = this.typeUrl_;
            field.oneofIndex_ = this.oneofIndex_;
            field.packed_ = this.packed_;
            RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 128) == 128) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -129;
                }
                field.options_ = this.options_;
            } else {
                field.options_ = repeatedFieldBuilderV3.build();
            }
            field.jsonName_ = this.jsonName_;
            field.defaultValue_ = this.defaultValue_;
            field.bitField0_ = 0;
            onBuilt();
            return field;
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
            if (message instanceof Field) {
                return mergeFrom((Field) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Field field) {
            if (field == Field.getDefaultInstance()) {
                return this;
            }
            if (field.kind_ != 0) {
                setKindValue(field.getKindValue());
            }
            if (field.cardinality_ != 0) {
                setCardinalityValue(field.getCardinalityValue());
            }
            if (field.getNumber() != 0) {
                setNumber(field.getNumber());
            }
            if (!field.getName().isEmpty()) {
                this.name_ = field.name_;
                onChanged();
            }
            if (!field.getTypeUrl().isEmpty()) {
                this.typeUrl_ = field.typeUrl_;
                onChanged();
            }
            if (field.getOneofIndex() != 0) {
                setOneofIndex(field.getOneofIndex());
            }
            if (field.getPacked()) {
                setPacked(field.getPacked());
            }
            if (this.optionsBuilder_ == null) {
                if (!field.options_.isEmpty()) {
                    if (this.options_.isEmpty()) {
                        this.options_ = field.options_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureOptionsIsMutable();
                        this.options_.addAll(field.options_);
                    }
                    onChanged();
                }
            } else if (!field.options_.isEmpty()) {
                if (this.optionsBuilder_.isEmpty()) {
                    this.optionsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Option, com.google.protobuf.Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = null;
                    this.optionsBuilder_ = null;
                    this.options_ = field.options_;
                    this.bitField0_ &= -129;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getOptionsFieldBuilder();
                    }
                    this.optionsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.optionsBuilder_.addAllMessages(field.options_);
                }
            }
            if (!field.getJsonName().isEmpty()) {
                this.jsonName_ = field.jsonName_;
                onChanged();
            }
            if (!field.getDefaultValue().isEmpty()) {
                this.defaultValue_ = field.defaultValue_;
                onChanged();
            }
            mergeUnknownFields(field.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.protobuf.Field.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Field.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.protobuf.Field r3 = (com.google.protobuf.Field) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.protobuf.Field r4 = (com.google.protobuf.Field) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Field.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Field$Builder");
        }

        public int getKindValue() {
            return this.kind_;
        }

        public Builder setKindValue(int i) {
            this.kind_ = i;
            onChanged();
            return this;
        }

        public Kind getKind() {
            Kind valueOf = Kind.valueOf(this.kind_);
            return valueOf == null ? Kind.UNRECOGNIZED : valueOf;
        }

        public Builder setKind(Kind kind) {
            if (kind != null) {
                this.kind_ = kind.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearKind() {
            this.kind_ = 0;
            onChanged();
            return this;
        }

        public int getCardinalityValue() {
            return this.cardinality_;
        }

        public Builder setCardinalityValue(int i) {
            this.cardinality_ = i;
            onChanged();
            return this;
        }

        public Cardinality getCardinality() {
            Cardinality valueOf = Cardinality.valueOf(this.cardinality_);
            return valueOf == null ? Cardinality.UNRECOGNIZED : valueOf;
        }

        public Builder setCardinality(Cardinality cardinality) {
            if (cardinality != null) {
                this.cardinality_ = cardinality.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearCardinality() {
            this.cardinality_ = 0;
            onChanged();
            return this;
        }

        public int getNumber() {
            return this.number_;
        }

        public Builder setNumber(int i) {
            this.number_ = i;
            onChanged();
            return this;
        }

        public Builder clearNumber() {
            this.number_ = 0;
            onChanged();
            return this;
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
            this.name_ = Field.getDefaultInstance().getName();
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

        public String getTypeUrl() {
            Object obj = this.typeUrl_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.typeUrl_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getTypeUrlBytes() {
            Object obj = this.typeUrl_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.typeUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setTypeUrl(String str) {
            if (str != null) {
                this.typeUrl_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearTypeUrl() {
            this.typeUrl_ = Field.getDefaultInstance().getTypeUrl();
            onChanged();
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.typeUrl_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public int getOneofIndex() {
            return this.oneofIndex_;
        }

        public Builder setOneofIndex(int i) {
            this.oneofIndex_ = i;
            onChanged();
            return this;
        }

        public Builder clearOneofIndex() {
            this.oneofIndex_ = 0;
            onChanged();
            return this;
        }

        public boolean getPacked() {
            return this.packed_;
        }

        public Builder setPacked(boolean z) {
            this.packed_ = z;
            onChanged();
            return this;
        }

        public Builder clearPacked() {
            this.packed_ = false;
            onChanged();
            return this;
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 128) != 128) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 128;
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
                this.bitField0_ &= -129;
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
                this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, (this.bitField0_ & 128) == 128, getParentForChildren(), isClean());
                this.options_ = null;
            }
            return this.optionsBuilder_;
        }

        public String getJsonName() {
            Object obj = this.jsonName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.jsonName_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getJsonNameBytes() {
            Object obj = this.jsonName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.jsonName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setJsonName(String str) {
            if (str != null) {
                this.jsonName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearJsonName() {
            this.jsonName_ = Field.getDefaultInstance().getJsonName();
            onChanged();
            return this;
        }

        public Builder setJsonNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.jsonName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDefaultValue() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.defaultValue_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDefaultValueBytes() {
            Object obj = this.defaultValue_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.defaultValue_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDefaultValue(String str) {
            if (str != null) {
                this.defaultValue_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDefaultValue() {
            this.defaultValue_ = Field.getDefaultInstance().getDefaultValue();
            onChanged();
            return this;
        }

        public Builder setDefaultValueBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.defaultValue_ = byteString;
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

    public enum Cardinality implements ProtocolMessageEnum {
        CARDINALITY_UNKNOWN(0),
        CARDINALITY_OPTIONAL(1),
        CARDINALITY_REQUIRED(2),
        CARDINALITY_REPEATED(3),
        UNRECOGNIZED(-1);
        
        public static final int CARDINALITY_OPTIONAL_VALUE = 1;
        public static final int CARDINALITY_REPEATED_VALUE = 3;
        public static final int CARDINALITY_REQUIRED_VALUE = 2;
        public static final int CARDINALITY_UNKNOWN_VALUE = 0;
        private static final Cardinality[] VALUES = null;
        private static final EnumLiteMap<Cardinality> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<Cardinality>() {
                public Cardinality findValueByNumber(int i) {
                    return Cardinality.forNumber(i);
                }
            };
            VALUES = values();
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Cardinality valueOf(int i) {
            return forNumber(i);
        }

        public static Cardinality forNumber(int i) {
            switch (i) {
                case 0:
                    return CARDINALITY_UNKNOWN;
                case 1:
                    return CARDINALITY_OPTIONAL;
                case 2:
                    return CARDINALITY_REQUIRED;
                case 3:
                    return CARDINALITY_REPEATED;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<Cardinality> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) Field.getDescriptor().getEnumTypes().get(1);
        }

        public static Cardinality valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private Cardinality(int i) {
            this.value = i;
        }
    }

    public enum Kind implements ProtocolMessageEnum {
        TYPE_UNKNOWN(0),
        TYPE_DOUBLE(1),
        TYPE_FLOAT(2),
        TYPE_INT64(3),
        TYPE_UINT64(4),
        TYPE_INT32(5),
        TYPE_FIXED64(6),
        TYPE_FIXED32(7),
        TYPE_BOOL(8),
        TYPE_STRING(9),
        TYPE_GROUP(10),
        TYPE_MESSAGE(11),
        TYPE_BYTES(12),
        TYPE_UINT32(13),
        TYPE_ENUM(14),
        TYPE_SFIXED32(15),
        TYPE_SFIXED64(16),
        TYPE_SINT32(17),
        TYPE_SINT64(18),
        UNRECOGNIZED(-1);
        
        public static final int TYPE_BOOL_VALUE = 8;
        public static final int TYPE_BYTES_VALUE = 12;
        public static final int TYPE_DOUBLE_VALUE = 1;
        public static final int TYPE_ENUM_VALUE = 14;
        public static final int TYPE_FIXED32_VALUE = 7;
        public static final int TYPE_FIXED64_VALUE = 6;
        public static final int TYPE_FLOAT_VALUE = 2;
        public static final int TYPE_GROUP_VALUE = 10;
        public static final int TYPE_INT32_VALUE = 5;
        public static final int TYPE_INT64_VALUE = 3;
        public static final int TYPE_MESSAGE_VALUE = 11;
        public static final int TYPE_SFIXED32_VALUE = 15;
        public static final int TYPE_SFIXED64_VALUE = 16;
        public static final int TYPE_SINT32_VALUE = 17;
        public static final int TYPE_SINT64_VALUE = 18;
        public static final int TYPE_STRING_VALUE = 9;
        public static final int TYPE_UINT32_VALUE = 13;
        public static final int TYPE_UINT64_VALUE = 4;
        public static final int TYPE_UNKNOWN_VALUE = 0;
        private static final Kind[] VALUES = null;
        private static final EnumLiteMap<Kind> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<Kind>() {
                public Kind findValueByNumber(int i) {
                    return Kind.forNumber(i);
                }
            };
            VALUES = values();
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Kind valueOf(int i) {
            return forNumber(i);
        }

        public static Kind forNumber(int i) {
            switch (i) {
                case 0:
                    return TYPE_UNKNOWN;
                case 1:
                    return TYPE_DOUBLE;
                case 2:
                    return TYPE_FLOAT;
                case 3:
                    return TYPE_INT64;
                case 4:
                    return TYPE_UINT64;
                case 5:
                    return TYPE_INT32;
                case 6:
                    return TYPE_FIXED64;
                case 7:
                    return TYPE_FIXED32;
                case 8:
                    return TYPE_BOOL;
                case 9:
                    return TYPE_STRING;
                case 10:
                    return TYPE_GROUP;
                case 11:
                    return TYPE_MESSAGE;
                case 12:
                    return TYPE_BYTES;
                case 13:
                    return TYPE_UINT32;
                case 14:
                    return TYPE_ENUM;
                case 15:
                    return TYPE_SFIXED32;
                case 16:
                    return TYPE_SFIXED64;
                case 17:
                    return TYPE_SINT32;
                case 18:
                    return TYPE_SINT64;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<Kind> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) Field.getDescriptor().getEnumTypes().get(0);
        }

        public static Kind valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private Kind(int i) {
            this.value = i;
        }
    }

    private Field(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Field() {
        this.memoizedIsInitialized = -1;
        this.kind_ = 0;
        this.cardinality_ = 0;
        this.number_ = 0;
        this.name_ = "";
        this.typeUrl_ = "";
        this.oneofIndex_ = 0;
        this.packed_ = false;
        this.options_ = Collections.emptyList();
        this.jsonName_ = "";
        this.defaultValue_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Field(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 8:
                            this.kind_ = codedInputStream.readEnum();
                            break;
                        case 16:
                            this.cardinality_ = codedInputStream.readEnum();
                            break;
                        case 24:
                            this.number_ = codedInputStream.readInt32();
                            break;
                        case 34:
                            this.name_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 50:
                            this.typeUrl_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 56:
                            this.oneofIndex_ = codedInputStream.readInt32();
                            break;
                        case 64:
                            this.packed_ = codedInputStream.readBool();
                            break;
                        case 74:
                            if (!(z2 & true)) {
                                this.options_ = new ArrayList();
                                z2 |= true;
                            }
                            this.options_.add(codedInputStream.readMessage(Option.parser(), extensionRegistryLite));
                            break;
                        case 82:
                            this.jsonName_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 90:
                            this.defaultValue_ = codedInputStream.readStringRequireUtf8();
                            break;
                        default:
                            if (parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.options_ = Collections.unmodifiableList(this.options_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
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
        return TypeProto.internal_static_google_protobuf_Field_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_Field_fieldAccessorTable.ensureFieldAccessorsInitialized(Field.class, Builder.class);
    }

    public int getKindValue() {
        return this.kind_;
    }

    public Kind getKind() {
        Kind valueOf = Kind.valueOf(this.kind_);
        return valueOf == null ? Kind.UNRECOGNIZED : valueOf;
    }

    public int getCardinalityValue() {
        return this.cardinality_;
    }

    public Cardinality getCardinality() {
        Cardinality valueOf = Cardinality.valueOf(this.cardinality_);
        return valueOf == null ? Cardinality.UNRECOGNIZED : valueOf;
    }

    public int getNumber() {
        return this.number_;
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

    public String getTypeUrl() {
        Object obj = this.typeUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.typeUrl_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getTypeUrlBytes() {
        Object obj = this.typeUrl_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.typeUrl_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public int getOneofIndex() {
        return this.oneofIndex_;
    }

    public boolean getPacked() {
        return this.packed_;
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

    public String getJsonName() {
        Object obj = this.jsonName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.jsonName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getJsonNameBytes() {
        Object obj = this.jsonName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.jsonName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getDefaultValue() {
        Object obj = this.defaultValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.defaultValue_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDefaultValueBytes() {
        Object obj = this.defaultValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.defaultValue_ = copyFromUtf8;
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
        if (this.kind_ != Kind.TYPE_UNKNOWN.getNumber()) {
            codedOutputStream.writeEnum(1, this.kind_);
        }
        if (this.cardinality_ != Cardinality.CARDINALITY_UNKNOWN.getNumber()) {
            codedOutputStream.writeEnum(2, this.cardinality_);
        }
        int i = this.number_;
        if (i != 0) {
            codedOutputStream.writeInt32(3, i);
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.name_);
        }
        if (!getTypeUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.typeUrl_);
        }
        int i2 = this.oneofIndex_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(7, i2);
        }
        boolean z = this.packed_;
        if (z) {
            codedOutputStream.writeBool(8, z);
        }
        for (int i3 = 0; i3 < this.options_.size(); i3++) {
            codedOutputStream.writeMessage(9, (MessageLite) this.options_.get(i3));
        }
        if (!getJsonNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.jsonName_);
        }
        if (!getDefaultValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.defaultValue_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.kind_ != Kind.TYPE_UNKNOWN.getNumber() ? CodedOutputStream.computeEnumSize(1, this.kind_) + 0 : 0;
        if (this.cardinality_ != Cardinality.CARDINALITY_UNKNOWN.getNumber()) {
            computeEnumSize += CodedOutputStream.computeEnumSize(2, this.cardinality_);
        }
        int i2 = this.number_;
        if (i2 != 0) {
            computeEnumSize += CodedOutputStream.computeInt32Size(3, i2);
        }
        if (!getNameBytes().isEmpty()) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(4, this.name_);
        }
        if (!getTypeUrlBytes().isEmpty()) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(6, this.typeUrl_);
        }
        int i3 = this.oneofIndex_;
        if (i3 != 0) {
            computeEnumSize += CodedOutputStream.computeInt32Size(7, i3);
        }
        boolean z = this.packed_;
        if (z) {
            computeEnumSize += CodedOutputStream.computeBoolSize(8, z);
        }
        for (int i4 = 0; i4 < this.options_.size(); i4++) {
            computeEnumSize += CodedOutputStream.computeMessageSize(9, (MessageLite) this.options_.get(i4));
        }
        if (!getJsonNameBytes().isEmpty()) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(10, this.jsonName_);
        }
        if (!getDefaultValueBytes().isEmpty()) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(11, this.defaultValue_);
        }
        int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return super.equals(obj);
        }
        Field field = (Field) obj;
        if (!((((((((((this.kind_ == field.kind_) && this.cardinality_ == field.cardinality_) && getNumber() == field.getNumber()) && getName().equals(field.getName())) && getTypeUrl().equals(field.getTypeUrl())) && getOneofIndex() == field.getOneofIndex()) && getPacked() == field.getPacked()) && getOptionsList().equals(field.getOptionsList())) && getJsonName().equals(field.getJsonName())) && getDefaultValue().equals(field.getDefaultValue())) || !this.unknownFields.equals(field.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.kind_) * 37) + 2) * 53) + this.cardinality_) * 37) + 3) * 53) + getNumber()) * 37) + 4) * 53) + getName().hashCode()) * 37) + 6) * 53) + getTypeUrl().hashCode()) * 37) + 7) * 53) + getOneofIndex()) * 37) + 8) * 53) + Internal.hashBoolean(getPacked());
        if (getOptionsCount() > 0) {
            hashCode = (((hashCode * 37) + 9) * 53) + getOptionsList().hashCode();
        }
        int hashCode2 = (((((((((hashCode * 37) + 10) * 53) + getJsonName().hashCode()) * 37) + 11) * 53) + getDefaultValue().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Field parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Field) PARSER.parseFrom(byteBuffer);
    }

    public static Field parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Field) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Field parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Field) PARSER.parseFrom(byteString);
    }

    public static Field parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Field) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Field parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Field) PARSER.parseFrom(bArr);
    }

    public static Field parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Field) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Field parseFrom(InputStream inputStream) throws IOException {
        return (Field) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Field parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Field) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Field parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Field) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Field parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Field) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Field parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Field) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Field parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Field) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Field field) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(field);
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

    public static Field getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Field> parser() {
        return PARSER;
    }

    public Parser<Field> getParserForType() {
        return PARSER;
    }

    public Field getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
