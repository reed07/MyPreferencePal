package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class agp<K> extends agg<K> {
    private final transient age<K, ?> a;
    private final transient agb<K> b;

    agp(age<K, ?> age, agb<K> agb) {
        this.a = age;
        this.b = agb;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return true;
    }

    /* renamed from: a */
    public final agt<K> iterator() {
        return this.b.a();
    }

    /* access modifiers changed from: 0000 */
    public final int a(Object[] objArr, int i) {
        return this.b.a(objArr, i);
    }

    public final agb<K> e() {
        return this.b;
    }

    public final boolean contains(Object obj) {
        return this.a.get(obj) != null;
    }

    public final int size() {
        return this.a.size();
    }
}
