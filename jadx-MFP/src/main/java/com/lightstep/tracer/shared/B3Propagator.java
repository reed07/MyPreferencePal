package com.lightstep.tracer.shared;

import io.opentracing.propagation.TextMap;

public class B3Propagator implements Propagator<TextMap> {
    public void inject(SpanContext spanContext, TextMap textMap) {
        long traceId = spanContext.getTraceId();
        long spanId = spanContext.getSpanId();
        textMap.put("X-B3-TraceId", Util.toHexString(traceId));
        textMap.put("X-B3-SpanId", Util.toHexString(spanId));
        textMap.put("X-B3-Sampled", "true");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=io.opentracing.propagation.TextMap, code=io.opentracing.propagation.TextMap<java.util.Map$Entry>, for r7v0, types: [io.opentracing.propagation.TextMap, io.opentracing.propagation.TextMap<java.util.Map$Entry>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.lightstep.tracer.shared.SpanContext extract(io.opentracing.propagation.TextMap<java.util.Map.Entry> r7) {
        /*
            r6 = this;
            java.util.Iterator r7 = r7.iterator()
            r0 = 0
            r1 = r0
            r2 = r1
        L_0x0007:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r7.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "X-B3-TraceId"
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 == 0) goto L_0x002c
            java.lang.Object r1 = r3.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Long r1 = com.lightstep.tracer.shared.Util.fromHexString(r1)
            goto L_0x0007
        L_0x002c:
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "X-B3-SpanId"
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 == 0) goto L_0x0007
            java.lang.Object r2 = r3.getValue()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Long r2 = com.lightstep.tracer.shared.Util.fromHexString(r2)
            goto L_0x0007
        L_0x0045:
            if (r1 == 0) goto L_0x0057
            if (r2 == 0) goto L_0x0057
            com.lightstep.tracer.shared.SpanContext r7 = new com.lightstep.tracer.shared.SpanContext
            long r0 = r1.longValue()
            long r2 = r2.longValue()
            r7.<init>(r0, r2)
            return r7
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.shared.B3Propagator.extract(io.opentracing.propagation.TextMap):com.lightstep.tracer.shared.SpanContext");
    }
}
