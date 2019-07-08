package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* compiled from: IMASDK */
public final class ahc {
    private final ByteBuffer a;

    private ahc(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int c(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    private ahc(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
        this.a.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static ahc a(byte[] bArr, int i, int i2) {
        return new ahc(bArr, 0, i2);
    }

    public final void a(String str) throws IOException {
        try {
            int c = c(str.length());
            if (c == c(str.length() * 3)) {
                int position = this.a.position();
                if (this.a.remaining() >= c) {
                    this.a.position(position + c);
                    a((CharSequence) str, this.a);
                    int position2 = this.a.position();
                    this.a.position(position);
                    b((position2 - position) - c);
                    this.a.position(position2);
                    return;
                }
                throw new ahd(position + c, this.a.limit());
            }
            int length = str.length();
            int i = 0;
            int i2 = 0;
            while (i2 < length && str.charAt(i2) < 128) {
                i2++;
            }
            int i3 = length;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                char charAt = str.charAt(i2);
                if (charAt < 2048) {
                    i3 += (127 - charAt) >>> 31;
                    i2++;
                } else {
                    int length2 = str.length();
                    while (i2 < length2) {
                        char charAt2 = str.charAt(i2);
                        if (charAt2 < 2048) {
                            i += (127 - charAt2) >>> 31;
                        } else {
                            i += 2;
                            if (55296 <= charAt2 && charAt2 <= 57343) {
                                if (Character.codePointAt(str, i2) >= 65536) {
                                    i2++;
                                } else {
                                    StringBuilder sb = new StringBuilder(39);
                                    sb.append("Unpaired surrogate at index ");
                                    sb.append(i2);
                                    throw new IllegalArgumentException(sb.toString());
                                }
                            }
                        }
                        i2++;
                    }
                    i3 += i;
                }
            }
            if (i3 >= length) {
                b(i3);
                a((CharSequence) str, this.a);
                return;
            }
            long j = ((long) i3) + 4294967296L;
            StringBuilder sb2 = new StringBuilder(54);
            sb2.append("UTF-8 length does not fit in int: ");
            sb2.append(j);
            throw new IllegalArgumentException(sb2.toString());
        } catch (BufferOverflowException e) {
            ahd ahd = new ahd(this.a.position(), this.a.limit());
            ahd.initCause(e);
            throw ahd;
        }
    }

    private static void a(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        if (!byteBuffer.isReadOnly()) {
            int i2 = 0;
            if (byteBuffer.hasArray()) {
                try {
                    byte[] array = byteBuffer.array();
                    int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                    int remaining = byteBuffer.remaining();
                    int length = charSequence.length();
                    int i3 = remaining + arrayOffset;
                    while (i2 < length) {
                        int i4 = i2 + arrayOffset;
                        if (i4 >= i3) {
                            break;
                        }
                        char charAt = charSequence.charAt(i2);
                        if (charAt >= 128) {
                            break;
                        }
                        array[i4] = (byte) charAt;
                        i2++;
                    }
                    if (i2 == length) {
                        i = arrayOffset + length;
                    } else {
                        i = arrayOffset + i2;
                        while (i2 < length) {
                            char charAt2 = charSequence.charAt(i2);
                            if (charAt2 < 128 && i < i3) {
                                int i5 = i + 1;
                                array[i] = (byte) charAt2;
                                i = i5;
                            } else if (charAt2 < 2048 && i <= i3 - 2) {
                                int i6 = i + 1;
                                array[i] = (byte) ((charAt2 >>> 6) | 960);
                                i = i6 + 1;
                                array[i6] = (byte) ((charAt2 & '?') | 128);
                            } else if ((charAt2 < 55296 || 57343 < charAt2) && i <= i3 - 3) {
                                int i7 = i + 1;
                                array[i] = (byte) ((charAt2 >>> 12) | AmazonAdTask.DEFAULT_AD_HEIGHT);
                                int i8 = i7 + 1;
                                array[i7] = (byte) (((charAt2 >>> 6) & 63) | 128);
                                int i9 = i8 + 1;
                                array[i8] = (byte) ((charAt2 & '?') | 128);
                                i = i9;
                            } else if (i <= i3 - 4) {
                                int i10 = i2 + 1;
                                if (i10 != charSequence.length()) {
                                    char charAt3 = charSequence.charAt(i10);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        int i11 = i + 1;
                                        array[i] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                        int i12 = i11 + 1;
                                        array[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                                        int i13 = i12 + 1;
                                        array[i12] = (byte) (((codePoint >>> 6) & 63) | 128);
                                        i = i13 + 1;
                                        array[i13] = (byte) ((codePoint & 63) | 128);
                                        i2 = i10;
                                    } else {
                                        i2 = i10;
                                    }
                                }
                                int i14 = i2 - 1;
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i14);
                                throw new IllegalArgumentException(sb.toString());
                            } else {
                                StringBuilder sb2 = new StringBuilder(37);
                                sb2.append("Failed writing ");
                                sb2.append(charAt2);
                                sb2.append(" at index ");
                                sb2.append(i);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            i2++;
                        }
                    }
                    byteBuffer.position(i - byteBuffer.arrayOffset());
                } catch (ArrayIndexOutOfBoundsException e) {
                    BufferOverflowException bufferOverflowException = new BufferOverflowException();
                    bufferOverflowException.initCause(e);
                    throw bufferOverflowException;
                }
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt4 = charSequence.charAt(i2);
                    if (charAt4 < 128) {
                        byteBuffer.put((byte) charAt4);
                    } else if (charAt4 < 2048) {
                        byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                        byteBuffer.put((byte) ((charAt4 & '?') | 128));
                    } else if (charAt4 < 55296 || 57343 < charAt4) {
                        byteBuffer.put((byte) ((charAt4 >>> 12) | AmazonAdTask.DEFAULT_AD_HEIGHT));
                        byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((charAt4 & '?') | 128));
                    } else {
                        int i15 = i2 + 1;
                        if (i15 != charSequence.length()) {
                            char charAt5 = charSequence.charAt(i15);
                            if (Character.isSurrogatePair(charAt4, charAt5)) {
                                int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                                byteBuffer.put((byte) ((codePoint2 >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                                i2 = i15;
                            } else {
                                i2 = i15;
                            }
                        }
                        int i16 = i2 - 1;
                        StringBuilder sb3 = new StringBuilder(39);
                        sb3.append("Unpaired surrogate at index ");
                        sb3.append(i16);
                        throw new IllegalArgumentException(sb3.toString());
                    }
                    i2++;
                }
            }
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    public final int a() {
        return this.a.remaining();
    }

    public final void a(byte b) throws IOException {
        if (this.a.hasRemaining()) {
            this.a.put(b);
            return;
        }
        throw new ahd(this.a.position(), this.a.limit());
    }

    private final void a(int i) throws IOException {
        byte b = (byte) i;
        if (this.a.hasRemaining()) {
            this.a.put(b);
            return;
        }
        throw new ahd(this.a.position(), this.a.limit());
    }

    public final void a(int i, int i2) throws IOException {
        b((i << 3) | i2);
    }

    private final void b(int i) throws IOException {
        while ((i & -128) != 0) {
            a((i & Statements.GetOwnedFoodIdsDateDescending) | 128);
            i >>>= 7;
        }
        a(i);
    }
}
