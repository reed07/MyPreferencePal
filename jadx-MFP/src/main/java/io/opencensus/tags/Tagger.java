package io.opencensus.tags;

public abstract class Tagger {
    public abstract TagContext empty();

    public abstract TagContext getCurrentTagContext();

    public abstract TagContextBuilder toBuilder(TagContext tagContext);
}
