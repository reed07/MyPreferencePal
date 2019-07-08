package com.google.ads.interactivemedia.v3.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* compiled from: IMASDK */
final class agy extends WeakReference<Throwable> {
    private final int a;

    public agy(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.a = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final int hashCode() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        agy agy = (agy) obj;
        return this.a == agy.a && get() == agy.get();
    }
}
