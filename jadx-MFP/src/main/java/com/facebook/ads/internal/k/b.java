package com.facebook.ads.internal.k;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

public class b {
    private final String a;
    private final Map<String, String> b;
    private final String c;

    public b(String str, Map<String, String> map) {
        this(str, map, false);
    }

    public b(String str, Map<String, String> map, boolean z) {
        this.a = str;
        this.b = map;
        this.c = z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
    }

    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("stacktrace", this.a);
        hashMap.put("caught_exception", this.c);
        hashMap.putAll(this.b);
        return hashMap;
    }
}
