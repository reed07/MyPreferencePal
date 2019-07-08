package com.inmobi.commons.core.configs;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConfigDao */
public final class c {
    com.inmobi.commons.core.d.c a = com.inmobi.commons.core.d.c.b("config_store");

    public final void a(a aVar) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(aVar.a());
        sb.append("_config");
        String c = cVar.c(sb.toString());
        if (c != null) {
            try {
                aVar.a(new JSONObject(c));
            } catch (JSONException unused) {
            }
        }
    }

    public final boolean a(String str) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_config");
        return cVar.c(sb.toString()) != null;
    }

    public final long b(String str) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_config_update_ts");
        return cVar.b(sb.toString(), 0);
    }

    public final void a(String str, long j) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_config_update_ts");
        cVar.a(sb.toString(), j);
    }
}
