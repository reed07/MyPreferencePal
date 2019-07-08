package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;

@zzark
public final class zzbah extends zzaxv {
    private final String zzag;
    private final zzbbh zzenn;

    public zzbah(Context context, String str, String str2) {
        this(str2, zzbv.zzlf().zzo(context, str));
    }

    public final void onStop() {
    }

    private zzbah(String str, String str2) {
        this.zzenn = new zzbbh(str2);
        this.zzag = str;
    }

    public final void zzki() {
        this.zzenn.zzed(this.zzag);
    }
}
