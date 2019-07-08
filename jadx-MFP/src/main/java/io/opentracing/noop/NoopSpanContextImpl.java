package io.opentracing.noop;

import java.util.Collections;
import java.util.Map.Entry;

/* compiled from: NoopSpanContext */
final class NoopSpanContextImpl implements NoopSpanContext {
    static final NoopSpanContextImpl INSTANCE = new NoopSpanContextImpl();

    NoopSpanContextImpl() {
    }

    public Iterable<Entry<String, String>> baggageItems() {
        return Collections.emptyList();
    }

    public String toString() {
        return NoopSpanContext.class.getSimpleName();
    }
}
