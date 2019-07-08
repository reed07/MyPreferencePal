package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.content.Context;

/* compiled from: IMASDK */
public final class n {
    @SuppressLint({"StaticFieldLeak"})
    private static n a = new n();
    private Context b;

    public static n a() {
        return a;
    }

    private n() {
    }

    public final Context b() {
        return this.b;
    }

    public final void a(Context context) {
        this.b = context != null ? context.getApplicationContext() : null;
    }
}
