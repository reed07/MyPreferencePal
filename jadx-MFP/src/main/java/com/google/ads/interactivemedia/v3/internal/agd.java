package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class agd extends agb<E> {
    private final transient int a;
    private final transient int b;
    private final /* synthetic */ agb c;

    agd(agb agb, int i, int i2) {
        this.c = agb;
        this.a = i;
        this.b = i2;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return true;
    }

    public final int size() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] b() {
        return this.c.b();
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return this.c.c() + this.a;
    }

    /* access modifiers changed from: 0000 */
    public final int d() {
        return this.c.c() + this.a + this.b;
    }

    public final E get(int i) {
        afx.a(i, this.b);
        return this.c.get(i + this.a);
    }

    /* renamed from: a */
    public final agb<E> subList(int i, int i2) {
        afx.a(i, i2, this.b);
        agb agb = this.c;
        int i3 = this.a;
        return agb.subList(i + i3, i2 + i3);
    }
}
