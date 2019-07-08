package com.google.rpc;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RetryInfo extends GeneratedMessageV3 implements RetryInfoOrBuilder {
    private static final RetryInfo DEFAULT_INSTANCE = new RetryInfo();
    /* access modifiers changed from: private */
    public static final Parser<RetryInfo> PARSER = new AbstractParser<RetryInfo>() {
        public RetryInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RetryInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RETRY_DELAY_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Duration retryDelay_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements RetryInfoOrBuilder {
        private SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> retryDelayBuilder_;
        private Duration retryDelay_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryInfo.class, Builder.class);
        }

        private Builder() {
            this.retryDelay_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.retryDelay_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            RetryInfo.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = null;
            } else {
                this.retryDelay_ = null;
                this.retryDelayBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
        }

        public RetryInfo getDefaultInstanceForType() {
            return RetryInfo.getDefaultInstance();
        }

        public RetryInfo build() {
            RetryInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public RetryInfo buildPartial() {
            RetryInfo retryInfo = new RetryInfo((com.google.protobuf.GeneratedMessageV3.Builder) this);
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                retryInfo.retryDelay_ = this.retryDelay_;
            } else {
                retryInfo.retryDelay_ = (Duration) singleFieldBuilderV3.build();
            }
            onBuilt();
            return retryInfo;
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
            if (message instanceof RetryInfo) {
                return mergeFrom((RetryInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RetryInfo retryInfo) {
            if (retryInfo == RetryInfo.getDefaultInstance()) {
                return this;
            }
            if (retryInfo.hasRetryDelay()) {
                mergeRetryDelay(retryInfo.getRetryDelay());
            }
            mergeUnknownFields(retryInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.RetryInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.RetryInfo.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.RetryInfo r3 = (com.google.rpc.RetryInfo) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.RetryInfo r4 = (com.google.rpc.RetryInfo) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.RetryInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.RetryInfo$Builder");
        }

        public boolean hasRetryDelay() {
            return (this.retryDelayBuilder_ == null && this.retryDelay_ == null) ? false : true;
        }

        public Duration getRetryDelay() {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Duration) singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.retryDelay_;
            if (duration == null) {
                duration = Duration.getDefaultInstance();
            }
            return duration;
        }

        public Builder setRetryDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else if (duration != null) {
                this.retryDelay_ = duration;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRetryDelay(com.google.protobuf.Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.retryDelay_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRetryDelay(Duration duration) {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.retryDelay_;
                if (duration2 != null) {
                    this.retryDelay_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.retryDelay_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearRetryDelay() {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelay_ = null;
                onChanged();
            } else {
                this.retryDelay_ = null;
                this.retryDelayBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Duration.Builder getRetryDelayBuilder() {
            onChanged();
            return (com.google.protobuf.Duration.Builder) getRetryDelayFieldBuilder().getBuilder();
        }

        public DurationOrBuilder getRetryDelayOrBuilder() {
            SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.retryDelayBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DurationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.retryDelay_;
            if (duration == null) {
                duration = Duration.getDefaultInstance();
            }
            return duration;
        }

        private SingleFieldBuilderV3<Duration, com.google.protobuf.Duration.Builder, DurationOrBuilder> getRetryDelayFieldBuilder() {
            if (this.retryDelayBuilder_ == null) {
                this.retryDelayBuilder_ = new SingleFieldBuilderV3<>(getRetryDelay(), getParentForChildren(), isClean());
                this.retryDelay_ = null;
            }
            return this.retryDelayBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private RetryInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private RetryInfo() {
        this.memoizedIsInitialized = -1;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RetryInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        com.google.protobuf.Duration.Builder builder = null;
                        if (this.retryDelay_ != null) {
                            builder = this.retryDelay_.toBuilder();
                        }
                        this.retryDelay_ = (Duration) codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.retryDelay_);
                            this.retryDelay_ = builder.buildPartial();
                        }
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
        return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_RetryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RetryInfo.class, Builder.class);
    }

    public boolean hasRetryDelay() {
        return this.retryDelay_ != null;
    }

    public Duration getRetryDelay() {
        Duration duration = this.retryDelay_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    public DurationOrBuilder getRetryDelayOrBuilder() {
        return getRetryDelay();
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
        if (this.retryDelay_ != null) {
            codedOutputStream.writeMessage(1, getRetryDelay());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.retryDelay_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getRetryDelay());
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
        if (!(obj instanceof RetryInfo)) {
            return super.equals(obj);
        }
        RetryInfo retryInfo = (RetryInfo) obj;
        boolean z2 = hasRetryDelay() == retryInfo.hasRetryDelay();
        if (hasRetryDelay()) {
            z2 = z2 && getRetryDelay().equals(retryInfo.getRetryDelay());
        }
        if (!z2 || !this.unknownFields.equals(retryInfo.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasRetryDelay()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getRetryDelay().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static RetryInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteBuffer);
    }

    public static RetryInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteString);
    }

    public static RetryInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(bArr);
    }

    public static RetryInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RetryInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(InputStream inputStream) throws IOException {
        return (RetryInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RetryInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RetryInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RetryInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RetryInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RetryInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RetryInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RetryInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RetryInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RetryInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RetryInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RetryInfo retryInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(retryInfo);
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

    public static RetryInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RetryInfo> parser() {
        return PARSER;
    }

    public Parser<RetryInfo> getParserForType() {
        return PARSER;
    }

    public RetryInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
