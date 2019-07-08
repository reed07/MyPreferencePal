package com.uacf.core.asyncservice;

import android.os.Handler;
import android.os.Looper;
import com.uacf.core.tracing.FSTraceableServiceImpl;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import io.opentracing.Tracer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class SimpleAsyncServiceBase extends FSTraceableServiceImpl {
    /* access modifiers changed from: private */
    public AtomicInteger counter = new AtomicInteger();
    private boolean destroyed;
    private ExecutorService executor;
    private Handler handler = new Handler(Looper.getMainLooper());

    public enum InvokeMode {
        Auto,
        Async,
        Sync
    }

    /* access modifiers changed from: protected */
    public abstract int getMaxThreads();

    /* access modifiers changed from: protected */
    public abstract String getThreadName();

    public SimpleAsyncServiceBase() {
        super(null);
    }

    public SimpleAsyncServiceBase(Tracer tracer) {
        super(tracer);
    }

    /* access modifiers changed from: protected */
    public synchronized void shutdown() {
        if (this.executor != null) {
            this.executor.shutdownNow();
            this.executor = null;
        }
        this.destroyed = true;
    }

    /* access modifiers changed from: protected */
    public Handler getHandler() {
        return this.handler;
    }

    /* access modifiers changed from: protected */
    public Future<?> async(Runnable runnable) {
        throwIfDestroyed();
        return safeGetExecutor().submit(runnable);
    }

    /* access modifiers changed from: protected */
    public void sync(Runnable runnable) {
        throwIfDestroyed();
        runnable.run();
    }

    /* access modifiers changed from: protected */
    public void auto(Runnable runnable) {
        invoke(runnable, InvokeMode.Auto);
    }

    /* access modifiers changed from: protected */
    public void invoke(Runnable runnable, InvokeMode invokeMode) {
        throwIfDestroyed();
        if (invokeMode == InvokeMode.Async) {
            async(runnable);
        } else if (invokeMode == InvokeMode.Sync) {
            sync(runnable);
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            async(runnable);
        } else {
            sync(runnable);
        }
    }

    private static boolean currentThreadIsMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    /* access modifiers changed from: protected */
    public final void postToMainThread(final Function0 function0) {
        if (function0 != null) {
            this.handler.post(new Runnable() {
                public void run() {
                    function0.execute();
                }
            });
        }
    }

    public final <T> void postToMainThread(final Function1<T> function1, final T t) {
        if (function1 != null) {
            this.handler.post(new Runnable() {
                public void run() {
                    function1.execute(t);
                }
            });
        }
    }

    public final <A, B> void postToMainThread(final Function2<A, B> function2, final A a, final B b) {
        if (function2 != null) {
            this.handler.post(new Runnable() {
                public void run() {
                    function2.execute(a, b);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public final void invokeOnMainThread(Function0 function0) {
        if (currentThreadIsMainThread()) {
            FunctionUtils.invokeIfValid(function0);
        } else {
            postToMainThread(function0);
        }
    }

    /* access modifiers changed from: protected */
    public final <T> void invokeOnMainThread(Function1<T> function1, T t) {
        if (currentThreadIsMainThread()) {
            FunctionUtils.invokeIfValid(function1, t);
        } else {
            postToMainThread(function1, t);
        }
    }

    /* access modifiers changed from: protected */
    public final <A, B> void invokeOnMainThread(Function2<A, B> function2, A a, B b) {
        if (currentThreadIsMainThread()) {
            FunctionUtils.invokeIfValid(function2, a, b);
        } else {
            postToMainThread(function2, a, b);
        }
    }

    private synchronized ExecutorService safeGetExecutor() {
        if (this.executor == null) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, getMaxThreads(), 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    StringBuilder sb = new StringBuilder();
                    sb.append(SimpleAsyncServiceBase.this.getThreadName());
                    sb.append("-");
                    sb.append(String.valueOf(SimpleAsyncServiceBase.this.counter.incrementAndGet()));
                    thread.setName(sb.toString());
                    return thread;
                }
            });
            this.executor = threadPoolExecutor;
        }
        return this.executor;
    }

    private synchronized void throwIfDestroyed() {
        if (this.destroyed) {
            StringBuilder sb = new StringBuilder();
            sb.append("SimpleAsyncServiceBase ");
            sb.append(getThreadName());
            sb.append(" already destroyed.");
            throw new RuntimeException(sb.toString());
        }
    }
}
