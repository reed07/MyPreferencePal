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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ReportRequest extends GeneratedMessageV3 implements ReportRequestOrBuilder {
    private static final ReportRequest DEFAULT_INSTANCE = new ReportRequest();
    /* access modifiers changed from: private */
    public static final Parser<ReportRequest> PARSER = new AbstractParser<ReportRequest>() {
        public ReportRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ReportRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public Auth auth_;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public InternalMetrics internalMetrics_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Reporter reporter_;
    /* access modifiers changed from: private */
    public List<Span> spans_;
    /* access modifiers changed from: private */
    public long timestampOffsetMicros_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ReportRequestOrBuilder {
        private SingleFieldBuilderV3<Auth, com.lightstep.tracer.grpc.Auth.Builder, AuthOrBuilder> authBuilder_;
        private Auth auth_;
        private int bitField0_;
        private SingleFieldBuilderV3<InternalMetrics, com.lightstep.tracer.grpc.InternalMetrics.Builder, InternalMetricsOrBuilder> internalMetricsBuilder_;
        private InternalMetrics internalMetrics_;
        private SingleFieldBuilderV3<Reporter, com.lightstep.tracer.grpc.Reporter.Builder, ReporterOrBuilder> reporterBuilder_;
        private Reporter reporter_;
        private RepeatedFieldBuilderV3<Span, com.lightstep.tracer.grpc.Span.Builder, SpanOrBuilder> spansBuilder_;
        private List<Span> spans_;
        private long timestampOffsetMicros_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_ReportRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ReportRequest.class, Builder.class);
        }

        private Builder() {
            this.reporter_ = null;
            this.auth_ = null;
            this.spans_ = Collections.emptyList();
            this.internalMetrics_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.reporter_ = null;
            this.auth_ = null;
            this.spans_ = Collections.emptyList();
            this.internalMetrics_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ReportRequest.alwaysUseFieldBuilders) {
                getSpansFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            if (this.reporterBuilder_ == null) {
                this.reporter_ = null;
            } else {
                this.reporter_ = null;
                this.reporterBuilder_ = null;
            }
            if (this.authBuilder_ == null) {
                this.auth_ = null;
            } else {
                this.auth_ = null;
                this.authBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Span, com.lightstep.tracer.grpc.Span.Builder, SpanOrBuilder> repeatedFieldBuilderV3 = this.spansBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.spans_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.timestampOffsetMicros_ = 0;
            if (this.internalMetricsBuilder_ == null) {
                this.internalMetrics_ = null;
            } else {
                this.internalMetrics_ = null;
                this.internalMetricsBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_ReportRequest_descriptor;
        }

        public ReportRequest getDefaultInstanceForType() {
            return ReportRequest.getDefaultInstance();
        }

        public ReportRequest build() {
            ReportRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ReportRequest buildPartial() {
            ReportRequest reportRequest = new ReportRequest((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Reporter, com.lightstep.tracer.grpc.Reporter.Builder, ReporterOrBuilder> singleFieldBuilderV3 = this.reporterBuilder_;
            if (singleFieldBuilderV3 == null) {
                reportRequest.reporter_ = this.reporter_;
            } else {
                reportRequest.reporter_ = (Reporter) singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Auth, com.lightstep.tracer.grpc.Auth.Builder, AuthOrBuilder> singleFieldBuilderV32 = this.authBuilder_;
            if (singleFieldBuilderV32 == null) {
                reportRequest.auth_ = this.auth_;
            } else {
                reportRequest.auth_ = (Auth) singleFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<Span, com.lightstep.tracer.grpc.Span.Builder, SpanOrBuilder> repeatedFieldBuilderV3 = this.spansBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.spans_ = Collections.unmodifiableList(this.spans_);
                    this.bitField0_ &= -5;
                }
                reportRequest.spans_ = this.spans_;
            } else {
                reportRequest.spans_ = repeatedFieldBuilderV3.build();
            }
            reportRequest.timestampOffsetMicros_ = this.timestampOffsetMicros_;
            SingleFieldBuilderV3<InternalMetrics, com.lightstep.tracer.grpc.InternalMetrics.Builder, InternalMetricsOrBuilder> singleFieldBuilderV33 = this.internalMetricsBuilder_;
            if (singleFieldBuilderV33 == null) {
                reportRequest.internalMetrics_ = this.internalMetrics_;
            } else {
                reportRequest.internalMetrics_ = (InternalMetrics) singleFieldBuilderV33.build();
            }
            reportRequest.bitField0_ = 0;
            onBuilt();
            return reportRequest;
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
            if (message instanceof ReportRequest) {
                return mergeFrom((ReportRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ReportRequest reportRequest) {
            if (reportRequest == ReportRequest.getDefaultInstance()) {
                return this;
            }
            if (reportRequest.hasReporter()) {
                mergeReporter(reportRequest.getReporter());
            }
            if (reportRequest.hasAuth()) {
                mergeAuth(reportRequest.getAuth());
            }
            if (this.spansBuilder_ == null) {
                if (!reportRequest.spans_.isEmpty()) {
                    if (this.spans_.isEmpty()) {
                        this.spans_ = reportRequest.spans_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureSpansIsMutable();
                        this.spans_.addAll(reportRequest.spans_);
                    }
                    onChanged();
                }
            } else if (!reportRequest.spans_.isEmpty()) {
                if (this.spansBuilder_.isEmpty()) {
                    this.spansBuilder_.dispose();
                    RepeatedFieldBuilderV3<Span, com.lightstep.tracer.grpc.Span.Builder, SpanOrBuilder> repeatedFieldBuilderV3 = null;
                    this.spansBuilder_ = null;
                    this.spans_ = reportRequest.spans_;
                    this.bitField0_ &= -5;
                    if (ReportRequest.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getSpansFieldBuilder();
                    }
                    this.spansBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.spansBuilder_.addAllMessages(reportRequest.spans_);
                }
            }
            if (reportRequest.getTimestampOffsetMicros() != 0) {
                setTimestampOffsetMicros(reportRequest.getTimestampOffsetMicros());
            }
            if (reportRequest.hasInternalMetrics()) {
                mergeInternalMetrics(reportRequest.getInternalMetrics());
            }
            mergeUnknownFields(reportRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.ReportRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.ReportRequest.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.ReportRequest r3 = (com.lightstep.tracer.grpc.ReportRequest) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.ReportRequest r4 = (com.lightstep.tracer.grpc.ReportRequest) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.ReportRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.ReportRequest$Builder");
        }

        public Builder setReporter(com.lightstep.tracer.grpc.Reporter.Builder builder) {
            SingleFieldBuilderV3<Reporter, com.lightstep.tracer.grpc.Reporter.Builder, ReporterOrBuilder> singleFieldBuilderV3 = this.reporterBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reporter_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeReporter(Reporter reporter) {
            SingleFieldBuilderV3<Reporter, com.lightstep.tracer.grpc.Reporter.Builder, ReporterOrBuilder> singleFieldBuilderV3 = this.reporterBuilder_;
            if (singleFieldBuilderV3 == null) {
                Reporter reporter2 = this.reporter_;
                if (reporter2 != null) {
                    this.reporter_ = Reporter.newBuilder(reporter2).mergeFrom(reporter).buildPartial();
                } else {
                    this.reporter_ = reporter;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(reporter);
            }
            return this;
        }

        public Builder setAuth(com.lightstep.tracer.grpc.Auth.Builder builder) {
            SingleFieldBuilderV3<Auth, com.lightstep.tracer.grpc.Auth.Builder, AuthOrBuilder> singleFieldBuilderV3 = this.authBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.auth_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAuth(Auth auth) {
            SingleFieldBuilderV3<Auth, com.lightstep.tracer.grpc.Auth.Builder, AuthOrBuilder> singleFieldBuilderV3 = this.authBuilder_;
            if (singleFieldBuilderV3 == null) {
                Auth auth2 = this.auth_;
                if (auth2 != null) {
                    this.auth_ = Auth.newBuilder(auth2).mergeFrom(auth).buildPartial();
                } else {
                    this.auth_ = auth;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(auth);
            }
            return this;
        }

        private void ensureSpansIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.spans_ = new ArrayList(this.spans_);
                this.bitField0_ |= 4;
            }
        }

        public Builder addAllSpans(Iterable<? extends Span> iterable) {
            RepeatedFieldBuilderV3<Span, com.lightstep.tracer.grpc.Span.Builder, SpanOrBuilder> repeatedFieldBuilderV3 = this.spansBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpansIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.spans_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        private RepeatedFieldBuilderV3<Span, com.lightstep.tracer.grpc.Span.Builder, SpanOrBuilder> getSpansFieldBuilder() {
            if (this.spansBuilder_ == null) {
                this.spansBuilder_ = new RepeatedFieldBuilderV3<>(this.spans_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.spans_ = null;
            }
            return this.spansBuilder_;
        }

        public Builder setTimestampOffsetMicros(long j) {
            this.timestampOffsetMicros_ = j;
            onChanged();
            return this;
        }

        public Builder setInternalMetrics(InternalMetrics internalMetrics) {
            SingleFieldBuilderV3<InternalMetrics, com.lightstep.tracer.grpc.InternalMetrics.Builder, InternalMetricsOrBuilder> singleFieldBuilderV3 = this.internalMetricsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(internalMetrics);
            } else if (internalMetrics != null) {
                this.internalMetrics_ = internalMetrics;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder mergeInternalMetrics(InternalMetrics internalMetrics) {
            SingleFieldBuilderV3<InternalMetrics, com.lightstep.tracer.grpc.InternalMetrics.Builder, InternalMetricsOrBuilder> singleFieldBuilderV3 = this.internalMetricsBuilder_;
            if (singleFieldBuilderV3 == null) {
                InternalMetrics internalMetrics2 = this.internalMetrics_;
                if (internalMetrics2 != null) {
                    this.internalMetrics_ = InternalMetrics.newBuilder(internalMetrics2).mergeFrom(internalMetrics).buildPartial();
                } else {
                    this.internalMetrics_ = internalMetrics;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(internalMetrics);
            }
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private ReportRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ReportRequest() {
        this.memoizedIsInitialized = -1;
        this.spans_ = Collections.emptyList();
        this.timestampOffsetMicros_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [com.lightstep.tracer.grpc.Reporter$Builder] */
    /* JADX WARNING: type inference failed for: r7v2, types: [com.lightstep.tracer.grpc.Reporter$Builder] */
    /* JADX WARNING: type inference failed for: r7v3, types: [com.lightstep.tracer.grpc.Auth$Builder] */
    /* JADX WARNING: type inference failed for: r7v4, types: [com.lightstep.tracer.grpc.Auth$Builder] */
    /* JADX WARNING: type inference failed for: r7v5, types: [com.lightstep.tracer.grpc.InternalMetrics$Builder] */
    /* JADX WARNING: type inference failed for: r7v6, types: [com.lightstep.tracer.grpc.InternalMetrics$Builder] */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.lightstep.tracer.grpc.Auth$Builder, com.lightstep.tracer.grpc.Reporter$Builder, com.lightstep.tracer.grpc.InternalMetrics$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.lightstep.tracer.grpc.Reporter$Builder, com.lightstep.tracer.grpc.Auth$Builder, com.lightstep.tracer.grpc.InternalMetrics$Builder]
  mth insns count: 104
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
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ReportRequest(com.google.protobuf.CodedInputStream r9, com.google.protobuf.ExtensionRegistryLite r10) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r8 = this;
            r8.<init>()
            if (r10 == 0) goto L_0x0104
            com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
            r1 = 0
            r2 = 0
        L_0x000b:
            r3 = 4
            if (r1 != 0) goto L_0x00ee
            int r4 = r9.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r5 = 1
            if (r4 == 0) goto L_0x00c2
            r6 = 10
            r7 = 0
            if (r4 == r6) goto L_0x009d
            r6 = 18
            if (r4 == r6) goto L_0x0078
            r6 = 26
            if (r4 == r6) goto L_0x005d
            r6 = 40
            if (r4 == r6) goto L_0x0056
            r6 = 50
            if (r4 == r6) goto L_0x0032
            boolean r3 = r8.parseUnknownFieldProto3(r9, r0, r10, r4)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r3 != 0) goto L_0x000b
            r1 = 1
            goto L_0x000b
        L_0x0032:
            com.lightstep.tracer.grpc.InternalMetrics r4 = r8.internalMetrics_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r4 == 0) goto L_0x003c
            com.lightstep.tracer.grpc.InternalMetrics r4 = r8.internalMetrics_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.InternalMetrics$Builder r7 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
        L_0x003c:
            com.google.protobuf.Parser r4 = com.lightstep.tracer.grpc.InternalMetrics.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.google.protobuf.MessageLite r4 = r9.readMessage(r4, r10)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.InternalMetrics r4 = (com.lightstep.tracer.grpc.InternalMetrics) r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.internalMetrics_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r7 == 0) goto L_0x000b
            com.lightstep.tracer.grpc.InternalMetrics r4 = r8.internalMetrics_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r7.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.InternalMetrics r4 = r7.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.internalMetrics_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            goto L_0x000b
        L_0x0056:
            long r4 = r9.readInt64()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.timestampOffsetMicros_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            goto L_0x000b
        L_0x005d:
            r4 = r2 & 4
            if (r4 == r3) goto L_0x006a
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r4.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.spans_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r2 = r2 | 4
        L_0x006a:
            java.util.List<com.lightstep.tracer.grpc.Span> r4 = r8.spans_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.google.protobuf.Parser r5 = com.lightstep.tracer.grpc.Span.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.google.protobuf.MessageLite r5 = r9.readMessage(r5, r10)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r4.add(r5)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            goto L_0x000b
        L_0x0078:
            com.lightstep.tracer.grpc.Auth r4 = r8.auth_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r4 == 0) goto L_0x0082
            com.lightstep.tracer.grpc.Auth r4 = r8.auth_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.Auth$Builder r7 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
        L_0x0082:
            com.google.protobuf.Parser r4 = com.lightstep.tracer.grpc.Auth.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.google.protobuf.MessageLite r4 = r9.readMessage(r4, r10)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.Auth r4 = (com.lightstep.tracer.grpc.Auth) r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.auth_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r7 == 0) goto L_0x000b
            com.lightstep.tracer.grpc.Auth r4 = r8.auth_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r7.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.Auth r4 = r7.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.auth_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            goto L_0x000b
        L_0x009d:
            com.lightstep.tracer.grpc.Reporter r4 = r8.reporter_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r4 == 0) goto L_0x00a7
            com.lightstep.tracer.grpc.Reporter r4 = r8.reporter_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.Reporter$Builder r7 = r4.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
        L_0x00a7:
            com.google.protobuf.Parser r4 = com.lightstep.tracer.grpc.Reporter.parser()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.google.protobuf.MessageLite r4 = r9.readMessage(r4, r10)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.Reporter r4 = (com.lightstep.tracer.grpc.Reporter) r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.reporter_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            if (r7 == 0) goto L_0x000b
            com.lightstep.tracer.grpc.Reporter r4 = r8.reporter_     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r7.mergeFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            com.lightstep.tracer.grpc.Reporter r4 = r7.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            r8.reporter_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x00d2, IOException -> 0x00c7 }
            goto L_0x000b
        L_0x00c2:
            r1 = 1
            goto L_0x000b
        L_0x00c5:
            r9 = move-exception
            goto L_0x00d8
        L_0x00c7:
            r9 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r10 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x00c5 }
            r10.<init>(r9)     // Catch:{ all -> 0x00c5 }
            com.google.protobuf.InvalidProtocolBufferException r9 = r10.setUnfinishedMessage(r8)     // Catch:{ all -> 0x00c5 }
            throw r9     // Catch:{ all -> 0x00c5 }
        L_0x00d2:
            r9 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r9 = r9.setUnfinishedMessage(r8)     // Catch:{ all -> 0x00c5 }
            throw r9     // Catch:{ all -> 0x00c5 }
        L_0x00d8:
            r10 = r2 & 4
            if (r10 != r3) goto L_0x00e4
            java.util.List<com.lightstep.tracer.grpc.Span> r10 = r8.spans_
            java.util.List r10 = java.util.Collections.unmodifiableList(r10)
            r8.spans_ = r10
        L_0x00e4:
            com.google.protobuf.UnknownFieldSet r10 = r0.build()
            r8.unknownFields = r10
            r8.makeExtensionsImmutable()
            throw r9
        L_0x00ee:
            r9 = r2 & 4
            if (r9 != r3) goto L_0x00fa
            java.util.List<com.lightstep.tracer.grpc.Span> r9 = r8.spans_
            java.util.List r9 = java.util.Collections.unmodifiableList(r9)
            r8.spans_ = r9
        L_0x00fa:
            com.google.protobuf.UnknownFieldSet r9 = r0.build()
            r8.unknownFields = r9
            r8.makeExtensionsImmutable()
            return
        L_0x0104:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.ReportRequest.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return Collector.internal_static_lightstep_collector_ReportRequest_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_ReportRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(ReportRequest.class, Builder.class);
    }

    public boolean hasReporter() {
        return this.reporter_ != null;
    }

    public Reporter getReporter() {
        Reporter reporter = this.reporter_;
        return reporter == null ? Reporter.getDefaultInstance() : reporter;
    }

    public boolean hasAuth() {
        return this.auth_ != null;
    }

    public Auth getAuth() {
        Auth auth = this.auth_;
        return auth == null ? Auth.getDefaultInstance() : auth;
    }

    public List<Span> getSpansList() {
        return this.spans_;
    }

    public int getSpansCount() {
        return this.spans_.size();
    }

    public long getTimestampOffsetMicros() {
        return this.timestampOffsetMicros_;
    }

    public boolean hasInternalMetrics() {
        return this.internalMetrics_ != null;
    }

    public InternalMetrics getInternalMetrics() {
        InternalMetrics internalMetrics = this.internalMetrics_;
        return internalMetrics == null ? InternalMetrics.getDefaultInstance() : internalMetrics;
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
        if (this.reporter_ != null) {
            codedOutputStream.writeMessage(1, getReporter());
        }
        if (this.auth_ != null) {
            codedOutputStream.writeMessage(2, getAuth());
        }
        for (int i = 0; i < this.spans_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.spans_.get(i));
        }
        long j = this.timestampOffsetMicros_;
        if (j != 0) {
            codedOutputStream.writeInt64(5, j);
        }
        if (this.internalMetrics_ != null) {
            codedOutputStream.writeMessage(6, getInternalMetrics());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.reporter_ != null ? CodedOutputStream.computeMessageSize(1, getReporter()) + 0 : 0;
        if (this.auth_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getAuth());
        }
        for (int i2 = 0; i2 < this.spans_.size(); i2++) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.spans_.get(i2));
        }
        long j = this.timestampOffsetMicros_;
        if (j != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(5, j);
        }
        if (this.internalMetrics_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(6, getInternalMetrics());
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
        if (!(obj instanceof ReportRequest)) {
            return super.equals(obj);
        }
        ReportRequest reportRequest = (ReportRequest) obj;
        boolean z2 = hasReporter() == reportRequest.hasReporter();
        if (hasReporter()) {
            z2 = z2 && getReporter().equals(reportRequest.getReporter());
        }
        boolean z3 = z2 && hasAuth() == reportRequest.hasAuth();
        if (hasAuth()) {
            z3 = z3 && getAuth().equals(reportRequest.getAuth());
        }
        boolean z4 = ((z3 && getSpansList().equals(reportRequest.getSpansList())) && (getTimestampOffsetMicros() > reportRequest.getTimestampOffsetMicros() ? 1 : (getTimestampOffsetMicros() == reportRequest.getTimestampOffsetMicros() ? 0 : -1)) == 0) && hasInternalMetrics() == reportRequest.hasInternalMetrics();
        if (hasInternalMetrics()) {
            z4 = z4 && getInternalMetrics().equals(reportRequest.getInternalMetrics());
        }
        if (!z4 || !this.unknownFields.equals(reportRequest.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasReporter()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getReporter().hashCode();
        }
        if (hasAuth()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getAuth().hashCode();
        }
        if (getSpansCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getSpansList().hashCode();
        }
        int hashLong = (((hashCode * 37) + 5) * 53) + Internal.hashLong(getTimestampOffsetMicros());
        if (hasInternalMetrics()) {
            hashLong = (((hashLong * 37) + 6) * 53) + getInternalMetrics().hashCode();
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

    public static ReportRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ReportRequest> getParserForType() {
        return PARSER;
    }

    public ReportRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
