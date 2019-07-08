package com.google.android.gms.internal.ads;

import android.os.Handler;

public final class zzgl {
    private final Handler handler;
    /* access modifiers changed from: private */
    public final zzgk zzabi;

    public zzgl(Handler handler2, zzgk zzgk) {
        this.handler = zzgk != null ? (Handler) zzpo.checkNotNull(handler2) : null;
        this.zzabi = zzgk;
    }

    public final void zzc(zzhn zzhn) {
        if (this.zzabi != null) {
            this.handler.post(new zzgm(this, zzhn));
        }
    }

    public final void zzb(String str, long j, long j2) {
        if (this.zzabi != null) {
            Handler handler2 = this.handler;
            zzgn zzgn = new zzgn(this, str, j, j2);
            handler2.post(zzgn);
        }
    }

    public final void zzd(zzfs zzfs) {
        if (this.zzabi != null) {
            this.handler.post(new zzgo(this, zzfs));
        }
    }

    public final void zzb(int i, long j, long j2) {
        if (this.zzabi != null) {
            Handler handler2 = this.handler;
            zzgp zzgp = new zzgp(this, i, j, j2);
            handler2.post(zzgp);
        }
    }

    public final void zzd(zzhn zzhn) {
        if (this.zzabi != null) {
            this.handler.post(new zzgq(this, zzhn));
        }
    }

    public final void zzm(int i) {
        if (this.zzabi != null) {
            this.handler.post(new zzgr(this, i));
        }
    }
}
