package com.google.android.gms.internal.measurement;

import java.util.Comparator;

final class zztg implements Comparator<zzte> {
    zztg() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzte zzte = (zzte) obj;
        zzte zzte2 = (zzte) obj2;
        zztl zztl = (zztl) zzte.iterator();
        zztl zztl2 = (zztl) zzte2.iterator();
        while (zztl.hasNext() && zztl2.hasNext()) {
            int compare = Integer.compare(zzte.zza(zztl.nextByte()), zzte.zza(zztl2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzte.size(), zzte2.size());
    }
}
