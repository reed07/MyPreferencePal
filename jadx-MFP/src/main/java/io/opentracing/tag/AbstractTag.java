package io.opentracing.tag;

public abstract class AbstractTag<T> {
    protected final String key;

    public AbstractTag(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }
}
