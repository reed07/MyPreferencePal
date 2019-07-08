package io.opentracing.util;

import io.opentracing.Scope;
import io.opentracing.Span;

public class ThreadLocalScope implements Scope {
    private final boolean finishOnClose;
    private final ThreadLocalScopeManager scopeManager;
    private final ThreadLocalScope toRestore;
    private final Span wrapped;

    ThreadLocalScope(ThreadLocalScopeManager threadLocalScopeManager, Span span, boolean z) {
        this.scopeManager = threadLocalScopeManager;
        this.wrapped = span;
        this.finishOnClose = z;
        this.toRestore = (ThreadLocalScope) threadLocalScopeManager.tlsScope.get();
        threadLocalScopeManager.tlsScope.set(this);
    }

    public void close() {
        if (this.scopeManager.tlsScope.get() == this) {
            if (this.finishOnClose) {
                this.wrapped.finish();
            }
            this.scopeManager.tlsScope.set(this.toRestore);
        }
    }

    public Span span() {
        return this.wrapped;
    }
}
