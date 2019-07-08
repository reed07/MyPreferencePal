package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.e.g;

public class l extends b {
    private static final String d = "l";
    private final Uri e;

    public l(Context context, c cVar, String str, Uri uri) {
        super(context, cVar, str);
        this.e = uri;
    }

    public void a() {
        try {
            Log.w("REDIRECTACTION: ", this.e.toString());
            g.a(new g(), this.a, this.e, this.c);
        } catch (Exception e2) {
            String str = d;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to open link url: ");
            sb.append(this.e.toString());
            Log.d(str, sb.toString(), e2);
        }
    }
}
