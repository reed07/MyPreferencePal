package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzbdy {
    public final boolean zzetk;
    public final int zzetl;
    public final int zzetm;
    public final int zzetn;
    public final String zzeto;
    public final int zzetp;
    public final int zzetq;
    public final int zzetr;
    public final boolean zzets;
    public final boolean zzett;

    public zzbdy(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.zzetk = zza(jSONObject, "aggressive_media_codec_release", zzaan.zzcph);
        this.zzetl = zzb(jSONObject, "byte_buffer_precache_limit", zzaan.zzcor);
        this.zzetm = zzb(jSONObject, "exo_cache_buffer_size", zzaan.zzcov);
        this.zzetn = zzb(jSONObject, "exo_connect_timeout_millis", zzaan.zzcon);
        this.zzeto = zzc(jSONObject, "exo_player_version", zzaan.zzcom);
        this.zzetp = zzb(jSONObject, "exo_read_timeout_millis", zzaan.zzcoo);
        this.zzetq = zzb(jSONObject, "load_check_interval_bytes", zzaan.zzcop);
        this.zzetr = zzb(jSONObject, "player_precache_limit", zzaan.zzcoq);
        this.zzets = zza(jSONObject, "use_cache_data_source", zzaan.zzcvu);
        this.zzett = zzb(jSONObject, "use_dash", false);
    }

    private static boolean zza(JSONObject jSONObject, String str, zzaac<Boolean> zzaac) {
        return zzb(jSONObject, str, ((Boolean) zzwu.zzpz().zzd(zzaac)).booleanValue());
    }

    private static boolean zzb(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException unused) {
            }
        }
        return z;
    }

    private static int zzb(JSONObject jSONObject, String str, zzaac<Integer> zzaac) {
        if (jSONObject != null) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
            }
        }
        return ((Integer) zzwu.zzpz().zzd(zzaac)).intValue();
    }

    private static String zzc(JSONObject jSONObject, String str, zzaac<String> zzaac) {
        if (jSONObject != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
            }
        }
        return (String) zzwu.zzpz().zzd(zzaac);
    }
}
