package io.opentracing.noop;

import io.opentracing.Span;

public interface NoopSpan extends Span {
    public static final NoopSpan INSTANCE = new NoopSpanImpl();
}
