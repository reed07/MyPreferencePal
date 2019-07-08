package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzed implements Runnable {
    private final /* synthetic */ boolean zzaeg;
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;
    private final /* synthetic */ AtomicReference zzasm;

    zzed(zzeb zzeb, AtomicReference atomicReference, zzk zzk, boolean z) {
        this.zzasl = zzeb;
        this.zzasm = atomicReference;
        this.zzaqn = zzk;
        this.zzaeg = z;
    }

    public final void run() {
        synchronized (this.zzasm) {
            try {
                zzaj zzd = this.zzasl.zzasf;
                if (zzd == null) {
                    this.zzasl.zzgt().zzjg().zzby("Failed to get user properties");
                    this.zzasm.notify();
                    return;
                }
                this.zzasm.set(zzd.zza(this.zzaqn, this.zzaeg));
                this.zzasl.zzcy();
                this.zzasm.notify();
            } catch (RemoteException e) {
                try {
                    this.zzasl.zzgt().zzjg().zzg("Failed to get user properties", e);
                    this.zzasm.notify();
                } catch (Throwable th) {
                    this.zzasm.notify();
                    throw th;
                }
            }
        }
    }
}
