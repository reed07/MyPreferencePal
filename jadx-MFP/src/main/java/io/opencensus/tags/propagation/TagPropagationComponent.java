package io.opencensus.tags.propagation;

public abstract class TagPropagationComponent {
    public abstract TagContextBinarySerializer getBinarySerializer();
}
