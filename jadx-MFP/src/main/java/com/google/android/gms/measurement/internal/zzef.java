package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzef implements Runnable {
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;
    private final /* synthetic */ AtomicReference zzasm;

    zzef(zzeb zzeb, AtomicReference atomicReference, zzk zzk) {
        this.zzasl = zzeb;
        this.zzasm = atomicReference;
        this.zzaqn = zzk;
    }

    public final void run() {
        synchronized (this.zzasm) {
            try {
                zzaj zzd = this.zzasl.zzasf;
                if (zzd == null) {
                    this.zzasl.zzgt().zzjg().zzby("Failed to get app instance id");
                    this.zzasm.notify();
                    return;
                }
                this.zzasm.set(zzd.zzc(this.zzaqn));
                String str = (String) this.zzasm.get();
                if (str != null) {
                    this.zzasl.zzgj().zzcp(str);
                    this.zzasl.zzgu().zzanj.zzcd(str);
                }
                this.zzasl.zzcy();
                this.zzasm.notify();
            } catch (RemoteException e) {
                try {
                    this.zzasl.zzgt().zzjg().zzg("Failed to get app instance id", e);
                    this.zzasm.notify();
                } catch (Throwable th) {
                    this.zzasm.notify();
                    throw th;
                }
            }
        }
    }
}
