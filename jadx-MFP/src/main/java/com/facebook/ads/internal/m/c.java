package com.facebook.ads.internal.m;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.w.b.v;
import com.facebook.appevents.UserDataStore;
import java.util.ArrayList;
import java.util.List;

public class c {
    private List<a> a = new ArrayList();
    private int b = 0;
    private d c;
    @Nullable
    private String d;
    @Nullable
    private String e;

    public c(d dVar, @Nullable String str, @Nullable String str2) {
        this.c = dVar;
        this.d = str;
        this.e = str2;
    }

    public d a() {
        return this.c;
    }

    public void a(a aVar) {
        this.a.add(aVar);
    }

    @Nullable
    public String b() {
        return this.d;
    }

    @Nullable
    public String c() {
        return this.e;
    }

    public int d() {
        return this.a.size();
    }

    public a e() {
        if (this.b >= this.a.size()) {
            return null;
        }
        this.b++;
        return (a) this.a.get(this.b - 1);
    }

    @Nullable
    public String f() {
        int i = this.b;
        if (i <= 0 || i > this.a.size()) {
            return null;
        }
        return ((a) this.a.get(this.b - 1)).c().optString(UserDataStore.CITY);
    }

    public boolean g() {
        return this.c == null || v.a() > this.c.a() + ((long) this.c.l());
    }

    public long h() {
        d dVar = this.c;
        if (dVar != null) {
            return dVar.a() + ((long) this.c.l());
        }
        return -1;
    }
}
