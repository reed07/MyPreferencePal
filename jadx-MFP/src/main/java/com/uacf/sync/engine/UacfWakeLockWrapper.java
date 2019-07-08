package com.uacf.sync.engine;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.uacf.core.util.Ln;

public class UacfWakeLockWrapper {
    private final String name;
    private final WakeLock wakeLock;

    public UacfWakeLockWrapper(Context context, String str) {
        this.name = str;
        this.wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, str);
        this.wakeLock.setReferenceCounted(false);
    }

    public synchronized void acquire() {
        if (this.wakeLock.isHeld()) {
            Ln.d("WakeLock acquire() called for %s, but already held.", this.name);
        } else {
            this.wakeLock.acquire(600000);
            Ln.d("WakeLock %s acquired! the device will now remain awake. isHeld()=%s", this.name, String.valueOf(this.wakeLock.isHeld()));
        }
    }

    public synchronized void release() {
        if (!this.wakeLock.isHeld()) {
            Ln.d("WakeLock %s is not held, but we are going to release anyway...", this.name);
        }
        this.wakeLock.release();
        Ln.d("WakeLock %s is released. isHeld()=%s", this.name, String.valueOf(this.wakeLock.isHeld()));
    }
}
