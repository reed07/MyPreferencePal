package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zze {
    private final ConcurrentHashMap<zzf, List<Throwable>> zze = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzf = new ReferenceQueue<>();

    zze() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference poll = this.zzf.poll();
        while (poll != null) {
            this.zze.remove(poll);
            poll = this.zzf.poll();
        }
        List<Throwable> list = (List) this.zze.get(new zzf(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> list2 = (List) this.zze.putIfAbsent(new zzf(th, this.zzf), vector);
        return list2 == null ? vector : list2;
    }
}
