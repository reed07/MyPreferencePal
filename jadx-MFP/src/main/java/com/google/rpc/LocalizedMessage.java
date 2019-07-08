package com.google.rpc;

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
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LocalizedMessage extends GeneratedMessageV3 implements LocalizedMessageOrBuilder {
    private static final LocalizedMessage DEFAULT_INSTANCE = new LocalizedMessage();
    public static final int LOCALE_FIELD_NUMBER = 1;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<LocalizedMessage> PARSER = new AbstractParser<LocalizedMessage>() {
        public LocalizedMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LocalizedMessage(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object locale_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object message_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LocalizedMessageOrBuilder {
        private Object locale_;
        private Object message_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(LocalizedMessage.class, Builder.class);
        }

        private Builder() {
            this.locale_ = "";
            this.message_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.locale_ = "";
            this.message_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            LocalizedMessage.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.locale_ = "";
            this.message_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_descriptor;
        }

        public LocalizedMessage getDefaultInstanceForType() {
            return LocalizedMessage.getDefaultInstance();
        }

        public LocalizedMessage build() {
            LocalizedMessage buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public LocalizedMessage buildPartial() {
            LocalizedMessage localizedMessage = new LocalizedMessage((com.google.protobuf.GeneratedMessageV3.Builder) this);
            localizedMessage.locale_ = this.locale_;
            localizedMessage.message_ = this.message_;
            onBuilt();
            return localizedMessage;
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
            if (message instanceof LocalizedMessage) {
                return mergeFrom((LocalizedMessage) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LocalizedMessage localizedMessage) {
            if (localizedMessage == LocalizedMessage.getDefaultInstance()) {
                return this;
            }
            if (!localizedMessage.getLocale().isEmpty()) {
                this.locale_ = localizedMessage.locale_;
                onChanged();
            }
            if (!localizedMessage.getMessage().isEmpty()) {
                this.message_ = localizedMessage.message_;
                onChanged();
            }
            mergeUnknownFields(localizedMessage.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.LocalizedMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.LocalizedMessage.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.LocalizedMessage r3 = (com.google.rpc.LocalizedMessage) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.LocalizedMessage r4 = (com.google.rpc.LocalizedMessage) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.LocalizedMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.LocalizedMessage$Builder");
        }

        public String getLocale() {
            Object obj = this.locale_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.locale_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getLocaleBytes() {
            Object obj = this.locale_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.locale_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setLocale(String str) {
            if (str != null) {
                this.locale_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearLocale() {
            this.locale_ = LocalizedMessage.getDefaultInstance().getLocale();
            onChanged();
            return this;
        }

        public Builder setLocaleBytes(ByteString byteString) {
            if (byteString != null) {
                LocalizedMessage.checkByteStringIsUtf8(byteString);
                this.locale_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getMessage() {
            Object obj = this.message_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.message_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getMessageBytes() {
            Object obj = this.message_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.message_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setMessage(String str) {
            if (str != null) {
                this.message_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearMessage() {
            this.message_ = LocalizedMessage.getDefaultInstance().getMessage();
            onChanged();
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            if (byteString != null) {
                LocalizedMessage.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
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

    private LocalizedMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private LocalizedMessage() {
        this.memoizedIsInitialized = -1;
        this.locale_ = "";
        this.message_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LocalizedMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.locale_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.message_ = codedInputStream.readStringRequireUtf8();
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
        return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_LocalizedMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(LocalizedMessage.class, Builder.class);
    }

    public String getLocale() {
        Object obj = this.locale_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.locale_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getLocaleBytes() {
        Object obj = this.locale_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.locale_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getMessage() {
        Object obj = this.message_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.message_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getMessageBytes() {
        Object obj = this.message_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.message_ = copyFromUtf8;
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
        if (!getLocaleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.locale_);
        }
        if (!getMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getLocaleBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.locale_);
        }
        if (!getMessageBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.message_);
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
        if (!(obj instanceof LocalizedMessage)) {
            return super.equals(obj);
        }
        LocalizedMessage localizedMessage = (LocalizedMessage) obj;
        if (!((getLocale().equals(localizedMessage.getLocale())) && getMessage().equals(localizedMessage.getMessage())) || !this.unknownFields.equals(localizedMessage.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLocale().hashCode()) * 37) + 2) * 53) + getMessage().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static LocalizedMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteBuffer);
    }

    public static LocalizedMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteString);
    }

    public static LocalizedMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(bArr);
    }

    public static LocalizedMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocalizedMessage) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(InputStream inputStream) throws IOException {
        return (LocalizedMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LocalizedMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LocalizedMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LocalizedMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (LocalizedMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LocalizedMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LocalizedMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (LocalizedMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LocalizedMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LocalizedMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LocalizedMessage localizedMessage) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(localizedMessage);
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

    public static LocalizedMessage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LocalizedMessage> parser() {
        return PARSER;
    }

    public Parser<LocalizedMessage> getParserForType() {
        return PARSER;
    }

    public LocalizedMessage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
