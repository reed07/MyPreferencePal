package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class zzby implements Runnable {
    private zzby() {
    }

    public final void run() {
        try {
            zzbw.zziv = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
        } finally {
            zzbw.zziy.countDown();
        }
    }
}
