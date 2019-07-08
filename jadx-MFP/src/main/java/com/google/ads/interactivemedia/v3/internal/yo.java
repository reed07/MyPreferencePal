package com.google.ads.interactivemedia.v3.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: IMASDK */
public final class yo<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> d = new yp();
    int a;
    int b;
    final d<K, V> c;
    private Comparator<? super K> e;
    private d<K, V> f;
    private a g;
    private b h;

    /* compiled from: IMASDK */
    class a extends AbstractSet<Entry<K, V>> {
        a() {
        }

        public final int size() {
            return yo.this.a;
        }

        public final Iterator<Entry<K, V>> iterator() {
            return new yq(this);
        }

        public final boolean contains(Object obj) {
            return (obj instanceof Entry) && yo.this.a((Entry) obj) != null;
        }

        public final boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            d a2 = yo.this.a((Entry) obj);
            if (a2 == null) {
                return false;
            }
            yo.this.a(a2, true);
            return true;
        }

        public final void clear() {
            yo.this.clear();
        }
    }

    /* compiled from: IMASDK */
    final class b extends AbstractSet<K> {
        b() {
        }

        public final int size() {
            return yo.this.a;
        }

        public final Iterator<K> iterator() {
            return new yr(this);
        }

        public final boolean contains(Object obj) {
            return yo.this.containsKey(obj);
        }

        public final boolean remove(Object obj) {
            return yo.this.a(obj) != null;
        }

        public final void clear() {
            yo.this.clear();
        }
    }

    /* compiled from: IMASDK */
    abstract class c<T> implements Iterator<T> {
        private d<K, V> a = yo.this.c.d;
        private d<K, V> b = null;
        private int c = yo.this.b;

        c() {
        }

        public final boolean hasNext() {
            return this.a != yo.this.c;
        }

        /* access modifiers changed from: 0000 */
        public final d<K, V> a() {
            d<K, V> dVar = this.a;
            if (dVar == yo.this.c) {
                throw new NoSuchElementException();
            } else if (yo.this.b == this.c) {
                this.a = dVar.d;
                this.b = dVar;
                return dVar;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final void remove() {
            d<K, V> dVar = this.b;
            if (dVar != null) {
                yo.this.a(dVar, true);
                this.b = null;
                this.c = yo.this.b;
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* compiled from: IMASDK */
    static final class d<K, V> implements Entry<K, V> {
        d<K, V> a;
        d<K, V> b;
        d<K, V> c;
        d<K, V> d;
        d<K, V> e;
        final K f;
        V g;
        int h;

        d() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d<K, V> dVar, K k, d<K, V> dVar2, d<K, V> dVar3) {
            this.a = dVar;
            this.f = k;
            this.h = 1;
            this.d = dVar2;
            this.e = dVar3;
            dVar3.d = this;
            dVar2.e = this;
        }

        public final K getKey() {
            return this.f;
        }

        public final V getValue() {
            return this.g;
        }

        public final V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            K k = this.f;
            if (k != null ? k.equals(entry.getKey()) : entry.getKey() == null) {
                V v = this.g;
                if (v != null ? v.equals(entry.getValue()) : entry.getValue() == null) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            K k = this.f;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.g;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f);
            sb.append("=");
            sb.append(this.g);
            return sb.toString();
        }
    }

    public yo() {
        this(d);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Comparator<? super K>, code=java.util.Comparator, for r2v0, types: [java.util.Comparator<? super K>, java.util.Comparator] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private yo(java.util.Comparator r2) {
        /*
            r1 = this;
            r1.<init>()
            r0 = 0
            r1.a = r0
            r1.b = r0
            com.google.ads.interactivemedia.v3.internal.yo$d r0 = new com.google.ads.interactivemedia.v3.internal.yo$d
            r0.<init>()
            r1.c = r0
            if (r2 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            java.util.Comparator<java.lang.Comparable> r2 = d
        L_0x0014:
            r1.e = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.yo.<init>(java.util.Comparator):void");
    }

    public final int size() {
        return this.a;
    }

    public final V get(Object obj) {
        d b2 = b(obj);
        if (b2 != null) {
            return b2.g;
        }
        return null;
    }

    public final boolean containsKey(Object obj) {
        return b(obj) != null;
    }

    public final V put(K k, V v) {
        if (k != null) {
            d a2 = a(k, true);
            V v2 = a2.g;
            a2.g = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public final void clear() {
        this.f = null;
        this.a = 0;
        this.b++;
        d<K, V> dVar = this.c;
        dVar.e = dVar;
        dVar.d = dVar;
    }

    public final V remove(Object obj) {
        d a2 = a(obj);
        if (a2 != null) {
            return a2.g;
        }
        return null;
    }

    private final d<K, V> a(K k, boolean z) {
        int i;
        d<K, V> dVar;
        Comparator<? super K> comparator = this.e;
        d<K, V> dVar2 = this.f;
        if (dVar2 != null) {
            Comparable comparable = comparator == d ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(dVar2.f);
                } else {
                    i = comparator.compare(k, dVar2.f);
                }
                if (i != 0) {
                    d<K, V> dVar3 = i < 0 ? dVar2.b : dVar2.c;
                    if (dVar3 == null) {
                        break;
                    }
                    dVar2 = dVar3;
                } else {
                    return dVar2;
                }
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        d<K, V> dVar4 = this.c;
        if (dVar2 != null) {
            dVar = new d<>(dVar2, k, dVar4, dVar4.e);
            if (i < 0) {
                dVar2.b = dVar;
            } else {
                dVar2.c = dVar;
            }
            b(dVar2, true);
        } else if (comparator != d || (k instanceof Comparable)) {
            dVar = new d<>(dVar2, k, dVar4, dVar4.e);
            this.f = dVar;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(k.getClass().getName());
            sb.append(" is not Comparable");
            throw new ClassCastException(sb.toString());
        }
        this.a++;
        this.b++;
        return dVar;
    }

    private final d<K, V> b(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return a((K) obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if ((r3 == r5 || (r3 != null && r3.equals(r5))) != false) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.yo.d<K, V> a(java.util.Map.Entry<?, ?> r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r5.getKey()
            com.google.ads.interactivemedia.v3.internal.yo$d r0 = r4.b(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0023
            V r3 = r0.g
            java.lang.Object r5 = r5.getValue()
            if (r3 == r5) goto L_0x001f
            if (r3 == 0) goto L_0x001d
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r5 = 0
            goto L_0x0020
        L_0x001f:
            r5 = 1
        L_0x0020:
            if (r5 == 0) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            if (r1 == 0) goto L_0x0027
            return r0
        L_0x0027:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.yo.a(java.util.Map$Entry):com.google.ads.interactivemedia.v3.internal.yo$d");
    }

    /* access modifiers changed from: 0000 */
    public final void a(d<K, V> dVar, boolean z) {
        d<K, V> dVar2;
        int i;
        if (z) {
            dVar.e.d = dVar.d;
            dVar.d.e = dVar.e;
        }
        d<K, V> dVar3 = dVar.b;
        d<K, V> dVar4 = dVar.c;
        d<K, V> dVar5 = dVar.a;
        int i2 = 0;
        if (dVar3 == null || dVar4 == null) {
            if (dVar3 != null) {
                a(dVar, dVar3);
                dVar.b = null;
            } else if (dVar4 != null) {
                a(dVar, dVar4);
                dVar.c = null;
            } else {
                a(dVar, null);
            }
            b(dVar5, false);
            this.a--;
            this.b++;
            return;
        }
        if (dVar3.h > dVar4.h) {
            d<K, V> dVar6 = dVar3;
            for (d<K, V> dVar7 = dVar3.c; dVar7 != null; dVar7 = dVar7.c) {
                dVar6 = dVar7;
            }
            dVar2 = dVar6;
        } else {
            d<K, V> dVar8 = dVar4;
            d<K, V> dVar9 = dVar4.b;
            dVar2 = dVar8;
            while (dVar9 != null) {
                d<K, V> dVar10 = dVar9;
                dVar9 = dVar9.b;
                dVar2 = dVar10;
            }
        }
        a(dVar2, false);
        d<K, V> dVar11 = dVar.b;
        if (dVar11 != null) {
            i = dVar11.h;
            dVar2.b = dVar11;
            dVar11.a = dVar2;
            dVar.b = null;
        } else {
            i = 0;
        }
        d<K, V> dVar12 = dVar.c;
        if (dVar12 != null) {
            i2 = dVar12.h;
            dVar2.c = dVar12;
            dVar12.a = dVar2;
            dVar.c = null;
        }
        dVar2.h = Math.max(i, i2) + 1;
        a(dVar, dVar2);
    }

    /* access modifiers changed from: 0000 */
    public final d<K, V> a(Object obj) {
        d<K, V> b2 = b(obj);
        if (b2 != null) {
            a(b2, true);
        }
        return b2;
    }

    private final void a(d<K, V> dVar, d<K, V> dVar2) {
        d<K, V> dVar3 = dVar.a;
        dVar.a = null;
        if (dVar2 != null) {
            dVar2.a = dVar3;
        }
        if (dVar3 == null) {
            this.f = dVar2;
        } else if (dVar3.b == dVar) {
            dVar3.b = dVar2;
        } else {
            dVar3.c = dVar2;
        }
    }

    private final void b(d<K, V> dVar, boolean z) {
        while (dVar != null) {
            d<K, V> dVar2 = dVar.b;
            d<K, V> dVar3 = dVar.c;
            int i = 0;
            int i2 = dVar2 != null ? dVar2.h : 0;
            int i3 = dVar3 != null ? dVar3.h : 0;
            int i4 = i2 - i3;
            if (i4 != -2) {
                if (i4 != 2) {
                    if (i4 != 0) {
                        dVar.h = Math.max(i2, i3) + 1;
                        if (!z) {
                            break;
                        }
                    } else {
                        dVar.h = i2 + 1;
                        if (z) {
                            return;
                        }
                    }
                } else {
                    d<K, V> dVar4 = dVar2.b;
                    d<K, V> dVar5 = dVar2.c;
                    int i5 = dVar5 != null ? dVar5.h : 0;
                    if (dVar4 != null) {
                        i = dVar4.h;
                    }
                    int i6 = i - i5;
                    if (i6 == 1 || (i6 == 0 && !z)) {
                        b(dVar);
                    } else {
                        a(dVar2);
                        b(dVar);
                    }
                    if (z) {
                        break;
                    }
                }
            } else {
                d<K, V> dVar6 = dVar3.b;
                d<K, V> dVar7 = dVar3.c;
                int i7 = dVar7 != null ? dVar7.h : 0;
                if (dVar6 != null) {
                    i = dVar6.h;
                }
                int i8 = i - i7;
                if (i8 == -1 || (i8 == 0 && !z)) {
                    a(dVar);
                } else {
                    b(dVar3);
                    a(dVar);
                }
                if (z) {
                    break;
                }
            }
            dVar = dVar.a;
        }
    }

    private final void a(d<K, V> dVar) {
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar3.b;
        d<K, V> dVar5 = dVar3.c;
        dVar.c = dVar4;
        if (dVar4 != null) {
            dVar4.a = dVar;
        }
        a(dVar, dVar3);
        dVar3.b = dVar;
        dVar.a = dVar3;
        int i = 0;
        dVar.h = Math.max(dVar2 != null ? dVar2.h : 0, dVar4 != null ? dVar4.h : 0) + 1;
        int i2 = dVar.h;
        if (dVar5 != null) {
            i = dVar5.h;
        }
        dVar3.h = Math.max(i2, i) + 1;
    }

    private final void b(d<K, V> dVar) {
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar2.b;
        d<K, V> dVar5 = dVar2.c;
        dVar.b = dVar5;
        if (dVar5 != null) {
            dVar5.a = dVar;
        }
        a(dVar, dVar2);
        dVar2.c = dVar;
        dVar.a = dVar2;
        int i = 0;
        dVar.h = Math.max(dVar3 != null ? dVar3.h : 0, dVar5 != null ? dVar5.h : 0) + 1;
        int i2 = dVar.h;
        if (dVar4 != null) {
            i = dVar4.h;
        }
        dVar2.h = Math.max(i2, i) + 1;
    }

    public final Set<Entry<K, V>> entrySet() {
        a aVar = this.g;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a();
        this.g = aVar2;
        return aVar2;
    }

    public final Set<K> keySet() {
        b bVar = this.h;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b();
        this.h = bVar2;
        return bVar2;
    }

    private final Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
