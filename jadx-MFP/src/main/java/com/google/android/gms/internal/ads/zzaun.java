package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaun extends zzaux {
    private final Context mContext;
    private final Object mLock;
    private final zzbbi zzbob;
    @GuardedBy("mLock")
    private final zzauo zzeeg;

    public zzaun(Context context, zzv zzv, zzalg zzalg, zzbbi zzbbi) {
        zzauo zzauo = new zzauo(context, zzv, zzwf.zzpo(), zzalg, zzbbi);
        this(context, zzbbi, zzauo);
    }

    @VisibleForTesting
    private zzaun(Context context, zzbbi zzbbi, zzauo zzauo) {
        this.mLock = new Object();
        this.mContext = context;
        this.zzbob = zzbbi;
        this.zzeeg = zzauo;
    }

    public final void setAppPackageName(String str) throws RemoteException {
        Context context = this.mContext;
        if (context instanceof zzaum) {
            try {
                ((zzaum) context).setAppPackageName(str);
            } catch (NameNotFoundException unused) {
                if (VERSION.SDK_INT > 15) {
                    throw new RemoteException(NameNotFoundException.class.getSimpleName());
                }
            }
        }
    }

    public final void zza(zzavh zzavh) {
        synchronized (this.mLock) {
            this.zzeeg.zza(zzavh);
        }
    }

    public final void show() {
        synchronized (this.mLock) {
            this.zzeeg.zzxh();
        }
    }

    public final synchronized void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.mContext instanceof zzaum) {
            ((zzaum) this.mContext).zzf((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
        show();
    }

    public final void zza(zzavb zzavb) {
        synchronized (this.mLock) {
            this.zzeeg.zza(zzavb);
        }
    }

    public final void zza(zzauu zzauu) {
        synchronized (this.mLock) {
            this.zzeeg.zza(zzauu);
        }
    }

    public final void zza(zzxq zzxq) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcrk)).booleanValue()) {
            synchronized (this.mLock) {
                this.zzeeg.zza(zzxq);
            }
        }
    }

    public final Bundle getAdMetadata() {
        Bundle adMetadata;
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcrk)).booleanValue()) {
            return new Bundle();
        }
        synchronized (this.mLock) {
            adMetadata = this.zzeeg.getAdMetadata();
        }
        return adMetadata;
    }

    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzeeg.setUserId(str);
        }
    }

    public final void setCustomData(String str) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcrl)).booleanValue()) {
            synchronized (this.mLock) {
                this.zzeeg.zzap(str);
            }
        }
    }

    public final boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.mLock) {
            isLoaded = this.zzeeg.isLoaded();
        }
        return isLoaded;
    }

    public final void pause() {
        zze(null);
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzeeg.pause();
        }
    }

    public final void resume() {
        zzf(null);
    }

    public final void zzf(IObjectWrapper iObjectWrapper) {
        Context context;
        synchronized (this.mLock) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                try {
                    context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
                } catch (Exception e) {
                    zzaxz.zzc("Unable to extract updated context.", e);
                }
            }
            if (context != null) {
                this.zzeeg.onContextChanged(context);
            }
            this.zzeeg.resume();
        }
    }

    public final void destroy() {
        zzg(null);
    }

    public final void zzg(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzeeg.destroy();
        }
    }

    public final String getMediationAdapterClassName() {
        String mediationAdapterClassName;
        synchronized (this.mLock) {
            mediationAdapterClassName = this.zzeeg.getMediationAdapterClassName();
        }
        return mediationAdapterClassName;
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            this.zzeeg.setImmersiveMode(z);
        }
    }
}
