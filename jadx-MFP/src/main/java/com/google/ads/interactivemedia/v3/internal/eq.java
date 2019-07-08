package com.google.ads.interactivemedia.v3.internal;

import com.google.logging.type.LogSeverity;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: IMASDK */
final class eq {
    private final int a;
    private final int b;
    private final float c;
    private final float d;
    private final float e;
    private final int f;
    private final int g;
    private final int h = (this.g * 2);
    private final short[] i;
    private short[] j;
    private int k;
    private short[] l;
    private int m;
    private short[] n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;

    public eq(int i2, int i3, float f2, float f3, int i4) {
        this.a = i2;
        this.b = i3;
        this.c = f2;
        this.d = f3;
        this.e = ((float) i2) / ((float) i4);
        this.f = i2 / LogSeverity.WARNING_VALUE;
        this.g = i2 / 65;
        int i5 = this.h;
        this.i = new short[i5];
        this.j = new short[(i5 * i3)];
        this.l = new short[(i5 * i3)];
        this.n = new short[(i5 * i3)];
    }

    public final void a(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i2 = this.b;
        int i3 = remaining / i2;
        int i4 = (i2 * i3) << 1;
        this.j = a(this.j, this.k, i3);
        shortBuffer.get(this.j, this.k * this.b, i4 / 2);
        this.k += i3;
        d();
    }

