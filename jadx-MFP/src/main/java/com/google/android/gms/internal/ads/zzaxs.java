package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaxs {
    private final Object mLock;
    private final zzaxt zzeia;
    private final String zzeid;
    @GuardedBy("mLock")
    private int zzejx;
    @GuardedBy("mLock")
    private int zzejy;

    public zzaxs(String str) {
        this(zzbv.zzlj().zzys(), str);
    }

    private zzaxs(zzaxt zzaxt, String str) {
        this.mLock = new Object();
        this.zzeia = zzaxt;
        this.zzeid = str;
    }

    public final void zzl(int i, int i2) {
        synchronized (this.mLock) {
            this.zzejx = i;
            this.zzejy = i2;
            this.zzeia.zza(this);
        }
    }

    public final String zzyx() {
        return this.zzeid;
    }

    public final Bundle toBundle() {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.zzejx);
            bundle.putInt("pmnll", this.zzejy);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzaxs zzaxs = (zzaxs) obj;
        String str = this.zzeid;
        if (str != null) {
            return str.equals(zzaxs.zzeid);
        }
        return zzaxs.zzeid == null;
    }

    public final int hashCode() {
        String str = this.zzeid;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
