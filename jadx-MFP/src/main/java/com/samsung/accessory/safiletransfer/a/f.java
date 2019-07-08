package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    private int a = 0;
    private String b = "";
    private String c = "";

    public final int a() {
        return this.a;
    }

    public final void a(Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject((String) obj);
        this.a = jSONObject.getInt("id");
        this.b = jSONObject.getString("sourcePath");
        this.c = jSONObject.getString("destPath");
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }
}
