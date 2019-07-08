package com.lightstep.tracer.grpc;

import com.google.protobuf.AbstractParser;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Log extends GeneratedMessageV3 implements LogOrBuilder {
    private static final Log DEFAULT_INSTANCE = new Log();
    /* access modifiers changed from: private */
    public static final Parser<Log> PARSER = new AbstractParser<Log>() {
        public Log parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Log(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public List<KeyValue> fields_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Timestamp timestamp_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LogOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> fieldsBuilder_;
        private List<KeyValue> fields_;
        private SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
        private Timestamp timestamp_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_Log_fieldAccessorTable.ensureFieldAccessorsInitialized(Log.class, Builder.class);
        }

        private Builder() {
            this.timestamp_ = null;
            this.fields_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.timestamp_ = null;
            this.fields_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Log.alwaysUseFieldBuilders) {
                getFieldsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_Log_descriptor;
        }

        public Log getDefaultInstanceForType() {
            return Log.getDefaultInstance();
        }

        public Log build() {
            Log buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Log buildPartial() {
            Log log = new Log((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                log.timestamp_ = this.timestamp_;
            } else {
                log.timestamp_ = (Timestamp) singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.fields_ = Collections.unmodifiableList(this.fields_);
                    this.bitField0_ &= -3;
                }
                log.fields_ = this.fields_;
            } else {
                log.fields_ = repeatedFieldBuilderV3.build();
            }
            log.bitField0_ = 0;
            onBuilt();
            return log;
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
            if (message instanceof Log) {
                return mergeFrom((Log) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Log log) {
            if (log == Log.getDefaultInstance()) {
                return this;
            }
            if (log.hasTimestamp()) {
                mergeTimestamp(log.getTimestamp());
            }
            if (this.fieldsBuilder_ == null) {
                if (!log.fields_.isEmpty()) {
                    if (this.fields_.isEmpty()) {
                        this.fields_ = log.fields_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureFieldsIsMutable();
                        this.fields_.addAll(log.fields_);
                    }
                    onChanged();
                }
            } else if (!log.fields_.isEmpty()) {
                if (this.fieldsBuilder_.isEmpty()) {
                    this.fieldsBuilder_.dispose();
                    RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.fieldsBuilder_ = null;
                    this.fields_ = log.fields_;
                    this.bitField0_ &= -3;
                    if (Log.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getFieldsFieldBuilder();
                    }
                    this.fieldsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.fieldsBuilder_.addAllMessages(log.fields_);
                }
            }
            mergeUnknownFields(log.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.Log.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.Log.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.Log r3 = (com.lightstep.tracer.grpc.Log) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.Log r4 = (com.lightstep.tracer.grpc.Log) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.Log.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.Log$Builder");
        }

        public Builder setTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(timestamp);
            } else if (timestamp != null) {
                this.timestamp_ = timestamp;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder mergeTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.timestamp_;
                if (timestamp2 != null) {
                    this.timestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.timestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        private void ensureFieldsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.fields_ = new ArrayList(this.fields_);
                this.bitField0_ |= 2;
            }
        }

        public Builder addFields(KeyValue keyValue) {
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(keyValue);
            } else if (keyValue != null) {
                ensureFieldsIsMutable();
                this.fields_.add(keyValue);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        private RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> getFieldsFieldBuilder() {
            if (this.fieldsBuilder_ == null) {
                this.fieldsBuilder_ = new RepeatedFieldBuilderV3<>(this.fields_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.fields_ = null;
            }
            return this.fieldsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Log(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Log() {
        this.memoizedIsInitialized = -1;
        this.fields_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Log(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        com.google.protobuf.Timestamp.Builder builder = null;
                        if (this.timestamp_ != null) {
                            builder = this.timestamp_.toBuilder();
                        }
                        this.timestamp_ = (Timestamp) codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.timestamp_);
                            this.timestamp_ = builder.buildPartial();
                        }
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.fields_ = new ArrayList();
                            z2 |= true;
                        }
                        this.fields_.add(codedInputStream.readMessage(KeyValue.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.fields_ = Collections.unmodifiableList(this.fields_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.fields_ = Collections.unmodifiableList(this.fields_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return Collector.internal_static_lightstep_collector_Log_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_Log_fieldAccessorTable.ensureFieldAccessorsInitialized(Log.class, Builder.class);
    }

    public boolean hasTimestamp() {
        return this.timestamp_ != null;
    }

    public Timestamp getTimestamp() {
        Timestamp timestamp = this.timestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    public List<KeyValue> getFieldsList() {
        return this.fields_;
    }

    public int getFieldsCount() {
        return this.fields_.size();
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
        if (this.timestamp_ != null) {
            codedOutputStream.writeMessage(1, getTimestamp());
        }
        for (int i = 0; i < this.fields_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.fields_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.timestamp_ != null ? CodedOutputStream.computeMessageSize(1, getTimestamp()) + 0 : 0;
        for (int i2 = 0; i2 < this.fields_.size(); i2++) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.fields_.get(i2));
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Log)) {
            return super.equals(obj);
        }
        Log log = (Log) obj;
        boolean z2 = hasTimestamp() == log.hasTimestamp();
        if (hasTimestamp()) {
            z2 = z2 && getTimestamp().equals(log.getTimestamp());
        }
        if (!(z2 && getFieldsList().equals(log.getFieldsList())) || !this.unknownFields.equals(log.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasTimestamp()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getTimestamp().hashCode();
        }
        if (getFieldsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getFieldsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
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

    public static Log getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Log> parser() {
        return PARSER;
    }

    public Parser<Log> getParserForType() {
        return PARSER;
    }

    public Log getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
