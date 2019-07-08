package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzp;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzbq {
    private final Set<String> zzbcj;
    private final String zzqn;

    public abstract zzp zzc(Map<String, zzp> map);

    public abstract boolean zznk();

    public String zzot() {
        return this.zzqn;
    }

    public zzbq(String str, String... strArr) {
        this.zzqn = str;
        this.zzbcj = new HashSet(strArr.length);
        for (String add : strArr) {
            this.zzbcj.add(add);
        }
    }

    public Set<String> zzou() {
        return this.zzbcj;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(Set<String> set) {
        return set.containsAll(this.zzbcj);
    }
}
