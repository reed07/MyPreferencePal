package com.google.ads.interactivemedia.v3.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: IMASDK */
final class agx {
    private final ConcurrentHashMap<agy, List<Throwable>> a = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    agx() {
    }

    public final List<Throwable> a(Throwable th, boolean z) {
        Reference poll = this.b.poll();
        while (poll != null) {
            this.a.remove(poll);
            poll = this.b.poll();
        }
        return (List) this.a.get(new agy(th, null));
    }
}
