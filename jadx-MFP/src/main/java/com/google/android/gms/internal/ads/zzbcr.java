package com.google.android.gms.internal.ads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzark
public class zzbcr<T> implements zzbcn<T> {
    private final Object mLock = new Object();
    private int zzdiz = 0;
    private final BlockingQueue<zzbcs> zzepy = new LinkedBlockingQueue();
    private T zzepz;

    public final void zza(zzbcq<T> zzbcq, zzbco zzbco) {
        synchronized (this.mLock) {
            if (this.zzdiz == 1) {
                zzbcq.zzh(this.zzepz);
            } else if (this.zzdiz == -1) {
                zzbco.run();
            } else if (this.zzdiz == 0) {
                this.zzepy.add(new zzbcs(this, zzbcq, zzbco));
            }
        }
    }

    public final void zzo(T t) {
        synchronized (this.mLock) {
            if (this.zzdiz == 0) {
                this.zzepz = t;
                this.zzdiz = 1;
                for (zzbcs zzbcs : this.zzepy) {
                    zzbcs.zzeqa.zzh(t);
                }
                this.zzepy.clear();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    public final void reject() {
        synchronized (this.mLock) {
            if (this.zzdiz == 0) {
                this.zzdiz = -1;
                for (zzbcs zzbcs : this.zzepy) {
                    zzbcs.zzeqb.run();
                }
                this.zzepy.clear();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    public final int getStatus() {
        return this.zzdiz;
    }
}
