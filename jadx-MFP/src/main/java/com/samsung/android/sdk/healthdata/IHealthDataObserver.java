package com.samsung.android.sdk.healthdata;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHealthDataObserver extends IInterface {

    public static abstract class Stub extends Binder implements IHealthDataObserver {

        static class a implements IHealthDataObserver {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void onChange(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IHealthDataObserver");
                    obtain.writeString(str);
                    this.a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.samsung.android.sdk.healthdata.IHealthDataObserver");
        }

        public static IHealthDataObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.healthdata.IHealthDataObserver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IHealthDataObserver)) {
                return new a(iBinder);
            }
            return (IHealthDataObserver) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.samsung.android.sdk.healthdata.IHealthDataObserver");
                onChange(parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.samsung.android.sdk.healthdata.IHealthDataObserver");
                return true;
            }
        }
    }

    void onChange(String str) throws RemoteException;
}
