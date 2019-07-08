package com.google.android.gms.internal.icing;

import java.util.Comparator;

final class zzcg implements Comparator<zzce> {
    zzcg() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzce zzce = (zzce) obj;
        zzce zzce2 = (zzce) obj2;
        zzcl zzcl = (zzcl) zzce.iterator();
        zzcl zzcl2 = (zzcl) zzce2.iterator();
        while (zzcl.hasNext() && zzcl2.hasNext()) {
            int compare = Integer.compare(zzce.zza(zzcl.nextByte()), zzce.zza(zzcl2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzce.size(), zzce2.size());
    }
}
