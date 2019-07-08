package com.google.android.gms.internal.ads;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzarf implements UncaughtExceptionHandler {
    private final /* synthetic */ UncaughtExceptionHandler zzdvk;
    private final /* synthetic */ zzare zzdvl;

    zzarf(zzare zzare, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdvl = zzare;
        this.zzdvk = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzdvl.zza(thread, th);
            UncaughtExceptionHandler uncaughtExceptionHandler = this.zzdvk;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zzdvk;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
