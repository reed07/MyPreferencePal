package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.common.base.Ascii;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import java.nio.ByteBuffer;

final class zzxo extends zzxn {
    zzxo() {
    }

    /* access modifiers changed from: 0000 */
    public final int zzb(int i, byte[] bArr, int i2, int i3) {
        while (r9 < i3 && bArr[r9] >= 0) {
            i2 = r9 + 1;
        }
        if (r9 >= i3) {
            return 0;
        }
        while (r9 < i3) {
            int i4 = r9 + 1;
            byte b = bArr[r9];
            if (b >= 0) {
                r9 = i4;
            } else if (b < -32) {
                if (i4 >= i3) {
                    return b;
                }
                if (b >= -62) {
                    r9 = i4 + 1;
                    if (bArr[i4] > -65) {
                    }
                }
                return -1;
            } else if (b < -16) {
                if (i4 >= i3 - 1) {
                    return zzxl.zzg(bArr, i4, i3);
                }
                int i5 = i4 + 1;
                byte b2 = bArr[i4];
                if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                    r9 = i5 + 1;
                    if (bArr[i5] > -65) {
                    }
                }
                return -1;
            } else if (i4 >= i3 - 2) {
                return zzxl.zzg(bArr, i4, i3);
            } else {
                int i6 = i4 + 1;
                byte b3 = bArr[i4];
                if (b3 <= -65 && (((b << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                    int i7 = i6 + 1;
                    if (bArr[i6] <= -65) {
                        int i8 = i7 + 1;
                        if (bArr[i7] <= -65) {
                            r9 = i8;
                        }
                    }
                }
                return -1;
            }
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final String zzh(byte[] bArr, int i, int i2) throws zzuv {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (r13 < i3) {
                byte b = bArr[r13];
                if (!zzxm.zzd(b)) {
                    break;
                }
                i = r13 + 1;
                int i5 = i4 + 1;
                zzxm.zza(b, cArr, i4);
                i4 = i5;
            }
            int i6 = i4;
            while (r13 < i3) {
                int i7 = r13 + 1;
                byte b2 = bArr[r13];
                if (zzxm.zzd(b2)) {
                    int i8 = i6 + 1;
                    zzxm.zza(b2, cArr, i6);
                    while (i7 < i3) {
                        byte b3 = bArr[i7];
                        if (!zzxm.zzd(b3)) {
                            break;
                        }
                        i7++;
                        int i9 = i8 + 1;
                        zzxm.zza(b3, cArr, i8);
                        i8 = i9;
                    }
                    r13 = i7;
                    i6 = i8;
                } else if (zzxm.zze(b2)) {
                    if (i7 < i3) {
                        int i10 = i7 + 1;
                        int i11 = i6 + 1;
                        zzxm.zza(b2, bArr[i7], cArr, i6);
                        r13 = i10;
                        i6 = i11;
                    } else {
                        throw zzuv.zzwx();
                    }
                } else if (zzxm.zzf(b2)) {
                    if (i7 < i3 - 1) {
                        int i12 = i7 + 1;
                        int i13 = i12 + 1;
                        int i14 = i6 + 1;
                        zzxm.zza(b2, bArr[i7], bArr[i12], cArr, i6);
                        r13 = i13;
                        i6 = i14;
                    } else {
                        throw zzuv.zzwx();
                    }
                } else if (i7 < i3 - 2) {
                    int i15 = i7 + 1;
                    byte b4 = bArr[i7];
                    int i16 = i15 + 1;
                    int i17 = i16 + 1;
                    int i18 = i6 + 1;
                    zzxm.zza(b2, b4, bArr[i15], bArr[i16], cArr, i6);
                    r13 = i17;
                    i6 = i18 + 1;
                } else {
                    throw zzuv.zzwx();
                }
            }
            return new String(cArr, 0, i6);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: 0000 */
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int length = charSequence.length();
        int i3 = i2 + i;
        int i4 = 0;
        while (i4 < length) {
            int i5 = i4 + i;
            if (i5 >= i3) {
                break;
            }
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i5] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        int i6 = i + i4;
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i6 < i3) {
                int i7 = i6 + 1;
                bArr[i6] = (byte) charAt2;
                i6 = i7;
            } else if (charAt2 < 2048 && i6 <= i3 - 2) {
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 6) | 960);
                i6 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i6 <= i3 - 3) {
                int i9 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 12) | AmazonAdTask.DEFAULT_AD_HEIGHT);
                int i10 = i9 + 1;
                bArr[i9] = (byte) (((charAt2 >>> 6) & 63) | 128);
                int i11 = i10 + 1;
                bArr[i10] = (byte) ((charAt2 & '?') | 128);
                i6 = i11;
            } else if (i6 <= i3 - 4) {
                int i12 = i4 + 1;
                if (i12 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i12);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i13 = i6 + 1;
                        bArr[i6] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                        int i14 = i13 + 1;
                        bArr[i13] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i15 = i14 + 1;
                        bArr[i14] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i6 = i15 + 1;
                        bArr[i15] = (byte) ((codePoint & 63) | 128);
                        i4 = i12;
                    } else {
                        i4 = i12;
                    }
                }
                throw new zzxp(i4 - 1, length);
            } else {
                if (55296 <= charAt2 && charAt2 <= 57343) {
                    int i16 = i4 + 1;
                    if (i16 == charSequence.length() || !Character.isSurrogatePair(charAt2, charSequence.charAt(i16))) {
                        throw new zzxp(i4, length);
                    }
                }
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(charAt2);
                sb.append(" at index ");
                sb.append(i6);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            i4++;
        }
        return i6;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzc(charSequence, byteBuffer);
    }
}
