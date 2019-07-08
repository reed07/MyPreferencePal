package io.opentracing.util;

import io.opentracing.Scope;
import io.opentracing.ScopeManager;
import io.opentracing.Span;

public class ThreadLocalScopeManager implements ScopeManager {
    final ThreadLocal<ThreadLocalScope> tlsScope = new ThreadLocal<>();

    public Scope activate(Span span, boolean z) {
        return new ThreadLocalScope(this, span, z);
    }

    public Scope active() {
        return (Scope) this.tlsScope.get();
    }
}
