package com.facebook.ads.internal.u;

import com.facebook.ads.internal.m.c;

class e {
    private final a a;
    private final c b;
    private final String c;
    private final String d;
    private final String e;

    enum a {
        UNKNOWN,
        ERROR,
        ADS
    }

    e(a aVar) {
        this(aVar, null, null, null, null);
    }

    e(a aVar, c cVar, String str, String str2, String str3) {
        this.a = aVar;
        this.b = cVar;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public c a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public a b() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public String d() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public String e() {
        return this.e;
    }
}
