package io.opentracing;

import java.util.Map.Entry;

public interface SpanContext {
    Iterable<Entry<String, String>> baggageItems();
}
