package io.opentracing.util;

import io.opentracing.Scope;
import io.opentracing.Span;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoFinishScope implements Scope {
    final AutoFinishScopeManager manager;
    final AtomicInteger refCount;
    private final AutoFinishScope toRestore;
    private final Span wrapped;

    public class Continuation {
    }

    AutoFinishScope(AutoFinishScopeManager autoFinishScopeManager, AtomicInteger atomicInteger, Span span) {
        this.manager = autoFinishScopeManager;
        this.refCount = atomicInteger;
        this.wrapped = span;
        this.toRestore = (AutoFinishScope) autoFinishScopeManager.tlsScope.get();
        autoFinishScopeManager.tlsScope.set(this);
    }

    public void close() {
        if (this.manager.tlsScope.get() == this) {
            if (this.refCount.decrementAndGet() == 0) {
                this.wrapped.finish();
            }
            this.manager.tlsScope.set(this.toRestore);
        }
    }

    public Span span() {
        return this.wrapped;
    }
}
