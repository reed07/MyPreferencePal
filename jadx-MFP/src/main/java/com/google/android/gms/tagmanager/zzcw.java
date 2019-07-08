package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@VisibleForTesting
public class zzcw {
    private static String zzbcw;
    @VisibleForTesting
    static Map<String, String> zzbcx = new HashMap();

    public static void zzdu(String str) {
        synchronized (zzcw.class) {
            zzbcw = str;
        }
    }

    static void zzf(Context context, String str) {
        zzft.zza(context, "gtm_install_referrer", "referrer", str);
        zzh(context, str);
    }

    public static String zzg(Context context, String str) {
        if (zzbcw == null) {
            synchronized (zzcw.class) {
                if (zzbcw == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzbcw = sharedPreferences.getString("referrer", "");
                    } else {
                        zzbcw = "";
                    }
                }
            }
        }
        return zzw(zzbcw, str);
    }

    public static void zzh(Context context, String str) {
        String zzw = zzw(str, "conv");
        if (zzw != null && zzw.length() > 0) {
            zzbcx.put(zzw, str);
            zzft.zza(context, "gtm_click_referrers", zzw, str);
        }
    }

    public static String zzw(String str, String str2) {
        if (str2 != null) {
            String str3 = "http://hostname/?";
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).getQueryParameter(str2);
        } else if (str.length() > 0) {
            return str;
        } else {
            return null;
        }
    }
}
