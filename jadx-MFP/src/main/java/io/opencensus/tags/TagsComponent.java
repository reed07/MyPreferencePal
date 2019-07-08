package io.opencensus.tags;

import io.opencensus.tags.propagation.TagPropagationComponent;

public abstract class TagsComponent {
    public abstract TagPropagationComponent getTagPropagationComponent();

    public abstract Tagger getTagger();
}
