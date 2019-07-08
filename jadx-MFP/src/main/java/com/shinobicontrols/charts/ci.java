package com.shinobicontrols.charts;

class ci {
    double a = 0.0d;
    double b = 0.0d;
    double c = 0.0d;
    double d = 0.0d;

    ci() {
    }

    /* access modifiers changed from: 0000 */
    public double a() {
        return this.c - this.a;
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        return this.d - this.b;
    }

    /* access modifiers changed from: 0000 */
    public void a(double d2, double d3, double d4, double d5) {
        this.a = d2;
        this.b = d3;
        this.c = d4;
        this.d = d5;
    }

    /* access modifiers changed from: 0000 */
    public void a(double d2, double d3) {
        this.a += d2;
        this.b += d3;
        this.c += d2;
        this.d += d3;
    }

    /* access modifiers changed from: 0000 */
    public void b(double d2, double d3) {
        this.c += d2 - this.a;
        this.d += d3 - this.b;
        this.a = d2;
        this.b = d3;
    }
}
