package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

final class zzxl {
    private static final zzxn zzcdi;

    /* access modifiers changed from: private */
    public static int zzbz(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int zzc(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean zzl(byte[] bArr) {
        return zzcdi.zzf(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: private */
    public static int zzq(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static boolean zzf(byte[] bArr, int i, int i2) {
        return zzcdi.zzf(bArr, i, i2);
    }

    /* access modifiers changed from: private */
    public static int zzg(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return zzbz(b);
            case 1:
                return zzq(b, bArr[i]);
            case 2:
                return zzc(b, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new zzxp(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        long j = ((long) i3) + 4294967296L;
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(j);
        throw new IllegalArgumentException(sb.toString());
    }

    static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zzcdi.zzb(charSequence, bArr, i, i2);
    }

    static String zzh(byte[] bArr, int i, int i2) throws zzuv {
        return zzcdi.zzh(bArr, i, i2);
    }

    static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzxn zzxn = zzcdi;
        if (byteBuffer.hasArray()) {
            int arrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
        } else if (byteBuffer.isDirect()) {
            zzxn.zzb(charSequence, byteBuffer);
        } else {
            zzxn.zzc(charSequence, byteBuffer);
        }
    }

    static {
        zzxn zzxn;
        if (!(zzxj.zzyo() && zzxj.zzyp()) || zztb.zzub()) {
            zzxn = new zzxo();
        } else {
            zzxn = new zzxq();
        }
        zzcdi = zzxn;
    }
}
