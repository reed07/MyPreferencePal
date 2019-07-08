package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzuo.zza.zzb;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzarn extends zzaxv implements zzasa {
    private final Context mContext;
    @VisibleForTesting
    private zzakr zzdmn;
    @VisibleForTesting
    private zzasi zzdnh;
    @VisibleForTesting
    private zzasm zzdsl;
    private Runnable zzdsm;
    private final Object zzdsn = new Object();
    private final zzarm zzdvp;
    private final zzasj zzdvq;
    private final zzum zzdvr;
    private final zzur zzdvs;
    @GuardedBy("mCancelLock")
    @VisibleForTesting
    private boolean zzdvt;
    @GuardedBy("mCancelLock")
    @VisibleForTesting
    private zzazb zzdvu;

    public zzarn(Context context, zzasj zzasj, zzarm zzarm, zzur zzur) {
        this.zzdvp = zzarm;
        this.mContext = context;
        this.zzdvq = zzasj;
        this.zzdvs = zzur;
        this.zzdvr = new zzum(this.zzdvs);
        this.zzdvr.zza((zzun) new zzaro(this));
        zzvq zzvq = new zzvq();
        zzvq.zzchy = Integer.valueOf(this.zzdvq.zzbsp.zzeou);
        zzvq.zzchz = Integer.valueOf(this.zzdvq.zzbsp.zzeov);
        zzvq.zzcia = Integer.valueOf(this.zzdvq.zzbsp.zzeow ? 0 : 2);
        this.zzdvr.zza((zzun) new zzarp(zzvq));
        if (this.zzdvq.zzdwh != null) {
            this.zzdvr.zza((zzun) new zzarq(this));
        }
        zzwf zzwf = this.zzdvq.zzbst;
        if (zzwf.zzckl && "interstitial_mb".equals(zzwf.zzckk)) {
            this.zzdvr.zza(zzarr.zzdvx);
        } else if (zzwf.zzckl && "reward_mb".equals(zzwf.zzckk)) {
            this.zzdvr.zza(zzars.zzdvx);
        } else if (zzwf.zzckn || zzwf.zzckl) {
            this.zzdvr.zza(zzaru.zzdvx);
        } else {
            this.zzdvr.zza(zzart.zzdvx);
        }
        this.zzdvr.zza(zzb.AD_REQUEST);
    }

    public final void zzki() {
        zzaxz.zzdn("AdLoaderBackgroundTask started.");
        this.zzdsm = new zzarv(this);
        zzayh.zzelc.postDelayed(this.zzdsm, ((Long) zzwu.zzpz().zzd(zzaan.zzcte)).longValue());
        long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
        if (this.zzdvq.zzdwg.extras != null) {
            String string = this.zzdvq.zzdwg.extras.getString("_ad");
            if (string != null) {
                zzasi zzasi = new zzasi(this.zzdvq, elapsedRealtime, null, null, null, null);
                this.zzdnh = zzasi;
                zza(zzatv.zza(this.mContext, this.zzdnh, string));
                return;
            }
        }
        zzbcr zzbcr = new zzbcr();
        zzayf.zzc(new zzarw(this, zzbcr));
        zzasi zzasi2 = new zzasi(this.zzdvq, elapsedRealtime, zzbv.zzmf().zzx(this.mContext), zzbv.zzmf().zzy(this.mContext), zzbv.zzmf().zzz(this.mContext), zzbv.zzmf().zzaa(this.mContext));
        this.zzdnh = zzasi2;
        zzbcr.zzo(this.zzdnh);
    }

    private final void zzd(int i, String str) {
        zzasi zzasi;
        int i2 = i;
        if (i2 == 3 || i2 == -1) {
            zzaxz.zzen(str);
        } else {
            zzaxz.zzeo(str);
        }
        zzasm zzasm = this.zzdsl;
        if (zzasm == null) {
            this.zzdsl = new zzasm(i2);
        } else {
            this.zzdsl = new zzasm(i2, zzasm.zzdlx);
        }
        zzasi zzasi2 = this.zzdnh;
        if (zzasi2 != null) {
            zzasi = zzasi2;
        } else {
            zzasi zzasi3 = new zzasi(this.zzdvq, -1, null, null, null, null);
            zzasi = zzasi3;
        }
        zzasm zzasm2 = this.zzdsl;
        zzaxg zzaxg = new zzaxg(zzasi, zzasm2, this.zzdmn, null, i, -1, zzasm2.zzdyh, null, this.zzdvr, null);
        this.zzdvp.zza(zzaxg);
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01dd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(@android.support.annotation.NonNull com.google.android.gms.internal.ads.zzasm r14) {
        /*
            r13 = this;
            java.lang.String r0 = "Received ad response."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r0)
            r13.zzdsl = r14
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl
            java.lang.String r14 = r14.zzdze
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            if (r0 != 0) goto L_0x001a
            com.google.android.gms.internal.ads.zzaww r0 = com.google.android.gms.ads.internal.zzbv.zzmf()
            android.content.Context r1 = r13.mContext
            r0.zzh(r1, r14)
        L_0x001a:
            com.google.android.gms.common.util.Clock r14 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r6 = r14.elapsedRealtime()
            java.lang.Object r14 = r13.zzdsn
            monitor-enter(r14)
            r0 = 0
            r13.zzdvu = r0     // Catch:{ all -> 0x022e }
            monitor-exit(r14)     // Catch:{ all -> 0x022e }
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r14 = r14.zzyq()
            com.google.android.gms.internal.ads.zzasm r1 = r13.zzdsl
            boolean r1 = r1.zzdxb
            r14.zzap(r1)
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r14 = com.google.android.gms.internal.ads.zzaan.zzcrz
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r14 = r1.zzd(r14)
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x006f
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl
            boolean r14 = r14.zzdxn
            if (r14 == 0) goto L_0x0060
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r14 = r14.zzyq()
            com.google.android.gms.internal.ads.zzasi r1 = r13.zzdnh
            java.lang.String r1 = r1.zzbsn
            r14.zzds(r1)
            goto L_0x006f
        L_0x0060:
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r14 = r14.zzyq()
            com.google.android.gms.internal.ads.zzasi r1 = r13.zzdnh
            java.lang.String r1 = r1.zzbsn
            r14.zzdt(r1)
        L_0x006f:
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            int r14 = r14.errorCode     // Catch:{ zzarx -> 0x021a }
            r1 = -2
            r2 = -3
            if (r14 == r1) goto L_0x009f
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            int r14 = r14.errorCode     // Catch:{ zzarx -> 0x021a }
            if (r14 != r2) goto L_0x007e
            goto L_0x009f
        L_0x007e:
            com.google.android.gms.internal.ads.zzarx r14 = new com.google.android.gms.internal.ads.zzarx     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzasm r0 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            int r0 = r0.errorCode     // Catch:{ zzarx -> 0x021a }
            r1 = 66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ zzarx -> 0x021a }
            r2.<init>(r1)     // Catch:{ zzarx -> 0x021a }
            java.lang.String r1 = "There was a problem getting an ad response. ErrorCode: "
            r2.append(r1)     // Catch:{ zzarx -> 0x021a }
            r2.append(r0)     // Catch:{ zzarx -> 0x021a }
            java.lang.String r0 = r2.toString()     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzasm r1 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            int r1 = r1.errorCode     // Catch:{ zzarx -> 0x021a }
            r14.<init>(r0, r1)     // Catch:{ zzarx -> 0x021a }
            throw r14     // Catch:{ zzarx -> 0x021a }
        L_0x009f:
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            int r14 = r14.errorCode     // Catch:{ zzarx -> 0x021a }
            r1 = 0
            if (r14 == r2) goto L_0x013c
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            java.lang.String r14 = r14.zzdyb     // Catch:{ zzarx -> 0x021a }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ zzarx -> 0x021a }
            if (r14 != 0) goto L_0x0133
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzayb r14 = r14.zzyq()     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            boolean r2 = r2.zzdwn     // Catch:{ zzarx -> 0x021a }
            r14.zzam(r2)     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            boolean r14 = r14.zzdyd     // Catch:{ zzarx -> 0x021a }
            if (r14 == 0) goto L_0x0103
            com.google.android.gms.internal.ads.zzakr r14 = new com.google.android.gms.internal.ads.zzakr     // Catch:{ JSONException -> 0x00dc }
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl     // Catch:{ JSONException -> 0x00dc }
            java.lang.String r2 = r2.zzdyb     // Catch:{ JSONException -> 0x00dc }
            r14.<init>(r2)     // Catch:{ JSONException -> 0x00dc }
            r13.zzdmn = r14     // Catch:{ JSONException -> 0x00dc }
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()     // Catch:{ JSONException -> 0x00dc }
            com.google.android.gms.internal.ads.zzakr r2 = r13.zzdmn     // Catch:{ JSONException -> 0x00dc }
            boolean r2 = r2.zzdlv     // Catch:{ JSONException -> 0x00dc }
            r14.zzal(r2)     // Catch:{ JSONException -> 0x00dc }
            goto L_0x010e
        L_0x00dc:
            r14 = move-exception
            java.lang.String r0 = "Could not parse mediation config."
            com.google.android.gms.internal.ads.zzaxz.zzb(r0, r14)     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzarx r14 = new com.google.android.gms.internal.ads.zzarx     // Catch:{ zzarx -> 0x021a }
            java.lang.String r0 = "Could not parse mediation config: "
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            java.lang.String r2 = r2.zzdyb     // Catch:{ zzarx -> 0x021a }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ zzarx -> 0x021a }
            int r3 = r2.length()     // Catch:{ zzarx -> 0x021a }
            if (r3 == 0) goto L_0x00f9
            java.lang.String r0 = r0.concat(r2)     // Catch:{ zzarx -> 0x021a }
            goto L_0x00ff
        L_0x00f9:
            java.lang.String r2 = new java.lang.String     // Catch:{ zzarx -> 0x021a }
            r2.<init>(r0)     // Catch:{ zzarx -> 0x021a }
            r0 = r2
        L_0x00ff:
            r14.<init>(r0, r1)     // Catch:{ zzarx -> 0x021a }
            throw r14     // Catch:{ zzarx -> 0x021a }
        L_0x0103:
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            boolean r2 = r2.zzdlv     // Catch:{ zzarx -> 0x021a }
            r14.zzal(r2)     // Catch:{ zzarx -> 0x021a }
        L_0x010e:
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            java.lang.String r14 = r14.zzdxc     // Catch:{ zzarx -> 0x021a }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ zzarx -> 0x021a }
            if (r14 != 0) goto L_0x013c
            java.lang.String r14 = "Received cookie from server. Setting webview cookie in CookieManager."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r14)     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzayp r14 = com.google.android.gms.ads.internal.zzbv.zzlh()     // Catch:{ zzarx -> 0x021a }
            android.content.Context r2 = r13.mContext     // Catch:{ zzarx -> 0x021a }
            android.webkit.CookieManager r14 = r14.zzba(r2)     // Catch:{ zzarx -> 0x021a }
            if (r14 == 0) goto L_0x013c
            java.lang.String r2 = "googleads.g.doubleclick.net"
            com.google.android.gms.internal.ads.zzasm r3 = r13.zzdsl     // Catch:{ zzarx -> 0x021a }
            java.lang.String r3 = r3.zzdxc     // Catch:{ zzarx -> 0x021a }
            r14.setCookie(r2, r3)     // Catch:{ zzarx -> 0x021a }
            goto L_0x013c
        L_0x0133:
            com.google.android.gms.internal.ads.zzarx r14 = new com.google.android.gms.internal.ads.zzarx     // Catch:{ zzarx -> 0x021a }
            java.lang.String r0 = "No fill from ad server."
            r1 = 3
            r14.<init>(r0, r1)     // Catch:{ zzarx -> 0x021a }
            throw r14     // Catch:{ zzarx -> 0x021a }
        L_0x013c:
            com.google.android.gms.internal.ads.zzasi r14 = r13.zzdnh     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzwf r14 = r14.zzbst     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzwf[] r14 = r14.zzckm     // Catch:{ zzarx -> 0x021a }
            if (r14 == 0) goto L_0x014c
            com.google.android.gms.internal.ads.zzasi r14 = r13.zzdnh     // Catch:{ zzarx -> 0x021a }
            com.google.android.gms.internal.ads.zzwf r14 = r13.zza(r14)     // Catch:{ zzarx -> 0x021a }
            r4 = r14
            goto L_0x014d
        L_0x014c:
            r4 = r0
        L_0x014d:
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r14 = r14.zzyq()
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl
            boolean r2 = r2.zzdyn
            r14.zzan(r2)
            com.google.android.gms.internal.ads.zzaxk r14 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r14 = r14.zzyq()
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl
            boolean r2 = r2.zzdyz
            r14.zzao(r2)
            com.google.android.gms.internal.ads.zzasm r14 = r13.zzdsl
            java.lang.String r14 = r14.zzdyl
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x0186
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ Exception -> 0x0180 }
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl     // Catch:{ Exception -> 0x0180 }
            java.lang.String r2 = r2.zzdyl     // Catch:{ Exception -> 0x0180 }
            r14.<init>(r2)     // Catch:{ Exception -> 0x0180 }
            r10 = r14
            goto L_0x0187
        L_0x0180:
            r14 = move-exception
            java.lang.String r2 = "Error parsing the JSON for Active View."
            com.google.android.gms.internal.ads.zzaxz.zzb(r2, r14)
        L_0x0186:
            r10 = r0
        L_0x0187:
            com.google.android.gms.internal.ads.zzasi r14 = r13.zzdnh
            com.google.android.gms.internal.ads.zzwb r14 = r14.zzdwg
            android.os.Bundle r14 = r14.zzcjl
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl
            int r2 = r2.zzdzb
            r3 = 2
            r5 = 1
            if (r2 != r3) goto L_0x01ba
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r5)
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r1 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r1 = r1.getName()
            android.os.Bundle r1 = r14.getBundle(r1)
            if (r1 != 0) goto L_0x01b3
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r2 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r2 = r2.getName()
            r14.putBundle(r2, r1)
        L_0x01b3:
            java.lang.String r2 = "render_test_ad_label"
            r1.putBoolean(r2, r5)
            r12 = r0
            goto L_0x01d7
        L_0x01ba:
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl
            int r2 = r2.zzdzb
            if (r2 != r5) goto L_0x01c6
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r12 = r0
            goto L_0x01d7
        L_0x01c6:
            com.google.android.gms.internal.ads.zzasm r1 = r13.zzdsl
            int r1 = r1.zzdzb
            if (r1 != 0) goto L_0x01d6
            boolean r0 = com.google.android.gms.internal.ads.zzbal.zzf(r14)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r12 = r0
            goto L_0x01d7
        L_0x01d6:
            r12 = r0
        L_0x01d7:
            com.google.android.gms.internal.ads.zzasm r0 = r13.zzdsl
            boolean r0 = r0.zzdzf
            if (r0 == 0) goto L_0x01fc
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r0 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r0 = r0.getName()
            android.os.Bundle r0 = r14.getBundle(r0)
            if (r0 != 0) goto L_0x01f7
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r1 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r1 = r1.getName()
            r14.putBundle(r1, r0)
        L_0x01f7:
            java.lang.String r14 = "is_analytics_logging_enabled"
            r0.putBoolean(r14, r5)
        L_0x01fc:
            com.google.android.gms.internal.ads.zzaxg r14 = new com.google.android.gms.internal.ads.zzaxg
            com.google.android.gms.internal.ads.zzasi r1 = r13.zzdnh
            com.google.android.gms.internal.ads.zzasm r2 = r13.zzdsl
            com.google.android.gms.internal.ads.zzakr r3 = r13.zzdmn
            r5 = -2
            long r8 = r2.zzdyh
            com.google.android.gms.internal.ads.zzum r11 = r13.zzdvr
            r0 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r8, r10, r11, r12)
            com.google.android.gms.internal.ads.zzarm r0 = r13.zzdvp
            r0.zza(r14)
            android.os.Handler r14 = com.google.android.gms.internal.ads.zzayh.zzelc
            java.lang.Runnable r0 = r13.zzdsm
            r14.removeCallbacks(r0)
            return
        L_0x021a:
            r14 = move-exception
            int r0 = r14.getErrorCode()
            java.lang.String r14 = r14.getMessage()
            r13.zzd(r0, r14)
            android.os.Handler r14 = com.google.android.gms.internal.ads.zzayh.zzelc
            java.lang.Runnable r0 = r13.zzdsm
            r14.removeCallbacks(r0)
            return
        L_0x022e:
            r0 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x022e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzarn.zza(com.google.android.gms.internal.ads.zzasm):void");
    }

    public final void onStop() {
        synchronized (this.zzdsn) {
            if (this.zzdvu != null) {
                this.zzdvu.cancel();
            }
        }
    }

    @VisibleForTesting
    private final zzwf zza(zzasi zzasi) throws zzarx {
        int i;
        zzwf[] zzwfArr;
        zzasi zzasi2 = this.zzdnh;
        if ((zzasi2 == null || zzasi2.zzbtn == null || this.zzdnh.zzbtn.size() <= 1) ? false : true) {
            zzakr zzakr = this.zzdmn;
            if (zzakr != null && !zzakr.zzdmi) {
                return null;
            }
        }
        if (this.zzdsl.zzcko) {
            for (zzwf zzwf : zzasi.zzbst.zzckm) {
                if (zzwf.zzcko) {
                    return new zzwf(zzwf, zzasi.zzbst.zzckm);
                }
            }
        }
        if (this.zzdsl.zzdyg != null) {
            String[] split = this.zzdsl.zzdyg.split(AvidJSONUtil.KEY_X);
            if (split.length != 2) {
                String str = "Invalid ad size format from the ad response: ";
                String valueOf = String.valueOf(this.zzdsl.zzdyg);
                throw new zzarx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
            }
            try {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                zzwf[] zzwfArr2 = zzasi.zzbst.zzckm;
                int length = zzwfArr2.length;
                for (int i2 = 0; i2 < length; i2++) {
                    zzwf zzwf2 = zzwfArr2[i2];
                    float f = this.mContext.getResources().getDisplayMetrics().density;
                    int i3 = zzwf2.width == -1 ? (int) (((float) zzwf2.widthPixels) / f) : zzwf2.width;
                    if (zzwf2.height == -2) {
                        i = (int) (((float) zzwf2.heightPixels) / f);
                    } else {
                        i = zzwf2.height;
                    }
                    if (parseInt == i3 && parseInt2 == i && !zzwf2.zzcko) {
                        return new zzwf(zzwf2, zzasi.zzbst.zzckm);
                    }
                }
                String str2 = "The ad size from the ad response was not one of the requested sizes: ";
                String valueOf2 = String.valueOf(this.zzdsl.zzdyg);
                throw new zzarx(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), 0);
            } catch (NumberFormatException unused) {
                String str3 = "Invalid ad size number from the ad response: ";
                String valueOf3 = String.valueOf(this.zzdsl.zzdyg);
                throw new zzarx(valueOf3.length() != 0 ? str3.concat(valueOf3) : new String(str3), 0);
            }
        } else {
            throw new zzarx("The ad response must specify one of the supported ad sizes.", 0);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzbcn r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzdsn
            monitor-enter(r0)
            boolean r1 = r4.zzdvt     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x000e
            java.lang.String r5 = "Request task was already canceled"
            com.google.android.gms.internal.ads.zzaxz.zzeo(r5)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x000e:
            com.google.android.gms.internal.ads.zzasj r1 = r4.zzdvq     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzbbi r1 = r1.zzbsp     // Catch:{ all -> 0x0060 }
            android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzarz r3 = new com.google.android.gms.internal.ads.zzarz     // Catch:{ all -> 0x0060 }
            r3.<init>(r2)     // Catch:{ all -> 0x0060 }
            boolean r3 = r3.zza(r1)     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x002d
            java.lang.String r1 = "Fetching ad response from local ad request service."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r1)     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzasf r1 = new com.google.android.gms.internal.ads.zzasf     // Catch:{ all -> 0x0060 }
            r1.<init>(r2, r5, r4)     // Catch:{ all -> 0x0060 }
            r1.zzwa()     // Catch:{ all -> 0x0060 }
            goto L_0x004b
        L_0x002d:
            java.lang.String r3 = "Fetching ad response from remote ad request service."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r3)     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzwu.zzpv()     // Catch:{ all -> 0x0060 }
            r3 = 12451000(0xbdfcb8, float:1.7447567E-38)
            boolean r3 = com.google.android.gms.internal.ads.zzbat.zzc(r2, r3)     // Catch:{ all -> 0x0060 }
            if (r3 != 0) goto L_0x0045
            java.lang.String r5 = "Failed to connect to remote ad request service."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r5)     // Catch:{ all -> 0x0060 }
            r1 = 0
            goto L_0x004b
        L_0x0045:
            com.google.android.gms.internal.ads.zzasg r3 = new com.google.android.gms.internal.ads.zzasg     // Catch:{ all -> 0x0060 }
            r3.<init>(r2, r1, r5, r4)     // Catch:{ all -> 0x0060 }
            r1 = r3
        L_0x004b:
            r4.zzdvu = r1     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzazb r5 = r4.zzdvu     // Catch:{ all -> 0x0060 }
            if (r5 != 0) goto L_0x005e
            r5 = 0
            java.lang.String r1 = "Could not start the ad request service."
            r4.zzd(r5, r1)     // Catch:{ all -> 0x0060 }
            android.os.Handler r5 = com.google.android.gms.internal.ads.zzayh.zzelc     // Catch:{ all -> 0x0060 }
            java.lang.Runnable r1 = r4.zzdsm     // Catch:{ all -> 0x0060 }
            r5.removeCallbacks(r1)     // Catch:{ all -> 0x0060 }
        L_0x005e:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzarn.zza(com.google.android.gms.internal.ads.zzbcn):void");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzwh() {
        synchronized (this.zzdsn) {
            this.zzdvt = true;
            if (this.zzdvu != null) {
                onStop();
            }
            zzd(2, "Timed out waiting for ad response.");
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(zzvp zzvp) {
        zzvp.zzchu.zzchc = this.zzdvq.zzdwh.packageName;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(zzvp zzvp) {
        zzvp.zzchp = this.zzdvq.zzdws;
    }
}
