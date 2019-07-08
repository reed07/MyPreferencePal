package com.google.ads.interactivemedia.v3.internal;

import java.util.Set;

/* compiled from: IMASDK */
public class agk {
    static int a(Set<?> set) {
        int i = 0;
        for (Object next : set) {
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    static boolean a(Set<?> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            return set.size() == set2.size() && set.containsAll(set2);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }
}
