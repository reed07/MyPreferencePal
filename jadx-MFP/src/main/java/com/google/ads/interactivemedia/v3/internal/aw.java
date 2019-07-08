package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class aw extends Exception {
    public final int a;

    public static aw a(IOException iOException) {
        return new aw(0, iOException, -1);
    }

    public static aw a(Exception exc, int i) {
        return new aw(1, exc, i);
    }

    public static aw a(RuntimeException runtimeException) {
        return new aw(2, runtimeException, -1);
    }

    public static aw a(OutOfMemoryError outOfMemoryError) {
        return new aw(4, outOfMemoryError, -1);
    }

    private aw(int i, Throwable th, int i2) {
        super(th);
        this.a = i;
    }
}
