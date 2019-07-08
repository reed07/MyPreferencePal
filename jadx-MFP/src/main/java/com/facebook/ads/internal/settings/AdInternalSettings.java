package com.facebook.ads.internal.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.w.f.a;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class AdInternalSettings {
    public static Bundle a = new Bundle();
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    public static int e = 0;
    public static int f = 0;
    private static final String g = "AdInternalSettings";
    private static final Collection<String> h = new HashSet();
    private static volatile boolean i = false;

    static {
        h.add(ServerProtocol.DIALOG_PARAM_SDK_VERSION);
        h.add("google_sdk");
        h.add("vbox86p");
        h.add("vbox86tp");
    }

    private static ArrayList<String> a() {
        ArrayList<String> stringArrayList = a.getStringArrayList("LIST_TEST_DEVICES_KEY");
        if (stringArrayList != null) {
            return stringArrayList;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        a.putStringArrayList("LIST_TEST_DEVICES_KEY", arrayList);
        return arrayList;
    }

    public static void addTestDevice(String str) {
        a().add(str);
    }

    public static void addTestDevices(Collection<String> collection) {
        a().addAll(collection);
    }

    public static void clearTestDevices() {
        a().clear();
    }

    public static String getMediationService() {
        return a.getString("STR_MEDIATION_SERVICE_KEY", null);
    }

    public static String getUrlPrefix() {
        return a.getString("STR_URL_PREFIX_KEY", null);
    }

    public static boolean isDebugBuild() {
        return a.getBoolean("BOOL_DEBUG_BUILD_KEY", false);
    }

    public static boolean isExplicitTestMode() {
        return a.getBoolean("BOOL_EXPLICIT_TEST_MODE_KEY", false);
    }

    public static boolean isTestMode(Context context) {
        if (a.getBoolean("BOOL_DEBUG_BUILD_KEY", false) || isExplicitTestMode() || h.contains(Build.PRODUCT)) {
            return true;
        }
        String string = a.getString("STR_DEVICE_ID_HASH_KEY", null);
        if (string == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(a.a("FBAdPrefs", context), 0);
            string = sharedPreferences.getString("deviceIdHash", null);
            if (TextUtils.isEmpty(string)) {
                string = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", string).apply();
            }
            a.putString("STR_DEVICE_ID_HASH_KEY", string);
        }
        if (a().contains(string)) {
            return true;
        }
        if (!i) {
            i = true;
            String str = g;
            StringBuilder sb = new StringBuilder();
            sb.append("Test mode device hash: ");
            sb.append(string);
            Log.d(str, sb.toString());
            String str2 = g;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"");
            sb2.append(string);
            sb2.append("\");");
            Log.d(str2, sb2.toString());
        }
        return false;
    }

    public static boolean isVideoAutoplay() {
        return a.getBoolean("BOOL_VIDEO_AUTOPLAY_KEY");
    }

    public static boolean isVideoAutoplayOnMobile() {
        return a.getBoolean("BOOL_AUTOPLAY_ON_MOBILE_KEY", false);
    }

    public static boolean isVisibleAnimation() {
        return a.getBoolean("BOOL_VISIBLE_ANIMATION_KEY", false);
    }

    public static void setDebugBuild(boolean z) {
        a.putBoolean("BOOL_DEBUG_BUILD_KEY", z);
    }

    public static void setMediationService(String str) {
        a.putString("STR_MEDIATION_SERVICE_KEY", str);
    }

    public static void setTestMode(boolean z) {
        a.putBoolean("BOOL_EXPLICIT_TEST_MODE_KEY", z);
    }

    public static void setUrlPrefix(String str) {
        a.putString("STR_URL_PREFIX_KEY", str);
    }

    public static void setVideoAutoplay(boolean z) {
        a.putBoolean("BOOL_VIDEO_AUTOPLAY_KEY", z);
    }

    public static void setVideoAutoplayOnMobile(boolean z) {
        a.putBoolean("BOOL_AUTOPLAY_ON_MOBILE_KEY", z);
    }

    public static void setVisibleAnimation(boolean z) {
        a.putBoolean("BOOL_VISIBLE_ANIMATION_KEY", z);
    }
}
