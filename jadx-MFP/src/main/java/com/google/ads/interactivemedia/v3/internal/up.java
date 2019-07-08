package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class up {
    public static final byte[] a = {0, 0, 0, 1};
    public static final float[] b = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object c = new Object();
    private static int[] d = new int[10];

    public static int a(byte[] bArr, int i) {
        int i2;
        synchronized (c) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                while (true) {
                    if (i3 < i - 2) {
                        if (bArr[i3] == 0 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 3) {
                            break;
                        }
                        i3++;
                    } else {
                        i3 = i;
                        break;
                    }
                }
                if (i3 < i) {
                    if (d.length <= i4) {
                        d = Arrays.copyOf(d, d.length << 1);
                    }
                    int i5 = i4 + 1;
                    d[i4] = i3;
                    i3 += 3;
                    i4 = i5;
                }
            }
            i2 = i - i4;
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = d[i8] - i7;
                System.arraycopy(bArr, i7, bArr, i6, i9);
                int i10 = i6 + i9;
                int i11 = i10 + 1;
                bArr[i10] = 0;
                i6 = i11 + 1;
                bArr[i11] = 0;
                i7 += i9 + 3;
            }
            System.arraycopy(bArr, i7, bArr, i6, i2 - i6);
        }
        return i2;
    }

    public static void a(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                byte b2 = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (b2 == 1 && (byteBuffer.get(i3) & Ascii.US) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (b2 == 0) {
                    i2++;
                }
                if (b2 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean a(String str, byte b2) {
        if ((!MimeTypes.VIDEO_H264.equals(str) || (b2 & Ascii.US) != 6) && (!MimeTypes.VIDEO_H265.equals(str) || ((b2 & 126) >> 1) != 39)) {
            return false;
        }
        return true;
    }

    public static int b(byte[] bArr, int i) {
        return bArr[i + 3] & Ascii.US;
    }

    public static int c(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static ur a(byte[] bArr, int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        int i4;
        int i5;
        int i6;
        float f;
        int i7;
        int i8;
        uu uuVar = new uu(bArr, i, i2);
        uuVar.a(8);
        int c2 = uuVar.c(8);
        int c3 = uuVar.c(8);
        int c4 = uuVar.c(8);
        int d2 = uuVar.d();
        int i9 = 1;
        if (c2 == 100 || c2 == 110 || c2 == 122 || c2 == 244 || c2 == 44 || c2 == 83 || c2 == 86 || c2 == 118 || c2 == 128 || c2 == 138) {
            i3 = uuVar.d();
            z = i3 == 3 ? uuVar.b() : false;
            uuVar.d();
            uuVar.d();
            uuVar.a();
            if (uuVar.b()) {
                int i10 = i3 != 3 ? 8 : 12;
                int i11 = 0;
                while (i11 < i10) {
                    if (uuVar.b()) {
                        int i12 = i11 < 6 ? 16 : 64;
                        int i13 = 8;
                        int i14 = 8;
                        for (int i15 = 0; i15 < i12; i15++) {
                            if (i13 != 0) {
                                i13 = ((i14 + uuVar.e()) + 256) % 256;
                            }
                            if (i13 != 0) {
                                i14 = i13;
                            }
                        }
                    }
                    i11++;
                }
            }
        } else {
            i3 = 1;
            z = false;
        }
        int d3 = uuVar.d() + 4;
        int d4 = uuVar.d();
        if (d4 == 0) {
            i4 = uuVar.d() + 4;
            z2 = false;
        } else if (d4 == 1) {
            boolean b2 = uuVar.b();
            uuVar.e();
            uuVar.e();
            long d5 = (long) uuVar.d();
            boolean z3 = b2;
            for (int i16 = 0; ((long) i16) < d5; i16++) {
                uuVar.d();
            }
            z2 = z3;
            i4 = 0;
        } else {
            i4 = 0;
            z2 = false;
        }
        uuVar.d();
        uuVar.a();
        int d6 = uuVar.d() + 1;
        int d7 = uuVar.d() + 1;
        int b3 = uuVar.b();
        int i17 = (2 - b3) * d7;
        if (b3 == 0) {
            uuVar.a();
        }
        uuVar.a();
        int i18 = d6 << 4;
        int i19 = i17 << 4;
        if (uuVar.b()) {
            int d8 = uuVar.d();
            int d9 = uuVar.d();
            int d10 = uuVar.d();
            int d11 = uuVar.d();
            if (i3 == 0) {
                i7 = 2 - b3;
                i8 = 1;
            } else {
                i8 = i3 == 3 ? 1 : 2;
                if (i3 == 1) {
                    i9 = 2;
                }
                i7 = (2 - b3) * i9;
            }
            i5 = i19 - ((d10 + d11) * i7);
            i6 = i18 - ((d8 + d9) * i8);
        } else {
            i5 = i19;
            i6 = i18;
        }
        float f2 = 1.0f;
        if (uuVar.b() && uuVar.b()) {
            int c5 = uuVar.c(8);
            if (c5 == 255) {
                int c6 = uuVar.c(16);
                int c7 = uuVar.c(16);
                if (!(c6 == 0 || c7 == 0)) {
                    f2 = ((float) c6) / ((float) c7);
                }
                f = f2;
            } else {
                float[] fArr = b;
                if (c5 < fArr.length) {
                    f = fArr[c5];
                } else {
                    StringBuilder sb = new StringBuilder(46);
                    sb.append("Unexpected aspect_ratio_idc value: ");
                    sb.append(c5);
                    Log.w("NalUnitUtil", sb.toString());
                }
            }
            ur urVar = new ur(c2, c3, c4, d2, i6, i5, f, z, b3, d3, d4, i4, z2);
            return urVar;
        }
        f = 1.0f;
        ur urVar2 = new ur(c2, c3, c4, d2, i6, i5, f, z, b3, d3, d4, i4, z2);
        return urVar2;
    }

    public static uq b(byte[] bArr, int i, int i2) {
        uu uuVar = new uu(bArr, 3, i2);
        uuVar.a(8);
        int d2 = uuVar.d();
        int d3 = uuVar.d();
        uuVar.a();
        return new uq(d2, d3, uuVar.b());
    }

    public static int a(byte[] bArr, int i, int i2, boolean[] zArr) {
        int i3 = i2 - i;
        boolean z = false;
        qi.c(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr != null) {
            if (zArr[0]) {
                a(zArr);
                return i - 3;
            } else if (i3 > 1 && zArr[1] && bArr[i] == 1) {
                a(zArr);
                return i - 2;
            } else if (i3 > 2 && zArr[2] && bArr[i] == 0 && bArr[i + 1] == 1) {
                a(zArr);
                return i - 1;
            }
        }
        int i4 = i2 - 1;
        int i5 = i + 2;
        while (i5 < i4) {
            if ((bArr[i5] & 254) == 0) {
                int i6 = i5 - 2;
                if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && bArr[i5] == 1) {
                    if (zArr != null) {
                        a(zArr);
                    }
                    return i6;
                }
                i5 -= 2;
            }
            i5 += 3;
        }
        if (zArr != null) {
            boolean z2 = i3 > 2 ? bArr[i2 + -3] == 0 && bArr[i2 + -2] == 0 && bArr[i4] == 1 : i3 == 2 ? zArr[2] && bArr[i2 + -2] == 0 && bArr[i4] == 1 : zArr[1] && bArr[i4] == 1;
            zArr[0] = z2;
            boolean z3 = i3 > 1 ? bArr[i2 + -2] == 0 && bArr[i4] == 0 : zArr[2] && bArr[i4] == 0;
            zArr[1] = z3;
            if (bArr[i4] == 0) {
                z = true;
            }
            zArr[2] = z;
        }
        return i2;
    }

    public static void a(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }
}
