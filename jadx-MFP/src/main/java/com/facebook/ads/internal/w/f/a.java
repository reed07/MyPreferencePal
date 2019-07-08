package com.facebook.ads.internal.w.f;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.lang.reflect.Field;

@AnyThread
public final class a {
    public static boolean a;
    @Nullable
    private static String b;

    @Nullable
    private static String a() {
        try {
            return (String) Application.class.getMethod("getProcessName", null).invoke(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    private static String a(Application application) {
        try {
            Field field = application.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(application);
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", null).invoke(obj2, null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public static String a(Context context) {
        String str = b;
        if (str != null) {
            return str;
        }
        if (VERSION.SDK_INT >= 28) {
            return a();
        }
        Context applicationContext = context.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            return null;
        }
        b = a((Application) applicationContext);
        return b;
    }

    public static String a(String str, Context context) {
        String packageName = context.getPackageName();
        String a2 = a(context);
        if (TextUtils.isEmpty(a2) || packageName.equals(a2)) {
            return str;
        }
        if (a2.contains(":")) {
            a2 = a2.split(":")[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_");
        sb.append(a2);
        return sb.toString();
    }
}
