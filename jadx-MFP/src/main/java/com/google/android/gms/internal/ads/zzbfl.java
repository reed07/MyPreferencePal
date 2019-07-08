package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

final class zzbfl implements Runnable {
    private final /* synthetic */ String zzdvo;
    private final /* synthetic */ String zzewp;
    private final /* synthetic */ int zzewq;
    private final /* synthetic */ int zzewr;
    private final /* synthetic */ boolean zzews = false;
    private final /* synthetic */ zzbfk zzewt;

    zzbfl(zzbfk zzbfk, String str, String str2, int i, int i2, boolean z) {
        this.zzewt = zzbfk;
        this.zzdvo = str;
        this.zzewp = str2;
        this.zzewq = i;
        this.zzewr = i2;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheProgress");
        hashMap.put("src", this.zzdvo);
        hashMap.put("cachedSrc", this.zzewp);
        hashMap.put("bytesLoaded", Integer.toString(this.zzewq));
        hashMap.put("totalBytes", Integer.toString(this.zzewr));
        hashMap.put("cacheReady", this.zzews ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        this.zzewt.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}
