package com.shinobicontrols.charts;

class dj<T> {
    T a;
    boolean b;

    dj(T t) {
        this.a = t;
    }

    /* access modifiers changed from: 0000 */
    public void a(T t) {
        this.a = t;
        this.b = true;
    }

    /* access modifiers changed from: 0000 */
    public void b(T t) {
        if (!this.b) {
            this.a = t;
        }
    }
}
