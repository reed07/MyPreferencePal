package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.nio.ByteBuffer;

abstract class zzbue {
    zzbue() {
    }

    /* access modifiers changed from: 0000 */
    public abstract int zzb(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: 0000 */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    /* access modifiers changed from: 0000 */
    public abstract String zzo(byte[] bArr, int i, int i2) throws zzbrl;

    /* access modifiers changed from: 0000 */
    public final boolean zzm(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }

    static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i3 = 0;
        while (i3 < length) {
            try {
                char charAt = charSequence.charAt(i3);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i3, (byte) charAt);
                i3++;
            } catch (IndexOutOfBoundsException unused) {
                int position2 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                char charAt2 = charSequence.charAt(i3);
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(charAt2);
                sb.append(" at index ");
                sb.append(position2);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
        if (i3 == length) {
            byteBuffer.position(position + i3);
            return;
        }
        position += i3;
        while (i3 < length) {
            char charAt3 = charSequence.charAt(i3);
            if (charAt3 < 128) {
                byteBuffer.put(position, (byte) charAt3);
            } else if (charAt3 < 2048) {
                int i4 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt3 >>> 6) | 192));
                    byteBuffer.put(i4, (byte) ((charAt3 & '?') | 128));
                    position = i4;
                } catch (IndexOutOfBoundsException unused2) {
                    i = i4;
                    int position22 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                    char charAt22 = charSequence.charAt(i3);
                    StringBuilder sb2 = new StringBuilder(37);
                    sb2.append("Failed writing ");
                    sb2.append(charAt22);
                    sb2.append(" at index ");
                    sb2.append(position22);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else if (charAt3 < 55296 || 57343 < charAt3) {
                int i5 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt3 >>> 12) | 224));
                    position = i5 + 1;
                    byteBuffer.put(i5, (byte) (((charAt3 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt3 & '?') | 128));
                } catch (IndexOutOfBoundsException unused3) {
                    i = i5;
                    int position222 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                    char charAt222 = charSequence.charAt(i3);
                    StringBuilder sb22 = new StringBuilder(37);
                    sb22.append("Failed writing ");
                    sb22.append(charAt222);
                    sb22.append(" at index ");
                    sb22.append(position222);
                    throw new ArrayIndexOutOfBoundsException(sb22.toString());
                }
            } else {
                int i6 = i3 + 1;
                if (i6 != length) {
                    try {
                        char charAt4 = charSequence.charAt(i6);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            int i7 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                i = i7 + 1;
                                try {
                                    byteBuffer.put(i7, (byte) (((codePoint >>> 12) & 63) | 128));
                                    i2 = i + 1;
                                } catch (IndexOutOfBoundsException unused4) {
                                    i3 = i6;
                                    int position2222 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                                    char charAt2222 = charSequence.charAt(i3);
                                    StringBuilder sb222 = new StringBuilder(37);
                                    sb222.append("Failed writing ");
                                    sb222.append(charAt2222);
                                    sb222.append(" at index ");
                                    sb222.append(position2222);
                                    throw new ArrayIndexOutOfBoundsException(sb222.toString());
                                }
                                try {
                                    byteBuffer.put(i, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i2, (byte) ((codePoint & 63) | 128));
                                    position = i2;
                                    i3 = i6;
                                } catch (IndexOutOfBoundsException unused5) {
                                    position = i2;
                                    i3 = i6;
                                    int position22222 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                                    char charAt22222 = charSequence.charAt(i3);
                                    StringBuilder sb2222 = new StringBuilder(37);
                                    sb2222.append("Failed writing ");
                                    sb2222.append(charAt22222);
                                    sb2222.append(" at index ");
                                    sb2222.append(position22222);
                                    throw new ArrayIndexOutOfBoundsException(sb2222.toString());
                                }
                            } catch (IndexOutOfBoundsException unused6) {
                                i = i7;
                                i3 = i6;
                                int position222222 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                                char charAt222222 = charSequence.charAt(i3);
                                StringBuilder sb22222 = new StringBuilder(37);
                                sb22222.append("Failed writing ");
                                sb22222.append(charAt222222);
                                sb22222.append(" at index ");
                                sb22222.append(position222222);
                                throw new ArrayIndexOutOfBoundsException(sb22222.toString());
                            }
                        } else {
                            i3 = i6;
                        }
                    } catch (IndexOutOfBoundsException unused7) {
                        i3 = i6;
                        int position2222222 = byteBuffer.position() + Math.max(i3, (i - byteBuffer.position()) + 1);
                        char charAt2222222 = charSequence.charAt(i3);
                        StringBuilder sb222222 = new StringBuilder(37);
                        sb222222.append("Failed writing ");
                        sb222222.append(charAt2222222);
                        sb222222.append(" at index ");
                        sb222222.append(position2222222);
                        throw new ArrayIndexOutOfBoundsException(sb222222.toString());
                    }
                }
                throw new zzbug(i3, length);
            }
            i3++;
            position++;
        }
        byteBuffer.position(position);
    }
}
