package com.uacf.core.tracing;

import com.uacf.core.util.Ln;
import io.opentracing.Scope;
import io.opentracing.Tracer;

public class FSTraceableServiceImpl {
    private Tracer tracer;

    public FSTraceableServiceImpl(Tracer tracer2) {
        this.tracer = tracer2;
    }

    public Tracer getTracer() {
        return this.tracer;
    }

    /* access modifiers changed from: protected */
    public Scope startActiveSpanForMethod() {
        String str;
        if (this.tracer != null) {
            try {
                str = FSTracingUtil.createSpanNameForCallingMethod();
                try {
                    return this.tracer.buildSpan(str).startActive(true);
                } catch (Exception e) {
                    e = e;
                    StringBuilder sb = new StringBuilder();
                    sb.append("FSTraceableServiceImpl: Failed to create start span ");
                    sb.append(str);
                    Ln.e(e, sb.toString(), new Object[0]);
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                str = null;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("FSTraceableServiceImpl: Failed to create start span ");
                sb2.append(str);
                Ln.e(e, sb2.toString(), new Object[0]);
                return null;
            }
        }
        return null;
    }
}
