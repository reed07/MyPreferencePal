package com.google.ads.interactivemedia.v3.internal;

import java.util.NoSuchElementException;

/* compiled from: IMASDK */
final class agi extends agt<T> {
    private boolean a;
    private final /* synthetic */ Object b;

    agi(Object obj) {
        this.b = obj;
    }

    public final boolean hasNext() {
        return !this.a;
    }

    public final T next() {
        if (!this.a) {
            this.a = true;
            return this.b;
        }
        throw new NoSuchElementException();
    }
}
