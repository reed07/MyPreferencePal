package io.opentracing.noop;

import io.opentracing.SpanContext;
import java.util.Map;

/* compiled from: NoopSpan */
final class NoopSpanImpl implements NoopSpan {
    public void finish() {
    }

    public void finish(long j) {
    }

    public String getBaggageItem(String str) {
        return null;
    }

    public NoopSpan log(long j, String str) {
        return this;
    }

    public NoopSpan log(long j, Map<String, ?> map) {
        return this;
    }

    public NoopSpan log(String str) {
        return this;
    }

    public NoopSpan log(Map<String, ?> map) {
        return this;
    }

    public NoopSpan setBaggageItem(String str, String str2) {
        return this;
    }

    public NoopSpan setOperationName(String str) {
        return this;
    }

    public NoopSpan setTag(String str, Number number) {
        return this;
    }

    public NoopSpan setTag(String str, String str2) {
        return this;
    }

    public NoopSpan setTag(String str, boolean z) {
        return this;
    }

    NoopSpanImpl() {
    }

    public SpanContext context() {
        return NoopSpanContextImpl.INSTANCE;
    }

    public String toString() {
        return NoopSpan.class.getSimpleName();
    }
}
