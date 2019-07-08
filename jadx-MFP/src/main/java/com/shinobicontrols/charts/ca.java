package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.List;

class ca {
    private final bz a = new bz();
    private final bz b = new bz();
    private final List<a> c = new ArrayList();
    private final bz d = new bz();

    private static class a {
        double a;
        double b;
        double c;
        double d;

        private a() {
        }

        static a a(bz bzVar, bz bzVar2) {
            a aVar = new a();
            aVar.a = bzVar.b;
            aVar.b = bzVar.c;
            aVar.c = bzVar2.b;
            aVar.d = bzVar2.c;
            return aVar;
        }
    }

    ca() {
    }

    /* access modifiers changed from: 0000 */
    public bz a(InternalDataPoint internalDataPoint, bz bzVar, InternalDataPoint[] internalDataPointArr, boolean z) {
        double d2;
        double d3;
        a();
        if (z) {
            d3 = bzVar.b;
            d2 = bzVar.c;
        } else {
            d3 = bzVar.c;
            d2 = bzVar.b;
        }
        a(internalDataPointArr, d3, z);
        if (!a(z, d3, d2)) {
            a(internalDataPoint);
        }
        return this.d;
    }

    private void a() {
        bz bzVar = this.a;
        bzVar.b = 0.0d;
        bzVar.c = 0.0d;
        bz bzVar2 = this.b;
        bzVar2.b = 0.0d;
        bzVar2.c = 0.0d;
        this.c.clear();
        bz bzVar3 = this.d;
        bzVar3.b = 0.0d;
        bzVar3.c = 0.0d;
    }

    private void a(InternalDataPoint[] internalDataPointArr, double d2, boolean z) {
        int length = internalDataPointArr.length;
        int i = 0;
        while (i < length - 1) {
            this.a.b = internalDataPointArr[i].c;
            this.a.c = internalDataPointArr[i].d;
            i++;
            this.b.b = internalDataPointArr[i].c;
            this.b.c = internalDataPointArr[i].d;
            a(z);
            double d3 = z ? this.a.b : this.a.c;
            double d4 = z ? this.b.b : this.b.c;
            if (d2 >= d3 && d2 <= d4) {
                this.c.add(a.a(this.a, this.b));
            }
        }
    }

    private void a(boolean z) {
        boolean z2 = true;
        if (!z ? this.a.c <= this.b.c : this.a.b <= this.b.b) {
            z2 = false;
        }
        if (z2) {
            double d2 = this.a.b;
            double d3 = this.a.c;
            this.a.b = this.b.b;
            this.a.c = this.b.c;
            bz bzVar = this.b;
            bzVar.b = d2;
            bzVar.c = d3;
        }
    }

    private boolean a(boolean z, double d2, double d3) {
        boolean z2 = z;
        Object[] array = this.c.toArray();
        int length = array.length;
        int i = 0;
        double d4 = Double.MAX_VALUE;
        boolean z3 = false;
        while (i < length) {
            a aVar = (a) array[i];
            double a2 = a(aVar, z2, d2);
            Object[] objArr = array;
            int i2 = length;
            double d5 = aVar.a + ((aVar.c - aVar.a) * a2);
            int i3 = i;
            boolean z4 = z3;
            double d6 = aVar.b + (a2 * (aVar.d - aVar.b));
            double abs = Math.abs(z2 ? d3 - d6 : d3 - d5);
            if (abs < d4) {
                bz bzVar = this.d;
                bzVar.b = d5;
                bzVar.c = d6;
                d4 = abs;
                z3 = true;
            } else {
                z3 = z4;
            }
            i = i3 + 1;
            array = objArr;
            length = i2;
        }
        return z3;
    }

    private double a(a aVar, boolean z, double d2) {
        double d3 = z ? aVar.a : aVar.b;
        double d4 = z ? aVar.c : aVar.d;
        if (d4 != d3) {
            return Math.abs((d2 - d3) / (d4 - d3));
        }
        return 1.0d;
    }

    private void a(InternalDataPoint internalDataPoint) {
        this.d.b = internalDataPoint.c;
        this.d.c = internalDataPoint.d;
    }
}
