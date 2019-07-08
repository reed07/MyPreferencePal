package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* compiled from: IMASDK */
public final class zc<E> extends xj<Object> {
    public static final xl a = new zd();
    private final Class<E> b;
    private final xj<E> c;

    public zc(wo woVar, xj<E> xjVar, Class<E> cls) {
        this.c = new aab(woVar, xjVar, cls);
        this.b = cls;
    }

    public final Object read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        abu.a();
        while (abu.e()) {
            arrayList.add(this.c.read(abu));
        }
        abu.b();
        int size = arrayList.size();
        Object newInstance = Array.newInstance(this.b, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public final void write(abx abx, Object obj) throws IOException {
        if (obj == null) {
            abx.f();
            return;
        }
        abx.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.write(abx, Array.get(obj, i));
        }
        abx.c();
    }
}
