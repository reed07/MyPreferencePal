package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class m implements Serializable {
    private static final long serialVersionUID = 351643298236575728L;
    private final String a;
    private final String b;
    private final String c;
    private final String d;

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;

        /* access modifiers changed from: 0000 */
        public a a(String str) {
            this.a = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public m a() {
            return new m(this);
        }

        /* access modifiers changed from: 0000 */
        public a b(String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public a c(String str) {
            this.c = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public a d(String str) {
            this.d = str;
            return this;
        }
    }

    private m(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }
}
