package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread.UncaughtExceptionHandler;

final class zzbt implements UncaughtExceptionHandler {
    private final String zzapd;
    private final /* synthetic */ zzbr zzape;

    public zzbt(zzbr zzbr, String str) {
        this.zzape = zzbr;
        Preconditions.checkNotNull(str);
        this.zzapd = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzape.zzgt().zzjg().zzg(this.zzapd, th);
    }
}
