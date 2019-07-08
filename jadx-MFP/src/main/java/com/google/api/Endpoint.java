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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class Endpoint extends GeneratedMessageV3 implements EndpointOrBuilder {
    public static final int ALIASES_FIELD_NUMBER = 2;
    public static final int ALLOW_CORS_FIELD_NUMBER = 5;
    private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
    public static final int FEATURES_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>() {
        public Endpoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Endpoint(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TARGET_FIELD_NUMBER = 101;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public LazyStringList aliases_;
    /* access modifiers changed from: private */
    public boolean allowCors_;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public LazyStringList features_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public volatile Object target_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements EndpointOrBuilder {
        private LazyStringList aliases_;
        private boolean allowCors_;
        private int bitField0_;
        private LazyStringList features_;
        private Object name_;
        private Object target_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.features_ = LazyStringArrayList.EMPTY;
            this.target_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.features_ = LazyStringArrayList.EMPTY;
            this.target_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Endpoint.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            this.features_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            this.target_ = "";
            this.allowCors_ = false;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        public Endpoint getDefaultInstanceForType() {
            return Endpoint.getDefaultInstance();
        }

        public Endpoint build() {
            Endpoint buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Endpoint buildPartial() {
            Endpoint endpoint = new Endpoint((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            endpoint.name_ = this.name_;
            if ((this.bitField0_ & 2) == 2) {
                this.aliases_ = this.aliases_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            endpoint.aliases_ = this.aliases_;
            if ((this.bitField0_ & 4) == 4) {
                this.features_ = this.features_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            endpoint.features_ = this.features_;
            endpoint.target_ = this.target_;
            endpoint.allowCors_ = this.allowCors_;
            endpoint.bitField0_ = 0;
            onBuilt();
            return endpoint;
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
            if (message instanceof Endpoint) {
                return mergeFrom((Endpoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Endpoint endpoint) {
            if (endpoint == Endpoint.getDefaultInstance()) {
                return this;
            }
            if (!endpoint.getName().isEmpty()) {
                this.name_ = endpoint.name_;
                onChanged();
            }
            if (!endpoint.aliases_.isEmpty()) {
                if (this.aliases_.isEmpty()) {
                    this.aliases_ = endpoint.aliases_;
                    this.bitField0_ &= -3;
                } else {
                    ensureAliasesIsMutable();
                    this.aliases_.addAll(endpoint.aliases_);
                }
                onChanged();
            }
            if (!endpoint.features_.isEmpty()) {
                if (this.features_.isEmpty()) {
                    this.features_ = endpoint.features_;
                    this.bitField0_ &= -5;
                } else {
                    ensureFeaturesIsMutable();
                    this.features_.addAll(endpoint.features_);
                }
                onChanged();
            }
            if (!endpoint.getTarget().isEmpty()) {
                this.target_ = endpoint.target_;
                onChanged();
            }
            if (endpoint.getAllowCors()) {
                setAllowCors(endpoint.getAllowCors());
            }
            mergeUnknownFields(endpoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Endpoint.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Endpoint.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Endpoint r3 = (com.google.api.Endpoint) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Endpoint r4 = (com.google.api.Endpoint) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Endpoint.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Endpoint$Builder");
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
            this.name_ = Endpoint.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureAliasesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.aliases_ = new LazyStringArrayList(this.aliases_);
                this.bitField0_ |= 2;
            }
        }

        public ProtocolStringList getAliasesList() {
            return this.aliases_.getUnmodifiableView();
        }

        public int getAliasesCount() {
            return this.aliases_.size();
        }

        public String getAliases(int i) {
            return (String) this.aliases_.get(i);
        }

        public ByteString getAliasesBytes(int i) {
            return this.aliases_.getByteString(i);
        }

        public Builder setAliases(int i, String str) {
            if (str != null) {
                ensureAliasesIsMutable();
                this.aliases_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAliases(String str) {
            if (str != null) {
                ensureAliasesIsMutable();
                this.aliases_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllAliases(Iterable<String> iterable) {
            ensureAliasesIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.aliases_);
            onChanged();
            return this;
        }

        public Builder clearAliases() {
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addAliasesBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                ensureAliasesIsMutable();
                this.aliases_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureFeaturesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.features_ = new LazyStringArrayList(this.features_);
                this.bitField0_ |= 4;
            }
        }

        public ProtocolStringList getFeaturesList() {
            return this.features_.getUnmodifiableView();
        }

        public int getFeaturesCount() {
            return this.features_.size();
        }

        public String getFeatures(int i) {
            return (String) this.features_.get(i);
        }

        public ByteString getFeaturesBytes(int i) {
            return this.features_.getByteString(i);
        }

        public Builder setFeatures(int i, String str) {
            if (str != null) {
                ensureFeaturesIsMutable();
                this.features_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addFeatures(String str) {
            if (str != null) {
                ensureFeaturesIsMutable();
                this.features_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllFeatures(Iterable<String> iterable) {
            ensureFeaturesIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.features_);
            onChanged();
            return this;
        }

        public Builder clearFeatures() {
            this.features_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder addFeaturesBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                ensureFeaturesIsMutable();
                this.features_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getTarget() {
            Object obj = this.target_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.target_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getTargetBytes() {
            Object obj = this.target_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.target_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setTarget(String str) {
            if (str != null) {
                this.target_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearTarget() {
            this.target_ = Endpoint.getDefaultInstance().getTarget();
            onChanged();
            return this;
        }

        public Builder setTargetBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                this.target_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean getAllowCors() {
            return this.allowCors_;
        }

        public Builder setAllowCors(boolean z) {
            this.allowCors_ = z;
            onChanged();
            return this;
        }

        public Builder clearAllowCors() {
            this.allowCors_ = false;
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

    private Endpoint(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Endpoint() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.aliases_ = LazyStringArrayList.EMPTY;
        this.features_ = LazyStringArrayList.EMPTY;
        this.target_ = "";
        this.allowCors_ = false;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Endpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                        if (!(z2 & true)) {
                            this.aliases_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.aliases_.add(readStringRequireUtf8);
                    } else if (readTag == 34) {
                        String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                        if (!(z2 & true)) {
                            this.features_ = new LazyStringArrayList();
                            z2 |= true;
                        }
                        this.features_.add(readStringRequireUtf82);
                    } else if (readTag == 40) {
                        this.allowCors_ = codedInputStream.readBool();
                    } else if (readTag == 810) {
                        this.target_ = codedInputStream.readStringRequireUtf8();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.aliases_ = this.aliases_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.features_ = this.features_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.aliases_ = this.aliases_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.features_ = this.features_.getUnmodifiableView();
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return EndpointProto.internal_static_google_api_Endpoint_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
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

    public ProtocolStringList getAliasesList() {
        return this.aliases_;
    }

    public int getAliasesCount() {
        return this.aliases_.size();
    }

    public String getAliases(int i) {
        return (String) this.aliases_.get(i);
    }

    public ByteString getAliasesBytes(int i) {
        return this.aliases_.getByteString(i);
    }

    public ProtocolStringList getFeaturesList() {
        return this.features_;
    }

    public int getFeaturesCount() {
        return this.features_.size();
    }

    public String getFeatures(int i) {
        return (String) this.features_.get(i);
    }

    public ByteString getFeaturesBytes(int i) {
        return this.features_.getByteString(i);
    }

    public String getTarget() {
        Object obj = this.target_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.target_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getTargetBytes() {
        Object obj = this.target_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.target_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean getAllowCors() {
        return this.allowCors_;
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
        for (int i = 0; i < this.aliases_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.aliases_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.features_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.features_.getRaw(i2));
        }
        boolean z = this.allowCors_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        if (!getTargetBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 101, this.target_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.aliases_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.aliases_.getRaw(i3));
        }
        int size = computeStringSize + i2 + (getAliasesList().size() * 1);
        int i4 = 0;
        for (int i5 = 0; i5 < this.features_.size(); i5++) {
            i4 += computeStringSizeNoTag(this.features_.getRaw(i5));
        }
        int size2 = size + i4 + (getFeaturesList().size() * 1);
        boolean z = this.allowCors_;
        if (z) {
            size2 += CodedOutputStream.computeBoolSize(5, z);
        }
        if (!getTargetBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(101, this.target_);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Endpoint)) {
            return super.equals(obj);
        }
        Endpoint endpoint = (Endpoint) obj;
        if (!(((((getName().equals(endpoint.getName())) && getAliasesList().equals(endpoint.getAliasesList())) && getFeaturesList().equals(endpoint.getFeaturesList())) && getTarget().equals(endpoint.getTarget())) && getAllowCors() == endpoint.getAllowCors()) || !this.unknownFields.equals(endpoint.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getAliasesCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getAliasesList().hashCode();
        }
        if (getFeaturesCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getFeaturesList().hashCode();
        }
        int hashCode2 = (((((((((hashCode * 37) + 101) * 53) + getTarget().hashCode()) * 37) + 5) * 53) + Internal.hashBoolean(getAllowCors())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteBuffer);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteString);
    }

    public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Endpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(bArr);
    }

    public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Endpoint) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Endpoint parseFrom(InputStream inputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Endpoint endpoint) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(endpoint);
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

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return PARSER;
    }

    public Parser<Endpoint> getParserForType() {
        return PARSER;
    }

    public Endpoint getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
