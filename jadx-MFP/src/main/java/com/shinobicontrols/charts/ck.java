package com.shinobicontrols.charts;

import java.lang.Comparable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ck<T extends Comparable<T>> implements aq<T> {
    ck() {
    }

    public Set<ap> a(List<Range<T>> list) {
        HashSet hashSet = new HashSet();
        double d = 0.0d;
        hashSet.add(new ap(Double.NEGATIVE_INFINITY, new bj(1.0d, 0.0d)));
        if (list.isEmpty()) {
            return hashSet;
        }
        for (Range range : list) {
            hashSet.add(new ap(range.a - d, new bj(1.0d, range.b() + d)));
            d += range.b();
        }
        return hashSet;
    }
}
