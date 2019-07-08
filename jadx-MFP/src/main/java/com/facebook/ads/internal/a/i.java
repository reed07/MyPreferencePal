package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import com.facebook.ads.internal.s.c;
import java.util.Map;

public class i extends h {
    private final Uri e;
    private Map<String, String> f;

    i(Context context, c cVar, String str, Uri uri, Map<String, String> map) {
        super(context, cVar, str, null);
        this.e = uri;
        this.f = map;
    }

    public void a(Map<String, String> map) {
        this.f.putAll(map);
    }

    public Uri c() {
        return Uri.parse(this.e.getQueryParameter("link"));
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        a(this.f, null);
    }
}
