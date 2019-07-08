package com.facebook.ads.internal.f;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class b {
    private final List<String> a = new ArrayList();
    private final List<String> b = new ArrayList();

    public enum a {
        REPORT("report"),
        HIDE(MessengerShareContentUtility.SHARE_BUTTON_HIDE),
        NONE("none");
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        /* access modifiers changed from: 0000 */
        public String a() {
            return this.d;
        }
    }

    public void a() {
        this.a.add(TtmlNode.START);
    }

    public void a(int i) {
        this.b.add(String.valueOf(i));
    }

    public void a(a aVar) {
        List<String> list = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(aVar.a());
        sb.append("_end");
        list.add(sb.toString());
    }

    public void a(a aVar, int i) {
        List<String> list = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(aVar.a());
        sb.append("_");
        sb.append(i);
        list.add(sb.toString());
    }

    public void b() {
        this.a.add("why_am_i_seeing_this");
    }

    public void c() {
        this.a.add("manage_ad_preferences");
    }

    public Map<String, String> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("user_journey", new JSONArray(this.a).toString());
        hashMap.put("options_selected", new JSONArray(this.b).toString());
        return hashMap;
    }

    public boolean e() {
        return !this.a.isEmpty() || !this.b.isEmpty();
    }

    public void f() {
        this.a.clear();
        this.b.clear();
    }
}
