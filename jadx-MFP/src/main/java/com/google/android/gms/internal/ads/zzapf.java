package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;

@zzark
public abstract class zzapf implements zzazb<Void>, zzbho {
    protected final Context mContext;
    protected final zzbgg zzdin;
    private final zzapm zzdsj;
    private final zzaxg zzdsk;
    protected zzasm zzdsl;
    private Runnable zzdsm;
    private final Object zzdsn = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean zzdso = new AtomicBoolean(true);

    protected zzapf(Context context, zzaxg zzaxg, zzbgg zzbgg, zzapm zzapm) {
        this.mContext = context;
        this.zzdsk = zzaxg;
        this.zzdsl = this.zzdsk.zzehy;
        this.zzdin = zzbgg;
        this.zzdsj = zzapm;
    }

    /* access modifiers changed from: protected */
    public abstract void zzvz();

    public final void zzp(boolean z) {
        zzaxz.zzdn("WebView finished loading.");
        int i = 0;
        if (this.zzdso.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            zzcq(i);
            zzayh.zzelc.removeCallbacks(this.zzdsm);
        }
    }

    public void cancel() {
        if (this.zzdso.getAndSet(false)) {
            this.zzdin.stopLoading();
            zzbv.zzlh();
            zzayp.zzi(this.zzdin);
            zzcq(-1);
            zzayh.zzelc.removeCallbacks(this.zzdsm);
        }
    }

    /* access modifiers changed from: protected */
    public void zzcq(int i) {
        int i2 = i;
        if (i2 != -2) {
            this.zzdsl = new zzasm(i2, this.zzdsl.zzdlx);
        }
        this.zzdin.zzadf();
        zzapm zzapm = this.zzdsj;
        zzasi zzasi = this.zzdsk.zzeag;
        zzaxf zzaxf = r1;
        zzapm zzapm2 = zzapm;
        zzaxf zzaxf2 = new zzaxf(zzasi.zzdwg, this.zzdin, this.zzdsl.zzdlq, i, this.zzdsl.zzdlr, this.zzdsl.zzdyf, this.zzdsl.orientation, this.zzdsl.zzdlx, zzasi.zzdwj, this.zzdsl.zzdyd, null, null, null, null, null, this.zzdsl.zzdye, this.zzdsk.zzbst, this.zzdsl.zzdyc, this.zzdsk.zzehn, this.zzdsl.zzdyh, this.zzdsl.zzdyi, this.zzdsk.zzehh, null, this.zzdsl.zzdyr, this.zzdsl.zzdys, this.zzdsl.zzdyt, this.zzdsl.zzdyu, this.zzdsl.zzdyv, null, this.zzdsl.zzdlu, this.zzdsl.zzdyy, this.zzdsk.zzehw, this.zzdsk.zzehy.zzbph, this.zzdsk.zzehx, this.zzdsk.zzehy.zzdzc, this.zzdsl.zzdls, this.zzdsk.zzehy.zzbpi, this.zzdsk.zzehy.zzdzd, this.zzdsk.zzehy.zzdzf);
        zzapm2.zzb(zzaxf);
    }

    public final /* synthetic */ Object zzwa() {
        Preconditions.checkMainThread("Webview render task needs to be called on UI thread.");
        this.zzdsm = new zzapg(this);
        zzayh.zzelc.postDelayed(this.zzdsm, ((Long) zzwu.zzpz().zzd(zzaan.zzctf)).longValue());
        zzvz();
        return null;
    }
}
