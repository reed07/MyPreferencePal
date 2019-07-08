package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.ads.interactivemedia.v3.internal.if reason: invalid class name */
/* compiled from: IMASDK */
public final class Cif implements ic {
    private final ir a;
    private final boolean b;
    private final boolean c;
    private final in d = new in(7, 128);
    private final in e = new in(8, 128);
    private final in f = new in(6, 128);
    private long g;
    private final boolean[] h = new boolean[3];
    private String i;
    private gc j;
    private ig k;
    private boolean l;
    private long m;
    private boolean n;
    private final ut o = new ut();

    public Cif(ir irVar, boolean z, boolean z2) {
        this.a = irVar;
        this.b = z;
        this.c = z2;
    }

    public final void b() {
    }

    public final void a() {
        up.a(this.h);
        this.d.a();
        this.e.a();
        this.f.a();
        this.k.b();
        this.g = 0;
        this.n = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.i = jdVar.c();
        this.j = fsVar.a(jdVar.b(), 2);
        this.k = new ig(this.j, this.b, this.c);
        this.a.a(fsVar, jdVar);
    }

    public final void a(long j2, int i2) {
        this.m = j2;
        this.n |= (i2 & 2) != 0;
    }

    public final void a(ut utVar) {
        int i2;
        int i3;
        int i4;
        byte[] bArr;
        int i5;
        int i6;
        ut utVar2 = utVar;
        int d2 = utVar.d();
        int c2 = utVar.c();
        byte[] bArr2 = utVar2.a;
        this.g += (long) utVar.b();
        this.j.a(utVar2, utVar.b());
        while (true) {
            int a2 = up.a(bArr2, d2, c2, this.h);
            if (a2 == c2) {
                a(bArr2, d2, c2);
                return;
            }
            int b2 = up.b(bArr2, a2);
            int i7 = a2 - d2;
            if (i7 > 0) {
                a(bArr2, d2, a2);
            }
            int i8 = c2 - a2;
            long j2 = this.g - ((long) i8);
            int i9 = i7 < 0 ? -i7 : 0;
            long j3 = this.m;
            if (!this.l || this.k.a()) {
                this.d.b(i9);
                this.e.b(i9);
                if (this.l) {
                    i4 = a2;
                    i5 = c2;
                    bArr = bArr2;
                    i3 = b2;
                    i2 = i8;
                    if (this.d.b()) {
                        this.k.a(up.a(this.d.a, 3, this.d.b));
                        this.d.a();
                    } else if (this.e.b()) {
                        this.k.a(up.b(this.e.a, 3, this.e.b));
                        this.e.a();
                    }
                } else if (!this.d.b() || !this.e.b()) {
                    i4 = a2;
                    i5 = c2;
                    bArr = bArr2;
                    i3 = b2;
                    i2 = i8;
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Arrays.copyOf(this.d.a, this.d.b));
                    arrayList.add(Arrays.copyOf(this.e.a, this.e.b));
                    ur a3 = up.a(this.d.a, 3, this.d.b);
                    uq b3 = up.b(this.e.a, 3, this.e.b);
                    i5 = c2;
                    bArr = bArr2;
                    i4 = a2;
                    i3 = b2;
                    i2 = i8;
                    ur urVar = a3;
                    this.j.a(bs.a(this.i, MimeTypes.VIDEO_H264, ub.b(a3.a, a3.b, a3.c), -1, -1, a3.e, a3.f, -1.0f, (List<byte[]>) arrayList, -1, a3.g, (fa) null));
                    this.l = true;
                    this.k.a(urVar);
                    this.k.a(b3);
                    this.d.a();
                    this.e.a();
                }
            } else {
                i4 = a2;
                i5 = c2;
                bArr = bArr2;
                i3 = b2;
                i2 = i8;
            }
            if (this.f.b(i9)) {
                this.o.a(this.f.a, up.a(this.f.a, this.f.b));
                this.o.c(4);
                this.a.a(j3, this.o);
            }
            if (this.k.a(j2, i2, this.l, this.n)) {
                this.n = false;
            }
            long j4 = this.m;
            if (!this.l || this.k.a()) {
                i6 = i3;
                this.d.a(i6);
                this.e.a(i6);
            } else {
                i6 = i3;
            }
            this.f.a(i6);
            this.k.a(j2, i6, j4);
            d2 = i4 + 3;
            c2 = i5;
            bArr2 = bArr;
        }
    }

    private final void a(byte[] bArr, int i2, int i3) {
        if (!this.l || this.k.a()) {
            this.d.a(bArr, i2, i3);
            this.e.a(bArr, i2, i3);
        }
        this.f.a(bArr, i2, i3);
        this.k.a(bArr, i2, i3);
    }
}
