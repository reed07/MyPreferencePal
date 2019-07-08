package io.opentracing.noop;

import io.opentracing.Tracer.SpanBuilder;

public interface NoopSpanBuilder extends SpanBuilder, NoopSpanContext {
    public static final NoopSpanBuilder INSTANCE = new NoopSpanBuilderImpl();
}
