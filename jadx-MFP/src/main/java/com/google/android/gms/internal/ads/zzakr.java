package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzakr {
    public final long zzdlo;
    public final List<zzakq> zzdlp;
    public final List<String> zzdlq;
    public final List<String> zzdlr;
    public final List<String> zzdls;
    public final List<String> zzdlt;
    public final List<String> zzdlu;
    public final boolean zzdlv;
    public final String zzdlw;
    public final long zzdlx;
    public final String zzdly;
    public final int zzdlz;
    public final int zzdma;
    public final long zzdmb;
    public final boolean zzdmc;
    public final boolean zzdmd;
    public final boolean zzdme;
    public final boolean zzdmf;
    public int zzdmg;
    public int zzdmh;
    public boolean zzdmi;

    public zzakr(List<zzakq> list, long j, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, boolean z, String str, long j2, int i, int i2, String str2, int i3, int i4, long j3, boolean z2) {
        this.zzdlp = list;
        this.zzdlo = j;
        this.zzdlq = list2;
        this.zzdlr = list3;
        this.zzdls = list4;
        this.zzdlt = list5;
        this.zzdlu = list6;
        this.zzdlv = z;
        this.zzdlw = str;
        this.zzdlx = -1;
        this.zzdmg = 0;
        this.zzdmh = 1;
        this.zzdly = null;
        this.zzdlz = 0;
        this.zzdma = -1;
        this.zzdmb = -1;
        this.zzdmc = false;
        this.zzdmd = false;
        this.zzdme = false;
        this.zzdmf = false;
        this.zzdmi = false;
    }

    public zzakr(String str) throws JSONException {
        this(new JSONObject(str));
    }

    public zzakr(JSONObject jSONObject) throws JSONException {
        if (zzaxz.isLoggable(2)) {
            String str = "Mediation Response JSON: ";
            String valueOf = String.valueOf(jSONObject.toString(2));
            zzaxz.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                zzakq zzakq = new zzakq(jSONArray.getJSONObject(i2));
                boolean z = true;
                if (zzakq.zzuj()) {
                    this.zzdmi = true;
                }
                arrayList.add(zzakq);
                if (i < 0) {
                    Iterator it = zzakq.zzdkw.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((String) it.next()).equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        i = i2;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        this.zzdmg = i;
        this.zzdmh = jSONArray.length();
        this.zzdlp = Collections.unmodifiableList(arrayList);
        this.zzdlw = jSONObject.optString("qdata");
        this.zzdma = jSONObject.optInt("fs_model_type", -1);
        long j = -1;
        this.zzdmb = jSONObject.optLong("timeout_ms", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.zzdlo = optJSONObject.optLong("ad_network_timeout_millis", -1);
            zzbv.zzlz();
            this.zzdlq = zzakz.zza(optJSONObject, "click_urls");
            zzbv.zzlz();
            this.zzdlr = zzakz.zza(optJSONObject, "imp_urls");
            zzbv.zzlz();
            this.zzdls = zzakz.zza(optJSONObject, "downloaded_imp_urls");
            zzbv.zzlz();
            this.zzdlt = zzakz.zza(optJSONObject, "nofill_urls");
            zzbv.zzlz();
            this.zzdlu = zzakz.zza(optJSONObject, "remote_ping_urls");
            this.zzdlv = optJSONObject.optBoolean("render_in_browser", false);
            long optLong = optJSONObject.optLong("refresh", -1);
            if (optLong > 0) {
                j = 1000 * optLong;
            }
            this.zzdlx = j;
            zzawd zza = zzawd.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.zzdly = null;
                this.zzdlz = 0;
            } else {
                this.zzdly = zza.type;
                this.zzdlz = zza.zzefo;
            }
            this.zzdmc = optJSONObject.optBoolean("use_displayed_impression", false);
            this.zzdmd = optJSONObject.optBoolean("allow_pub_rendered_attribution", false);
            this.zzdme = optJSONObject.optBoolean("allow_pub_owned_ad_view", false);
            this.zzdmf = optJSONObject.optBoolean("allow_custom_click_gesture", false);
            return;
        }
        this.zzdlo = -1;
        this.zzdlq = null;
        this.zzdlr = null;
        this.zzdls = null;
        this.zzdlt = null;
        this.zzdlu = null;
        this.zzdlx = -1;
        this.zzdly = null;
        this.zzdlz = 0;
        this.zzdmc = false;
        this.zzdlv = false;
        this.zzdmd = false;
        this.zzdme = false;
        this.zzdmf = false;
    }
}
