package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: IMASDK */
public class al {
    private JSONObject a;
    private final ak b;

    public JSONObject a() {
        return this.a;
    }

    public void a(JSONObject jSONObject) {
        this.a = jSONObject;
    }

    public al(ak akVar) {
        this.b = akVar;
    }

    public void a(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        ak akVar = this.b;
        ao aoVar = new ao(this, hashSet, jSONObject, d);
        akVar.a(aoVar);
    }

    public void b(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        ak akVar = this.b;
        an anVar = new an(this, hashSet, jSONObject, d);
        akVar.a(anVar);
    }

    public void b() {
        this.b.a(new am(this));
    }
}
