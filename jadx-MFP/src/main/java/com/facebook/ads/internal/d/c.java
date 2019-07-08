package com.facebook.ads.internal.d;

import java.io.Serializable;

public class c implements Serializable {
    private a a;
    private a b;

    public static class a implements Serializable {
        private double a;
        private double b;
        private double c;
        private double d;
        private double e;
        private double f;
        private double g;
        private int h;
        private double i;
        private double j;
        private double k;

        public a(double d2) {
            this.e = d2;
        }

        public void a() {
            this.a = 0.0d;
            this.c = 0.0d;
            this.d = 0.0d;
            this.f = 0.0d;
            this.h = 0;
            this.i = 0.0d;
            this.j = 1.0d;
            this.k = 0.0d;
        }

        public void a(double d2, double d3) {
            this.h++;
            this.i += d2;
            this.c = d3;
            this.k += d3 * d2;
            this.a = this.k / this.i;
            this.j = Math.min(this.j, d3);
            this.f = Math.max(this.f, d3);
            if (d3 >= this.e) {
                this.d += d2;
                this.b += d2;
                this.g = Math.max(this.g, this.b);
                return;
            }
            this.b = 0.0d;
        }

        public void b() {
            this.b = 0.0d;
        }

        public double c() {
            if (this.h == 0) {
                return 0.0d;
            }
            return this.j;
        }

        public double d() {
            return this.a;
        }

        public double e() {
            return this.f;
        }

        public double f() {
            return this.i;
        }

        public double g() {
            return this.d;
        }

        public double h() {
            return this.g;
        }
    }

    public c() {
        this(0.5d, 0.5d);
    }

    public c(double d) {
        this(d, 0.5d);
    }

    public c(double d, double d2) {
        this.a = new a(d);
        this.b = new a(d2);
        a();
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.a.a();
        this.b.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(double d, double d2) {
        this.a.a(d, d2);
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.a.b();
        this.b.b();
    }

    /* access modifiers changed from: 0000 */
    public void b(double d, double d2) {
        this.b.a(d, d2);
    }

    public a c() {
        return this.a;
    }

    public a d() {
        return this.b;
    }
}
