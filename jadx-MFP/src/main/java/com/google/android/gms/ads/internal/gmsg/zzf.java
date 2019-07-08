package com.google.android.gms.ads.internal.gmsg;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.j.e;
import com.google.android.gms.internal.ads.zzahu;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawz;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbah;
import com.google.android.gms.internal.ads.zzbdz;
import com.google.android.gms.internal.ads.zzbff;
import com.google.android.gms.internal.ads.zzbfg;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbhc;
import com.google.android.gms.internal.ads.zzbhk;
import com.google.android.gms.internal.ads.zzbhl;
import com.google.android.gms.internal.ads.zzbhm;
import com.google.android.gms.internal.ads.zzcu;
import com.google.android.gms.internal.ads.zzcv;
import com.mopub.common.Constants;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzf {
    public static final zzu<zzbgg> zzdet = zzg.zzdfl;
    public static final zzu<zzbgg> zzdeu = zzh.zzdfl;
    public static final zzu<zzbgg> zzdev = zzi.zzdfl;
    public static final zzu<zzbgg> zzdew = new zzl();
    public static final zzu<zzbgg> zzdex = new zzm();
    public static final zzu<zzbgg> zzdey = zzj.zzdfl;
    public static final zzu<Object> zzdez = new zzn();
    public static final zzu<zzbgg> zzdfa = new zzo();
    public static final zzu<zzbgg> zzdfb = zzk.zzdfl;
    public static final zzu<zzbgg> zzdfc = new zzp();
    public static final zzu<zzbgg> zzdfd = new zzq();
    public static final zzu<zzbdz> zzdfe = new zzbff();
    public static final zzu<zzbdz> zzdff = new zzbfg();
    public static final zzu<zzbgg> zzdfg = new zze();
    public static final zzae zzdfh = new zzae();
    public static final zzu<zzbgg> zzdfi = new zzr();
    public static final zzu<zzbgg> zzdfj = new zzs();
    public static final zzu<zzbgg> zzdfk = new zzt();

    static final /* synthetic */ void zza(zzbhk zzbhk, Map map) {
        String str = (String) map.get("tx");
        String str2 = (String) map.get("ty");
        String str3 = (String) map.get("td");
        try {
            int parseInt = Integer.parseInt(str);
            int parseInt2 = Integer.parseInt(str2);
            int parseInt3 = Integer.parseInt(str3);
            zzcu zzado = zzbhk.zzado();
            if (zzado != null) {
                zzado.zzab().zza(parseInt, parseInt2, parseInt3);
            }
        } catch (NumberFormatException unused) {
            zzaxz.zzeo("Could not parse touch parameters from gmsg.");
        }
    }

    static final /* synthetic */ void zza(zzbhc zzbhc, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzaxz.zzeo("URL missing from httpTrack GMSG.");
        } else {
            new zzbah(zzbhc.getContext(), ((zzbhl) zzbhc).zzabz().zzdp, str).zzyz();
        }
    }

    static final /* synthetic */ void zza(zzahu zzahu, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzaxz.zzeo("URL missing from click GMSG.");
            return;
        }
        Uri parse = Uri.parse(str);
        try {
            zzcu zzado = ((zzbhk) zzahu).zzado();
            if (zzado != null && zzado.zzb(parse)) {
                parse = zzado.zza(parse, ((zzbhc) zzahu).getContext(), ((zzbhm) zzahu).getView(), ((zzbhc) zzahu).zzabw());
            }
        } catch (zzcv unused) {
            String str2 = "Unable to append parameter to URL: ";
            String valueOf = String.valueOf(str);
            zzaxz.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzbhc zzbhc = (zzbhc) zzahu;
        new zzbah(zzbhc.getContext(), ((zzbhl) zzahu).zzabz().zzdp, zzawz.zzb(parse, zzbhc.getContext())).zzyz();
    }

    static final /* synthetic */ void zzb(zzbhc zzbhc, Map map) {
        PackageManager packageManager = zzbhc.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String optString = jSONObject2.optString("id");
                        String optString2 = jSONObject2.optString("u");
                        String optString3 = jSONObject2.optString("i");
                        String optString4 = jSONObject2.optString("m");
                        String optString5 = jSONObject2.optString("p");
                        String optString6 = jSONObject2.optString("c");
                        jSONObject2.optString("f");
                        jSONObject2.optString(e.a);
                        String optString7 = jSONObject2.optString("intent_url");
                        Intent intent = null;
                        if (!TextUtils.isEmpty(optString7)) {
                            try {
                                intent = Intent.parseUri(optString7, 0);
                            } catch (URISyntaxException e) {
                                URISyntaxException uRISyntaxException = e;
                                String str = "Error parsing the url: ";
                                String valueOf = String.valueOf(optString7);
                                zzaxz.zzb(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), uRISyntaxException);
                            }
                        }
                        boolean z = true;
                        if (intent == null) {
                            intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                        }
                        if (packageManager.resolveActivity(intent, 65536) == null) {
                            z = false;
                        }
                        try {
                            jSONObject.put(optString, z);
                        } catch (JSONException e2) {
                            zzaxz.zzb("Error constructing openable urls response.", e2);
                        }
                    } catch (JSONException e3) {
                        zzaxz.zzb("Error parsing the intent data.", e3);
                    }
                }
                ((zzahu) zzbhc).zza("openableIntents", jSONObject);
            } catch (JSONException unused) {
                ((zzahu) zzbhc).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException unused2) {
            ((zzahu) zzbhc).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void zzc(zzbhc zzbhc, Map map) {
        String str = (String) map.get(Constants.VIDEO_TRACKING_URLS_KEY);
        if (TextUtils.isEmpty(str)) {
            zzaxz.zzeo("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] split = str.split(",");
        HashMap hashMap = new HashMap();
        PackageManager packageManager = zzbhc.getContext().getPackageManager();
        for (String str2 : split) {
            String[] split2 = str2.split(";", 2);
            boolean z = true;
            if (packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) == null) {
                z = false;
            }
            hashMap.put(str2, Boolean.valueOf(z));
        }
        ((zzahu) zzbhc).zza("openableURLs", (Map<String, ?>) hashMap);
    }
}
