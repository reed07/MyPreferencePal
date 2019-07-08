package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
public final class zzab extends zzi<zzab> {
    private final Map<String, Object> zzsy = new HashMap();

    public final String toString() {
        return zza((Object) this.zzsy);
    }

    public final void set(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        Preconditions.checkNotEmpty(str, "Name can not be empty or \"&\"");
        this.zzsy.put(str, str2);
    }

    public final Map<String, Object> zzaw() {
        return Collections.unmodifiableMap(this.zzsy);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzab zzab = (zzab) zzi;
        Preconditions.checkNotNull(zzab);
        zzab.zzsy.putAll(this.zzsy);
    }
}
