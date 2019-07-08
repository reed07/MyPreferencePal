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

public final class InternalMetrics extends GeneratedMessageV3 implements InternalMetricsOrBuilder {
    private static final InternalMetrics DEFAULT_INSTANCE = new InternalMetrics();
    /* access modifiers changed from: private */
    public static final Parser<InternalMetrics> PARSER = new AbstractParser<InternalMetrics>() {
        public InternalMetrics parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new InternalMetrics(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public List<MetricsSample> counts_;
    /* access modifiers changed from: private */
    public long durationMicros_;
    /* access modifiers changed from: private */
    public List<MetricsSample> gauges_;
    /* access modifiers changed from: private */
    public List<Log> logs_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Timestamp startTimestamp_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements InternalMetricsOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> countsBuilder_;
        private List<MetricsSample> counts_;
        private long durationMicros_;
        private RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> gaugesBuilder_;
        private List<MetricsSample> gauges_;
        private RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> logsBuilder_;
        private List<Log> logs_;
        private SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> startTimestampBuilder_;
        private Timestamp startTimestamp_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_InternalMetrics_fieldAccessorTable.ensureFieldAccessorsInitialized(InternalMetrics.class, Builder.class);
        }

        private Builder() {
            this.startTimestamp_ = null;
            this.logs_ = Collections.emptyList();
            this.counts_ = Collections.emptyList();
            this.gauges_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.startTimestamp_ = null;
            this.logs_ = Collections.emptyList();
            this.counts_ = Collections.emptyList();
            this.gauges_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (InternalMetrics.alwaysUseFieldBuilders) {
                getLogsFieldBuilder();
                getCountsFieldBuilder();
                getGaugesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            if (this.startTimestampBuilder_ == null) {
                this.startTimestamp_ = null;
            } else {
                this.startTimestamp_ = null;
                this.startTimestampBuilder_ = null;
            }
            this.durationMicros_ = 0;
            RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> repeatedFieldBuilderV32 = this.countsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.counts_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> repeatedFieldBuilderV33 = this.gaugesBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.gauges_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_InternalMetrics_descriptor;
        }

        public InternalMetrics getDefaultInstanceForType() {
            return InternalMetrics.getDefaultInstance();
        }

        public InternalMetrics build() {
            InternalMetrics buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public InternalMetrics buildPartial() {
            InternalMetrics internalMetrics = new InternalMetrics((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                internalMetrics.startTimestamp_ = this.startTimestamp_;
            } else {
                internalMetrics.startTimestamp_ = (Timestamp) singleFieldBuilderV3.build();
            }
            internalMetrics.durationMicros_ = this.durationMicros_;
            RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.logs_ = Collections.unmodifiableList(this.logs_);
                    this.bitField0_ &= -5;
                }
                internalMetrics.logs_ = this.logs_;
            } else {
                internalMetrics.logs_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> repeatedFieldBuilderV32 = this.countsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.counts_ = Collections.unmodifiableList(this.counts_);
                    this.bitField0_ &= -9;
                }
                internalMetrics.counts_ = this.counts_;
            } else {
                internalMetrics.counts_ = repeatedFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> repeatedFieldBuilderV33 = this.gaugesBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                if ((this.bitField0_ & 16) == 16) {
                    this.gauges_ = Collections.unmodifiableList(this.gauges_);
                    this.bitField0_ &= -17;
                }
                internalMetrics.gauges_ = this.gauges_;
            } else {
                internalMetrics.gauges_ = repeatedFieldBuilderV33.build();
            }
            internalMetrics.bitField0_ = 0;
            onBuilt();
            return internalMetrics;
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
            if (message instanceof InternalMetrics) {
                return mergeFrom((InternalMetrics) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(InternalMetrics internalMetrics) {
            if (internalMetrics == InternalMetrics.getDefaultInstance()) {
                return this;
            }
            if (internalMetrics.hasStartTimestamp()) {
                mergeStartTimestamp(internalMetrics.getStartTimestamp());
            }
            if (internalMetrics.getDurationMicros() != 0) {
                setDurationMicros(internalMetrics.getDurationMicros());
            }
            RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.logsBuilder_ == null) {
                if (!internalMetrics.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = internalMetrics.logs_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureLogsIsMutable();
                        this.logs_.addAll(internalMetrics.logs_);
                    }
                    onChanged();
                }
            } else if (!internalMetrics.logs_.isEmpty()) {
                if (this.logsBuilder_.isEmpty()) {
                    this.logsBuilder_.dispose();
                    this.logsBuilder_ = null;
                    this.logs_ = internalMetrics.logs_;
                    this.bitField0_ &= -5;
                    this.logsBuilder_ = InternalMetrics.alwaysUseFieldBuilders ? getLogsFieldBuilder() : null;
                } else {
                    this.logsBuilder_.addAllMessages(internalMetrics.logs_);
                }
            }
            if (this.countsBuilder_ == null) {
                if (!internalMetrics.counts_.isEmpty()) {
                    if (this.counts_.isEmpty()) {
                        this.counts_ = internalMetrics.counts_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureCountsIsMutable();
                        this.counts_.addAll(internalMetrics.counts_);
                    }
                    onChanged();
                }
            } else if (!internalMetrics.counts_.isEmpty()) {
                if (this.countsBuilder_.isEmpty()) {
                    this.countsBuilder_.dispose();
                    this.countsBuilder_ = null;
                    this.counts_ = internalMetrics.counts_;
                    this.bitField0_ &= -9;
                    this.countsBuilder_ = InternalMetrics.alwaysUseFieldBuilders ? getCountsFieldBuilder() : null;
                } else {
                    this.countsBuilder_.addAllMessages(internalMetrics.counts_);
                }
            }
            if (this.gaugesBuilder_ == null) {
                if (!internalMetrics.gauges_.isEmpty()) {
                    if (this.gauges_.isEmpty()) {
                        this.gauges_ = internalMetrics.gauges_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureGaugesIsMutable();
                        this.gauges_.addAll(internalMetrics.gauges_);
                    }
                    onChanged();
                }
            } else if (!internalMetrics.gauges_.isEmpty()) {
                if (this.gaugesBuilder_.isEmpty()) {
                    this.gaugesBuilder_.dispose();
                    this.gaugesBuilder_ = null;
                    this.gauges_ = internalMetrics.gauges_;
                    this.bitField0_ &= -17;
                    if (InternalMetrics.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getGaugesFieldBuilder();
                    }
                    this.gaugesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.gaugesBuilder_.addAllMessages(internalMetrics.gauges_);
                }
            }
            mergeUnknownFields(internalMetrics.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.InternalMetrics.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.InternalMetrics.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.InternalMetrics r3 = (com.lightstep.tracer.grpc.InternalMetrics) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.InternalMetrics r4 = (com.lightstep.tracer.grpc.InternalMetrics) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.InternalMetrics.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.InternalMetrics$Builder");
        }

        public Builder mergeStartTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.startTimestamp_;
                if (timestamp2 != null) {
                    this.startTimestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.startTimestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder setDurationMicros(long j) {
            this.durationMicros_ = j;
            onChanged();
            return this;
        }

        private void ensureLogsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.logs_ = new ArrayList(this.logs_);
                this.bitField0_ |= 4;
            }
        }

        private RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> getLogsFieldBuilder() {
            if (this.logsBuilder_ == null) {
                this.logsBuilder_ = new RepeatedFieldBuilderV3<>(this.logs_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.logs_ = null;
            }
            return this.logsBuilder_;
        }

        private void ensureCountsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.counts_ = new ArrayList(this.counts_);
                this.bitField0_ |= 8;
            }
        }

        public Builder addCounts(MetricsSample metricsSample) {
            RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> repeatedFieldBuilderV3 = this.countsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(metricsSample);
            } else if (metricsSample != null) {
                ensureCountsIsMutable();
                this.counts_.add(metricsSample);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        private RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> getCountsFieldBuilder() {
            if (this.countsBuilder_ == null) {
                this.countsBuilder_ = new RepeatedFieldBuilderV3<>(this.counts_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                this.counts_ = null;
            }
            return this.countsBuilder_;
        }

        private void ensureGaugesIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.gauges_ = new ArrayList(this.gauges_);
                this.bitField0_ |= 16;
            }
        }

        private RepeatedFieldBuilderV3<MetricsSample, com.lightstep.tracer.grpc.MetricsSample.Builder, MetricsSampleOrBuilder> getGaugesFieldBuilder() {
            if (this.gaugesBuilder_ == null) {
                this.gaugesBuilder_ = new RepeatedFieldBuilderV3<>(this.gauges_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                this.gauges_ = null;
            }
            return this.gaugesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private InternalMetrics(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private InternalMetrics() {
        this.memoizedIsInitialized = -1;
        this.durationMicros_ = 0;
        this.logs_ = Collections.emptyList();
        this.counts_ = Collections.emptyList();
        this.gauges_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private InternalMetrics(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (this.startTimestamp_ != null) {
                            builder = this.startTimestamp_.toBuilder();
                        }
                        this.startTimestamp_ = (Timestamp) codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.startTimestamp_);
                            this.startTimestamp_ = builder.buildPartial();
                        }
                    } else if (readTag == 16) {
                        this.durationMicros_ = codedInputStream.readUInt64();
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.logs_ = new ArrayList();
                            z2 |= true;
                        }
                        this.logs_.add(codedInputStream.readMessage(Log.parser(), extensionRegistryLite));
                    } else if (readTag == 34) {
                        if (!(z2 & true)) {
                            this.counts_ = new ArrayList();
                            z2 |= true;
                        }
                        this.counts_.add(codedInputStream.readMessage(MetricsSample.parser(), extensionRegistryLite));
                    } else if (readTag == 42) {
                        if (!(z2 & true)) {
                            this.gauges_ = new ArrayList();
                            z2 |= true;
                        }
                        this.gauges_.add(codedInputStream.readMessage(MetricsSample.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.logs_ = Collections.unmodifiableList(this.logs_);
                    }
                    if (z2 & true) {
                        this.counts_ = Collections.unmodifiableList(this.counts_);
                    }
                    if (z2 & true) {
                        this.gauges_ = Collections.unmodifiableList(this.gauges_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.logs_ = Collections.unmodifiableList(this.logs_);
            }
            if (z2 & true) {
                this.counts_ = Collections.unmodifiableList(this.counts_);
            }
            if (z2 & true) {
                this.gauges_ = Collections.unmodifiableList(this.gauges_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return Collector.internal_static_lightstep_collector_InternalMetrics_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_InternalMetrics_fieldAccessorTable.ensureFieldAccessorsInitialized(InternalMetrics.class, Builder.class);
    }

    public boolean hasStartTimestamp() {
        return this.startTimestamp_ != null;
    }

    public Timestamp getStartTimestamp() {
        Timestamp timestamp = this.startTimestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    public long getDurationMicros() {
        return this.durationMicros_;
    }

    public List<Log> getLogsList() {
        return this.logs_;
    }

    public int getLogsCount() {
        return this.logs_.size();
    }

    public List<MetricsSample> getCountsList() {
        return this.counts_;
    }

    public int getCountsCount() {
        return this.counts_.size();
    }

    public List<MetricsSample> getGaugesList() {
        return this.gauges_;
    }

    public int getGaugesCount() {
        return this.gauges_.size();
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
        if (this.startTimestamp_ != null) {
            codedOutputStream.writeMessage(1, getStartTimestamp());
        }
        long j = this.durationMicros_;
        if (j != 0) {
            codedOutputStream.writeUInt64(2, j);
        }
        for (int i = 0; i < this.logs_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.logs_.get(i));
        }
        for (int i2 = 0; i2 < this.counts_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.counts_.get(i2));
        }
        for (int i3 = 0; i3 < this.gauges_.size(); i3++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.gauges_.get(i3));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.startTimestamp_ != null ? CodedOutputStream.computeMessageSize(1, getStartTimestamp()) + 0 : 0;
        long j = this.durationMicros_;
        if (j != 0) {
            computeMessageSize += CodedOutputStream.computeUInt64Size(2, j);
        }
        int i2 = computeMessageSize;
        for (int i3 = 0; i3 < this.logs_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.logs_.get(i3));
        }
        for (int i4 = 0; i4 < this.counts_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.counts_.get(i4));
        }
        for (int i5 = 0; i5 < this.gauges_.size(); i5++) {
            i2 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.gauges_.get(i5));
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
        if (!(obj instanceof InternalMetrics)) {
            return super.equals(obj);
        }
        InternalMetrics internalMetrics = (InternalMetrics) obj;
        boolean z2 = hasStartTimestamp() == internalMetrics.hasStartTimestamp();
        if (hasStartTimestamp()) {
            z2 = z2 && getStartTimestamp().equals(internalMetrics.getStartTimestamp());
        }
        if (!((((z2 && (getDurationMicros() > internalMetrics.getDurationMicros() ? 1 : (getDurationMicros() == internalMetrics.getDurationMicros() ? 0 : -1)) == 0) && getLogsList().equals(internalMetrics.getLogsList())) && getCountsList().equals(internalMetrics.getCountsList())) && getGaugesList().equals(internalMetrics.getGaugesList())) || !this.unknownFields.equals(internalMetrics.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasStartTimestamp()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getStartTimestamp().hashCode();
        }
        int hashLong = (((hashCode * 37) + 2) * 53) + Internal.hashLong(getDurationMicros());
        if (getLogsCount() > 0) {
            hashLong = (((hashLong * 37) + 3) * 53) + getLogsList().hashCode();
        }
        if (getCountsCount() > 0) {
            hashLong = (((hashLong * 37) + 4) * 53) + getCountsList().hashCode();
        }
        if (getGaugesCount() > 0) {
            hashLong = (((hashLong * 37) + 5) * 53) + getGaugesList().hashCode();
        }
        int hashCode2 = (hashLong * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(InternalMetrics internalMetrics) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(internalMetrics);
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

    public static InternalMetrics getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<InternalMetrics> parser() {
        return PARSER;
    }

    public Parser<InternalMetrics> getParserForType() {
        return PARSER;
    }

    public InternalMetrics getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
