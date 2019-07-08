package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zzbpw implements Comparator<zzbpu> {
    zzbpw() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzbpu zzbpu = (zzbpu) obj;
        zzbpu zzbpu2 = (zzbpu) obj2;
        zzbqa zzbqa = (zzbqa) zzbpu.iterator();
        zzbqa zzbqa2 = (zzbqa) zzbpu2.iterator();
        while (zzbqa.hasNext() && zzbqa2.hasNext()) {
            int compare = Integer.compare(zzbpu.zzb(zzbqa.nextByte()), zzbpu.zzb(zzbqa2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzbpu.size(), zzbpu2.size());
    }
}
