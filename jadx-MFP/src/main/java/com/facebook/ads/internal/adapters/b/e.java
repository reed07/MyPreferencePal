package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class e implements Serializable {
    private static final long serialVersionUID = 5306126965868117466L;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final a g;

    public enum a {
        CONTEXTUAL_APP("contextual_app"),
        PAGE_POST("page_post");
        
        private final String c;

        private a(String str) {
            this.c = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0026  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.facebook.ads.internal.adapters.b.e.a a(java.lang.String r2) {
            /*
                int r0 = r2.hashCode()
                r1 = 883765328(0x34ad3050, float:3.2258913E-7)
                if (r0 == r1) goto L_0x0019
                r1 = 1434358835(0x557e9433, float:1.7494529E13)
                if (r0 == r1) goto L_0x000f
                goto L_0x0023
            L_0x000f:
                java.lang.String r0 = "contextual_app"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x0023
                r2 = 0
                goto L_0x0024
            L_0x0019:
                java.lang.String r0 = "page_post"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x0023
                r2 = 1
                goto L_0x0024
            L_0x0023:
                r2 = -1
            L_0x0024:
                if (r2 == 0) goto L_0x0029
                com.facebook.ads.internal.adapters.b.e$a r2 = PAGE_POST
                return r2
            L_0x0029:
                com.facebook.ads.internal.adapters.b.e$a r2 = CONTEXTUAL_APP
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.b.e.a.a(java.lang.String):com.facebook.ads.internal.adapters.b.e$a");
        }
    }

    public static class b {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public a g;

        /* access modifiers changed from: 0000 */
        public b a(String str) {
            this.a = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public e a() {
            return new e(this);
        }

        /* access modifiers changed from: 0000 */
        public b b(String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public b c(String str) {
            this.c = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public b d(String str) {
            this.d = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public b e(String str) {
            this.e = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public b f(String str) {
            this.f = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public b g(String str) {
            this.g = a.a(str);
            return this;
        }
    }

    private e(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.e = bVar.e;
        this.f = bVar.f;
        this.g = bVar.g;
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

    public String e() {
        return this.e;
    }

    public a f() {
        return this.g;
    }

    public String g() {
        return this.f;
    }
}
