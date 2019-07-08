package com.inmobi.rendering.mraid;

import com.myfitnesspal.shared.constants.Constants.Extras;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OrientationProperties */
public class g {
    private static String e = "g";
    public boolean a = true;
    public String b = "none";
    public String c = TtmlNode.RIGHT;
    public String d = null;

    public static g a(String str, g gVar) {
        g gVar2 = new g();
        gVar2.d = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            gVar2.b = jSONObject.optString("forceOrientation", gVar.b);
            gVar2.a = jSONObject.optBoolean("allowOrientationChange", gVar.a);
            gVar2.c = jSONObject.optString("direction", gVar.c);
            if (!gVar2.b.equals(Extras.ORIENTATION_PORTRAIT) && !gVar2.b.equals(Extras.ORIENTATION_LANDSCAPE)) {
                gVar2.b = "none";
            }
            if (gVar2.c.equals(TtmlNode.LEFT) || gVar2.c.equals(TtmlNode.RIGHT)) {
                return gVar2;
            }
            gVar2.c = TtmlNode.RIGHT;
            return gVar2;
        } catch (JSONException unused) {
            return null;
        }
    }
}
