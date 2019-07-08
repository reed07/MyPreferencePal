package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.internal.CommonUtils;
import java.util.concurrent.ScheduledExecutorService;

public abstract class EventsHandler<T> implements EventsStorageListener {
    protected final Context context;
    protected final ScheduledExecutorService executor;
    protected EventsStrategy<T> strategy;

    public EventsHandler(Context context2, EventsStrategy<T> eventsStrategy, EventsFilesManager eventsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        this.context = context2.getApplicationContext();
        this.executor = scheduledExecutorService;
        this.strategy = eventsStrategy;
        eventsFilesManager.registerRollOverListener(this);
    }

    public void recordEventAsync(final T t, final boolean z) {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    EventsHandler.this.strategy.recordEvent(t);
                    if (z) {
                        EventsHandler.this.strategy.rollFileOver();
                    }
                } catch (Exception e) {
                    CommonUtils.logControlledError(EventsHandler.this.context, "Failed to record event.", e);
                }
            }
        });
    }

    public void onRollOver(String str) {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    EventsHandler.this.strategy.sendEvents();
                } catch (Exception e) {
                    CommonUtils.logControlledError(EventsHandler.this.context, "Failed to send events files.", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Exception e) {
            CommonUtils.logControlledError(this.context, "Failed to submit events task", e);
        }
    }
}
