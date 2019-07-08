package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zamd = false;
    private ArrayList<Integer> zame;

    @KeepForSdk
    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract T getEntry(int i, int i2);

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0064, code lost:
        if (r6.mDataHolder.getString(r4, r7, r3) == null) goto L_0x0068;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get(int r7) {
        /*
            r6 = this;
            r6.zacb()
            int r0 = r6.zah(r7)
            r1 = 0
            if (r7 < 0) goto L_0x0068
            java.util.ArrayList<java.lang.Integer> r2 = r6.zame
            int r2 = r2.size()
            if (r7 != r2) goto L_0x0013
            goto L_0x0068
        L_0x0013:
            java.util.ArrayList<java.lang.Integer> r2 = r6.zame
            int r2 = r2.size()
            r3 = 1
            int r2 = r2 - r3
            if (r7 != r2) goto L_0x0031
            com.google.android.gms.common.data.DataHolder r2 = r6.mDataHolder
            int r2 = r2.getCount()
            java.util.ArrayList<java.lang.Integer> r4 = r6.zame
            java.lang.Object r4 = r4.get(r7)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r2 = r2 - r4
            goto L_0x004c
        L_0x0031:
            java.util.ArrayList<java.lang.Integer> r2 = r6.zame
            int r4 = r7 + 1
            java.lang.Object r2 = r2.get(r4)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.util.ArrayList<java.lang.Integer> r4 = r6.zame
            java.lang.Object r4 = r4.get(r7)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r2 = r2 - r4
        L_0x004c:
            if (r2 != r3) goto L_0x0067
            int r7 = r6.zah(r7)
            com.google.android.gms.common.data.DataHolder r3 = r6.mDataHolder
            int r3 = r3.getWindowIndex(r7)
            java.lang.String r4 = r6.getChildDataMarkerColumn()
            if (r4 == 0) goto L_0x0067
            com.google.android.gms.common.data.DataHolder r5 = r6.mDataHolder
            java.lang.String r7 = r5.getString(r4, r7, r3)
            if (r7 != 0) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r1 = r2
        L_0x0068:
            java.lang.Object r7 = r6.getEntry(r0, r1)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.EntityBuffer.get(int):java.lang.Object");
    }

    @KeepForSdk
    public int getCount() {
        zacb();
        return this.zame.size();
    }

    private final void zacb() {
        synchronized (this) {
            if (!this.zamd) {
                int count = this.mDataHolder.getCount();
                this.zame = new ArrayList<>();
                if (count > 0) {
                    this.zame.add(Integer.valueOf(0));
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    int i = 1;
                    while (i < count) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 != null) {
                            if (!string2.equals(string)) {
                                this.zame.add(Integer.valueOf(i));
                                string = string2;
                            }
                            i++;
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                    }
                }
                this.zamd = true;
            }
        }
    }

    private final int zah(int i) {
        if (i >= 0 && i < this.zame.size()) {
            return ((Integer) this.zame.get(i)).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }
}
