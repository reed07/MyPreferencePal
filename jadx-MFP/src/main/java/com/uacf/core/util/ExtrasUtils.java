package com.uacf.core.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.Serializable;
import java.util.ArrayList;

public final class ExtrasUtils {
    public static String getString(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return null;
        }
        return extras.getString(str);
    }

    public static String getString(Intent intent, String str, String str2) {
        return Strings.toString(getString(intent, str), str2);
    }

    public static String getStringExtra(Intent intent, String str) {
        if (intent == null || !Strings.notEmpty(str)) {
            return null;
        }
        return intent.getStringExtra(str);
    }

    public static String getStringExtra(Intent intent, String str, String str2) {
        return hasExtra(intent, str) ? intent.getStringExtra(str) : str2;
    }

    public static String[] getStringArray(Intent intent, String str) {
        if (intent == null || !Strings.notEmpty(str)) {
            return null;
        }
        return intent.getStringArrayExtra(str);
    }

    public static String getString(Bundle bundle, String str, String str2) {
        return (bundle == null || !Strings.notEmpty(str)) ? str2 : bundle.getString(str);
    }

    public static int getInt(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return 0;
        }
        return extras.getInt(str);
    }

    public static int getInt(Intent intent, String str, int i) {
        return containsKey(intent, str) ? getInt(intent, str) : i;
    }

    public static long getLong(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return 0;
        }
        return extras.getLong(str);
    }

    public static long getLong(Intent intent, String str, long j) {
        return containsKey(intent, str) ? getLong(intent, str) : j;
    }

    public static float getFloat(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        return (extras == null || !Strings.notEmpty(str)) ? BitmapDescriptorFactory.HUE_RED : extras.getFloat(str);
    }

    public static float getFloat(Intent intent, String str, float f) {
        return containsKey(intent, str) ? getFloat(intent, str) : f;
    }

    public static int getIntExtra(Intent intent, String str, int i) {
        return (intent == null || !Strings.notEmpty(str)) ? i : intent.getIntExtra(str, i);
    }

    public static boolean getBoolean(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return false;
        }
        return extras.getBoolean(str);
    }

    public static boolean getBoolean(Intent intent, String str, boolean z) {
        return containsKey(intent, str) ? getBoolean(intent, str) : z;
    }

    public static Object get(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return null;
        }
        return extras.get(str);
    }

    public static <T extends Parcelable> T getParcelable(Intent intent, String str, ClassLoader classLoader) {
        return BundleUtils.getParcelable(getExtras(intent), str, classLoader);
    }

    public static <T extends Parcelable> ArrayList<T> getParcelableArrayList(Intent intent, String str, ClassLoader classLoader) {
        return BundleUtils.getParcelableArrayList(getExtras(intent), str, classLoader);
    }

    public static Serializable getSerializable(Intent intent, String str, ClassLoader classLoader) {
        return BundleUtils.getSerializable(getExtras(intent), str, classLoader);
    }

    public static int[] getIntArrayExtra(Intent intent, String str) {
        if (intent == null || !Strings.notEmpty(str)) {
            return null;
        }
        return intent.getIntArrayExtra(str);
    }

    public static int[] getIntArrayExtra(Intent intent, String str, int[] iArr) {
        return hasExtra(intent, str) ? intent.getIntArrayExtra(str) : iArr;
    }

    public static Bundle getExtras(Intent intent) {
        if (intent != null) {
            return intent.getExtras();
        }
        return null;
    }

    public static boolean containsKey(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        return extras != null && extras.containsKey(str);
    }

    public static boolean hasExtra(Intent intent, String str) {
        return intent != null && intent.hasExtra(str);
    }

    public static void removeExtra(Intent intent, String str) {
        if (intent != null) {
            intent.removeExtra(str);
        }
    }

    public static Bundle getBundle(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return null;
        }
        return extras.getBundle(str);
    }

    public static ArrayList<String> getStringArrayList(Intent intent, String str) {
        Bundle extras = getExtras(intent);
        if (extras == null || !Strings.notEmpty(str)) {
            return null;
        }
        return extras.getStringArrayList(str);
    }
}
