package io.opentracing.noop;

import io.opentracing.ScopeManager;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.propagation.Format;

/* compiled from: NoopTracer */
final class NoopTracerImpl implements NoopTracer {
    static final NoopTracer INSTANCE = new NoopTracerImpl();

    public Span activeSpan() {
        return null;
    }

    public <C> void inject(SpanContext spanContext, Format<C> format, C c) {
    }

    NoopTracerImpl() {
    }

    public ScopeManager scopeManager() {
        return NoopScopeManager.INSTANCE;
    }

    public SpanBuilder buildSpan(String str) {
        return NoopSpanBuilderImpl.INSTANCE;
    }

    public <C> SpanContext extract(Format<C> format, C c) {
        return NoopSpanBuilderImpl.INSTANCE;
    }

    public String toString() {
        return NoopTracer.class.getSimpleName();
    }
}
