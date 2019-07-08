package com.myfitnesspal.shared.ui.activity.busymanager;

import android.content.Context;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.Ln;

public class BusyManagerImpl implements BusyManager {
    private final Object busyLock = new Object();
    private int busyMask = 0;
    private Context context;

    public boolean isBusy() {
        return getBusyMask() != 0;
    }

    public boolean isBusy(int i) {
        return (getBusyMask() & i) == i;
    }

    public void setBusy(boolean z) {
        setBusy(-1, z);
    }

    public void setBusy(int i, boolean z) {
        int busyMask2 = getBusyMask();
        int i2 = z ? busyMask2 | i : (~i) & busyMask2;
        Ln.d("SETBUSY %s: current = %08X, busy = %s, mask = %08X, newValue = %08X", this.context, Integer.valueOf(busyMask2), Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2));
        setBusyMask(i2);
        MaterialUtils.showIndeterminateProgress(this.context, isBusy());
    }

    public Context getContext() {
        return this.context;
    }

    public BusyManager setContext(Context context2) {
        this.context = context2;
        return this;
    }

    private int getBusyMask() {
        int i;
        synchronized (this.busyLock) {
            i = this.busyMask;
        }
        return i;
    }

    private void setBusyMask(int i) {
        synchronized (this.busyLock) {
            Ln.d("SETBUSY: setBusyMask, current = %X new value = %X", Integer.valueOf(this.busyMask), Integer.valueOf(i));
            this.busyMask = i;
        }
    }
}
