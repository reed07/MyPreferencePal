package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzbtw extends zzbtu<zzbtv, zzbtv> {
    zzbtw() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzbtb zzbtb) {
        return false;
    }

    private static void zza(Object obj, zzbtv zzbtv) {
        ((zzbrd) obj).zzfpu = zzbtv;
    }

    /* access modifiers changed from: 0000 */
    public final void zzs(Object obj) {
        ((zzbrd) obj).zzfpu.zzakj();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int zzac(Object obj) {
        return ((zzbtv) obj).zzamj();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int zzai(Object obj) {
        return ((zzbtv) obj).zzapb();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzh(Object obj, Object obj2) {
        zzbtv zzbtv = (zzbtv) obj;
        zzbtv zzbtv2 = (zzbtv) obj2;
        if (zzbtv2.equals(zzbtv.zzaoz())) {
            return zzbtv;
        }
        return zzbtv.zza(zzbtv, zzbtv2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(Object obj, zzbup zzbup) throws IOException {
        ((zzbtv) obj).zza(zzbup);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, zzbup zzbup) throws IOException {
        ((zzbtv) obj).zzb(zzbup);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzg(Object obj, Object obj2) {
        zza(obj, (zzbtv) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzah(Object obj) {
        zzbtv zzbtv = ((zzbrd) obj).zzfpu;
        if (zzbtv != zzbtv.zzaoz()) {
            return zzbtv;
        }
        zzbtv zzapa = zzbtv.zzapa();
        zza(obj, zzapa);
        return zzapa;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzag(Object obj) {
        return ((zzbrd) obj).zzfpu;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzbtv) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzz(Object obj) {
        zzbtv zzbtv = (zzbtv) obj;
        zzbtv.zzakj();
        return zzbtv;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzaoy() {
        return zzbtv.zzapa();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzbtv) obj).zzc((i << 3) | 3, (zzbtv) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, zzbpu zzbpu) {
        ((zzbtv) obj).zzc((i << 3) | 2, zzbpu);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzbtv) obj).zzc((i << 3) | 1, Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzbtv) obj).zzc((i << 3) | 5, Integer.valueOf(i2));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzbtv) obj).zzc(i << 3, Long.valueOf(j));
    }
}
