package com.moat.analytics.mobile.inm;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.inm.NativeDisplayTracker.MoatUserInteractionType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class t extends b implements NativeDisplayTracker {
    private final Map<String, String> g;
    private final Set<MoatUserInteractionType> h = new HashSet();

    t(View view, Map<String, String> map) {
        super(view, true, false);
        p.a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.g = map;
        if (view == null) {
            String str = "Target view is null";
            StringBuilder sb = new StringBuilder("NativeDisplayTracker initialization not successful, ");
            sb.append(str);
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, sb.toString());
            this.a = new m(str);
        } else if (map == null || map.isEmpty()) {
            StringBuilder sb2 = new StringBuilder("NativeDisplayTracker initialization not successful, ");
            sb2.append("AdIds is null or empty");
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, sb2.toString());
            this.a = new m("AdIds is null or empty");
        } else {
            g gVar = ((k) k.getInstance()).d;
            if (gVar == null) {
                String str2 = "prepareNativeDisplayTracking was not called successfully";
                StringBuilder sb3 = new StringBuilder("NativeDisplayTracker initialization not successful, ");
                sb3.append(str2);
                p.a("[ERROR] ", 3, "NativeDisplayTracker", this, sb3.toString());
                this.a = new m(str2);
                return;
            }
            super.a(gVar.b);
            try {
                super.a(gVar.a);
                i();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(a());
                sb4.append(" created for ");
                sb4.append(g());
                sb4.append(", with adIds:");
                sb4.append(map.toString());
                p.a("[SUCCESS] ", sb4.toString());
            } catch (m e) {
                this.a = e;
            }
        }
    }

    private static String a(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder("moatClientLevel");
            sb.append(i);
            String sb2 = sb.toString();
            if (map.containsKey(sb2)) {
                linkedHashMap.put(sb2, map.get(sb2));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            StringBuilder sb3 = new StringBuilder("moatClientSlicer");
            sb3.append(i2);
            String sb4 = sb3.toString();
            if (map.containsKey(sb4)) {
                linkedHashMap.put(sb4, map.get(sb4));
            }
        }
        for (String str : map.keySet()) {
            if (!linkedHashMap.containsKey(str)) {
                linkedHashMap.put(str, (String) map.get(str));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    private void i() {
        if (this.c != null) {
            this.c.a(j());
        }
    }

    private String j() {
        String str = "";
        try {
            String a = a(this.g);
            StringBuilder sb = new StringBuilder("Parsed ad ids = ");
            sb.append(a);
            p.a(3, "NativeDisplayTracker", (Object) this, sb.toString());
            StringBuilder sb2 = new StringBuilder("{\"adIds\":");
            sb2.append(a);
            sb2.append(", \"adKey\":\"");
            sb2.append(this.e);
            sb2.append("\", \"adSize\":");
            sb2.append(k());
            sb2.append("}");
            return sb2.toString();
        } catch (Exception e) {
            m.a(e);
            return str;
        }
    }

    private String k() {
        try {
            Rect a = z.a(super.f());
            int width = a.width();
            int height = a.height();
            HashMap hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            m.a(e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        String str = "NativeDisplayTracker";
        try {
            StringBuilder sb = new StringBuilder("reportUserInteractionEvent:");
            sb.append(moatUserInteractionType.name());
            p.a(3, str, (Object) this, sb.toString());
            if (!this.h.contains(moatUserInteractionType)) {
                this.h.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.e);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.c != null) {
                    this.c.b(jSONObject.toString());
                }
            }
        } catch (JSONException e) {
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a(e);
        } catch (Exception e2) {
            m.a(e2);
        }
    }
}
