package com.inmobi.a;

import com.google.logging.type.LogSeverity;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SignalsConfig */
public final class p extends com.inmobi.commons.core.configs.a {
    private static final String d = com.inmobi.commons.core.configs.a.class.getSimpleName();
    public b a = new b();
    a b = new a();
    JSONObject c;

    /* compiled from: SignalsConfig */
    public static class a {
        boolean a = false;
        String b = "https://dock.inmobi.com/carb/v1/i";
        String c = "https://dock.inmobi.com/carb/v1/o";
        int d = 86400;
        int e = 3;
        int f = 60;
        int g = 60;
        long h = 307200;
    }

    /* compiled from: SignalsConfig */
    public static class b {
        public boolean a = false;
        int b = LogSeverity.NOTICE_VALUE;
        int c = 3;
        int d = 50;
        String e = "https://sdkm.w.inmobi.com/user/e.asm";
        int f = 3;
        int g = 60;
        boolean h = false;
        boolean i = false;
        public int j = 0;
        boolean k = false;
        public boolean l = false;
        public int m = 0;
        public boolean n = false;
        public boolean o = false;
        public boolean p = false;

        public final boolean a() {
            return this.h && this.a;
        }

        /* access modifiers changed from: 0000 */
        public final boolean b() {
            return this.i && this.a;
        }
    }

    public final String a() {
        return "signals";
    }

    public p() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("enabled", true);
            jSONObject.put("samplingFactor", 0);
            this.c = jSONObject;
        } catch (JSONException unused) {
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("ice");
        this.a.b = jSONObject2.getInt("sampleInterval");
        this.a.d = jSONObject2.getInt("sampleHistorySize");
        this.a.c = jSONObject2.getInt("stopRequestTimeout");
        this.a.a = jSONObject2.getBoolean("enabled");
        this.a.e = jSONObject2.getString("endPoint");
        this.a.f = jSONObject2.getInt("maxRetries");
        this.a.g = jSONObject2.getInt("retryInterval");
        this.a.h = jSONObject2.getBoolean("locationEnabled");
        this.a.i = jSONObject2.getBoolean("sessionEnabled");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("w");
        this.a.j = jSONObject3.getInt("wf");
        this.a.l = jSONObject3.getBoolean("cwe");
        this.a.k = jSONObject3.getBoolean("vwe");
        JSONObject jSONObject4 = jSONObject2.getJSONObject("c");
        this.a.n = jSONObject4.getBoolean("oe");
        this.a.p = jSONObject4.getBoolean("cce");
        this.a.o = jSONObject4.getBoolean("vce");
        this.a.m = jSONObject4.getInt("cof");
        JSONObject jSONObject5 = jSONObject.getJSONObject("carb");
        this.b.a = jSONObject5.getBoolean("enabled");
        this.b.b = jSONObject5.getString("getEndPoint");
        this.b.c = jSONObject5.getString("postEndPoint");
        this.b.d = jSONObject5.getInt("retrieveFrequency");
        this.b.e = jSONObject5.getInt("maxRetries");
        this.b.f = jSONObject5.getInt("retryInterval");
        this.b.g = jSONObject5.getInt("timeoutInterval");
        this.b.h = jSONObject5.getLong("maxGetResponseSize");
        this.c = jSONObject.optJSONObject("telemetry");
    }

    public final JSONObject b() throws JSONException {
        JSONObject b2 = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sampleInterval", this.a.b);
        jSONObject.put("stopRequestTimeout", this.a.c);
        jSONObject.put("sampleHistorySize", this.a.d);
        jSONObject.put("enabled", this.a.a);
        jSONObject.put("endPoint", this.a.e);
        jSONObject.put("maxRetries", this.a.f);
        jSONObject.put("retryInterval", this.a.g);
        jSONObject.put("locationEnabled", this.a.h);
        jSONObject.put("sessionEnabled", this.a.i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("wf", this.a.j);
        jSONObject2.put("vwe", this.a.k);
        jSONObject2.put("cwe", this.a.l);
        jSONObject.put("w", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("cof", this.a.m);
        jSONObject3.put("vce", this.a.o);
        jSONObject3.put("cce", this.a.p);
        jSONObject3.put("oe", this.a.n);
        jSONObject.put("c", jSONObject3);
        b2.put("ice", jSONObject);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("enabled", this.b.a);
        jSONObject4.put("getEndPoint", this.b.b);
        jSONObject4.put("postEndPoint", this.b.c);
        jSONObject4.put("retrieveFrequency", this.b.d);
        jSONObject4.put("maxRetries", this.b.e);
        jSONObject4.put("retryInterval", this.b.f);
        jSONObject4.put("timeoutInterval", this.b.g);
        jSONObject4.put("maxGetResponseSize", this.b.h);
        b2.put("carb", jSONObject4);
        b2.put("telemetry", this.c);
        return b2;
    }

    public final boolean c() {
        if (this.a.b < 0 || this.a.d < 0 || this.a.c < 0 || this.a.e.trim().length() == 0 || this.a.f < 0 || this.a.g < 0 || this.a.j < 0 || this.a.m < 0 || this.b.b.trim().length() == 0 || this.b.c.trim().length() == 0 || ((!this.b.b.startsWith("http://") && !this.b.b.startsWith("https://")) || ((!this.b.c.startsWith("http://") && !this.b.c.startsWith("https://")) || this.b.d < 0 || this.b.e < 0 || this.b.f < 0 || this.b.g < 0 || this.b.h < 0))) {
            return false;
        }
        return true;
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new p();
    }
}
