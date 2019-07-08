package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zztf implements Comparator<zztl> {
    zztf(zzte zzte) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zztl zztl = (zztl) obj;
        zztl zztl2 = (zztl) obj2;
        int i = zztl.zzbzg - zztl2.zzbzg;
        if (i != 0) {
            return i;
        }
        return (int) (zztl.value - zztl2.value);
    }
}
