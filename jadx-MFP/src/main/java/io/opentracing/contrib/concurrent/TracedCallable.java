package io.opentracing.contrib.concurrent;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import java.util.concurrent.Callable;

public class TracedCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private final Span span;
    private final Tracer tracer;

    public TracedCallable(Callable<V> callable, Tracer tracer2) {
        this.delegate = callable;
        this.tracer = tracer2;
        this.span = tracer2.activeSpan();
    }

    public V call() throws Exception {
        Scope activate = this.span == null ? null : this.tracer.scopeManager().activate(this.span, false);
        try {
            return this.delegate.call();
        } finally {
            if (activate != null) {
                activate.close();
            }
        }
    }
}
