package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.zzgj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VisibleForTesting
public final class zzrl {
    private String version;
    private final List<zzrm> zzbox;
    private final Map<String, List<zzri>> zzboy;
    private int zzph;

    private zzrl() {
        this.zzbox = new ArrayList();
        this.zzboy = new HashMap();
        this.version = "";
        this.zzph = 0;
    }

    public final zzrl zzb(zzrm zzrm) {
        this.zzbox.add(zzrm);
        return this;
    }

    public final zzrl zzc(zzri zzri) {
        String zzc = zzgj.zzc((zzp) zzri.zzsi().get(zzb.INSTANCE_NAME.toString()));
        List list = (List) this.zzboy.get(zzc);
        if (list == null) {
            list = new ArrayList();
            this.zzboy.put(zzc, list);
        }
        list.add(zzri);
        return this;
    }

    public final zzrl zzfi(String str) {
        this.version = str;
        return this;
    }

    public final zzrl zzah(int i) {
        this.zzph = i;
        return this;
    }

    public final zzrk zztd() {
        zzrk zzrk = new zzrk(this.zzbox, this.zzboy, this.version, this.zzph);
        return zzrk;
    }
}
