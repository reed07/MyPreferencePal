package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzacf;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzasj;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzwb;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

final class zzbe implements Callable<zzacf> {
    private final /* synthetic */ zzaxg zzblz;
    private final /* synthetic */ int zzbqf;
    private final /* synthetic */ JSONArray zzbqg;
    private final /* synthetic */ int zzbqh;
    private final /* synthetic */ zzbb zzbqi;

    zzbe(zzbb zzbb, int i, JSONArray jSONArray, int i2, zzaxg zzaxg) {
        this.zzbqi = zzbb;
        this.zzbqf = i;
        this.zzbqg = jSONArray;
        this.zzbqh = i2;
        this.zzblz = zzaxg;
    }

    public final /* synthetic */ Object call() throws Exception {
        Bundle bundle;
        if (this.zzbqf >= this.zzbqg.length()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.zzbqg.get(this.zzbqf));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ads", jSONArray);
        zzbb zzbb = new zzbb(this.zzbqi.zzbls.zzsp, this.zzbqi.zzbly, this.zzbqi.zzbls.zzbst, this.zzbqi.zzbls.zzbsn, this.zzbqi.zzbma, this.zzbqi.zzbls.zzbsp, true);
        zzbb zzbb2 = this.zzbqi;
        zzbb.zza(zzbb2.zzbls, zzbb.zzbls);
        zzbb.zzkm();
        zzbb.zza(this.zzbqi.zzblo);
        zzaba zzaba = zzbb.zzbln;
        int i = this.zzbqf;
        zzaba.zzg("num_ads_requested", String.valueOf(this.zzbqh));
        zzaba.zzg("ad_index", String.valueOf(i));
        zzasi zzasi = this.zzblz.zzeag;
        String jSONObject2 = jSONObject.toString();
        if (zzasi.zzdwg.extras != null) {
            bundle = new Bundle(zzasi.zzdwg.extras);
        } else {
            bundle = new Bundle();
        }
        bundle.putString("_ad", jSONObject2);
        zzwb zzwb = r5;
        zzwb zzwb2 = new zzwb(zzasi.zzdwg.versionCode, zzasi.zzdwg.zzcjb, bundle, zzasi.zzdwg.zzcjc, zzasi.zzdwg.zzcjd, zzasi.zzdwg.zzcje, zzasi.zzdwg.zzcjf, zzasi.zzdwg.zzcjg, zzasi.zzdwg.zzcjh, zzasi.zzdwg.zzcji, zzasi.zzdwg.zzcjj, zzasi.zzdwg.zzcjk, zzasi.zzdwg.zzcjl, zzasi.zzdwg.zzcjm, zzasi.zzdwg.zzcjn, zzasi.zzdwg.zzcjo, zzasi.zzdwg.zzcjp, zzasi.zzdwg.zzcjq, null, zzasi.zzdwg.zzcjs, zzasi.zzdwg.zzcjt);
        zzasj zzasj = new zzasj(zzasi.zzdwf, zzwb, zzasi.zzbst, zzasi.zzbsn, zzasi.applicationInfo, zzasi.zzdwh, zzasi.zzdwj, zzasi.zzclm, zzasi.zzbsp, zzasi.zzdwk, zzasi.zzbtt, zzasi.zzdwu, zzasi.zzdwm, zzasi.zzdwn, zzasi.zzdwo, zzasi.zzdwp, zzasi.zzbwv, zzasi.zzdwq, zzasi.zzdwr, zzasi.zzdws, zzasi.zzdwt, zzasi.zzbsm, zzasi.zzbti, zzasi.zzdww, zzasi.zzdwx, zzasi.zzdxd, zzasi.zzdwy, zzasi.zzdwz, zzasi.zzdxa, zzasi.zzdxb, zzbbq.zzm(zzasi.zzdxc), zzasi.zzdxe, zzasi.zzdlv, zzasi.zzdxf, zzasi.zzdxg, zzasi.zzdxh, zzasi.zzbtl, zzasi.zzdxi, zzasi.zzdxj, zzasi.zzdxn, zzbbq.zzm(zzasi.zzdwi), zzasi.zzbtn, zzasi.zzdxo, zzasi.zzdxp, 1, zzasi.zzdxr, zzasi.zzdxs, zzasi.zzdxt, zzasi.zzdxu, zzasi.zzdxv, zzasi.zzbtk, zzasi.zzdxx);
        zzbb.zza(zzasj, zzbb.zzbln);
        return (zzacf) zzbb.zzko().get();
    }
}
