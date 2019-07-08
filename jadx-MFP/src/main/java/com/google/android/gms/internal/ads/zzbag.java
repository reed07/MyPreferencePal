package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@zzark
public final class zzbag {
    private Map<Integer, Bitmap> zzenl = new ConcurrentHashMap();
    private AtomicInteger zzenm = new AtomicInteger(0);

    public final int zzb(Bitmap bitmap) {
        if (bitmap == null) {
            zzaxz.zzdn("Bitmap is null. Skipping putting into the Memory Map.");
            return -1;
        }
        int andIncrement = this.zzenm.getAndIncrement();
        this.zzenl.put(Integer.valueOf(andIncrement), bitmap);
        return andIncrement;
    }

    public final Bitmap zza(Integer num) {
        return (Bitmap) this.zzenl.get(num);
    }

    public final void zzb(Integer num) {
        this.zzenl.remove(num);
    }
}
