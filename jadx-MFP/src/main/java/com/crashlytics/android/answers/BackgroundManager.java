package com.crashlytics.android.answers;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class BackgroundManager {
    final AtomicReference<ScheduledFuture<?>> backgroundFutureRef = new AtomicReference<>();
    private final ScheduledExecutorService executorService;
    private volatile boolean flushOnBackground = true;
    boolean inBackground = true;
    private final List<Listener> listeners = new ArrayList();

    public interface Listener {
        void onBackground();
    }

    public BackgroundManager(ScheduledExecutorService scheduledExecutorService) {
        this.executorService = scheduledExecutorService;
    }

    public void setFlushOnBackground(boolean z) {
        this.flushOnBackground = z;
    }

    /* access modifiers changed from: private */
    public void notifyBackground() {
        for (Listener onBackground : this.listeners) {
            onBackground.onBackground();
        }
    }

    public void registerListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void onActivityResumed() {
        this.inBackground = false;
        ScheduledFuture scheduledFuture = (ScheduledFuture) this.backgroundFutureRef.getAndSet(null);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void onActivityPaused() {
        if (this.flushOnBackground && !this.inBackground) {
            this.inBackground = true;
            try {
                this.backgroundFutureRef.compareAndSet(null, this.executorService.schedule(new Runnable() {
                    public void run() {
                        BackgroundManager.this.backgroundFutureRef.set(null);
                        BackgroundManager.this.notifyBackground();
                    }
                }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, TimeUnit.MILLISECONDS));
            } catch (RejectedExecutionException e) {
                Fabric.getLogger().d("Answers", "Failed to schedule background detector", e);
            }
        }
    }
}
