package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzagy extends zzavc {
    private final /* synthetic */ zzagj zzdgq;

    zzagy(zzagj zzagj) {
        this.zzdgq = zzagj;
    }

    public final void onRewardedVideoAdLoaded() throws RemoteException {
        this.zzdgq.zzbnj.add(new zzagz(this));
    }

    public final void onRewardedVideoAdOpened() throws RemoteException {
        this.zzdgq.zzbnj.add(new zzaha(this));
    }

    public final void onRewardedVideoStarted() throws RemoteException {
        this.zzdgq.zzbnj.add(new zzahb(this));
    }

    public final void onRewardedVideoAdClosed() throws RemoteException {
        this.zzdgq.zzbnj.add(new zzahc(this));
    }

    public final void zza(zzaur zzaur) throws RemoteException {
        this.zzdgq.zzbnj.add(new zzahd(this, zzaur));
    }

    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        this.zzdgq.zzbnj.add(new zzahe(this));
    }

    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        this.zzdgq.zzbnj.add(new zzahf(this, i));
    }

    public final void onRewardedVideoCompleted() throws RemoteException {
        this.zzdgq.zzbnj.add(new zzahg(this));
    }
}
