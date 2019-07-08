package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;

final class zzfe implements zzeo {
    private final int flags;
    private final String info;
    private final Object[] zzmg;
    private final zzeq zzmj;

    zzfe(zzeq zzeq, String str, Object[] objArr) {
        this.zzmj = zzeq;
        this.info = str;
        this.zzmg = objArr;
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
    public final String zzda() {
        return this.info;
    }

    /* access modifiers changed from: 0000 */
    public final Object[] zzdb() {
        return this.zzmg;
    }

    public final zzeq zzct() {
        return this.zzmj;
    }

    public final int zzcr() {
        return (this.flags & 1) == 1 ? zzd.zzki : zzd.zzkj;
    }

    public final boolean zzcs() {
        return (this.flags & 2) == 2;
    }
}
