package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;

/* compiled from: IMASDK */
final class agr<E> extends agg<E> {
    static final agr<Object> a;
    private final transient Object[] b;
    private final transient Object[] c;
    private final transient int d;
    private final transient int e;
    private final transient int f;

    agr(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.b = objArr;
        this.c = objArr2;
        this.d = i2;
        this.e = i;
        this.f = i3;
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean g() {
        return true;
    }

    public final boolean contains(Object obj) {
        Object[] objArr = this.c;
        if (obj == null || objArr == null) {
            return false;
        }
        int b2 = agj.b(obj);
        while (true) {
            int i = b2 & this.d;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            b2 = i + 1;
        }
    }

    public final int size() {
        return this.f;
    }

    public final agt<E> a() {
        return e().a();
    }

    /* access modifiers changed from: 0000 */
    public final Object[] b() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final int d() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.b, 0, objArr, i, this.f);
        return i + this.f;
    }

    /* access modifiers changed from: 0000 */
    public final agb<E> h() {
        return agb.b(this.b, this.f);
    }

    public final int hashCode() {
        return this.e;
    }

    public final /* synthetic */ Iterator iterator() {
        return e().a();
    }

    static {
        agr agr = new agr(new Object[0], 0, null, 0, 0);
        a = agr;
    }
}
