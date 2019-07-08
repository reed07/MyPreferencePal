package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

final class zzbta implements zzbsj {
    private final int flags;
    private final String info;
    private final Object[] zzfsj;
    private final zzbsl zzfsm;

    zzbta(zzbsl zzbsl, String str, Object[] objArr) {
        this.zzfsm = zzbsl;
        this.info = str;
        this.zzfsj = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.flags = c | (charAt2 << i);
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zzaoi() {
        return this.info;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzaoj() {
        return this.zzfsj;
    }

    public final zzbsl zzaob() {
        return this.zzfsm;
    }

    public final int zzanz() {
        return (this.flags & 1) == 1 ? zze.zzfqj : zze.zzfqk;
    }

    public final boolean zzaoa() {
        return (this.flags & 2) == 2;
    }
}
