package com.moat.analytics.mobile.und;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);
    static final Integer a = Integer.valueOf(Integer.MIN_VALUE);
    private static final Double e = Double.valueOf(Double.NaN);
    Integer b;
    Double c;
    MoatAdEventType d;
    private final Long f;

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, a, e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d2) {
        this.f = Long.valueOf(System.currentTimeMillis());
        this.d = moatAdEventType;
        this.c = d2;
        this.b = num;
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Object> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("adVolume", this.c);
        hashMap.put("playhead", this.b);
        hashMap.put("aTimeStamp", this.f);
        hashMap.put("type", this.d.toString());
        return hashMap;
    }
}