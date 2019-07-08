package com.facebook.ads.internal.w.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.concurrent.Callable;

class d implements a {
    private static final short[] a = {512, 512, 456, 512, 328, 456, 335, 512, 405, 328, 271, 456, 388, 335, 292, 512, 454, 405, 364, 328, 298, 271, 496, 456, 420, 388, 360, 335, 312, 292, 273, 512, 482, 454, 428, 405, 383, 364, 345, 328, 312, 298, 284, 271, 259, 496, 475, 456, 437, 420, 404, 388, 374, 360, 347, 335, 323, 312, 302, 292, 282, 273, 265, 512, 497, 482, 468, 454, 441, 428, 417, 405, 394, 383, 373, 364, 354, 345, 337, 328, 320, 312, 305, 298, 291, 284, 278, 271, 265, 259, 507, 496, 485, 475, 465, 456, 446, 437, 428, 420, 412, 404, 396, 388, 381, 374, 367, 360, 354, 347, 341, 335, 329, 323, 318, 312, 307, 302, 297, 292, 287, 282, 278, 273, 269, 265, 261, 512, 505, 497, 489, 482, 475, 468, 461, 454, 447, 441, 435, 428, 422, 417, 411, 405, 399, 394, 389, 383, 378, 373, 368, 364, 359, 354, 350, 345, 341, 337, 332, 328, 324, 320, 316, 312, 309, 305, 301, 298, 294, 291, 287, 284, 281, 278, 274, 271, 268, 265, 262, 259, 257, 507, 501, 496, 491, 485, 480, 475, 470, 465, 460, 456, 451, 446, 442, 437, 433, 428, 424, 420, 416, 412, 408, 404, 400, 396, 392, 388, 385, 381, 377, 374, 370, 367, 363, 360, 357, 354, 350, 347, 344, 341, 338, 335, 332, 329, 326, 323, 320, 318, 315, 312, 310, 307, 304, 302, 299, 297, 294, 292, 289, 287, 285, 282, 280, 278, 275, 273, 271, 269, 267, 265, 263, 261, 259};
    private static final byte[] b = {9, Ascii.VT, Ascii.FF, Ascii.CR, Ascii.CR, Ascii.SO, Ascii.SO, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.DLE, 17, 17, 17, 17, 17, 17, 17, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.DC4, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.NAK, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN, Ascii.CAN};

    private static class a implements Callable<Void> {
        private final int[] a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;

        public a(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = iArr;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
        }

        /* renamed from: a */
        public Void call() {
            d.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
            return null;
        }
    }

    d() {
    }

