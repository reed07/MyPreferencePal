package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class LottieTask<T> {
    public static Executor EXECUTOR = Executors.newCachedThreadPool();
    private final Set<LottieListener<Throwable>> failureListeners;
    private final Handler handler;
    /* access modifiers changed from: private */
    @Nullable
    public volatile LottieResult<T> result;
    private final Set<LottieListener<T>> successListeners;
    /* access modifiers changed from: private */
    public final FutureTask<LottieResult<T>> task;
    @Nullable
    private Thread taskObserver;

    @RestrictTo
    public LottieTask(Callable<LottieResult<T>> callable) {
        this(callable, false);
    }

    @RestrictTo
    LottieTask(Callable<LottieResult<T>> callable, boolean z) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        this.task = new FutureTask<>(callable);
        if (z) {
            try {
                setResult((LottieResult) callable.call());
            } catch (Throwable th) {
                setResult(new LottieResult(th));
            }
        } else {
            EXECUTOR.execute(this.task);
            startTaskObserverIfNeeded();
        }
    }

    /* access modifiers changed from: private */
    public void setResult(@Nullable LottieResult<T> lottieResult) {
        if (this.result == null) {
            this.result = lottieResult;
            notifyListeners();
            return;
        }
        throw new IllegalStateException("A task may only be set once.");
    }

    public synchronized LottieTask<T> addListener(LottieListener<T> lottieListener) {
        if (!(this.result == null || this.result.getValue() == null)) {
            lottieListener.onResult(this.result.getValue());
        }
        this.successListeners.add(lottieListener);
        startTaskObserverIfNeeded();
        return this;
    }

    public synchronized LottieTask<T> removeListener(LottieListener<T> lottieListener) {
        this.successListeners.remove(lottieListener);
        stopTaskObserverIfNeeded();
        return this;
    }

    public synchronized LottieTask<T> addFailureListener(LottieListener<Throwable> lottieListener) {
        if (!(this.result == null || this.result.getException() == null)) {
            lottieListener.onResult(this.result.getException());
        }
        this.failureListeners.add(lottieListener);
        startTaskObserverIfNeeded();
        return this;
    }

    public synchronized LottieTask<T> removeFailureListener(LottieListener<Throwable> lottieListener) {
        this.failureListeners.remove(lottieListener);
        stopTaskObserverIfNeeded();
        return this;
    }

    private void notifyListeners() {
        this.handler.post(new Runnable() {
            public void run() {
                if (LottieTask.this.result != null && !LottieTask.this.task.isCancelled()) {
                    LottieResult access$000 = LottieTask.this.result;
                    if (access$000.getValue() != null) {
                        LottieTask.this.notifySuccessListeners(access$000.getValue());
                    } else {
                        LottieTask.this.notifyFailureListeners(access$000.getException());
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifySuccessListeners(T t) {
        for (LottieListener onResult : new ArrayList(this.successListeners)) {
            onResult.onResult(t);
        }
    }

    /* access modifiers changed from: private */
    public void notifyFailureListeners(Throwable th) {
        ArrayList<LottieListener> arrayList = new ArrayList<>(this.failureListeners);
        if (arrayList.isEmpty()) {
            Log.w("LOTTIE", "Lottie encountered an error but no failure listener was added.", th);
            return;
        }
        for (LottieListener onResult : arrayList) {
            onResult.onResult(th);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void startTaskObserverIfNeeded() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.taskObserverAlive()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0021
            com.airbnb.lottie.LottieResult<T> r0 = r2.result     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x000c
            goto L_0x0021
        L_0x000c:
            com.airbnb.lottie.LottieTask$2 r0 = new com.airbnb.lottie.LottieTask$2     // Catch:{ all -> 0x0023 }
            java.lang.String r1 = "LottieTaskObserver"
            r0.<init>(r1)     // Catch:{ all -> 0x0023 }
            r2.taskObserver = r0     // Catch:{ all -> 0x0023 }
            java.lang.Thread r0 = r2.taskObserver     // Catch:{ all -> 0x0023 }
            r0.start()     // Catch:{ all -> 0x0023 }
            java.lang.String r0 = "Starting TaskObserver thread"
            com.airbnb.lottie.L.debug(r0)     // Catch:{ all -> 0x0023 }
            monitor-exit(r2)
            return
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieTask.startTaskObserverIfNeeded():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stopTaskObserverIfNeeded() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.taskObserverAlive()     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            java.util.Set<com.airbnb.lottie.LottieListener<T>> r0 = r1.successListeners     // Catch:{ all -> 0x0024 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0015
            com.airbnb.lottie.LottieResult<T> r0 = r1.result     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0022
        L_0x0015:
            java.lang.Thread r0 = r1.taskObserver     // Catch:{ all -> 0x0024 }
            r0.interrupt()     // Catch:{ all -> 0x0024 }
            r0 = 0
            r1.taskObserver = r0     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = "Stopping TaskObserver thread"
            com.airbnb.lottie.L.debug(r0)     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r1)
            return
        L_0x0024:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieTask.stopTaskObserverIfNeeded():void");
    }

    private boolean taskObserverAlive() {
        Thread thread = this.taskObserver;
        return thread != null && thread.isAlive();
    }
}
