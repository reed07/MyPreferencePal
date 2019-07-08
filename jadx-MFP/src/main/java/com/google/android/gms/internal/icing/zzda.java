package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzda extends zzcz<Object> {
    zzda() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(zzeq zzeq) {
        return zzeq instanceof zzc;
    }

    /* access modifiers changed from: 0000 */
    public final zzdc<Object> zzc(Object obj) {
        return ((zzc) obj).zzjz;
    }

    /* access modifiers changed from: 0000 */
    public final zzdc<Object> zzd(Object obj) {
        zzc zzc = (zzc) obj;
        if (zzc.zzjz.isImmutable()) {
            zzc.zzjz = (zzdc) zzc.zzjz.clone();
        }
        return zzc.zzjz;
    }

    /* access modifiers changed from: 0000 */
    public final void zze(Object obj) {
        zzc(obj).zzaj();
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzgr zzgr, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }
}
