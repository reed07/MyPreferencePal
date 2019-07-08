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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public final class SpanContext extends GeneratedMessageV3 implements SpanContextOrBuilder {
    private static final SpanContext DEFAULT_INSTANCE = new SpanContext();
    /* access modifiers changed from: private */
    public static final Parser<SpanContext> PARSER = new AbstractParser<SpanContext>() {
        public SpanContext parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SpanContext(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public MapField<String, String> baggage_;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public long spanId_;
    /* access modifiers changed from: private */
    public long traceId_;

    private static final class BaggageDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(Collector.internal_static_lightstep_collector_SpanContext_BaggageEntry_descriptor, FieldType.STRING, "", FieldType.STRING, "");

        private BaggageDefaultEntryHolder() {
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SpanContextOrBuilder {
        private MapField<String, String> baggage_;
        private int bitField0_;
        private long spanId_;
        private long traceId_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetBaggage();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMutableMapField(int i) {
            if (i == 3) {
                return internalGetMutableBaggage();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_SpanContext_fieldAccessorTable.ensureFieldAccessorsInitialized(SpanContext.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            SpanContext.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.traceId_ = 0;
            this.spanId_ = 0;
            internalGetMutableBaggage().clear();
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_SpanContext_descriptor;
        }

        public SpanContext getDefaultInstanceForType() {
            return SpanContext.getDefaultInstance();
        }

        public SpanContext build() {
            SpanContext buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public SpanContext buildPartial() {
            SpanContext spanContext = new SpanContext((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            spanContext.traceId_ = this.traceId_;
            spanContext.spanId_ = this.spanId_;
            spanContext.baggage_ = internalGetBaggage();
            spanContext.baggage_.makeImmutable();
            spanContext.bitField0_ = 0;
            onBuilt();
            return spanContext;
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
            if (message instanceof SpanContext) {
                return mergeFrom((SpanContext) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SpanContext spanContext) {
            if (spanContext == SpanContext.getDefaultInstance()) {
                return this;
            }
            if (spanContext.getTraceId() != 0) {
                setTraceId(spanContext.getTraceId());
            }
            if (spanContext.getSpanId() != 0) {
                setSpanId(spanContext.getSpanId());
            }
            internalGetMutableBaggage().mergeFrom(spanContext.internalGetBaggage());
            mergeUnknownFields(spanContext.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.SpanContext.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.SpanContext.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.SpanContext r3 = (com.lightstep.tracer.grpc.SpanContext) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.SpanContext r4 = (com.lightstep.tracer.grpc.SpanContext) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.SpanContext.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.SpanContext$Builder");
        }

        public Builder setTraceId(long j) {
            this.traceId_ = j;
            onChanged();
            return this;
        }

        public Builder setSpanId(long j) {
            this.spanId_ = j;
            onChanged();
            return this;
        }

        private MapField<String, String> internalGetBaggage() {
            MapField<String, String> mapField = this.baggage_;
            return mapField == null ? MapField.emptyMapField(BaggageDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableBaggage() {
            onChanged();
            if (this.baggage_ == null) {
                this.baggage_ = MapField.newMapField(BaggageDefaultEntryHolder.defaultEntry);
            }
            if (!this.baggage_.isMutable()) {
                this.baggage_ = this.baggage_.copy();
            }
            return this.baggage_;
        }

        public Builder putAllBaggage(Map<String, String> map) {
            internalGetMutableBaggage().getMutableMap().putAll(map);
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private SpanContext(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private SpanContext() {
        this.memoizedIsInitialized = -1;
        this.traceId_ = 0;
        this.spanId_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SpanContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.traceId_ = codedInputStream.readUInt64();
                    } else if (readTag == 16) {
                        this.spanId_ = codedInputStream.readUInt64();
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.baggage_ = MapField.newMapField(BaggageDefaultEntryHolder.defaultEntry);
                            z2 |= true;
                        }
                        MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(BaggageDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                        this.baggage_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return Collector.internal_static_lightstep_collector_SpanContext_descriptor;
    }

    /* access modifiers changed from: protected */
    public MapField internalGetMapField(int i) {
        if (i == 3) {
            return internalGetBaggage();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid map field number: ");
        sb.append(i);
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_SpanContext_fieldAccessorTable.ensureFieldAccessorsInitialized(SpanContext.class, Builder.class);
    }

    public long getTraceId() {
        return this.traceId_;
    }

    public long getSpanId() {
        return this.spanId_;
    }

    /* access modifiers changed from: private */
    public MapField<String, String> internalGetBaggage() {
        MapField<String, String> mapField = this.baggage_;
        return mapField == null ? MapField.emptyMapField(BaggageDefaultEntryHolder.defaultEntry) : mapField;
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
        long j = this.traceId_;
        if (j != 0) {
            codedOutputStream.writeUInt64(1, j);
        }
        long j2 = this.spanId_;
        if (j2 != 0) {
            codedOutputStream.writeUInt64(2, j2);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetBaggage(), BaggageDefaultEntryHolder.defaultEntry, 3);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        long j = this.traceId_;
        if (j != 0) {
            i2 = 0 + CodedOutputStream.computeUInt64Size(1, j);
        }
        long j2 = this.spanId_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeUInt64Size(2, j2);
        }
        for (Entry entry : internalGetBaggage().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(3, BaggageDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof SpanContext)) {
            return super.equals(obj);
        }
        SpanContext spanContext = (SpanContext) obj;
        if (!((((getTraceId() > spanContext.getTraceId() ? 1 : (getTraceId() == spanContext.getTraceId() ? 0 : -1)) == 0) && (getSpanId() > spanContext.getSpanId() ? 1 : (getSpanId() == spanContext.getSpanId() ? 0 : -1)) == 0) && internalGetBaggage().equals(spanContext.internalGetBaggage())) || !this.unknownFields.equals(spanContext.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getTraceId())) * 37) + 2) * 53) + Internal.hashLong(getSpanId());
        if (!internalGetBaggage().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 3) * 53) + internalGetBaggage().hashCode();
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

    public static Builder newBuilder(SpanContext spanContext) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(spanContext);
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

    public static SpanContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SpanContext> parser() {
        return PARSER;
    }

    public Parser<SpanContext> getParserForType() {
        return PARSER;
    }

    public SpanContext getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
