package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class zzeo implements Runnable {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ String zzadz;
    private final /* synthetic */ String zzagj;
    private final /* synthetic */ zzk zzaqn;
    private final /* synthetic */ zzeb zzasl;
    private final /* synthetic */ AtomicReference zzasm;

    zzeo(zzeb zzeb, AtomicReference atomicReference, String str, String str2, String str3, zzk zzk) {
        this.zzasl = zzeb;
        this.zzasm = atomicReference;
        this.zzagj = str;
        this.zzads = str2;
        this.zzadz = str3;
        this.zzaqn = zzk;
    }

    public final void run() {
        synchronized (this.zzasm) {
            try {
                zzaj zzd = this.zzasl.zzasf;
                if (zzd == null) {
                    this.zzasl.zzgt().zzjg().zzd("Failed to get conditional properties", zzas.zzbw(this.zzagj), this.zzads, this.zzadz);
                    this.zzasm.set(Collections.emptyList());
                    this.zzasm.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zzagj)) {
                    this.zzasm.set(zzd.zza(this.zzads, this.zzadz, this.zzaqn));
                } else {
                    this.zzasm.set(zzd.zze(this.zzagj, this.zzads, this.zzadz));
                }
                this.zzasl.zzcy();
                this.zzasm.notify();
            } catch (RemoteException e) {
                try {
                    this.zzasl.zzgt().zzjg().zzd("Failed to get conditional properties", zzas.zzbw(this.zzagj), this.zzads, e);
                    this.zzasm.set(Collections.emptyList());
                    this.zzasm.notify();
                } catch (Throwable th) {
                    this.zzasm.notify();
                    throw th;
                }
            }
        }
    }
}
