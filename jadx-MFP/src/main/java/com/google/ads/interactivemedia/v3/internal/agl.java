package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class agl<E> extends agb<E> {
    static final agb<Object> a = new agl(new Object[0], 0);
    private final transient Object[] b;
    private final transient int c;

    agl(Object[] objArr, int i) {
        this.b = objArr;
        this.c = i;
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return false;
    }

    public final int size() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] b() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final int d() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.b, 0, objArr, i, this.c);
        return i + this.c;
    }

    public final E get(int i) {
        afx.a(i, this.c);
        return this.b[i];
    }
}
