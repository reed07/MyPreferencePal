package io.opentracing.noop;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.noop.NoopScopeManager.NoopScope;

/* compiled from: NoopScopeManager */
class NoopScopeManagerImpl implements NoopScopeManager {

    /* compiled from: NoopScopeManager */
    static class NoopScopeImpl implements NoopScope {
        public void close() {
        }

        NoopScopeImpl() {
        }

        public Span span() {
            return NoopSpan.INSTANCE;
        }
    }

    public Scope active() {
        return null;
    }

    NoopScopeManagerImpl() {
    }

    public Scope activate(Span span, boolean z) {
        return NoopScope.INSTANCE;
    }
}
