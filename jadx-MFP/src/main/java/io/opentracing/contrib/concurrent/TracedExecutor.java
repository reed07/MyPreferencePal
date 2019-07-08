package io.opentracing.contrib.concurrent;

import io.opentracing.Tracer;
import java.util.concurrent.Executor;

public class TracedExecutor implements Executor {
    private final Executor delegate;
    protected final Tracer tracer;

    public void execute(Runnable runnable) {
        Executor executor = this.delegate;
        if (this.tracer.activeSpan() != null) {
            runnable = new TracedRunnable(runnable, this.tracer);
        }
        executor.execute(runnable);
    }
}
