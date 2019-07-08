package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzbv;

final class zzahp {
    boolean zzblw;
    zzal zzdhl;
    @Nullable
    zzwb zzdhm;
    zzagj zzdhn;
    long zzdho;
    boolean zzdhp;
    private final /* synthetic */ zzaho zzdhq;

    zzahp(zzaho zzaho, zzagi zzagi) {
        this.zzdhq = zzaho;
        this.zzdhl = zzagi.zzby(zzaho.zzboa);
        this.zzdhn = new zzagj();
        zzagj zzagj = this.zzdhn;
        zzal zzal = this.zzdhl;
        zzal.zza((zzxa) new zzagk(zzagj));
        zzal.zza((zzxt) new zzags(zzagj));
        zzal.zza((zzabg) new zzagu(zzagj));
        zzal.zza((zzwx) new zzagw(zzagj));
        zzal.zza((zzavb) new zzagy(zzagj));
    }

    zzahp(zzaho zzaho, zzagi zzagi, zzwb zzwb) {
        this(zzaho, zzagi);
        this.zzdhm = zzwb;
    }

    /* access modifiers changed from: 0000 */
    public final boolean load() {
        if (this.zzblw) {
            return false;
        }
        zzwb zzwb = this.zzdhm;
        if (zzwb == null) {
            zzwb = this.zzdhq.zzdhi;
        }
        this.zzdhp = this.zzdhl.zzb(zzahm.zzi(zzwb));
        this.zzblw = true;
        this.zzdho = zzbv.zzlm().currentTimeMillis();
        return true;
    }
}
