package com.samsung.android.sdk.healthdata;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDataWatcher extends IInterface {

    public static abstract class Stub extends Binder implements IDataWatcher {

        static class a implements IDataWatcher {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final void registerDataObserver(String str, IHealthDataObserver iHealthDataObserver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataWatcher");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iHealthDataObserver != null ? iHealthDataObserver.asBinder() : null);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void unregisterDataObserver(IHealthDataObserver iHealthDataObserver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataWatcher");
                    obtain.writeStrongBinder(iHealthDataObserver != null ? iHealthDataObserver.asBinder() : null);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void registerDataObserver2(String str, String str2, IHealthDataObserver iHealthDataObserver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataWatcher");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iHealthDataObserver != null ? iHealthDataObserver.asBinder() : null);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void unregisterDataObserver2(String str, IHealthDataObserver iHealthDataObserver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDataWatcher");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iHealthDataObserver != null ? iHealthDataObserver.asBinder() : null);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.samsung.android.sdk.healthdata.IDataWatcher");
        }

        public static IDataWatcher asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.healthdata.IDataWatcher");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDataWatcher)) {
                return new a(iBinder);
            }
            return (IDataWatcher) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDataWatcher");
                        registerDataObserver(parcel.readString(), com.samsung.android.sdk.healthdata.IHealthDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDataWatcher");
                        unregisterDataObserver(com.samsung.android.sdk.healthdata.IHealthDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDataWatcher");
                        registerDataObserver2(parcel.readString(), parcel.readString(), com.samsung.android.sdk.healthdata.IHealthDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDataWatcher");
                        unregisterDataObserver2(parcel.readString(), com.samsung.android.sdk.healthdata.IHealthDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.samsung.android.sdk.healthdata.IDataWatcher");
                return true;
            }
        }
    }

    void registerDataObserver(String str, IHealthDataObserver iHealthDataObserver) throws RemoteException;

    void registerDataObserver2(String str, String str2, IHealthDataObserver iHealthDataObserver) throws RemoteException;

    void unregisterDataObserver(IHealthDataObserver iHealthDataObserver) throws RemoteException;

    void unregisterDataObserver2(String str, IHealthDataObserver iHealthDataObserver) throws RemoteException;
}
