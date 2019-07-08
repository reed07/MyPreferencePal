package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: IMASDK */
final class zf<E> extends xj<Collection<E>> {
    private final xj<E> a;
    private final ys<? extends Collection<E>> b;

    public zf(wo woVar, Type type, xj<E> xjVar, ys<? extends Collection<E>> ysVar) {
        this.a = new aab(woVar, xjVar, type);
        this.b = ysVar;
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        Collection collection = (Collection) this.b.a();
        abu.a();
        while (abu.e()) {
            collection.add(this.a.read(abu));
        }
        abu.b();
        return collection;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        Collection<Object> collection = (Collection) obj;
        if (collection == null) {
            abx.f();
            return;
        }
        abx.b();
        for (Object write : collection) {
            this.a.write(abx, write);
        }
        abx.c();
    }
}
