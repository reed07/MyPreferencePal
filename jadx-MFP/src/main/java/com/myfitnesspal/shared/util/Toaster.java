package com.myfitnesspal.shared.util;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;
import java.text.MessageFormat;

public class Toaster {
    private static void show(Activity activity, final int i, final int i2) {
        if (activity != null) {
            final Application application = activity.getApplication();
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(application, i, i2).show();
                }
            });
        }
    }

    private static void show(Activity activity, final String str, final int i) {
        if (activity != null && !TextUtils.isEmpty(str)) {
            final Application application = activity.getApplication();
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(application, str, i).show();
                }
            });
        }
    }

    public static void showLong(Activity activity, int i) {
        show(activity, i, 1);
    }

    public static void showShort(Activity activity, int i) {
        show(activity, i, 0);
    }

    public static void showLong(Activity activity, String str) {
        show(activity, str, 1);
    }

    public static void showShort(Activity activity, String str) {
        show(activity, str, 0);
    }

    public static void showLong(Activity activity, String str, Object... objArr) {
        show(activity, MessageFormat.format(str, objArr), 1);
    }

    public static void showShort(Activity activity, String str, Object... objArr) {
        show(activity, MessageFormat.format(str, objArr), 0);
    }

    public static void showLong(Activity activity, int i, Object... objArr) {
        if (activity != null) {
            showLong(activity, activity.getString(i), objArr);
        }
    }

    public static void showShort(Activity activity, int i, Object... objArr) {
        if (activity != null) {
            showShort(activity, activity.getString(i), objArr);
        }
    }
}
