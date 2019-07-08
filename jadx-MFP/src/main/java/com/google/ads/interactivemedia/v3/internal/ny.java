package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class ny {
    public final int[] a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;

    public static ny a(int i, int[] iArr, int i2, int i3, int i4) {
        ny nyVar = new ny(i, 0, iArr, i2, i3, i4, -1);
        return nyVar;
    }

    public static ny a(int[] iArr, int i) {
        ny nyVar = new ny(4, 1, iArr, i, -1, -1, -1);
        return nyVar;
    }

    public static ny b(int[] iArr, int i) {
        ny nyVar = new ny(3, 1, iArr, i, -1, -1, -1);
        return nyVar;
    }

    public static ny a(int i) {
        ny nyVar = new ny(4, 2, new int[0], -1, -1, -1, i);
        return nyVar;
    }

    private ny(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
        this.b = i;
        this.a = iArr;
        this.c = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.d = i6;
    }
}
