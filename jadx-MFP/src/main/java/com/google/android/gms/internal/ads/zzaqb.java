package com.google.android.gms.internal.ads;

import java.util.List;

final class zzaqb implements zzbbm<List<zzabr>, zzabm> {
    private final /* synthetic */ String zzdtr;
    private final /* synthetic */ Integer zzdts;
    private final /* synthetic */ Integer zzdtt;
    private final /* synthetic */ int zzdtu;
    private final /* synthetic */ int zzdtv;
    private final /* synthetic */ int zzdtw;
    private final /* synthetic */ int zzdtx;
    private final /* synthetic */ boolean zzdty;

    zzaqb(zzapw zzapw, String str, Integer num, Integer num2, int i, int i2, int i3, int i4, boolean z) {
        this.zzdtr = str;
        this.zzdts = num;
        this.zzdtt = num2;
        this.zzdtu = i;
        this.zzdtv = i2;
        this.zzdtw = i3;
        this.zzdtx = i4;
        this.zzdty = z;
    }

    public final /* synthetic */ Object apply(Object obj) {
        List list = (List) obj;
        Integer num = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        String str = this.zzdtr;
        Integer num2 = this.zzdts;
        Integer num3 = this.zzdtt;
        int i = this.zzdtu;
        if (i > 0) {
            num = Integer.valueOf(i);
        }
        zzabm zzabm = new zzabm(str, list, num2, num3, num, this.zzdtv + this.zzdtw, this.zzdtx, this.zzdty);
        return zzabm;
    }
}
