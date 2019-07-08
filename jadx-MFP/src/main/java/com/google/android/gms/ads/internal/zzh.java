package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzabg;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzaol;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgm;
import com.google.android.gms.internal.ads.zzbgq;
import com.google.android.gms.internal.ads.zzbht;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public abstract class zzh extends zzc implements zzaf, zzaol {
    private boolean zzbme;

    public zzh(Context context, zzwf zzwf, String str, zzalg zzalg, zzbbi zzbbi, zzv zzv) {
        super(context, zzwf, str, zzalg, zzbbi, zzv);
    }

    /* access modifiers changed from: protected */
    public zzbgg zza(zzaxg zzaxg, @Nullable zzw zzw, @Nullable zzawr zzawr) throws zzbgq {
        zzaxg zzaxg2 = zzaxg;
        View nextView = this.zzbls.zzbsq.getNextView();
        if (nextView instanceof zzbgg) {
            ((zzbgg) nextView).destroy();
        }
        if (nextView != null) {
            this.zzbls.zzbsq.removeView(nextView);
        }
        zzbv.zzlg();
        zzbgg zza = zzbgm.zza(this.zzbls.zzsp, zzbht.zzb(this.zzbls.zzbst), this.zzbls.zzbst.zzckk, false, false, this.zzbls.zzbso, this.zzbls.zzbsp, this.zzbln, this, this.zzbly, zzaxg2.zzehw);
        if (this.zzbls.zzbst.zzckm == null) {
            zzg(zza.getView());
        }
        zza.zzadl().zza(this, this, this, this, this, false, null, zzw, this, zzawr);
        zza(zza);
        zza.zzfb(zzaxg2.zzeag.zzdws);
        return zza;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbgg zzbgg) {
        zzbgg.zza("/trackActiveViewUnit", (zzu<? super zzbgg>) new zzi<Object>(this));
    }

    /* access modifiers changed from: protected */
    public void zzil() {
        super.zzil();
        if (this.zzbme) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuk)).booleanValue()) {
                zzb(this.zzbls.zzbsu.zzdrv);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzb(zzbgg zzbgg) {
        if (this.zzbls.zzbsu != null) {
            this.zzblu.zza(this.zzbls.zzbst, this.zzbls.zzbsu, zzbgg.getView(), zzbgg);
            this.zzbme = false;
            return;
        }
        this.zzbme = true;
        zzaxz.zzeo("Request to enable ActiveView before adState is available.");
    }

    /* access modifiers changed from: protected */
    public void zza(zzaxg zzaxg, zzaba zzaba) {
        if (zzaxg.errorCode != -2) {
            zzayh.zzelc.post(new zzj(this, zzaxg));
            return;
        }
        if (zzaxg.zzbst != null) {
            this.zzbls.zzbst = zzaxg.zzbst;
        }
        if (!zzaxg.zzehy.zzdyd || zzaxg.zzehy.zzckp) {
            zzayh.zzelc.post(new zzk(this, zzaxg, this.zzbly.zzbmu.zza(this.zzbls.zzsp, this.zzbls.zzbsp, zzaxg.zzehy), zzaba));
            return;
        }
        this.zzbls.zzbtw = 0;
        zzbw zzbw = this.zzbls;
        zzbv.zzle();
        zzbw.zzbss = zzapl.zza(this.zzbls.zzsp, this, zzaxg, this.zzbls.zzbso, null, this.zzbma, this, zzaba);
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable zzaxf zzaxf, zzaxf zzaxf2) {
        if (this.zzbls.zzmj() && this.zzbls.zzbsq != null) {
            this.zzbls.zzbsq.zzmm().zzef(zzaxf2.zzdyi);
        }
        try {
            if (zzaxf2.zzdrv != null && !zzaxf2.zzdyd && zzaxf2.zzehx && !zzaxf2.zzdwg.extras.containsKey("sdk_less_server_data")) {
                try {
                    zzaxf2.zzdrv.zzadz();
                } catch (Throwable unused) {
                    zzaxz.v("Could not render test Ad label.");
                }
            }
        } catch (RuntimeException unused2) {
            zzaxz.v("Could not render test AdLabel.");
        }
        if (!(zzaxf2 == null || zzaxf2.zzdrv == null)) {
            zzaxf2.zzdrv.zzay(zzaxf2.zzdzf);
        }
        return super.zza(zzaxf, zzaxf2);
    }

    public final void zza(zzabg zzabg) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzbls.zzbto = zzabg;
    }

    public final void zzh(View view) {
        this.zzbls.zzbtv = view;
        zzaxf zzaxf = new zzaxf(this.zzbls.zzbsv, null, null, null, null, null, null, null);
        zzb(zzaxf);
    }

    public final void zzjh() {
        onAdClicked();
    }

    public final void zzji() {
        recordImpression();
        zzih();
    }

    /* access modifiers changed from: protected */
    public final boolean zzjj() {
        return (this.zzbls.zzbsv == null || this.zzbls.zzbsv.zzehy == null || !this.zzbls.zzbsv.zzehy.zzdzc) ? false : true;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        zzik();
    }

    public final void zzjk() {
        zzii();
    }
}
