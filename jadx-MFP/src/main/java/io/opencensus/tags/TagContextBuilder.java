package io.opencensus.tags;

public abstract class TagContextBuilder {
    public abstract TagContext build();

    public abstract TagContextBuilder put(TagKey tagKey, TagValue tagValue);
}
