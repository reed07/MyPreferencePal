package com.lightstep.tracer.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpanContext implements io.opentracing.SpanContext {
    private final Map<String, String> baggage;
    private final long spanId;
    private final long traceId;

    public SpanContext() {
        this(Util.generateRandomGUID(), Util.generateRandomGUID());
    }

    public SpanContext(long j, long j2) {
        this(Long.valueOf(j), Long.valueOf(j2), null);
    }

    SpanContext(Long l, Long l2, Map<String, String> map) {
        if (l == null) {
            l = Long.valueOf(Util.generateRandomGUID());
        }
        if (l2 == null) {
            l2 = Long.valueOf(Util.generateRandomGUID());
        }
        if (map == null) {
            map = new HashMap<>();
        }
        this.traceId = l.longValue();
        this.spanId = l2.longValue();
        this.baggage = map;
    }

    public long getSpanId() {
        return this.spanId;
    }

    public long getTraceId() {
        return this.traceId;
    }

    /* access modifiers changed from: 0000 */
    public String getBaggageItem(String str) {
        return (String) this.baggage.get(str);
    }

    /* access modifiers changed from: 0000 */
    public Map<String, String> getBaggage() {
        return this.baggage;
    }

    /* access modifiers changed from: 0000 */
    public SpanContext withBaggageItem(String str, String str2) {
        this.baggage.put(str, str2);
        return new SpanContext(Long.valueOf(getTraceId()), Long.valueOf(getSpanId()), this.baggage);
    }

    public Iterable<Entry<String, String>> baggageItems() {
        return this.baggage.entrySet();
    }

    public com.lightstep.tracer.grpc.SpanContext getInnerSpanCtx() {
        return com.lightstep.tracer.grpc.SpanContext.newBuilder().setTraceId(getTraceId()).setSpanId(getSpanId()).putAllBaggage(this.baggage).build();
    }
}
