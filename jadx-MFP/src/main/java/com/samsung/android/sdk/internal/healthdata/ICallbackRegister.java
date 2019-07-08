package com.samsung.android.sdk.internal.healthdata;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICallbackRegister extends IInterface {

    public static abstract class Stub extends Binder implements ICallbackRegister {

        static class a implements ICallbackRegister {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void setCallback(int i, IHealthResultReceiver iHealthResultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iHealthResultReceiver != null ? iHealthResultReceiver.asBinder() : null);
                    this.a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public final void cancel(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
                    obtain.writeInt(i);
                    this.a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
        }

        public static ICallbackRegister asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICallbackRegister)) {
                return new a(iBinder);
            }
            return (ICallbackRegister) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
                        setCallback(parcel.readInt(), com.samsung.android.sdk.internal.healthdata.IHealthResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 2:
                        parcel.enforceInterface("com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
                        cancel(parcel.readInt());
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.samsung.android.sdk.internal.healthdata.ICallbackRegister");
                return true;
            }
        }
    }

    void cancel(int i) throws RemoteException;

    void setCallback(int i, IHealthResultReceiver iHealthResultReceiver) throws RemoteException;
}
