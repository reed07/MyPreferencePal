package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@zzark
public final class zzaxt implements zzsw {
    private final Object lock = new Object();
    private final zzayb zzejz;
    private final zzaxr zzeka;
    @VisibleForTesting
    private final zzaxp zzekb;
    @VisibleForTesting
    private final HashSet<zzaxh> zzekc = new HashSet<>();
    @VisibleForTesting
    private final HashSet<zzaxs> zzekd = new HashSet<>();

    public zzaxt(String str, zzayb zzayb) {
        this.zzekb = new zzaxp(str, zzayb);
        this.zzejz = zzayb;
        this.zzeka = new zzaxr();
    }

    public final void zza(zzaxs zzaxs) {
        synchronized (this.lock) {
            this.zzekd.add(zzaxs);
        }
    }

    public final void zzb(zzaxh zzaxh) {
        synchronized (this.lock) {
            this.zzekc.add(zzaxh);
        }
    }

    public final void zzb(HashSet<zzaxh> hashSet) {
        synchronized (this.lock) {
            this.zzekc.addAll(hashSet);
        }
    }

    public final Bundle zza(Context context, zzaxq zzaxq) {
        HashSet hashSet = new HashSet();
        synchronized (this.lock) {
            hashSet.addAll(this.zzekc);
            this.zzekc.clear();
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("app", this.zzekb.zzl(context, this.zzeka.zzyw()));
        Bundle bundle2 = new Bundle();
        Iterator it = this.zzekd.iterator();
        while (it.hasNext()) {
            zzaxs zzaxs = (zzaxs) it.next();
            bundle2.putBundle(zzaxs.zzyx(), zzaxs.toBundle());
        }
        bundle.putBundle("slots", bundle2);
        ArrayList arrayList = new ArrayList();
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            arrayList.add(((zzaxh) it2.next()).toBundle());
        }
        bundle.putParcelableArrayList("ads", arrayList);
        zzaxq.zza(hashSet);
        return bundle;
    }

    public final void zzs(boolean z) {
        long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
        if (z) {
            if (currentTimeMillis - this.zzejz.zzzj() > ((Long) zzwu.zzpz().zzd(zzaan.zzcrn)).longValue()) {
                this.zzekb.zzejp = -1;
                return;
            }
            this.zzekb.zzejp = this.zzejz.zzzk();
            return;
        }
        this.zzejz.zzau(currentTimeMillis);
        this.zzejz.zzcw(this.zzekb.zzejp);
    }

    public final void zzxw() {
        synchronized (this.lock) {
            this.zzekb.zzxw();
        }
    }

    public final void zzxv() {
        synchronized (this.lock) {
            this.zzekb.zzxv();
        }
    }

    public final void zzb(zzwb zzwb, long j) {
        synchronized (this.lock) {
            this.zzekb.zzb(zzwb, j);
        }
    }

    public final zzaxh zza(Clock clock, String str) {
        return new zzaxh(clock, this, this.zzeka.zzyv(), str);
    }
}
