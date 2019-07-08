package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzakq {
    public final String zzdku;
    public final String zzdkv;
    public final List<String> zzdkw;
    public final String zzdkx;
    public final String zzdky;
    public final List<String> zzdkz;
    public final List<String> zzdla;
    public final List<String> zzdlb;
    public final List<String> zzdlc;
    public final List<String> zzdld;
    public final String zzdle;
    public final List<String> zzdlf;
    public final List<String> zzdlg;
    public final List<String> zzdlh;
    @Nullable
    public final String zzdli;
    @Nullable
    public final String zzdlj;
    public final String zzdlk;
    @Nullable
    public final List<String> zzdll;
    public final String zzdlm;
    @Nullable
    private final String zzdln;
    public final long zzdlo;

    public zzakq(String str, String str2, List<String> list, String str3, String str4, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String str5, String str6, List<String> list6, List<String> list7, List<String> list8, String str7, String str8, String str9, List<String> list9, String str10, List<String> list10, String str11, long j) {
        this.zzdku = str;
        this.zzdkv = null;
        this.zzdkw = list;
        this.zzdkx = null;
        this.zzdky = null;
        this.zzdkz = list2;
        this.zzdla = list3;
        this.zzdlb = list4;
        this.zzdlc = list5;
        this.zzdle = str5;
        this.zzdlf = list6;
        this.zzdlg = list7;
        this.zzdlh = list8;
        this.zzdli = null;
        this.zzdlj = null;
        this.zzdlk = null;
        this.zzdll = null;
        this.zzdlm = null;
        this.zzdld = list10;
        this.zzdln = null;
        this.zzdlo = -1;
    }

    public zzakq(JSONObject jSONObject) throws JSONException {
        List<String> list;
        this.zzdkv = jSONObject.optString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.zzdkw = Collections.unmodifiableList(arrayList);
        this.zzdkx = jSONObject.optString("allocation_id", null);
        zzbv.zzlz();
        this.zzdkz = zzakz.zza(jSONObject, "clickurl");
        zzbv.zzlz();
        this.zzdla = zzakz.zza(jSONObject, "imp_urls");
        zzbv.zzlz();
        this.zzdlb = zzakz.zza(jSONObject, "downloaded_imp_urls");
        zzbv.zzlz();
        this.zzdld = zzakz.zza(jSONObject, "fill_urls");
        zzbv.zzlz();
        this.zzdlf = zzakz.zza(jSONObject, "video_start_urls");
        zzbv.zzlz();
        this.zzdlh = zzakz.zza(jSONObject, "video_complete_urls");
        zzbv.zzlz();
        this.zzdlg = zzakz.zza(jSONObject, "video_reward_urls");
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null) {
            zzbv.zzlz();
            list = zzakz.zza(optJSONObject, "manual_impression_urls");
        } else {
            list = null;
        }
        this.zzdlc = list;
        this.zzdku = optJSONObject != null ? optJSONObject.toString() : null;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
        this.zzdle = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.zzdky = optJSONObject2 != null ? optJSONObject2.optString("class_name") : null;
        this.zzdli = jSONObject.optString("html_template", null);
        this.zzdlj = jSONObject.optString("ad_base_url", null);
        JSONObject optJSONObject3 = jSONObject.optJSONObject("assets");
        this.zzdlk = optJSONObject3 != null ? optJSONObject3.toString() : null;
        zzbv.zzlz();
        this.zzdll = zzakz.zza(jSONObject, "template_ids");
        JSONObject optJSONObject4 = jSONObject.optJSONObject("ad_loader_options");
        this.zzdlm = optJSONObject4 != null ? optJSONObject4.toString() : null;
        this.zzdln = jSONObject.optString("response_type", null);
        this.zzdlo = jSONObject.optLong("ad_network_timeout_millis", -1);
    }

    public final boolean zzuj() {
        return "banner".equalsIgnoreCase(this.zzdln);
    }

    public final boolean zzuk() {
        return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equalsIgnoreCase(this.zzdln);
    }
}
