package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;

/* compiled from: IMASDK */
final class ags<E> extends agg<E> {
    private final transient E a;
    private transient int b;

    ags(E e) {
        this.a = afx.a(e);
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return false;
    }

    public final int size() {
        return 1;
    }

    ags(E e, int i) {
        this.a = e;
        this.b = i;
    }

    public final boolean contains(Object obj) {
        return this.a.equals(obj);
    }

    public final agt<E> a() {
        return agj.a(this.a);
    }

    /* access modifiers changed from: 0000 */
    public final agb<E> h() {
        return agb.a(this.a);
    }

    /* access modifiers changed from: 0000 */
    public final int a(Object[] objArr, int i) {
        objArr[i] = this.a;
        return i + 1;
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = this.a.hashCode();
        this.b = hashCode;
        return hashCode;
    }

    /* access modifiers changed from: 0000 */
    public final boolean g() {
        return this.b != 0;
    }

    public final String toString() {
        String obj = this.a.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public final /* synthetic */ Iterator iterator() {
        return agj.a(this.a);
    }
}
