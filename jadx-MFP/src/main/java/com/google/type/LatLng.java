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

public final class LatLng extends GeneratedMessageV3 implements LatLngOrBuilder {
    private static final LatLng DEFAULT_INSTANCE = new LatLng();
    public static final int LATITUDE_FIELD_NUMBER = 1;
    public static final int LONGITUDE_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<LatLng> PARSER = new AbstractParser<LatLng>() {
        public LatLng parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LatLng(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public double latitude_;
    /* access modifiers changed from: private */
    public double longitude_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LatLngOrBuilder {
        private double latitude_;
        private double longitude_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return LatLngProto.internal_static_google_type_LatLng_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return LatLngProto.internal_static_google_type_LatLng_fieldAccessorTable.ensureFieldAccessorsInitialized(LatLng.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            LatLng.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.latitude_ = 0.0d;
            this.longitude_ = 0.0d;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return LatLngProto.internal_static_google_type_LatLng_descriptor;
        }

        public LatLng getDefaultInstanceForType() {
            return LatLng.getDefaultInstance();
        }

        public LatLng build() {
            LatLng buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public LatLng buildPartial() {
            LatLng latLng = new LatLng((com.google.protobuf.GeneratedMessageV3.Builder) this);
            latLng.latitude_ = this.latitude_;
            latLng.longitude_ = this.longitude_;
            onBuilt();
            return latLng;
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
            if (message instanceof LatLng) {
                return mergeFrom((LatLng) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LatLng latLng) {
            if (latLng == LatLng.getDefaultInstance()) {
                return this;
            }
            if (latLng.getLatitude() != 0.0d) {
                setLatitude(latLng.getLatitude());
            }
            if (latLng.getLongitude() != 0.0d) {
                setLongitude(latLng.getLongitude());
            }
            mergeUnknownFields(latLng.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.type.LatLng.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.LatLng.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.type.LatLng r3 = (com.google.type.LatLng) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.type.LatLng r4 = (com.google.type.LatLng) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.LatLng.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.LatLng$Builder");
        }

        public double getLatitude() {
            return this.latitude_;
        }

        public Builder setLatitude(double d) {
            this.latitude_ = d;
            onChanged();
            return this;
        }

        public Builder clearLatitude() {
            this.latitude_ = 0.0d;
            onChanged();
            return this;
        }

        public double getLongitude() {
            return this.longitude_;
        }

        public Builder setLongitude(double d) {
            this.longitude_ = d;
            onChanged();
            return this;
        }

        public Builder clearLongitude() {
            this.longitude_ = 0.0d;
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

    private LatLng(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private LatLng() {
        this.memoizedIsInitialized = -1;
        this.latitude_ = 0.0d;
        this.longitude_ = 0.0d;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LatLng(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 9) {
                        this.latitude_ = codedInputStream.readDouble();
                    } else if (readTag == 17) {
                        this.longitude_ = codedInputStream.readDouble();
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
        return LatLngProto.internal_static_google_type_LatLng_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return LatLngProto.internal_static_google_type_LatLng_fieldAccessorTable.ensureFieldAccessorsInitialized(LatLng.class, Builder.class);
    }

    public double getLatitude() {
        return this.latitude_;
    }

    public double getLongitude() {
        return this.longitude_;
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
        double d = this.latitude_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
        }
        double d2 = this.longitude_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(2, d2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        double d = this.latitude_;
        if (d != 0.0d) {
            i2 = 0 + CodedOutputStream.computeDoubleSize(1, d);
        }
        double d2 = this.longitude_;
        if (d2 != 0.0d) {
            i2 += CodedOutputStream.computeDoubleSize(2, d2);
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
        if (!(obj instanceof LatLng)) {
            return super.equals(obj);
        }
        LatLng latLng = (LatLng) obj;
        if (!(((Double.doubleToLongBits(getLatitude()) > Double.doubleToLongBits(latLng.getLatitude()) ? 1 : (Double.doubleToLongBits(getLatitude()) == Double.doubleToLongBits(latLng.getLatitude()) ? 0 : -1)) == 0) && Double.doubleToLongBits(getLongitude()) == Double.doubleToLongBits(latLng.getLongitude())) || !this.unknownFields.equals(latLng.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getLatitude()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getLongitude()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static LatLng parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (LatLng) PARSER.parseFrom(byteBuffer);
    }

    public static LatLng parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LatLng) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LatLng parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LatLng) PARSER.parseFrom(byteString);
    }

    public static LatLng parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LatLng) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LatLng parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LatLng) PARSER.parseFrom(bArr);
    }

    public static LatLng parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LatLng) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LatLng parseFrom(InputStream inputStream) throws IOException {
        return (LatLng) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LatLng parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LatLng) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LatLng parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (LatLng) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LatLng parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LatLng) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LatLng parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (LatLng) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LatLng parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LatLng) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LatLng latLng) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(latLng);
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

    public static LatLng getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LatLng> parser() {
        return PARSER;
    }

    public Parser<LatLng> getParserForType() {
        return PARSER;
    }

    public LatLng getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
