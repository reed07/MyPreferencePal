package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: IMASDK */
public abstract class ed implements dj {
    protected int b = -1;
    protected int c = -1;
    protected int d = -1;
    private ByteBuffer e = a;
    private ByteBuffer f = a;
    private boolean g;

    /* access modifiers changed from: protected */
    public void k() {
    }

    /* access modifiers changed from: protected */
    public void l() {
    }

    /* access modifiers changed from: protected */
    public void m() {
    }

    public boolean a() {
        return this.b != -1;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public final int d() {
        return this.b;
    }

    public final void e() {
        this.g = true;
        k();
    }

    public ByteBuffer f() {
        ByteBuffer byteBuffer = this.f;
        this.f = a;
        return byteBuffer;
    }

    public boolean g() {
        return this.g && this.f == a;
    }

    public final void h() {
        this.f = a;
        this.g = false;
        l();
    }

    public final void i() {
        h();
        this.e = a;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        m();
    }

    /* access modifiers changed from: protected */
    public final boolean b(int i, int i2, int i3) {
        if (i == this.b && i2 == this.c && i3 == this.d) {
            return false;
        }
        this.b = i;
        this.c = i2;
        this.d = i3;
        return true;
    }

    /* access modifiers changed from: protected */
    public final ByteBuffer a(int i) {
        if (this.e.capacity() < i) {
            this.e = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.e.clear();
        }
        ByteBuffer byteBuffer = this.e;
        this.f = byteBuffer;
        return byteBuffer;
    }

    /* access modifiers changed from: protected */
    public final boolean j() {
        return this.f.hasRemaining();
    }
}
