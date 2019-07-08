package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public abstract class zztd {
    @Nullable
    private static MessageDigest zzbyx;
    protected Object mLock = new Object();

    /* access modifiers changed from: 0000 */
    public abstract byte[] zzay(String str);

    /* access modifiers changed from: protected */
    @Nullable
    public final MessageDigest zzoc() {
        synchronized (this.mLock) {
            if (zzbyx != null) {
                MessageDigest messageDigest = zzbyx;
                return messageDigest;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    zzbyx = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            MessageDigest messageDigest2 = zzbyx;
            return messageDigest2;
        }
    }
}
