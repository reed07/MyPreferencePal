package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class yw implements CharSequence {
    char[] a;

    yw() {
    }

    public final int length() {
        return this.a.length;
    }

    public final char charAt(int i) {
        return this.a[i];
    }

    public final CharSequence subSequence(int i, int i2) {
        return new String(this.a, i, i2 - i);
    }
}
