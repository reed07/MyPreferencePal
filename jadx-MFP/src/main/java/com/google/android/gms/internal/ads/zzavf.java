package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzark
public final class zzavf extends RemoteCreator<zzauz> {
    public zzavf() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    public final zzauw zza(Context context, zzalg zzalg) {
        try {
            IBinder zza = ((zzauz) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), zzalg, 14300000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            if (queryLocalInterface instanceof zzauw) {
                return (zzauw) queryLocalInterface;
            }
            return new zzauy(zza);
        } catch (RemoteException | RemoteCreatorException e) {
            zzbbd.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        if (queryLocalInterface instanceof zzauz) {
            return (zzauz) queryLocalInterface;
        }
        return new zzava(iBinder);
    }
}
