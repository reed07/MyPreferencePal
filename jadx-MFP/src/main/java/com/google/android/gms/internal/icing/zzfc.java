package com.google.android.gms.internal.icing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzfc {
    private static final zzfc zzmy = new zzfc();
    private final zzfg zzmz = new zzeg();
    private final ConcurrentMap<Class<?>, zzff<?>> zzna = new ConcurrentHashMap();

    public static zzfc zzcy() {
        return zzmy;
    }

    public final <T> zzff<T> zze(Class<T> cls) {
        zzdl.zza(cls, "messageType");
        zzff<T> zzff = (zzff) this.zzna.get(cls);
        if (zzff != null) {
            return zzff;
        }
        zzff<T> zzd = this.zzmz.zzd(cls);
        zzdl.zza(cls, "messageType");
        zzdl.zza(zzd, "schema");
        zzff zzff2 = (zzff) this.zzna.putIfAbsent(cls, zzd);
        return zzff2 != null ? zzff2 : zzd;
    }

    public final <T> zzff<T> zzn(T t) {
        return zze(t.getClass());
    }

    private zzfc() {
    }
}
