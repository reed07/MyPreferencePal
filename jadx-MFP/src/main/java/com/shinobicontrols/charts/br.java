package com.shinobicontrols.charts;

class br extends cq {
    br(OHLCSeries oHLCSeries) {
        super(false, oHLCSeries);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        bs bsVar = (bs) this.a.p;
        bsVar.a(this.a.n.a());
        int i = 0;
        int i2 = 0;
        while (i < this.a.n.c.length) {
            InternalDataPoint internalDataPoint = this.a.n.c[i];
            int i3 = i2 + 1;
            bsVar.a[i2] = (float) internalDataPoint.c;
            int i4 = i3 + 1;
            bsVar.a[i3] = ((Double) internalDataPoint.j.get("High")).floatValue();
            int i5 = i4 + 1;
            bsVar.a[i4] = ((Double) internalDataPoint.j.get("Open")).floatValue();
            int i6 = i5 + 1;
            bsVar.a[i5] = ((Double) internalDataPoint.j.get("Close")).floatValue();
            int i7 = i6 + 1;
            bsVar.a[i6] = ((Double) internalDataPoint.j.get("Low")).floatValue();
            internalDataPoint.d = (((Double) internalDataPoint.j.get("High")).doubleValue() + ((double) ((Double) internalDataPoint.j.get("Low")).floatValue())) / 2.0d;
            i++;
            i2 = i7;
        }
    }
}