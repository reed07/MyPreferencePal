package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class hq {
    private static final int[] a = {vf.h("isom"), vf.h("iso2"), vf.h("iso3"), vf.h("iso4"), vf.h("iso5"), vf.h("iso6"), vf.h("avc1"), vf.h("hvc1"), vf.h("hev1"), vf.h("av01"), vf.h("mp41"), vf.h("mp42"), vf.h("3g2a"), vf.h("3g2b"), vf.h("3gr6"), vf.h("3gs6"), vf.h("3ge6"), vf.h("3gg6"), vf.h("M4V "), vf.h("M4A "), vf.h("f4v "), vf.h("kddi"), vf.h("M4VP"), vf.h("qt  "), vf.h("MSNV"), vf.h("dby1")};

    public static boolean a(fr frVar) throws IOException, InterruptedException {
        return a(frVar, true);
    }

    public static boolean b(fr frVar) throws IOException, InterruptedException {
        return a(frVar, false);
    }

    private static boolean a(fr frVar, boolean z) throws IOException, InterruptedException {
        boolean z2;
        boolean z3;
        fr frVar2 = frVar;
        long d = frVar.d();
        long j = 4096;
        long j2 = -1;
        int i = (d > -1 ? 1 : (d == -1 ? 0 : -1));
        if (i != 0 && d <= 4096) {
            j = d;
        }
        int i2 = (int) j;
        ut utVar = new ut(64);
        boolean z4 = false;
        int i3 = i2;
        int i4 = 0;
        boolean z5 = false;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            utVar.a(8);
            frVar2.c(utVar.a, z4 ? 1 : 0, 8);
            long j3 = utVar.j();
            int l = utVar.l();
            int i5 = 16;
            if (j3 == 1) {
                frVar2.c(utVar.a, 8, 8);
                utVar.b(16);
                j3 = utVar.m();
            } else {
                if (j3 == 0) {
                    long d2 = frVar.d();
                    if (d2 != j2) {
                        j3 = (d2 - frVar.b()) + 8;
                        i5 = 8;
                    }
                }
                i5 = 8;
            }
            if (i == 0 || ((long) i4) + j3 <= d) {
                long j4 = (long) i5;
                if (j3 >= j4) {
                    i4 += i5;
                    if (l != gu.O) {
                        if (l != gu.V && l != gu.X) {
                            long j5 = j3;
                            int i6 = i3;
                            if ((((long) i4) + j3) - j4 >= ((long) i6)) {
                                break;
                            }
                            int i7 = (int) (j5 - j4);
                            i4 += i7;
                            if (l != gu.a) {
                                if (i7 != 0) {
                                    frVar2.c(i7);
                                }
                                i3 = i6;
                                j2 = -1;
                                z4 = false;
                            } else if (i7 < 8) {
                                return false;
                            } else {
                                utVar.a(i7);
                                frVar2.c(utVar.a, 0, i7);
                                int i8 = i7 / 4;
                                int i9 = 0;
                                while (true) {
                                    if (i9 >= i8) {
                                        break;
                                    }
                                    if (i9 == 1) {
                                        utVar.d(4);
                                    } else {
                                        int l2 = utVar.l();
                                        if ((l2 >>> 8) != vf.h("3gp")) {
                                            int[] iArr = a;
                                            int length = iArr.length;
                                            int i10 = 0;
                                            while (true) {
                                                if (i10 >= length) {
                                                    z3 = false;
                                                    break;
                                                } else if (iArr[i10] == l2) {
                                                    z3 = true;
                                                    break;
                                                } else {
                                                    i10++;
                                                }
                                            }
                                        } else {
                                            z3 = true;
                                        }
                                        if (z3) {
                                            z5 = true;
                                            break;
                                        }
                                    }
                                    i9++;
                                }
                                if (!z5) {
                                    return false;
                                }
                                i3 = i6;
                                j2 = -1;
                                z4 = false;
                            }
                        } else {
                            z2 = true;
                        }
                    } else {
                        i3 += (int) j3;
                        if (i == 0 || ((long) i3) <= d) {
                            j2 = -1;
                        } else {
                            i3 = (int) d;
                            j2 = -1;
                        }
                    }
                } else {
                    return z4;
                }
            } else {
                return z4;
            }
        }
        z2 = false;
        return z5 && z == z2;
    }
}
