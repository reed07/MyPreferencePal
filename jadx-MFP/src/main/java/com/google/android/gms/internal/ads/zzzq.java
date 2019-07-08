package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzzq extends zzaux {
    /* access modifiers changed from: private */
    public zzavb zzcnc;

    public final void destroy() throws RemoteException {
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
    }

    public final void resume() throws RemoteException {
    }

    public final void setAppPackageName(String str) {
    }

    public final void setCustomData(String str) throws RemoteException {
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
    }

    public final void show() throws RemoteException {
    }

    public final void zza(zzauu zzauu) throws RemoteException {
    }

    public final void zza(zzxq zzxq) throws RemoteException {
    }

    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zza(zzavh zzavh) throws RemoteException {
        zzbbd.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzbat.zztu.post(new zzzr(this));
    }

    public final void zza(zzavb zzavb) throws RemoteException {
        this.zzcnc = zzavb;
    }

    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }
}
