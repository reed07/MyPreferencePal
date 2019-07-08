package com.inmobi.commons.core.utilities.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer2.util.MimeTypes;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.e;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: DeviceInfo */
public class b {
    @SuppressLint({"MissingPermission", "NewApi"})
    private static String b() {
        Context b = a.b();
        if (b == null) {
            return "";
        }
        String str = "";
        if (e.a(b, "root", "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) b.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (VERSION.SDK_INT < 28) {
                        int type = activeNetworkInfo.getType();
                        int subtype = activeNetworkInfo.getSubtype();
                        if (type == 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(type);
                            sb.append("|");
                            sb.append(subtype);
                            str = sb.toString();
                        } else {
                            str = type == 1 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : Integer.toString(type);
                        }
                    } else {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                        if (networkCapabilities != null) {
                            if (networkCapabilities.hasTransport(0)) {
                                StringBuilder sb2 = new StringBuilder("0|");
                                sb2.append(activeNetworkInfo.getSubtype());
                                str = sb2.toString();
                            } else {
                                str = networkCapabilities.hasTransport(1) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : networkCapabilities.hasTransport(2) ? "7" : networkCapabilities.hasTransport(3) ? "9" : networkCapabilities.hasTransport(4) ? "17" : networkCapabilities.hasTransport(5) ? "10" : networkCapabilities.hasTransport(6) ? "11" : "8";
                            }
                        }
                    }
                }
            }
        }
        return str;
    }

    public static Map<String, String> a(boolean z) {
        int i;
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("d-brand-name", Build.BRAND);
            hashMap.put("d-manufacturer-name", Build.MANUFACTURER);
            hashMap.put("d-model-name", Build.MODEL);
            hashMap.put("d-nettype-raw", b());
            hashMap.put("d-localization", Locale.getDefault().toString());
            String str = "d-media-volume";
            Context b = a.b();
            if (b != null) {
                if (!z) {
                    AudioManager audioManager = (AudioManager) b.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
                    i = (audioManager.getStreamVolume(3) * 100) / audioManager.getStreamMaxVolume(3);
                    hashMap.put(str, String.valueOf(i));
                    return hashMap;
                }
            }
            i = 0;
            hashMap.put(str, String.valueOf(i));
        } catch (Exception e) {
            b.class.getSimpleName();
            new StringBuilder("SDK encountered unexpected error in getting device info; ").append(e.getMessage());
        }
        return hashMap;
    }

    public static int a() {
        String b = b();
        if (b.startsWith("0")) {
            return 0;
        }
        return b.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES) ? 1 : 2;
    }

    public static int a(@NonNull Context context) {
        return ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getStreamVolume(3);
    }
}
