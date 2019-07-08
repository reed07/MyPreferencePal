package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: IMASDK */
public final class th {
    private final Map<String, String> a = new HashMap();
    private Map<String, String> b;

    public final synchronized Map<String, String> a() {
        if (this.b == null) {
            this.b = Collections.unmodifiableMap(new HashMap(this.a));
        }
        return this.b;
    }
}
