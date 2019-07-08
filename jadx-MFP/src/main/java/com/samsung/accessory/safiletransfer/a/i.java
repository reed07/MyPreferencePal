package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class i {
    private int a;
    private String b;
    private String c;
    private boolean d;

    public i() {
    }

    public i(int i, String str, String str2, boolean z) {
        this.a = i;
        this.b = str;
        this.d = z;
        this.c = str2;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("path", this.b);
        jSONObject.put("fileuri", this.c);
        jSONObject.put("accepted", this.d);
        return jSONObject;
    }
}
