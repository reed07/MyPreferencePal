package com.samsung.accessory.safiletransfer.a;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e {
    private int[] a = null;
    private int b = -1;

    public final void a(Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject((String) obj);
        JSONArray jSONArray = jSONObject.getJSONArray("id");
        this.b = jSONObject.getInt("errorCode");
        jSONObject.getString("errorMsg");
        this.a = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            this.a[i] = jSONArray.getInt(i);
        }
    }

    public final int[] a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }
}
