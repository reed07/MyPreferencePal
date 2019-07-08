package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

public final class zzpl<T> implements zzpi {
    private volatile T result;
    public final int type;
    private final zzov zzagy;
    public final zzoz zzazo;
    private final zzpm<? extends T> zzbhf;
    private volatile boolean zzbhg;
    private volatile long zzbhh;

    public zzpl(zzov zzov, Uri uri, int i, zzpm<? extends T> zzpm) {
        this.zzagy = zzov;
        this.zzazo = new zzoz(uri, 1);
        this.type = i;
        this.zzbhf = zzpm;
    }

    public final T getResult() {
        return this.result;
    }

    public final long zzfv() {
        return this.zzbhh;
    }

    public final void cancelLoad() {
        this.zzbhg = true;
    }

    public final boolean zzfe() {
        return this.zzbhg;
    }

    public final void zzff() throws IOException, InterruptedException {
        zzoy zzoy = new zzoy(this.zzagy, this.zzazo);
        try {
            zzoy.open();
            this.result = this.zzbhf.zzb(this.zzagy.getUri(), zzoy);
        } finally {
            this.zzbhh = zzoy.zzgt();
            zzqe.closeQuietly(zzoy);
        }
    }
}
