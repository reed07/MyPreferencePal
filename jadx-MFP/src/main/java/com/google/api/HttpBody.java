package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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

public final class HttpBody extends GeneratedMessageV3 implements HttpBodyOrBuilder {
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 2;
    private static final HttpBody DEFAULT_INSTANCE = new HttpBody();
    public static final int EXTENSIONS_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final Parser<HttpBody> PARSER = new AbstractParser<HttpBody>() {
        public HttpBody parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpBody(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object contentType_;
    /* access modifiers changed from: private */
    public ByteString data_;
    /* access modifiers changed from: private */
    public List<Any> extensions_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements HttpBodyOrBuilder {
        private int bitField0_;
        private Object contentType_;
        private ByteString data_;
        private RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> extensionsBuilder_;
        private List<Any> extensions_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpBodyProto.internal_static_google_api_HttpBody_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpBody.class, Builder.class);
        }

        private Builder() {
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            this.extensions_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            this.extensions_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (HttpBody.alwaysUseFieldBuilders) {
                getExtensionsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.extensions_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
        }

        public HttpBody getDefaultInstanceForType() {
            return HttpBody.getDefaultInstance();
        }

        public HttpBody build() {
            HttpBody buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public HttpBody buildPartial() {
            HttpBody httpBody = new HttpBody((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            httpBody.contentType_ = this.contentType_;
            httpBody.data_ = this.data_;
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.extensions_ = Collections.unmodifiableList(this.extensions_);
                    this.bitField0_ &= -5;
                }
                httpBody.extensions_ = this.extensions_;
            } else {
                httpBody.extensions_ = repeatedFieldBuilderV3.build();
            }
            httpBody.bitField0_ = 0;
            onBuilt();
            return httpBody;
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
            if (message instanceof HttpBody) {
                return mergeFrom((HttpBody) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpBody httpBody) {
            if (httpBody == HttpBody.getDefaultInstance()) {
                return this;
            }
            if (!httpBody.getContentType().isEmpty()) {
                this.contentType_ = httpBody.contentType_;
                onChanged();
            }
            if (httpBody.getData() != ByteString.EMPTY) {
                setData(httpBody.getData());
            }
            if (this.extensionsBuilder_ == null) {
                if (!httpBody.extensions_.isEmpty()) {
                    if (this.extensions_.isEmpty()) {
                        this.extensions_ = httpBody.extensions_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureExtensionsIsMutable();
                        this.extensions_.addAll(httpBody.extensions_);
                    }
                    onChanged();
                }
            } else if (!httpBody.extensions_.isEmpty()) {
                if (this.extensionsBuilder_.isEmpty()) {
                    this.extensionsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = null;
                    this.extensionsBuilder_ = null;
                    this.extensions_ = httpBody.extensions_;
                    this.bitField0_ &= -5;
                    if (HttpBody.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getExtensionsFieldBuilder();
                    }
                    this.extensionsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.extensionsBuilder_.addAllMessages(httpBody.extensions_);
                }
            }
            mergeUnknownFields(httpBody.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.HttpBody.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.HttpBody.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.HttpBody r3 = (com.google.api.HttpBody) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.HttpBody r4 = (com.google.api.HttpBody) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpBody.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.HttpBody$Builder");
        }

        public String getContentType() {
            Object obj = this.contentType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.contentType_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getContentTypeBytes() {
            Object obj = this.contentType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.contentType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setContentType(String str) {
            if (str != null) {
                this.contentType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearContentType() {
            this.contentType_ = HttpBody.getDefaultInstance().getContentType();
            onChanged();
            return this;
        }

        public Builder setContentTypeBytes(ByteString byteString) {
            if (byteString != null) {
                HttpBody.checkByteStringIsUtf8(byteString);
                this.contentType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public ByteString getData() {
            return this.data_;
        }

        public Builder setData(ByteString byteString) {
            if (byteString != null) {
                this.data_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearData() {
            this.data_ = HttpBody.getDefaultInstance().getData();
            onChanged();
            return this;
        }

        private void ensureExtensionsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.extensions_ = new ArrayList(this.extensions_);
                this.bitField0_ |= 4;
            }
        }

        public List<Any> getExtensionsList() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.extensions_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getExtensionsCount() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.extensions_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Any getExtensions(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Any) this.extensions_.get(i);
            }
            return (Any) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setExtensions(int i, Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, any);
            } else if (any != null) {
                ensureExtensionsIsMutable();
                this.extensions_.set(i, any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setExtensions(int i, com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addExtensions(Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(any);
            } else if (any != null) {
                ensureExtensionsIsMutable();
                this.extensions_.add(any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addExtensions(int i, Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, any);
            } else if (any != null) {
                ensureExtensionsIsMutable();
                this.extensions_.add(i, any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addExtensions(com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addExtensions(int i, com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllExtensions(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.extensions_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearExtensions() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.extensions_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeExtensions(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getExtensionsBuilder(int i) {
            return (com.google.protobuf.Any.Builder) getExtensionsFieldBuilder().getBuilder(i);
        }

        public AnyOrBuilder getExtensionsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AnyOrBuilder) this.extensions_.get(i);
            }
            return (AnyOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.extensions_);
        }

        public com.google.protobuf.Any.Builder addExtensionsBuilder() {
            return (com.google.protobuf.Any.Builder) getExtensionsFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public com.google.protobuf.Any.Builder addExtensionsBuilder(int i) {
            return (com.google.protobuf.Any.Builder) getExtensionsFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<com.google.protobuf.Any.Builder> getExtensionsBuilderList() {
            return getExtensionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getExtensionsFieldBuilder() {
            if (this.extensionsBuilder_ == null) {
                this.extensionsBuilder_ = new RepeatedFieldBuilderV3<>(this.extensions_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.extensions_ = null;
            }
            return this.extensionsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private HttpBody(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private HttpBody() {
        this.memoizedIsInitialized = -1;
        this.contentType_ = "";
        this.data_ = ByteString.EMPTY;
        this.extensions_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HttpBody(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.contentType_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.data_ = codedInputStream.readBytes();
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.extensions_ = new ArrayList();
                            z2 |= true;
                        }
                        this.extensions_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.extensions_ = Collections.unmodifiableList(this.extensions_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.extensions_ = Collections.unmodifiableList(this.extensions_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpBodyProto.internal_static_google_api_HttpBody_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpBody.class, Builder.class);
    }

    public String getContentType() {
        Object obj = this.contentType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.contentType_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getContentTypeBytes() {
        Object obj = this.contentType_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.contentType_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public ByteString getData() {
        return this.data_;
    }

    public List<Any> getExtensionsList() {
        return this.extensions_;
    }

    public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
        return this.extensions_;
    }

    public int getExtensionsCount() {
        return this.extensions_.size();
    }

    public Any getExtensions(int i) {
        return (Any) this.extensions_.get(i);
    }

    public AnyOrBuilder getExtensionsOrBuilder(int i) {
        return (AnyOrBuilder) this.extensions_.get(i);
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
        if (!getContentTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.contentType_);
        }
        if (!this.data_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.data_);
        }
        for (int i = 0; i < this.extensions_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.extensions_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getContentTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.contentType_) + 0 : 0;
        if (!this.data_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeBytesSize(2, this.data_);
        }
        for (int i2 = 0; i2 < this.extensions_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.extensions_.get(i2));
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
        if (!(obj instanceof HttpBody)) {
            return super.equals(obj);
        }
        HttpBody httpBody = (HttpBody) obj;
        if (!(((getContentType().equals(httpBody.getContentType())) && getData().equals(httpBody.getData())) && getExtensionsList().equals(httpBody.getExtensionsList())) || !this.unknownFields.equals(httpBody.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getContentType().hashCode()) * 37) + 2) * 53) + getData().hashCode();
        if (getExtensionsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getExtensionsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static HttpBody parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HttpBody) PARSER.parseFrom(byteBuffer);
    }

    public static HttpBody parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpBody) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpBody parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpBody) PARSER.parseFrom(byteString);
    }

    public static HttpBody parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpBody) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpBody parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpBody) PARSER.parseFrom(bArr);
    }

    public static HttpBody parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpBody) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpBody parseFrom(InputStream inputStream) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpBody parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpBody parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpBody parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpBody parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpBody parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpBody httpBody) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(httpBody);
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

    public static HttpBody getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpBody> parser() {
        return PARSER;
    }

    public Parser<HttpBody> getParserForType() {
        return PARSER;
    }

    public HttpBody getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
