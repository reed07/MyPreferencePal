package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdReason;

@zzark
public final class zzyo implements MuteThisAdReason {
    private final String zzclw;
    private zzyl zzclx;

    public zzyo(zzyl zzyl) {
        String str;
        this.zzclx = zzyl;
        try {
            str = zzyl.getDescription();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            str = null;
        }
        this.zzclw = str;
    }

    public final String getDescription() {
        return this.zzclw;
    }

    public final zzyl zzqe() {
        return this.zzclx;
    }
}
