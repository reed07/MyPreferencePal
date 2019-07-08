package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzfn implements Iterator<Object> {
    zzfn() {
    }

    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
