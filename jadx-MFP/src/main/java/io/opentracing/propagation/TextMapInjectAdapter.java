package io.opentracing.propagation;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class TextMapInjectAdapter implements TextMap {
    private final Map<String, String> map;

    public Iterator<Entry<String, String>> iterator() {
        throw new UnsupportedOperationException("TextMapInjectAdapter should only be used with Tracer.inject()");
    }

    public void put(String str, String str2) {
        this.map.put(str, str2);
    }
}
