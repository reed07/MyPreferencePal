package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.Set;

/* compiled from: IMASDK */
public abstract class agg<E> extends aga<E> implements Set<E> {
    private transient agb<E> a;

    private static <E> agg<E> a(E e) {
        return new ags(e);
    }

    /* renamed from: a */
    public abstract agt<E> iterator();

    /* access modifiers changed from: 0000 */
    public boolean g() {
        return false;
    }

    private static <E> agg<E> a(int i, Object... objArr) {
        while (true) {
            switch (i) {
                case 0:
                    return agr.a;
                case 1:
                    return a((E) objArr[0]);
                default:
                    int a2 = a(i);
                    Object[] objArr2 = new Object[a2];
                    int i2 = a2 - 1;
                    int i3 = 0;
                    int i4 = 0;
                    for (int i5 = 0; i5 < i; i5++) {
                        Object a3 = agj.a(objArr[i5], i5);
                        int hashCode = a3.hashCode();
                        int b = agj.b(hashCode);
                        while (true) {
                            int i6 = b & i2;
                            Object obj = objArr2[i6];
                            if (obj == null) {
                                int i7 = i4 + 1;
                                objArr[i4] = a3;
                                objArr2[i6] = a3;
                                i3 += hashCode;
                                i4 = i7;
                            } else if (!obj.equals(a3)) {
                                b++;
                            }
                        }
                    }
                    Arrays.fill(objArr, i4, i, null);
                    if (i4 == 1) {
                        return new ags(objArr[0], i3);
                    }
                    if (a(i4) < a2 / 2) {
                        i = i4;
                    } else {
                        int length = objArr.length;
                        if (i4 < (length >> 1) + (length >> 2)) {
                            objArr = Arrays.copyOf(objArr, i4);
                        }
                        agr agr = new agr(objArr, i3, objArr2, i2, i4);
                        return agr;
                    }
            }
        }
    }

    static int a(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        afx.a(z, (Object) "collection too large");
        return 1073741824;
    }

    public static <E> agg<E> a(E[] eArr) {
        switch (eArr.length) {
            case 0:
                return agr.a;
            case 1:
                return a(eArr[0]);
            default:
                return a(eArr.length, (Object[]) eArr.clone());
        }
    }

    agg() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof agg) || !g() || !((agg) obj).g() || hashCode() == obj.hashCode()) {
            return agk.a(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return agk.a(this);
    }

    public agb<E> e() {
        agb<E> agb = this.a;
        if (agb != null) {
            return agb;
        }
        agb<E> h = h();
        this.a = h;
        return h;
    }

    /* access modifiers changed from: 0000 */
    public agb<E> h() {
        return agb.b(toArray());
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new agh(toArray());
    }
}
