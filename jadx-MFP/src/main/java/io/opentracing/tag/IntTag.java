package io.opentracing.tag;

import io.opentracing.Span;

public class IntTag extends AbstractTag<Integer> {
    public IntTag(String str) {
        super(str);
    }

    public void set(Span span, Integer num) {
        span.setTag(this.key, (Number) num);
    }
}
