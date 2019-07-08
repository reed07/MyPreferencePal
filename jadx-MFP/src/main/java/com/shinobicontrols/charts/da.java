package com.shinobicontrols.charts;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class da<T extends Comparable<T>, U> {
    private final Axis<T, U> a;
    private final Comparator<Range<T>> b = new Comparator<Range<T>>() {
        /* renamed from: a */
        public int compare(Range<T> range, Range<T> range2) {
            if (range.a < range2.a) {
                return -1;
            }
            if (range.a > range2.a) {
                return 1;
            }
            if (range.b < range2.b) {
                return -1;
            }
            if (range.b > range2.b) {
                return 1;
            }
            return 0;
        }
    };

    da(Axis<T, U> axis) {
        this.a = axis;
    }

    /* access modifiers changed from: 0000 */
    public List<Range<T>> a(List<Range<T>> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, this.b);
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public List<Range<T>> b(List<Range<T>> list) {
        int i;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2 = i) {
            Range range = (Range) list.get(i2);
            Comparable minimum = range.getMinimum();
            Comparable maximum = range.getMaximum();
            i = i2 + 1;
            while (i < list.size()) {
                Range range2 = (Range) list.get(i);
                if (!range.a(range2, true)) {
                    break;
                }
                range = a(range, range2);
                maximum = range.getMaximum();
                i++;
            }
            if (i - i2 != 1) {
                range = this.a.createRange(minimum, maximum);
            }
            arrayList.add(range);
        }
        return arrayList;
    }

    private Range<T> a(Range<T> range, Range<T> range2) {
        return range.getMaximum().compareTo(range2.getMaximum()) >= 0 ? range : range2;
    }
}
