package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class h {
    private int a;
    private String b;
    private boolean c;

    public h() {
    }

    public h(int i, String str, boolean z) {
        this.a = i;
        this.b = str;
        this.c = z;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("path", this.b);
        jSONObject.put("accepted", this.c);
        return jSONObject;
    }
}
