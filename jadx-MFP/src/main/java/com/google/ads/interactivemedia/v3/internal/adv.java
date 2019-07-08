package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.CuePoint;
import com.google.ads.interactivemedia.v3.impl.data.c;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class adv {
    public final AdEventType a;
    public final c b;
    public Map<String, String> c;
    public List<CuePoint> d;
    AdProgressInfo e;
    public String f;
    public double g;

    public adv(AdEventType adEventType, c cVar) {
        this.a = adEventType;
        this.b = cVar;
    }

    public final boolean equals(Object obj) {
        return ahh.a((Object) this, obj, new String[0]);
    }

    public final int hashCode() {
        return ahj.a(this, new String[0]);
    }
}
