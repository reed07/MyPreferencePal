package com.google.type;

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
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Money extends GeneratedMessageV3 implements MoneyOrBuilder {
    public static final int CURRENCY_CODE_FIELD_NUMBER = 1;
    private static final Money DEFAULT_INSTANCE = new Money();
    public static final int NANOS_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<Money> PARSER = new AbstractParser<Money>() {
        public Money parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Money(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int UNITS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object currencyCode_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public int nanos_;
    /* access modifiers changed from: private */
    public long units_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MoneyOrBuilder {
        private Object currencyCode_;
        private int nanos_;
        private long units_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return MoneyProto.internal_static_google_type_Money_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MoneyProto.internal_static_google_type_Money_fieldAccessorTable.ensureFieldAccessorsInitialized(Money.class, Builder.class);
        }

        private Builder() {
            this.currencyCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.currencyCode_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Money.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.currencyCode_ = "";
            this.units_ = 0;
            this.nanos_ = 0;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return MoneyProto.internal_static_google_type_Money_descriptor;
        }

        public Money getDefaultInstanceForType() {
            return Money.getDefaultInstance();
        }

        public Money build() {
            Money buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Money buildPartial() {
            Money money = new Money((com.google.protobuf.GeneratedMessageV3.Builder) this);
            money.currencyCode_ = this.currencyCode_;
            money.units_ = this.units_;
            money.nanos_ = this.nanos_;
            onBuilt();
            return money;
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
            if (message instanceof Money) {
                return mergeFrom((Money) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Money money) {
            if (money == Money.getDefaultInstance()) {
                return this;
            }
            if (!money.getCurrencyCode().isEmpty()) {
                this.currencyCode_ = money.currencyCode_;
                onChanged();
            }
            if (money.getUnits() != 0) {
                setUnits(money.getUnits());
            }
            if (money.getNanos() != 0) {
                setNanos(money.getNanos());
            }
            mergeUnknownFields(money.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.type.Money.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Money.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.type.Money r3 = (com.google.type.Money) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.type.Money r4 = (com.google.type.Money) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Money.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Money$Builder");
        }

        public String getCurrencyCode() {
            Object obj = this.currencyCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currencyCode_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getCurrencyCodeBytes() {
            Object obj = this.currencyCode_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.currencyCode_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setCurrencyCode(String str) {
            if (str != null) {
                this.currencyCode_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearCurrencyCode() {
            this.currencyCode_ = Money.getDefaultInstance().getCurrencyCode();
            onChanged();
            return this;
        }

        public Builder setCurrencyCodeBytes(ByteString byteString) {
            if (byteString != null) {
                Money.checkByteStringIsUtf8(byteString);
                this.currencyCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public long getUnits() {
            return this.units_;
        }

        public Builder setUnits(long j) {
            this.units_ = j;
            onChanged();
            return this;
        }

        public Builder clearUnits() {
            this.units_ = 0;
            onChanged();
            return this;
        }

        public int getNanos() {
            return this.nanos_;
        }

        public Builder setNanos(int i) {
            this.nanos_ = i;
            onChanged();
            return this;
        }

        public Builder clearNanos() {
            this.nanos_ = 0;
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

    private Money(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Money() {
        this.memoizedIsInitialized = -1;
        this.currencyCode_ = "";
        this.units_ = 0;
        this.nanos_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Money(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 10) {
                        this.currencyCode_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 16) {
                        this.units_ = codedInputStream.readInt64();
                    } else if (readTag == 24) {
                        this.nanos_ = codedInputStream.readInt32();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return MoneyProto.internal_static_google_type_Money_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return MoneyProto.internal_static_google_type_Money_fieldAccessorTable.ensureFieldAccessorsInitialized(Money.class, Builder.class);
    }

    public String getCurrencyCode() {
        Object obj = this.currencyCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.currencyCode_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getCurrencyCodeBytes() {
        Object obj = this.currencyCode_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.currencyCode_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public long getUnits() {
        return this.units_;
    }

    public int getNanos() {
        return this.nanos_;
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
        if (!getCurrencyCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.currencyCode_);
        }
        long j = this.units_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        int i = this.nanos_;
        if (i != 0) {
            codedOutputStream.writeInt32(3, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getCurrencyCodeBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.currencyCode_);
        }
        long j = this.units_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(2, j);
        }
        int i3 = this.nanos_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeInt32Size(3, i3);
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
        if (!(obj instanceof Money)) {
            return super.equals(obj);
        }
        Money money = (Money) obj;
        if (!(((getCurrencyCode().equals(money.getCurrencyCode())) && (getUnits() > money.getUnits() ? 1 : (getUnits() == money.getUnits() ? 0 : -1)) == 0) && getNanos() == money.getNanos()) || !this.unknownFields.equals(money.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrencyCode().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getUnits())) * 37) + 3) * 53) + getNanos()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Money parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteBuffer);
    }

    public static Money parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Money parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteString);
    }

    public static Money parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Money parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(bArr);
    }

    public static Money parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Money) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Money parseFrom(InputStream inputStream) throws IOException {
        return (Money) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Money parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Money) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Money parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Money) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Money parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Money) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Money parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Money) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Money parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Money) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Money money) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(money);
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

    public static Money getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Money> parser() {
        return PARSER;
    }

    public Parser<Money> getParserForType() {
        return PARSER;
    }

    public Money getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
