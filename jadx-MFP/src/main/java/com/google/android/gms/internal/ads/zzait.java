package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzait {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock;
    private final zzbbi zzbob;
    private final String zzdiv;
    /* access modifiers changed from: private */
    public zzazn<zzaii> zzdiw;
    private zzazn<zzaii> zzdix;
    /* access modifiers changed from: private */
    @Nullable
    public zzajm zzdiy;
    /* access modifiers changed from: private */
    public int zzdiz;

    public zzait(Context context, zzbbi zzbbi, String str) {
        this.mLock = new Object();
        this.zzdiz = 1;
        this.zzdiv = str;
        this.mContext = context.getApplicationContext();
        this.zzbob = zzbbi;
        this.zzdiw = new zzajh();
        this.zzdix = new zzajh();
    }

    public zzait(Context context, zzbbi zzbbi, String str, zzazn<zzaii> zzazn, zzazn<zzaii> zzazn2) {
        this(context, zzbbi, str);
        this.zzdiw = zzazn;
        this.zzdix = zzazn2;
    }

    /* access modifiers changed from: protected */
    public final zzajm zza(@Nullable zzcu zzcu) {
        zzajm zzajm = new zzajm(this.zzdix);
        zzbcg.zzepo.execute(new zzaiu(this, zzcu, zzajm));
        zzajm.zza(new zzaje(this, zzajm), new zzajf(this, zzajm));
        return zzajm;
    }

    public final zzaji zzb(@Nullable zzcu zzcu) {
        synchronized (this.mLock) {
            synchronized (this.mLock) {
                if (this.zzdiy != null && this.zzdiz == 0) {
                    if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcob)).booleanValue()) {
                        this.zzdiy.zza(new zzaiv(this), zzaiw.zzdjd);
                    }
                }
            }
            if (this.zzdiy != null) {
                if (this.zzdiy.getStatus() != -1) {
                    if (this.zzdiz == 0) {
                        zzaji zzud = this.zzdiy.zzud();
                        return zzud;
                    } else if (this.zzdiz == 1) {
                        this.zzdiz = 2;
                        zza((zzcu) null);
                        zzaji zzud2 = this.zzdiy.zzud();
                        return zzud2;
                    } else if (this.zzdiz == 2) {
                        zzaji zzud3 = this.zzdiy.zzud();
                        return zzud3;
                    } else {
                        zzaji zzud4 = this.zzdiy.zzud();
                        return zzud4;
                    }
                }
            }
            this.zzdiz = 2;
            this.zzdiy = zza((zzcu) null);
            zzaji zzud5 = this.zzdiy.zzud();
            return zzud5;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(zzaii zzaii) {
        if (zzaii.isDestroyed()) {
            this.zzdiz = 1;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(zzcu zzcu, zzajm zzajm) {
        zzaii zzaii;
        try {
            Context context = this.mContext;
            zzbbi zzbbi = this.zzbob;
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcrd)).booleanValue()) {
                zzaii = new zzahv(context, zzbbi);
            } else {
                zzaii = new zzaik(context, zzbbi, zzcu, null);
            }
            zzaii.zza(new zzaix(this, zzajm, zzaii));
            zzaii.zza("/jsLoaded", new zzaja(this, zzajm, zzaii));
            zzbaj zzbaj = new zzbaj();
            zzajb zzajb = new zzajb(this, zzcu, zzaii, zzbaj);
            zzbaj.set(zzajb);
            zzaii.zza("/requestReload", zzajb);
            if (this.zzdiv.endsWith(".js")) {
                zzaii.zzcd(this.zzdiv);
            } else if (this.zzdiv.startsWith("<html>")) {
                zzaii.zzce(this.zzdiv);
            } else {
                zzaii.zzcf(this.zzdiv);
            }
            zzayh.zzelc.postDelayed(new zzajc(this, zzajm, zzaii), (long) zzajg.zzdjn);
        } catch (Throwable th) {
            zzaxz.zzb("Error creating webview.", th);
            zzbv.zzlj().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzajm.reject();
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzajm r4, com.google.android.gms.internal.ads.zzaii r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            int r1 = r4.getStatus()     // Catch:{ all -> 0x002a }
            r2 = -1
            if (r1 == r2) goto L_0x0028
            int r1 = r4.getStatus()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r1 != r2) goto L_0x0012
            goto L_0x0028
        L_0x0012:
            r4.reject()     // Catch:{ all -> 0x002a }
            java.util.concurrent.Executor r4 = com.google.android.gms.internal.ads.zzbcg.zzepo     // Catch:{ all -> 0x002a }
            r5.getClass()     // Catch:{ all -> 0x002a }
            java.lang.Runnable r5 = com.google.android.gms.internal.ads.zzaiz.zzb(r5)     // Catch:{ all -> 0x002a }
            r4.execute(r5)     // Catch:{ all -> 0x002a }
            java.lang.String r4 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.ads.zzaxz.v(r4)     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzait.zza(com.google.android.gms.internal.ads.zzajm, com.google.android.gms.internal.ads.zzaii):void");
    }
}
