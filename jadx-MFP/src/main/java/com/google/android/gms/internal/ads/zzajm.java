package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

public final class zzajm extends zzbcr<zzaii> {
    private final Object mLock = new Object();
    /* access modifiers changed from: private */
    public zzazn<zzaii> zzdix;
    private boolean zzdjs;
    private int zzdjt;

    public zzajm(zzazn<zzaii> zzazn) {
        this.zzdix = zzazn;
        this.zzdjs = false;
        this.zzdjt = 0;
    }

    public final zzaji zzud() {
        zzaji zzaji = new zzaji(this);
        synchronized (this.mLock) {
            zza(new zzajn(this, zzaji), new zzajo(this, zzaji));
            Preconditions.checkState(this.zzdjt >= 0);
            this.zzdjt++;
        }
        return zzaji;
    }

    /* access modifiers changed from: protected */
    public final void zzue() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzdjt > 0);
            zzaxz.v("Releasing 1 reference for JS Engine");
            this.zzdjt--;
            zzug();
        }
    }

    public final void zzuf() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzdjt >= 0);
            zzaxz.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzdjs = true;
            zzug();
        }
    }

    private final void zzug() {
        synchronized (this.mLock) {
            Preconditions.checkState(this.zzdjt >= 0);
            if (!this.zzdjs || this.zzdjt != 0) {
                zzaxz.v("There are still references to the engine. Not destroying.");
            } else {
                zzaxz.v("No reference is left (including root). Cleaning up engine.");
                zza(new zzajp(this), new zzbcp());
            }
        }
    }
}
