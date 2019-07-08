package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public final class zzxi extends zzew implements zzxg {
    zzxi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    public final zzxd zzkd() throws RemoteException {
        zzxd zzxd;
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzxd = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader");
            if (queryLocalInterface instanceof zzxd) {
                zzxd = (zzxd) queryLocalInterface;
            } else {
                zzxd = new zzxf(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxd;
    }

    public final void zzb(zzxa zzxa) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzxa);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaeb zzaeb) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaeb);
        zza(3, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaee zzaee) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaee);
        zza(4, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, zzaek zzaek, zzaeh zzaeh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaek);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaeh);
        zza(5, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzacp zzacp) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzacp);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzxz zzxz) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzxz);
        zza(7, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaen zzaen, zzwf zzwf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaen);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        zza(8, obtainAndWriteInterfaceToken);
    }

    public final void zza(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) publisherAdViewOptions);
        zza(9, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaeq zzaeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzaeq);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzafz zzafz) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzafz);
        zza(13, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzagf zzagf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzagf);
        zza(14, obtainAndWriteInterfaceToken);
    }
}
