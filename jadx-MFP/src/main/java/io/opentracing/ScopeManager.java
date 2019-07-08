package io.opentracing;

public interface ScopeManager {
    Scope activate(Span span, boolean z);

    Scope active();
}
