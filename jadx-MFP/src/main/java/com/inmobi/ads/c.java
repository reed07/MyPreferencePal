package com.inmobi.ads;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.brightcove.player.model.Source.Fields;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.shared.service.device.UserAgentProviderImpl.Params;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdConfig */
public final class c extends com.inmobi.commons.core.configs.a {
    private static boolean q = true;
    private static boolean r = false;
    private static boolean s = true;
    private static boolean t = true;
    private static final String u = "c";
    private static final Object v = new Object();
    private h A = new h();
    String a = "https://i.w.inmobi.com/showad.asm";
    public String b = "https://sdktm.w.inmobi.com/sdkpubreq/v2";
    public int c = 20;
    int d = 60;
    int e = 60;
    a f;
    Map<String, a> g;
    public e h = new e();
    public i i = new i();
    public g j = new g();
    public l k = new l();
    public JSONObject l;
    public k m = new k();
    public b n = new b();
    boolean o = false;
    private List<String> w = new LinkedList();
    private d x;
    private Map<String, d> y;
    private Map<String, h> z = new HashMap();

    /* compiled from: AdConfig */
    public static final class a {
        public int a;
        public long b;
        public int c;
        public long d;
        public long e;
        public j f;
        public j g;
        public boolean h;

        public final boolean a() {
            long j = this.e;
            long j2 = this.d;
            if (j >= j2) {
                long j3 = this.b;
                if (j > j3 || j3 < j2 || !this.f.a() || !this.g.a()) {
                    return false;
                }
                int i = this.a;
                if (i >= 0 && i <= 3) {
                    long j4 = this.b;
                    if (j4 > 0 && j4 <= 86400) {
                        int i2 = this.c;
                        if (i2 > 0 && i2 <= 1000) {
                            long j5 = this.e;
                            if (j5 > 0 && j5 <= 180) {
                                long j6 = this.d;
                                if (j6 >= 0 && j6 <= 60) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                return false;
            }
            return false;
        }
    }

    /* compiled from: AdConfig */
    public static final class b {
        public int a = 3;
        public int b = 1;
        int c = 10;
        public long d = 104857600;
        public long e = 259200;
    }

    /* renamed from: com.inmobi.ads.c$c reason: collision with other inner class name */
    /* compiled from: AdConfig */
    public static final class C0043c {
        boolean a = false;
        int b = 2000;
    }

    /* compiled from: AdConfig */
    public static final class d {
        int a = 1;
        int b;
        int c;
        long d;
        public boolean e;

        public final boolean a() {
            return this.b > 0 && this.a >= 0 && this.c >= 0 && this.d >= 0;
        }
    }

    /* compiled from: AdConfig */
    public static final class e {
        public int a = 3;
        public int b = 60;
        public int c = 120;
        public int d = 500;
        public int e = 10;
        public long f = 10800;
    }

    /* compiled from: AdConfig */
    public static final class f {
        boolean a;
        String b;

        public f(boolean z, String str) {
            this.a = z;
            this.b = str;
        }
    }

    /* compiled from: AdConfig */
    public static final class g {
        public long a = 432000;
        public int b = 3;
        public int c = 60;
        public String d = "https://i.l.inmobicdn.net/sdk/sdk/500/android/mraid.js";
    }

    /* compiled from: AdConfig */
    public static final class h {
        public boolean a = false;
        public long b = 259200;
        public int c = 5;

        public final boolean a() {
            return this.b >= 0 && this.c > 0;
        }
    }

    /* compiled from: AdConfig */
    public static final class i {
        int a = 60;
        int b = ModuleDescriptor.MODULE_VERSION;
        int c = AmazonAdTask.DEFAULT_AD_HEIGHT;
        int d = 100;
        String e = "#00000000";
        public int f = Color.parseColor("#00000000");
        public boolean g = true;
        int h = 5;
        int i = 20;
        long j = 5242880;
        ArrayList<String> k = new ArrayList<>(Arrays.asList(new String[]{MimeTypes.VIDEO_MP4}));
        public boolean l = false;
        public boolean m = false;
    }

    /* compiled from: AdConfig */
    public static final class j {
        public long a;
        public int b;

        public final boolean a() {
            long j = this.a;
            if (j > 0 && j <= 60) {
                int i = this.b;
                if (i > 0 && i <= 97) {
                    return true;
                }
            }
            return false;
        }
    }

    /* compiled from: AdConfig */
    public static final class k {
        int a = 3;
        long b = 3145728;
        public long c = 31457280;
        C0043c d = new C0043c();
        public ArrayList<String> e = new ArrayList<>(Arrays.asList(new String[]{MimeTypes.VIDEO_MP4, "image/jpeg", "image/jpg", "image/gif", "image/png"}));
    }

    /* compiled from: AdConfig */
    public static final class l {
        int a = 50;
        int b = 1000;
        int c = 100;
        int d = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        int e = 67;
        int f = 50;
        public int g = 2000;
        int h = 50;
        boolean i = true;
        boolean j = true;
        f k = new f(true, "https://s3.ap-south-1.amazonaws.com/admasterinmobi/im_sdkconfig.xml");
    }

    public final String a() {
        return "ads";
    }

    private static JSONObject e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", 3);
        jSONObject2.put("maxBatchSize", 10);
        jSONObject.put("wifi", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", 3);
        jSONObject3.put("maxBatchSize", 5);
        jSONObject.put("others", jSONObject3);
        return jSONObject;
    }

    public c() {
        this.w.add("bannerDict");
        this.w.add("intDict");
        this.w.add("nativeDict");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", 1);
            jSONObject2.put("fetchLimit", 1);
            jSONObject2.put("minThreshold", 0);
            jSONObject2.put("timeToLive", 3300);
            jSONObject2.put("sortByBid", false);
            jSONObject.put("base", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("maxCacheSize", 1);
            jSONObject3.put("fetchLimit", 1);
            jSONObject3.put("minThreshold", 1);
            jSONObject3.put("timeToLive", 3300);
            jSONObject.put("banner", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("maxCacheSize", 1);
            jSONObject4.put("fetchLimit", 1);
            jSONObject4.put("minThreshold", 1);
            jSONObject4.put("timeToLive", 3300);
            jSONObject.put("int", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("maxCacheSize", 100);
            jSONObject5.put("fetchLimit", 1);
            jSONObject5.put("minThreshold", 1);
            jSONObject5.put("timeToLive", 3300);
            jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, jSONObject5);
            c(jSONObject);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("enabled", true);
            jSONObject6.put("samplingFactor", 0);
            this.l = jSONObject6;
            JSONObject jSONObject7 = new JSONObject();
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("enabled", q);
            jSONObject8.put("maxRetryCount", 1);
            jSONObject8.put("eventTTL", 14400);
            jSONObject8.put("maxEventsToPersist", 1000);
            jSONObject8.put("txLatency", 60);
            jSONObject8.put("processingInterval", 0);
            jSONObject8.put("networkType", e());
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put("enabled", r);
            jSONObject9.put("maxRetryCount", 1);
            jSONObject9.put("eventTTL", 14400);
            jSONObject9.put("maxEventsToPersist", 1000);
            jSONObject9.put("txLatency", 60);
            jSONObject9.put("processingInterval", 0);
            jSONObject9.put("networkType", e());
            JSONObject jSONObject10 = new JSONObject();
            jSONObject10.put("enabled", s);
            jSONObject10.put("maxRetryCount", 1);
            jSONObject10.put("eventTTL", 14400);
            jSONObject10.put("maxEventsToPersist", 1000);
            jSONObject10.put("txLatency", 60);
            jSONObject10.put("processingInterval", 0);
            jSONObject10.put("networkType", e());
            JSONObject jSONObject11 = new JSONObject();
            jSONObject11.put("enabled", t);
            jSONObject11.put("maxRetryCount", 1);
            jSONObject11.put("eventTTL", 14400);
            jSONObject11.put("maxEventsToPersist", 1000);
            jSONObject11.put("txLatency", 60);
            jSONObject11.put("processingInterval", 0);
            jSONObject11.put("networkType", e());
            jSONObject7.put("baseDict", jSONObject8);
            jSONObject7.put("bannerDict", jSONObject9);
            jSONObject7.put("intDict", jSONObject10);
            jSONObject7.put("nativeDict", jSONObject11);
            b(jSONObject7);
        } catch (JSONException unused) {
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        f fVar;
        super.a(jSONObject);
        if (jSONObject.has("url")) {
            this.a = jSONObject.getString("url");
        }
        if (jSONObject.has("trueRequestUrl")) {
            this.b = jSONObject.getString("trueRequestUrl");
        }
        this.c = jSONObject.getInt("minimumRefreshInterval");
        this.d = jSONObject.getInt("defaultRefreshInterval");
        this.e = jSONObject.getInt("fetchTimeout");
        this.o = jSONObject.getBoolean("flushCacheOnStart");
        c(jSONObject.getJSONObject("cache"));
        b(jSONObject.getJSONObject("trcFlagDict"));
        JSONObject jSONObject2 = jSONObject.getJSONObject(Params.PRELOAD);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("base");
        this.A = new h();
        this.A.a = jSONObject3.getBoolean("enabled");
        this.A.b = jSONObject3.getLong("placementExpiry");
        this.A.c = jSONObject3.getInt("maxPreloadedAds");
        jSONObject2.remove("base");
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject4 = jSONObject2.getJSONObject(str);
            h hVar = new h();
            hVar.a = jSONObject4.optBoolean("enabled", this.A.a);
            hVar.b = jSONObject4.optLong("placementExpiry", this.A.b);
            hVar.c = jSONObject4.optInt("maxPreloadedAds", this.A.c);
            this.z.put(str, hVar);
        }
        JSONObject jSONObject5 = jSONObject.getJSONObject("imai");
        this.h.a = jSONObject5.getInt("maxRetries");
        this.h.b = jSONObject5.getInt("pingInterval");
        this.h.c = jSONObject5.getInt("pingTimeout");
        this.h.d = jSONObject5.getInt("maxDbEvents");
        this.h.e = jSONObject5.getInt("maxEventBatch");
        this.h.f = jSONObject5.getLong("pingCacheExpiry");
        JSONObject jSONObject6 = jSONObject.getJSONObject("rendering");
        this.i.a = jSONObject6.getInt("renderTimeout");
        this.i.c = jSONObject6.getInt("picHeight");
        this.i.b = jSONObject6.getInt("picWidth");
        this.i.d = jSONObject6.getInt("picQuality");
        this.i.e = jSONObject6.getString("webviewBackground");
        this.i.g = jSONObject6.getBoolean("autoRedirectionEnforcement");
        this.i.h = jSONObject6.getInt("maxVibrationDuration");
        this.i.i = jSONObject6.getInt("maxVibrationPatternLength");
        this.i.m = jSONObject6.optBoolean("enablePubMuteControl", false);
        this.i.j = (long) jSONObject6.getJSONObject("savecontent").getInt("maxSaveSize");
        synchronized (v) {
            this.i.k.clear();
            JSONArray jSONArray = jSONObject6.getJSONObject("savecontent").getJSONArray("allowedContentType");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.i.k.add(jSONArray.getString(i2));
            }
        }
        this.i.l = jSONObject6.getBoolean("shouldRenderPopup");
        JSONObject jSONObject7 = jSONObject.getJSONObject(AdType.MRAID);
        this.j.a = jSONObject7.getLong("expiry");
        this.j.b = jSONObject7.getInt("maxRetries");
        this.j.c = jSONObject7.getInt("retryInterval");
        this.j.d = jSONObject7.getString("url");
        if (jSONObject.has("telemetry")) {
            this.l = jSONObject.getJSONObject("telemetry");
        }
        JSONObject jSONObject8 = jSONObject.getJSONObject("viewability");
        this.k.a = jSONObject8.getInt("impressionMinPercentageViewed");
        this.k.b = jSONObject8.getInt("impressionMinTimeViewed");
        this.k.e = jSONObject8.optInt("displayMinPercentageAnimate", 67);
        this.k.c = jSONObject8.optInt("visibilityThrottleMillis", 100);
        this.k.d = jSONObject8.optInt("impressionPollIntervalMillis", Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.k.i = jSONObject8.optBoolean("moatEnabled", false);
        this.k.j = jSONObject8.optBoolean("iasEnabled", false);
        l lVar = this.k;
        JSONObject optJSONObject = jSONObject8.optJSONObject("mmaConfig");
        if (optJSONObject != null) {
            fVar = new f(optJSONObject.optBoolean("enabled", false), optJSONObject.optString("xmlConfigUrl", ""));
        } else {
            fVar = new f(true, "https://s3.ap-south-1.amazonaws.com/admasterinmobi/im_sdkconfig.xml");
        }
        lVar.k = fVar;
        JSONObject jSONObject9 = jSONObject8.getJSONObject("video");
        this.k.f = jSONObject9.getInt("impressionMinPercentageViewed");
        this.k.g = jSONObject9.getInt("impressionMinTimeViewed");
        this.k.h = jSONObject9.optInt("videoMinPercentagePlay", 50);
        JSONObject jSONObject10 = jSONObject.getJSONObject("vastVideo");
        this.m.a = jSONObject10.getInt("maxWrapperLimit");
        this.m.b = jSONObject10.getLong("optimalVastVideoSize");
        this.m.c = jSONObject10.getLong("vastMaxAssetSize");
        synchronized (v) {
            this.m.e.clear();
            JSONArray jSONArray2 = jSONObject10.getJSONArray("allowedContentType");
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                this.m.e.add(jSONArray2.getString(i3));
            }
        }
        C0043c cVar = this.m.d;
        JSONObject jSONObject11 = jSONObject10.getJSONObject(Fields.BIT_RATE);
        cVar.a = jSONObject11.getBoolean("bitrate_mandatory");
        cVar.b = jSONObject11.getInt("headerTimeout");
        JSONObject jSONObject12 = jSONObject.getJSONObject("assetCache");
        this.n.b = jSONObject12.getInt("retryInterval");
        this.n.a = jSONObject12.getInt("maxRetries");
        this.n.c = jSONObject12.getInt("maxCachedAssets");
        this.n.d = (long) jSONObject12.getInt("maxCacheSize");
        this.n.e = jSONObject12.getLong("timeToLive");
    }

    private void b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("baseDict");
        this.f = new a();
        this.f.h = jSONObject2.getBoolean("enabled");
        this.f.a = jSONObject2.getInt("maxRetryCount");
        this.f.b = jSONObject2.getLong("eventTTL");
        this.f.c = jSONObject2.getInt("maxEventsToPersist");
        this.f.d = jSONObject2.getLong("processingInterval");
        this.f.e = jSONObject2.getLong("txLatency");
        a(jSONObject2.getJSONObject("networkType"), this.f);
        jSONObject.remove("baseDict");
        this.g = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (str != null) {
                List<String> list = this.w;
                if (list != null && list.contains(str)) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject(str);
                    a aVar = new a();
                    aVar.h = jSONObject3.optBoolean("enabled", this.f.h);
                    aVar.a = jSONObject3.optInt("maxRetryCount", this.f.a);
                    aVar.b = jSONObject3.optLong("eventTTL", this.f.b);
                    aVar.c = jSONObject3.optInt("maxEventsToPersist", this.f.c);
                    aVar.d = jSONObject3.optLong("processingInterval", this.f.d);
                    aVar.e = jSONObject3.optLong("txLatency", this.f.e);
                    a(jSONObject3.getJSONObject("networkType"), aVar);
                    this.g.put(str, aVar);
                }
            }
        }
    }

