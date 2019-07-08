package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbut;
import java.io.IOException;

public abstract class zzbut<M extends zzbut<M>> extends zzbuz {
    protected zzbuv zzfwk;

    /* access modifiers changed from: protected */
    public int zzt() {
        if (this.zzfwk == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzfwk.size(); i2++) {
            i += this.zzfwk.zzgg(i2).zzt();
        }
        return i;
    }

    public void zza(zzbur zzbur) throws IOException {
        if (this.zzfwk != null) {
            for (int i = 0; i < this.zzfwk.size(); i++) {
                this.zzfwk.zzgg(i).zza(zzbur);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbuq zzbuq, int i) throws IOException {
        int position = zzbuq.getPosition();
        if (!zzbuq.zzep(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzbvb zzbvb = new zzbvb(i, zzbuq.zzam(position, zzbuq.getPosition() - position));
        zzbuw zzbuw = null;
        zzbuv zzbuv = this.zzfwk;
        if (zzbuv == null) {
            this.zzfwk = new zzbuv();
        } else {
            zzbuw = zzbuv.zzgf(i2);
        }
        if (zzbuw == null) {
            zzbuw = new zzbuw();
            this.zzfwk.zza(i2, zzbuw);
        }
        zzbuw.zza(zzbvb);
        return true;
    }

    public final /* synthetic */ zzbuz zzapm() throws CloneNotSupportedException {
        return (zzbut) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzbut zzbut = (zzbut) super.clone();
        zzbux.zza(this, zzbut);
        return zzbut;
    }
}
