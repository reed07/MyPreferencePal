package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;
import org.json.JSONObject;

public abstract class a implements Serializable {
    private static final long serialVersionUID = -5352540727250859603L;
    private int a = 200;
    private String b;
    private String c;

    public static a a(boolean z, JSONObject jSONObject) {
        return z ? f.a(jSONObject) : q.a(jSONObject);
    }

    public abstract String a();

    public void a(int i) {
        this.a = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public int b() {
        return this.a;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }
}
