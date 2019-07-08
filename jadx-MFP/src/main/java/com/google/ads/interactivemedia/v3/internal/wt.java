package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: IMASDK */
final class wt extends xj<AtomicLongArray> {
    private final /* synthetic */ xj a;

    wt(xj xjVar) {
        this.a = xjVar;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        ArrayList arrayList = new ArrayList();
        abu.a();
        while (abu.e()) {
            arrayList.add(Long.valueOf(((Number) this.a.read(abu)).longValue()));
        }
        abu.b();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
        abx.b();
        int length = atomicLongArray.length();
        for (int i = 0; i < length; i++) {
            this.a.write(abx, Long.valueOf(atomicLongArray.get(i)));
        }
        abx.c();
    }
}
