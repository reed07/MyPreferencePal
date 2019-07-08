package io.opentracing.tag;

import io.opentracing.Span;

public class StringTag extends AbstractTag<String> {
    public StringTag(String str) {
        super(str);
    }

    public void set(Span span, String str) {
        span.setTag(this.key, str);
    }
}
