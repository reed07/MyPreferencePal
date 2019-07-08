package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class ml {
    public final boolean a;
    private final int b;

    public ml(int i, boolean z) {
        this.b = i;
        this.a = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ml mlVar = (ml) obj;
        return this.b == mlVar.b && this.a == mlVar.a;
    }

    public final int hashCode() {
        return (this.b * 31) + (this.a ? 1 : 0);
    }
}
