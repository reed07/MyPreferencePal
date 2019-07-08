package com.google.android.gms.internal.ads;

import android.content.Context;

@zzark
public final class zzawm implements zzaws {
    private zzawt zzegg;

    public zzawm(zzawt zzawt) {
        this.zzegg = zzawt;
    }

    public final zzawr zza(Context context, zzbbi zzbbi, zzasm zzasm) {
        if (zzasm.zzdyx == null) {
            return null;
        }
        zzawg zzawg = new zzawg(context, zzbbi, zzasm.zzdyx, zzasm.zzbde, this.zzegg);
        return zzawg;
    }
}
