package io.opencensus.trace;

import java.util.Collections;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Link {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();

    public enum Type {
        CHILD_LINKED_SPAN,
        PARENT_LINKED_SPAN
    }

    Link() {
    }
}
