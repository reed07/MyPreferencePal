package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.b.c;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExpandProperties */
public class b {
    private static final String d = "b";
    public boolean a = false;
    public boolean b;
    public String c;
    private int e = c.a().a;
    private int f = c.a().b;
    private boolean g = true;

    public b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.e);
            jSONObject.put("height", this.f);
            jSONObject.put("useCustomClose", this.a);
            jSONObject.put("isModal", this.g);
        } catch (JSONException e2) {
            new StringBuilder("Exception in composing ExpandProperties: ").append(e2.getMessage());
        }
        this.c = jSONObject.toString();
    }

    public static b a(String str) {
        b bVar = new b();
        bVar.c = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar.g = true;
            if (jSONObject.has("useCustomClose")) {
                bVar.b = true;
            }
            bVar.a = jSONObject.optBoolean("useCustomClose", false);
        } catch (JSONException unused) {
        }
        return bVar;
    }
}
