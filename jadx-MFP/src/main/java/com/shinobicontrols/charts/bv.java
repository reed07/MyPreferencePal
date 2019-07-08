package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class bv {
    private final List<ap> a = new ArrayList();

    bv() {
    }

    static bv a() {
        bv bvVar = new bv();
        ap apVar = new ap(Double.NEGATIVE_INFINITY, new bj(1.0d, 0.0d));
        HashSet hashSet = new HashSet();
        hashSet.add(apVar);
        bvVar.a((Set<ap>) hashSet);
        return bvVar;
    }

    /* access modifiers changed from: 0000 */
    public double a(double d) {
        ap b = b(d);
        if (b == null) {
            return Double.NaN;
        }
        return b.c.a(d);
    }

    /* access modifiers changed from: 0000 */
    public ap b(double d) {
        if (!this.a.isEmpty()) {
            int i = 0;
            if (d >= ((ap) this.a.get(0)).b) {
                while (i < this.a.size() - 1) {
                    ap apVar = (ap) this.a.get(i);
                    i++;
                    ap apVar2 = (ap) this.a.get(i);
                    if (d >= apVar.b && d < apVar2.b) {
                        return apVar;
                    }
                }
                List<ap> list = this.a;
                return (ap) list.get(list.size() - 1);
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(Set<ap> set) {
        this.a.clear();
        this.a.addAll(set);
        Collections.sort(this.a, ap.a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.a.size(); i++) {
            sb.append(((ap) this.a.get(i)).toString());
            if (i != this.a.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
