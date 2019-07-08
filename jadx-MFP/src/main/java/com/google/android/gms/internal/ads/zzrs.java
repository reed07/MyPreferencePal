package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;

@zzark
public final class zzrs implements zzsb {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzrg zzbvr;
    private final zzu<zzajr> zzbvt = new zzrx(this);
    private final zzu<zzajr> zzbvu = new zzry(this);
    private final zzu<zzajr> zzbvv = new zzrz(this);
    /* access modifiers changed from: private */
    public final zzaa zzbvx;
    private zzaji zzbvy;
    /* access modifiers changed from: private */
    public boolean zzbvz;
    private final zzu<zzajr> zzbwa = new zzsa(this);

    public zzrs(zzrg zzrg, zzait zzait, Context context) {
        this.zzbvr = zzrg;
        this.mContext = context;
        this.zzbvx = new zzaa(this.mContext);
        this.zzbvy = zzait.zzb((zzcu) null);
        this.zzbvy.zza(new zzrt(this), new zzru(this));
        String str = "Core JS tracking ad unit: ";
        String valueOf = String.valueOf(this.zzbvr.zzbuu.zzmt());
        zzaxz.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        this.zzbvy.zza(new zzrv(this, jSONObject), new zzbcp());
    }

    public final boolean zznf() {
        return this.zzbvz;
    }

    public final void zzng() {
        this.zzbvy.zza(new zzrw(this), new zzbcp());
        this.zzbvy.release();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzajr zzajr) {
        zzajr.zza("/updateActiveView", this.zzbvt);
        zzajr.zza("/untrackActiveViewUnit", this.zzbvu);
        zzajr.zza("/visibilityChanged", this.zzbvv);
        if (zzbv.zzmf().zzv(this.mContext)) {
            zzajr.zza("/logScionEvent", this.zzbwa);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzajr zzajr) {
        zzajr.zzb("/visibilityChanged", this.zzbvv);
        zzajr.zzb("/untrackActiveViewUnit", this.zzbvu);
        zzajr.zzb("/updateActiveView", this.zzbvt);
        if (zzbv.zzmf().zzv(this.mContext)) {
            zzajr.zzb("/logScionEvent", this.zzbwa);
        }
    }
}
