package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayDeque;

/* compiled from: IMASDK */
class gh {
    private final byte[] a = new byte[8];
    private final ArrayDeque<gf> b = new ArrayDeque<>();
    private final gm c = new gm();
    private gg d;
    private int e;
    private int f;
    private long g;

    public void a(gg ggVar) {
        this.d = ggVar;
    }

    public void a() {
        this.e = 0;
        this.b.clear();
        this.c.a();
    }

    public boolean a(fr frVar) throws IOException, InterruptedException {
        qi.a(this.d);
        while (true) {
            if (this.b.isEmpty() || frVar.c() < ((gf) this.b.peek()).b) {
                if (this.e == 0) {
                    long a2 = this.c.a(frVar, true, false, 4);
                    if (a2 == -2) {
                        a2 = b(frVar);
                    }
                    if (a2 == -1) {
                        return false;
                    }
                    this.f = (int) a2;
                    this.e = 1;
                }
                if (this.e == 1) {
                    this.g = this.c.a(frVar, false, true, 8);
                    this.e = 2;
                }
                int a3 = this.d.a(this.f);
                switch (a3) {
                    case 0:
                        frVar.b((int) this.g);
                        this.e = 0;
                    case 1:
                        long c2 = frVar.c();
                        this.b.push(new gf(this.f, this.g + c2, 0));
                        this.d.a(this.f, c2, this.g);
                        this.e = 0;
                        return true;
                    case 2:
                        long j = this.g;
                        if (j <= 8) {
                            this.d.a(this.f, a(frVar, (int) j));
                            this.e = 0;
                            return true;
                        }
                        StringBuilder sb = new StringBuilder(42);
                        sb.append("Invalid integer size: ");
                        sb.append(j);
                        throw new ca(sb.toString());
                    case 3:
                        long j2 = this.g;
                        if (j2 <= 2147483647L) {
                            this.d.a(this.f, c(frVar, (int) j2));
                            this.e = 0;
                            return true;
                        }
                        StringBuilder sb2 = new StringBuilder(41);
                        sb2.append("String element size: ");
                        sb2.append(j2);
                        throw new ca(sb2.toString());
                    case 4:
                        this.d.a(this.f, (int) this.g, frVar);
                        this.e = 0;
                        return true;
                    case 5:
                        long j3 = this.g;
                        if (j3 == 4 || j3 == 8) {
                            this.d.a(this.f, b(frVar, (int) this.g));
                            this.e = 0;
                            return true;
                        }
                        StringBuilder sb3 = new StringBuilder(40);
                        sb3.append("Invalid float size: ");
                        sb3.append(j3);
                        throw new ca(sb3.toString());
                    default:
                        StringBuilder sb4 = new StringBuilder(32);
                        sb4.append("Invalid element type ");
                        sb4.append(a3);
                        throw new ca(sb4.toString());
                }
            } else {
                this.d.c(((gf) this.b.pop()).a);
                return true;
            }
        }
    }

    private long b(fr frVar) throws IOException, InterruptedException {
        frVar.a();
        while (true) {
            frVar.c(this.a, 0, 4);
            int a2 = gm.a(this.a[0]);
            if (a2 != -1 && a2 <= 4) {
                int a3 = (int) gm.a(this.a, a2, false);
                if (this.d.b(a3)) {
                    frVar.b(a2);
                    return (long) a3;
                }
            }
            frVar.b(1);
        }
    }

    private long a(fr frVar, int i) throws IOException, InterruptedException {
        frVar.b(this.a, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.a[i2] & 255));
        }
        return j;
    }

    private double b(fr frVar, int i) throws IOException, InterruptedException {
        long a2 = a(frVar, i);
        if (i == 4) {
            return (double) Float.intBitsToFloat((int) a2);
        }
        return Double.longBitsToDouble(a2);
    }

    private static String c(fr frVar, int i) throws IOException, InterruptedException {
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        frVar.b(bArr, 0, i);
        while (i > 0 && bArr[i - 1] == 0) {
            i--;
        }
        return new String(bArr, 0, i);
    }
}
