package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/* compiled from: IMASDK */
public final class xu {
    private final Map<Type, ww<?>> a;
    private final abr b = abr.a();

    public xu(Map<Type, ww<?>> map) {
        this.a = map;
    }

    public final <T> ys<T> a(abt<T> abt) {
        Type b2 = abt.b();
        Class a2 = abt.a();
        ww wwVar = (ww) this.a.get(b2);
        if (wwVar != null) {
            return new xv(this, wwVar, b2);
        }
        ww wwVar2 = (ww) this.a.get(a2);
        if (wwVar2 != null) {
            return new yb(this, wwVar2, b2);
        }
        ys<T> a3 = a(a2);
        if (a3 != null) {
            return a3;
        }
        ys<T> ysVar = Collection.class.isAssignableFrom(a2) ? SortedSet.class.isAssignableFrom(a2) ? new yd<>(this) : EnumSet.class.isAssignableFrom(a2) ? new ye<>(this, b2) : Set.class.isAssignableFrom(a2) ? new yf<>(this) : Queue.class.isAssignableFrom(a2) ? new yg<>(this) : new yh<>(this) : Map.class.isAssignableFrom(a2) ? ConcurrentNavigableMap.class.isAssignableFrom(a2) ? new yi<>(this) : ConcurrentMap.class.isAssignableFrom(a2) ? new xw<>(this) : SortedMap.class.isAssignableFrom(a2) ? new xx<>(this) : (!(b2 instanceof ParameterizedType) || String.class.isAssignableFrom(abt.a(((ParameterizedType) b2).getActualTypeArguments()[0]).a())) ? new xz<>(this) : new xy<>(this) : null;
        if (ysVar != null) {
            return ysVar;
        }
        return new ya(this, a2, b2);
    }

    private final <T> ys<T> a(Class<? super T> cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.b.a(declaredConstructor);
            }
            return new yc(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final String toString() {
        return this.a.toString();
    }
}
