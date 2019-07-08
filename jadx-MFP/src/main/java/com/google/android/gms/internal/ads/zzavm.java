package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzavm implements Runnable {
    private final /* synthetic */ zzwb zzbod;
    private final /* synthetic */ zzalj zzeey;
    private final /* synthetic */ zzavk zzeez;
    private final /* synthetic */ zzavs zzefa;

    zzavm(zzavk zzavk, zzalj zzalj, zzwb zzwb, zzavs zzavs) {
        this.zzeez = zzavk;
        this.zzeey = zzalj;
        this.zzbod = zzwb;
        this.zzefa = zzavs;
    }

    public final void run() {
        try {
            this.zzeey.zza(ObjectWrapper.wrap(this.zzeez.mContext), this.zzbod, (String) null, (zzavz) this.zzefa, this.zzeez.zzeer);
        } catch (RemoteException e) {
            String str = "Fail to initialize adapter ";
            String valueOf = String.valueOf(this.zzeez.zzdml);
            zzaxz.zzc(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
            zzavk zzavk = this.zzeez;
            zzavk.zza(zzavk.zzdml, 0);
        }
    }
}
