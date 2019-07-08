package io.opentracing;

import io.opentracing.propagation.Format;

public interface Tracer {

    public interface SpanBuilder {
        SpanBuilder addReference(String str, SpanContext spanContext);

        SpanBuilder asChildOf(Span span);

        SpanBuilder asChildOf(SpanContext spanContext);

        SpanBuilder ignoreActiveSpan();

        Span start();

        Scope startActive(boolean z);

        @Deprecated
        Span startManual();

        SpanBuilder withStartTimestamp(long j);

        SpanBuilder withTag(String str, Number number);

        SpanBuilder withTag(String str, String str2);

        SpanBuilder withTag(String str, boolean z);
    }

    Span activeSpan();

    SpanBuilder buildSpan(String str);

    <C> SpanContext extract(Format<C> format, C c);

    <C> void inject(SpanContext spanContext, Format<C> format, C c);

    ScopeManager scopeManager();
}
