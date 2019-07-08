package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ShowFirstParty
@VisibleForTesting
public abstract class zzef extends zzbq {
    private static final String zzbcf = zzb.ARG0.toString();
    private static final String zzbeb = zzb.ARG1.toString();

    public zzef(String str) {
        super(str, zzbcf, zzbeb);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzp zzp, zzp zzp2, Map<String, zzp> map);

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        Iterator it = map.values().iterator();
        do {
            boolean z = false;
            if (!it.hasNext()) {
                zzp zzp = (zzp) map.get(zzbcf);
                zzp zzp2 = (zzp) map.get(zzbeb);
                if (!(zzp == null || zzp2 == null)) {
                    z = zza(zzp, zzp2, map);
                }
                return zzgj.zzj(Boolean.valueOf(z));
            }
        } while (((zzp) it.next()) != zzgj.zzqq());
        return zzgj.zzj(Boolean.valueOf(false));
    }

    public final /* bridge */ /* synthetic */ Set zzou() {
        return super.zzou();
    }

    public final /* bridge */ /* synthetic */ String zzot() {
        return super.zzot();
    }
}
