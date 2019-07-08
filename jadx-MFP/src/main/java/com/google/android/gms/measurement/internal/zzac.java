package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzac {
    final String name;
    final long zzahv;
    final long zzahw;
    final long zzahx;
    final long zzahy;
    final Long zzahz;
    final Long zzaia;
    final Long zzaib;
    final Boolean zzaic;
    final String zztt;

    zzac(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Long l3, Boolean bool) {
        long j5 = j;
        long j6 = j2;
        long j7 = j4;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = true;
        Preconditions.checkArgument(j5 >= 0);
        Preconditions.checkArgument(j6 >= 0);
        if (j7 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.zztt = str;
        this.name = str2;
        this.zzahv = j5;
        this.zzahw = j6;
        this.zzahx = j3;
        this.zzahy = j7;
        this.zzahz = l;
        this.zzaia = l2;
        this.zzaib = l3;
        this.zzaic = bool;
    }

    /* access modifiers changed from: 0000 */
    public final zzac zzae(long j) {
        zzac zzac = new zzac(this.zztt, this.name, this.zzahv, this.zzahw, j, this.zzahy, this.zzahz, this.zzaia, this.zzaib, this.zzaic);
        return zzac;
    }

    /* access modifiers changed from: 0000 */
    public final zzac zza(long j, long j2) {
        zzac zzac = new zzac(this.zztt, this.name, this.zzahv, this.zzahw, this.zzahx, j, Long.valueOf(j2), this.zzaia, this.zzaib, this.zzaic);
        return zzac;
    }

    /* access modifiers changed from: 0000 */
    public final zzac zza(Long l, Long l2, Boolean bool) {
        zzac zzac = new zzac(this.zztt, this.name, this.zzahv, this.zzahw, this.zzahx, this.zzahy, this.zzahz, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
        return zzac;
    }
}
