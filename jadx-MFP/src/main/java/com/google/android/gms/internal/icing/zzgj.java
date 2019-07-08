package com.google.android.gms.internal.icing;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.myfitnesspal.feature.video.task.AmazonAdTask;

final class zzgj extends zzgg {
    zzgj() {
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bd, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(int r16, byte[] r17, int r18, int r19) {
        /*
            r15 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r1 | r2
            int r4 = r0.length
            int r4 = r4 - r2
            r3 = r3 | r4
            r4 = 2
            r5 = 3
            r6 = 0
            if (r3 < 0) goto L_0x00be
            long r7 = (long) r1
            long r1 = (long) r2
            long r1 = r1 - r7
            int r2 = (int) r1
            r1 = 16
            r9 = 1
            if (r2 >= r1) goto L_0x001c
            r1 = 0
            goto L_0x002e
        L_0x001c:
            r11 = r7
            r1 = 0
        L_0x001e:
            if (r1 >= r2) goto L_0x002d
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.icing.zzgd.zza(r0, r11)
            if (r3 >= 0) goto L_0x0029
            goto L_0x002e
        L_0x0029:
            int r1 = r1 + 1
            r11 = r13
            goto L_0x001e
        L_0x002d:
            r1 = r2
        L_0x002e:
            int r2 = r2 - r1
            long r11 = (long) r1
            long r7 = r7 + r11
        L_0x0031:
            r1 = 0
        L_0x0032:
            if (r2 <= 0) goto L_0x0040
            long r11 = r7 + r9
            byte r1 = com.google.android.gms.internal.icing.zzgd.zza(r0, r7)
            if (r1 < 0) goto L_0x0041
            int r2 = r2 + -1
            r7 = r11
            goto L_0x0032
        L_0x0040:
            r11 = r7
        L_0x0041:
            if (r2 != 0) goto L_0x0044
            return r6
        L_0x0044:
            int r2 = r2 + -1
            r3 = -32
            r7 = -65
            r8 = -1
            if (r1 >= r3) goto L_0x0062
            if (r2 != 0) goto L_0x0050
            return r1
        L_0x0050:
            int r2 = r2 + -1
            r3 = -62
            if (r1 < r3) goto L_0x0061
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.icing.zzgd.zza(r0, r11)
            if (r1 <= r7) goto L_0x005f
            goto L_0x0061
        L_0x005f:
            r7 = r13
            goto L_0x0031
        L_0x0061:
            return r8
        L_0x0062:
            r13 = -16
            if (r1 >= r13) goto L_0x008f
            if (r2 >= r4) goto L_0x006d
            int r0 = zza(r0, r1, r11, r2)
            return r0
        L_0x006d:
            int r2 = r2 + -2
            long r13 = r11 + r9
            byte r11 = com.google.android.gms.internal.icing.zzgd.zza(r0, r11)
            if (r11 > r7) goto L_0x008e
            r12 = -96
            if (r1 != r3) goto L_0x007d
            if (r11 < r12) goto L_0x008e
        L_0x007d:
            r3 = -19
            if (r1 != r3) goto L_0x0083
            if (r11 >= r12) goto L_0x008e
        L_0x0083:
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.icing.zzgd.zza(r0, r13)
            if (r1 <= r7) goto L_0x008c
            goto L_0x008e
        L_0x008c:
            r7 = r11
            goto L_0x0031
        L_0x008e:
            return r8
        L_0x008f:
            if (r2 >= r5) goto L_0x0096
            int r0 = zza(r0, r1, r11, r2)
            return r0
        L_0x0096:
            int r2 = r2 + -3
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.icing.zzgd.zza(r0, r11)
            if (r3 > r7) goto L_0x00bd
            int r1 = r1 << 28
            int r3 = r3 + 112
            int r1 = r1 + r3
            int r1 = r1 >> 30
            if (r1 != 0) goto L_0x00bd
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.icing.zzgd.zza(r0, r13)
            if (r1 > r7) goto L_0x00bd
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.icing.zzgd.zza(r0, r11)
            if (r1 <= r7) goto L_0x00ba
            goto L_0x00bd
        L_0x00ba:
            r7 = r13
            goto L_0x0031
        L_0x00bd:
            return r8
        L_0x00be:
            java.lang.ArrayIndexOutOfBoundsException r3 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            int r0 = r0.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r18)
            r1 = 1
            r5[r1] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r19)
            r5[r4] = r0
            java.lang.String r0 = "Array length=%d, index=%d, limit=%d"
            java.lang.String r0 = java.lang.String.format(r0, r5)
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzgj.zzb(int, byte[], int, int):int");
    }

    /* access modifiers changed from: 0000 */
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        CharSequence charSequence2 = charSequence;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        long j2 = (long) i3;
        long j3 = ((long) i4) + j2;
        int length = charSequence.length();
        if (length > i4 || bArr2.length - i4 < i3) {
            char charAt = charSequence2.charAt(length - 1);
            int i5 = i3 + i4;
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt);
            sb.append(" at index ");
            sb.append(i5);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i6 = 0;
        while (i6 < length) {
            char charAt2 = charSequence2.charAt(i6);
            if (charAt2 >= 128) {
                break;
            }
            long j4 = 1 + j;
            zzgd.zza(bArr2, j, (byte) charAt2);
            i6++;
            j2 = j4;
        }
        if (i6 == length) {
            return (int) j;
        }
        while (i6 < length) {
            char charAt3 = charSequence2.charAt(i6);
            if (charAt3 < 128 && j < j3) {
                long j5 = j + 1;
                zzgd.zza(bArr2, j, (byte) charAt3);
                j = j5;
            } else if (charAt3 < 2048 && j <= j3 - 2) {
                long j6 = j + 1;
                zzgd.zza(bArr2, j, (byte) ((charAt3 >>> 6) | 960));
                j = j6 + 1;
                zzgd.zza(bArr2, j6, (byte) ((charAt3 & '?') | 128));
            } else if ((charAt3 < 55296 || 57343 < charAt3) && j <= j3 - 3) {
                long j7 = j + 1;
                zzgd.zza(bArr2, j, (byte) ((charAt3 >>> 12) | AmazonAdTask.DEFAULT_AD_HEIGHT));
                long j8 = j7 + 1;
                zzgd.zza(bArr2, j7, (byte) (((charAt3 >>> 6) & 63) | 128));
                long j9 = j8 + 1;
                zzgd.zza(bArr2, j8, (byte) ((charAt3 & '?') | 128));
                j = j9;
            } else if (j <= j3 - 4) {
                int i7 = i6 + 1;
                if (i7 != length) {
                    char charAt4 = charSequence2.charAt(i7);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j10 = j + 1;
                        zzgd.zza(bArr2, j, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                        long j11 = j10 + 1;
                        zzgd.zza(bArr2, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j12 = j11 + 1;
                        zzgd.zza(bArr2, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                        j = j12 + 1;
                        zzgd.zza(bArr2, j12, (byte) ((codePoint & 63) | 128));
                        i6 = i7;
                    }
                } else {
                    i7 = i6;
                }
                throw new zzgi(i7 - 1, length);
            } else {
                if (55296 <= charAt3 && charAt3 <= 57343) {
                    int i8 = i6 + 1;
                    if (i8 == length || !Character.isSurrogatePair(charAt3, charSequence2.charAt(i8))) {
                        throw new zzgi(i6, length);
                    }
                }
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed writing ");
                sb2.append(charAt3);
                sb2.append(" at index ");
                sb2.append(j);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            }
            i6++;
        }
        return (int) j;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzgf.zzal(i);
            case 1:
                return zzgf.zzo(i, zzgd.zza(bArr, j));
            case 2:
                return zzgf.zzc(i, (int) zzgd.zza(bArr, j), (int) zzgd.zza(bArr, j + 1));
            default:
                throw new AssertionError();
        }
    }
}
