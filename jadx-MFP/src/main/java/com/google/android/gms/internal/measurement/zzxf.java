package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzxf extends zzxd<zzxe, zzxe> {
    zzxf() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzwk zzwk) {
        return false;
    }

    private static void zza(Object obj, zzxe zzxe) {
        ((zzuo) obj).zzbyf = zzxe;
    }

    /* access modifiers changed from: 0000 */
    public final void zzy(Object obj) {
        ((zzuo) obj).zzbyf.zzsw();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int zzai(Object obj) {
        return ((zzxe) obj).zzvx();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int zzan(Object obj) {
        return ((zzxe) obj).zzyn();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzh(Object obj, Object obj2) {
        zzxe zzxe = (zzxe) obj;
        zzxe zzxe2 = (zzxe) obj2;
        if (zzxe2.equals(zzxe.zzyl())) {
            return zzxe;
        }
        return zzxe.zza(zzxe, zzxe2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(Object obj, zzxy zzxy) throws IOException {
        ((zzxe) obj).zza(zzxy);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, zzxy zzxy) throws IOException {
        ((zzxe) obj).zzb(zzxy);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzg(Object obj, Object obj2) {
        zza(obj, (zzxe) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzam(Object obj) {
        zzxe zzxe = ((zzuo) obj).zzbyf;
        if (zzxe != zzxe.zzyl()) {
            return zzxe;
        }
        zzxe zzym = zzxe.zzym();
        zza(obj, zzym);
        return zzym;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzal(Object obj) {
        return ((zzuo) obj).zzbyf;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzxe) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzaf(Object obj) {
        zzxe zzxe = (zzxe) obj;
        zzxe.zzsw();
        return zzxe;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzyk() {
        return zzxe.zzym();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzxe) obj).zzb((i << 3) | 3, (Object) (zzxe) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, zzte zzte) {
        ((zzxe) obj).zzb((i << 3) | 2, (Object) zzte);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzxe) obj).zzb((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzxe) obj).zzb((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzxe) obj).zzb(i << 3, (Object) Long.valueOf(j));
    }
}
