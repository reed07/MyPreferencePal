package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzbrf {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzfqr;
    private static final ByteBuffer zzfqs;
    private static final zzbqf zzfqt;

    public static int zzbf(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzbi(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static boolean zzi(zzbsl zzbsl) {
        return false;
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zzv(byte[] bArr) {
        return zzbuc.zzv(bArr);
    }

    public static String zzw(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static Object zzb(Object obj, Object obj2) {
        return ((zzbsl) obj).zzamt().zzd((zzbsl) obj2).zzamz();
    }

    static {
        byte[] bArr = new byte[0];
        zzfqr = bArr;
        zzfqs = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzfqr;
        zzfqt = zzbqf.zzb(bArr2, 0, bArr2.length, false);
    }
}
