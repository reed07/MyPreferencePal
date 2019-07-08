package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzud extends zzuc<Object> {
    zzud() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(zzvv zzvv) {
        return zzvv instanceof zzc;
    }

    /* access modifiers changed from: 0000 */
    public final zzuf<Object> zzw(Object obj) {
        return ((zzc) obj).zzbyl;
    }

    /* access modifiers changed from: 0000 */
    public final zzuf<Object> zzx(Object obj) {
        zzc zzc = (zzc) obj;
        if (zzc.zzbyl.isImmutable()) {
            zzc.zzbyl = (zzuf) zzc.zzbyl.clone();
        }
        return zzc.zzbyl;
    }

    /* access modifiers changed from: 0000 */
    public final void zzy(Object obj) {
        zzw(obj).zzsw();
    }

    /* access modifiers changed from: 0000 */
    public final <UT, UB> UB zza(zzwk zzwk, Object obj, zzub zzub, zzuf<Object> zzuf, UB ub, zzxd<UT, UB> zzxd) throws IOException {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final int zzb(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzxy zzxy, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(zzub zzub, zzvv zzvv, int i) {
        return zzub.zza(zzvv, i);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzwk zzwk, Object obj, zzub zzub, zzuf<Object> zzuf) throws IOException {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzte zzte, Object obj, zzub zzub, zzuf<Object> zzuf) throws IOException {
        throw new NoSuchMethodError();
    }
}
