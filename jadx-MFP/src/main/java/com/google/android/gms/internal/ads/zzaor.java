package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzaor extends zzew implements zzaop {
    zzaor(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zza(1, obtainAndWriteInterfaceToken);
    }

    public final void onRestart() throws RemoteException {
        zza(2, obtainAndWriteInterfaceToken());
    }

    public final void onStart() throws RemoteException {
        zza(3, obtainAndWriteInterfaceToken());
    }

    public final void onResume() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void onPause() throws RemoteException {
        zza(5, obtainAndWriteInterfaceToken());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    public final void onStop() throws RemoteException {
        zza(7, obtainAndWriteInterfaceToken());
    }

    public final void onDestroy() throws RemoteException {
        zza(8, obtainAndWriteInterfaceToken());
    }

    public final void zzay() throws RemoteException {
        zza(9, obtainAndWriteInterfaceToken());
    }

    public final void onBackPressed() throws RemoteException {
        zza(10, obtainAndWriteInterfaceToken());
    }

    public final boolean zzvq() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        boolean zza = zzey.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    public final void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) intent);
        zza(12, obtainAndWriteInterfaceToken);
    }

    public final void zzq(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zza(13, obtainAndWriteInterfaceToken);
    }
}
