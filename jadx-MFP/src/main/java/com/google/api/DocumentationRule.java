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

public final class DocumentationRule extends GeneratedMessageV3 implements DocumentationRuleOrBuilder {
    private static final DocumentationRule DEFAULT_INSTANCE = new DocumentationRule();
    public static final int DEPRECATION_DESCRIPTION_FIELD_NUMBER = 3;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<DocumentationRule> PARSER = new AbstractParser<DocumentationRule>() {
        public DocumentationRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DocumentationRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public volatile Object deprecationDescription_;
    /* access modifiers changed from: private */
    public volatile Object description_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements DocumentationRuleOrBuilder {
        private Object deprecationDescription_;
        private Object description_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_DocumentationRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_DocumentationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentationRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.description_ = "";
            this.deprecationDescription_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.description_ = "";
            this.deprecationDescription_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            DocumentationRule.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.description_ = "";
            this.deprecationDescription_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_DocumentationRule_descriptor;
        }

        public DocumentationRule getDefaultInstanceForType() {
            return DocumentationRule.getDefaultInstance();
        }

        public DocumentationRule build() {
            DocumentationRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public DocumentationRule buildPartial() {
            DocumentationRule documentationRule = new DocumentationRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            documentationRule.selector_ = this.selector_;
            documentationRule.description_ = this.description_;
            documentationRule.deprecationDescription_ = this.deprecationDescription_;
            onBuilt();
            return documentationRule;
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
            if (message instanceof DocumentationRule) {
                return mergeFrom((DocumentationRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DocumentationRule documentationRule) {
            if (documentationRule == DocumentationRule.getDefaultInstance()) {
                return this;
            }
            if (!documentationRule.getSelector().isEmpty()) {
                this.selector_ = documentationRule.selector_;
                onChanged();
            }
            if (!documentationRule.getDescription().isEmpty()) {
                this.description_ = documentationRule.description_;
                onChanged();
            }
            if (!documentationRule.getDeprecationDescription().isEmpty()) {
                this.deprecationDescription_ = documentationRule.deprecationDescription_;
                onChanged();
            }
            mergeUnknownFields(documentationRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.DocumentationRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.DocumentationRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.DocumentationRule r3 = (com.google.api.DocumentationRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.DocumentationRule r4 = (com.google.api.DocumentationRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.DocumentationRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.DocumentationRule$Builder");
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
            this.selector_ = DocumentationRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                DocumentationRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDescription(String str) {
            if (str != null) {
                this.description_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDescription() {
            this.description_ = DocumentationRule.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                DocumentationRule.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDeprecationDescription() {
            Object obj = this.deprecationDescription_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.deprecationDescription_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDeprecationDescriptionBytes() {
            Object obj = this.deprecationDescription_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.deprecationDescription_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDeprecationDescription(String str) {
            if (str != null) {
                this.deprecationDescription_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDeprecationDescription() {
            this.deprecationDescription_ = DocumentationRule.getDefaultInstance().getDeprecationDescription();
            onChanged();
            return this;
        }

        public Builder setDeprecationDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                DocumentationRule.checkByteStringIsUtf8(byteString);
                this.deprecationDescription_ = byteString;
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

    private DocumentationRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private DocumentationRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.description_ = "";
        this.deprecationDescription_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DocumentationRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.selector_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.description_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        this.deprecationDescription_ = codedInputStream.readStringRequireUtf8();
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
        return DocumentationProto.internal_static_google_api_DocumentationRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentationProto.internal_static_google_api_DocumentationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentationRule.class, Builder.class);
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

    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.description_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getDeprecationDescription() {
        Object obj = this.deprecationDescription_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.deprecationDescription_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDeprecationDescriptionBytes() {
        Object obj = this.deprecationDescription_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.deprecationDescription_ = copyFromUtf8;
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
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
        }
        if (!getDeprecationDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.deprecationDescription_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getSelectorBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.selector_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.description_);
        }
        if (!getDeprecationDescriptionBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.deprecationDescription_);
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
        if (!(obj instanceof DocumentationRule)) {
            return super.equals(obj);
        }
        DocumentationRule documentationRule = (DocumentationRule) obj;
        if (!(((getSelector().equals(documentationRule.getSelector())) && getDescription().equals(documentationRule.getDescription())) && getDeprecationDescription().equals(documentationRule.getDeprecationDescription())) || !this.unknownFields.equals(documentationRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 37) + 3) * 53) + getDeprecationDescription().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DocumentationRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentationRule) PARSER.parseFrom(byteBuffer);
    }

    public static DocumentationRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentationRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DocumentationRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentationRule) PARSER.parseFrom(byteString);
    }

    public static DocumentationRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentationRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DocumentationRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentationRule) PARSER.parseFrom(bArr);
    }

    public static DocumentationRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentationRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DocumentationRule parseFrom(InputStream inputStream) throws IOException {
        return (DocumentationRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DocumentationRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentationRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DocumentationRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentationRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DocumentationRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentationRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DocumentationRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentationRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DocumentationRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentationRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DocumentationRule documentationRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(documentationRule);
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

    public static DocumentationRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentationRule> parser() {
        return PARSER;
    }

    public Parser<DocumentationRule> getParserForType() {
        return PARSER;
    }

    public DocumentationRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
