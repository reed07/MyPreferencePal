package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: IMASDK */
public abstract class ai extends aj {
    protected final HashSet<String> a;
    protected final JSONObject b;
    protected final double c;

    public ai(al alVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(alVar);
        this.a = new HashSet<>(hashSet);
        this.b = jSONObject;
        this.c = d;
    }
}
