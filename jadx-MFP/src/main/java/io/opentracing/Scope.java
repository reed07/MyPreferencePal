package io.opentracing;

import java.io.Closeable;

public interface Scope extends Closeable {
    void close();

    Span span();
}
