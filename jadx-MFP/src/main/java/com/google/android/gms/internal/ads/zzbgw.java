package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.brightcove.player.event.EventType;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzbgw extends zzyq {
    private final Object lock = new Object();
    @GuardedBy("lock")
    private boolean zzcng;
    @GuardedBy("lock")
    private boolean zzcnh;
    @GuardedBy("lock")
    private zzys zzdnv;
    private final zzbdz zzerw;
    private final boolean zzfai;
    private final boolean zzfaj;
    @GuardedBy("lock")
    private boolean zzfak;
    @GuardedBy("lock")
    private boolean zzfal = true;
    @GuardedBy("lock")
    private float zzfam;
    @GuardedBy("lock")
    private float zzfan;
    @GuardedBy("lock")
    private float zzfao;
    @GuardedBy("lock")
    private int zzxe;

    public zzbgw(zzbdz zzbdz, float f, boolean z, boolean z2) {
        this.zzerw = zzbdz;
        this.zzfam = f;
        this.zzfai = z;
        this.zzfaj = z2;
    }

    public final void play() {
        zzf(EventType.PLAY, null);
    }

    public final void pause() {
        zzf(EventType.PAUSE, null);
    }

    public final void mute(boolean z) {
        zzf(z ? "mute" : "unmute", null);
    }

    public final void zzb(zzzw zzzw) {
        zza(zzzw.zzcnf, zzzw.zzcng, zzzw.zzcnh);
    }

    public final void zza(boolean z, boolean z2, boolean z3) {
        synchronized (this.lock) {
            this.zzcng = z2;
            this.zzcnh = z3;
        }
        zzf("initialState", CollectionUtils.mapOf("muteStart", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0", "customControlsRequested", z2 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0", "clickToExpandRequested", z3 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0"));
    }

    private final void zzf(String str, @Nullable Map<String, String> map) {
        HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzbcg.zzepo.execute(new zzbgx(this, hashMap));
    }

    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzfal;
        }
        return z;
    }

    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzxe;
        }
        return i;
    }

    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzfao;
        }
        return f;
    }

    public final float zzqf() {
        float f;
        synchronized (this.lock) {
            f = this.zzfam;
        }
        return f;
    }

    public final float zzqg() {
        float f;
        synchronized (this.lock) {
            f = this.zzfan;
        }
        return f;
    }

    public final void zza(zzys zzys) {
        synchronized (this.lock) {
            this.zzdnv = zzys;
        }
    }

    public final zzys zzqh() throws RemoteException {
        zzys zzys;
        synchronized (this.lock) {
            zzys = this.zzdnv;
        }
        return zzys;
    }

    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzfai && this.zzcng;
        }
        return z;
    }

    public final boolean isClickToExpandEnabled() {
        boolean z;
        boolean isCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (!isCustomControlsEnabled) {
                try {
                    if (this.zzcnh && this.zzfaj) {
                        z = true;
                    }
                } finally {
                }
            }
            z = false;
        }
        return z;
    }

    public final void zze(float f) {
        synchronized (this.lock) {
            this.zzfan = f;
        }
    }

    public final void zzaew() {
        boolean z;
        int i;
        synchronized (this.lock) {
            z = this.zzfal;
            i = this.zzxe;
            this.zzxe = 3;
        }
        zza(i, 3, z, z);
    }

    public final void zza(float f, float f2, int i, boolean z, float f3) {
        boolean z2;
        int i2;
        synchronized (this.lock) {
            this.zzfam = f2;
            this.zzfan = f;
            z2 = this.zzfal;
            this.zzfal = z;
            i2 = this.zzxe;
            this.zzxe = i;
            float f4 = this.zzfao;
            this.zzfao = f3;
            if (Math.abs(this.zzfao - f4) > 1.0E-4f) {
                this.zzerw.getView().invalidate();
            }
        }
        zza(i2, i, z2, z);
    }

    private final void zza(int i, int i2, boolean z, boolean z2) {
        Executor executor = zzbcg.zzepo;
        zzbgy zzbgy = new zzbgy(this, i, i2, z, z2);
        executor.execute(zzbgy);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(int i, int i2, boolean z, boolean z2) {
        synchronized (this.lock) {
            boolean z3 = false;
            boolean z4 = i != i2;
            boolean z5 = !this.zzfak && i2 == 1;
            boolean z6 = z4 && i2 == 1;
            boolean z7 = z4 && i2 == 2;
            boolean z8 = z4 && i2 == 3;
            boolean z9 = z != z2;
            if (this.zzfak || z5) {
                z3 = true;
            }
            this.zzfak = z3;
            if (this.zzdnv != null) {
                if (z5) {
                    try {
                        this.zzdnv.onVideoStart();
                    } catch (RemoteException e) {
                        zzaxz.zzc("Unable to call onVideoStart()", e);
                    }
                }
                if (z6) {
                    try {
                        this.zzdnv.onVideoPlay();
                    } catch (RemoteException e2) {
                        zzaxz.zzc("Unable to call onVideoPlay()", e2);
                    }
                }
                if (z7) {
                    try {
                        this.zzdnv.onVideoPause();
                    } catch (RemoteException e3) {
                        zzaxz.zzc("Unable to call onVideoPause()", e3);
                    }
                }
                if (z8) {
                    try {
                        this.zzdnv.onVideoEnd();
                    } catch (RemoteException e4) {
                        zzaxz.zzc("Unable to call onVideoEnd()", e4);
                    }
                    this.zzerw.zzacc();
                }
                if (z9) {
                    try {
                        this.zzdnv.onVideoMute(z2);
                    } catch (RemoteException e5) {
                        zzaxz.zzc("Unable to call onVideoMute()", e5);
                    }
                }
            } else {
                return;
            }
        }
        return;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzo(Map map) {
        this.zzerw.zza("pubVideoCmd", map);
    }
}
