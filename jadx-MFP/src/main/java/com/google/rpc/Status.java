package com.google.rpc;

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

public final class Status extends GeneratedMessageV3 implements StatusOrBuilder {
    public static final int CODE_FIELD_NUMBER = 1;
    private static final Status DEFAULT_INSTANCE = new Status();
    public static final int DETAILS_FIELD_NUMBER = 3;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<Status> PARSER = new AbstractParser<Status>() {
        public Status parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Status(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public int code_;
    /* access modifiers changed from: private */
    public List<Any> details_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object message_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements StatusOrBuilder {
        private int bitField0_;
        private int code_;
        private RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> detailsBuilder_;
        private List<Any> details_;
        private Object message_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return StatusProto.internal_static_google_rpc_Status_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return StatusProto.internal_static_google_rpc_Status_fieldAccessorTable.ensureFieldAccessorsInitialized(Status.class, Builder.class);
        }

        private Builder() {
            this.message_ = "";
            this.details_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.message_ = "";
            this.details_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Status.alwaysUseFieldBuilders) {
                getDetailsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.code_ = 0;
            this.message_ = "";
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.details_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return StatusProto.internal_static_google_rpc_Status_descriptor;
        }

        public Status getDefaultInstanceForType() {
            return Status.getDefaultInstance();
        }

        public Status build() {
            Status buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Status buildPartial() {
            Status status = new Status((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            status.code_ = this.code_;
            status.message_ = this.message_;
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.details_ = Collections.unmodifiableList(this.details_);
                    this.bitField0_ &= -5;
                }
                status.details_ = this.details_;
            } else {
                status.details_ = repeatedFieldBuilderV3.build();
            }
            status.bitField0_ = 0;
            onBuilt();
            return status;
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
            if (message instanceof Status) {
                return mergeFrom((Status) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Status status) {
            if (status == Status.getDefaultInstance()) {
                return this;
            }
            if (status.getCode() != 0) {
                setCode(status.getCode());
            }
            if (!status.getMessage().isEmpty()) {
                this.message_ = status.message_;
                onChanged();
            }
            if (this.detailsBuilder_ == null) {
                if (!status.details_.isEmpty()) {
                    if (this.details_.isEmpty()) {
                        this.details_ = status.details_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureDetailsIsMutable();
                        this.details_.addAll(status.details_);
                    }
                    onChanged();
                }
            } else if (!status.details_.isEmpty()) {
                if (this.detailsBuilder_.isEmpty()) {
                    this.detailsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = null;
                    this.detailsBuilder_ = null;
                    this.details_ = status.details_;
                    this.bitField0_ &= -5;
                    if (Status.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getDetailsFieldBuilder();
                    }
                    this.detailsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.detailsBuilder_.addAllMessages(status.details_);
                }
            }
            mergeUnknownFields(status.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.Status.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.Status.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.Status r3 = (com.google.rpc.Status) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.Status r4 = (com.google.rpc.Status) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Status.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Status$Builder");
        }

        public int getCode() {
            return this.code_;
        }

        public Builder setCode(int i) {
            this.code_ = i;
            onChanged();
            return this;
        }

        public Builder clearCode() {
            this.code_ = 0;
            onChanged();
            return this;
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
            this.message_ = Status.getDefaultInstance().getMessage();
            onChanged();
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            if (byteString != null) {
                Status.checkByteStringIsUtf8(byteString);
                this.message_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureDetailsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.details_ = new ArrayList(this.details_);
                this.bitField0_ |= 4;
            }
        }

        public List<Any> getDetailsList() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.details_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getDetailsCount() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.details_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Any getDetails(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Any) this.details_.get(i);
            }
            return (Any) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setDetails(int i, Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, any);
            } else if (any != null) {
                ensureDetailsIsMutable();
                this.details_.set(i, any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setDetails(int i, com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addDetails(Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(any);
            } else if (any != null) {
                ensureDetailsIsMutable();
                this.details_.add(any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addDetails(int i, Any any) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, any);
            } else if (any != null) {
                ensureDetailsIsMutable();
                this.details_.add(i, any);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addDetails(com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addDetails(int i, com.google.protobuf.Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllDetails(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.details_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearDetails() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.details_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeDetails(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDetailsIsMutable();
                this.details_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Any.Builder getDetailsBuilder(int i) {
            return (com.google.protobuf.Any.Builder) getDetailsFieldBuilder().getBuilder(i);
        }

        public AnyOrBuilder getDetailsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AnyOrBuilder) this.details_.get(i);
            }
            return (AnyOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
            RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.detailsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.details_);
        }

        public com.google.protobuf.Any.Builder addDetailsBuilder() {
            return (com.google.protobuf.Any.Builder) getDetailsFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public com.google.protobuf.Any.Builder addDetailsBuilder(int i) {
            return (com.google.protobuf.Any.Builder) getDetailsFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<com.google.protobuf.Any.Builder> getDetailsBuilderList() {
            return getDetailsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, com.google.protobuf.Any.Builder, AnyOrBuilder> getDetailsFieldBuilder() {
            if (this.detailsBuilder_ == null) {
                this.detailsBuilder_ = new RepeatedFieldBuilderV3<>(this.details_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.details_ = null;
            }
            return this.detailsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Status(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Status() {
        this.memoizedIsInitialized = -1;
        this.code_ = 0;
        this.message_ = "";
        this.details_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Status(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 8) {
                        this.code_ = codedInputStream.readInt32();
                    } else if (readTag == 18) {
                        this.message_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.details_ = new ArrayList();
                            z2 |= true;
                        }
                        this.details_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.details_ = Collections.unmodifiableList(this.details_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.details_ = Collections.unmodifiableList(this.details_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return StatusProto.internal_static_google_rpc_Status_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return StatusProto.internal_static_google_rpc_Status_fieldAccessorTable.ensureFieldAccessorsInitialized(Status.class, Builder.class);
    }

    public int getCode() {
        return this.code_;
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

    public List<Any> getDetailsList() {
        return this.details_;
    }

    public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
        return this.details_;
    }

    public int getDetailsCount() {
        return this.details_.size();
    }

    public Any getDetails(int i) {
        return (Any) this.details_.get(i);
    }

    public AnyOrBuilder getDetailsOrBuilder(int i) {
        return (AnyOrBuilder) this.details_.get(i);
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
        int i = this.code_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        if (!getMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.message_);
        }
        for (int i2 = 0; i2 < this.details_.size(); i2++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.details_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.code_;
        int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) + 0 : 0;
        if (!getMessageBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.message_);
        }
        for (int i3 = 0; i3 < this.details_.size(); i3++) {
            computeInt32Size += CodedOutputStream.computeMessageSize(3, (MessageLite) this.details_.get(i3));
        }
        int serializedSize = computeInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Status)) {
            return super.equals(obj);
        }
        Status status = (Status) obj;
        if (!(((getCode() == status.getCode()) && getMessage().equals(status.getMessage())) && getDetailsList().equals(status.getDetailsList())) || !this.unknownFields.equals(status.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCode()) * 37) + 2) * 53) + getMessage().hashCode();
        if (getDetailsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getDetailsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Status parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Status) PARSER.parseFrom(byteBuffer);
    }

    public static Status parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Status) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Status parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Status) PARSER.parseFrom(byteString);
    }

    public static Status parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Status) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Status parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Status) PARSER.parseFrom(bArr);
    }

    public static Status parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Status) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Status parseFrom(InputStream inputStream) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Status parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Status parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Status) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Status parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Status) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Status parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Status parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Status) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Status status) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(status);
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

    public static Status getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Status> parser() {
        return PARSER;
    }

    public Parser<Status> getParserForType() {
        return PARSER;
    }

    public Status getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
