package io.opentracing.propagation;

import java.util.Iterator;
import java.util.Map.Entry;

public interface TextMap extends Iterable<Entry<String, String>> {
    Iterator<Entry<String, String>> iterator();

    void put(String str, String str2);
}
