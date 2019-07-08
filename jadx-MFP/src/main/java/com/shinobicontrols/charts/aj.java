package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.List;

abstract class aj<T> {
    private final List<T> a = new ArrayList();

    /* access modifiers changed from: 0000 */
    public abstract void a(T t);

    aj() {
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        for (T a2 : this.a) {
            a(a2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        this.a.clear();
    }
}
