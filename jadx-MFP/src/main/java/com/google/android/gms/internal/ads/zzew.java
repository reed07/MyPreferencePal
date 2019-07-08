package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzew implements IInterface {
    private final IBinder zzvd;
    private final String zzve;

    protected zzew(IBinder iBinder, String str) {
        this.zzvd = iBinder;
        this.zzve = str;
    }

    public IBinder asBinder() {
        return this.zzvd;
    }

    /* access modifiers changed from: protected */
    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzve);
        return obtain;
    }

    /* access modifiers changed from: protected */
    public final Parcel transactAndReadException(int i, Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.zzvd.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, Parcel parcel) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            this.zzvd.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(int i, Parcel parcel) throws RemoteException {
        try {
            this.zzvd.transact(2, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
