package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzbsy {
    private static final zzbsy zzftb = new zzbsy();
    private final zzbtd zzftc;
    private final ConcurrentMap<Class<?>, zzbtc<?>> zzftd = new ConcurrentHashMap();

    public static zzbsy zzaog() {
        return zzftb;
    }

    public final <T> zzbtc<T> zzf(Class<T> cls) {
        zzbrf.zza(cls, "messageType");
        zzbtc<T> zzbtc = (zzbtc) this.zzftd.get(cls);
        if (zzbtc != null) {
            return zzbtc;
        }
        zzbtc<T> zze = this.zzftc.zze(cls);
        zzbrf.zza(cls, "messageType");
        zzbrf.zza(zze, "schema");
        zzbtc zzbtc2 = (zzbtc) this.zzftd.putIfAbsent(cls, zze);
        return zzbtc2 != null ? zzbtc2 : zze;
    }

    public final <T> zzbtc<T> zzaf(T t) {
        return zzf(t.getClass());
    }

    private zzbsy() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzbtd zzbtd = null;
        for (int i = 0; i <= 0; i++) {
            zzbtd = zzgc(strArr[0]);
            if (zzbtd != null) {
                break;
            }
        }
        if (zzbtd == null) {
            zzbtd = new zzbsa();
        }
        this.zzftc = zzbtd;
    }

    private static zzbtd zzgc(String str) {
        try {
            return (zzbtd) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }
}
