package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayf;
import com.google.android.gms.internal.ads.zzbat;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzcq;
import com.google.android.gms.internal.ads.zzct;
import com.google.android.gms.internal.ads.zzwu;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzark
public final class zzag implements zzcq, Runnable {
    private final List<Object[]> zzbnj;
    private final AtomicReference<zzcq> zzbnk;
    private zzbbi zzbnl;
    private CountDownLatch zzbnm;
    private Context zzsp;

    public zzag(zzbw zzbw) {
        this(zzbw.zzsp, zzbw.zzbsp);
    }

    private zzag(Context context, zzbbi zzbbi) {
        this.zzbnj = new Vector();
        this.zzbnk = new AtomicReference<>();
        this.zzbnm = new CountDownLatch(1);
        this.zzsp = context;
        this.zzbnl = zzbbi;
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            zzayf.zzc(this);
        } else {
            run();
        }
    }

    private final boolean zzjy() {
        try {
            this.zzbnm.await();
            return true;
        } catch (InterruptedException e) {
            zzaxz.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private final void zzjz() {
        if (!this.zzbnj.isEmpty()) {
            for (Object[] objArr : this.zzbnj) {
                if (objArr.length == 1) {
                    ((zzcq) this.zzbnk.get()).zza((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((zzcq) this.zzbnk.get()).zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.zzbnj.clear();
        }
    }

    private static Context zze(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    public final String zza(Context context) {
        if (zzjy()) {
            zzcq zzcq = (zzcq) this.zzbnk.get();
            if (zzcq != null) {
                zzjz();
                return zzcq.zza(zze(context));
            }
        }
        return "";
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        if (zzjy()) {
            zzcq zzcq = (zzcq) this.zzbnk.get();
            if (zzcq != null) {
                zzjz();
                return zzcq.zza(zze(context), str, view, activity);
            }
        }
        return "";
    }

    public final void zzb(View view) {
        zzcq zzcq = (zzcq) this.zzbnk.get();
        if (zzcq != null) {
            zzcq.zzb(view);
        }
    }

    public final void zza(MotionEvent motionEvent) {
        zzcq zzcq = (zzcq) this.zzbnk.get();
        if (zzcq != null) {
            zzjz();
            zzcq.zza(motionEvent);
            return;
        }
        this.zzbnj.add(new Object[]{motionEvent});
    }

    public final void zza(int i, int i2, int i3) {
        zzcq zzcq = (zzcq) this.zzbnk.get();
        if (zzcq != null) {
            zzjz();
            zzcq.zza(i, i2, i3);
            return;
        }
        this.zzbnj.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.zzbnl.zzeow;
            if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcrq)).booleanValue() && z2) {
                z = true;
            }
            this.zzbnk.set(zzct.zza(this.zzbnl.zzdp, zze(this.zzsp), z));
        } finally {
            this.zzbnm.countDown();
            this.zzsp = null;
            this.zzbnl = null;
        }
    }
}
