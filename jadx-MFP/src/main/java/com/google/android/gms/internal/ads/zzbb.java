package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public final class zzbb {
    public static int zza(byte b) {
        return b < 0 ? b + 256 : b;
    }

    public static long zza(ByteBuffer byteBuffer) {
        long j = (long) byteBuffer.getInt();
        return j < 0 ? j + 4294967296L : j;
    }

    public static int zzb(ByteBuffer byteBuffer) {
        return (zza(byteBuffer.get()) << 8) + 0 + zza(byteBuffer.get());
    }

    public static long zzc(ByteBuffer byteBuffer) {
        long zza = (zza(byteBuffer) << 32) + 0;
        if (zza >= 0) {
            return zza + zza(byteBuffer);
        }
        throw new RuntimeException("I don't know how to deal with UInt64! long is not sufficient and I don't want to use BigInt");
    }

    public static double zzd(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        return ((double) ((((0 | ((bArr[0] << Ascii.CAN) & -16777216)) | ((bArr[1] << Ascii.DLE) & 16711680)) | ((bArr[2] << 8) & 65280)) | (bArr[3] & 255))) / 65536.0d;
    }

    public static double zze(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        return ((double) ((((0 | ((bArr[0] << Ascii.CAN) & -16777216)) | ((bArr[1] << Ascii.DLE) & 16711680)) | ((bArr[2] << 8) & 65280)) | (bArr[3] & 255))) / 1.073741824E9d;
    }

    public static String zzf(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
