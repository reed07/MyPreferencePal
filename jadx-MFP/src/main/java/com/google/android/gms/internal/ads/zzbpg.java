package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzbpg {
    private final ConcurrentHashMap<zzbph, List<Throwable>> zzfkq = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzfkr = new ReferenceQueue<>();

    zzbpg() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference poll = this.zzfkr.poll();
        while (poll != null) {
            this.zzfkq.remove(poll);
            poll = this.zzfkr.poll();
        }
        return (List) this.zzfkq.get(new zzbph(th, null));
    }
}
