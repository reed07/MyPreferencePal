package io.opentracing.contrib.okhttp3;

import io.opentracing.propagation.TextMap;
import java.util.Iterator;
import java.util.Map.Entry;
import okhttp3.Request.Builder;

public class RequestBuilderInjectAdapter implements TextMap {
    private Builder requestBuilder;

    public RequestBuilderInjectAdapter(Builder builder) {
        this.requestBuilder = builder;
    }

    public Iterator<Entry<String, String>> iterator() {
        throw new UnsupportedOperationException("Should be used only with tracer#inject()");
    }

    public void put(String str, String str2) {
        this.requestBuilder.addHeader(str, str2);
    }
}
