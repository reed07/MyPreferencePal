package com.shinobicontrols.charts;

import java.text.DecimalFormat;

class bq extends DecimalFormat {
    private static final long serialVersionUID = 5745069603170376668L;
    private String a;
    private int b;
    private int c = -1;
    private int d;
    private double e = -1.0d;
    private double f;

    bq() {
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        if (!(this.e == this.f && this.c == this.d)) {
            this.a = super.format(this.e);
            this.f = this.e;
            this.d = this.c;
        }
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        if (i != this.b) {
            this.b = i;
            this.e = -Math.pow(10.0d, (double) this.b);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean b(int i) {
        if (i == this.c) {
            return false;
        }
        this.c = i;
        super.setMinimumFractionDigits(this.c);
        super.setMaximumFractionDigits(this.c + 2);
        return true;
    }
}
