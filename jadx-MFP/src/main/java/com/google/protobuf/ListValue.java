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

public final class ListValue extends GeneratedMessageV3 implements ListValueOrBuilder {
    private static final ListValue DEFAULT_INSTANCE = new ListValue();
    /* access modifiers changed from: private */
    public static final Parser<ListValue> PARSER = new AbstractParser<ListValue>() {
        public ListValue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListValue(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<Value> values_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ListValueOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> valuesBuilder_;
        private List<Value> values_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_ListValue_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
        }

        private Builder() {
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getValuesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_ListValue_descriptor;
        }

        public ListValue getDefaultInstanceForType() {
            return ListValue.getDefaultInstance();
        }

        public ListValue build() {
            ListValue buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ListValue buildPartial() {
            ListValue listValue = new ListValue((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                    this.bitField0_ &= -2;
                }
                listValue.values_ = this.values_;
            } else {
                listValue.values_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return listValue;
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
            if (message instanceof ListValue) {
                return mergeFrom((ListValue) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListValue listValue) {
            if (listValue == ListValue.getDefaultInstance()) {
                return this;
            }
            if (this.valuesBuilder_ == null) {
                if (!listValue.values_.isEmpty()) {
                    if (this.values_.isEmpty()) {
                        this.values_ = listValue.values_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureValuesIsMutable();
                        this.values_.addAll(listValue.values_);
                    }
                    onChanged();
                }
            } else if (!listValue.values_.isEmpty()) {
                if (this.valuesBuilder_.isEmpty()) {
                    this.valuesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.valuesBuilder_ = null;
                    this.values_ = listValue.values_;
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getValuesFieldBuilder();
                    }
                    this.valuesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.valuesBuilder_.addAllMessages(listValue.values_);
                }
            }
            mergeUnknownFields(listValue.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.protobuf.ListValue.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.ListValue.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.protobuf.ListValue r3 = (com.google.protobuf.ListValue) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.protobuf.ListValue r4 = (com.google.protobuf.ListValue) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ListValue.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.ListValue$Builder");
        }

        private void ensureValuesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.values_ = new ArrayList(this.values_);
                this.bitField0_ |= 1;
            }
        }

        public List<Value> getValuesList() {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.values_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getValuesCount() {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.values_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Value getValues(int i) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Value) this.values_.get(i);
            }
            return (Value) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setValues(int i, Value value) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, value);
            } else if (value != null) {
                ensureValuesIsMutable();
                this.values_.set(i, value);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setValues(int i, com.google.protobuf.Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addValues(Value value) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(value);
            } else if (value != null) {
                ensureValuesIsMutable();
                this.values_.add(value);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addValues(int i, Value value) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, value);
            } else if (value != null) {
                ensureValuesIsMutable();
                this.values_.add(i, value);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addValues(com.google.protobuf.Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addValues(int i, com.google.protobuf.Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllValues(Iterable<? extends Value> iterable) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.values_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearValues() {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeValues(int i) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Value.Builder getValuesBuilder(int i) {
            return (com.google.protobuf.Value.Builder) getValuesFieldBuilder().getBuilder(i);
        }

        public ValueOrBuilder getValuesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (ValueOrBuilder) this.values_.get(i);
            }
            return (ValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
            RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.values_);
        }

        public com.google.protobuf.Value.Builder addValuesBuilder() {
            return (com.google.protobuf.Value.Builder) getValuesFieldBuilder().addBuilder(Value.getDefaultInstance());
        }

        public com.google.protobuf.Value.Builder addValuesBuilder(int i) {
            return (com.google.protobuf.Value.Builder) getValuesFieldBuilder().addBuilder(i, Value.getDefaultInstance());
        }

        public List<com.google.protobuf.Value.Builder> getValuesBuilderList() {
            return getValuesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Value, com.google.protobuf.Value.Builder, ValueOrBuilder> getValuesFieldBuilder() {
            if (this.valuesBuilder_ == null) {
                List<Value> list = this.values_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.valuesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.values_ = null;
            }
            return this.valuesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private ListValue(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ListValue() {
        this.memoizedIsInitialized = -1;
        this.values_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.values_ = new ArrayList();
                            z2 |= true;
                        }
                        this.values_.add(codedInputStream.readMessage(Value.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.values_ = Collections.unmodifiableList(this.values_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.values_ = Collections.unmodifiableList(this.values_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_ListValue_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
    }

    public List<Value> getValuesList() {
        return this.values_;
    }

    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    public int getValuesCount() {
        return this.values_.size();
    }

    public Value getValues(int i) {
        return (Value) this.values_.get(i);
    }

    public ValueOrBuilder getValuesOrBuilder(int i) {
        return (ValueOrBuilder) this.values_.get(i);
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
        for (int i = 0; i < this.values_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.values_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.values_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.values_.get(i3));
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
        if (!(obj instanceof ListValue)) {
            return super.equals(obj);
        }
        ListValue listValue = (ListValue) obj;
        if (!(getValuesList().equals(listValue.getValuesList())) || !this.unknownFields.equals(listValue.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getValuesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getValuesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListValue) PARSER.parseFrom(byteBuffer);
    }

    public static ListValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListValue) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListValue) PARSER.parseFrom(byteString);
    }

    public static ListValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListValue) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListValue) PARSER.parseFrom(bArr);
    }

    public static ListValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListValue) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListValue parseFrom(InputStream inputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListValue listValue) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listValue);
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

    public static ListValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListValue> parser() {
        return PARSER;
    }

    public Parser<ListValue> getParserForType() {
        return PARSER;
    }

    public ListValue getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
