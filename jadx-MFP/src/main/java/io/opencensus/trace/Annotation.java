package io.opencensus.trace;

import java.util.Collections;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Annotation {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.unmodifiableMap(Collections.emptyMap());

    Annotation() {
    }
}
