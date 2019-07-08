package com.google.ads.interactivemedia.v3.internal;

import java.util.NoSuchElementException;

/* compiled from: IMASDK */
class afz<E> extends agu<E> {
    private final int a;
    private int b;
    private final agb<E> c;

    /* access modifiers changed from: protected */
    public E a(int i) {
        return this.c.get(i);
    }

    protected afz(int i, int i2) {
        afx.b(i2, i);
        this.a = i;
        this.b = i2;
    }

    public final boolean hasNext() {
        return this.b < this.a;
    }

    public final E next() {
        if (hasNext()) {
            int i = this.b;
            this.b = i + 1;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.b;
    }

    public final boolean hasPrevious() {
        return this.b > 0;
    }

    public final E previous() {
        if (hasPrevious()) {
            int i = this.b - 1;
            this.b = i;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.b - 1;
    }

    afz(agb<E> agb, int i) {
        this(agb.size(), i);
        this.c = agb;
    }
}
