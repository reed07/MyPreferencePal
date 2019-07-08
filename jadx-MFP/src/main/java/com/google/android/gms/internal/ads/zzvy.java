package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzark
public final class zzvy extends RemoteCreator<zzxj> {
    public zzvy() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public final zzxg zza(Context context, String str, zzalg zzalg) {
        try {
            IBinder zza = ((zzxj) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), str, zzalg, 14300000);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (queryLocalInterface instanceof zzxg) {
                return (zzxg) queryLocalInterface;
            }
            return new zzxi(zza);
        } catch (RemoteException | RemoteCreatorException e) {
            zzbbd.zzc("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        if (queryLocalInterface instanceof zzxj) {
            return (zzxj) queryLocalInterface;
        }
        return new zzxk(iBinder);
    }
}
