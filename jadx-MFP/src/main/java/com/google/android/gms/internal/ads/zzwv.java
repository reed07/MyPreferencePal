package com.google.android.gms.internal.ads;

import java.util.Random;

@zzark
public final class zzwv extends zzya {
    private Object mLock = new Object();
    private final Random zzcls = new Random();
    private long zzclt;

    public zzwv() {
        zzqd();
    }

    public final void zzqd() {
        synchronized (this.mLock) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzcls.nextInt()) + 2147483648L;
                if (j != this.zzclt && j != 0) {
                    break;
                }
            }
            this.zzclt = j;
        }
    }

    public final long getValue() {
        return this.zzclt;
    }
}
