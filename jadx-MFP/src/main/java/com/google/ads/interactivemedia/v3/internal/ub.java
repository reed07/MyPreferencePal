package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

/* compiled from: IMASDK */
public final class ub {
    private static final byte[] a = {0, 0, 0, 1};
    private static final int[] b = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] c = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair<Integer, Integer> a(byte[] bArr) throws ca {
        return a(new us(bArr), false);
    }

    public static Pair<Integer, Integer> a(us usVar, boolean z) throws ca {
        int a2 = a(usVar);
        int b2 = b(usVar);
        int c2 = usVar.c(4);
        if (a2 == 5 || a2 == 29) {
            b2 = b(usVar);
            a2 = a(usVar);
            if (a2 == 22) {
                c2 = usVar.c(4);
            }
        }
        boolean z2 = true;
        if (z) {
            if (a2 != 17) {
                switch (a2) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        break;
                    default:
                        switch (a2) {
                            case 6:
                            case 7:
                                break;
                            default:
                                switch (a2) {
                                    case 19:
                                    case 20:
                                    case 21:
                                    case 22:
                                    case 23:
                                        break;
                                    default:
                                        StringBuilder sb = new StringBuilder(42);
                                        sb.append("Unsupported audio object type: ");
                                        sb.append(a2);
                                        throw new ca(sb.toString());
                                }
                        }
                }
            }
            usVar.b(1);
            if (usVar.d()) {
                usVar.b(14);
            }
            boolean d = usVar.d();
            if (c2 != 0) {
                if (a2 == 6 || a2 == 20) {
                    usVar.b(3);
                }
                if (d) {
                    if (a2 == 22) {
                        usVar.b(16);
                    }
                    if (a2 == 17 || a2 == 19 || a2 == 20 || a2 == 23) {
                        usVar.b(3);
                    }
                    usVar.b(1);
                }
                switch (a2) {
                    case 17:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        int c3 = usVar.c(2);
                        if (c3 == 2 || c3 == 3) {
                            StringBuilder sb2 = new StringBuilder(33);
                            sb2.append("Unsupported epConfig: ");
                            sb2.append(c3);
                            throw new ca(sb2.toString());
                        }
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }
        int i = c[c2];
        if (i == -1) {
            z2 = false;
        }
        qi.b(z2);
        return Pair.create(Integer.valueOf(b2), Integer.valueOf(i));
    }

    public static byte[] a(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & 120))};
    }

    public static String b(int i, int i2, int i3) {
        return String.format("avc1.%02X%02X%02X", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = a;
        byte[] bArr3 = new byte[(bArr2.length + i2)];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i, bArr3, a.length, i2);
        return bArr3;
    }

    private static int a(us usVar) {
        int c2 = usVar.c(5);
        return c2 == 31 ? usVar.c(6) + 32 : c2;
    }

    private static int b(us usVar) {
        int c2 = usVar.c(4);
        if (c2 == 15) {
            return usVar.c(24);
        }
        qi.b(c2 < 13);
        return b[c2];
    }
}
