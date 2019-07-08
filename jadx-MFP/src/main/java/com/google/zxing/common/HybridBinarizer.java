package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    private static int cap(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width < 40 || height < 40) {
            this.matrix = super.getBlackMatrix();
        } else {
            byte[] matrix2 = luminanceSource.getMatrix();
            int i = width >> 3;
            int i2 = (width & 7) != 0 ? i + 1 : i;
            int i3 = height >> 3;
            int i4 = (height & 7) != 0 ? i3 + 1 : i3;
            int[][] calculateBlackPoints = calculateBlackPoints(matrix2, i2, i4, width, height);
            BitMatrix bitMatrix2 = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix2, i2, i4, width, height, calculateBlackPoints, bitMatrix2);
            this.matrix = bitMatrix2;
        }
        return this.matrix;
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        int i5 = i;
        int i6 = i2;
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i7 << 3;
            int i9 = i4 - 8;
            if (i8 > i9) {
                i8 = i9;
            }
            for (int i10 = 0; i10 < i5; i10++) {
                int i11 = i10 << 3;
                int i12 = i3 - 8;
                if (i11 <= i12) {
                    i12 = i11;
                }
                int cap = cap(i10, 2, i5 - 3);
                int cap2 = cap(i7, 2, i6 - 3);
                int i13 = 0;
                for (int i14 = -2; i14 <= 2; i14++) {
                    int[] iArr2 = iArr[cap2 + i14];
                    i13 += iArr2[cap - 2] + iArr2[cap - 1] + iArr2[cap] + iArr2[cap + 1] + iArr2[cap + 2];
                }
                thresholdBlock(bArr, i12, i8, i13 / 25, i3, bitMatrix);
            }
        }
    }

    private static void thresholdBlock(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bitMatrix.set(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{i6, i5});
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i7 << 3;
            int i9 = i4 - 8;
            if (i8 > i9) {
                i8 = i9;
            }
            for (int i10 = 0; i10 < i5; i10++) {
                int i11 = i10 << 3;
                int i12 = i3 - 8;
                if (i11 > i12) {
                    i11 = i12;
                }
                int i13 = (i8 * i3) + i11;
                int i14 = 0;
                int i15 = 0;
                byte b = 0;
                byte b2 = 255;
                while (i14 < 8) {
                    int i16 = i15;
                    for (int i17 = 0; i17 < 8; i17++) {
                        byte b3 = bArr[i13 + i17] & 255;
                        i16 += b3;
                        if (b3 < b2) {
                            b2 = b3;
                        }
                        if (b3 > b) {
                            b = b3;
                        }
                    }
                    if (b - b2 <= 24) {
                        i15 = i16;
                        i14++;
                        i13 += i3;
                    }
                    while (true) {
                        i14++;
                        i13 += i3;
                        if (i14 >= 8) {
                            break;
                        }
                        for (int i18 = 0; i18 < 8; i18++) {
                            i16 += bArr[i13 + i18] & 255;
                        }
                    }
                    i15 = i16;
                    i14++;
                    i13 += i3;
                }
                int i19 = i15 >> 6;
                if (b - b2 <= 24) {
                    i19 = b2 / 2;
                    if (i7 > 0 && i10 > 0) {
                        int i20 = i7 - 1;
                        int i21 = i10 - 1;
                        int i22 = ((iArr[i20][i10] + (iArr[i7][i21] * 2)) + iArr[i20][i21]) / 4;
                        if (b2 < i22) {
                            i19 = i22;
                        }
                    }
                }
                iArr[i7][i10] = i19;
            }
        }
        return iArr;
    }
}
