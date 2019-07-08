package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
public final class fw {
    private static final String[] h = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] i = {44100, 48000, 32000};
    private static final int[] j = {32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};
    private static final int[] k = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};
    private static final int[] l = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};
    private static final int[] m = {32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};
    private static final int[] n = {8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000};
    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public static int a(int i2) {
        if ((i2 & -2097152) != -2097152) {
            return -1;
        }
        int i3 = (i2 >>> 19) & 3;
        if (i3 == 1) {
            return -1;
        }
        int i4 = (i2 >>> 17) & 3;
        if (i4 == 0) {
            return -1;
        }
        int i5 = (i2 >>> 12) & 15;
        if (i5 == 0 || i5 == 15) {
            return -1;
        }
        int i6 = (i2 >>> 10) & 3;
        if (i6 == 3) {
            return -1;
        }
        int i7 = i[i6];
        if (i3 == 2) {
            i7 /= 2;
        } else if (i3 == 0) {
            i7 /= 4;
        }
        int i8 = (i2 >>> 9) & 1;
        if (i4 == 3) {
            return ((((i3 == 3 ? j[i5 - 1] : k[i5 - 1]) * 12) / i7) + i8) << 2;
        }
        int i9 = i3 == 3 ? i4 == 2 ? l[i5 - 1] : m[i5 - 1] : n[i5 - 1];
        int i10 = 144;
        if (i3 == 3) {
            return ((i9 * 144) / i7) + i8;
        }
        if (i4 == 1) {
            i10 = 72;
        }
        return ((i10 * i9) / i7) + i8;
    }

    public static boolean a(int i2, fw fwVar) {
        int i3;
        int i4;
        int i5;
        int i6;
        if ((i2 & -2097152) != -2097152) {
            return false;
        }
        int i7 = (i2 >>> 19) & 3;
        if (i7 == 1) {
            return false;
        }
        int i8 = (i2 >>> 17) & 3;
        if (i8 == 0) {
            return false;
        }
        int i9 = (i2 >>> 12) & 15;
        if (i9 == 0 || i9 == 15) {
            return false;
        }
        int i10 = (i2 >>> 10) & 3;
        if (i10 == 3) {
            return false;
        }
        int i11 = i[i10];
        int i12 = 2;
        if (i7 == 2) {
            i11 /= 2;
        } else if (i7 == 0) {
            i11 /= 4;
        }
        int i13 = (i2 >>> 9) & 1;
        if (i8 == 3) {
            i4 = ((((i7 == 3 ? j[i9 - 1] : k[i9 - 1]) * 12) / i11) + i13) << 2;
            i3 = 384;
        } else {
            int i14 = 1152;
            if (i7 == 3) {
                i6 = i8 == 2 ? l[i9 - 1] : m[i9 - 1];
            } else {
                i6 = n[i9 - 1];
                if (i8 == 1) {
                    i14 = 576;
                }
                if (i8 == 1) {
                    i5 = 72;
                    i4 = ((i5 * i6) / i11) + i13;
                    i3 = i14;
                }
            }
            i5 = 144;
            i4 = ((i5 * i6) / i11) + i13;
            i3 = i14;
        }
        int i15 = ((i4 * 8) * i11) / i3;
        String str = h[3 - i8];
        if (((i2 >> 6) & 3) == 3) {
            i12 = 1;
        }
        fwVar.a = i7;
        fwVar.b = str;
        fwVar.c = i4;
        fwVar.d = i11;
        fwVar.e = i12;
        fwVar.f = i15;
        fwVar.g = i3;
        return true;
    }
}
