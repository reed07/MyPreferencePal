package com.facebook.ads.internal.w.b;

import org.json.JSONArray;

public enum b {
    APP_AD(0),
    LINK_AD(1),
    APP_AD_V2(2),
    LINK_AD_V2(3),
    APP_ENGAGEMENT_AD(4),
    AD_CHOICES(5),
    JS_TRIGGER(6),
    JS_TRIGGER_NO_AUTO_IMP_LOGGING(7),
    VIDEO_AD(8),
    INLINE_VIDEO_AD(9),
    BANNER_TO_INTERSTITIAL(10),
    NATIVE_CLOSE_BUTTON(11),
    UNIFIED_LOGGING(16),
    HTTP_LINKS(17);
    
    public static final b[] o = null;
    private static final String q = null;
    private final int p;

    static {
        int i;
        b bVar;
        b bVar2;
        b bVar3;
        b bVar4;
        b bVar5;
        b bVar6;
        b bVar7;
        o = new b[]{bVar, bVar2, bVar3, bVar4, bVar5, bVar6, bVar7};
        JSONArray jSONArray = new JSONArray();
        for (b a : o) {
            jSONArray.put(a.a());
        }
        q = jSONArray.toString();
    }

    private b(int i) {
        this.p = i;
    }

    public static String b() {
        return q;
    }

    public int a() {
        return this.p;
    }

    public String toString() {
        return String.valueOf(this.p);
    }
}
