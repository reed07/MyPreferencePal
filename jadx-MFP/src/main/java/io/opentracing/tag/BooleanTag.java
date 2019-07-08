package io.opentracing.tag;

import io.opentracing.Span;

public class BooleanTag extends AbstractTag<Boolean> {
    public BooleanTag(String str) {
        super(str);
    }

    public void set(Span span, Boolean bool) {
        span.setTag(this.key, bool.booleanValue());
    }
}
