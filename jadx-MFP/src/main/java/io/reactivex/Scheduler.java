package io.reactivex;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.schedulers.NewThreadWorker;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.SchedulerRunnableIntrospection;
import java.util.concurrent.TimeUnit;

public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOSECONDS = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    static final class DisposeTask implements Disposable, SchedulerRunnableIntrospection, Runnable {
        @NonNull
        final Runnable decoratedRun;
        @Nullable
        Thread runner;
        @NonNull
        final Worker w;

        DisposeTask(@NonNull Runnable runnable, @NonNull Worker worker) {
            this.decoratedRun = runnable;
            this.w = worker;
        }

        public void run() {
            this.runner = Thread.currentThread();
            try {
                this.decoratedRun.run();
            } finally {
                dispose();
                this.runner = null;
            }
        }

        public void dispose() {
            if (this.runner == Thread.currentThread()) {
                Worker worker = this.w;
                if (worker instanceof NewThreadWorker) {
                    ((NewThreadWorker) worker).shutdown();
                    return;
                }
            }
            this.w.dispose();
        }

        public boolean isDisposed() {
            return this.w.isDisposed();
        }
    }

    static final class PeriodicDirectTask implements Disposable, SchedulerRunnableIntrospection, Runnable {
        volatile boolean disposed;
        @NonNull
        final Runnable run;
        @NonNull
        final Worker worker;

        PeriodicDirectTask(@NonNull Runnable runnable, @NonNull Worker worker2) {
            this.run = runnable;
            this.worker = worker2;
        }

        public void run() {
            if (!this.disposed) {
                try {
                    this.run.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.worker.dispose();
                    throw ExceptionHelper.wrapOrThrow(th);
                }
            }
        }

        public void dispose() {
            this.disposed = true;
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }

    public static abstract class Worker implements Disposable {

        final class PeriodicTask implements SchedulerRunnableIntrospection, Runnable {
            long count;
            @NonNull
            final Runnable decoratedRun;
            long lastNowNanoseconds;
            final long periodInNanoseconds;
            @NonNull
            final SequentialDisposable sd;
            long startInNanoseconds;

            PeriodicTask(long j, Runnable runnable, @NonNull long j2, SequentialDisposable sequentialDisposable, @NonNull long j3) {
                this.decoratedRun = runnable;
                this.sd = sequentialDisposable;
                this.periodInNanoseconds = j3;
                this.lastNowNanoseconds = j2;
                this.startInNanoseconds = j;
            }

            public void run() {
                long j;
                this.decoratedRun.run();
                if (!this.sd.isDisposed()) {
                    long now = Worker.this.now(TimeUnit.NANOSECONDS);
                    long j2 = Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS + now;
                    long j3 = this.lastNowNanoseconds;
                    if (j2 < j3 || now >= j3 + this.periodInNanoseconds + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS) {
                        long j4 = this.periodInNanoseconds;
                        long j5 = now + j4;
                        long j6 = this.count + 1;
                        this.count = j6;
                        this.startInNanoseconds = j5 - (j4 * j6);
                        j = j5;
                    } else {
                        long j7 = this.startInNanoseconds;
                        long j8 = this.count + 1;
                        this.count = j8;
                        j = j7 + (j8 * this.periodInNanoseconds);
                    }
                    this.lastNowNanoseconds = now;
                    this.sd.replace(Worker.this.schedule(this, j - now, TimeUnit.NANOSECONDS));
                }
            }
        }

        @NonNull
        public abstract Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit);

        @NonNull
        public Disposable schedule(@NonNull Runnable runnable) {
            return schedule(runnable, 0, TimeUnit.NANOSECONDS);
        }

        @NonNull
        public Disposable schedulePeriodically(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
            long j3 = j;
            TimeUnit timeUnit2 = timeUnit;
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable onSchedule = RxJavaPlugins.onSchedule(runnable);
            long nanos = timeUnit2.toNanos(j2);
            long now = now(TimeUnit.NANOSECONDS);
            SequentialDisposable sequentialDisposable3 = sequentialDisposable;
            PeriodicTask periodicTask = r0;
            PeriodicTask periodicTask2 = new PeriodicTask(now + timeUnit2.toNanos(j3), onSchedule, now, sequentialDisposable2, nanos);
            Disposable schedule = schedule(periodicTask, j3, timeUnit2);
            if (schedule == EmptyDisposable.INSTANCE) {
                return schedule;
            }
            sequentialDisposable3.replace(schedule);
            return sequentialDisposable2;
        }

        public long now(@NonNull TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    @NonNull
    public abstract Worker createWorker();

    public void start() {
    }

    public long now(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable) {
        return scheduleDirect(runnable, 0, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
        Worker createWorker = createWorker();
        DisposeTask disposeTask = new DisposeTask(RxJavaPlugins.onSchedule(runnable), createWorker);
        createWorker.schedule(disposeTask, j, timeUnit);
        return disposeTask;
    }

    @NonNull
    public Disposable schedulePeriodicallyDirect(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        Worker createWorker = createWorker();
        PeriodicDirectTask periodicDirectTask = new PeriodicDirectTask(RxJavaPlugins.onSchedule(runnable), createWorker);
        Disposable schedulePeriodically = createWorker.schedulePeriodically(periodicDirectTask, j, j2, timeUnit);
        return schedulePeriodically == EmptyDisposable.INSTANCE ? schedulePeriodically : periodicDirectTask;
    }
}
