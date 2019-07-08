package com.facebook.ads.internal.s;

import java.util.HashMap;
import java.util.Map;

public class h {
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("is_timeout", Boolean.toString(this.a));
        hashMap.put("ad_count", Integer.toString(this.b));
        hashMap.put("default_ad_index", Integer.toString(this.c));
        hashMap.put("selected_ad_index", Integer.toString(this.d));
        hashMap.put("elapsed_time_from_timer_ms", Integer.toString(this.e));
        hashMap.put("countdown_time_ms", Integer.toString(this.f));
        return hashMap;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b(int i) {
        this.c = i;
    }

    public void c(int i) {
        this.d = i;
    }

    public void d(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.f = i;
    }
}
