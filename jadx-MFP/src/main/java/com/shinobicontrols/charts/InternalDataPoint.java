package com.shinobicontrols.charts;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class InternalDataPoint {
    static a k = new a();
    static b l = new b();
    double a;
    double b;
    double c;
    double d;
    int e = 0;
    double f = 0.0d;
    double g = 0.0d;
    boolean h = false;
    int i;
    Map<String, Double> j;

    static class a implements Comparator<InternalDataPoint> {
        a() {
        }

        /* renamed from: a */
        public int compare(InternalDataPoint internalDataPoint, InternalDataPoint internalDataPoint2) {
            return Double.compare(internalDataPoint.a, internalDataPoint2.a);
        }
    }

    static class b implements Comparator<InternalDataPoint> {
        b() {
        }

        /* renamed from: a */
        public int compare(InternalDataPoint internalDataPoint, InternalDataPoint internalDataPoint2) {
            return Double.compare(internalDataPoint.b, internalDataPoint2.b);
        }
    }

    InternalDataPoint() {
    }

    InternalDataPoint(double d2, double d3) {
        this.a = d2;
        this.b = d3;
        this.c = d2;
        this.d = d3;
    }

    /* access modifiers changed from: 0000 */
    public void a(double d2, double d3, double d4, double d5) {
        a(d4, d3);
        this.j.put("Open", Double.valueOf(d2));
        this.j.put("Close", Double.valueOf(d5));
    }

    /* access modifiers changed from: 0000 */
    public void a(double d2, double d3) {
        if (this.j == null) {
            this.j = new HashMap();
        }
        this.j.put("Low", Double.valueOf(d2));
        this.j.put("High", Double.valueOf(d3));
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return ((Double) this.j.get("Close")).doubleValue() > ((Double) this.j.get("Open")).doubleValue();
    }
}