    static /* synthetic */ void a(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i6;
        int i11 = i7 - 1;
        int i12 = i8 - 1;
        int i13 = (i9 * 2) + 1;
        short s = a[i9];
        byte b2 = b[i9];
        int[] iArr2 = new int[i13];
        if (i10 == 1) {
            int i14 = (i5 * i8) / i4;
            int i15 = ((i5 + 1) * i8) / i4;
            while (i14 < i15) {
                int i16 = i7 * i14;
                int i17 = 0;
                long j = 0;
                long j2 = 0;
                long j3 = 0;
                long j4 = 0;
                long j5 = 0;
                long j6 = 0;
                while (i17 <= i9) {
                    iArr2[i17] = iArr[i16];
                    i17++;
                    j += (long) (((iArr[i16] >>> 16) & 255) * i17);
                    j2 += (long) (((iArr[i16] >>> 8) & 255) * i17);
                    j3 += (long) ((iArr[i16] & 255) * i17);
                    j4 += (long) ((iArr[i16] >>> 16) & 255);
                    j5 += (long) ((iArr[i16] >>> 8) & 255);
                    j6 += (long) (iArr[i16] & 255);
                }
                int i18 = i16;
                long j7 = j;
                long j8 = j2;
                long j9 = 0;
                long j10 = 0;
                long j11 = 0;
                for (int i19 = 1; i19 <= i9; i19++) {
                    if (i19 <= i11) {
                        i18++;
                    }
                    iArr2[i19 + i9] = iArr[i18];
                    int i20 = (i9 + 1) - i19;
                    j7 += (long) (((iArr[i18] >>> 16) & 255) * i20);
                    j8 += (long) (((iArr[i18] >>> 8) & 255) * i20);
                    j3 += (long) ((iArr[i18] & 255) * i20);
                    j9 += (long) ((iArr[i18] >>> 16) & 255);
                    j10 += (long) ((iArr[i18] >>> 8) & 255);
                    j11 += (long) (iArr[i18] & 255);
                }
                int i21 = i9 > i11 ? i11 : i9;
                int i22 = i21;
                int i23 = i21 + i16;
                int i24 = i16;
                int i25 = i9;
                int i26 = 0;
                while (i26 < i7) {
                    int i27 = i15;
                    int i28 = i26;
                    long j12 = j9;
                    long j13 = (long) s;
                    iArr[i24] = (int) (((long) (iArr[i24] & -16777216)) | ((((j7 * j13) >>> b2) & 255) << 16) | ((((j8 * j13) >>> b2) & 255) << 8) | (((j13 * j3) >>> b2) & 255));
                    i24++;
                    long j14 = j7 - j4;
                    long j15 = j8 - j5;
                    long j16 = j3 - j6;
                    int i29 = (i25 + i13) - i9;
                    if (i29 >= i13) {
                        i29 -= i13;
                    }
                    long j17 = j4 - ((long) ((iArr2[i29] >>> 16) & 255));
                    long j18 = j5 - ((long) ((iArr2[i29] >>> 8) & 255));
                    long j19 = j6 - ((long) (iArr2[i29] & 255));
                    if (i22 < i11) {
                        i23++;
                        i22++;
                    }
                    iArr2[i29] = iArr[i23];
                    long j20 = j12 + ((long) ((iArr[i23] >>> 16) & 255));
                    long j21 = j10 + ((long) ((iArr[i23] >>> 8) & 255));
                    long j22 = j11 + ((long) (iArr[i23] & 255));
                    j7 = j14 + j20;
                    j8 = j15 + j21;
                    j3 = j16 + j22;
                    i25++;
                    if (i25 >= i13) {
                        i25 = 0;
                    }
                    j4 = j17 + ((long) ((iArr2[i25] >>> 16) & 255));
                    j5 = j18 + ((long) ((iArr2[i25] >>> 8) & 255));
                    j6 = j19 + ((long) (iArr2[i25] & 255));
                    j9 = j20 - ((long) ((iArr2[i25] >>> 16) & 255));
                    j10 = j21 - ((long) ((iArr2[i25] >>> 8) & 255));
                    j11 = j22 - ((long) (iArr2[i25] & 255));
                    i26 = i28 + 1;
                    i15 = i27;
                    i7 = i;
                }
                int i30 = i15;
                i14++;
                i7 = i;
            }
        } else if (i10 == 2) {
            int i31 = (i5 * i) / i4;
            int i32 = ((i5 + 1) * i) / i4;
            while (i31 < i32) {
                int i33 = 0;
                long j23 = 0;
                long j24 = 0;
                long j25 = 0;
                long j26 = 0;
                long j27 = 0;
                long j28 = 0;
                while (i33 <= i9) {
                    iArr2[i33] = iArr[i31];
                    i33++;
                    j23 += (long) (((iArr[i31] >>> 16) & 255) * i33);
                    j24 += (long) (((iArr[i31] >>> 8) & 255) * i33);
                    j25 += (long) ((iArr[i31] & 255) * i33);
                    j26 += (long) ((iArr[i31] >>> 16) & 255);
                    j27 += (long) ((iArr[i31] >>> 8) & 255);
                    j28 += (long) (iArr[i31] & 255);
                    i32 = i32;
                }
                int i34 = i32;
                int i35 = i31;
                long j29 = j23;
                long j30 = j24;
                int i36 = 1;
                long j31 = 0;
                long j32 = 0;
                long j33 = 0;
                while (i36 <= i9) {
                    if (i36 <= i12) {
                        i35 += i;
                    }
                    iArr2[i36 + i9] = iArr[i35];
                    int i37 = (i9 + 1) - i36;
                    j29 += (long) (((iArr[i35] >>> 16) & 255) * i37);
                    j30 += (long) (((iArr[i35] >>> 8) & 255) * i37);
                    j25 += (long) ((iArr[i35] & 255) * i37);
                    j31 += (long) ((iArr[i35] >>> 16) & 255);
                    j32 += (long) ((iArr[i35] >>> 8) & 255);
                    j33 += (long) (iArr[i35] & 255);
                    i36++;
                    i13 = i13;
                }
                int i38 = i13;
                int i39 = i9 > i12 ? i12 : i9;
                int i40 = i9;
                int i41 = i39;
                long j34 = j32;
                int i42 = 0;
                long j35 = j31;
                int i43 = (i39 * i) + i31;
                int i44 = i31;
                while (i42 < i8) {
                    int i45 = i31;
                    long j36 = j34;
                    long j37 = (long) s;
                    iArr[i44] = (int) (((long) (iArr[i44] & -16777216)) | ((((j29 * j37) >>> b2) & 255) << 16) | ((((j30 * j37) >>> b2) & 255) << 8) | (((j37 * j25) >>> b2) & 255));
                    int i46 = i44 + i;
                    long j38 = j29 - j26;
                    long j39 = j30 - j27;
                    long j40 = j25 - j28;
                    int i47 = (i40 + i38) - i9;
                    int i48 = i38;
                    if (i47 >= i48) {
                        i47 -= i48;
                    }
                    long j41 = j26 - ((long) ((iArr2[i47] >>> 16) & 255));
                    long j42 = j27 - ((long) ((iArr2[i47] >>> 8) & 255));
                    long j43 = j28 - ((long) (iArr2[i47] & 255));
                    if (i41 < i12) {
                        i43 += i;
                        i41++;
                    }
                    iArr2[i47] = iArr[i43];
                    long j44 = j35 + ((long) ((iArr[i43] >>> 16) & 255));
                    long j45 = j36 + ((long) ((iArr[i43] >>> 8) & 255));
                    int i49 = i46;
                    int i50 = i12;
                    long j46 = j33 + ((long) (iArr[i43] & 255));
                    j29 = j38 + j44;
                    j30 = j39 + j45;
                    j25 = j40 + j46;
                    i40++;
                    if (i40 >= i48) {
                        i40 = 0;
                    }
                    j26 = j41 + ((long) ((iArr2[i40] >>> 16) & 255));
                    j27 = j42 + ((long) ((iArr2[i40] >>> 8) & 255));
                    j28 = j43 + ((long) (iArr2[i40] & 255));
                    j35 = j44 - ((long) ((iArr2[i40] >>> 16) & 255));
                    j34 = j45 - ((long) ((iArr2[i40] >>> 8) & 255));
                    j33 = j46 - ((long) (iArr2[i40] & 255));
                    i42++;
                    i44 = i49;
                    i12 = i50;
                    i31 = i45;
                    i38 = i48;
                    i8 = i2;
                }
                int i51 = i38;
                int i52 = i12;
                i31++;
                i32 = i34;
                i13 = i51;
                i8 = i2;
            }
        }
    }

    public Bitmap a(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        try {
            int[] iArr = new int[(width * height)];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i = f.a;
            ArrayList arrayList = new ArrayList(i);
            ArrayList arrayList2 = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = (int) f;
                int[] iArr2 = iArr;
                int i4 = width;
                int i5 = height;
                int i6 = i3;
                int i7 = i3;
                int i8 = i;
                int i9 = i2;
                a aVar = r0;
                a aVar2 = new a(iArr2, i4, i5, i6, i8, i9, 1);
                arrayList.add(aVar);
                a aVar3 = new a(iArr2, i4, i5, i7, i8, i9, 2);
                arrayList2.add(aVar3);
            }
            try {
                f.b.invokeAll(arrayList);
                try {
                    f.b.invokeAll(arrayList2);
                    try {
                        return Bitmap.createBitmap(iArr, width, height, Config.ARGB_8888);
                    } catch (OutOfMemoryError unused) {
                        return null;
                    }
                } catch (InterruptedException unused2) {
                    return null;
                }
            } catch (InterruptedException unused3) {
                return null;
            }
        } catch (OutOfMemoryError unused4) {
            return null;
        }
    }
}
