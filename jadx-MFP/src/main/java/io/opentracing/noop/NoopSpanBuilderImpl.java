package io.opentracing.noop;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.noop.NoopScopeManager.NoopScope;
import java.util.Collections;
import java.util.Map.Entry;

/* compiled from: NoopSpanBuilder */
final class NoopSpanBuilderImpl implements NoopSpanBuilder {
    public SpanBuilder addReference(String str, SpanContext spanContext) {
        return this;
    }

    public SpanBuilder asChildOf(Span span) {
        return this;
    }

    public SpanBuilder asChildOf(SpanContext spanContext) {
        return this;
    }

    public SpanBuilder ignoreActiveSpan() {
        return this;
    }

    public SpanBuilder withStartTimestamp(long j) {
        return this;
    }

    public SpanBuilder withTag(String str, Number number) {
        return this;
    }

    public SpanBuilder withTag(String str, String str2) {
        return this;
    }

    public SpanBuilder withTag(String str, boolean z) {
        return this;
    }

    NoopSpanBuilderImpl() {
    }

    public Scope startActive(boolean z) {
        return NoopScope.INSTANCE;
    }

    public Span start() {
        return startManual();
    }

    public Span startManual() {
        return NoopSpanImpl.INSTANCE;
    }

    public Iterable<Entry<String, String>> baggageItems() {
        return Collections.emptyMap().entrySet();
    }

    public String toString() {
        return NoopSpanBuilder.class.getSimpleName();
    }
}
