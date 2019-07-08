package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawm;
import com.google.android.gms.internal.ads.zzawn;
import com.google.android.gms.internal.ads.zzaws;
import com.google.android.gms.internal.ads.zzbdj;
import com.google.android.gms.internal.ads.zzbdr;
import com.google.android.gms.internal.ads.zzbfd;
import com.google.android.gms.internal.ads.zzbfr;
import com.google.android.gms.internal.ads.zzur;

@zzark
public final class zzv {
    public final zzbfr zzbms;
    public final zzbdj zzbmt;
    public final zzaws zzbmu;
    public final zzur zzbmv;

    private zzv(zzbfr zzbfr, zzbdj zzbdj, zzaws zzaws, zzur zzur) {
        this.zzbms = zzbfr;
        this.zzbmt = zzbdj;
        this.zzbmu = zzaws;
        this.zzbmv = zzur;
    }

    public static zzv zzd(Context context) {
        return new zzv(new zzbfd(), new zzbdr(), new zzawm(new zzawn()), new zzur(context));
    }
}
