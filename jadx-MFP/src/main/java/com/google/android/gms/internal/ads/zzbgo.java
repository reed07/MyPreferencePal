package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;
import java.util.concurrent.Callable;

final /* synthetic */ class zzbgo implements Callable {
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
    private final zzum zzezb;

    zzbgo(Context context, zzbht zzbht, String str, boolean z, boolean z2, zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv, zzum zzum) {
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
        this.zzezb = zzum;
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
        zzum zzum = this.zzezb;
        zzbgr zzbgr = new zzbgr(zzbgt.zzb(context, zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv, zzum));
        zzbgr.setWebViewClient(zzbv.zzlh().zza(zzbgr, zzum, z2));
        zzbgr.setWebChromeClient(new zzbfy(zzbgr));
        return zzbgr;
    }
}
