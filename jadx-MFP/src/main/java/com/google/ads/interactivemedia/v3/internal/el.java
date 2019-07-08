package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class el {
    private static final int[] a = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] b = {-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] c = {64, 112, 128, 192, 224, 256, 384, 448, 512, AmazonAdTask.DEFAULT_AD_WIDTH, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static boolean a(int i) {
        return i == 2147385345 || i == -25230976 || i == 536864768 || i == -14745368;
    }

    public static bs a(byte[] bArr, String str, String str2, fa faVar) {
        us usVar;
        int i;
        byte[] bArr2 = bArr;
        int i2 = 0;
        if (bArr2[0] == Byte.MAX_VALUE) {
            usVar = new us(bArr2);
        } else {
            byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length);
            if (copyOf[0] == -2 || copyOf[0] == -1) {
                for (int i3 = 0; i3 < copyOf.length - 1; i3 += 2) {
                    byte b2 = copyOf[i3];
                    int i4 = i3 + 1;
                    copyOf[i3] = copyOf[i4];
                    copyOf[i4] = b2;
                }
            }
            usVar = new us(copyOf);
            if (copyOf[0] == 31) {
                us usVar2 = new us(copyOf);
                while (usVar2.a() >= 16) {
                    usVar2.b(2);
                    usVar.a(usVar2.c(14), 14);
                }
            }
            usVar.a(copyOf, copyOf.length);
        }
        usVar.b(60);
        int i5 = a[usVar.c(6)];
        int i6 = b[usVar.c(4)];
        int c2 = usVar.c(5);
        int[] iArr = c;
        if (c2 >= iArr.length) {
            i = -1;
        } else {
            i = (iArr[c2] * 1000) / 2;
        }
        usVar.b(10);
        if (usVar.c(2) > 0) {
            i2 = 1;
        }
        return bs.a(str, MimeTypes.AUDIO_DTS, null, i, -1, i5 + i2, i6, null, null, 0, str2);
    }

    public static int a(byte[] bArr) {
        int i;
        byte b2 = bArr[0];
        if (b2 != 31) {
            switch (b2) {
                case -2:
                    i = ((bArr[4] & 252) >> 2) | ((bArr[5] & 1) << 6);
                    break;
                case -1:
                    i = ((bArr[7] & 60) >> 2) | ((bArr[4] & 7) << 4);
                    break;
                default:
                    i = ((bArr[5] & 252) >> 2) | ((bArr[4] & 1) << 6);
                    break;
            }
        } else {
            i = ((bArr[6] & 60) >> 2) | ((bArr[5] & 7) << 4);
        }
        return (i + 1) << 5;
    }

    public static int a(ByteBuffer byteBuffer) {
        int i;
        int position = byteBuffer.position();
        byte b2 = byteBuffer.get(position);
        if (b2 != 31) {
            switch (b2) {
                case -2:
                    i = ((byteBuffer.get(position + 4) & 252) >> 2) | ((byteBuffer.get(position + 5) & 1) << 6);
                    break;
                case -1:
                    i = ((byteBuffer.get(position + 7) & 60) >> 2) | ((byteBuffer.get(position + 4) & 7) << 4);
                    break;
                default:
                    i = ((byteBuffer.get(position + 5) & 252) >> 2) | ((byteBuffer.get(position + 4) & 1) << 6);
                    break;
            }
        } else {
            i = ((byteBuffer.get(position + 6) & 60) >> 2) | ((byteBuffer.get(position + 5) & 7) << 4);
        }
        return (i + 1) << 5;
    }

    public static int b(byte[] bArr) {
        int i;
        boolean z = false;
        byte b2 = bArr[0];
        if (b2 != 31) {
            switch (b2) {
                case -2:
                    i = (((bArr[6] & 240) >> 4) | ((bArr[4] & 3) << Ascii.FF) | ((bArr[7] & 255) << 4)) + 1;
                    break;
                case -1:
                    i = (((bArr[9] & 60) >> 2) | ((bArr[7] & 3) << Ascii.FF) | ((bArr[6] & 255) << 4)) + 1;
                    z = true;
                    break;
                default:
                    i = (((bArr[7] & 240) >> 4) | ((bArr[5] & 3) << Ascii.FF) | ((bArr[6] & 255) << 4)) + 1;
                    break;
            }
        } else {
            i = (((bArr[8] & 60) >> 2) | ((bArr[6] & 3) << Ascii.FF) | ((bArr[7] & 255) << 4)) + 1;
            z = true;
        }
        return z ? (i << 4) / 14 : i;
    }
}
