package com.inmobi.rendering.mraid;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ResizeProperties */
public class h {
    private static final String g = "h";
    String a = "top-right";
    int b;
    int c;
    int d = 0;
    int e = 0;
    boolean f = true;

    public static h a(String str, h hVar) {
        h hVar2 = new h();
        try {
            JSONObject jSONObject = new JSONObject(str);
            hVar2.b = jSONObject.getInt("width");
            hVar2.c = jSONObject.getInt("height");
            hVar2.d = jSONObject.getInt("offsetX");
            hVar2.e = jSONObject.getInt("offsetY");
            if (hVar == null) {
                return hVar2;
            }
            hVar2.a = jSONObject.optString("customClosePosition", hVar.a);
            hVar2.f = jSONObject.optBoolean("allowOffscreen", hVar.f);
            return hVar2;
        } catch (JSONException unused) {
            return null;
        }
    }

    public final String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.b);
            jSONObject.put("height", this.c);
            jSONObject.put("customClosePosition", this.a);
            jSONObject.put("offsetX", this.d);
            jSONObject.put("offsetY", this.e);
            jSONObject.put("allowOffscreen", this.f);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
