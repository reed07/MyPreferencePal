package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class zzpu {
    public static final byte[] zzbhi = {0, 0, 0, 1};
    private static final float[] zzbhm = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object zzbhn = new Object();
    private static int[] zzbho = new int[10];

    public static int zzb(byte[] bArr, int i) {
        int i2;
        synchronized (zzbhn) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                while (true) {
                    if (i3 < i - 2) {
                        if (bArr[i3] == 0 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 3) {
                            break;
                        }
                        i3++;
                    } else {
                        i3 = i;
                        break;
                    }
                }
                if (i3 < i) {
                    if (zzbho.length <= i4) {
                        zzbho = Arrays.copyOf(zzbho, zzbho.length << 1);
                    }
                    int i5 = i4 + 1;
                    zzbho[i4] = i3;
                    i3 += 3;
                    i4 = i5;
                }
            }
            i2 = i - i4;
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = zzbho[i8] - i7;
                System.arraycopy(bArr, i7, bArr, i6, i9);
                int i10 = i6 + i9;
                int i11 = i10 + 1;
                bArr[i10] = 0;
                i6 = i11 + 1;
                bArr[i11] = 0;
                i7 += i9 + 3;
            }
            System.arraycopy(bArr, i7, bArr, i6, i2 - i6);
        }
        return i2;
    }

    public static void zzk(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                byte b = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (b == 1 && (byteBuffer.get(i3) & Ascii.US) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (b == 0) {
                    i2++;
                }
                if (b != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean zza(String str, byte b) {
        if ((!MimeTypes.VIDEO_H264.equals(str) || (b & Ascii.US) != 6) && (!MimeTypes.VIDEO_H265.equals(str) || ((b & 126) >> 1) != 39)) {
            return false;
        }
        return true;
    }

    public static zzpv zzd(byte[] bArr, int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        int i4;
        int i5;
        int i6;
        float f;
        int i7;
        int i8;
        zzpy zzpy = new zzpy(bArr, i, i2);
        zzpy.zzbn(8);
        int zzbj = zzpy.zzbj(8);
        zzpy.zzbn(16);
        int zzhk = zzpy.zzhk();
        int i9 = 1;
        if (zzbj == 100 || zzbj == 110 || zzbj == 122 || zzbj == 244 || zzbj == 44 || zzbj == 83 || zzbj == 86 || zzbj == 118 || zzbj == 128 || zzbj == 138) {
            i3 = zzpy.zzhk();
            boolean zzhj = i3 == 3 ? zzpy.zzhj() : false;
            zzpy.zzhk();
            zzpy.zzhk();
            zzpy.zzbn(1);
            if (zzpy.zzhj()) {
                int i10 = i3 != 3 ? 8 : 12;
                int i11 = 0;
                while (i11 < i10) {
                    if (zzpy.zzhj()) {
                        int i12 = i11 < 6 ? 16 : 64;
                        int i13 = 8;
                        int i14 = 8;
                        for (int i15 = 0; i15 < i12; i15++) {
                            if (i13 != 0) {
                                i13 = ((zzpy.zzhl() + i14) + 256) % 256;
                            }
                            if (i13 != 0) {
                                i14 = i13;
                            }
                        }
                    }
                    i11++;
                }
            }
            z = zzhj;
        } else {
            i3 = 1;
            z = false;
        }
        int zzhk2 = zzpy.zzhk() + 4;
        int zzhk3 = zzpy.zzhk();
        if (zzhk3 == 0) {
            i4 = zzpy.zzhk() + 4;
            z2 = false;
        } else if (zzhk3 == 1) {
            boolean zzhj2 = zzpy.zzhj();
            zzpy.zzhl();
            zzpy.zzhl();
            long zzhk4 = (long) zzpy.zzhk();
            for (int i16 = 0; ((long) i16) < zzhk4; i16++) {
                zzpy.zzhk();
            }
            z2 = zzhj2;
            i4 = 0;
        } else {
            i4 = 0;
            z2 = false;
        }
        zzpy.zzhk();
        zzpy.zzbn(1);
        int zzhk5 = zzpy.zzhk() + 1;
        int zzhk6 = zzpy.zzhk() + 1;
        boolean zzhj3 = zzpy.zzhj();
        int i17 = (true - (zzhj3 ? 1 : 0)) * zzhk6;
        if (!zzhj3) {
            zzpy.zzbn(1);
        }
        zzpy.zzbn(1);
        int i18 = zzhk5 << 4;
        int i19 = i17 << 4;
        if (zzpy.zzhj()) {
            int zzhk7 = zzpy.zzhk();
            int zzhk8 = zzpy.zzhk();
            int zzhk9 = zzpy.zzhk();
            int zzhk10 = zzpy.zzhk();
            if (i3 == 0) {
                i7 = true - zzhj3;
                i8 = 1;
            } else {
                i8 = i3 == 3 ? 1 : 2;
                if (i3 == 1) {
                    i9 = 2;
                }
                i7 = (true - zzhj3) * i9;
            }
            i6 = i18 - ((zzhk7 + zzhk8) * i8);
            i5 = i19 - ((zzhk9 + zzhk10) * i7);
        } else {
            i6 = i18;
            i5 = i19;
        }
        float f2 = 1.0f;
        if (zzpy.zzhj() && zzpy.zzhj()) {
            int zzbj2 = zzpy.zzbj(8);
            if (zzbj2 == 255) {
                int zzbj3 = zzpy.zzbj(16);
                int zzbj4 = zzpy.zzbj(16);
                if (!(zzbj3 == 0 || zzbj4 == 0)) {
                    f2 = ((float) zzbj3) / ((float) zzbj4);
                }
                f = f2;
            } else {
                float[] fArr = zzbhm;
                if (zzbj2 < fArr.length) {
                    f = fArr[zzbj2];
                } else {
                    StringBuilder sb = new StringBuilder(46);
                    sb.append("Unexpected aspect_ratio_idc value: ");
                    sb.append(zzbj2);
                    Log.w("NalUnitUtil", sb.toString());
                }
            }
            zzpv zzpv = new zzpv(zzhk, i6, i5, f, z, zzhj3, zzhk2, zzhk3, i4, z2);
            return zzpv;
        }
        f = 1.0f;
        zzpv zzpv2 = new zzpv(zzhk, i6, i5, f, z, zzhj3, zzhk2, zzhk3, i4, z2);
        return zzpv2;
    }
}
