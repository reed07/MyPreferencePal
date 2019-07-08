package com.facebook.ads.internal.v.a;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class g implements r {
    private void a(Map<String, List<String>> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                for (String str2 : (List) map.get(str)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(":");
                    sb.append(str2);
                    a(sb.toString());
                }
            }
        }
    }

    public void a(n nVar) {
        if (nVar != null) {
            a("=== HTTP Response ===");
            StringBuilder sb = new StringBuilder();
            sb.append("Receive url: ");
            sb.append(nVar.b());
            a(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Status: ");
            sb2.append(nVar.a());
            a(sb2.toString());
            a(nVar.c());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Content:\n");
            sb3.append(nVar.e());
            a(sb3.toString());
        }
    }

    public void a(String str) {
        System.out.println(str);
    }

    public void a(HttpURLConnection httpURLConnection, Object obj) {
        a("=== HTTP Request ===");
        StringBuilder sb = new StringBuilder();
        sb.append(httpURLConnection.getRequestMethod());
        sb.append(" ");
        sb.append(httpURLConnection.getURL().toString());
        a(sb.toString());
        if (obj instanceof String) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Content: ");
            sb2.append((String) obj);
            a(sb2.toString());
        }
        a(httpURLConnection.getRequestProperties());
    }

    public boolean a() {
        return AdInternalSettings.isDebugBuild();
    }
}
