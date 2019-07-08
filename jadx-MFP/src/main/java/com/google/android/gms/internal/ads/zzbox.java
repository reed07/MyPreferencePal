package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.util.Arrays;

final class zzbox {
    private static long zzf(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE))) & 4294967295L;
    }

    private static long zzg(byte[] bArr, int i, int i2) {
        return (zzf(bArr, i) >> i2) & 67108863;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }

    static byte[] zze(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            long zzg = zzg(bArr3, 0, 0) & 67108863;
            int i = 2;
            int i2 = 3;
            long zzg2 = zzg(bArr3, 3, 2) & 67108611;
            long zzg3 = zzg(bArr3, 6, 4) & 67092735;
            long zzg4 = zzg(bArr3, 9, 6) & 66076671;
            long zzg5 = zzg(bArr3, 12, 8) & 1048575;
            long j = zzg2 * 5;
            long j2 = zzg3 * 5;
            long j3 = zzg4 * 5;
            long j4 = zzg5 * 5;
            byte[] bArr5 = new byte[17];
            long j5 = 0;
            long j6 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            int i3 = 0;
            while (i3 < bArr4.length) {
                int min = Math.min(16, bArr4.length - i3);
                System.arraycopy(bArr4, i3, bArr5, 0, min);
                bArr5[min] = 1;
                if (min != 16) {
                    Arrays.fill(bArr5, min + 1, 17, 0);
                }
                long zzg6 = j9 + zzg(bArr5, 0, 0);
                long zzg7 = j5 + zzg(bArr5, i2, i);
                long zzg8 = j6 + zzg(bArr5, 6, 4);
                long zzg9 = j7 + zzg(bArr5, 9, 6);
                long zzg10 = j8 + (zzg(bArr5, 12, 8) | ((long) (bArr5[16] << Ascii.CAN)));
                long j10 = (zzg6 * zzg) + (zzg7 * j4) + (zzg8 * j3) + (zzg9 * j2) + (zzg10 * j);
                long j11 = (zzg6 * zzg3) + (zzg7 * zzg2) + (zzg8 * zzg) + (zzg9 * j4) + (zzg10 * j3);
                long j12 = (zzg6 * zzg2) + (zzg7 * zzg) + (zzg8 * j4) + (zzg9 * j3) + (zzg10 * j2) + (j10 >> 26);
                long j13 = j11 + (j12 >> 26);
                long j14 = (zzg6 * zzg4) + (zzg7 * zzg3) + (zzg8 * zzg2) + (zzg9 * zzg) + (zzg10 * j4) + (j13 >> 26);
                long j15 = (zzg6 * zzg5) + (zzg7 * zzg4) + (zzg8 * zzg3) + (zzg9 * zzg2) + (zzg10 * zzg) + (j14 >> 26);
                long j16 = (j10 & 67108863) + ((j15 >> 26) * 5);
                j5 = (j12 & 67108863) + (j16 >> 26);
                i3 += 16;
                j6 = j13 & 67108863;
                j7 = j14 & 67108863;
                j8 = j15 & 67108863;
                j9 = j16 & 67108863;
                i = 2;
                i2 = 3;
            }
            long j17 = j6 + (j5 >> 26);
            long j18 = j17 & 67108863;
            long j19 = j7 + (j17 >> 26);
            long j20 = j19 & 67108863;
            long j21 = j8 + (j19 >> 26);
            long j22 = j21 & 67108863;
            long j23 = j9 + ((j21 >> 26) * 5);
            long j24 = j23 & 67108863;
            long j25 = (j5 & 67108863) + (j23 >> 26);
            long j26 = j24 + 5;
            long j27 = j26 & 67108863;
            long j28 = (j26 >> 26) + j25;
            long j29 = j18 + (j28 >> 26);
            long j30 = j29 >> 26;
            long j31 = j29 & 67108863;
            long j32 = j20 + j30;
            long j33 = j32 & 67108863;
            long j34 = (j22 + (j32 >> 26)) - 67108864;
            long j35 = j28 & 67108863;
            long j36 = j34 >> 63;
            long j37 = j24 & j36;
            long j38 = j25 & j36;
            long j39 = j18 & j36;
            long j40 = j20 & j36;
            long j41 = j22 & j36;
            long j42 = ~j36;
            long j43 = j37 | (j27 & j42);
            long j44 = j38 | (j35 & j42);
            long j45 = j39 | (j31 & j42);
            long j46 = j40 | (j33 & j42);
            long j47 = ((j44 >> 6) | (j45 << 20)) & 4294967295L;
            long j48 = ((j45 >> 12) | (j46 << 14)) & 4294967295L;
            long j49 = ((((j34 & j42) | j41) << 8) | (j46 >> 18)) & 4294967295L;
            long zzf = (((j44 << 26) | j43) & 4294967295L) + zzf(bArr3, 16);
            long j50 = zzf & 4294967295L;
            long zzf2 = j47 + zzf(bArr3, 20) + (zzf >> 32);
            long j51 = zzf2 & 4294967295L;
            long zzf3 = j48 + zzf(bArr3, 24) + (zzf2 >> 32);
            long j52 = zzf3 & 4294967295L;
            long zzf4 = (j49 + zzf(bArr3, 28) + (zzf3 >> 32)) & 4294967295L;
            byte[] bArr6 = new byte[16];
            zza(bArr6, j50, 0);
            zza(bArr6, j51, 4);
            zza(bArr6, j52, 8);
            zza(bArr6, zzf4, 12);
            return bArr6;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }
}
