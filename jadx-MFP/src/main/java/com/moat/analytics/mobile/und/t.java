package com.moat.analytics.mobile.und;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.und.NativeDisplayTracker.MoatUserInteractionType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class t extends b implements NativeDisplayTracker {
    private final Map<String, String> f;
    private final Set<MoatUserInteractionType> g = new HashSet();

    t(View view, Map<String, String> map) {
        super(view, true, false);
        p.a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.f = map;
        g gVar = ((k) k.getInstance()).d;
        if (gVar != null) {
            super.a(gVar.b);
            super.a(gVar.a);
        }
        g();
        StringBuilder sb = new StringBuilder();
        sb.append(a());
        sb.append(" created for ");
        sb.append(e());
        sb.append(", with adIds:");
        sb.append(map.toString());
        p.a("[SUCCESS] ", sb.toString());
    }

    private static String a(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("moatClientLevel");
            sb.append(i);
            String sb2 = sb.toString();
            if (map.containsKey(sb2)) {
                linkedHashMap.put(sb2, map.get(sb2));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("moatClientSlicer");
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

    private void g() {
        if (this.a != null) {
            this.a.a(h());
        }
    }

    private String h() {
        String str = "";
        try {
            String a = a(this.f);
            StringBuilder sb = new StringBuilder();
            sb.append("Parsed ad ids = ");
            sb.append(a);
            p.a(3, "NativeDisplayTracker", (Object) this, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("{\"adIds\":");
            sb2.append(a);
            sb2.append(", \"adKey\":\"");
            sb2.append(this.b);
            sb2.append("\", \"adSize\":");
            sb2.append(i());
            sb2.append("}");
            return sb2.toString();
        } catch (Exception e) {
            m.a(e);
            return str;
        }
    }

    private String i() {
        try {
            Rect a = y.a(super.d());
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
            StringBuilder sb = new StringBuilder();
            sb.append("reportUserInteractionEvent:");
            sb.append(moatUserInteractionType.name());
            p.a(3, str, (Object) this, sb.toString());
            if (!this.g.contains(moatUserInteractionType)) {
                this.g.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.b);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.a != null) {
                    this.a.b(jSONObject.toString());
                }
            }
        } catch (JSONException e) {
            e = e;
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a(e);
        } catch (Exception e2) {
            e = e2;
            m.a(e);
        }
    }
}
