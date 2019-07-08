package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Series.Orientation;

class j extends cq {
    j(BandSeries bandSeries) {
        super(false, bandSeries);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        k kVar = (k) this.a.p;
        kVar.i = 0;
        kVar.g = 0;
        kVar.h = 0;
        kVar.a(this.a.n.c.length);
        boolean z = true;
        for (int i = 0; i < this.a.n.c.length; i++) {
            InternalDataPoint internalDataPoint = this.a.n.c[i];
            a(i, internalDataPoint, kVar.a, kVar.d, kVar);
            if (internalDataPoint.h) {
                a(kVar.g, internalDataPoint, kVar.c, kVar.f, kVar);
                kVar.g++;
            } else {
                a(kVar.h, internalDataPoint, kVar.b, kVar.e, kVar);
                kVar.h++;
            }
            int i2 = i * 2;
            int a = a(i2);
            if (i2 <= 0) {
                z = kVar.a[a] >= kVar.d[a];
            } else if (kVar.a[a] >= kVar.d[a]) {
                if (!z) {
                    kVar.i++;
                    z = true;
                }
            } else if (z) {
                kVar.i++;
                z = false;
            }
        }
    }

    private int a(int i) {
        return this.a.j == Orientation.HORIZONTAL ? i + 1 : i;
    }

    private void a(int i, InternalDataPoint internalDataPoint, float[] fArr, float[] fArr2, k kVar) {
        int i2 = i * 2;
        if (this.a.j == Orientation.VERTICAL) {
            int i3 = i2 + 1;
            fArr[i3] = (float) internalDataPoint.a;
            fArr[i2] = ((Double) internalDataPoint.j.get("High")).floatValue();
            fArr2[i3] = (float) internalDataPoint.a;
            fArr2[i2] = ((Double) internalDataPoint.j.get("Low")).floatValue();
            internalDataPoint.c = (((Double) internalDataPoint.j.get("High")).doubleValue() + ((double) ((Double) internalDataPoint.j.get("Low")).floatValue())) / 2.0d;
            internalDataPoint.d = internalDataPoint.a;
            return;
        }
        fArr[i2] = (float) internalDataPoint.a;
        int i4 = i2 + 1;
        fArr[i4] = ((Double) internalDataPoint.j.get("High")).floatValue();
        fArr2[i2] = (float) internalDataPoint.a;
        fArr2[i4] = ((Double) internalDataPoint.j.get("Low")).floatValue();
        internalDataPoint.c = internalDataPoint.a;
        internalDataPoint.d = (((Double) internalDataPoint.j.get("High")).doubleValue() + ((double) ((Double) internalDataPoint.j.get("Low")).floatValue())) / 2.0d;
    }
}
