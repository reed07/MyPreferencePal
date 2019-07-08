package com.uacf.core.tasks;

public abstract class PollingStrategy {
    protected int count = 0;

    /* access modifiers changed from: protected */
    public int getMax() {
        return 30;
    }

    /* access modifiers changed from: protected */
    public abstract long getNextInterval();

    public long nextInterval() {
        if (this.count >= getMax()) {
            return -1;
        }
        long nextInterval = getNextInterval();
        this.count++;
        return nextInterval;
    }
}
