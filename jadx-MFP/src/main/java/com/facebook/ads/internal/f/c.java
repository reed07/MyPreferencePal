package com.facebook.ads.internal.f;

import android.support.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class c implements Serializable {
    private static final long serialVersionUID = -3209129042070173126L;
    private final int a;
    @Nullable
    private final String b;
    private final String c;
    private final List<c> d;
    private c e;

    c(int i, @Nullable String str, String str2) {
        this.d = new ArrayList();
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    c(String str) {
        this(0, null, str);
    }

    public int a() {
        return this.a;
    }

    public void a(c cVar) {
        cVar.e = this;
        this.d.add(cVar);
    }

    public String b() {
        return this.b;
    }

    @Nullable
    public String c() {
        return this.c;
    }

    public List<c> d() {
        return this.d;
    }

    public c e() {
        return this.e;
    }
}
