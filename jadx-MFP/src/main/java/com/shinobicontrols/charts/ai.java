package com.shinobicontrols.charts;

import android.util.DisplayMetrics;

class ai extends aj<an> implements an {
    private float a = 1.0f;
    private float b = 1.0f;
    private DisplayMetrics c;

    ai() {
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public DisplayMetrics c() {
        return this.c;
    }

    public void a(float f, float f2, DisplayMetrics displayMetrics) {
        this.b = f;
        this.a = f2;
        this.c = displayMetrics;
        d();
    }

    /* access modifiers changed from: 0000 */
    public void a(an anVar) {
        anVar.a(this.b, this.a, this.c);
    }
}
