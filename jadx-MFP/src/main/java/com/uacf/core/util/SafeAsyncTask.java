package com.uacf.core.util;

import android.os.Handler;
import android.os.Looper;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public abstract class SafeAsyncTask<ResultT> implements Callable<ResultT> {
    protected static final Executor DEFAULT_EXECUTOR = Executors.newFixedThreadPool(25);
    public static final int DEFAULT_POOL_SIZE = 25;
    protected Executor executor;
    protected FutureTask<Void> future;
    protected Handler handler;
    protected StackTraceElement[] launchLocation;

    public static class Task<ResultT> implements Callable<Void> {
        protected Handler handler;
        protected SafeAsyncTask<ResultT> parent;

        public Task(SafeAsyncTask<ResultT> safeAsyncTask) {
            this.parent = safeAsyncTask;
            this.handler = safeAsyncTask.handler != null ? safeAsyncTask.handler : new Handler(Looper.getMainLooper());
        }

        public Void call() throws Exception {
            try {
                doPreExecute();
                doSuccess(doCall());
            } catch (Exception e) {
                try {
                    doException(e);
                } catch (Exception e2) {
                    Ln.e(e2);
                }
            } catch (Throwable th) {
                try {
                    doThrowable(th);
                } catch (Exception e3) {
                    Ln.e(e3);
                } catch (Throwable th2) {
                    doFinally();
                    throw th2;
                }
            }
            doFinally();
            return null;
        }

        /* access modifiers changed from: protected */
        public void doPreExecute() throws Exception {
            postToUiThreadAndWait(new Callable<Object>() {
                public Object call() throws Exception {
                    Task.this.parent.onPreExecute();
                    return null;
                }
            });
        }

        /* access modifiers changed from: protected */
        public ResultT doCall() throws Exception {
            return this.parent.call();
        }

        /* access modifiers changed from: protected */
        public void doSuccess(final ResultT resultt) throws Exception {
            postToUiThreadAndWait(new Callable<Object>() {
                public Object call() throws Exception {
                    Task.this.parent.onSuccess(resultt);
                    return null;
                }
            });
        }

        /* access modifiers changed from: protected */
        public void doException(final Exception exc) throws Exception {
            if (this.parent.launchLocation != null) {
                ArrayList arrayList = new ArrayList(Arrays.asList(exc.getStackTrace()));
                arrayList.addAll(Arrays.asList(this.parent.launchLocation));
                exc.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]));
            }
            postToUiThreadAndWait(new Callable<Object>() {
                public Object call() throws Exception {
                    Exception exc = exc;
                    if ((exc instanceof InterruptedException) || (exc instanceof InterruptedIOException)) {
                        Task.this.parent.onInterrupted(exc);
                    } else {
                        Task.this.parent.onException(exc);
                    }
                    return null;
                }
            });
        }

        /* access modifiers changed from: protected */
        public void doThrowable(final Throwable th) throws Exception {
            if (this.parent.launchLocation != null) {
                ArrayList arrayList = new ArrayList(Arrays.asList(th.getStackTrace()));
                arrayList.addAll(Arrays.asList(this.parent.launchLocation));
                th.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]));
            }
            postToUiThreadAndWait(new Callable<Object>() {
                public Object call() throws Exception {
                    Task.this.parent.onThrowable(th);
                    return null;
                }
            });
        }

        /* access modifiers changed from: protected */
        public void doFinally() throws Exception {
            postToUiThreadAndWait(new Callable<Object>() {
                public Object call() throws Exception {
                    Task.this.parent.onFinally();
                    return null;
                }
            });
        }

        /* access modifiers changed from: protected */
        public void postToUiThreadAndWait(final Callable callable) throws Exception {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final Exception[] excArr = new Exception[1];
            this.handler.post(new Runnable() {
                public void run() {
                    try {
                        callable.call();
                    } catch (Exception e) {
                        excArr[0] = e;
                    } catch (Throwable th) {
                        countDownLatch.countDown();
                        throw th;
                    }
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            if (excArr[0] != null) {
                throw excArr[0];
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinally() throws RuntimeException {
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void onSuccess(ResultT resultt) throws Exception {
    }

    public SafeAsyncTask() {
        this.executor = DEFAULT_EXECUTOR;
    }

    public SafeAsyncTask(Handler handler2) {
        this.handler = handler2;
        this.executor = DEFAULT_EXECUTOR;
    }

    public SafeAsyncTask(Executor executor2) {
        this.executor = executor2;
    }

    public SafeAsyncTask(Handler handler2, Executor executor2) {
        this.handler = handler2;
        this.executor = executor2;
    }

    public FutureTask<Void> future() {
        this.future = new FutureTask<>(newTask());
        return this.future;
    }

    public SafeAsyncTask<ResultT> executor(Executor executor2) {
        this.executor = executor2;
        return this;
    }

    public Executor executor() {
        return this.executor;
    }

    public SafeAsyncTask<ResultT> handler(Handler handler2) {
        this.handler = handler2;
        return this;
    }

    public Handler handler() {
        return this.handler;
    }

    public void execute() {
        execute(Thread.currentThread().getStackTrace());
    }

    /* access modifiers changed from: protected */
    public void execute(StackTraceElement[] stackTraceElementArr) {
        this.launchLocation = stackTraceElementArr;
        this.executor.execute(future());
    }

    public boolean cancel(boolean z) {
        FutureTask<Void> futureTask = this.future;
        if (futureTask != null) {
            return futureTask.cancel(z);
        }
        throw new UnsupportedOperationException("You cannot cancel this task before calling future()");
    }

    /* access modifiers changed from: protected */
    public void onInterrupted(Exception exc) {
        onException(exc);
    }

    /* access modifiers changed from: protected */
    public void onException(Exception exc) throws RuntimeException {
        onThrowable(exc);
    }

    /* access modifiers changed from: protected */
    public void onThrowable(Throwable th) throws RuntimeException {
        Ln.e(th, "Throwable caught during background processing", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public Task<ResultT> newTask() {
        return new Task<>(this);
    }
}
