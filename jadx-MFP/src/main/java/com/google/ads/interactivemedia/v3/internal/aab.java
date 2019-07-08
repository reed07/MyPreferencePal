package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: IMASDK */
final class aab<T> extends xj<T> {
    private final wo a;
    private final xj<T> b;
    private final Type c;

    aab(wo woVar, xj<T> xjVar, Type type) {
        this.a = woVar;
        this.b = xjVar;
        this.c = type;
    }

    public final T read(abu abu) throws IOException {
        return this.b.read(abu);
    }

    public final void write(abx abx, T t) throws IOException {
        xj<T> xjVar = this.b;
        Type type = this.c;
        if (t != null && (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) {
            type = t.getClass();
        }
        if (type != this.c) {
            xjVar = this.a.a(abt.a(type));
            if (xjVar instanceof zs) {
                xj<T> xjVar2 = this.b;
                if (!(xjVar2 instanceof zs)) {
                    xjVar = xjVar2;
                }
            }
        }
        xjVar.write(abx, t);
    }
}
