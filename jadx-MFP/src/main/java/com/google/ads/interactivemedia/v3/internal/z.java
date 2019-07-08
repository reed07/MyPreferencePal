package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.WindowManager;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class z {
    private static WindowManager a;
    private static String[] b = {AvidJSONUtil.KEY_X, "y", "width", "height"};
    private static float c = Resources.getSystem().getDisplayMetrics().density;

    public static void a(Context context) {
        if (context != null) {
            c = context.getResources().getDisplayMetrics().density;
            a = (WindowManager) context.getSystemService("window");
        }
    }

    public static JSONObject a(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AvidJSONUtil.KEY_X, (double) (((float) i) / c));
            jSONObject.put("y", (double) (((float) i2) / c));
            jSONObject.put("width", (double) (((float) i3) / c));
            jSONObject.put("height", (double) (((float) i4) / c));
        } catch (JSONException e) {
            ho.a("Error with creating viewStateObject", (Exception) e);
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 47);
            sb.append("JSONException during JSONObject.put for name [");
            sb.append(str);
            sb.append("]");
            ho.a(sb.toString(), (Exception) e);
        }
    }

    public static void a(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("adSessionId", str);
        } catch (JSONException e) {
            ho.a("Error with setting ad session id", (Exception) e);
        }
    }

    public static void a(JSONObject jSONObject, List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        try {
            jSONObject.put(AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR, jSONArray);
        } catch (JSONException e) {
            ho.a("Error with setting friendly obstruction", (Exception) e);
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                jSONObject.put(AvidJSONUtil.KEY_CHILD_VIEWS, optJSONArray);
            }
            optJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            agv.a(e);
        }
    }

    public static void a(JSONObject jSONObject) {
        float f;
        int i = VERSION.SDK_INT;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        if (i < 17) {
            JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                float f3 = BitmapDescriptorFactory.HUE_RED;
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        double optDouble = optJSONObject.optDouble(AvidJSONUtil.KEY_X);
                        double optDouble2 = optJSONObject.optDouble("y");
                        double optDouble3 = optJSONObject.optDouble("width");
                        double optDouble4 = optJSONObject.optDouble("height");
                        f2 = Math.max(f2, (float) (optDouble + optDouble3));
                        f3 = Math.max(f3, (float) (optDouble2 + optDouble4));
                    }
                }
                f = f3;
                aa aaVar = new aa(f2, f);
                jSONObject.put("width", (double) aaVar.a);
                jSONObject.put("height", (double) aaVar.b);
            }
        } else if (a != null) {
            Point point = new Point(0, 0);
            a.getDefaultDisplay().getRealSize(point);
            f2 = ((float) point.x) / c;
            f = ((float) point.y) / c;
            aa aaVar2 = new aa(f2, f);
            jSONObject.put("width", (double) aaVar2.a);
            jSONObject.put("height", (double) aaVar2.b);
        }
        f = BitmapDescriptorFactory.HUE_RED;
        aa aaVar22 = new aa(f2, f);
        try {
            jSONObject.put("width", (double) aaVar22.a);
            jSONObject.put("height", (double) aaVar22.b);
        } catch (JSONException e) {
            agv.a(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ad A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(org.json.JSONObject r10, org.json.JSONObject r11) {
        /*
            r0 = 1
            if (r10 != 0) goto L_0x0006
            if (r11 != 0) goto L_0x0006
            return r0
        L_0x0006:
            r1 = 0
            if (r10 == 0) goto L_0x00af
            if (r11 != 0) goto L_0x000d
            goto L_0x00af
        L_0x000d:
            java.lang.String[] r2 = b
            int r3 = r2.length
            r4 = 0
        L_0x0011:
            if (r4 >= r3) goto L_0x0026
            r5 = r2[r4]
            double r6 = r10.optDouble(r5)
            double r8 = r11.optDouble(r5)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x0023
            r2 = 0
            goto L_0x0027
        L_0x0023:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0026:
            r2 = 1
        L_0x0027:
            if (r2 == 0) goto L_0x00ae
            java.lang.String r2 = "adSessionId"
            java.lang.String r3 = ""
            java.lang.String r2 = r10.optString(r2, r3)
            java.lang.String r3 = "adSessionId"
            java.lang.String r4 = ""
            java.lang.String r3 = r11.optString(r3, r4)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00ae
            java.lang.String r2 = "isFriendlyObstructionFor"
            org.json.JSONArray r2 = r10.optJSONArray(r2)
            java.lang.String r3 = "isFriendlyObstructionFor"
            org.json.JSONArray r3 = r11.optJSONArray(r3)
            if (r2 != 0) goto L_0x004f
            if (r3 == 0) goto L_0x0075
        L_0x004f:
            boolean r4 = a(r2, r3)
            if (r4 != 0) goto L_0x0057
            r2 = 0
            goto L_0x0076
        L_0x0057:
            r4 = 0
        L_0x0058:
            int r5 = r2.length()
            if (r4 >= r5) goto L_0x0075
            java.lang.String r5 = ""
            java.lang.String r5 = r2.optString(r4, r5)
            java.lang.String r6 = ""
            java.lang.String r6 = r3.optString(r4, r6)
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0072
            r2 = 0
            goto L_0x0076
        L_0x0072:
            int r4 = r4 + 1
            goto L_0x0058
        L_0x0075:
            r2 = 1
        L_0x0076:
            if (r2 == 0) goto L_0x00ae
            java.lang.String r2 = "childViews"
            org.json.JSONArray r10 = r10.optJSONArray(r2)
            java.lang.String r2 = "childViews"
            org.json.JSONArray r11 = r11.optJSONArray(r2)
            if (r10 != 0) goto L_0x0088
            if (r11 == 0) goto L_0x00aa
        L_0x0088:
            boolean r2 = a(r10, r11)
            if (r2 != 0) goto L_0x0090
            r10 = 0
            goto L_0x00ab
        L_0x0090:
            r2 = 0
        L_0x0091:
            int r3 = r10.length()
            if (r2 >= r3) goto L_0x00aa
            org.json.JSONObject r3 = r10.optJSONObject(r2)
            org.json.JSONObject r4 = r11.optJSONObject(r2)
            boolean r3 = b(r3, r4)
            if (r3 != 0) goto L_0x00a7
            r10 = 0
            goto L_0x00ab
        L_0x00a7:
            int r2 = r2 + 1
            goto L_0x0091
        L_0x00aa:
            r10 = 1
        L_0x00ab:
            if (r10 == 0) goto L_0x00ae
            return r0
        L_0x00ae:
            return r1
        L_0x00af:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.z.b(org.json.JSONObject, org.json.JSONObject):boolean");
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray == null || jSONArray2 == null || jSONArray.length() != jSONArray2.length()) ? false : true;
    }
}
