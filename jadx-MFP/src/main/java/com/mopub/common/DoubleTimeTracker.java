package com.mopub.common;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;

public class DoubleTimeTracker {
    private long interval;
    @NonNull
    private final Clock mClock;
    private long startedTimestamp;
    @NonNull
    private volatile State state;

    public interface Clock {
        long elapsedRealTime();
    }

    private enum State {
        STARTED,
        PAUSED
    }

    private static class SystemClockClock implements Clock {
        private SystemClockClock() {
        }

        public long elapsedRealTime() {
            return SystemClock.elapsedRealtime();
        }
    }

    public DoubleTimeTracker() {
        this(new SystemClockClock());
    }

    @VisibleForTesting
    public DoubleTimeTracker(@NonNull Clock clock) {
        this.mClock = clock;
        this.state = State.PAUSED;
    }

    public synchronized void start() {
        if (this.state == State.STARTED) {
            MoPubLog.v("DoubleTimeTracker already started.");
            return;
        }
        this.state = State.STARTED;
        this.startedTimestamp = this.mClock.elapsedRealTime();
    }

    public synchronized void pause() {
        if (this.state == State.PAUSED) {
            MoPubLog.v("DoubleTimeTracker already paused.");
            return;
        }
        this.interval += computeIntervalDiff();
        this.startedTimestamp = 0;
        this.state = State.PAUSED;
    }

    public synchronized double getInterval() {
        return (double) (this.interval + computeIntervalDiff());
    }

    private synchronized long computeIntervalDiff() {
        if (this.state == State.PAUSED) {
            return 0;
        }
        return this.mClock.elapsedRealTime() - this.startedTimestamp;
    }
}
