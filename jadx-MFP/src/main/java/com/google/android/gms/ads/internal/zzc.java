package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzadx;
import com.google.android.gms.internal.ads.zzaeh;
import com.google.android.gms.internal.ads.zzaks;
import com.google.android.gms.internal.ads.zzakz;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzarn;
import com.google.android.gms.internal.ads.zzasj;
import com.google.android.gms.internal.ads.zzatd;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxj;
import com.google.android.gms.internal.ads.zzaxv;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbcg;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzsx;
import com.google.android.gms.internal.ads.zzuo.zza.zzb;
import com.google.android.gms.internal.ads.zzur;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public abstract class zzc extends zza implements zzn, zzbo, zzaks {
    protected final zzalg zzbma;
    private transient boolean zzbmb;

    public zzc(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        this(new zzbw(context, zzwf, str, zzbbi), zzalg, null, zzv);
    }

    @VisibleForTesting
    private zzc(zzbw zzbw, zzalg zzalg, @Nullable zzbl zzbl, zzv zzv) {
        super(zzbw, null, zzv);
        this.zzbma = zzalg;
        this.zzbmb = false;
    }

    public final boolean zza(zzwb zzwb, zzaba zzaba, int i) {
        Bundle bundle;
        zzaxj zzaxj;
        if (!zziu()) {
            return false;
        }
        zzbv.zzlf();
        zzsx zzzo = zzbv.zzlj().zzyq().zzzo();
        String str = null;
        if (zzzo == null) {
            bundle = null;
        } else {
            bundle = zzayh.zza(zzzo);
        }
        this.zzblr.cancel();
        this.zzbls.zzbtw = 0;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuz)).booleanValue()) {
            zzaxj = zzbv.zzlj().zzyq().zzzi();
            zzad zzln = zzbv.zzln();
            Context context = this.zzbls.zzsp;
            zzbbi zzbbi = this.zzbls.zzbsp;
            String str2 = this.zzbls.zzbsn;
            if (zzaxj != null) {
                str = zzaxj.zzyf();
            }
            zzln.zza(context, zzbbi, false, zzaxj, str, str2, null);
        } else {
            zzaxj = null;
        }
        return zza(zza(zzwb, bundle, zzaxj, i), zzaba);
    }

    public final boolean zza(zzasj zzasj, zzaba zzaba) {
        zzaxv zzaxv;
        this.zzbln = zzaba;
        zzaba.zzg("seq_num", zzasj.zzdwj);
        zzaba.zzg("request_id", zzasj.zzdws);
        zzaba.zzg(Attributes.SESSION_ID, zzasj.zzclm);
        if (zzasj.zzdwh != null) {
            zzaba.zzg("app_version", String.valueOf(zzasj.zzdwh.versionCode));
        }
        zzbw zzbw = this.zzbls;
        zzbv.zzlb();
        Context context = this.zzbls.zzsp;
        zzur zzur = this.zzbly.zzbmv;
        if (zzasj.zzdwg.extras.getBundle("sdk_less_server_data") != null) {
            zzaxv = new zzatd(context, zzasj, this, zzur);
        } else {
            zzaxv = new zzarn(context, zzasj, this, zzur);
        }
        zzaxv.zzyz();
        zzbw.zzbsr = zzaxv;
        return true;
    }

    public boolean zza(zzwb zzwb, zzaba zzaba) {
        return zza(zzwb, zzaba, 1);
    }

    public final void zzb(zzaxf zzaxf) {
        super.zzb(zzaxf);
        if (zzaxf.zzdnb != null) {
            zzaxz.zzdn("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzbls.zzbsq != null) {
                this.zzbls.zzbsq.zzmp();
            }
            zzaxz.zzdn("Pinging network fill URLs.");
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, false, zzaxf.zzdnb.zzdld);
            if (!(zzaxf.zzehj == null || zzaxf.zzehj.zzdlu == null || zzaxf.zzehj.zzdlu.size() <= 0)) {
                zzaxz.zzdn("Pinging urls remotely");
                zzbv.zzlf().zza(this.zzbls.zzsp, zzaxf.zzehj.zzdlu);
            }
        } else {
            zzaxz.zzdn("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzbls.zzbsq != null) {
                this.zzbls.zzbsq.zzmo();
            }
        }
        if (zzaxf.errorCode == 3 && zzaxf.zzehj != null && zzaxf.zzehj.zzdlt != null) {
            zzaxz.zzdn("Pinging no fill URLs.");
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, false, zzaxf.zzehj.zzdlt);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        int i;
        if (!(zzaxf == null || zzaxf.zzdne == null)) {
            zzaxf.zzdne.zza((zzaks) null);
        }
        if (zzaxf2.zzdne != null) {
            zzaxf2.zzdne.zza((zzaks) this);
        }
        int i2 = 0;
        if (zzaxf2.zzehj != null) {
            i2 = zzaxf2.zzehj.zzdmg;
            i = zzaxf2.zzehj.zzdmh;
        } else {
            i = 0;
        }
        this.zzbls.zzbtu.zzl(i2, i);
        return true;
    }

    public void onAdClicked() {
        if (this.zzbls.zzbsu == null) {
            zzaxz.zzeo("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzbls.zzbsu.zzehj == null || this.zzbls.zzbsu.zzehj.zzdlq == null)) {
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu, this.zzbls.zzbsn, false, zza(this.zzbls.zzbsu.zzehj.zzdlq, this.zzbls.zzbsu.zzdzf));
        }
        if (!(this.zzbls.zzbsu.zzdnb == null || this.zzbls.zzbsu.zzdnb.zzdkz == null)) {
            zzbv.zzlz();
            zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, this.zzbls.zzbsu, this.zzbls.zzbsn, false, this.zzbls.zzbsu.zzdnb.zzdkz);
        }
        super.onAdClicked();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzaxf zzaxf) {
        zzwb zzwb;
        boolean z = false;
        if (this.zzblt != null) {
            zzwb = this.zzblt;
            this.zzblt = null;
        } else {
            zzwb = zzaxf.zzdwg;
            if (zzwb.extras != null) {
                z = zzwb.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzwb, zzaxf, z);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzwb zzwb, zzaxf zzaxf, boolean z) {
        if (!z && this.zzbls.zzmj()) {
            if (zzaxf.zzdlx > 0) {
                this.zzblr.zza(zzwb, zzaxf.zzdlx);
            } else if (zzaxf.zzehj != null && zzaxf.zzehj.zzdlx > 0) {
                this.zzblr.zza(zzwb, zzaxf.zzehj.zzdlx);
            } else if (!zzaxf.zzdyd && zzaxf.errorCode == 2) {
                this.zzblr.zzg(zzwb);
            }
        }
        return this.zzblr.zzkv();
    }

    public void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null || !this.zzbls.zzmj())) {
            zzbv.zzlh();
            zzayp.zzi(this.zzbls.zzbsu.zzdrv);
        }
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdnc == null)) {
            try {
                this.zzbls.zzbsu.zzdnc.pause();
            } catch (RemoteException unused) {
                zzaxz.zzeo("Could not pause mediation adapter.");
            }
        }
        this.zzblu.zzj(this.zzbls.zzbsu);
        this.zzblr.pause();
    }

    public void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        zzbgg zzbgg = (this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdrv == null) ? null : this.zzbls.zzbsu.zzdrv;
        if (zzbgg != null && this.zzbls.zzmj()) {
            zzbv.zzlh();
            zzayp.zzj(this.zzbls.zzbsu.zzdrv);
        }
        if (!(this.zzbls.zzbsu == null || this.zzbls.zzbsu.zzdnc == null)) {
            try {
                this.zzbls.zzbsu.zzdnc.resume();
            } catch (RemoteException unused) {
                zzaxz.zzeo("Could not resume mediation adapter.");
            }
        }
        if (zzbgg == null || !zzbgg.zzadt()) {
            this.zzblr.resume();
        }
        this.zzblu.zzk(this.zzbls.zzbsu);
    }

    /* access modifiers changed from: protected */
    public final boolean zzc(zzwb zzwb) {
        return super.zzc(zzwb) && !this.zzbmb;
    }

    /* access modifiers changed from: protected */
    public boolean zziu() {
        zzbv.zzlf();
        if (zzayh.zzn(this.zzbls.zzsp, "android.permission.INTERNET")) {
            zzbv.zzlf();
            if (zzayh.zzah(this.zzbls.zzsp)) {
                return true;
            }
        }
        return false;
    }

    public void zziv() {
        this.zzbmb = false;
        zzii();
        this.zzbls.zzbsw.zzxx();
    }

    public void zziw() {
        this.zzbmb = true;
        zzik();
    }

    public final void onPause() {
        this.zzblu.zzj(this.zzbls.zzbsu);
    }

    public final void onResume() {
        this.zzblu.zzk(this.zzbls.zzbsu);
    }

    public void zzix() {
        zzaxz.zzeo("Mediated ad does not support onVideoEnd callback");
    }

    public void zziy() {
        onAdClicked();
    }

    public final void zziz() {
        zziv();
    }

    public final void zzja() {
        zzij();
    }

    public final void zzjb() {
        zziw();
    }

    public final void zzjc() {
        if (this.zzbls.zzbsu != null) {
            String str = this.zzbls.zzbsu.zzdnd;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 74);
            sb.append("Mediation adapter ");
            sb.append(str);
            sb.append(" refreshed, but mediation adapters should never refresh.");
            zzaxz.zzeo(sb.toString());
        }
        zza(this.zzbls.zzbsu, true);
        zzb(this.zzbls.zzbsu, true);
        zzil();
    }

    public void zzjd() {
        recordImpression();
    }

    public final void zzd(String str, String str2) {
        onAppEvent(str, str2);
    }

    public final void zza(zzadx zzadx, String str) {
        Object obj;
        zzaeh zzaeh = null;
        if (zzadx != null) {
            try {
                obj = zzadx.getCustomTemplateId();
            } catch (RemoteException e) {
                zzaxz.zzc("Unable to call onCustomClick.", e);
                return;
            }
        } else {
            obj = null;
        }
        if (!(this.zzbls.zzbtg == null || obj == null)) {
            zzaeh = (zzaeh) this.zzbls.zzbtg.get(obj);
        }
        if (zzaeh == null) {
            zzaxz.zzeo("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            zzaeh.zzb(zzadx, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x018d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzasj zza(com.google.android.gms.internal.ads.zzwb r59, android.os.Bundle r60, com.google.android.gms.internal.ads.zzaxj r61, int r62) {
        /*
            r58 = this;
            r0 = r58
            com.google.android.gms.ads.internal.zzbw r1 = r0.zzbls
            android.content.Context r1 = r1.zzsp
            android.content.pm.ApplicationInfo r7 = r1.getApplicationInfo()
            r1 = 0
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls     // Catch:{ NameNotFoundException -> 0x001b }
            android.content.Context r2 = r2.zzsp     // Catch:{ NameNotFoundException -> 0x001b }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r2 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r2)     // Catch:{ NameNotFoundException -> 0x001b }
            java.lang.String r4 = r7.packageName     // Catch:{ NameNotFoundException -> 0x001b }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r4, r1)     // Catch:{ NameNotFoundException -> 0x001b }
            r8 = r2
            goto L_0x001c
        L_0x001b:
            r8 = 0
        L_0x001c:
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            android.content.Context r2 = r2.zzsp
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r4 = r2.getDisplayMetrics()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r2 = r2.zzbsq
            if (r2 == 0) goto L_0x0094
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r2 = r2.zzbsq
            android.view.ViewParent r2 = r2.getParent()
            if (r2 == 0) goto L_0x0094
            r2 = 2
            int[] r2 = new int[r2]
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzbsq
            r5.getLocationOnScreen(r2)
            r5 = r2[r1]
            r6 = 1
            r2 = r2[r6]
            com.google.android.gms.ads.internal.zzbw r9 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r9 = r9.zzbsq
            int r9 = r9.getWidth()
            com.google.android.gms.ads.internal.zzbw r10 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r10 = r10.zzbsq
            int r10 = r10.getHeight()
            com.google.android.gms.ads.internal.zzbw r11 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r11 = r11.zzbsq
            boolean r11 = r11.isShown()
            if (r11 == 0) goto L_0x0072
            int r11 = r5 + r9
            if (r11 <= 0) goto L_0x0072
            int r11 = r2 + r10
            if (r11 <= 0) goto L_0x0072
            int r11 = r4.widthPixels
            if (r5 > r11) goto L_0x0072
            int r11 = r4.heightPixels
            if (r2 > r11) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r6 = 0
        L_0x0073:
            android.os.Bundle r11 = new android.os.Bundle
            r12 = 5
            r11.<init>(r12)
            java.lang.String r12 = "x"
            r11.putInt(r12, r5)
            java.lang.String r5 = "y"
            r11.putInt(r5, r2)
            java.lang.String r2 = "width"
            r11.putInt(r2, r9)
            java.lang.String r2 = "height"
            r11.putInt(r2, r10)
            java.lang.String r2 = "visible"
            r11.putInt(r2, r6)
            r12 = r11
            goto L_0x0095
        L_0x0094:
            r12 = 0
        L_0x0095:
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            com.google.android.gms.internal.ads.zzaxk r5 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzaxt r5 = r5.zzys()
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzbv.zzlm()
            com.google.android.gms.ads.internal.zzbw r9 = r0.zzbls
            java.lang.String r9 = r9.zzbsn
            com.google.android.gms.internal.ads.zzaxh r5 = r5.zza(r6, r9)
            r2.zzbsw = r5
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            com.google.android.gms.internal.ads.zzaxh r2 = r2.zzbsw
            r14 = r59
            r2.zzn(r14)
            com.google.android.gms.ads.internal.zzbv.zzlf()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            android.content.Context r2 = r2.zzsp
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzbsq
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzbls
            com.google.android.gms.internal.ads.zzwf r6 = r6.zzbst
            java.lang.String r20 = com.google.android.gms.internal.ads.zzayh.zza(r2, r5, r6)
            r5 = 0
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            com.google.android.gms.internal.ads.zzxz r2 = r2.zzbtb
            if (r2 == 0) goto L_0x00e1
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls     // Catch:{ RemoteException -> 0x00dc }
            com.google.android.gms.internal.ads.zzxz r2 = r2.zzbtb     // Catch:{ RemoteException -> 0x00dc }
            long r5 = r2.getValue()     // Catch:{ RemoteException -> 0x00dc }
            r21 = r5
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r2 = "Cannot get correlation id, default to 0."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r2)
        L_0x00e1:
            r21 = r5
        L_0x00e3:
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r23 = r2.toString()
            com.google.android.gms.internal.ads.zzaxk r2 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzaxt r2 = r2.zzys()
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzbls
            android.content.Context r5 = r5.zzsp
            android.os.Bundle r15 = r2.zza(r5, r0)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r2 = 0
        L_0x0106:
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzbls
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzaek> r5 = r5.zzbth
            int r5 = r5.size()
            if (r2 >= r5) goto L_0x0137
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzbls
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzaek> r5 = r5.zzbth
            java.lang.Object r5 = r5.keyAt(r2)
            java.lang.String r5 = (java.lang.String) r5
            r13.add(r5)
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzbls
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzaeh> r6 = r6.zzbtg
            boolean r6 = r6.containsKey(r5)
            if (r6 == 0) goto L_0x0134
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzbls
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzaeh> r6 = r6.zzbtg
            java.lang.Object r6 = r6.get(r5)
            if (r6 == 0) goto L_0x0134
            r11.add(r5)
        L_0x0134:
            int r2 = r2 + 1
            goto L_0x0106
        L_0x0137:
            com.google.android.gms.ads.internal.zzf r2 = new com.google.android.gms.ads.internal.zzf
            r2.<init>(r0)
            com.google.android.gms.internal.ads.zzbcb r34 = com.google.android.gms.internal.ads.zzayf.zza(r2)
            com.google.android.gms.ads.internal.zzg r2 = new com.google.android.gms.ads.internal.zzg
            r2.<init>(r0)
            com.google.android.gms.internal.ads.zzbcb r44 = com.google.android.gms.internal.ads.zzayf.zza(r2)
            if (r61 == 0) goto L_0x0152
            java.lang.String r2 = r61.zzye()
            r35 = r2
            goto L_0x0154
        L_0x0152:
            r35 = 0
        L_0x0154:
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            java.util.List<java.lang.String> r2 = r2.zzbtt
            if (r2 == 0) goto L_0x01ac
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            java.util.List<java.lang.String> r2 = r2.zzbtt
            int r2 = r2.size()
            if (r2 <= 0) goto L_0x01ac
            if (r8 == 0) goto L_0x0168
            int r1 = r8.versionCode
        L_0x0168:
            com.google.android.gms.internal.ads.zzaxk r2 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r2 = r2.zzyq()
            int r2 = r2.zzzh()
            if (r1 <= r2) goto L_0x018d
            com.google.android.gms.internal.ads.zzaxk r2 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r2 = r2.zzyq()
            r2.zzzn()
            com.google.android.gms.internal.ads.zzaxk r2 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r2 = r2.zzyq()
            r2.zzcv(r1)
            goto L_0x01ac
        L_0x018d:
            com.google.android.gms.internal.ads.zzaxk r1 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r1 = r1.zzyq()
            org.json.JSONObject r1 = r1.zzzm()
            if (r1 == 0) goto L_0x01ac
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            java.lang.String r2 = r2.zzbsn
            org.json.JSONArray r1 = r1.optJSONArray(r2)
            if (r1 == 0) goto L_0x01ac
            java.lang.String r1 = r1.toString()
            r46 = r1
            goto L_0x01ae
        L_0x01ac:
            r46 = 0
        L_0x01ae:
            com.google.android.gms.internal.ads.zzasj r1 = new com.google.android.gms.internal.ads.zzasj
            r2 = r1
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzbls
            com.google.android.gms.internal.ads.zzwf r5 = r5.zzbst
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzbls
            java.lang.String r6 = r6.zzbsn
            com.google.android.gms.ads.internal.zzbw r9 = r0.zzbls
            com.google.android.gms.internal.ads.zzaxh r9 = r9.zzbsw
            java.lang.String r9 = r9.zzxy()
            java.lang.String r10 = com.google.android.gms.internal.ads.zzwu.zzqa()
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            com.google.android.gms.internal.ads.zzbbi r3 = r3.zzbsp
            r48 = r11
            r11 = r3
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            java.util.List<java.lang.String> r3 = r3.zzbtt
            r56 = r13
            r13 = r3
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r3 = r3.zzyq()
            boolean r16 = r3.zzzb()
            int r3 = r4.widthPixels
            r17 = r3
            int r3 = r4.heightPixels
            r18 = r3
            float r3 = r4.density
            r19 = r3
            java.util.List r24 = com.google.android.gms.internal.ads.zzaan.zzqw()
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            java.lang.String r3 = r3.zzbsm
            r25 = r3
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            com.google.android.gms.internal.ads.zzacp r3 = r3.zzbti
            r26 = r3
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            java.lang.String r27 = r3.zzml()
            com.google.android.gms.internal.ads.zzaza r3 = com.google.android.gms.ads.internal.zzbv.zzlk()
            float r28 = r3.zzkj()
            com.google.android.gms.internal.ads.zzaza r3 = com.google.android.gms.ads.internal.zzbv.zzlk()
            boolean r29 = r3.zzkk()
            com.google.android.gms.ads.internal.zzbv.zzlf()
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            android.content.Context r3 = r3.zzsp
            int r30 = com.google.android.gms.internal.ads.zzayh.zzas(r3)
            com.google.android.gms.ads.internal.zzbv.zzlf()
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            com.google.android.gms.ads.internal.zzbx r3 = r3.zzbsq
            int r31 = com.google.android.gms.internal.ads.zzayh.zzy(r3)
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            android.content.Context r3 = r3.zzsp
            boolean r3 = r3 instanceof android.app.Activity
            r32 = r3
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r3 = r3.zzyq()
            boolean r33 = r3.zzzg()
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            boolean r36 = r3.zzyj()
            com.google.android.gms.internal.ads.zzbfj r3 = com.google.android.gms.ads.internal.zzbv.zzmd()
            int r37 = r3.zzada()
            com.google.android.gms.ads.internal.zzbv.zzlf()
            android.os.Bundle r38 = com.google.android.gms.internal.ads.zzayh.zzzv()
            com.google.android.gms.internal.ads.zzazj r3 = com.google.android.gms.ads.internal.zzbv.zzlp()
            java.lang.String r39 = r3.zzaag()
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            com.google.android.gms.internal.ads.zzyv r3 = r3.zzbtl
            r40 = r3
            com.google.android.gms.internal.ads.zzazj r3 = com.google.android.gms.ads.internal.zzbv.zzlp()
            boolean r41 = r3.zzaah()
            com.google.android.gms.internal.ads.zzahq r3 = com.google.android.gms.internal.ads.zzahq.zzto()
            android.os.Bundle r42 = r3.zztx()
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzayb r3 = r3.zzyq()
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzbls
            java.lang.String r4 = r4.zzbsn
            boolean r43 = r3.zzdu(r4)
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            java.util.List<java.lang.Integer> r3 = r3.zzbtn
            r45 = r3
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzbls
            android.content.Context r3 = r3.zzsp
            com.google.android.gms.common.wrappers.PackageManagerWrapper r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r3)
            boolean r49 = r3.isCallerInstantApp()
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            boolean r50 = r3.zzyk()
            com.google.android.gms.ads.internal.zzbv.zzlh()
            boolean r51 = com.google.android.gms.internal.ads.zzayp.zzaaa()
            com.google.android.gms.internal.ads.zzaxk r3 = com.google.android.gms.ads.internal.zzbv.zzlj()
            com.google.android.gms.internal.ads.zzbcb r3 = r3.zzyr()
            r61 = r1
            r57 = r2
            r1 = 1000(0x3e8, double:4.94E-321)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
            r14 = 0
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzbbq.zza(r3, r14, r1, r4)
            r52 = r1
            java.util.ArrayList r52 = (java.util.ArrayList) r52
            com.google.android.gms.internal.ads.zzayh r1 = com.google.android.gms.ads.internal.zzbv.zzlf()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            android.content.Context r2 = r2.zzsp
            java.lang.String r53 = r1.zzaw(r2)
            com.google.android.gms.ads.internal.zzbw r1 = r0.zzbls
            com.google.android.gms.internal.ads.zzafz r1 = r1.zzbtk
            r54 = r1
            com.google.android.gms.internal.ads.zzayh r1 = com.google.android.gms.ads.internal.zzbv.zzlf()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzbls
            android.content.Context r2 = r2.zzsp
            android.os.Bundle r55 = r1.zzax(r2)
            r3 = r12
            r4 = r59
            r12 = r15
            r14 = r56
            r15 = r60
            r47 = r48
            r48 = r62
            r2 = r57
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55)
            return r61
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzc.zza(com.google.android.gms.internal.ads.zzwb, android.os.Bundle, com.google.android.gms.internal.ads.zzaxj, int):com.google.android.gms.internal.ads.zzasj");
    }

    public final void recordImpression() {
        zza(this.zzbls.zzbsu, false);
    }

    /* access modifiers changed from: protected */
    public void zza(@Nullable zzaxf zzaxf, boolean z) {
        if (zzaxf == null) {
            zzaxz.zzeo("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (zzaxf == null) {
            zzaxz.zzeo("Ad state was null when trying to ping impression URLs.");
        } else {
            zzaxz.zzdn("Pinging Impression URLs.");
            if (this.zzbls.zzbsw != null) {
                this.zzbls.zzbsw.zzxv();
            }
            zzaxf.zzehw.zza(zzb.AD_IMPRESSION);
            if (zzaxf.zzdlr != null && !zzaxf.zzehq) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zza(zzaxf.zzdlr, zzaxf.zzdzf));
                zzaxf.zzehq = true;
            }
        }
        if (!zzaxf.zzehs || z) {
            if (!(zzaxf.zzehj == null || zzaxf.zzehj.zzdlr == null)) {
                zzbv.zzlz();
                zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zza(zzaxf.zzehj.zzdlr, zzaxf.zzdzf));
            }
            if (!(zzaxf.zzdnb == null || zzaxf.zzdnb.zzdla == null)) {
                zzbv.zzlz();
                zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zzaxf.zzdnb.zzdla);
            }
            zzaxf.zzehs = true;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(@Nullable zzaxf zzaxf, boolean z) {
        if (zzaxf != null) {
            if (!(zzaxf == null || zzaxf.zzdls == null || zzaxf.zzehr)) {
                zzbv.zzlf();
                zzayh.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzc(zzaxf.zzdls));
                zzaxf.zzehr = true;
            }
            if (!zzaxf.zzeht || z) {
                if (!(zzaxf.zzehj == null || zzaxf.zzehj.zzdls == null)) {
                    zzbv.zzlz();
                    zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zzc(zzaxf.zzehj.zzdls));
                }
                if (!(zzaxf.zzdnb == null || zzaxf.zzdnb.zzdlb == null)) {
                    zzbv.zzlz();
                    zzakz.zza(this.zzbls.zzsp, this.zzbls.zzbsp.zzdp, zzaxf, this.zzbls.zzbsn, z, zzaxf.zzdnb.zzdlb);
                }
                zzaxf.zzeht = true;
            }
        }
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        if (this.zzbls.zzbsu == null) {
            return null;
        }
        return this.zzbls.zzbsu.zzdnd;
    }

    @Nullable
    public final String zzje() {
        if (this.zzbls.zzbsu == null) {
            return null;
        }
        return zzc(this.zzbls.zzbsu);
    }

    @Nullable
    static String zzc(zzaxf zzaxf) {
        if (zzaxf == null) {
            return null;
        }
        String str = zzaxf.zzdnd;
        if (("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) && zzaxf.zzdnb != null) {
            try {
                return new JSONObject(zzaxf.zzdnb.zzdle).getString("class_name");
            } catch (NullPointerException | JSONException unused) {
            }
        }
        return str;
    }

    public void showInterstitial() {
        zzaxz.zzeo("showInterstitial is not supported for current ad type");
    }

    public final void zzjf() {
        Executor executor = zzbcg.zzepo;
        zzbl zzbl = this.zzblr;
        zzbl.getClass();
        executor.execute(zzd.zza(zzbl));
    }

    public final void zzjg() {
        Executor executor = zzbcg.zzepo;
        zzbl zzbl = this.zzblr;
        zzbl.getClass();
        executor.execute(zze.zza(zzbl));
    }
}
