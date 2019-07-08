package io.fabric.sdk.android.services.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE;
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor defaultExecutor = SERIAL_EXECUTOR;
    private static final InternalHandler handler = new InternalHandler();
    private static final BlockingQueue<Runnable> poolWorkQueue = new LinkedBlockingQueue(128);
    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("AsyncTask #");
            sb.append(this.count.getAndIncrement());
            return new Thread(runnable, sb.toString());
        }
    };
    private final AtomicBoolean cancelled = new AtomicBoolean();
    private final FutureTask<Result> future = new FutureTask<Result>(this.worker) {
        /* access modifiers changed from: protected */
        public void done() {
            try {
                AsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException unused) {
                AsyncTask.this.postResultIfNotInvoked(null);
            }
        }
    };
    private volatile Status status = Status.PENDING;
    /* access modifiers changed from: private */
    public final AtomicBoolean taskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> worker = new WorkerRunnable<Params, Result>() {
        public Result call() throws Exception {
            AsyncTask.this.taskInvoked.set(true);
            Process.setThreadPriority(10);
            AsyncTask asyncTask = AsyncTask.this;
            return asyncTask.postResult(asyncTask.doInBackground(this.params));
        }
    };

    private static class AsyncTaskResult<Data> {
        final Data[] data;
        final AsyncTask task;

        AsyncTaskResult(AsyncTask asyncTask, Data... dataArr) {
            this.task = asyncTask;
            this.data = dataArr;
        }
    }

    private static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            switch (message.what) {
                case 1:
                    asyncTaskResult.task.finish(asyncTaskResult.data[0]);
                    return;
                case 2:
                    asyncTaskResult.task.onProgressUpdate(asyncTaskResult.data);
                    return;
                default:
                    return;
            }
        }
    }

    private static class SerialExecutor implements Executor {
        Runnable active;
        final LinkedList<Runnable> tasks;

        private SerialExecutor() {
            this.tasks = new LinkedList<>();
        }

        public synchronized void execute(final Runnable runnable) {
            this.tasks.offer(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        SerialExecutor.this.scheduleNext();
                    }
                }
            });
            if (this.active == null) {
                scheduleNext();
            }
        }

        /* access modifiers changed from: protected */
        public synchronized void scheduleNext() {
            Runnable runnable = (Runnable) this.tasks.poll();
            this.active = runnable;
            if (runnable != null) {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.active);
            }
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] params;

        private WorkerRunnable() {
        }
    }

    /* access modifiers changed from: protected */
    public abstract Result doInBackground(Params... paramsArr);

    /* access modifiers changed from: protected */
    public void onCancelled() {
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Progress... progressArr) {
    }

    static {
        int i = CPU_COUNT;
        CORE_POOL_SIZE = i + 1;
        MAXIMUM_POOL_SIZE = (i * 2) + 1;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1, TimeUnit.SECONDS, poolWorkQueue, threadFactory);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    /* access modifiers changed from: private */
    public void postResultIfNotInvoked(Result result) {
        if (!this.taskInvoked.get()) {
            postResult(result);
        }
    }

    /* access modifiers changed from: private */
    public Result postResult(Result result) {
        handler.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    public final Status getStatus() {
        return this.status;
    }

    /* access modifiers changed from: protected */
    public void onCancelled(Result result) {
        onCancelled();
    }

    public final boolean isCancelled() {
        return this.cancelled.get();
    }

    public final boolean cancel(boolean z) {
        this.cancelled.set(true);
        return this.future.cancel(z);
    }

    public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.status != Status.PENDING) {
            switch (this.status) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.status = Status.RUNNING;
        onPreExecute();
        this.worker.params = paramsArr;
        executor.execute(this.future);
        return this;
    }

    /* access modifiers changed from: private */
    public void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.status = Status.FINISHED;
    }
}
