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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class TimeOfDay extends GeneratedMessageV3 implements TimeOfDayOrBuilder {
    private static final TimeOfDay DEFAULT_INSTANCE = new TimeOfDay();
    public static final int HOURS_FIELD_NUMBER = 1;
    public static final int MINUTES_FIELD_NUMBER = 2;
    public static final int NANOS_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Parser<TimeOfDay> PARSER = new AbstractParser<TimeOfDay>() {
        public TimeOfDay parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TimeOfDay(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SECONDS_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int hours_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public int minutes_;
    /* access modifiers changed from: private */
    public int nanos_;
    /* access modifiers changed from: private */
    public int seconds_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements TimeOfDayOrBuilder {
        private int hours_;
        private int minutes_;
        private int nanos_;
        private int seconds_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeOfDay.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            TimeOfDay.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.hours_ = 0;
            this.minutes_ = 0;
            this.seconds_ = 0;
            this.nanos_ = 0;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
        }

        public TimeOfDay getDefaultInstanceForType() {
            return TimeOfDay.getDefaultInstance();
        }

        public TimeOfDay build() {
            TimeOfDay buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public TimeOfDay buildPartial() {
            TimeOfDay timeOfDay = new TimeOfDay((com.google.protobuf.GeneratedMessageV3.Builder) this);
            timeOfDay.hours_ = this.hours_;
            timeOfDay.minutes_ = this.minutes_;
            timeOfDay.seconds_ = this.seconds_;
            timeOfDay.nanos_ = this.nanos_;
            onBuilt();
            return timeOfDay;
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
            if (message instanceof TimeOfDay) {
                return mergeFrom((TimeOfDay) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TimeOfDay timeOfDay) {
            if (timeOfDay == TimeOfDay.getDefaultInstance()) {
                return this;
            }
            if (timeOfDay.getHours() != 0) {
                setHours(timeOfDay.getHours());
            }
            if (timeOfDay.getMinutes() != 0) {
                setMinutes(timeOfDay.getMinutes());
            }
            if (timeOfDay.getSeconds() != 0) {
                setSeconds(timeOfDay.getSeconds());
            }
            if (timeOfDay.getNanos() != 0) {
                setNanos(timeOfDay.getNanos());
            }
            mergeUnknownFields(timeOfDay.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.type.TimeOfDay.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.TimeOfDay.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.type.TimeOfDay r3 = (com.google.type.TimeOfDay) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.type.TimeOfDay r4 = (com.google.type.TimeOfDay) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.TimeOfDay.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.TimeOfDay$Builder");
        }

        public int getHours() {
            return this.hours_;
        }

        public Builder setHours(int i) {
            this.hours_ = i;
            onChanged();
            return this;
        }

        public Builder clearHours() {
            this.hours_ = 0;
            onChanged();
            return this;
        }

        public int getMinutes() {
            return this.minutes_;
        }

        public Builder setMinutes(int i) {
            this.minutes_ = i;
            onChanged();
            return this;
        }

        public Builder clearMinutes() {
            this.minutes_ = 0;
            onChanged();
            return this;
        }

        public int getSeconds() {
            return this.seconds_;
        }

        public Builder setSeconds(int i) {
            this.seconds_ = i;
            onChanged();
            return this;
        }

        public Builder clearSeconds() {
            this.seconds_ = 0;
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

    private TimeOfDay(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private TimeOfDay() {
        this.memoizedIsInitialized = -1;
        this.hours_ = 0;
        this.minutes_ = 0;
        this.seconds_ = 0;
        this.nanos_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private TimeOfDay(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 8) {
                        this.hours_ = codedInputStream.readInt32();
                    } else if (readTag == 16) {
                        this.minutes_ = codedInputStream.readInt32();
                    } else if (readTag == 24) {
                        this.seconds_ = codedInputStream.readInt32();
                    } else if (readTag == 32) {
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
        return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return TimeOfDayProto.internal_static_google_type_TimeOfDay_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeOfDay.class, Builder.class);
    }

    public int getHours() {
        return this.hours_;
    }

    public int getMinutes() {
        return this.minutes_;
    }

    public int getSeconds() {
        return this.seconds_;
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
        int i = this.hours_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        int i2 = this.minutes_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(2, i2);
        }
        int i3 = this.seconds_;
        if (i3 != 0) {
            codedOutputStream.writeInt32(3, i3);
        }
        int i4 = this.nanos_;
        if (i4 != 0) {
            codedOutputStream.writeInt32(4, i4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.hours_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
        }
        int i4 = this.minutes_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeInt32Size(2, i4);
        }
        int i5 = this.seconds_;
        if (i5 != 0) {
            i2 += CodedOutputStream.computeInt32Size(3, i5);
        }
        int i6 = this.nanos_;
        if (i6 != 0) {
            i2 += CodedOutputStream.computeInt32Size(4, i6);
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
        if (!(obj instanceof TimeOfDay)) {
            return super.equals(obj);
        }
        TimeOfDay timeOfDay = (TimeOfDay) obj;
        if (!((((getHours() == timeOfDay.getHours()) && getMinutes() == timeOfDay.getMinutes()) && getSeconds() == timeOfDay.getSeconds()) && getNanos() == timeOfDay.getNanos()) || !this.unknownFields.equals(timeOfDay.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getHours()) * 37) + 2) * 53) + getMinutes()) * 37) + 3) * 53) + getSeconds()) * 37) + 4) * 53) + getNanos()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static TimeOfDay parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TimeOfDay) PARSER.parseFrom(byteBuffer);
    }

    public static TimeOfDay parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TimeOfDay) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TimeOfDay) PARSER.parseFrom(byteString);
    }

    public static TimeOfDay parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TimeOfDay) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TimeOfDay) PARSER.parseFrom(bArr);
    }

    public static TimeOfDay parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TimeOfDay) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(InputStream inputStream) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TimeOfDay parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TimeOfDay parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TimeOfDay timeOfDay) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(timeOfDay);
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

    public static TimeOfDay getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TimeOfDay> parser() {
        return PARSER;
    }

    public Parser<TimeOfDay> getParserForType() {
        return PARSER;
    }

    public TimeOfDay getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
