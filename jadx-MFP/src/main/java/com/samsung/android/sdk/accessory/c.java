package com.samsung.android.sdk.accessory;

public final class c extends Exception {
    private int a;

    public c() {
    }

    public c(int i, String str) {
        super(str);
        this.a = i;
    }

    public c(String str, Throwable th) {
        super(str, th);
        this.a = 2048;
    }

    public final int a() {
        return this.a;
    }
}
