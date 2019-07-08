package com.shinobicontrols.charts;

class ao {
    NumberRange a = new NumberRange();
    NumberRange b = new NumberRange();
    InternalDataPoint[] c = new InternalDataPoint[0];

    ao() {
    }

    /* access modifiers changed from: 0000 */
    public Object[] a(DataAdapter<?, ?> dataAdapter) {
        Object[] array = dataAdapter.getDataPointsForDisplay().toArray();
        this.a = new NumberRange();
        this.b = new NumberRange();
        return array;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        InternalDataPoint[] internalDataPointArr = this.c;
        if (internalDataPointArr == null || internalDataPointArr.length != i) {
            this.c = new InternalDataPoint[i];
        }
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.c.length;
    }
}
