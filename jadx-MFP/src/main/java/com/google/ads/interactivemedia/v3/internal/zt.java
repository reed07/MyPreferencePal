package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Field;

/* compiled from: IMASDK */
class zt {
    final String a;
    final boolean b;
    final boolean c;
    final /* synthetic */ Field d;
    final /* synthetic */ boolean e;
    final /* synthetic */ xj f;
    final /* synthetic */ wo g;
    final /* synthetic */ abt h;
    final /* synthetic */ boolean i;

    protected zt(String str, boolean z, boolean z2) {
        this.a = str;
        this.b = z;
        this.c = z2;
    }

    public boolean a(Object obj) throws IOException, IllegalAccessException {
        if (this.b && this.d.get(obj) != obj) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(abx abx, Object obj) throws IOException, IllegalAccessException {
        xj xjVar;
        Object obj2 = this.d.get(obj);
        if (this.e) {
            xjVar = this.f;
        } else {
            xjVar = new aab(this.g, this.f, this.h.b());
        }
        xjVar.write(abx, obj2);
    }

    /* access modifiers changed from: 0000 */
    public void a(abu abu, Object obj) throws IOException, IllegalAccessException {
        Object read = this.f.read(abu);
        if (read != null || !this.i) {
            this.d.set(obj, read);
        }
    }

    zt(zr zrVar, String str, boolean z, boolean z2, Field field, boolean z3, xj xjVar, wo woVar, abt abt, boolean z4) {
        this.d = field;
        this.e = z3;
        this.f = xjVar;
        this.g = woVar;
        this.h = abt;
        this.i = z4;
        this(str, z, z2);
    }
}
