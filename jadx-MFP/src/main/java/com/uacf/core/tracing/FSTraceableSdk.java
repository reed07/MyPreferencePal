package com.uacf.core.tracing;

import io.opentracing.SpanContext;

public interface FSTraceableSdk<T> {
    T setParentSpanContext(SpanContext spanContext);
}
