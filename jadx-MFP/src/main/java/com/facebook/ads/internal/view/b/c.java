package com.facebook.ads.internal.view.b;

import java.util.HashMap;
import java.util.Map;

public class c {
    public final String a;
    public final long b;
    public final long c;
    public final long d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;

    public static class a {
        private final String a;
        private long b = -1;
        private long c = -1;
        private long d = -1;
        private long e = -1;
        private long f = -1;
        private long g = -1;
        private long h = -1;

        public a(String str) {
            this.a = str;
        }

        public a a(long j) {
            this.b = j;
            return this;
        }

        public c a() {
            c cVar = new c(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            return cVar;
        }

        public a b(long j) {
            this.c = j;
            return this;
        }

        public a c(long j) {
            this.d = j;
            return this;
        }

        public a d(long j) {
            this.e = j;
            return this;
        }

        public a e(long j) {
            this.f = j;
            return this;
        }

        public a f(long j) {
            this.g = j;
            return this;
        }

        public a g(long j) {
            this.h = j;
            return this;
        }
    }

    private c(String str, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.a = str;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = j5;
        this.g = j6;
        this.h = j7;
    }

    public Map<String, String> a() {
        HashMap hashMap = new HashMap(7);
        hashMap.put("initial_url", this.a);
        hashMap.put("handler_time_ms", String.valueOf(this.b));
        hashMap.put("load_start_ms", String.valueOf(this.c));
        hashMap.put("response_end_ms", String.valueOf(this.d));
        hashMap.put("dom_content_loaded_ms", String.valueOf(this.e));
        hashMap.put("scroll_ready_ms", String.valueOf(this.f));
        hashMap.put("load_finish_ms", String.valueOf(this.g));
        hashMap.put("session_finish_ms", String.valueOf(this.h));
        return hashMap;
    }
}
