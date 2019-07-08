package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

/* compiled from: IMASDK */
public final class sx implements so {
    private final Context a;
    private final tz b;
    private final so c;

    public sx(Context context, String str) {
        this(context, str, (tz) null);
    }

    private sx(Context context, String str, tz tzVar) {
        this(context, (tz) null, (so) new td(str, null));
    }

    private sx(Context context, tz tzVar, so soVar) {
        this.a = context.getApplicationContext();
        this.b = tzVar;
        this.c = soVar;
    }

    public final /* synthetic */ sn a() {
        sw swVar = new sw(this.a, this.c.a());
        tz tzVar = this.b;
        if (tzVar != null) {
            swVar.a(tzVar);
        }
        return swVar;
    }
}
