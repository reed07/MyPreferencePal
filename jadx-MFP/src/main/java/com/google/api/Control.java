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
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Control extends GeneratedMessageV3 implements ControlOrBuilder {
    private static final Control DEFAULT_INSTANCE = new Control();
    public static final int ENVIRONMENT_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Control> PARSER = new AbstractParser<Control>() {
        public Control parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Control(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object environment_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ControlOrBuilder {
        private Object environment_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ControlProto.internal_static_google_api_Control_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ControlProto.internal_static_google_api_Control_fieldAccessorTable.ensureFieldAccessorsInitialized(Control.class, Builder.class);
        }

        private Builder() {
            this.environment_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.environment_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Control.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.environment_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ControlProto.internal_static_google_api_Control_descriptor;
        }

        public Control getDefaultInstanceForType() {
            return Control.getDefaultInstance();
        }

        public Control build() {
            Control buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Control buildPartial() {
            Control control = new Control((com.google.protobuf.GeneratedMessageV3.Builder) this);
            control.environment_ = this.environment_;
            onBuilt();
            return control;
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
            if (message instanceof Control) {
                return mergeFrom((Control) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Control control) {
            if (control == Control.getDefaultInstance()) {
                return this;
            }
            if (!control.getEnvironment().isEmpty()) {
                this.environment_ = control.environment_;
                onChanged();
            }
            mergeUnknownFields(control.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Control.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Control.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Control r3 = (com.google.api.Control) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Control r4 = (com.google.api.Control) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Control.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Control$Builder");
        }

        public String getEnvironment() {
            Object obj = this.environment_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.environment_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getEnvironmentBytes() {
            Object obj = this.environment_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.environment_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setEnvironment(String str) {
            if (str != null) {
                this.environment_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearEnvironment() {
            this.environment_ = Control.getDefaultInstance().getEnvironment();
            onChanged();
            return this;
        }

        public Builder setEnvironmentBytes(ByteString byteString) {
            if (byteString != null) {
                Control.checkByteStringIsUtf8(byteString);
                this.environment_ = byteString;
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

    private Control(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Control() {
        this.memoizedIsInitialized = -1;
        this.environment_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Control(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.environment_ = codedInputStream.readStringRequireUtf8();
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
        return ControlProto.internal_static_google_api_Control_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ControlProto.internal_static_google_api_Control_fieldAccessorTable.ensureFieldAccessorsInitialized(Control.class, Builder.class);
    }

    public String getEnvironment() {
        Object obj = this.environment_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.environment_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getEnvironmentBytes() {
        Object obj = this.environment_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.environment_ = copyFromUtf8;
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
        if (!getEnvironmentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.environment_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getEnvironmentBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.environment_);
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
        if (!(obj instanceof Control)) {
            return super.equals(obj);
        }
        Control control = (Control) obj;
        if (!(getEnvironment().equals(control.getEnvironment())) || !this.unknownFields.equals(control.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getEnvironment().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Control parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Control) PARSER.parseFrom(byteBuffer);
    }

    public static Control parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Control) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Control parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Control) PARSER.parseFrom(byteString);
    }

    public static Control parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Control) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Control parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Control) PARSER.parseFrom(bArr);
    }

    public static Control parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Control) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Control parseFrom(InputStream inputStream) throws IOException {
        return (Control) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Control parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Control) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Control parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Control) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Control parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Control) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Control parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Control) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Control parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Control) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Control control) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(control);
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

    public static Control getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Control> parser() {
        return PARSER;
    }

    public Parser<Control> getParserForType() {
        return PARSER;
    }

    public Control getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
