package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public final class gd {
    public final int a;
    public final byte[] b;
    public final int c;
    public final int d;

    public gd(int i, byte[] bArr, int i2, int i3) {
        this.a = i;
        this.b = bArr;
        this.c = i2;
        this.d = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gd gdVar = (gd) obj;
        return this.a == gdVar.a && this.c == gdVar.c && this.d == gdVar.d && Arrays.equals(this.b, gdVar.b);
    }

    public final int hashCode() {
        return (((((this.a * 31) + Arrays.hashCode(this.b)) * 31) + this.c) * 31) + this.d;
    }
}