    private static void a(JSONObject jSONObject, a aVar) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            j jVar = new j();
            jVar.a = jSONObject2.getLong("retryInterval");
            jVar.b = jSONObject2.getInt("maxBatchSize");
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1068855134) {
                if (hashCode != -1006804125) {
                    if (hashCode == 3649301 && str.equals("wifi")) {
                        c2 = 0;
                    }
                } else if (str.equals("others")) {
                    c2 = 2;
                }
            } else if (str.equals("mobile")) {
                c2 = 1;
            }
            if (c2 != 0) {
                aVar.f = jVar;
            } else {
                aVar.g = jVar;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.x = new d();
        this.x.a = jSONObject2.getInt("maxCacheSize");
        this.x.b = jSONObject2.getInt("fetchLimit");
        this.x.c = jSONObject2.getInt("minThreshold");
        this.x.d = jSONObject2.getLong("timeToLive");
        this.x.e = jSONObject2.getBoolean("sortByBid");
        jSONObject.remove("base");
        this.y = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject3 = jSONObject.getJSONObject(str);
            d dVar = new d();
            dVar.a = jSONObject3.optInt("maxCacheSize", this.x.a);
            dVar.b = jSONObject3.optInt("fetchLimit", this.x.b);
            dVar.c = jSONObject3.optInt("minThreshold", this.x.c);
            dVar.d = jSONObject3.optLong("timeToLive", this.x.d);
            dVar.e = jSONObject3.optBoolean("sortByBid", this.x.e);
            this.y.put(str, dVar);
        }
    }

    public final JSONObject b() throws JSONException {
        JSONObject b2 = super.b();
        b2.put("url", this.a);
        b2.put("trueRequestUrl", this.b);
        b2.put("minimumRefreshInterval", this.c);
        b2.put("defaultRefreshInterval", this.d);
        b2.put("fetchTimeout", this.e);
        b2.put("flushCacheOnStart", this.o);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", this.x.a);
        jSONObject2.put("fetchLimit", this.x.b);
        jSONObject2.put("minThreshold", this.x.c);
        jSONObject2.put("timeToLive", this.x.d);
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.y.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            d dVar = (d) entry.getValue();
            jSONObject3.put("maxCacheSize", dVar.a);
            jSONObject3.put("fetchLimit", dVar.b);
            jSONObject3.put("minThreshold", dVar.c);
            jSONObject3.put("timeToLive", dVar.d);
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        b2.put("cache", jSONObject);
        b2.put("trcFlagDict", g());
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("maxRetries", this.h.a);
        jSONObject4.put("pingInterval", this.h.b);
        jSONObject4.put("pingTimeout", this.h.c);
        jSONObject4.put("maxDbEvents", this.h.d);
        jSONObject4.put("maxEventBatch", this.h.e);
        jSONObject4.put("pingCacheExpiry", this.h.f);
        b2.put("imai", jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("renderTimeout", this.i.a);
        jSONObject5.put("picWidth", this.i.b);
        jSONObject5.put("picHeight", this.i.c);
        jSONObject5.put("picQuality", this.i.d);
        jSONObject5.put("webviewBackground", this.i.e);
        jSONObject5.put("autoRedirectionEnforcement", this.i.g);
        jSONObject5.put("maxVibrationDuration", this.i.h);
        jSONObject5.put("maxVibrationPatternLength", this.i.i);
        jSONObject5.put("enablePubMuteControl", this.i.m);
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("maxSaveSize", this.i.j);
        jSONObject6.put("allowedContentType", new JSONArray(this.i.k));
        jSONObject5.put("savecontent", jSONObject6);
        jSONObject5.put("shouldRenderPopup", this.i.l);
        b2.put("rendering", jSONObject5);
        JSONObject jSONObject7 = new JSONObject();
        jSONObject7.put("expiry", this.j.a);
        jSONObject7.put("maxRetries", this.j.b);
        jSONObject7.put("retryInterval", this.j.c);
        jSONObject7.put("url", this.j.d);
        b2.put(AdType.MRAID, jSONObject7);
        JSONObject jSONObject8 = new JSONObject();
        jSONObject8.put("impressionMinPercentageViewed", this.k.a);
        jSONObject8.put("impressionMinTimeViewed", this.k.b);
        jSONObject8.put("displayMinPercentageAnimate", this.k.e);
        jSONObject8.put("visibilityThrottleMillis", this.k.c);
        jSONObject8.put("impressionPollIntervalMillis", this.k.d);
        jSONObject8.put("moatEnabled", this.k.i);
        jSONObject8.put("iasEnabled", this.k.j);
        f fVar = this.k.k;
        JSONObject jSONObject9 = new JSONObject();
        jSONObject9.put("enabled", fVar.a);
        jSONObject9.put("xmlConfigUrl", fVar.b);
        jSONObject8.put("mmaConfig", jSONObject9);
        JSONObject jSONObject10 = new JSONObject();
        jSONObject10.put("impressionMinPercentageViewed", this.k.f);
        jSONObject10.put("impressionMinTimeViewed", this.k.g);
        jSONObject10.put("videoMinPercentagePlay", this.k.h);
        jSONObject8.put("video", jSONObject10);
        b2.put("viewability", jSONObject8);
        b2.put(Params.PRELOAD, f());
        JSONObject jSONObject11 = new JSONObject();
        jSONObject11.put("maxWrapperLimit", this.m.a);
        jSONObject11.put("optimalVastVideoSize", this.m.b);
        jSONObject11.put("vastMaxAssetSize", this.m.c);
        jSONObject11.put("allowedContentType", new JSONArray(this.m.e));
        C0043c cVar = this.m.d;
        JSONObject jSONObject12 = new JSONObject();
        jSONObject12.put("headerTimeout", cVar.b);
        jSONObject12.put("bitrate_mandatory", cVar.a);
        jSONObject11.put(Fields.BIT_RATE, jSONObject12);
        b2.put("vastVideo", jSONObject11);
        JSONObject jSONObject13 = new JSONObject();
        jSONObject13.put("retryInterval", this.n.b);
        jSONObject13.put("maxRetries", this.n.a);
        jSONObject13.put("maxCachedAssets", this.n.c);
        jSONObject13.put("maxCacheSize", this.n.d);
        jSONObject13.put("timeToLive", this.n.e);
        b2.put("assetCache", jSONObject13);
        JSONObject jSONObject14 = this.l;
        if (jSONObject14 != null) {
            b2.put("telemetry", jSONObject14);
        }
        return b2;
    }

    private JSONObject f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.A.a);
        jSONObject2.put("placementExpiry", this.A.b);
        jSONObject2.put("maxPreloadedAds", this.A.c);
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.z.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            h hVar = (h) entry.getValue();
            jSONObject3.put("enabled", hVar.a);
            jSONObject3.put("placementExpiry", hVar.b);
            jSONObject3.put("maxPreloadedAds", hVar.c);
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    private JSONObject g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.f.h);
        jSONObject2.put("maxRetryCount", this.f.a);
        jSONObject2.put("eventTTL", this.f.b);
        jSONObject2.put("maxEventsToPersist", this.f.c);
        jSONObject2.put("processingInterval", this.f.d);
        jSONObject2.put("txLatency", this.f.e);
        jSONObject2.put("networkType", a(this.f));
        jSONObject.put("baseDict", jSONObject2);
        for (Entry entry : this.g.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            a aVar = (a) entry.getValue();
            jSONObject3.put("enabled", aVar.h);
            jSONObject3.put("maxRetryCount", aVar.a);
            jSONObject3.put("eventTTL", aVar.b);
            jSONObject3.put("maxEventsToPersist", aVar.c);
            jSONObject3.put("processingInterval", aVar.d);
            jSONObject3.put("txLatency", aVar.e);
            jSONObject3.put("networkType", a(aVar));
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    private static JSONObject a(a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        j jVar = aVar.g;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", jVar.a);
        jSONObject2.put("maxBatchSize", jVar.b);
        jSONObject.put("wifi", jSONObject2);
        j jVar2 = aVar.f;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", jVar2.a);
        jSONObject3.put("maxBatchSize", jVar2.b);
        jSONObject.put("others", jSONObject3);
        return jSONObject;
    }

    public final boolean c() {
        if ((this.a.startsWith("http://") || this.a.startsWith("https://")) && (this.b.startsWith("http://") || this.b.startsWith("https://"))) {
            int i2 = this.c;
            if (i2 >= 0) {
                int i3 = this.d;
                if (i3 >= 0 && i2 <= i3 && this.e > 0) {
                    d dVar = this.x;
                    if (dVar == null || !dVar.a()) {
                        return false;
                    }
                    for (Entry value : this.y.entrySet()) {
                        if (!((d) value.getValue()).a()) {
                            return false;
                        }
                    }
                    a aVar = this.f;
                    if (aVar == null || !aVar.a()) {
                        return false;
                    }
                    for (Entry value2 : this.g.entrySet()) {
                        if (!((a) value2.getValue()).a()) {
                            return false;
                        }
                    }
                    if (this.h.d < 0 || this.h.e < 0 || this.h.a < 0 || this.h.b < 0 || this.h.c <= 0 || this.h.f <= 0 || this.j.a < 0 || this.j.c < 0 || this.j.b < 0 || ((!this.j.d.startsWith("http://") && !this.j.d.startsWith("https://")) || this.i.a < 0 || this.i.c < 0 || this.i.b < 0 || this.i.d < 0 || this.i.h < 0 || this.i.i < 0 || this.i.j < 0 || this.i.e == null || this.i.e.trim().length() == 0)) {
                        return false;
                    }
                    try {
                        this.i.f = Color.parseColor(this.i.e);
                        if (this.j.b >= 0 && this.j.c >= 0 && this.j.d != null && this.j.d.trim().length() != 0 && this.k.a > 0 && this.k.a <= 100 && this.k.b >= 0 && this.k.e > 0 && this.k.e <= 100 && this.k.f > 0 && this.k.f <= 100 && this.k.g >= 0 && this.k.h > 0 && this.k.h <= 100 && this.k.c >= 50 && this.k.c * 5 <= this.k.b && this.k.d >= 50 && this.k.d * 4 <= this.k.b) {
                            f fVar = this.k.k;
                            if (fVar.b.startsWith(Constants.HTTP) || fVar.b.startsWith(Constants.HTTPS)) {
                                h hVar = this.A;
                                if (hVar == null || !hVar.a()) {
                                    return false;
                                }
                                for (Entry value3 : this.z.entrySet()) {
                                    if (!((h) value3.getValue()).a()) {
                                        return false;
                                    }
                                }
                                if (this.m.b > 31457280 || this.m.b <= 0 || this.m.a < 0 || this.m.c <= 0 || this.m.c > 31457280 || this.n.b < 0 || this.n.c > 20 || this.n.c < 0 || this.n.e < 0 || this.n.d < 0 || this.n.a < 0) {
                                    return false;
                                }
                                return true;
                            }
                        }
                        return false;
                    } catch (IllegalArgumentException unused) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new c();
    }

    public final d a(String str) {
        d dVar = (d) this.y.get(str);
        return dVar == null ? this.x : dVar;
    }

    @NonNull
    public final a b(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("Dict");
        a aVar = (a) this.g.get(sb.toString());
        return aVar == null ? this.f : aVar;
    }

    public final h c(String str) {
        h hVar = (h) this.z.get(str);
        return hVar == null ? this.A : hVar;
    }
}
