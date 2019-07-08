package com.google.type;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
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

public final class Color extends GeneratedMessageV3 implements ColorOrBuilder {
    public static final int ALPHA_FIELD_NUMBER = 4;
    public static final int BLUE_FIELD_NUMBER = 3;
    private static final Color DEFAULT_INSTANCE = new Color();
    public static final int GREEN_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<Color> PARSER = new AbstractParser<Color>() {
        public Color parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Color(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RED_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public FloatValue alpha_;
    /* access modifiers changed from: private */
    public float blue_;
    /* access modifiers changed from: private */
    public float green_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public float red_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ColorOrBuilder {
        private SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> alphaBuilder_;
        private FloatValue alpha_;
        private float blue_;
        private float green_;
        private float red_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
        }

        private Builder() {
            this.alpha_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.alpha_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Color.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.red_ = BitmapDescriptorFactory.HUE_RED;
            this.green_ = BitmapDescriptorFactory.HUE_RED;
            this.blue_ = BitmapDescriptorFactory.HUE_RED;
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        public Color getDefaultInstanceForType() {
            return Color.getDefaultInstance();
        }

        public Color build() {
            Color buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Color buildPartial() {
            Color color = new Color((com.google.protobuf.GeneratedMessageV3.Builder) this);
            color.red_ = this.red_;
            color.green_ = this.green_;
            color.blue_ = this.blue_;
            SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                color.alpha_ = this.alpha_;
            } else {
                color.alpha_ = (FloatValue) singleFieldBuilderV3.build();
            }
            onBuilt();
            return color;
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
            if (message instanceof Color) {
                return mergeFrom((Color) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Color color) {
            if (color == Color.getDefaultInstance()) {
                return this;
            }
            if (color.getRed() != BitmapDescriptorFactory.HUE_RED) {
                setRed(color.getRed());
            }
            if (color.getGreen() != BitmapDescriptorFactory.HUE_RED) {
                setGreen(color.getGreen());
            }
            if (color.getBlue() != BitmapDescriptorFactory.HUE_RED) {
                setBlue(color.getBlue());
            }
            if (color.hasAlpha()) {
                mergeAlpha(color.getAlpha());
            }
            mergeUnknownFields(color.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.type.Color.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Color.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.type.Color r3 = (com.google.type.Color) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.type.Color r4 = (com.google.type.Color) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Color.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Color$Builder");
        }

        public float getRed() {
            return this.red_;
        }

        public Builder setRed(float f) {
            this.red_ = f;
            onChanged();
            return this;
        }

        public Builder clearRed() {
            this.red_ = BitmapDescriptorFactory.HUE_RED;
            onChanged();
            return this;
        }

        public float getGreen() {
            return this.green_;
        }

        public Builder setGreen(float f) {
            this.green_ = f;
            onChanged();
            return this;
        }

        public Builder clearGreen() {
            this.green_ = BitmapDescriptorFactory.HUE_RED;
            onChanged();
            return this;
        }

        public float getBlue() {
            return this.blue_;
        }

        public Builder setBlue(float f) {
            this.blue_ = f;
            onChanged();
            return this;
        }

        public Builder clearBlue() {
            this.blue_ = BitmapDescriptorFactory.HUE_RED;
            onChanged();
            return this;
        }

        public boolean hasAlpha() {
            return (this.alphaBuilder_ == null && this.alpha_ == null) ? false : true;
        }

        public FloatValue getAlpha() {
            SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FloatValue) singleFieldBuilderV3.getMessage();
            }
            FloatValue floatValue = this.alpha_;
            if (floatValue == null) {
                floatValue = FloatValue.getDefaultInstance();
            }
            return floatValue;
        }

        public Builder setAlpha(FloatValue floatValue) {
            SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(floatValue);
            } else if (floatValue != null) {
                this.alpha_ = floatValue;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAlpha(com.google.protobuf.FloatValue.Builder builder) {
            SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.alpha_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAlpha(FloatValue floatValue) {
            SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                FloatValue floatValue2 = this.alpha_;
                if (floatValue2 != null) {
                    this.alpha_ = FloatValue.newBuilder(floatValue2).mergeFrom(floatValue).buildPartial();
                } else {
                    this.alpha_ = floatValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(floatValue);
            }
            return this;
        }

        public Builder clearAlpha() {
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
                onChanged();
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.FloatValue.Builder getAlphaBuilder() {
            onChanged();
            return (com.google.protobuf.FloatValue.Builder) getAlphaFieldBuilder().getBuilder();
        }

        public FloatValueOrBuilder getAlphaOrBuilder() {
            SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FloatValueOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FloatValue floatValue = this.alpha_;
            if (floatValue == null) {
                floatValue = FloatValue.getDefaultInstance();
            }
            return floatValue;
        }

        private SingleFieldBuilderV3<FloatValue, com.google.protobuf.FloatValue.Builder, FloatValueOrBuilder> getAlphaFieldBuilder() {
            if (this.alphaBuilder_ == null) {
                this.alphaBuilder_ = new SingleFieldBuilderV3<>(getAlpha(), getParentForChildren(), isClean());
                this.alpha_ = null;
            }
            return this.alphaBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Color(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Color() {
        this.memoizedIsInitialized = -1;
        this.red_ = BitmapDescriptorFactory.HUE_RED;
        this.green_ = BitmapDescriptorFactory.HUE_RED;
        this.blue_ = BitmapDescriptorFactory.HUE_RED;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Color(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 13) {
                        this.red_ = codedInputStream.readFloat();
                    } else if (readTag == 21) {
                        this.green_ = codedInputStream.readFloat();
                    } else if (readTag == 29) {
                        this.blue_ = codedInputStream.readFloat();
                    } else if (readTag == 34) {
                        com.google.protobuf.FloatValue.Builder builder = null;
                        if (this.alpha_ != null) {
                            builder = this.alpha_.toBuilder();
                        }
                        this.alpha_ = (FloatValue) codedInputStream.readMessage(FloatValue.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.alpha_);
                            this.alpha_ = builder.buildPartial();
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
        return ColorProto.internal_static_google_type_Color_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
    }

    public float getRed() {
        return this.red_;
    }

    public float getGreen() {
        return this.green_;
    }

    public float getBlue() {
        return this.blue_;
    }

    public boolean hasAlpha() {
        return this.alpha_ != null;
    }

    public FloatValue getAlpha() {
        FloatValue floatValue = this.alpha_;
        return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
    }

    public FloatValueOrBuilder getAlphaOrBuilder() {
        return getAlpha();
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
        float f = this.red_;
        if (f != BitmapDescriptorFactory.HUE_RED) {
            codedOutputStream.writeFloat(1, f);
        }
        float f2 = this.green_;
        if (f2 != BitmapDescriptorFactory.HUE_RED) {
            codedOutputStream.writeFloat(2, f2);
        }
        float f3 = this.blue_;
        if (f3 != BitmapDescriptorFactory.HUE_RED) {
            codedOutputStream.writeFloat(3, f3);
        }
        if (this.alpha_ != null) {
            codedOutputStream.writeMessage(4, getAlpha());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        float f = this.red_;
        if (f != BitmapDescriptorFactory.HUE_RED) {
            i2 = 0 + CodedOutputStream.computeFloatSize(1, f);
        }
        float f2 = this.green_;
        if (f2 != BitmapDescriptorFactory.HUE_RED) {
            i2 += CodedOutputStream.computeFloatSize(2, f2);
        }
        float f3 = this.blue_;
        if (f3 != BitmapDescriptorFactory.HUE_RED) {
            i2 += CodedOutputStream.computeFloatSize(3, f3);
        }
        if (this.alpha_ != null) {
            i2 += CodedOutputStream.computeMessageSize(4, getAlpha());
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
        if (!(obj instanceof Color)) {
            return super.equals(obj);
        }
        Color color = (Color) obj;
        boolean z2 = (((Float.floatToIntBits(getRed()) == Float.floatToIntBits(color.getRed())) && Float.floatToIntBits(getGreen()) == Float.floatToIntBits(color.getGreen())) && Float.floatToIntBits(getBlue()) == Float.floatToIntBits(color.getBlue())) && hasAlpha() == color.hasAlpha();
        if (hasAlpha()) {
            z2 = z2 && getAlpha().equals(color.getAlpha());
        }
        if (!z2 || !this.unknownFields.equals(color.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Float.floatToIntBits(getRed())) * 37) + 2) * 53) + Float.floatToIntBits(getGreen())) * 37) + 3) * 53) + Float.floatToIntBits(getBlue());
        if (hasAlpha()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getAlpha().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Color parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Color) PARSER.parseFrom(byteBuffer);
    }

    public static Color parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Color) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Color parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Color) PARSER.parseFrom(byteString);
    }

    public static Color parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Color) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Color parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Color) PARSER.parseFrom(bArr);
    }

    public static Color parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Color) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Color parseFrom(InputStream inputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Color parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Color parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Color parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Color parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Color parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Color color) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(color);
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

    public static Color getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Color> parser() {
        return PARSER;
    }

    public Parser<Color> getParserForType() {
        return PARSER;
    }

    public Color getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
