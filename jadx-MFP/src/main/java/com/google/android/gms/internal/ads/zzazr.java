package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzazr {
    /* access modifiers changed from: private */
    public final List<String> zzemt = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> zzemu = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> zzemv = new ArrayList();

    public final zzazr zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zzemt.size()) {
            double doubleValue = ((Double) this.zzemv.get(i)).doubleValue();
            double doubleValue2 = ((Double) this.zzemu.get(i)).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            i++;
        }
        this.zzemt.add(i, str);
        this.zzemv.add(i, Double.valueOf(d));
        this.zzemu.add(i, Double.valueOf(d2));
        return this;
    }

    public final zzazo zzaaj() {
        return new zzazo(this);
    }
}
