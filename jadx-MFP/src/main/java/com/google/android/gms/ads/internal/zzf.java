package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import java.util.concurrent.Callable;

final class zzf implements Callable<String> {
    private final /* synthetic */ zzc zzbmd;

    zzf(zzc zzc) {
        this.zzbmd = zzc;
    }

    public final /* synthetic */ Object call() throws Exception {
        String str = "";
        CookieManager zzba = zzbv.zzlh().zzba(this.zzbmd.zzbls.zzsp);
        return zzba != null ? zzba.getCookie("googleads.g.doubleclick.net") : str;
    }
}
