package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzbw {
    private static boolean zziu = false;
    /* access modifiers changed from: private */
    public static MessageDigest zziv;
    private static final Object zziw = new Object();
    private static final Object zzix = new Object();
    static CountDownLatch zziy = new CountDownLatch(1);

    static void zzw() {
        synchronized (zzix) {
            if (!zziu) {
                zziu = true;
                new Thread(new zzby()).start();
            }
        }
    }

    private static MessageDigest zzx() {
        boolean z;
        zzw();
        try {
            z = zziy.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            z = false;
        }
        if (!z) {
            return null;
        }
        MessageDigest messageDigest = zziv;
        if (messageDigest == null) {
            return null;
        }
        return messageDigest;
    }

    static String zza(zzbl zzbl, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArr;
        byte[] zzb = zzbuz.zzb(zzbl);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctq)).booleanValue()) {
            Vector zza = zza(zzb, 255);
            if (zza == null || zza.size() == 0) {
                bArr = zza(zzbuz.zzb(zzc(4096)), str, true);
            } else {
                zzbs zzbs = new zzbs();
                zzbs.zzhr = new byte[zza.size()][];
                Iterator it = zza.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2 = i + 1;
                    zzbs.zzhr[i] = zza((byte[]) it.next(), str, false);
                    i = i2;
                }
                zzbs.zzhm = zzb(zzb);
                bArr = zzbuz.zzb(zzbs);
            }
        } else if (zzdq.zztk != null) {
            byte[] zzc = zzdq.zztk.zzc(zzb, str != null ? str.getBytes() : new byte[0]);
            zzbs zzbs2 = new zzbs();
            zzbs2.zzhr = new byte[][]{zzc};
            zzbs2.zzfy = Integer.valueOf(2);
            bArr = zzbuz.zzb(zzbs2);
        } else {
            throw new GeneralSecurityException();
        }
        return zzbu.zza(bArr, true);
    }

    private static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector<byte[]> vector = new Vector<>();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    private static zzbl zzc(long j) {
        zzbl zzbl = new zzbl();
        zzbl.zzeo = Long.valueOf(4096);
        return zzbl;
    }

    private static byte[] zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bArr2;
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = zzbuz.zzb(zzc(4096));
        }
        if (bArr.length < i) {
            byte[] bArr3 = new byte[(i - bArr.length)];
            new SecureRandom().nextBytes(bArr3);
            bArr2 = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr3).array();
        } else {
            bArr2 = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            bArr2 = ByteBuffer.allocate(256).put(zzb(bArr2)).put(bArr2).array();
        }
        byte[] bArr4 = new byte[256];
        for (zzcb zza : new zzbz().zzqm) {
            zza.zza(bArr2, bArr4);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzbpk(str.getBytes("UTF-8")).zzq(bArr4);
        }
        return bArr4;
    }

    public static byte[] zzb(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zziw) {
            MessageDigest zzx = zzx();
            if (zzx != null) {
                zzx.reset();
                zzx.update(bArr);
                digest = zziv.digest();
            } else {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
        }
        return digest;
    }
}
