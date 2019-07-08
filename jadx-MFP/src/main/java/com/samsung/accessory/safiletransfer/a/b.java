package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    private int a;

    public b() {
    }

    public b(int i) {
        this.a = i;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("TransactionId", this.a);
        return jSONObject;
    }
}
