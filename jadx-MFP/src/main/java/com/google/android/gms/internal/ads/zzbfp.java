package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

final class zzbfp implements Runnable {
    private final /* synthetic */ String val$message;
    private final /* synthetic */ String zzdvo;
    private final /* synthetic */ String zzewp;
    private final /* synthetic */ zzbfk zzewt;
    private final /* synthetic */ String zzewy;

    zzbfp(zzbfk zzbfk, String str, String str2, String str3, String str4) {
        this.zzewt = zzbfk;
        this.zzdvo = str;
        this.zzewp = str2;
        this.zzewy = str3;
        this.val$message = str4;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheCanceled");
        hashMap.put("src", this.zzdvo);
        if (!TextUtils.isEmpty(this.zzewp)) {
            hashMap.put("cachedSrc", this.zzewp);
        }
        hashMap.put("type", zzbfk.zzez(this.zzewy));
        hashMap.put("reason", this.zzewy);
        if (!TextUtils.isEmpty(this.val$message)) {
            hashMap.put("message", this.val$message);
        }
        this.zzewt.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}
