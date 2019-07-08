package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: IMASDK */
final class aae extends xj<AtomicIntegerArray> {
    aae() {
    }

    private static AtomicIntegerArray a(abu abu) throws IOException {
        ArrayList arrayList = new ArrayList();
        abu.a();
        while (abu.e()) {
            try {
                arrayList.add(Integer.valueOf(abu.m()));
            } catch (NumberFormatException e) {
                throw new xh((Throwable) e);
            }
        }
        abu.b();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i = 0; i < size; i++) {
            atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
        }
        return atomicIntegerArray;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return a(abu);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
        abx.b();
        int length = atomicIntegerArray.length();
        for (int i = 0; i < length; i++) {
            abx.a((long) atomicIntegerArray.get(i));
        }
        abx.c();
    }
}
