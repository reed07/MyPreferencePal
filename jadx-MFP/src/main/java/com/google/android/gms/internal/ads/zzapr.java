package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzapr extends zzaph {
    private final zzaba zzbln;
    private zzalg zzbma;
    /* access modifiers changed from: private */
    public final zzbgg zzdin;
    private zzakr zzdmn;
    @VisibleForTesting
    private zzakp zzdsw;
    protected zzakx zzdsx;
    /* access modifiers changed from: private */
    public boolean zzdsy;

    zzapr(Context context, zzaxg zzaxg, zzalg zzalg, zzapm zzapm, zzaba zzaba, zzbgg zzbgg) {
        super(context, zzaxg, zzapm);
        this.zzbma = zzalg;
        this.zzdmn = zzaxg.zzehj;
        this.zzbln = zzaba;
        this.zzdin = zzbgg;
    }

    /* access modifiers changed from: protected */
    public final zzaxf zzcr(int i) {
        String str;
        String str2;
        boolean z;
        zzakr zzakr;
        long j;
        zzaso zzaso;
        String str3;
        zzakr zzakr2;
        Iterator it;
        zzakr zzakr3;
        int i2;
        zzasi zzasi = this.zzdsk.zzeag;
        zzwb zzwb = zzasi.zzdwg;
        zzbgg zzbgg = this.zzdin;
        List<String> list = this.zzdsl.zzdlq;
        List<String> list2 = this.zzdsl.zzdlr;
        List<String> list3 = this.zzdsl.zzdyf;
        int i3 = this.zzdsl.orientation;
        long j2 = this.zzdsl.zzdlx;
        String str4 = zzasi.zzdwj;
        boolean z2 = this.zzdsl.zzdyd;
        zzakx zzakx = this.zzdsx;
        zzakq zzakq = zzakx != null ? zzakx.zzdnb : null;
        zzakx zzakx2 = this.zzdsx;
        zzalj zzalj = zzakx2 != null ? zzakx2.zzdnc : null;
        zzakx zzakx3 = this.zzdsx;
        if (zzakx3 != null) {
            str = zzakx3.zzdnd;
        } else {
            str = AdMobAdapter.class.getName();
        }
        zzakr zzakr4 = this.zzdmn;
        zzakx zzakx4 = this.zzdsx;
        zzakt zzakt = zzakx4 != null ? zzakx4.zzdne : null;
        zzakq zzakq2 = zzakq;
        zzalj zzalj2 = zzalj;
        long j3 = this.zzdsl.zzdye;
        zzwf zzwf = this.zzdsk.zzbst;
        long j4 = j3;
        long j5 = this.zzdsl.zzdyc;
        long j6 = this.zzdsk.zzehn;
        long j7 = this.zzdsl.zzdyh;
        String str5 = this.zzdsl.zzdyi;
        JSONObject jSONObject = this.zzdsk.zzehh;
        zzawd zzawd = this.zzdsl.zzdyr;
        List<String> list4 = this.zzdsl.zzdys;
        List<String> list5 = this.zzdsl.zzdyt;
        zzakr zzakr5 = this.zzdmn;
        zzwf zzwf2 = zzwf;
        boolean z3 = zzakr5 != null ? zzakr5.zzdmc : false;
        zzaso zzaso2 = this.zzdsl.zzdyv;
        zzakp zzakp = this.zzdsw;
        if (zzakp != null) {
            List zzui = zzakp.zzui();
            String str6 = "";
            if (zzui == null) {
                zzakr = zzakr4;
                zzaso = zzaso2;
                str3 = str6.toString();
                str2 = str4;
                z = z2;
                j = j7;
            } else {
                Iterator it2 = zzui.iterator();
                String str7 = str6;
                while (it2.hasNext()) {
                    zzaso zzaso3 = zzaso2;
                    zzakx zzakx5 = (zzakx) it2.next();
                    if (zzakx5 != null) {
                        it = it2;
                        if (zzakx5.zzdnb == null) {
                            zzakr2 = zzakr4;
                        } else if (!TextUtils.isEmpty(zzakx5.zzdnb.zzdkx)) {
                            String valueOf = String.valueOf(str7);
                            long j8 = j7;
                            String str8 = zzakx5.zzdnb.zzdkx;
                            switch (zzakx5.zzdna) {
                                case -1:
                                    i2 = 4;
                                    zzakr3 = zzakr4;
                                    break;
                                case 0:
                                    zzakr3 = zzakr4;
                                    i2 = 0;
                                    break;
                                case 1:
                                    zzakr3 = zzakr4;
                                    i2 = 1;
                                    break;
                                case 3:
                                    i2 = 2;
                                    zzakr3 = zzakr4;
                                    break;
                                case 4:
                                    i2 = 3;
                                    zzakr3 = zzakr4;
                                    break;
                                case 5:
                                    i2 = 5;
                                    zzakr3 = zzakr4;
                                    break;
                                default:
                                    i2 = 6;
                                    zzakr3 = zzakr4;
                                    break;
                            }
                            long j9 = zzakx5.zzdng;
                            boolean z4 = z2;
                            String str9 = str4;
                            StringBuilder sb = new StringBuilder(String.valueOf(str8).length() + 33);
                            sb.append(str8);
                            sb.append(".");
                            sb.append(i2);
                            sb.append(".");
                            sb.append(j9);
                            String sb2 = sb.toString();
                            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb2).length());
                            sb3.append(valueOf);
                            sb3.append(sb2);
                            sb3.append("_");
                            str7 = sb3.toString();
                            it2 = it;
                            zzaso2 = zzaso3;
                            j7 = j8;
                            zzakr4 = zzakr3;
                            z2 = z4;
                            str4 = str9;
                        } else {
                            zzakr2 = zzakr4;
                        }
                    } else {
                        zzakr2 = zzakr4;
                        it = it2;
                    }
                    it2 = it;
                    zzaso2 = zzaso3;
                    j7 = j7;
                    zzakr4 = zzakr2;
                    z2 = z2;
                    str4 = str4;
                }
                zzakr = zzakr4;
                zzaso = zzaso2;
                str2 = str4;
                z = z2;
                j = j7;
                str3 = str7.substring(0, Math.max(0, str7.length() - 1));
            }
        } else {
            zzakr = zzakr4;
            zzaso = zzaso2;
            str2 = str4;
            z = z2;
            j = j7;
            str3 = null;
        }
        zzakq zzakq3 = zzakq2;
        zzalj zzalj3 = zzalj2;
        zzakr zzakr6 = zzakr;
        long j10 = j4;
        zzwf zzwf3 = zzwf2;
        zzaxf zzaxf = new zzaxf(zzwb, zzbgg, list, i, list2, list3, i3, j2, str2, z, zzakq3, zzalj3, str, zzakr6, zzakt, j10, zzwf3, j5, j6, j, str5, jSONObject, null, zzawd, list4, list5, z3, zzaso, str3, this.zzdsl.zzdlu, this.zzdsl.zzdyy, this.zzdsk.zzehw, this.zzdsl.zzbph, this.zzdsk.zzehx, this.zzdsl.zzdzc, this.zzdsl.zzdls, this.zzdsl.zzbpi, this.zzdsl.zzdzd, this.zzdsl.zzdzf);
        return zzaxf;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.ads.zzakp] */
    /* JADX WARNING: type inference failed for: r17v0, types: [com.google.android.gms.internal.ads.zzald] */
    /* JADX WARNING: type inference failed for: r4v4, types: [com.google.android.gms.internal.ads.zzala] */
    /* JADX WARNING: type inference failed for: r17v2, types: [com.google.android.gms.internal.ads.zzald] */
    /* JADX WARNING: type inference failed for: r4v5, types: [com.google.android.gms.internal.ads.zzala] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r17v2, types: [com.google.android.gms.internal.ads.zzald]
  assigns: [com.google.android.gms.internal.ads.zzald, com.google.android.gms.internal.ads.zzala]
  uses: [com.google.android.gms.internal.ads.zzald, com.google.android.gms.internal.ads.zzakp, com.google.android.gms.internal.ads.zzala]
  mth insns count: 151
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0104  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzap(long r32) throws com.google.android.gms.internal.ads.zzapk {
        /*
            r31 = this;
            r1 = r31
            java.lang.Object r2 = r1.zzdsn
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzakr r0 = r1.zzdmn     // Catch:{ all -> 0x0174 }
            int r0 = r0.zzdma     // Catch:{ all -> 0x0174 }
            r3 = -1
            if (r0 == r3) goto L_0x0043
            com.google.android.gms.internal.ads.zzala r0 = new com.google.android.gms.internal.ads.zzala     // Catch:{ all -> 0x0174 }
            android.content.Context r5 = r1.mContext     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaxg r3 = r1.zzdsk     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasi r6 = r3.zzeag     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzalg r7 = r1.zzbma     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzakr r8 = r1.zzdmn     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasm r3 = r1.zzdsl     // Catch:{ all -> 0x0174 }
            boolean r9 = r3.zzckn     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasm r3 = r1.zzdsl     // Catch:{ all -> 0x0174 }
            boolean r10 = r3.zzckp     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasm r3 = r1.zzdsl     // Catch:{ all -> 0x0174 }
            java.lang.String r11 = r3.zzdyw     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r3 = com.google.android.gms.internal.ads.zzaan.zzctf     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaak r4 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0174 }
            java.lang.Object r3 = r4.zzd(r3)     // Catch:{ all -> 0x0174 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0174 }
            long r14 = r3.longValue()     // Catch:{ all -> 0x0174 }
            r16 = 2
            com.google.android.gms.internal.ads.zzaxg r3 = r1.zzdsk     // Catch:{ all -> 0x0174 }
            boolean r3 = r3.zzehx     // Catch:{ all -> 0x0174 }
            r4 = r0
            r12 = r32
            r17 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r14, r16, r17)     // Catch:{ all -> 0x0174 }
            goto L_0x008a
        L_0x0043:
            com.google.android.gms.internal.ads.zzald r0 = new com.google.android.gms.internal.ads.zzald     // Catch:{ all -> 0x0174 }
            android.content.Context r3 = r1.mContext     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaxg r4 = r1.zzdsk     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasi r4 = r4.zzeag     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzalg r5 = r1.zzbma     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzakr r6 = r1.zzdmn     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasm r7 = r1.zzdsl     // Catch:{ all -> 0x0174 }
            boolean r7 = r7.zzckn     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasm r8 = r1.zzdsl     // Catch:{ all -> 0x0174 }
            boolean r8 = r8.zzckp     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzasm r9 = r1.zzdsl     // Catch:{ all -> 0x0174 }
            java.lang.String r9 = r9.zzdyw     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r10 = com.google.android.gms.internal.ads.zzaan.zzctf     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaak r11 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0174 }
            java.lang.Object r10 = r11.zzd(r10)     // Catch:{ all -> 0x0174 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x0174 }
            long r27 = r10.longValue()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaba r10 = r1.zzbln     // Catch:{ all -> 0x0174 }
            com.google.android.gms.internal.ads.zzaxg r11 = r1.zzdsk     // Catch:{ all -> 0x0174 }
            boolean r11 = r11.zzehx     // Catch:{ all -> 0x0174 }
            r17 = r0
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r23 = r8
            r24 = r9
            r25 = r32
            r29 = r10
            r30 = r11
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r27, r29, r30)     // Catch:{ all -> 0x0174 }
        L_0x008a:
            r1.zzdsw = r0     // Catch:{ all -> 0x0174 }
            monitor-exit(r2)     // Catch:{ all -> 0x0174 }
            java.util.ArrayList r0 = new java.util.ArrayList
            com.google.android.gms.internal.ads.zzakr r2 = r1.zzdmn
            java.util.List<com.google.android.gms.internal.ads.zzakq> r2 = r2.zzdlp
            r0.<init>(r2)
            com.google.android.gms.internal.ads.zzaxg r2 = r1.zzdsk
            com.google.android.gms.internal.ads.zzasi r2 = r2.zzeag
            com.google.android.gms.internal.ads.zzwb r2 = r2.zzdwg
            android.os.Bundle r2 = r2.zzcjl
            java.lang.String r3 = "com.google.ads.mediation.admob.AdMobAdapter"
            r4 = 0
            if (r2 == 0) goto L_0x00b0
            android.os.Bundle r2 = r2.getBundle(r3)
            if (r2 == 0) goto L_0x00b0
            java.lang.String r5 = "_skipMediation"
            boolean r2 = r2.getBoolean(r5)
            goto L_0x00b1
        L_0x00b0:
            r2 = 0
        L_0x00b1:
            if (r2 == 0) goto L_0x00cf
            java.util.ListIterator r2 = r0.listIterator()
        L_0x00b7:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x00cf
            java.lang.Object r5 = r2.next()
            com.google.android.gms.internal.ads.zzakq r5 = (com.google.android.gms.internal.ads.zzakq) r5
            java.util.List<java.lang.String> r5 = r5.zzdkw
            boolean r5 = r5.contains(r3)
            if (r5 != 0) goto L_0x00b7
            r2.remove()
            goto L_0x00b7
        L_0x00cf:
            com.google.android.gms.internal.ads.zzakp r2 = r1.zzdsw
            com.google.android.gms.internal.ads.zzakx r0 = r2.zzh(r0)
            r1.zzdsx = r0
            com.google.android.gms.internal.ads.zzakx r0 = r1.zzdsx
            int r0 = r0.zzdna
            switch(r0) {
                case 0: goto L_0x0104;
                case 1: goto L_0x00fb;
                default: goto L_0x00de;
            }
        L_0x00de:
            com.google.android.gms.internal.ads.zzapk r0 = new com.google.android.gms.internal.ads.zzapk
            com.google.android.gms.internal.ads.zzakx r2 = r1.zzdsx
            int r2 = r2.zzdna
            r3 = 40
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Unexpected mediation result: "
            r5.append(r3)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r0.<init>(r2, r4)
            throw r0
        L_0x00fb:
            com.google.android.gms.internal.ads.zzapk r0 = new com.google.android.gms.internal.ads.zzapk
            r2 = 3
            java.lang.String r3 = "No fill from any mediation ad networks."
            r0.<init>(r3, r2)
            throw r0
        L_0x0104:
            com.google.android.gms.internal.ads.zzakx r0 = r1.zzdsx
            com.google.android.gms.internal.ads.zzakq r0 = r0.zzdnb
            if (r0 == 0) goto L_0x0173
            com.google.android.gms.internal.ads.zzakx r0 = r1.zzdsx
            com.google.android.gms.internal.ads.zzakq r0 = r0.zzdnb
            java.lang.String r0 = r0.zzdli
            if (r0 == 0) goto L_0x0173
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r2 = 1
            r0.<init>(r2)
            android.os.Handler r2 = com.google.android.gms.internal.ads.zzayh.zzelc
            com.google.android.gms.internal.ads.zzaps r3 = new com.google.android.gms.internal.ads.zzaps
            r3.<init>(r1, r0)
            r2.post(r3)
            r2 = 10
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014d }
            r0.await(r2, r5)     // Catch:{ InterruptedException -> 0x014d }
            java.lang.Object r2 = r1.zzdsn
            monitor-enter(r2)
            boolean r0 = r1.zzdsy     // Catch:{ all -> 0x014a }
            if (r0 == 0) goto L_0x0142
            com.google.android.gms.internal.ads.zzbgg r0 = r1.zzdin     // Catch:{ all -> 0x014a }
            boolean r0 = r0.isDestroyed()     // Catch:{ all -> 0x014a }
            if (r0 != 0) goto L_0x013a
            monitor-exit(r2)     // Catch:{ all -> 0x014a }
            return
        L_0x013a:
            com.google.android.gms.internal.ads.zzapk r0 = new com.google.android.gms.internal.ads.zzapk     // Catch:{ all -> 0x014a }
            java.lang.String r3 = "Assets not loaded, web view is destroyed"
            r0.<init>(r3, r4)     // Catch:{ all -> 0x014a }
            throw r0     // Catch:{ all -> 0x014a }
        L_0x0142:
            com.google.android.gms.internal.ads.zzapk r0 = new com.google.android.gms.internal.ads.zzapk     // Catch:{ all -> 0x014a }
            java.lang.String r3 = "View could not be prepared"
            r0.<init>(r3, r4)     // Catch:{ all -> 0x014a }
            throw r0     // Catch:{ all -> 0x014a }
        L_0x014a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x014a }
            throw r0
        L_0x014d:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzapk r2 = new com.google.android.gms.internal.ads.zzapk
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r3 = r3 + 38
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Interrupted while waiting for latch : "
            r5.append(r3)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.<init>(r0, r4)
            throw r2
        L_0x0173:
            return
        L_0x0174:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0174 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapr.zzap(long):void");
    }

    public final void onStop() {
        synchronized (this.zzdsn) {
            super.onStop();
            if (this.zzdsw != null) {
                this.zzdsw.cancel();
            }
        }
    }
}
