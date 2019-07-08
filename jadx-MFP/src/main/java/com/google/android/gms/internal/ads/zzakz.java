package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbv;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzakz {
    public static List<String> zza(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void zza(Context context, String str, zzaxf zzaxf, String str2, boolean z, List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str3 = z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0";
            for (String zza : list) {
                String zza2 = zza(zza(zza(zza(zza(zza(zza(zza, "@gw_adlocid@", str2), "@gw_adnetrefresh@", str3), "@gw_qdata@", zzaxf.zzehj.zzdlw), "@gw_sdkver@", str), "@gw_sessid@", zzwu.zzqa()), "@gw_seqnum@", zzaxf.zzdwj), "@gw_adnetstatus@", zzaxf.zzehk);
                if (zzaxf.zzdnb != null) {
                    zza2 = zza(zza(zza2, "@gw_adnetid@", zzaxf.zzdnb.zzdkv), "@gw_allocid@", zzaxf.zzdnb.zzdkx);
                }
                String zzb = zzawz.zzb(zza2, context, zzaxf.zzdzf);
                zzbv.zzlf();
                zzayh.zzc(context, str, zzb);
            }
        }
    }

    public static void zza(Context context, String str, List<String> list, @Nullable String str2, @Nullable String str3, @Nullable zzawd zzawd) {
        if (list != null && !list.isEmpty()) {
            String zzco = zzco(str2);
            String zzco2 = zzco(str3);
            long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
            for (String zza : list) {
                String zza2 = zza(zza(zza(zza, "@gw_rwd_userid@", Uri.encode(zzco)), "@gw_rwd_custom_data@", Uri.encode(zzco2)), "@gw_tmstmp@", Long.toString(currentTimeMillis));
                if (zzawd != null) {
                    zza2 = zza(zza(zza2, "@gw_rwd_itm@", Uri.encode(zzawd.type)), "@gw_rwd_amt@", Integer.toString(zzawd.zzefo));
                }
                zzbv.zzlf();
                zzayh.zzc(context, str, zza2);
            }
        }
    }

    private static String zza(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    @Nullable
    private static String zzco(@Nullable String str) {
        return (TextUtils.isEmpty(str) || !zzbax.isEnabled()) ? str : "fakeForAdDebugLog";
    }

    public static boolean zza(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || iArr.length != 2) {
            return false;
        }
        String[] split = str.split(AvidJSONUtil.KEY_X);
        if (split.length != 2) {
            return false;
        }
        try {
            iArr[0] = Integer.parseInt(split[0]);
            iArr[1] = Integer.parseInt(split[1]);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
