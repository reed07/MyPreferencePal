package com.inmobi.commons.core.a;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashConfig */
public class b extends com.inmobi.commons.core.configs.a {
    private static final String m = "b";
    public String a = "https://crash-metrics.sdk.inmobi.com/trace";
    long b = 0;
    int c = 3;
    int d = 50;
    long e = 2592000;
    long f = 86400;
    boolean g = false;
    boolean h = false;
    JSONObject i;
    JSONObject j;
    a k;
    a l;

    /* compiled from: CrashConfig */
    public final class a {
        long a;
        int b;

        public a() {
        }

        public final boolean a() {
            return this.b <= b.this.d && this.a > 0 && this.b > 0;
        }
    }

    public final String a() {
        return "crashReporting";
    }

    public b() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10);
            jSONObject2.put("maxBatchSize", 1);
            jSONObject.put("wifi", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("retryInterval", 10);
            jSONObject3.put("maxBatchSize", 1);
            jSONObject.put("others", jSONObject3);
            b(jSONObject);
            JSONObject jSONObject4 = new JSONObject();
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("enabled", false);
            jSONObject5.put("samplingFactor", 0);
            jSONObject4.put("catchEvent", jSONObject5);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("enabled", false);
            jSONObject6.put("samplingFactor", 0);
            jSONObject4.put("crashEvent", jSONObject6);
            c(jSONObject4);
        } catch (JSONException unused) {
        }
    }

    public final boolean c() {
        if (this.a.trim().length() == 0 || (!this.a.startsWith("http://") && !this.a.startsWith("https://"))) {
            return false;
        }
        long j2 = this.f;
        long j3 = this.b;
        if (j2 >= j3) {
            long j4 = this.e;
            if (j2 > j4 || j4 < j3 || !this.k.a() || !this.l.a() || this.b < 0 || this.c < 0 || this.f <= 0 || this.e <= 0 || this.d <= 0) {
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
                this.k = aVar;
            } else {
                this.l = aVar;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -488533857) {
                if (hashCode == 1411010355 && str.equals("crashEvent")) {
                    c2 = 1;
                }
            } else if (str.equals("catchEvent")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    this.j = jSONObject.getJSONObject(str);
                    break;
                case 1:
                    this.i = jSONObject.getJSONObject(str);
                    break;
            }
        }
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getString("url");
        this.b = jSONObject.getLong("processingInterval");
        this.c = jSONObject.getInt("maxRetryCount");
        this.d = jSONObject.getInt("maxEventsToPersist");
        this.e = jSONObject.getLong("eventTTL");
        this.f = jSONObject.getLong("txLatency");
        this.g = jSONObject.getBoolean("crashEnabled");
        this.h = jSONObject.getBoolean("catchEnabled");
        b(jSONObject.getJSONObject("networkType"));
        c(jSONObject.getJSONObject("telemetry"));
    }

    public final JSONObject b() throws JSONException {
        JSONObject b2 = super.b();
        new JSONObject();
        b2.put("url", this.a);
        b2.put("processingInterval", this.b);
        b2.put("maxRetryCount", this.c);
        b2.put("maxEventsToPersist", this.d);
        b2.put("eventTTL", this.e);
        b2.put("txLatency", this.f);
        b2.put("crashEnabled", this.g);
        b2.put("catchEnabled", this.h);
        JSONObject jSONObject = new JSONObject();
        a aVar = this.l;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", aVar.a);
        jSONObject2.put("maxBatchSize", aVar.b);
        jSONObject.put("wifi", jSONObject2);
        a aVar2 = this.k;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", aVar2.a);
        jSONObject3.put("maxBatchSize", aVar2.b);
        jSONObject.put("others", jSONObject3);
        b2.put("networkType", jSONObject);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("crashEvent", this.i);
        jSONObject4.put("catchEvent", this.j);
        b2.put("telemetry", jSONObject4);
        return b2;
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new b();
    }
}
