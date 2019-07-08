package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public final class zzawz {
    public static String zzb(String str, Context context, boolean z) {
        if ((((Boolean) zzwu.zzpz().zzd(zzaan.zzcqz)).booleanValue() && !z) || !zzbv.zzmf().zzv(context) || TextUtils.isEmpty(str)) {
            return str;
        }
        String zzz = zzbv.zzmf().zzz(context);
        if (zzz == null) {
            return str;
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcqs)).booleanValue()) {
            String str2 = (String) zzwu.zzpz().zzd(zzaan.zzcqt);
            if (str.contains(str2)) {
                if (zzbv.zzlf().zzea(str)) {
                    zzbv.zzmf().zzf(context, zzz);
                    return str.replace(str2, zzz);
                } else if (zzbv.zzlf().zzeb(str)) {
                    zzbv.zzmf().zzg(context, zzz);
                    return str.replace(str2, zzz);
                }
            }
        } else if (!str.contains("fbs_aeid")) {
            if (zzbv.zzlf().zzea(str)) {
                zzbv.zzmf().zzf(context, zzz);
                return zzb(str, "fbs_aeid", zzz).toString();
            } else if (zzbv.zzlf().zzeb(str)) {
                zzbv.zzmf().zzg(context, zzz);
                return zzb(str, "fbs_aeid", zzz).toString();
            }
        }
        return str;
    }

    public static String zzb(String str, Context context) {
        if (!zzbv.zzmf().zzv(context) || TextUtils.isEmpty(str)) {
            return str;
        }
        String zzz = zzbv.zzmf().zzz(context);
        if (zzz == null || !zzbv.zzlf().zzeb(str)) {
            return str;
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcqs)).booleanValue()) {
            String str2 = (String) zzwu.zzpz().zzd(zzaan.zzcqt);
            if (str.contains(str2)) {
                return str.replace(str2, zzz);
            }
        } else if (!str.contains("fbs_aeid")) {
            return zzb(str, "fbs_aeid", zzz).toString();
        }
        return str;
    }

    public static String zzb(Uri uri, Context context) {
        if (!zzbv.zzmf().zzv(context)) {
            return uri.toString();
        }
        String zzz = zzbv.zzmf().zzz(context);
        if (zzz == null) {
            return uri.toString();
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcqs)).booleanValue()) {
            String str = (String) zzwu.zzpz().zzd(zzaan.zzcqt);
            String uri2 = uri.toString();
            if (uri2.contains(str)) {
                zzbv.zzmf().zzf(context, zzz);
                return uri2.replace(str, zzz);
            }
        } else if (TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
            uri = zzb(uri.toString(), "fbs_aeid", zzz);
            zzbv.zzmf().zzf(context, zzz);
        }
        return uri.toString();
    }

    @VisibleForTesting
    private static Uri zzb(String str, String str2, String str3) {
        int indexOf = str.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = str.indexOf("?adurl");
        }
        if (indexOf == -1) {
            return Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
        }
        int i = indexOf + 1;
        StringBuilder sb = new StringBuilder(str.substring(0, i));
        sb.append(str2);
        sb.append("=");
        sb.append(str3);
        sb.append("&");
        sb.append(str.substring(i));
        return Uri.parse(sb.toString());
    }
}
