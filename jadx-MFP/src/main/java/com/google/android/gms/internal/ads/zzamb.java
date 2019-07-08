package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzamb extends zzyq {
    private final Object mLock = new Object();
    private volatile zzys zzdnv;

    public final void play() throws RemoteException {
        throw new RemoteException();
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void mute(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }

    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }

    public final float zzqf() throws RemoteException {
        throw new RemoteException();
    }

    public final float zzqg() throws RemoteException {
        throw new RemoteException();
    }

    public final void zza(zzys zzys) throws RemoteException {
        synchronized (this.mLock) {
            this.zzdnv = zzys;
        }
    }

    public final zzys zzqh() throws RemoteException {
        zzys zzys;
        synchronized (this.mLock) {
            zzys = this.zzdnv;
        }
        return zzys;
    }

    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }
}
