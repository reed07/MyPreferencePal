package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final /* synthetic */ class zzaid {
    public static void zza(zzaic zzaic, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzaic.zzh(str, jSONObject.toString());
    }

    public static void zza(zzaic zzaic, String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        zzaic.zzcg(sb.toString());
    }

    public static void zzb(zzaic zzaic, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String str2 = "Dispatching AFMA event: ";
        String valueOf = String.valueOf(sb.toString());
        zzaxz.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        zzaic.zzcg(sb.toString());
    }

    public static void zza(zzaic zzaic, String str, Map map) {
        try {
            zzaic.zza(str, zzbv.zzlf().zzn(map));
        } catch (JSONException unused) {
            zzaxz.zzeo("Could not convert parameters to JSON.");
        }
    }
}
