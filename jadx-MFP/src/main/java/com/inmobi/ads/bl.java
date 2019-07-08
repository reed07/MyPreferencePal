package com.inmobi.ads;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.c.d;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.ads.cache.b;
import com.inmobi.ads.cache.f;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PrefetchAdStore */
public class bl implements com.inmobi.ads.e.a {
    /* access modifiers changed from: private */
    public static final String c = "bl";
    boolean a = false;
    @NonNull
    d b;
    /* access modifiers changed from: private */
    public final a d;
    /* access modifiers changed from: private */
    public final d e;
    /* access modifiers changed from: private */
    public f f;
    private long g = 0;
    private boolean h;
    private final f i = new f() {
        public final void a(b bVar) {
            String str;
            bl.c;
            StringBuilder sb = new StringBuilder("onAssetsFetchFailure of batch ");
            if (bVar == null) {
                str = null;
            } else {
                str = bVar.toString();
            }
            sb.append(str);
            ArrayList<Long> arrayList = new ArrayList<>();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put(AbstractEvent.SIZE, Long.valueOf(c.a(aVar.e)));
                    bl.this.d.a("VideoAssetDownloadFailed", hashMap);
                    for (a aVar2 : bl.this.e.b(aVar.d, bl.this.f == null ? null : bl.this.f.c)) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(bl.this.f.a))) {
                arrayList.add(Long.valueOf(bl.this.f.a));
            }
            for (Long longValue : arrayList) {
                bl.this.d.b(longValue.longValue(), new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
            }
        }

