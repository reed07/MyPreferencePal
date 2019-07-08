package com.facebook.ads.internal.w.d;

import android.content.Context;
import com.facebook.ads.internal.w.h.b;
import java.io.File;

public final class a {
    public static boolean a(Context context) {
        try {
            return new File(context.getFilesDir(), "com.facebook.ads.ipc").exists();
        } catch (Exception e) {
            com.facebook.ads.internal.w.h.a.a(context, "ipc", b.ac, e);
            return false;
        }
    }
}
