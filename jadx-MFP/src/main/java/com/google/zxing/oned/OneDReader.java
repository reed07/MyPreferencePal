package com.google.zxing.oned;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e) {
            if (!(map != null && map.containsKey(DecodeHintType.TRY_HARDER)) || !binaryBitmap.isRotateSupported()) {
                throw e;
            }
            BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
            Result doDecode = doDecode(rotateCounterClockwise, map);
            Map resultMetadata = doDecode.getResultMetadata();
            int i = 270;
            if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                i = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
            ResultPoint[] resultPoints = doDecode.getResultPoints();
            if (resultPoints != null) {
                int height = rotateCounterClockwise.getHeight();
                for (int i2 = 0; i2 < resultPoints.length; i2++) {
                    resultPoints[i2] = new ResultPoint((((float) height) - resultPoints[i2].getY()) - 1.0f, resultPoints[i2].getX());
                }
            }
            return doDecode;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f0, code lost:
        throw com.google.zxing.NotFoundException.getNotFoundInstance();
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0081 A[Catch:{ ReaderException -> 0x00c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00c7 A[EDGE_INSN: B:69:0x00c7->B:49:0x00c7 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r20, java.util.Map<com.google.zxing.DecodeHintType, ?> r21) throws com.google.zxing.NotFoundException {
        /*
            r19 = this;
            r0 = r21
            int r1 = r20.getWidth()
            int r2 = r20.getHeight()
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>(r1)
            int r4 = r2 >> 1
            r5 = 0
            r6 = 1
            if (r0 == 0) goto L_0x001f
            com.google.zxing.DecodeHintType r7 = com.google.zxing.DecodeHintType.TRY_HARDER
            boolean r7 = r0.containsKey(r7)
            if (r7 == 0) goto L_0x001f
            r7 = 1
            goto L_0x0020
        L_0x001f:
            r7 = 0
        L_0x0020:
            if (r7 == 0) goto L_0x0025
            r8 = 8
            goto L_0x0026
        L_0x0025:
            r8 = 5
        L_0x0026:
            int r8 = r2 >> r8
            int r8 = java.lang.Math.max(r6, r8)
            if (r7 == 0) goto L_0x0030
            r7 = r2
            goto L_0x0032
        L_0x0030:
            r7 = 15
        L_0x0032:
            r9 = r0
            r0 = 0
        L_0x0034:
            if (r0 >= r7) goto L_0x00ea
            int r10 = r0 + 1
            int r11 = r10 / 2
            r0 = r0 & 1
            if (r0 != 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            if (r0 == 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            int r11 = -r11
        L_0x0045:
            int r11 = r11 * r8
            int r11 = r11 + r4
            if (r11 < 0) goto L_0x00e7
            if (r11 >= r2) goto L_0x00e7
            r0 = r20
            com.google.zxing.common.BitArray r3 = r0.getBlackRow(r11, r3)     // Catch:{ NotFoundException -> 0x00db }
            r12 = r9
            r9 = 0
        L_0x0054:
            r13 = 2
            if (r9 >= r13) goto L_0x00d4
            if (r9 != r6) goto L_0x0079
            r3.reverse()
            if (r12 == 0) goto L_0x0079
            com.google.zxing.DecodeHintType r13 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            boolean r13 = r12.containsKey(r13)
            if (r13 == 0) goto L_0x0079
            java.util.EnumMap r13 = new java.util.EnumMap
            java.lang.Class<com.google.zxing.DecodeHintType> r14 = com.google.zxing.DecodeHintType.class
            r13.<init>(r14)
            r13.putAll(r12)
            com.google.zxing.DecodeHintType r12 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            r13.remove(r12)
            r12 = r13
            r13 = r19
            goto L_0x007b
        L_0x0079:
            r13 = r19
        L_0x007b:
            com.google.zxing.Result r14 = r13.decodeRow(r11, r3, r12)     // Catch:{ ReaderException -> 0x00c8 }
            if (r9 != r6) goto L_0x00c7
            com.google.zxing.ResultMetadataType r15 = com.google.zxing.ResultMetadataType.ORIENTATION     // Catch:{ ReaderException -> 0x00c8 }
            r16 = 180(0xb4, float:2.52E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r16)     // Catch:{ ReaderException -> 0x00c8 }
            r14.putMetadata(r15, r6)     // Catch:{ ReaderException -> 0x00c8 }
            com.google.zxing.ResultPoint[] r6 = r14.getResultPoints()     // Catch:{ ReaderException -> 0x00c8 }
            if (r6 == 0) goto L_0x00c7
            com.google.zxing.ResultPoint r15 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00c8 }
            float r0 = (float) r1     // Catch:{ ReaderException -> 0x00c8 }
            r16 = r6[r5]     // Catch:{ ReaderException -> 0x00c8 }
            float r16 = r16.getX()     // Catch:{ ReaderException -> 0x00c8 }
            float r16 = r0 - r16
            r17 = 1065353216(0x3f800000, float:1.0)
            r18 = r1
            float r1 = r16 - r17
            r16 = r6[r5]     // Catch:{ ReaderException -> 0x00ca }
            float r5 = r16.getY()     // Catch:{ ReaderException -> 0x00ca }
            r15.<init>(r1, r5)     // Catch:{ ReaderException -> 0x00ca }
            r1 = 0
            r6[r1] = r15     // Catch:{ ReaderException -> 0x00ca }
            com.google.zxing.ResultPoint r5 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00ca }
            r15 = 1
            r16 = r6[r15]     // Catch:{ ReaderException -> 0x00cb }
            float r16 = r16.getX()     // Catch:{ ReaderException -> 0x00cb }
            float r0 = r0 - r16
            float r0 = r0 - r17
            r16 = r6[r15]     // Catch:{ ReaderException -> 0x00cb }
            float r1 = r16.getY()     // Catch:{ ReaderException -> 0x00cb }
            r5.<init>(r0, r1)     // Catch:{ ReaderException -> 0x00cb }
            r6[r15] = r5     // Catch:{ ReaderException -> 0x00cb }
        L_0x00c7:
            return r14
        L_0x00c8:
            r18 = r1
        L_0x00ca:
            r15 = 1
        L_0x00cb:
            int r9 = r9 + 1
            r1 = r18
            r0 = r20
            r5 = 0
            r6 = 1
            goto L_0x0054
        L_0x00d4:
            r15 = 1
            r13 = r19
            r18 = r1
            r9 = r12
            goto L_0x00e0
        L_0x00db:
            r15 = 1
            r13 = r19
            r18 = r1
        L_0x00e0:
            r0 = r10
            r1 = r18
            r5 = 0
            r6 = 1
            goto L_0x0034
        L_0x00e7:
            r13 = r19
            goto L_0x00ec
        L_0x00ea:
            r13 = r19
        L_0x00ec:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    protected static void recordPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i < size) {
            boolean z = !bitArray.get(i);
            int i2 = 0;
            while (i < size) {
                if (!(bitArray.get(i) ^ z)) {
                    i2++;
                    if (i2 == length) {
                        break;
                    }
                    iArr[i2] = 1;
                    z = !z;
                } else {
                    iArr[i2] = iArr[i2] + 1;
                }
                i++;
            }
            if (i2 == length) {
                return;
            }
            if (i2 != length - 1 || i != size) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void recordPatternInReverse(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.get(i) != z) {
                length--;
                z = !z;
            }
        }
        if (length < 0) {
            recordPattern(bitArray, i + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = (float) i;
        float f3 = f2 / ((float) i2);
        float f4 = f * f3;
        float f5 = BitmapDescriptorFactory.HUE_RED;
        for (int i4 = 0; i4 < length; i4++) {
            float f6 = ((float) iArr2[i4]) * f3;
            float f7 = (float) iArr[i4];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
