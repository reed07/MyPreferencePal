package com.facebook.ads.internal.i;

import android.content.Context;
import android.support.annotation.Nullable;

public final class a {
    @Nullable
    private static Context a;

    @Nullable
    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        if (context != null) {
            a = context.getApplicationContext();
        }
    }
}
