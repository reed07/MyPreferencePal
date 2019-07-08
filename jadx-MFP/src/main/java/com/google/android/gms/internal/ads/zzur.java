package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzark
public final class zzur {
    @VisibleForTesting
    zzfa zzcbz;
    @VisibleForTesting
    boolean zzcca;

    public final zzuv zzg(byte[] bArr) {
        return new zzuv(this, bArr);
    }

    public zzur(Context context, String str, String str2) {
        zzaan.initialize(context);
        try {
            this.zzcbz = (zzfa) zzbbe.zza(context, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzus.zzccb);
            ObjectWrapper.wrap(context);
            this.zzcbz.zza(ObjectWrapper.wrap(context), str, null);
            this.zzcca = true;
        } catch (RemoteException | zzbbg | NullPointerException unused) {
            zzbbd.zzdn("Cannot dynamite load clearcut");
        }
    }

    public zzur(Context context) {
        zzaan.initialize(context);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwp)).booleanValue()) {
            try {
                this.zzcbz = (zzfa) zzbbe.zza(context, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzut.zzccb);
                ObjectWrapper.wrap(context);
                this.zzcbz.zza(ObjectWrapper.wrap(context), "GMA_SDK");
                this.zzcca = true;
            } catch (RemoteException | zzbbg | NullPointerException unused) {
                zzbbd.zzdn("Cannot dynamite load clearcut");
            }
        }
    }

    public zzur() {
    }
}