        public final void b(b bVar) {
            String str;
            bl.c;
            StringBuilder sb = new StringBuilder("onAssetsFetchSuccess of batch ");
            if (bVar == null) {
                str = null;
            } else {
                str = bVar.toString();
            }
            sb.append(str);
            ArrayList<Long> arrayList = new ArrayList<>();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put(AbstractEvent.SIZE, Long.valueOf(c.a(aVar.e)));
                    hashMap.put("clientRequestId", bVar.f);
                    if (aVar.j) {
                        bl.this.d.a("GotCachedVideoAsset", hashMap);
                    } else {
                        bl.this.d.a("VideoAssetDownloaded", hashMap);
                    }
                    List<a> a2 = bl.this.e.a(aVar.d, bl.this.f == null ? null : bl.this.f.c);
                    bl.c;
                    StringBuilder sb2 = new StringBuilder("Found ");
                    sb2.append(a2.size());
                    sb2.append(" ads mapping to this asset");
                    for (a aVar2 : a2) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(bl.this.f.a))) {
                arrayList.add(Long.valueOf(bl.this.f.a));
            }
            for (Long longValue : arrayList) {
                long longValue2 = longValue.longValue();
                bl.c;
                StringBuilder sb3 = new StringBuilder("Notifying ad unit with placement ID (");
                sb3.append(longValue2);
                sb3.append(")");
                bl.this.d.a(longValue2);
            }
        }
    };

    /* compiled from: PrefetchAdStore */
    public interface a {
        void a(long j);

        void a(String str, Map<String, Object> map);

        void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    public bl(a aVar, @NonNull d dVar) {
        this.d = aVar;
        this.e = d.a();
        this.b = dVar;
    }

    private boolean a(int i2) {
        return SystemClock.elapsedRealtime() - this.g < ((long) (i2 * 1000));
    }

    public final void a(g gVar) {
        List c2 = c(gVar);
        if (c2 == null) {
            new StringBuilder("Could not parse ad response:").append(gVar.a.b());
            if (!this.a) {
                this.d.b(this.f.a, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        } else if (c2.size() == 0) {
            new StringBuilder("Ad response received but no ad available:").append(gVar.a.b());
            HashMap hashMap = new HashMap();
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerNoFill", hashMap);
            if (!this.a) {
                this.d.b(this.f.a, new InMobiAdRequestStatus(StatusCode.NO_FILL));
            }
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("numberOfAdsReturned", Integer.valueOf(c2.size()));
            hashMap2.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap2.put("isPreloaded", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            hashMap2.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerFill", hashMap2);
            if (!"HTML".equalsIgnoreCase(((a) c2.get(0)).e()) || !AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(this.f.e)) {
                this.e.a(c2, this.f.a, this.b.a, this.f.e, this.f.j, com.inmobi.ads.c.a.a(this.f.g), null);
                a(c2);
                if (!this.a && !this.h) {
                    this.d.a(this.f.a);
                }
                return;
            }
            if (!this.a) {
                this.d.b(this.f.a, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        }
    }

    public final void b(g gVar) {
        if (!this.a) {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(gVar.a.b.a.getValue()));
            hashMap.put("reason", gVar.a.b.b);
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerError", hashMap);
            this.d.b(this.f.a, gVar.b);
        }
    }

    private List<a> c(g gVar) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(gVar.a.b()).getJSONArray("ads");
            if (jSONArray != null) {
                int min = Math.min(gVar.c.d, jSONArray.length());
                for (int i2 = 0; i2 < min; i2++) {
                    a a2 = C0040a.a(jSONArray.getJSONObject(i2), gVar.c.a, gVar.c.e, gVar.c.c, gVar.c.i, gVar.c.j, gVar.c.k);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                if (min <= 0 || !arrayList.isEmpty()) {
                    return arrayList;
                }
                return null;
            }
        } catch (JSONException e2) {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e2.getLocalizedMessage());
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            hashMap.put("isPreloaded", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerError", hashMap);
            arrayList = null;
        }
        return arrayList;
    }

    private void a(List<a> list) {
        if (list != null && list.size() > 0) {
            a aVar = (a) list.get(0);
            if (aVar != null) {
                Set d2 = aVar.d();
                if (d2.size() == 0) {
                    this.d.a(this.f.a);
                    return;
                }
                AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar.h, d2, this.h ? this.i : null));
            }
            for (a aVar2 : list.subList(1, list.size())) {
                if (aVar2 != null && aVar2.e().equalsIgnoreCase("inmobiJson")) {
                    Set d3 = aVar2.d();
                    if (d3.size() != 0) {
                        AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar2.h, d3, (f) null));
                    }
                }
            }
        }
    }

    @Nullable
    public final String a(f fVar, boolean z, int i2) throws com.inmobi.ads.a.a {
        String str;
        if (e.b()) {
            d.c();
        }
        this.a = false;
        this.f = fVar;
        this.h = z;
        b.b();
        List c2 = this.e.c(this.f.a, this.f.c, this.f.j, com.inmobi.ads.c.a.a(this.f.g));
        int size = c2.size();
        if (size == 0) {
            this.a = false;
            if (!a(i2)) {
                return a(this.f);
            }
            throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
        }
        if (size < this.b.c) {
            this.a = true;
            if (!z) {
                this.d.a(this.f.a);
            }
            a(c2);
            if (!a(i2)) {
                str = a(this.f);
            } else {
                throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
        } else {
            this.a = true;
            String str2 = ((a) c2.get(0)).h;
            if (!z) {
                this.d.a(this.f.a);
            }
            a(c2);
            str = str2;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        hashMap.put("isPreloaded", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.d.a("AdCacheAdRequested", hashMap);
        return str;
    }

    @NonNull
    private String a(f fVar) {
        if (fVar != null) {
            Map<String, String> map = fVar.h;
            if (map == null) {
                map = new HashMap<>();
            }
            if (!map.containsKey("preload-request")) {
                map.put("preload-request", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                fVar.h = map;
            }
        }
        this.g = SystemClock.elapsedRealtime();
        new e(fVar, this).a();
        HashMap hashMap = new HashMap();
        hashMap.put("isPreloaded", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("clientRequestId", fVar.i);
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        this.d.a("ServerCallInitiated", hashMap);
        return fVar.i;
    }
}
