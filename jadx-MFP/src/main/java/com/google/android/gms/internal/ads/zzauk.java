package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzbw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;

@zzark
public final class zzauk {
    private static final zzalf zzedx = new zzalf();
    private final zzalg zzedy;
    private final zzbw zzedz;
    private final Map<String, zzavy> zzeea = new HashMap();
    private final zzavr zzeeb;
    private final zzb zzeec;
    private final zzapm zzeed;

    public zzauk(zzbw zzbw, zzalg zzalg, zzavr zzavr, zzb zzb, zzapm zzapm) {
        this.zzedz = zzbw;
        this.zzedy = zzalg;
        this.zzeeb = zzavr;
        this.zzeec = zzb;
        this.zzeed = zzapm;
    }

    public static boolean zza(zzaxf zzaxf, zzaxf zzaxf2) {
        return true;
    }

    public final zzb zzxb() {
        return this.zzeec;
    }

    public final zzapm zzxc() {
        return this.zzeed;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzavy zzdd(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, com.google.android.gms.internal.ads.zzavy> r0 = r4.zzeea
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.ads.zzavy r0 = (com.google.android.gms.internal.ads.zzavy) r0
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.ads.zzalg r1 = r4.zzedy     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = "com.google.ads.mediation.admob.AdMobAdapter"
            boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x002b }
            if (r2 == 0) goto L_0x0016
            com.google.android.gms.internal.ads.zzalf r1 = zzedx     // Catch:{ Exception -> 0x002b }
        L_0x0016:
            com.google.android.gms.internal.ads.zzavy r2 = new com.google.android.gms.internal.ads.zzavy     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.ads.zzalj r1 = r1.zzcp(r5)     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.ads.zzavr r3 = r4.zzeeb     // Catch:{ Exception -> 0x002b }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x002b }
            java.util.Map<java.lang.String, com.google.android.gms.internal.ads.zzavy> r0 = r4.zzeea     // Catch:{ Exception -> 0x0028 }
            r0.put(r5, r2)     // Catch:{ Exception -> 0x0028 }
            r0 = r2
            goto L_0x0045
        L_0x0028:
            r1 = move-exception
            r0 = r2
            goto L_0x002c
        L_0x002b:
            r1 = move-exception
        L_0x002c:
            java.lang.String r2 = "Fail to instantiate adapter "
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r3 = r5.length()
            if (r3 == 0) goto L_0x003d
            java.lang.String r5 = r2.concat(r5)
            goto L_0x0042
        L_0x003d:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r2)
        L_0x0042:
            com.google.android.gms.internal.ads.zzaxz.zzc(r5, r1)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzauk.zzdd(java.lang.String):com.google.android.gms.internal.ads.zzavy");
    }

    public final void zzxd() {
        zzbw zzbw = this.zzedz;
        zzbw.zzbtw = 0;
        zzbv.zzle();
        zzavu zzavu = new zzavu(this.zzedz.zzsp, this.zzedz.zzbsv, this);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzavu.getClass().getName());
        zzaxz.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzavu.zzwa();
        zzbw.zzbss = zzavu;
    }

    public final void zzah(boolean z) {
        zzavy zzdd = zzdd(this.zzedz.zzbsu.zzdnd);
        if (!(zzdd == null || zzdd.zzxn() == null)) {
            try {
                zzdd.zzxn().setImmersiveMode(z);
                zzdd.zzxn().showVideo();
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (String str : this.zzeea.keySet()) {
            try {
                zzavy zzavy = (zzavy) this.zzeea.get(str);
                if (!(zzavy == null || zzavy.zzxn() == null)) {
                    zzavy.zzxn().pause();
                }
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (String str : this.zzeea.keySet()) {
            try {
                zzavy zzavy = (zzavy) this.zzeea.get(str);
                if (!(zzavy == null || zzavy.zzxn() == null)) {
                    zzavy.zzxn().resume();
                }
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (String str : this.zzeea.keySet()) {
            try {
                zzavy zzavy = (zzavy) this.zzeea.get(str);
                if (!(zzavy == null || zzavy.zzxn() == null)) {
                    zzavy.zzxn().destroy();
                }
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onContextChanged(@NonNull Context context) {
        for (zzavy zzxn : this.zzeea.values()) {
            try {
                zzxn.zzxn().zzj(ObjectWrapper.wrap(context));
            } catch (RemoteException e) {
                zzaxz.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void zzxe() {
        if (this.zzedz.zzbsu != null && this.zzedz.zzbsu.zzdnb != null) {
            zzbv.zzlz();
            zzakz.zza(this.zzedz.zzsp, this.zzedz.zzbsp.zzdp, this.zzedz.zzbsu, this.zzedz.zzbsn, false, this.zzedz.zzbsu.zzdnb.zzdlf);
        }
    }

    public final void zzxf() {
        if (this.zzedz.zzbsu != null && this.zzedz.zzbsu.zzdnb != null) {
            zzbv.zzlz();
            zzakz.zza(this.zzedz.zzsp, this.zzedz.zzbsp.zzdp, this.zzedz.zzbsu, this.zzedz.zzbsn, false, this.zzedz.zzbsu.zzdnb.zzdlh);
        }
    }

    public final zzawd zzd(zzawd zzawd) {
        if (!(this.zzedz.zzbsu == null || this.zzedz.zzbsu.zzehj == null || TextUtils.isEmpty(this.zzedz.zzbsu.zzehj.zzdly))) {
            zzawd = new zzawd(this.zzedz.zzbsu.zzehj.zzdly, this.zzedz.zzbsu.zzehj.zzdlz);
        }
        if (!(this.zzedz.zzbsu == null || this.zzedz.zzbsu.zzdnb == null)) {
            zzbv.zzlz();
            zzakz.zza(this.zzedz.zzsp, this.zzedz.zzbsp.zzdp, this.zzedz.zzbsu.zzdnb.zzdlg, this.zzedz.zzbtr, this.zzedz.zzbts, zzawd);
        }
        return zzawd;
    }
}
