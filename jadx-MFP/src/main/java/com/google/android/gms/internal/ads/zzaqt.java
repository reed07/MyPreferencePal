package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import org.json.JSONObject;

@zzark
public final class zzaqt implements zzaqp<zzbgg> {
    private final Context mContext;
    private final zzbbi zzbpt;
    /* access modifiers changed from: private */
    public String zzbqb;
    private final zzcu zzdcf;
    private final zzbb zzdug;
    private zzbcb<zzbgg> zzduv;
    private final zzaa zzduw = new zzaa(this.mContext);
    private final zzaci zzdux;

    public zzaqt(Context context, zzbb zzbb, String str, zzcu zzcu, zzbbi zzbbi) {
        zzaxz.zzen("Webview loading for native ads.");
        this.mContext = context;
        this.zzdug = zzbb;
        this.zzdcf = zzcu;
        this.zzbpt = zzbbi;
        this.zzbqb = str;
        zzbv.zzlg();
        zzbcb zza = zzbgm.zza(this.mContext, this.zzbpt, (String) zzwu.zzpz().zzd(zzaan.zzcud), this.zzdcf, this.zzdug.zzid());
        this.zzdux = new zzaci(zzbb, str);
        this.zzduv = zzbbq.zza(zza, (zzbbl<? super A, ? extends B>) new zzaqu<Object,Object>(this), zzbcg.zzepp);
        zzbbo.zza(this.zzduv, "WebViewNativeAdsUtil.constructor");
    }

    public final zzbcb<JSONObject> zzh(JSONObject jSONObject) {
        return zzbbq.zza(this.zzduv, (zzbbl<? super A, ? extends B>) new zzaqv<Object,Object>(this, jSONObject), zzbcg.zzepo);
    }

    public final zzbcb<JSONObject> zzi(JSONObject jSONObject) {
        return zzbbq.zza(this.zzduv, (zzbbl<? super A, ? extends B>) new zzaqw<Object,Object>(this, jSONObject), zzbcg.zzepo);
    }

    public final zzbcb<JSONObject> zzj(JSONObject jSONObject) {
        return zzbbq.zza(this.zzduv, (zzbbl<? super A, ? extends B>) new zzaqx<Object,Object>(this, jSONObject), zzbcg.zzepo);
    }

    public final zzbcb<JSONObject> zzk(JSONObject jSONObject) {
        return zzbbq.zza(this.zzduv, (zzbbl<? super A, ? extends B>) new zzaqy<Object,Object>(this, jSONObject), zzbcg.zzepo);
    }

    public final void zza(String str, zzu<? super zzbgg> zzu) {
        zzbbq.zza(this.zzduv, (zzbbn<V>) new zzara<V>(this, str, zzu), zzbcg.zzepo);
    }

    public final void zzb(String str, zzu<? super zzbgg> zzu) {
        zzbbq.zza(this.zzduv, (zzbbn<V>) new zzarb<V>(this, str, zzu), zzbcg.zzepo);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzbbq.zza(this.zzduv, (zzbbn<V>) new zzarc<V>(this, str, jSONObject), zzbcg.zzepo);
    }

    public final void zzug() {
        zzbbq.zza(this.zzduv, (zzbbn<V>) new zzard<V>(this), zzbcg.zzepo);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zza(JSONObject jSONObject, zzbgg zzbgg) throws Exception {
        jSONObject.put("ads_id", this.zzbqb);
        zzbgg.zzb("google.afma.nativeAds.handleDownloadedImpressionPing", jSONObject);
        return zzbbq.zzm(new JSONObject());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zzb(JSONObject jSONObject, zzbgg zzbgg) throws Exception {
        jSONObject.put("ads_id", this.zzbqb);
        zzbgg.zzb("google.afma.nativeAds.handleImpressionPing", jSONObject);
        return zzbbq.zzm(new JSONObject());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zzc(JSONObject jSONObject, zzbgg zzbgg) throws Exception {
        jSONObject.put("ads_id", this.zzbqb);
        zzbgg.zzb("google.afma.nativeAds.handleClickGmsg", jSONObject);
        return zzbbq.zzm(new JSONObject());
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zzd(JSONObject jSONObject, zzbgg zzbgg) throws Exception {
        jSONObject.put("ads_id", this.zzbqb);
        zzbcl zzbcl = new zzbcl();
        zzbgg.zza("/nativeAdPreProcess", (zzu<? super zzbgg>) new zzaqz<Object>(this, zzbgg, zzbcl));
        zzbgg.zzb("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        return zzbcl;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zzh(zzbgg zzbgg) throws Exception {
        zzaxz.zzen("Javascript has loaded for native ads.");
        zzbhn zzadl = zzbgg.zzadl();
        zzbb zzbb = this.zzdug;
        zzadl.zza(zzbb, zzbb, zzbb, zzbb, zzbb, false, null, new zzw(this.mContext, null, null), null, null);
        zzbgg.zza("/logScionEvent", (zzu<? super zzbgg>) this.zzduw);
        zzbgg.zza("/logScionEvent", (zzu<? super zzbgg>) this.zzdux);
        return zzbbq.zzm(zzbgg);
    }
}
