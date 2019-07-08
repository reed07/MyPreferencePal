package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class g {
    private int a = -1;
    private long b = 0;

    public final int a() {
        return this.a;
    }

    public final void a(Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject((String) obj);
        this.a = jSONObject.getInt("id");
        this.b = jSONObject.getLong("progress");
    }

    public final long b() {
        return this.b;
    }
}
