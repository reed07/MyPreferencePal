package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private String a;

    public a() {
    }

    public a(String str) {
        this.a = str;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("AgentId", this.a);
        return jSONObject;
    }
}
