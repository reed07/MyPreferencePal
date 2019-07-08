package io.uacf.core.util;

import android.content.Context;

public final class ContextUtil {
    public static Context getApplicationContextSafe(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }
}
