package com.google.android.gms.internal.ads;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzarg implements UncaughtExceptionHandler {
    private final /* synthetic */ zzare zzdvl;
    private final /* synthetic */ UncaughtExceptionHandler zzdvm;

    zzarg(zzare zzare, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdvl = zzare;
        this.zzdvm = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzdvl.zza(thread, th);
            UncaughtExceptionHandler uncaughtExceptionHandler = this.zzdvm;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zzdvm;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
