package com.shinobicontrols.charts;

import java.lang.Comparable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class f<T extends Comparable<T>> implements aq<T> {
    f() {
    }

    public Set<ap> a(List<Range<T>> list) {
        HashSet hashSet = new HashSet();
        double d = 0.0d;
        hashSet.add(new ap(Double.NEGATIVE_INFINITY, new bj(1.0d, 0.0d)));
        if (list.isEmpty()) {
            return hashSet;
        }
        for (Range range : list) {
            ap a = a(range, d);
            ap b = b(range, d);
            hashSet.add(a);
            hashSet.add(b);
            d += range.b();
        }
        return hashSet;
    }

    private ap a(Range<T> range, double d) {
        return new ap(range.a, new bj(0.0d, range.a - d));
    }

    private ap b(Range<T> range, double d) {
        return new ap(range.b, new bj(1.0d, -(range.b() + d)));
    }
}
