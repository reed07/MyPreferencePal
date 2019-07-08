package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    private int a = -1;
    private int b = -1;

    public final int a() {
        return this.a;
    }

    public final void a(Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject((String) obj);
        this.a = jSONObject.getInt("id");
        this.b = jSONObject.getInt("errorCode");
        jSONObject.getString("errorMsg");
    }

    public final int b() {
        return this.b;
    }
}
