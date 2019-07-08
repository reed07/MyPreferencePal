package io.opentracing.propagation;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class TextMapExtractAdapter implements TextMap {
    private final Map<String, String> map;

    public Iterator<Entry<String, String>> iterator() {
        return this.map.entrySet().iterator();
    }

    public void put(String str, String str2) {
        throw new UnsupportedOperationException("TextMapInjectAdapter should only be used with Tracer.extract()");
    }
}
