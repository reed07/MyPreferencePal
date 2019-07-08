package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: IMASDK */
public final class ux {
    private static final Comparator<va> a = uy.a;
    private static final Comparator<va> b = uz.a;
    private final int c;
    private final ArrayList<va> d = new ArrayList<>();
    private final va[] e = new va[5];
    private int f = -1;
    private int g;
    private int h;
    private int i;

    public ux(int i2) {
        this.c = i2;
    }

    public final void a() {
        this.d.clear();
        this.f = -1;
        this.g = 0;
        this.h = 0;
    }

    public final void a(int i2, float f2) {
        va vaVar;
        if (this.f != 1) {
            Collections.sort(this.d, a);
            this.f = 1;
        }
        int i3 = this.i;
        if (i3 > 0) {
            va[] vaVarArr = this.e;
            int i4 = i3 - 1;
            this.i = i4;
            vaVar = vaVarArr[i4];
        } else {
            vaVar = new va(0);
        }
        int i5 = this.g;
        this.g = i5 + 1;
        vaVar.a = i5;
        vaVar.b = i2;
        vaVar.c = f2;
        this.d.add(vaVar);
        this.h += i2;
        while (true) {
            int i6 = this.h;
            int i7 = this.c;
            if (i6 > i7) {
                int i8 = i6 - i7;
                va vaVar2 = (va) this.d.get(0);
                if (vaVar2.b <= i8) {
                    this.h -= vaVar2.b;
                    this.d.remove(0);
                    int i9 = this.i;
                    if (i9 < 5) {
                        va[] vaVarArr2 = this.e;
                        this.i = i9 + 1;
                        vaVarArr2[i9] = vaVar2;
                    }
                } else {
                    vaVar2.b -= i8;
                    this.h -= i8;
                }
            } else {
                return;
            }
        }
    }

    public final float a(float f2) {
        if (this.f != 0) {
            Collections.sort(this.d, b);
            this.f = 0;
        }
        float f3 = ((float) this.h) * 0.5f;
        int i2 = 0;
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            va vaVar = (va) this.d.get(i3);
            i2 += vaVar.b;
            if (((float) i2) >= f3) {
                return vaVar.c;
            }
        }
        if (this.d.isEmpty()) {
            return Float.NaN;
        }
        ArrayList<va> arrayList = this.d;
        return ((va) arrayList.get(arrayList.size() - 1)).c;
    }

    static final /* synthetic */ int b(va vaVar, va vaVar2) {
        return vaVar.a - vaVar2.a;
    }
}
