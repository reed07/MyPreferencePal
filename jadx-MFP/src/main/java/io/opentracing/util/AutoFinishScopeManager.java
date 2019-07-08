package io.opentracing.util;

import io.opentracing.ScopeManager;
import io.opentracing.Span;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoFinishScopeManager implements ScopeManager {
    final ThreadLocal<AutoFinishScope> tlsScope = new ThreadLocal<>();

    public AutoFinishScope activate(Span span, boolean z) {
        return new AutoFinishScope(this, new AtomicInteger(1), span);
    }

    public AutoFinishScope active() {
        return (AutoFinishScope) this.tlsScope.get();
    }
}
