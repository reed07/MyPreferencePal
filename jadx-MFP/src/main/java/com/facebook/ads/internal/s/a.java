package com.facebook.ads.internal.s;

import android.text.TextUtils;
import com.facebook.ads.internal.w.b.k;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class a {
    private final String a;
    private final double b = (((double) System.currentTimeMillis()) / 1000.0d);
    private final double c;
    private final String d;
    private final Map<String, String> e;
    private final f f;
    private final g g;
    private final boolean h;

    /* renamed from: com.facebook.ads.internal.s.a$a reason: collision with other inner class name */
    public static class C0008a {
        private String a;
        private double b;
        private String c;
        private Map<String, String> d;
        private f e;
        private g f;
        private boolean g;

        public C0008a a(double d2) {
            this.b = d2;
            return this;
        }

        public C0008a a(f fVar) {
            this.e = fVar;
            return this;
        }

        public C0008a a(g gVar) {
            this.f = gVar;
            return this;
        }

        public C0008a a(String str) {
            this.a = str;
            return this;
        }

        public C0008a a(Map<String, String> map) {
            this.d = map;
            return this;
        }

        public C0008a a(boolean z) {
            this.g = z;
            return this;
        }

        public a a() {
            a aVar = new a(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
            return aVar;
        }

        public C0008a b(String str) {
            this.c = str;
            return this;
        }
    }

    public a(String str, double d2, String str2, Map<String, String> map, f fVar, g gVar, boolean z) {
        this.a = str;
        this.c = d2;
        this.d = str2;
        this.f = fVar;
        this.g = gVar;
        this.h = z;
        HashMap hashMap = new HashMap();
        if (map != null && !map.isEmpty()) {
            hashMap.putAll(map);
        }
        if (f()) {
            hashMap.put("analog", k.a(com.facebook.ads.internal.l.a.a()));
        }
        this.e = a(hashMap);
    }

    private static Map<String, String> a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 != null) {
                hashMap.put(str, str2);
            }
        }
        return hashMap;
    }

    public String a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }

    public double c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public Map<String, String> e() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return this.f == f.IMMEDIATE;
    }

    /* access modifiers changed from: 0000 */
    public final boolean g() {
        return !TextUtils.isEmpty(this.a);
    }

    public f h() {
        return this.f;
    }

    public g i() {
        return this.g;
    }
}
