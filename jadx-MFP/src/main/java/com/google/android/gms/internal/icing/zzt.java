package com.google.android.gms.internal.icing;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@ShowFirstParty
@VisibleForTesting
public final class zzt {
    private final String name;
    private int weight = 1;
    private String zzaa;
    private boolean zzab;
    private boolean zzac;
    private String zzaf;
    private final List<zzn> zzah = new ArrayList();

    public zzt(String str) {
        this.name = str;
    }

    public final zzt zzc(String str) {
        this.zzaa = str;
        return this;
    }

    public final zzt zzb(boolean z) {
        this.zzab = true;
        return this;
    }

    public final zzt zzc(boolean z) {
        this.zzac = true;
        return this;
    }

    public final zzt zzd(String str) {
        this.zzaf = str;
        return this;
    }

    public final zzs zzc() {
        String str = this.name;
        String str2 = this.zzaa;
        boolean z = this.zzab;
        int i = this.weight;
        boolean z2 = this.zzac;
        List<zzn> list = this.zzah;
        zzs zzs = new zzs(str, str2, z, i, z2, null, (zzn[]) list.toArray(new zzn[list.size()]), this.zzaf, null);
        return zzs;
    }
}
