package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class o implements Serializable {
    private static final long serialVersionUID = 1;
    private final String a;
    private final m b;
    private final e c;
    private final i d;
    private final b e;
    private final d f;
    private final String g;

    private o(String str, String str2, m mVar, e eVar, i iVar, b bVar, d dVar) {
        this.g = str;
        this.a = str2;
        this.b = mVar;
        this.c = eVar;
        this.d = iVar;
        this.e = bVar;
        this.f = dVar;
    }

    public static o a(k kVar) {
        l lVar = (l) kVar.d().get(0);
        o oVar = new o(kVar.e(), kVar.c(), kVar.a(), lVar.a(), lVar.b(), kVar.b(), lVar.c());
        return oVar;
    }

    public static o a(q qVar) {
        o oVar = new o(qVar.e(), qVar.a(), qVar.f(), qVar.g(), qVar.h(), qVar.i(), qVar.j());
        return oVar;
    }

    public String a() {
        return this.g;
    }

    public m b() {
        return this.b;
    }

    public e c() {
        return this.c;
    }

    public i d() {
        return this.d;
    }

    public b e() {
        return this.e;
    }

    public d f() {
        return this.f;
    }

    public String g() {
        return this.a;
    }
}
