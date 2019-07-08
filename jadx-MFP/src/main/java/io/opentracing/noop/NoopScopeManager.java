package io.opentracing.noop;

import io.opentracing.Scope;
import io.opentracing.ScopeManager;

public interface NoopScopeManager extends ScopeManager {
    public static final NoopScopeManager INSTANCE = new NoopScopeManagerImpl();

    public interface NoopScope extends Scope {
        public static final NoopScope INSTANCE = new NoopScopeImpl();
    }
}
