package com.lightstep.tracer.grpc;

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

public final class Span extends GeneratedMessageV3 implements SpanOrBuilder {
    private static final Span DEFAULT_INSTANCE = new Span();
    /* access modifiers changed from: private */
    public static final Parser<Span> PARSER = new AbstractParser<Span>() {
        public Span parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Span(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public long durationMicros_;
    /* access modifiers changed from: private */
    public List<Log> logs_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object operationName_;
    /* access modifiers changed from: private */
    public List<Reference> references_;
    /* access modifiers changed from: private */
    public SpanContext spanContext_;
    /* access modifiers changed from: private */
    public Timestamp startTimestamp_;
    /* access modifiers changed from: private */
    public List<KeyValue> tags_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements SpanOrBuilder {
        private int bitField0_;
        private long durationMicros_;
        private RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> logsBuilder_;
        private List<Log> logs_;
        private Object operationName_;
        private RepeatedFieldBuilderV3<Reference, com.lightstep.tracer.grpc.Reference.Builder, ReferenceOrBuilder> referencesBuilder_;
        private List<Reference> references_;
        private SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> spanContextBuilder_;
        private SpanContext spanContext_;
        private SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> startTimestampBuilder_;
        private Timestamp startTimestamp_;
        private RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> tagsBuilder_;
        private List<KeyValue> tags_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_Span_fieldAccessorTable.ensureFieldAccessorsInitialized(Span.class, Builder.class);
        }

        private Builder() {
            this.spanContext_ = null;
            this.operationName_ = "";
            this.references_ = Collections.emptyList();
            this.startTimestamp_ = null;
            this.tags_ = Collections.emptyList();
            this.logs_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.spanContext_ = null;
            this.operationName_ = "";
            this.references_ = Collections.emptyList();
            this.startTimestamp_ = null;
            this.tags_ = Collections.emptyList();
            this.logs_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Span.alwaysUseFieldBuilders) {
                getReferencesFieldBuilder();
                getTagsFieldBuilder();
                getLogsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            if (this.spanContextBuilder_ == null) {
                this.spanContext_ = null;
            } else {
                this.spanContext_ = null;
                this.spanContextBuilder_ = null;
            }
            this.operationName_ = "";
            RepeatedFieldBuilderV3<Reference, com.lightstep.tracer.grpc.Reference.Builder, ReferenceOrBuilder> repeatedFieldBuilderV3 = this.referencesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.references_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.startTimestampBuilder_ == null) {
                this.startTimestamp_ = null;
            } else {
                this.startTimestamp_ = null;
                this.startTimestampBuilder_ = null;
            }
            this.durationMicros_ = 0;
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV32 = this.tagsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.tags_ = Collections.emptyList();
                this.bitField0_ &= -33;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> repeatedFieldBuilderV33 = this.logsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_Span_descriptor;
        }

        public Span getDefaultInstanceForType() {
            return Span.getDefaultInstance();
        }

        public Span build() {
            Span buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Span buildPartial() {
            Span span = new Span((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> singleFieldBuilderV3 = this.spanContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                span.spanContext_ = this.spanContext_;
            } else {
                span.spanContext_ = (SpanContext) singleFieldBuilderV3.build();
            }
            span.operationName_ = this.operationName_;
            RepeatedFieldBuilderV3<Reference, com.lightstep.tracer.grpc.Reference.Builder, ReferenceOrBuilder> repeatedFieldBuilderV3 = this.referencesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.references_ = Collections.unmodifiableList(this.references_);
                    this.bitField0_ &= -5;
                }
                span.references_ = this.references_;
            } else {
                span.references_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV32 = this.startTimestampBuilder_;
            if (singleFieldBuilderV32 == null) {
                span.startTimestamp_ = this.startTimestamp_;
            } else {
                span.startTimestamp_ = (Timestamp) singleFieldBuilderV32.build();
            }
            span.durationMicros_ = this.durationMicros_;
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV32 = this.tagsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 32) == 32) {
                    this.tags_ = Collections.unmodifiableList(this.tags_);
                    this.bitField0_ &= -33;
                }
                span.tags_ = this.tags_;
            } else {
                span.tags_ = repeatedFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> repeatedFieldBuilderV33 = this.logsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                if ((this.bitField0_ & 64) == 64) {
                    this.logs_ = Collections.unmodifiableList(this.logs_);
                    this.bitField0_ &= -65;
                }
                span.logs_ = this.logs_;
            } else {
                span.logs_ = repeatedFieldBuilderV33.build();
            }
            span.bitField0_ = 0;
            onBuilt();
            return span;
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
            if (message instanceof Span) {
                return mergeFrom((Span) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Span span) {
            if (span == Span.getDefaultInstance()) {
                return this;
            }
            if (span.hasSpanContext()) {
                mergeSpanContext(span.getSpanContext());
            }
            if (!span.getOperationName().isEmpty()) {
                this.operationName_ = span.operationName_;
                onChanged();
            }
            RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.referencesBuilder_ == null) {
                if (!span.references_.isEmpty()) {
                    if (this.references_.isEmpty()) {
                        this.references_ = span.references_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureReferencesIsMutable();
                        this.references_.addAll(span.references_);
                    }
                    onChanged();
                }
            } else if (!span.references_.isEmpty()) {
                if (this.referencesBuilder_.isEmpty()) {
                    this.referencesBuilder_.dispose();
                    this.referencesBuilder_ = null;
                    this.references_ = span.references_;
                    this.bitField0_ &= -5;
                    this.referencesBuilder_ = Span.alwaysUseFieldBuilders ? getReferencesFieldBuilder() : null;
                } else {
                    this.referencesBuilder_.addAllMessages(span.references_);
                }
            }
            if (span.hasStartTimestamp()) {
                mergeStartTimestamp(span.getStartTimestamp());
            }
            if (span.getDurationMicros() != 0) {
                setDurationMicros(span.getDurationMicros());
            }
            if (this.tagsBuilder_ == null) {
                if (!span.tags_.isEmpty()) {
                    if (this.tags_.isEmpty()) {
                        this.tags_ = span.tags_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureTagsIsMutable();
                        this.tags_.addAll(span.tags_);
                    }
                    onChanged();
                }
            } else if (!span.tags_.isEmpty()) {
                if (this.tagsBuilder_.isEmpty()) {
                    this.tagsBuilder_.dispose();
                    this.tagsBuilder_ = null;
                    this.tags_ = span.tags_;
                    this.bitField0_ &= -33;
                    this.tagsBuilder_ = Span.alwaysUseFieldBuilders ? getTagsFieldBuilder() : null;
                } else {
                    this.tagsBuilder_.addAllMessages(span.tags_);
                }
            }
            if (this.logsBuilder_ == null) {
                if (!span.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = span.logs_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureLogsIsMutable();
                        this.logs_.addAll(span.logs_);
                    }
                    onChanged();
                }
            } else if (!span.logs_.isEmpty()) {
                if (this.logsBuilder_.isEmpty()) {
                    this.logsBuilder_.dispose();
                    this.logsBuilder_ = null;
                    this.logs_ = span.logs_;
                    this.bitField0_ &= -65;
                    if (Span.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getLogsFieldBuilder();
                    }
                    this.logsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.logsBuilder_.addAllMessages(span.logs_);
                }
            }
            mergeUnknownFields(span.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.Span.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.Span.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.Span r3 = (com.lightstep.tracer.grpc.Span) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.Span r4 = (com.lightstep.tracer.grpc.Span) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.Span.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.Span$Builder");
        }

