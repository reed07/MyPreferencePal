package com.samsung.accessory.safiletransfer.core;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;

public interface ISAFTManager extends IInterface {

    public static abstract class Stub extends Binder implements ISAFTManager {

        static class Proxy implements ISAFTManager {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean registerCallbackFacilitator(int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                    obtain.writeInt(i);
                    boolean z = true;
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle sendCommand(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ISAFTManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISAFTManager)) ? new Proxy(iBinder) : (ISAFTManager) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                        boolean registerCallbackFacilitator = registerCallbackFacilitator(parcel.readInt(), parcel.readInt() != 0 ? (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeInt(registerCallbackFacilitator ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                        Bundle sendCommand = sendCommand(parcel.readString());
                        parcel2.writeNoException();
                        if (sendCommand != null) {
                            parcel2.writeInt(1);
                            sendCommand.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                return true;
            }
        }
    }

    boolean registerCallbackFacilitator(int i, ResultReceiver resultReceiver) throws RemoteException;

    Bundle sendCommand(String str) throws RemoteException;
}
