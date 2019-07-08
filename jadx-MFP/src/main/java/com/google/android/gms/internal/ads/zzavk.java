package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Future;

@zzark
public final class zzavk extends zzaxv implements zzavq, zzavt, zzavx {
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mErrorCode = 3;
    private final Object mLock;
    public final String zzdml;
    private final zzaxg zzdsk;
    private final zzavy zzeep;
    private final zzavt zzeeq;
    /* access modifiers changed from: private */
    public final String zzeer;
    private final zzakq zzees;
    private final long zzeet;
    private int zzeeu = 0;
    private zzavn zzeev;
    private Future zzeew;
    private volatile zzb zzeex;

    public zzavk(Context context, String str, String str2, zzakq zzakq, zzaxg zzaxg, zzavy zzavy, zzavt zzavt, long j) {
        this.mContext = context;
        this.zzdml = str;
        this.zzeer = str2;
        this.zzees = zzakq;
        this.zzdsk = zzaxg;
        this.zzeep = zzavy;
        this.mLock = new Object();
        this.zzeeq = zzavt;
        this.zzeet = j;
    }

    public final void onStop() {
    }

    public final void zzki() {
        int i;
        zzavy zzavy = this.zzeep;
        if (zzavy != null && zzavy.zzxo() != null && this.zzeep.zzxn() != null) {
            zzavs zzxo = this.zzeep.zzxo();
            zzxo.zza((zzavt) null);
            zzxo.zza((zzavq) this);
            zzxo.zza((zzavx) this);
            zzwb zzwb = this.zzdsk.zzeag.zzdwg;
            zzalj zzxn = this.zzeep.zzxn();
            try {
                if (zzxn.isInitialized()) {
                    zzbat.zztu.post(new zzavl(this, zzwb, zzxn));
                } else {
                    zzbat.zztu.post(new zzavm(this, zzxn, zzwb, zzxo));
                }
            } catch (RemoteException e) {
                zzaxz.zzc("Fail to check if adapter is initialized.", e);
                zza(this.zzdml, 0);
            }
            long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzeeu != 0) {
                        zzavp zzar = new zzavp().zzar(zzbv.zzlm().elapsedRealtime() - elapsedRealtime);
                        if (1 == this.zzeeu) {
                            i = 6;
                        } else {
                            i = this.mErrorCode;
                        }
                        this.zzeev = zzar.zzcu(i).zzdf(this.zzdml).zzdg(this.zzees.zzdkx).zzxm();
                    } else if (!zzaq(elapsedRealtime)) {
                        this.zzeev = new zzavp().zzcu(this.mErrorCode).zzar(zzbv.zzlm().elapsedRealtime() - elapsedRealtime).zzdf(this.zzdml).zzdg(this.zzees.zzdkx).zzxm();
                    }
                }
            }
            zzxo.zza((zzavt) null);
            zzxo.zza((zzavq) null);
            if (this.zzeeu == 1) {
                this.zzeeq.zzde(this.zzdml);
            } else {
                this.zzeeq.zza(this.zzdml, this.mErrorCode);
            }
        }
    }

    public final Future zzxi() {
        Future future = this.zzeew;
        if (future != null) {
            return future;
        }
        zzbcb zzbcb = (zzbcb) zzwa();
        this.zzeew = zzbcb;
        return zzbcb;
    }

    public final zzavn zzxj() {
        zzavn zzavn;
        synchronized (this.mLock) {
            zzavn = this.zzeev;
        }
        return zzavn;
    }

    public final zzakq zzxk() {
        return this.zzees;
    }

    private final boolean zzaq(long j) {
        long elapsedRealtime = this.zzeet - (zzbv.zzlm().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.mErrorCode = 4;
            return false;
        }
        try {
            this.mLock.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            this.mErrorCode = 5;
            return false;
        }
    }

    public final void zzde(String str) {
        synchronized (this.mLock) {
            this.zzeeu = 1;
            this.mLock.notify();
        }
    }

    public final void zza(String str, int i) {
        synchronized (this.mLock) {
            this.zzeeu = 2;
            this.mErrorCode = i;
            this.mLock.notify();
        }
    }

    public final void zzxl() {
        zza(this.zzdsk.zzeag.zzdwg, this.zzeep.zzxn());
    }

    public final void zzct(int i) {
        zza(this.zzdml, 0);
    }

    public final void zza(zzb zzb) {
        this.zzeex = zzb;
    }

    public final void zzc(Bundle bundle) {
        zzb zzb = this.zzeex;
        if (zzb != null) {
            zzb.zza("", bundle);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzwb zzwb, zzalj zzalj) {
        this.zzeep.zzxo().zza((zzavt) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzdml)) {
                zzalj.zza(zzwb, this.zzeer, this.zzees.zzdku);
            } else {
                zzalj.zzc(zzwb, this.zzeer);
            }
        } catch (RemoteException e) {
            zzaxz.zzc("Fail to load ad from adapter.", e);
            zza(this.zzdml, 0);
        }
    }
}
