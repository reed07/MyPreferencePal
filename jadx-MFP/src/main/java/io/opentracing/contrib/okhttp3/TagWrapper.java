package io.opentracing.contrib.okhttp3;

import io.opentracing.Span;

public class TagWrapper {
    private Span span;
    private Object tag;

    public TagWrapper(Object obj) {
        this.tag = obj;
    }

    TagWrapper(TagWrapper tagWrapper, Span span2) {
        this.span = span2;
        this.tag = tagWrapper.tag;
    }

    /* access modifiers changed from: 0000 */
    public Span getSpan() {
        return this.span;
    }
}
