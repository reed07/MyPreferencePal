package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaxh {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private boolean zzebk = false;
    /* access modifiers changed from: private */
    public final Clock zzehz;
    private final zzaxt zzeia;
    @GuardedBy("mLock")
    private final LinkedList<zzaxi> zzeib;
    private final String zzeic;
    private final String zzeid;
    @GuardedBy("mLock")
    private long zzeie = -1;
    @GuardedBy("mLock")
    private long zzeif = -1;
    @GuardedBy("mLock")
    private long zzeig = -1;
    @GuardedBy("mLock")
    private long zzeih = 0;
    @GuardedBy("mLock")
    private long zzeii = -1;
    @GuardedBy("mLock")
    private long zzeij = -1;

    zzaxh(Clock clock, zzaxt zzaxt, String str, String str2) {
        this.zzehz = clock;
        this.zzeia = zzaxt;
        this.zzeic = str;
        this.zzeid = str2;
        this.zzeib = new LinkedList<>();
    }

    public final void zzn(zzwb zzwb) {
        synchronized (this.mLock) {
            this.zzeii = this.zzehz.elapsedRealtime();
            this.zzeia.zzb(zzwb, this.zzeii);
        }
    }

    public final void zzas(long j) {
        synchronized (this.mLock) {
            this.zzeij = j;
            if (this.zzeij != -1) {
                this.zzeia.zzb(this);
            }
        }
    }

    public final void zzat(long j) {
        synchronized (this.mLock) {
            if (this.zzeij != -1) {
                this.zzeie = j;
                this.zzeia.zzb(this);
            }
        }
    }

    public final void zzxv() {
        synchronized (this.mLock) {
            if (this.zzeij != -1 && this.zzeif == -1) {
                this.zzeif = this.zzehz.elapsedRealtime();
                this.zzeia.zzb(this);
            }
            this.zzeia.zzxv();
        }
    }

    public final void zzxw() {
        synchronized (this.mLock) {
            if (this.zzeij != -1) {
                zzaxi zzaxi = new zzaxi(this);
                zzaxi.zzyb();
                this.zzeib.add(zzaxi);
                this.zzeih++;
                this.zzeia.zzxw();
                this.zzeia.zzb(this);
            }
        }
    }

    public final void zzxx() {
        synchronized (this.mLock) {
            if (this.zzeij != -1 && !this.zzeib.isEmpty()) {
                zzaxi zzaxi = (zzaxi) this.zzeib.getLast();
                if (zzaxi.zzxz() == -1) {
                    zzaxi.zzya();
                    this.zzeia.zzb(this);
                }
            }
        }
    }

    public final void zzaj(boolean z) {
        synchronized (this.mLock) {
            if (this.zzeij != -1) {
                this.zzeig = this.zzehz.elapsedRealtime();
                if (!z) {
                    this.zzeif = this.zzeig;
                    this.zzeia.zzb(this);
                }
            }
        }
    }

    public final void zzak(boolean z) {
        synchronized (this.mLock) {
            if (this.zzeij != -1) {
                this.zzebk = z;
                this.zzeia.zzb(this);
            }
        }
    }

    public final Bundle toBundle() {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzeic);
            bundle.putString("slotid", this.zzeid);
            bundle.putBoolean("ismediation", this.zzebk);
            bundle.putLong("treq", this.zzeii);
            bundle.putLong("tresponse", this.zzeij);
            bundle.putLong("timp", this.zzeif);
            bundle.putLong("tload", this.zzeig);
            bundle.putLong("pcc", this.zzeih);
            bundle.putLong("tfetch", this.zzeie);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzeib.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzaxi) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }

    public final String zzxy() {
        return this.zzeic;
    }
}
