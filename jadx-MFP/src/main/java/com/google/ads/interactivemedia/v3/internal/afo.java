package com.google.ads.interactivemedia.v3.internal;

import android.util.Base64;

/* compiled from: IMASDK */
public class afo {
    public String a(byte[] bArr, boolean z) {
        return Base64.encodeToString(bArr, 11);
    }

    public byte[] a(String str, boolean z) throws IllegalArgumentException {
        return Base64.decode(str, 2);
    }

    afo() {
    }
}
