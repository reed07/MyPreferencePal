package com.uacf.core.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Task<T> {
    Callable<T> callable;
    Status currentStatus = Status.PENDING;
    Executor executor;
    FutureTask<T> futureTask;
    Observer<T> observable;
    Executor observer;
    PollingStrategy pollingStrategy;
    FutureTask<Task<T>> retryFutureTask;
    ScheduledExecutorService scheduledExecutorService;

    class NotifyRunnable implements Runnable {
        private Result<T> result;

        NotifyRunnable(Result<T> result2) {
            this.result = result2;
        }

        public void run() {
            if (Task.this.observable != null) {
                Task.this.observable.result(this.result);
            }
        }
    }

    public interface Observer<T> {
        void result(Result<T> result);
    }

    class RetryCallable implements Callable<Task<T>> {
        private Task<T> task;

        RetryCallable(Task<T> task2) {
            this.task = task2;
        }

        public Task<T> call() throws Exception {
            if (Task.this.currentStatus == Status.CANCELLED) {
                return null;
            }
            return this.task.execute();
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        CANCELLED,
        ERROR,
        SUCCESS
    }

    private Task(Callable<T> callable2) {
        this.callable = callable2;
    }

    public static <T> Task<T> fromCallable(@NonNull Callable<T> callable2) {
        return new Task<>(callable2);
    }

    public Task<T> executeOn(@NonNull ExecutorType executorType) {
        this.executor = TaskExecutor.getExecutorForType(executorType);
        return this;
    }

    public Task<T> observeOn(@NonNull ExecutorType executorType) {
        this.observer = TaskExecutor.getExecutorForType(executorType);
        return this;
    }

    public Task<T> pollingStrategy(@NonNull PollingStrategy pollingStrategy2) {
        this.pollingStrategy = pollingStrategy2;
        this.scheduledExecutorService = TaskExecutor.getScheduledThreadExecutor();
        return this;
    }

    public Task<T> observe(@NonNull Observer<T> observer2) {
        this.observable = observer2;
        if (this.executor == null) {
            throw new IllegalArgumentException("executeOn() needs to be called to set the desired Thread of execution for this Task.");
        } else if (this.observer != null) {
            return execute();
        } else {
            throw new IllegalArgumentException("observeOn() needs to be called to set the desired Thread to observe the result from this Task");
        }
    }

    /* access modifiers changed from: private */
    public Task<T> execute() {
        this.futureTask = new FutureTask<>(new Callable<T>() {
            public T call() {
                T t = null;
                if (Task.this.currentStatus == Status.CANCELLED) {
                    return null;
                }
                try {
                    t = Task.this.callable.call();
                    Task.this.setSuccess(t);
                } catch (Exception e) {
                    if (!(e instanceof InterruptedException) || Task.this.currentStatus != Status.CANCELLED) {
                        Task.this.setError(e);
                    }
                }
                if (Task.this.pollingStrategy != null) {
                    long nextInterval = Task.this.pollingStrategy.nextInterval();
                    if (nextInterval > 0) {
                        Task task = Task.this;
                        RetryCallable retryCallable = new RetryCallable(task);
                        Task.this.retryFutureTask = new FutureTask<>(retryCallable);
                        Task.this.scheduledExecutorService.schedule(Task.this.retryFutureTask, nextInterval, TimeUnit.MILLISECONDS);
                        Task.this.currentStatus = Status.PENDING;
                    }
                }
                return t;
            }
        });
        setRunning();
        this.executor.execute(this.futureTask);
        return this;
    }

    public void cancel() {
        if (this.currentStatus != Status.CANCELLED) {
            setCancelled();
        }
        FutureTask<Task<T>> futureTask2 = this.retryFutureTask;
        if (futureTask2 != null) {
            futureTask2.cancel(true);
        }
        FutureTask<T> futureTask3 = this.futureTask;
        if (futureTask3 != null && !futureTask3.isCancelled() && !this.futureTask.isDone()) {
            this.futureTask.cancel(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setRunning() {
        this.currentStatus = Status.RUNNING;
        if (this.observable != null) {
            this.observer.execute(new NotifyRunnable(Result.running()));
        }
    }

    /* access modifiers changed from: 0000 */
    public void setCancelled() {
        this.currentStatus = Status.CANCELLED;
        if (this.observable != null) {
            this.observer.execute(new NotifyRunnable(Result.cancelled()));
        }
    }

    /* access modifiers changed from: 0000 */
    public void setSuccess(T t) {
        this.currentStatus = Status.SUCCESS;
        if (this.observable != null) {
            this.observer.execute(new NotifyRunnable(Result.success(t)));
        }
    }

    /* access modifiers changed from: 0000 */
    public void setError(Exception exc) {
        this.currentStatus = Status.ERROR;
        if (this.observable != null) {
            this.observer.execute(new NotifyRunnable(Result.error(exc)));
        }
    }
}
