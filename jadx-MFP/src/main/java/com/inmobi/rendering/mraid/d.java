package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.d.c;

/* compiled from: MraidJsDao */
public final class d {
    public c a = c.b("mraid_js_store");

    public final void a(String str) {
        this.a.a("mraid_js_string", str);
        this.a.a("last_updated_ts", System.currentTimeMillis() / 1000);
    }
}
