package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzd;
import com.myfitnesspal.shared.constants.Constants.BusyStates;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzbqq {
    private static volatile boolean zzfmq = false;
    private static final Class<?> zzfmr = zzamc();
    private static volatile zzbqq zzfms;
    static final zzbqq zzfmt = new zzbqq(true);
    private final Map<zza, zzd<?, ?>> zzfmu;

    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * BusyStates.ALL) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.object == zza.object && this.number == zza.number) {
                return true;
            }
            return false;
        }
    }

    private static Class<?> zzamc() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbqq zzamd() {
        return zzbqp.zzama();
    }

    public static zzbqq zzame() {
        zzbqq zzbqq = zzfms;
        if (zzbqq == null) {
            synchronized (zzbqq.class) {
                zzbqq = zzfms;
                if (zzbqq == null) {
                    zzbqq = zzbqp.zzamb();
                    zzfms = zzbqq;
                }
            }
        }
        return zzbqq;
    }

    static zzbqq zzamb() {
        return zzbrb.zza(zzbqq.class);
    }

    public final <ContainingType extends zzbsl> zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzd) this.zzfmu.get(new zza(containingtype, i));
    }

    zzbqq() {
        this.zzfmu = new HashMap();
    }

    private zzbqq(boolean z) {
        this.zzfmu = Collections.emptyMap();
    }
}
