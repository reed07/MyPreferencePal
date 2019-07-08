package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzv;
import java.util.concurrent.Callable;

final /* synthetic */ class zzbia implements Callable {
    private final Context zzdks;
    private final String zzekx;
    private final zzbht zzeyt;
    private final boolean zzeyu;
    private final boolean zzeyv;
    private final zzcu zzeyw;
    private final zzbbi zzeyx;
    private final zzaba zzeyy;
    private final zzbo zzeyz;
    private final zzv zzeza;

    zzbia(Context context, zzbht zzbht, String str, boolean z, boolean z2, zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv) {
        this.zzdks = context;
        this.zzeyt = zzbht;
        this.zzekx = str;
        this.zzeyu = z;
        this.zzeyv = z2;
        this.zzeyw = zzcu;
        this.zzeyx = zzbbi;
        this.zzeyy = zzaba;
        this.zzeyz = zzbo;
        this.zzeza = zzv;
    }

    public final Object call() {
        Context context = this.zzdks;
        zzbht zzbht = this.zzeyt;
        String str = this.zzekx;
        boolean z = this.zzeyu;
        boolean z2 = this.zzeyv;
        zzcu zzcu = this.zzeyw;
        zzbbi zzbbi = this.zzeyx;
        zzaba zzaba = this.zzeyy;
        zzbo zzbo = this.zzeyz;
        zzv zzv = this.zzeza;
        zzbhu zzbhu = new zzbhu();
        zzbib zzbib = new zzbib(new zzbhs(context), zzbhu, zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv);
        zzbgr zzbgr = new zzbgr(zzbib);
        zzbib.setWebChromeClient(new zzbfy(zzbgr));
        zzbhu.zzb((zzbgg) zzbgr, z2);
        return zzbgr;
    }
}
