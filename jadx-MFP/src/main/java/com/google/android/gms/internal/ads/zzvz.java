package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzark
public final class zzvz extends RemoteCreator<zzxo> {
    @VisibleForTesting
    public zzvz() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public final zzxl zza(Context context, zzwf zzwf, String str, zzalg zzalg, int i) {
        try {
            IBinder zza = ((zzxo) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), zzwf, str, zzalg, 14300000, i);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzxl) {
                return (zzxl) queryLocalInterface;
            }
            return new zzxn(zza);
        } catch (RemoteException | RemoteCreatorException e) {
            zzbbd.zza("Could not create remote AdManager.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        if (queryLocalInterface instanceof zzxo) {
            return (zzxo) queryLocalInterface;
        }
        return new zzxp(iBinder);
    }
}
