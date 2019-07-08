package com.google.android.gms.internal.ads;

public final class zzapk extends Exception {
    private final int mErrorCode;

    public zzapk(String str, int i) {
        super(str);
        this.mErrorCode = i;
    }

    public final int getErrorCode() {
        return this.mErrorCode;
    }
}
