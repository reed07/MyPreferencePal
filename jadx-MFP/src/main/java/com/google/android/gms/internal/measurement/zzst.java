package com.google.android.gms.internal.measurement;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzst {
    private final ConcurrentHashMap<zzsu, List<Throwable>> zzbsi = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzbsj = new ReferenceQueue<>();

    zzst() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference poll = this.zzbsj.poll();
        while (poll != null) {
            this.zzbsi.remove(poll);
            poll = this.zzbsj.poll();
        }
        return (List) this.zzbsi.get(new zzsu(th, null));
    }
}
