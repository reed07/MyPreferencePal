package com.samsung.android.sdk.internal.healthdata;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHealthResultReceiver extends IInterface {

    public static abstract class Stub extends Binder implements IHealthResultReceiver {

        static class a implements IHealthResultReceiver {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void send(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.internal.healthdata.IHealthResultReceiver");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
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
            attachInterface(this, "com.samsung.android.sdk.internal.healthdata.IHealthResultReceiver");
        }

        public static IHealthResultReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.internal.healthdata.IHealthResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IHealthResultReceiver)) {
                return new a(iBinder);
            }
            return (IHealthResultReceiver) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.samsung.android.sdk.internal.healthdata.IHealthResultReceiver");
                send(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.samsung.android.sdk.internal.healthdata.IHealthResultReceiver");
                return true;
            }
        }
    }

    void send(int i, Bundle bundle) throws RemoteException;
}
