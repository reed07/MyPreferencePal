package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzani extends zzew implements zzang {
    zzani(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzwf zzwf, zzanj zzanj) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle2);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzanj);
        zza(1, obtainAndWriteInterfaceToken);
    }

    public final zzans zzvi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        zzans zzans = (zzans) zzey.zza(transactAndReadException, zzans.CREATOR);
        transactAndReadException.recycle();
        return zzans;
    }

    public final zzans zzvj() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        zzans zzans = (zzans) zzey.zza(transactAndReadException, zzans.CREATOR);
        transactAndReadException.recycle();
        return zzans;
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzamy zzamy, zzalm zzalm, zzwf zzwf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzamy);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        zza(4, obtainAndWriteInterfaceToken);
    }

    public final zzyp getVideoController() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        zzyp zzg = zzyq.zzg(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzg;
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzana zzana, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzana);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final void showInterstitial() throws RemoteException {
        zza(7, obtainAndWriteInterfaceToken());
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzane zzane, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzane);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(8, obtainAndWriteInterfaceToken);
    }

    public final void zzvk() throws RemoteException {
        zza(9, obtainAndWriteInterfaceToken());
    }

    public final void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final void zza(String[] strArr, Bundle[] bundleArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        obtainAndWriteInterfaceToken.writeTypedArray(bundleArr, 0);
        zza(11, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzanc zzanc, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzanc);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(12, obtainAndWriteInterfaceToken);
    }
}
