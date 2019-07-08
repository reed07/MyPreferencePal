package com.google.android.gms.internal.ads;

import java.util.Comparator;

public final class zzto implements Comparator<zztc> {
    public zzto(zztn zztn) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zztc zztc = (zztc) obj;
        zztc zztc2 = (zztc) obj2;
        if (zztc.zzny() < zztc2.zzny()) {
            return -1;
        }
        if (zztc.zzny() > zztc2.zzny()) {
            return 1;
        }
        if (zztc.zznx() < zztc2.zznx()) {
            return -1;
        }
        if (zztc.zznx() > zztc2.zznx()) {
            return 1;
        }
        float zzoa = (zztc.zzoa() - zztc.zzny()) * (zztc.zznz() - zztc.zznx());
        float zzoa2 = (zztc2.zzoa() - zztc2.zzny()) * (zztc2.zznz() - zztc2.zznx());
        if (zzoa > zzoa2) {
            return -1;
        }
        if (zzoa < zzoa2) {
            return 1;
        }
        return 0;
    }
}
