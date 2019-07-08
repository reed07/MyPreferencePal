package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

final class zzbfm implements Runnable {
    private final /* synthetic */ String zzdvo;
    private final /* synthetic */ String zzewp;
    private final /* synthetic */ boolean zzews;
    private final /* synthetic */ zzbfk zzewt;
    private final /* synthetic */ long zzewu;
    private final /* synthetic */ long zzewv;
    private final /* synthetic */ int zzeww;
    private final /* synthetic */ int zzewx;

    zzbfm(zzbfk zzbfk, String str, String str2, long j, long j2, boolean z, int i, int i2) {
        this.zzewt = zzbfk;
        this.zzdvo = str;
        this.zzewp = str2;
        this.zzewu = j;
        this.zzewv = j2;
        this.zzews = z;
        this.zzeww = i;
        this.zzewx = i2;
    }

    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "precacheProgress");
        hashMap.put("src", this.zzdvo);
        hashMap.put("cachedSrc", this.zzewp);
        hashMap.put("bufferedDuration", Long.toString(this.zzewu));
        hashMap.put("totalDuration", Long.toString(this.zzewv));
        hashMap.put("cacheReady", this.zzews ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        hashMap.put("playerCount", Integer.toString(this.zzeww));
        hashMap.put("playerPreparedCount", Integer.toString(this.zzewx));
        this.zzewt.zza("onPrecacheEvent", (Map<String, String>) hashMap);
    }
}
