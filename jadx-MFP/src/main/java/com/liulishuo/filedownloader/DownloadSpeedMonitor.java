package com.liulishuo.filedownloader;

import android.os.SystemClock;
import com.liulishuo.filedownloader.IDownloadSpeed.Lookup;
import com.liulishuo.filedownloader.IDownloadSpeed.Monitor;

public class DownloadSpeedMonitor implements Lookup, Monitor {
    private long mLastRefreshSofarBytes;
    private long mLastRefreshTime;
    private int mMinIntervalUpdateSpeed = 1000;
    private int mSpeed;
    private long mStartSofarBytes;
    private long mStartTime;

    public void start(long j) {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mStartSofarBytes = j;
    }

    public void end(long j) {
        if (this.mStartTime > 0) {
            long j2 = j - this.mStartSofarBytes;
            this.mLastRefreshTime = 0;
            long uptimeMillis = SystemClock.uptimeMillis() - this.mStartTime;
            if (uptimeMillis <= 0) {
                this.mSpeed = (int) j2;
            } else {
                this.mSpeed = (int) (j2 / uptimeMillis);
            }
        }
    }

    public void update(long j) {
        if (this.mMinIntervalUpdateSpeed > 0) {
            boolean z = true;
            if (this.mLastRefreshTime != 0) {
                long uptimeMillis = SystemClock.uptimeMillis() - this.mLastRefreshTime;
                if (uptimeMillis >= ((long) this.mMinIntervalUpdateSpeed) || (this.mSpeed == 0 && uptimeMillis > 0)) {
                    this.mSpeed = (int) ((j - this.mLastRefreshSofarBytes) / uptimeMillis);
                    this.mSpeed = Math.max(0, this.mSpeed);
                } else {
                    z = false;
                }
            }
            if (z) {
                this.mLastRefreshSofarBytes = j;
                this.mLastRefreshTime = SystemClock.uptimeMillis();
            }
        }
    }

    public void reset() {
        this.mSpeed = 0;
        this.mLastRefreshTime = 0;
    }
}
