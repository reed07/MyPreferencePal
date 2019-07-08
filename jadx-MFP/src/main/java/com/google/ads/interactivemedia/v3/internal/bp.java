package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class bp {
    private cb a;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public int d;

    private bp() {
    }

    public final boolean a(cb cbVar) {
        return cbVar != this.a || this.b > 0 || this.c;
    }

    public final void b(cb cbVar) {
        this.a = cbVar;
        this.b = 0;
        this.c = false;
    }

    public final void a(int i) {
        this.b += i;
    }

    public final void b(int i) {
        boolean z = true;
        if (!this.c || this.d == 4) {
            this.c = true;
            this.d = i;
            return;
        }
        if (i != 4) {
            z = false;
        }
        qi.b(z);
    }

    /* synthetic */ bp(byte b2) {
        this();
    }
}
