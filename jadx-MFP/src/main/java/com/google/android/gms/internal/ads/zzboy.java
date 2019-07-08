package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

public final class zzboy {
    private static final ThreadLocal<SecureRandom> zzfkf = new zzboz();

    /* access modifiers changed from: private */
    public static SecureRandom zzakb() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] zzeg(int i) {
        byte[] bArr = new byte[i];
        ((SecureRandom) zzfkf.get()).nextBytes(bArr);
        return bArr;
    }
}
