package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class ahl {
    private final Object a;
    private final int b;

    ahl(Object obj) {
        this.b = System.identityHashCode(obj);
        this.a = obj;
    }

    public final int hashCode() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ahl)) {
            return false;
        }
        ahl ahl = (ahl) obj;
        if (this.b == ahl.b && this.a == ahl.a) {
            return true;
        }
        return false;
    }
}