        public Builder setSpanContext(SpanContext spanContext) {
            SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> singleFieldBuilderV3 = this.spanContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(spanContext);
            } else if (spanContext != null) {
                this.spanContext_ = spanContext;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder mergeSpanContext(SpanContext spanContext) {
            SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> singleFieldBuilderV3 = this.spanContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                SpanContext spanContext2 = this.spanContext_;
                if (spanContext2 != null) {
                    this.spanContext_ = SpanContext.newBuilder(spanContext2).mergeFrom(spanContext).buildPartial();
                } else {
                    this.spanContext_ = spanContext;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(spanContext);
            }
            return this;
        }

        public Builder setOperationName(String str) {
            if (str != null) {
                this.operationName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureReferencesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.references_ = new ArrayList(this.references_);
                this.bitField0_ |= 4;
            }
        }

        public Builder addReferences(com.lightstep.tracer.grpc.Reference.Builder builder) {
            RepeatedFieldBuilderV3<Reference, com.lightstep.tracer.grpc.Reference.Builder, ReferenceOrBuilder> repeatedFieldBuilderV3 = this.referencesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureReferencesIsMutable();
                this.references_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        private RepeatedFieldBuilderV3<Reference, com.lightstep.tracer.grpc.Reference.Builder, ReferenceOrBuilder> getReferencesFieldBuilder() {
            if (this.referencesBuilder_ == null) {
                this.referencesBuilder_ = new RepeatedFieldBuilderV3<>(this.references_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.references_ = null;
            }
            return this.referencesBuilder_;
        }

        public Timestamp getStartTimestamp() {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Timestamp) singleFieldBuilderV3.getMessage();
            }
            Timestamp timestamp = this.startTimestamp_;
            if (timestamp == null) {
                timestamp = Timestamp.getDefaultInstance();
            }
            return timestamp;
        }

        public Builder setStartTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.startTimestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(timestamp);
            } else if (timestamp != null) {
                this.startTimestamp_ = timestamp;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
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

        private void ensureTagsIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.tags_ = new ArrayList(this.tags_);
                this.bitField0_ |= 32;
            }
        }

        public Builder addTags(com.lightstep.tracer.grpc.KeyValue.Builder builder) {
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                this.tags_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        private RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> getTagsFieldBuilder() {
            if (this.tagsBuilder_ == null) {
                this.tagsBuilder_ = new RepeatedFieldBuilderV3<>(this.tags_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                this.tags_ = null;
            }
            return this.tagsBuilder_;
        }

        private void ensureLogsIsMutable() {
            if ((this.bitField0_ & 64) != 64) {
                this.logs_ = new ArrayList(this.logs_);
                this.bitField0_ |= 64;
            }
        }

        public Builder addLogs(Log log) {
            RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(log);
            } else if (log != null) {
                ensureLogsIsMutable();
                this.logs_.add(log);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        private RepeatedFieldBuilderV3<Log, com.lightstep.tracer.grpc.Log.Builder, LogOrBuilder> getLogsFieldBuilder() {
            if (this.logsBuilder_ == null) {
                this.logsBuilder_ = new RepeatedFieldBuilderV3<>(this.logs_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                this.logs_ = null;
            }
            return this.logsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Span(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Span() {
        this.memoizedIsInitialized = -1;
        this.operationName_ = "";
        this.references_ = Collections.emptyList();
        this.durationMicros_ = 0;
        this.tags_ = Collections.emptyList();
        this.logs_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r9v1, types: [com.lightstep.tracer.grpc.SpanContext$Builder] */
    /* JADX WARNING: type inference failed for: r9v2, types: [com.lightstep.tracer.grpc.SpanContext$Builder] */
    /* JADX WARNING: type inference failed for: r9v3, types: [com.google.protobuf.Timestamp$Builder] */
    /* JADX WARNING: type inference failed for: r9v4, types: [com.google.protobuf.Timestamp$Builder] */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r9v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.protobuf.Timestamp$Builder, com.lightstep.tracer.grpc.SpanContext$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.lightstep.tracer.grpc.SpanContext$Builder, com.google.protobuf.Timestamp$Builder]
  mth insns count: 134
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Span(com.google.protobuf.CodedInputStream r11, com.google.protobuf.ExtensionRegistryLite r12) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r10 = this;
            r10.<init>()
            if (r12 == 0) goto L_0x015b
            com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
            r1 = 0
            r2 = 0
        L_0x000b:
            r3 = 64
            r4 = 32
            r5 = 4
            if (r1 != 0) goto L_0x012d
            int r6 = r11.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r7 = 1
            if (r6 == 0) goto L_0x00e9
            r8 = 10
            r9 = 0
            if (r6 == r8) goto L_0x00c4
            r8 = 18
            if (r6 == r8) goto L_0x00bc
            r8 = 26
            if (r6 == r8) goto L_0x00a0
            r8 = 34
            if (r6 == r8) goto L_0x007b
            r8 = 40
            if (r6 == r8) goto L_0x0074
            r8 = 50
            if (r6 == r8) goto L_0x0059
            r8 = 58
            if (r6 == r8) goto L_0x003e
            boolean r3 = r10.parseUnknownFieldProto3(r11, r0, r12, r6)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            if (r3 != 0) goto L_0x000b
            r1 = 1
            goto L_0x000b
        L_0x003e:
            r6 = r2 & 64
            if (r6 == r3) goto L_0x004b
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r6.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.logs_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r2 = r2 | 64
        L_0x004b:
            java.util.List<com.lightstep.tracer.grpc.Log> r6 = r10.logs_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.Parser r7 = com.lightstep.tracer.grpc.Log.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r6.add(r7)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x0059:
            r6 = r2 & 32
            if (r6 == r4) goto L_0x0066
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r6.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.tags_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r2 = r2 | 32
        L_0x0066:
            java.util.List<com.lightstep.tracer.grpc.KeyValue> r6 = r10.tags_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.Parser r7 = com.lightstep.tracer.grpc.KeyValue.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r6.add(r7)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x0074:
            long r6 = r11.readUInt64()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.durationMicros_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x007b:
            com.google.protobuf.Timestamp r6 = r10.startTimestamp_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            if (r6 == 0) goto L_0x0085
            com.google.protobuf.Timestamp r6 = r10.startTimestamp_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.Timestamp$Builder r9 = r6.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
        L_0x0085:
            com.google.protobuf.Parser r6 = com.google.protobuf.Timestamp.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.MessageLite r6 = r11.readMessage(r6, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.Timestamp r6 = (com.google.protobuf.Timestamp) r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.startTimestamp_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            if (r9 == 0) goto L_0x000b
            com.google.protobuf.Timestamp r6 = r10.startTimestamp_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r9.mergeFrom(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.Timestamp r6 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.startTimestamp_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x00a0:
            r6 = r2 & 4
            if (r6 == r5) goto L_0x00ad
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r6.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.references_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r2 = r2 | 4
        L_0x00ad:
            java.util.List<com.lightstep.tracer.grpc.Reference> r6 = r10.references_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.Parser r7 = com.lightstep.tracer.grpc.Reference.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.MessageLite r7 = r11.readMessage(r7, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r6.add(r7)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x00bc:
            java.lang.String r6 = r11.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.operationName_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x00c4:
            com.lightstep.tracer.grpc.SpanContext r6 = r10.spanContext_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            if (r6 == 0) goto L_0x00ce
            com.lightstep.tracer.grpc.SpanContext r6 = r10.spanContext_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.lightstep.tracer.grpc.SpanContext$Builder r9 = r6.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
        L_0x00ce:
            com.google.protobuf.Parser r6 = com.lightstep.tracer.grpc.SpanContext.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.google.protobuf.MessageLite r6 = r11.readMessage(r6, r12)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.lightstep.tracer.grpc.SpanContext r6 = (com.lightstep.tracer.grpc.SpanContext) r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.spanContext_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            if (r9 == 0) goto L_0x000b
            com.lightstep.tracer.grpc.SpanContext r6 = r10.spanContext_     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r9.mergeFrom(r6)     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            com.lightstep.tracer.grpc.SpanContext r6 = r9.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            r10.spanContext_ = r6     // Catch:{ InvalidProtocolBufferException -> 0x00f9, IOException -> 0x00ee }
            goto L_0x000b
        L_0x00e9:
            r1 = 1
            goto L_0x000b
        L_0x00ec:
            r11 = move-exception
            goto L_0x00ff
        L_0x00ee:
            r11 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r12 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00ec }
            r12.<init>(r11)     // Catch:{ all -> 0x00ec }
            com.google.protobuf.InvalidProtocolBufferException r11 = r12.setUnfinishedMessage(r10)     // Catch:{ all -> 0x00ec }
            throw r11     // Catch:{ all -> 0x00ec }
        L_0x00f9:
            r11 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r11 = r11.setUnfinishedMessage(r10)     // Catch:{ all -> 0x00ec }
            throw r11     // Catch:{ all -> 0x00ec }
        L_0x00ff:
            r12 = r2 & 4
            if (r12 != r5) goto L_0x010b
            java.util.List<com.lightstep.tracer.grpc.Reference> r12 = r10.references_
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.references_ = r12
        L_0x010b:
            r12 = r2 & 32
            if (r12 != r4) goto L_0x0117
            java.util.List<com.lightstep.tracer.grpc.KeyValue> r12 = r10.tags_
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.tags_ = r12
        L_0x0117:
            r12 = r2 & 64
            if (r12 != r3) goto L_0x0123
            java.util.List<com.lightstep.tracer.grpc.Log> r12 = r10.logs_
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.logs_ = r12
        L_0x0123:
            com.google.protobuf.UnknownFieldSet r12 = r0.build()
            r10.unknownFields = r12
            r10.makeExtensionsImmutable()
            throw r11
        L_0x012d:
            r11 = r2 & 4
            if (r11 != r5) goto L_0x0139
            java.util.List<com.lightstep.tracer.grpc.Reference> r11 = r10.references_
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.references_ = r11
        L_0x0139:
            r11 = r2 & 32
            if (r11 != r4) goto L_0x0145
            java.util.List<com.lightstep.tracer.grpc.KeyValue> r11 = r10.tags_
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.tags_ = r11
        L_0x0145:
            r11 = r2 & 64
            if (r11 != r3) goto L_0x0151
            java.util.List<com.lightstep.tracer.grpc.Log> r11 = r10.logs_
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.logs_ = r11
        L_0x0151:
            com.google.protobuf.UnknownFieldSet r11 = r0.build()
            r10.unknownFields = r11
            r10.makeExtensionsImmutable()
            return
        L_0x015b:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.Span.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return Collector.internal_static_lightstep_collector_Span_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_Span_fieldAccessorTable.ensureFieldAccessorsInitialized(Span.class, Builder.class);
    }

    public boolean hasSpanContext() {
        return this.spanContext_ != null;
    }

    public SpanContext getSpanContext() {
        SpanContext spanContext = this.spanContext_;
        return spanContext == null ? SpanContext.getDefaultInstance() : spanContext;
    }

    public String getOperationName() {
        Object obj = this.operationName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.operationName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getOperationNameBytes() {
        Object obj = this.operationName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.operationName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<Reference> getReferencesList() {
        return this.references_;
    }

    public int getReferencesCount() {
        return this.references_.size();
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

    public List<KeyValue> getTagsList() {
        return this.tags_;
    }

    public int getTagsCount() {
        return this.tags_.size();
    }

    public List<Log> getLogsList() {
        return this.logs_;
    }

    public int getLogsCount() {
        return this.logs_.size();
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
        if (this.spanContext_ != null) {
            codedOutputStream.writeMessage(1, getSpanContext());
        }
        if (!getOperationNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.operationName_);
        }
        for (int i = 0; i < this.references_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.references_.get(i));
        }
        if (this.startTimestamp_ != null) {
            codedOutputStream.writeMessage(4, getStartTimestamp());
        }
        long j = this.durationMicros_;
        if (j != 0) {
            codedOutputStream.writeUInt64(5, j);
        }
        for (int i2 = 0; i2 < this.tags_.size(); i2++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.tags_.get(i2));
        }
        for (int i3 = 0; i3 < this.logs_.size(); i3++) {
            codedOutputStream.writeMessage(7, (MessageLite) this.logs_.get(i3));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.spanContext_ != null ? CodedOutputStream.computeMessageSize(1, getSpanContext()) + 0 : 0;
        if (!getOperationNameBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.operationName_);
        }
        int i2 = computeMessageSize;
        for (int i3 = 0; i3 < this.references_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.references_.get(i3));
        }
        if (this.startTimestamp_ != null) {
            i2 += CodedOutputStream.computeMessageSize(4, getStartTimestamp());
        }
        long j = this.durationMicros_;
        if (j != 0) {
            i2 += CodedOutputStream.computeUInt64Size(5, j);
        }
        for (int i4 = 0; i4 < this.tags_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.tags_.get(i4));
        }
        for (int i5 = 0; i5 < this.logs_.size(); i5++) {
            i2 += CodedOutputStream.computeMessageSize(7, (MessageLite) this.logs_.get(i5));
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
        if (!(obj instanceof Span)) {
            return super.equals(obj);
        }
        Span span = (Span) obj;
        boolean z2 = hasSpanContext() == span.hasSpanContext();
        if (hasSpanContext()) {
            z2 = z2 && getSpanContext().equals(span.getSpanContext());
        }
        boolean z3 = ((z2 && getOperationName().equals(span.getOperationName())) && getReferencesList().equals(span.getReferencesList())) && hasStartTimestamp() == span.hasStartTimestamp();
        if (hasStartTimestamp()) {
            z3 = z3 && getStartTimestamp().equals(span.getStartTimestamp());
        }
        if (!(((z3 && (getDurationMicros() > span.getDurationMicros() ? 1 : (getDurationMicros() == span.getDurationMicros() ? 0 : -1)) == 0) && getTagsList().equals(span.getTagsList())) && getLogsList().equals(span.getLogsList())) || !this.unknownFields.equals(span.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasSpanContext()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSpanContext().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 2) * 53) + getOperationName().hashCode();
        if (getReferencesCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 3) * 53) + getReferencesList().hashCode();
        }
        if (hasStartTimestamp()) {
            hashCode2 = (((hashCode2 * 37) + 4) * 53) + getStartTimestamp().hashCode();
        }
        int hashLong = (((hashCode2 * 37) + 5) * 53) + Internal.hashLong(getDurationMicros());
        if (getTagsCount() > 0) {
            hashLong = (((hashLong * 37) + 6) * 53) + getTagsList().hashCode();
        }
        if (getLogsCount() > 0) {
            hashLong = (((hashLong * 37) + 7) * 53) + getLogsList().hashCode();
        }
        int hashCode3 = (hashLong * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
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

    public static Span getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Span> parser() {
        return PARSER;
    }

    public Parser<Span> getParserForType() {
        return PARSER;
    }

    public Span getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
