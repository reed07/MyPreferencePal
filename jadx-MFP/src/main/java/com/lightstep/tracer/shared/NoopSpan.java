package com.lightstep.tracer.shared;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

class NoopSpan implements Span {
    private static final SpanContext CONTEXT = new NoopSpanContext();
    static final Span INSTANCE = new NoopSpan();

    private static class NoopSpanContext implements SpanContext {
        private NoopSpanContext() {
        }

        public Iterable<Entry<String, String>> baggageItems() {
            return Collections.emptySet();
        }
    }

    public void finish() {
    }

    public void finish(long j) {
    }

    public String getBaggageItem(String str) {
        return null;
    }

    public Span log(long j, String str) {
        return this;
    }

    public final Span log(long j, Map<String, ?> map) {
        return this;
    }

    public Span log(String str) {
        return this;
    }

    public final Span log(Map<String, ?> map) {
        return this;
    }

    public Span setBaggageItem(String str, String str2) {
        return this;
    }

    public Span setOperationName(String str) {
        return this;
    }

    public Span setTag(String str, Number number) {
        return this;
    }

    public Span setTag(String str, String str2) {
        return this;
    }

    public Span setTag(String str, boolean z) {
        return this;
    }

    private NoopSpan() {
    }

    public SpanContext context() {
        return CONTEXT;
    }
}
