package io.opentracing.contrib.concurrent;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;

public class TracedRunnable implements Runnable {
    private final Runnable delegate;
    private final Span span;
    private final Tracer tracer;

    public TracedRunnable(Runnable runnable, Tracer tracer2) {
        this.delegate = runnable;
        this.tracer = tracer2;
        this.span = tracer2.activeSpan();
    }

    public void run() {
        Scope activate = this.span == null ? null : this.tracer.scopeManager().activate(this.span, false);
        try {
            this.delegate.run();
        } finally {
            if (activate != null) {
                activate.close();
            }
        }
    }
}
