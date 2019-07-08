package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.e.g;
import java.util.Map;

public class j extends h {
    private static final String e = "j";
    private final Uri f;
    private final Map<String, String> g;

    j(Context context, c cVar, String str, Uri uri, Map<String, String> map, m mVar) {
        super(context, cVar, str, mVar);
        this.f = uri;
        this.g = map;
    }

    @Nullable
    public a b() {
        try {
            g.a(new g(), this.a, Uri.parse(this.f.getQueryParameter("link")), this.c);
            return null;
        } catch (Exception e2) {
            String str = e;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to open link url: ");
            sb.append(this.f.toString());
            Log.d(str, sb.toString(), e2);
            return a.CANNOT_OPEN;
        }
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        a(this.g, b());
    }
}
