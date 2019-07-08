package com.facebook.ads.internal.view.e.a;

import com.facebook.ads.internal.adapters.b.l;
import java.util.HashMap;
import java.util.Map;

public class b {
    private final int a;
    private final int b;
    private final l c;

    b(int i, int i2, l lVar) {
        this.a = i;
        this.b = i2;
        this.c = lVar;
    }

    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("");
        hashMap.put("cardind", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.b);
        sb2.append("");
        hashMap.put("cardcnt", sb2.toString());
        return hashMap;
    }

    public int b() {
        return this.a;
    }

    public l c() {
        return this.c;
    }
}
