package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

public class rr {
    public final int a;
    public final int b;
    public final List<oy> c;
    public final List<ou> d;
    public final List<ou> e;

    public rr(int i, int i2, List<oy> list, List<ou> list2, List<ou> list3) {
        List<ou> list4;
        List<ou> list5;
        this.a = i;
        this.b = i2;
        this.c = Collections.unmodifiableList(list);
        if (list2 == null) {
            list4 = Collections.emptyList();
        } else {
            list4 = Collections.unmodifiableList(list2);
        }
        this.d = list4;
        if (list3 == null) {
            list5 = Collections.emptyList();
        } else {
            list5 = Collections.unmodifiableList(list3);
        }
        this.e = list5;
    }
}
