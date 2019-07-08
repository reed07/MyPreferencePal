package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.myfitnesspal.shared.constants.UtmParams;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;

@VisibleForTesting
public final class zzdg {
    private static final char[] zzacp = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static Map<String, String> zzae(String str) {
        HashMap hashMap = new HashMap();
        for (String split : str.split("&")) {
            String[] split2 = split.split("=", 3);
            String str2 = null;
            if (split2.length > 1) {
                hashMap.put(split2[0], TextUtils.isEmpty(split2[1]) ? null : split2[1]);
                if (split2.length == 3 && !TextUtils.isEmpty(split2[1]) && !hashMap.containsKey(split2[1])) {
                    String str3 = split2[1];
                    if (!TextUtils.isEmpty(split2[2])) {
                        str2 = split2[2];
                    }
                    hashMap.put(str3, str2);
                }
            } else if (split2.length == 1 && split2[0].length() != 0) {
                hashMap.put(split2[0], null);
            }
        }
        return hashMap;
    }

    public static String zzc(boolean z) {
        return z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
    }

    public static double zza(String str, double d) {
        if (str == null) {
            return 100.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return 100.0d;
        }
    }

    public static long zzaf(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static boolean zzb(String str, boolean z) {
        if (str == null || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return true;
        }
        if (str.equalsIgnoreCase("false") || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("0")) {
            return false;
        }
        return true;
    }

    public static String zzag(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains("?")) {
            String[] split = str.split("[\\?]");
            if (split.length > 1) {
                str = split[1];
            }
        }
        if (str.contains("%3D")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return null;
            }
        } else if (!str.contains("=")) {
            return null;
        }
        Map zzae = zzae(str);
        String[] strArr = {"dclid", "utm_source", "gclid", Param.ACLID, "utm_campaign", "utm_medium", UtmParams.UTM_TERM, UtmParams.UTM_CONTENT, "utm_id", "anid", "gmob_t"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (!TextUtils.isEmpty((CharSequence) zzae.get(strArr[i]))) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(strArr[i]);
                sb.append("=");
                sb.append((String) zzae.get(strArr[i]));
            }
        }
        return sb.toString();
    }

    public static zzy zza(zzcp zzcp, String str) {
        Preconditions.checkNotNull(zzcp);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new HashMap();
        try {
            String str2 = "?";
            String valueOf = String.valueOf(str);
            Map parse = HttpUtils.parse(new URI(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)), "UTF-8");
            zzy zzy = new zzy();
            zzy.zzf((String) parse.get(UtmParams.UTM_CONTENT));
            zzy.zzd((String) parse.get("utm_medium"));
            zzy.setName((String) parse.get("utm_campaign"));
            zzy.zzc((String) parse.get("utm_source"));
            zzy.zze((String) parse.get(UtmParams.UTM_TERM));
            zzy.zzg((String) parse.get("utm_id"));
            zzy.zzh((String) parse.get("anid"));
            zzy.zzi((String) parse.get("gclid"));
            zzy.zzj((String) parse.get("dclid"));
            zzy.zzk((String) parse.get(Param.ACLID));
            return zzy;
        } catch (URISyntaxException e) {
            zzcp.zzd("No valid campaign data found", e);
            return null;
        }
    }

    public static String zza(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language.toLowerCase(locale));
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-");
            sb.append(locale.getCountry().toLowerCase(locale));
        }
        return sb.toString();
    }

    public static void zzb(Map<String, String> map, String str, String str2) {
        if (str2 != null && !map.containsKey(str)) {
            map.put(str, str2);
        }
    }

    public static void zzc(Map<String, String> map, String str, String str2) {
        if (str2 != null && TextUtils.isEmpty((CharSequence) map.get(str))) {
            map.put(str, str2);
        }
    }

    public static void zzb(Map<String, String> map, String str, boolean z) {
        if (!map.containsKey(str)) {
            map.put(str, z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        }
    }

    public static void zza(Map<String, String> map, String str, Map<String, String> map2) {
        zzb(map, str, (String) map2.get(str));
    }

    public static MessageDigest zzah(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static boolean zza(double d, String str) {
        int i;
        if (d <= 0.0d || d >= 100.0d) {
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                i = ((i << 6) & CharCompanionObject.MAX_VALUE) + charAt + (charAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        } else {
            i = 1;
        }
        return ((double) (i % 10000)) >= d * 100.0d;
    }

    public static boolean zzai(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith("http:")) {
            return false;
        }
        return true;
    }

    public static boolean zza(Context context, String str, boolean z) {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 0);
            if (receiverInfo != null && receiverInfo.enabled && (!z || receiverInfo.exported)) {
                return true;
            }
        } catch (NameNotFoundException unused) {
        }
        return false;
    }

    public static boolean zzc(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, str), 0);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        } catch (NameNotFoundException unused) {
        }
        return false;
    }
}
