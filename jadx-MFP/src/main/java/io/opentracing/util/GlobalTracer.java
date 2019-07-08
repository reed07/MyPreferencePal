package io.opentracing.util;

import io.opentracing.ScopeManager;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.noop.NoopTracerFactory;
import io.opentracing.propagation.Format;
import java.util.logging.Logger;

public final class GlobalTracer implements Tracer {
    private static final GlobalTracer INSTANCE = new GlobalTracer();
    private static final Logger LOGGER = Logger.getLogger(GlobalTracer.class.getName());
    private static volatile Tracer tracer = NoopTracerFactory.create();

    private GlobalTracer() {
    }

    public ScopeManager scopeManager() {
        return tracer.scopeManager();
    }

    public SpanBuilder buildSpan(String str) {
        return tracer.buildSpan(str);
    }

    public <C> void inject(SpanContext spanContext, Format<C> format, C c) {
        tracer.inject(spanContext, format, c);
    }

    public <C> SpanContext extract(Format<C> format, C c) {
        return tracer.extract(format, c);
    }

    public Span activeSpan() {
        return tracer.activeSpan();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GlobalTracer.class.getSimpleName());
        sb.append('{');
        sb.append(tracer);
        sb.append('}');
        return sb.toString();
    }
}
