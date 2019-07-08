package com.inmobi.commons.core.e;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TelemetryConfig */
class d extends com.inmobi.commons.core.configs.a {
    private static final String j = "d";
    c a = new c();
    String b = "https://telemetry.sdk.inmobi.com/metrics";
    long c = 150;
    int d = 3;
    int e = 1000;
    long f = 259200;
    long g = 86400;
    a h;
    a i;

    /* compiled from: TelemetryConfig */
    public final class a {
        long a;
        int b;

        public a() {
        }

        public final boolean a() {
            return this.b <= d.this.e && this.a > 0 && this.b > 0;
        }
    }

    public final String a() {
        return "telemetry";
    }

    public d() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10);
            jSONObject2.put("maxBatchSize", 25);
            jSONObject.put("wifi", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("retryInterval", 10);
            jSONObject3.put("maxBatchSize", 25);
            jSONObject.put("others", jSONObject3);
            b(jSONObject);
        } catch (JSONException unused) {
        }
    }

    public final boolean c() {
        if (this.a == null || this.b.trim().length() == 0 || (!this.b.startsWith("http://") && !this.b.startsWith("https://"))) {
            return false;
        }
        long j2 = this.g;
        long j3 = this.c;
        if (j2 >= j3) {
            long j4 = this.f;
            if (j2 > j4 || j4 < j3 || !this.h.a() || !this.i.a() || this.c < 0 || this.d < 0 || this.g <= 0 || this.f <= 0 || this.e <= 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void b(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            a aVar = new a();
            aVar.a = jSONObject2.getLong("retryInterval");
            aVar.b = jSONObject2.getInt("maxBatchSize");
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
                this.h = aVar;
            } else {
                this.i = aVar;
            }
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.a.b = jSONObject2.getBoolean("enabled");
        this.a.a = jSONObject2.getInt("samplingFactor");
        this.b = jSONObject.getString("telemetryUrl");
        this.c = jSONObject.getLong("processingInterval");
        this.d = jSONObject.getInt("maxRetryCount");
        this.e = jSONObject.getInt("maxEventsToPersist");
        this.f = jSONObject.getLong("eventTTL");
        this.g = jSONObject.getLong("txLatency");
        b(jSONObject.getJSONObject("networkType"));
    }

    public final JSONObject b() throws JSONException {
        JSONObject b2 = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", this.a.b);
        jSONObject.put("samplingFactor", this.a.a);
        b2.put("base", jSONObject);
        b2.put("telemetryUrl", this.b);
        b2.put("processingInterval", this.c);
        b2.put("maxRetryCount", this.d);
        b2.put("maxEventsToPersist", this.e);
        b2.put("eventTTL", this.f);
        b2.put("txLatency", this.g);
        JSONObject jSONObject2 = new JSONObject();
        a aVar = this.i;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", aVar.a);
        jSONObject3.put("maxBatchSize", aVar.b);
        jSONObject2.put("wifi", jSONObject3);
        a aVar2 = this.h;
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("retryInterval", aVar2.a);
        jSONObject4.put("maxBatchSize", aVar2.b);
        jSONObject2.put("others", jSONObject4);
        b2.put("networkType", jSONObject2);
        return b2;
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new d();
    }
}
