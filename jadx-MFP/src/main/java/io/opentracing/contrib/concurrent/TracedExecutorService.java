package io.opentracing.contrib.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TracedExecutorService extends TracedExecutor implements ExecutorService {
    private final ExecutorService delegate;

    public void shutdown() {
        this.delegate.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        ExecutorService executorService = this.delegate;
        if (this.tracer.activeSpan() != null) {
            callable = new TracedCallable<>(callable, this.tracer);
        }
        return executorService.submit(callable);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        ExecutorService executorService = this.delegate;
        if (this.tracer.activeSpan() != null) {
            runnable = new TracedRunnable(runnable, this.tracer);
        }
        return executorService.submit(runnable, t);
    }

    public Future<?> submit(Runnable runnable) {
        ExecutorService executorService = this.delegate;
        if (this.tracer.activeSpan() != null) {
            runnable = new TracedRunnable(runnable, this.tracer);
        }
        return executorService.submit(runnable);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.delegate.invokeAll(toTraced(collection));
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.invokeAll(toTraced(collection), j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return this.delegate.invokeAny(toTraced(collection));
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.delegate.invokeAny(toTraced(collection), j, timeUnit);
    }

    private <T> Collection<? extends Callable<T>> toTraced(Collection<? extends Callable<T>> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Callable callable : collection) {
            if (this.tracer.activeSpan() != null) {
                callable = new TracedCallable(callable, this.tracer);
            }
            arrayList.add(callable);
        }
        return arrayList;
    }
}
