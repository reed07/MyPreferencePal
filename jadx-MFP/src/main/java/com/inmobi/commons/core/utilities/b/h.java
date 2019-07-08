package com.inmobi.commons.core.utilities.b;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SessionInfo */
public class h {
    private static final String e = "h";
    private static h f;
    private static final Object g = new Object();
    public String a;
    public long b;
    public long c;
    boolean d;

    public static h a() {
        h hVar = f;
        if (hVar == null) {
            synchronized (g) {
                hVar = f;
                if (hVar == null) {
                    hVar = new h();
                    f = hVar;
                }
            }
        }
        return hVar;
    }

    private h() {
    }

    public final JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", this.a);
            jSONObject.put("s-ts", this.b);
            jSONObject.put("e-ts", this.c);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final void a(boolean z) {
        this.d = z;
        if (!this.d) {
            this.a = null;
            this.b = 0;
            this.c = 0;
        }
    }
}