    public final void b(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.b, this.m);
        shortBuffer.put(this.l, 0, this.b * min);
        this.m -= min;
        short[] sArr = this.l;
        int i2 = this.b;
        System.arraycopy(sArr, min * i2, sArr, 0, this.m * i2);
    }

    public final void a() {
        int i2;
        int i3 = this.k;
        float f2 = this.c;
        float f3 = this.d;
        float f4 = this.e * f3;
        int i4 = this.m + ((int) ((((((float) i3) / (f2 / f3)) + ((float) this.o)) / f4) + 0.5f));
        this.j = a(this.j, i3, (this.h * 2) + i3);
        int i5 = 0;
        while (true) {
            i2 = this.h;
            int i6 = i2 * 2;
            int i7 = this.b;
            if (i5 >= i6 * i7) {
                break;
            }
            this.j[(i7 * i3) + i5] = 0;
            i5++;
        }
        this.k += i2 * 2;
        d();
        if (this.m > i4) {
            this.m = i4;
        }
        this.k = 0;
        this.r = 0;
        this.o = 0;
    }

    public final void b() {
        this.k = 0;
        this.m = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
    }

    public final int c() {
        return this.m;
    }

    private final short[] a(short[] sArr, int i2, int i3) {
        int length = sArr.length;
        int i4 = this.b;
        int i5 = length / i4;
        if (i2 + i3 <= i5) {
            return sArr;
        }
        return Arrays.copyOf(sArr, (((i5 * 3) / 2) + i3) * i4);
    }

    private final void b(short[] sArr, int i2, int i3) {
        this.l = a(this.l, this.m, i3);
        int i4 = this.b;
        System.arraycopy(sArr, i2 * i4, this.l, this.m * i4, i4 * i3);
        this.m += i3;
    }

    private final void c(short[] sArr, int i2, int i3) {
        int i4 = this.h / i3;
        int i5 = this.b;
        int i6 = i3 * i5;
        int i7 = i2 * i5;
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = 0;
            for (int i10 = 0; i10 < i6; i10++) {
                i9 += sArr[(i8 * i6) + i7 + i10];
            }
            this.i[i8] = (short) (i9 / i6);
        }
    }

    private final int a(short[] sArr, int i2, int i3, int i4) {
        int i5 = i2 * this.b;
        int i6 = 1;
        int i7 = 0;
        int i8 = 0;
        int i9 = 255;
        while (i3 <= i4) {
            int i10 = 0;
            for (int i11 = 0; i11 < i3; i11++) {
                i10 += Math.abs(sArr[i5 + i11] - sArr[(i5 + i3) + i11]);
            }
            if (i10 * i7 < i6 * i3) {
                i7 = i3;
                i6 = i10;
            }
            if (i10 * i9 > i8 * i3) {
                i9 = i3;
                i8 = i10;
            }
            i3++;
        }
        this.u = i6 / i7;
        this.v = i8 / i9;
        return i7;
    }

    private final void d() {
        float f2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = this.m;
        float f3 = this.c;
        float f4 = this.d;
        float f5 = f3 / f4;
        float f6 = this.e * f4;
        double d2 = (double) f5;
        float f7 = 1.0f;
        int i9 = 1;
        if (d2 > 1.00001d || d2 < 0.99999d) {
            int i10 = this.k;
            if (i10 >= this.h) {
                int i11 = 0;
                while (true) {
                    int i12 = this.r;
                    if (i12 > 0) {
                        int min = Math.min(this.h, i12);
                        b(this.j, i11, min);
                        this.r -= min;
                        i11 += min;
                    } else {
                        short[] sArr = this.j;
                        int i13 = this.a;
                        int i14 = i13 > 4000 ? i13 / 4000 : 1;
                        if (this.b == i9 && i14 == i9) {
                            i5 = a(sArr, i11, this.f, this.g);
                        } else {
                            c(sArr, i11, i14);
                            int a2 = a(this.i, 0, this.f / i14, this.g / i14);
                            if (i14 != i9) {
                                int i15 = a2 * i14;
                                int i16 = i14 << 2;
                                int i17 = i15 - i16;
                                int i18 = i15 + i16;
                                int i19 = this.f;
                                if (i17 >= i19) {
                                    i19 = i17;
                                }
                                int i20 = this.g;
                                if (i18 > i20) {
                                    i18 = i20;
                                }
                                if (this.b == i9) {
                                    i5 = a(sArr, i11, i19, i18);
                                } else {
                                    c(sArr, i11, i9);
                                    i5 = a(this.i, 0, i19, i18);
                                }
                            } else {
                                i5 = a2;
                            }
                        }
                        int i21 = this.u;
                        boolean z = (i21 == 0 || this.s == 0) ? false : this.v > i21 * 3 ? false : (i21 << 1) > this.t * 3;
                        int i22 = z ? this.s : i5;
                        this.t = this.u;
                        this.s = i5;
                        if (d2 > 1.0d) {
                            short[] sArr2 = this.j;
                            if (f5 >= 2.0f) {
                                i7 = (int) (((float) i22) / (f5 - f7));
                            } else {
                                this.r = (int) ((((float) i22) * (2.0f - f5)) / (f5 - f7));
                                i7 = i22;
                            }
                            this.l = a(this.l, this.m, i7);
                            int i23 = i7;
                            short[] sArr3 = sArr2;
                            int i24 = i22;
                            a(i7, this.b, this.l, this.m, sArr3, i11, sArr3, i11 + i22);
                            this.m += i23;
                            i11 += i24 + i23;
                        } else {
                            int i25 = i22;
                            short[] sArr4 = this.j;
                            if (f5 < 0.5f) {
                                i6 = (int) ((((float) i25) * f5) / (f7 - f5));
                            } else {
                                this.r = (int) ((((float) i25) * ((2.0f * f5) - f7)) / (f7 - f5));
                                i6 = i25;
                            }
                            int i26 = i25 + i6;
                            this.l = a(this.l, this.m, i26);
                            int i27 = this.b;
                            System.arraycopy(sArr4, i11 * i27, this.l, this.m * i27, i27 * i25);
                            int i28 = this.m + i25;
                            int i29 = i11 + i25;
                            int i30 = i26;
                            a(i6, this.b, this.l, i28, sArr4, i29, sArr4, i11);
                            this.m += i30;
                            i11 += i6;
                        }
                    }
                    if (this.h + i11 > i10) {
                        break;
                    }
                    f7 = 1.0f;
                    i9 = 1;
                }
                int i31 = this.k - i11;
                short[] sArr5 = this.j;
                int i32 = this.b;
                System.arraycopy(sArr5, i11 * i32, sArr5, 0, i32 * i31);
                this.k = i31;
            }
            f2 = 1.0f;
        } else {
            b(this.j, 0, this.k);
            this.k = 0;
            f2 = 1.0f;
        }
        if (f6 != f2 && this.m != i8) {
            int i33 = this.a;
            int i34 = (int) (((float) i33) / f6);
            while (true) {
                if (i34 <= 16384 && i33 <= 16384) {
                    break;
                }
                i34 /= 2;
                i33 /= 2;
            }
            int i35 = this.m - i8;
            this.n = a(this.n, this.o, i35);
            short[] sArr6 = this.l;
            int i36 = this.b;
            System.arraycopy(sArr6, i8 * i36, this.n, this.o * i36, i36 * i35);
            this.m = i8;
            this.o += i35;
            int i37 = 0;
            while (true) {
                i2 = this.o;
                if (i37 >= i2 - 1) {
                    break;
                }
                while (true) {
                    i3 = this.p;
                    int i38 = (i3 + 1) * i34;
                    i4 = this.q;
                    if (i38 <= i4 * i33) {
                        break;
                    }
                    this.l = a(this.l, this.m, 1);
                    int i39 = 0;
                    while (true) {
                        int i40 = this.b;
                        if (i39 >= i40) {
                            break;
                        }
                        short[] sArr7 = this.l;
                        int i41 = (this.m * i40) + i39;
                        short[] sArr8 = this.n;
                        int i42 = (i37 * i40) + i39;
                        short s2 = sArr8[i42];
                        short s3 = sArr8[i42 + i40];
                        int i43 = this.q * i33;
                        int i44 = this.p;
                        int i45 = i44 * i34;
                        int i46 = (i44 + 1) * i34;
                        int i47 = i46 - i43;
                        int i48 = i46 - i45;
                        sArr7[i41] = (short) (((s2 * i47) + ((i48 - i47) * s3)) / i48);
                        i39++;
                    }
                    this.q++;
                    this.m++;
                }
                this.p = i3 + 1;
                if (this.p == i33) {
                    this.p = 0;
                    qi.c(i4 == i34);
                    this.q = 0;
                }
                i37++;
            }
            int i49 = i2 - 1;
            if (i49 != 0) {
                short[] sArr9 = this.n;
                int i50 = this.b;
                System.arraycopy(sArr9, i49 * i50, sArr9, 0, (i2 - i49) * i50);
                this.o -= i49;
            }
        }
    }

    private static void a(int i2, int i3, short[] sArr, int i4, short[] sArr2, int i5, short[] sArr3, int i6) {
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = (i5 * i3) + i7;
            int i9 = (i6 * i3) + i7;
            int i10 = (i4 * i3) + i7;
            for (int i11 = 0; i11 < i2; i11++) {
                sArr[i10] = (short) (((sArr2[i8] * (i2 - i11)) + (sArr3[i9] * i11)) / i2);
                i10 += i3;
                i8 += i3;
                i9 += i3;
            }
        }
    }
}
