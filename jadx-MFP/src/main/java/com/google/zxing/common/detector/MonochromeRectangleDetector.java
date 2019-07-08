package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

@Deprecated
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height / 2;
        int i2 = width / 2;
        int max = Math.max(1, height / 256);
        int i3 = -max;
        int i4 = i2 / 2;
        int i5 = i2;
        int i6 = width;
        int i7 = i;
        int i8 = i3;
        int max2 = Math.max(1, width / 256);
        int i9 = height;
        int i10 = max;
        int i11 = max2;
        int i12 = -i11;
        int y = ((int) findCornerFromCenter(i5, 0, 0, i6, i7, i3, 0, i9, i4).getY()) - 1;
        int i13 = i11;
        int i14 = i / 2;
        ResultPoint findCornerFromCenter = findCornerFromCenter(i5, i12, 0, i6, i7, 0, y, i9, i14);
        int x = ((int) findCornerFromCenter.getX()) - 1;
        ResultPoint findCornerFromCenter2 = findCornerFromCenter(i5, i13, x, i6, i7, 0, y, i9, i14);
        int x2 = ((int) findCornerFromCenter2.getX()) + 1;
        ResultPoint findCornerFromCenter3 = findCornerFromCenter(i5, 0, x, x2, i7, i10, y, i9, i4);
        return new ResultPoint[]{findCornerFromCenter(i5, 0, x, x2, i7, i8, y, ((int) findCornerFromCenter3.getY()) + 1, i2 / 4), findCornerFromCenter, findCornerFromCenter2, findCornerFromCenter3};
    }

    private ResultPoint findCornerFromCenter(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        int[] iArr;
        int i10 = i;
        int i11 = i5;
        int i12 = i10;
        int i13 = i11;
        int[] iArr2 = null;
        int i14 = i8;
        while (i13 < i14 && i13 >= i7 && i12 < i4 && i12 >= i3) {
            if (i2 == 0) {
                iArr = blackWhiteRange(i13, i9, i3, i4, true);
            } else {
                iArr = blackWhiteRange(i12, i9, i7, i8, false);
            }
            if (iArr != null) {
                i13 += i6;
                i12 += i2;
                iArr2 = iArr;
            } else if (iArr2 != null) {
                char c = 1;
                if (i2 == 0) {
                    int i15 = i13 - i6;
                    if (iArr2[0] >= i10) {
                        return new ResultPoint((float) iArr2[1], (float) i15);
                    }
                    if (iArr2[1] <= i10) {
                        return new ResultPoint((float) iArr2[0], (float) i15);
                    }
                    if (i6 > 0) {
                        c = 0;
                    }
                    return new ResultPoint((float) iArr2[c], (float) i15);
                }
                int i16 = i12 - i2;
                if (iArr2[0] >= i11) {
                    return new ResultPoint((float) i16, (float) iArr2[1]);
                }
                if (iArr2[1] <= i11) {
                    return new ResultPoint((float) i16, (float) iArr2[0]);
                }
                float f = (float) i16;
                if (i2 < 0) {
                    c = 0;
                }
                return new ResultPoint(f, (float) iArr2[c]);
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0035 A[EDGE_INSN: B:53:0x0035->B:16:0x0035 ?: BREAK  
EDGE_INSN: B:53:0x0035->B:16:0x0035 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x006f A[EDGE_INSN: B:66:0x006f->B:36:0x006f ?: BREAK  
EDGE_INSN: B:66:0x006f->B:36:0x006f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] blackWhiteRange(int r6, int r7, int r8, int r9, boolean r10) {
        /*
            r5 = this;
            int r0 = r8 + r9
            r1 = 2
            int r0 = r0 / r1
            r2 = r0
        L_0x0005:
            if (r2 < r8) goto L_0x003e
            if (r10 == 0) goto L_0x0012
            com.google.zxing.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r2, r6)
            if (r3 == 0) goto L_0x001d
            goto L_0x001a
        L_0x0012:
            com.google.zxing.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r6, r2)
            if (r3 == 0) goto L_0x001d
        L_0x001a:
            int r2 = r2 + -1
            goto L_0x0005
        L_0x001d:
            r3 = r2
        L_0x001e:
            int r3 = r3 + -1
            if (r3 < r8) goto L_0x0035
            if (r10 == 0) goto L_0x002d
            com.google.zxing.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r3, r6)
            if (r4 == 0) goto L_0x001e
            goto L_0x0035
        L_0x002d:
            com.google.zxing.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r6, r3)
            if (r4 == 0) goto L_0x001e
        L_0x0035:
            int r4 = r2 - r3
            if (r3 < r8) goto L_0x003e
            if (r4 <= r7) goto L_0x003c
            goto L_0x003e
        L_0x003c:
            r2 = r3
            goto L_0x0005
        L_0x003e:
            r8 = 1
            int r2 = r2 + r8
        L_0x0040:
            if (r0 >= r9) goto L_0x0078
            if (r10 == 0) goto L_0x004d
            com.google.zxing.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r0, r6)
            if (r3 == 0) goto L_0x0058
            goto L_0x0055
        L_0x004d:
            com.google.zxing.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r6, r0)
            if (r3 == 0) goto L_0x0058
        L_0x0055:
            int r0 = r0 + 1
            goto L_0x0040
        L_0x0058:
            r3 = r0
        L_0x0059:
            int r3 = r3 + r8
            if (r3 >= r9) goto L_0x006f
            if (r10 == 0) goto L_0x0067
            com.google.zxing.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r3, r6)
            if (r4 == 0) goto L_0x0059
            goto L_0x006f
        L_0x0067:
            com.google.zxing.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r6, r3)
            if (r4 == 0) goto L_0x0059
        L_0x006f:
            int r4 = r3 - r0
            if (r3 >= r9) goto L_0x0078
            if (r4 <= r7) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            r0 = r3
            goto L_0x0040
        L_0x0078:
            int r0 = r0 + -1
            if (r0 <= r2) goto L_0x0084
            int[] r6 = new int[r1]
            r7 = 0
            r6[r7] = r2
            r6[r8] = r0
            return r6
        L_0x0084:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.MonochromeRectangleDetector.blackWhiteRange(int, int, int, int, boolean):int[]");
    }
}
