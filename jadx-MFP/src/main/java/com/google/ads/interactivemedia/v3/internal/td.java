package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public class td implements so {
    private final th a;
    private final String b;
    private final tz c;
    private final int d;
    private final int e;
    private final boolean f;

    public td() {
        this.a = new th();
    }

    public final /* synthetic */ sn a() {
        return a(this.a);
    }

    public td(String str, tz tzVar) {
        this(str, tzVar, 8000, 8000, false);
    }

    public td(String str, tz tzVar, int i, int i2, boolean z) {
        this();
        this.b = str;
        this.c = tzVar;
        this.d = 8000;
        this.e = 8000;
        this.f = false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public sy a(th thVar) {
        sy syVar = new sy(this.b, null, this.d, this.e, this.f, thVar);
        tz tzVar = this.c;
        if (tzVar != null) {
            syVar.a(tzVar);
        }
        return syVar;
    }
}
