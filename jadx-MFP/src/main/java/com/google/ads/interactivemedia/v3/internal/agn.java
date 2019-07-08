package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: IMASDK */
final class agn<K, V> extends agg<Entry<K, V>> {
    private final transient age<K, V> a;
    /* access modifiers changed from: private */
    public final transient Object[] b;
    /* access modifiers changed from: private */
    public final transient int c = 0;
    /* access modifiers changed from: private */
    public final transient int d;

    agn(age<K, V> age, Object[] objArr, int i, int i2) {
        this.a = age;
        this.b = objArr;
        this.d = i2;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return true;
    }

    public final agt<Entry<K, V>> a() {
        return e().a();
    }

    /* access modifiers changed from: 0000 */
    public final int a(Object[] objArr, int i) {
        return e().a(objArr, i);
    }

    /* access modifiers changed from: 0000 */
    public final agb<Entry<K, V>> h() {
        return new ago(this);
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (value == null || !value.equals(this.a.get(key))) {
            return false;
        }
        return true;
    }

    public final int size() {
        return this.d;
    }

    public final /* synthetic */ Iterator iterator() {
        return e().a();
    }
}
