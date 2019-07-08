package com.lightstep.tracer.shared;

import com.myfitnesspal.shared.model.v1.Country;
import io.opentracing.propagation.TextMap;
import java.util.Locale;
import java.util.Map.Entry;

class TextMapPropagator implements Propagator<TextMap> {
    private static final Locale english = new Locale("en", Country.UNITED_STATES_SHORT);

    TextMapPropagator() {
    }

    public void inject(SpanContext spanContext, TextMap textMap) {
        textMap.put("ot-tracer-traceid", Util.toHexString(spanContext.getTraceId()));
        textMap.put("ot-tracer-spanid", Util.toHexString(spanContext.getSpanId()));
        textMap.put("ot-tracer-sampled", "true");
        for (Entry entry : spanContext.baggageItems()) {
            StringBuilder sb = new StringBuilder();
            sb.append("ot-baggage-");
            sb.append((String) entry.getKey());
            textMap.put(sb.toString(), (String) entry.getValue());
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=io.opentracing.propagation.TextMap, code=io.opentracing.propagation.TextMap<java.util.Map$Entry>, for r8v0, types: [io.opentracing.propagation.TextMap, io.opentracing.propagation.TextMap<java.util.Map$Entry>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.lightstep.tracer.shared.SpanContext extract(io.opentracing.propagation.TextMap<java.util.Map.Entry> r8) {
        /*
            r7 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Iterator r8 = r8.iterator()
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x000c:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x005e
            java.lang.Object r4 = r8.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.String r5 = (java.lang.String) r5
            java.util.Locale r6 = english
            java.lang.String r5 = r5.toLowerCase(r6)
            java.lang.String r6 = "ot-tracer-traceid"
            boolean r6 = r6.equals(r5)
            if (r6 == 0) goto L_0x0036
            java.lang.Object r2 = r4.getValue()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Long r2 = com.lightstep.tracer.shared.Util.fromHexString(r2)
        L_0x0036:
            java.lang.String r6 = "ot-tracer-spanid"
            boolean r6 = r6.equals(r5)
            if (r6 == 0) goto L_0x0048
            java.lang.Object r3 = r4.getValue()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Long r3 = com.lightstep.tracer.shared.Util.fromHexString(r3)
        L_0x0048:
            java.lang.String r6 = "ot-baggage-"
            boolean r6 = r5.startsWith(r6)
            if (r6 == 0) goto L_0x000c
            r6 = 11
            java.lang.String r5 = r5.substring(r6)
            java.lang.Object r4 = r4.getValue()
            r0.put(r5, r4)
            goto L_0x000c
        L_0x005e:
            if (r2 == 0) goto L_0x0069
            if (r3 != 0) goto L_0x0063
            goto L_0x0069
        L_0x0063:
            com.lightstep.tracer.shared.SpanContext r8 = new com.lightstep.tracer.shared.SpanContext
            r8.<init>(r2, r3, r0)
            return r8
        L_0x0069:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.shared.TextMapPropagator.extract(io.opentracing.propagation.TextMap):com.lightstep.tracer.shared.SpanContext");
    }
}
