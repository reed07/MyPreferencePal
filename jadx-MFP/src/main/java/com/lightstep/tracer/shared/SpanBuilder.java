package com.lightstep.tracer.shared;

import com.lightstep.tracer.grpc.Reference;
import com.lightstep.tracer.grpc.Reference.Relationship;
import com.lightstep.tracer.grpc.Span;
import com.lightstep.tracer.grpc.Span.Builder;
import io.opentracing.Scope;
import io.opentracing.SpanContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpanBuilder implements io.opentracing.Tracer.SpanBuilder {
    private final Map<String, Boolean> boolTags;
    private final Builder grpcSpan = Span.newBuilder();
    private boolean ignoringActiveSpan;
    private final Map<String, Number> numTags;
    private final String operationName;
    private SpanContext parent;
    private Long spanId = null;
    private long startTimestampMicros;
    private final Map<String, String> stringTags;
    private Long traceId = null;
    private final AbstractTracer tracer;

    SpanBuilder(String str, AbstractTracer abstractTracer) {
        this.operationName = str;
        this.tracer = abstractTracer;
        this.stringTags = new HashMap();
        this.boolTags = new HashMap();
        this.numTags = new HashMap();
    }

    public io.opentracing.Tracer.SpanBuilder asChildOf(SpanContext spanContext) {
        return addReference("child_of", spanContext);
    }

    public io.opentracing.Tracer.SpanBuilder asChildOf(io.opentracing.Span span) {
        return span == null ? this : asChildOf(span.context());
    }

    public io.opentracing.Tracer.SpanBuilder addReference(String str, SpanContext spanContext) {
        if (spanContext != null && ("child_of".equals(str) || "follows_from".equals(str))) {
            this.parent = (SpanContext) spanContext;
            Reference.Builder newBuilder = Reference.newBuilder();
            newBuilder.setSpanContext(this.parent.getInnerSpanCtx());
            if ("child_of".equals(str)) {
                newBuilder.setRelationship(Relationship.CHILD_OF);
            } else {
                newBuilder.setRelationship(Relationship.FOLLOWS_FROM);
            }
            this.grpcSpan.addReferences(newBuilder);
        }
        return this;
    }

    public io.opentracing.Tracer.SpanBuilder ignoreActiveSpan() {
        this.ignoringActiveSpan = true;
        return this;
    }

    public io.opentracing.Tracer.SpanBuilder withTag(String str, String str2) {
        this.stringTags.put(str, str2);
        return this;
    }

    public io.opentracing.Tracer.SpanBuilder withTag(String str, boolean z) {
        this.boolTags.put(str, Boolean.valueOf(z));
        return this;
    }

    public io.opentracing.Tracer.SpanBuilder withTag(String str, Number number) {
        this.numTags.put(str, number);
        return this;
    }

    public io.opentracing.Tracer.SpanBuilder withStartTimestamp(long j) {
        this.startTimestampMicros = j;
        return this;
    }

    public Scope startActive(boolean z) {
        return this.tracer.scopeManager().activate(startManual(), z);
    }

    public io.opentracing.Span start() {
        return startManual();
    }

    private SpanContext activeSpanContext() {
        Scope active = this.tracer.scopeManager().active();
        if (active == null || active.span() == null) {
            return null;
        }
        SpanContext context = active.span().context();
        if (context instanceof SpanContext) {
            return (SpanContext) context;
        }
        return null;
    }

    public io.opentracing.Span startManual() {
        long j;
        if (this.tracer.isDisabled()) {
            return NoopSpan.INSTANCE;
        }
        if (this.startTimestampMicros == 0) {
            long nanoTime = System.nanoTime();
            this.startTimestampMicros = Util.nowMicrosApproximate();
            j = nanoTime;
        } else {
            j = -1;
        }
        this.grpcSpan.setOperationName(this.operationName);
        this.grpcSpan.setStartTimestamp(Util.epochTimeMicrosToProtoTime(this.startTimestampMicros));
        Long l = this.traceId;
        HashMap hashMap = null;
        if (this.parent == null && !this.ignoringActiveSpan) {
            this.parent = activeSpanContext();
            asChildOf((SpanContext) this.parent);
        }
        SpanContext spanContext = this.parent;
        if (spanContext != null) {
            l = Long.valueOf(spanContext.getTraceId());
            hashMap = new HashMap(this.parent.getBaggage());
        }
        SpanContext spanContext2 = new SpanContext(l, this.spanId, hashMap);
        this.grpcSpan.setSpanContext(spanContext2.getInnerSpanCtx());
        Span span = new Span(this.tracer, spanContext2, this.grpcSpan, j);
        for (Entry entry : this.stringTags.entrySet()) {
            span.setTag((String) entry.getKey(), (String) entry.getValue());
        }
        for (Entry entry2 : this.boolTags.entrySet()) {
            span.setTag((String) entry2.getKey(), ((Boolean) entry2.getValue()).booleanValue());
        }
        for (Entry entry3 : this.numTags.entrySet()) {
            span.setTag((String) entry3.getKey(), (Number) entry3.getValue());
        }
        return span;
    }
}
