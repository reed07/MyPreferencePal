package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
@VisibleForTesting
public final class zzagi {
    private final Context mContext;
    private final zzv zzbly;
    private final zzalg zzbma;
    private final zzbbi zzbob;

    zzagi(Context context, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        this.mContext = context;
        this.zzbma = zzalg;
        this.zzbob = zzbbi;
        this.zzbly = zzv;
    }

    @VisibleForTesting
    public final zzal zzbx(String str) {
        zzal zzal = new zzal(this.mContext, new zzwf(), str, this.zzbma, this.zzbob, this.zzbly);
        return zzal;
    }

    @VisibleForTesting
    public final zzal zzby(String str) {
        zzal zzal = new zzal(this.mContext.getApplicationContext(), new zzwf(), str, this.zzbma, this.zzbob, this.zzbly);
        return zzal;
    }

    @VisibleForTesting
    public final Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    @VisibleForTesting
    public final zzagi zztg() {
        return new zzagi(this.mContext.getApplicationContext(), this.zzbma, this.zzbob, this.zzbly);
    }
}
