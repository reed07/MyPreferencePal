package com.samsung.android.sdk.healthdata;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IDeviceManager extends IInterface {

    public static abstract class Stub extends Binder implements IDeviceManager {

        static class a implements IDeviceManager {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public final IBinder asBinder() {
                return this.a;
            }

            public final HealthDevice getLocalDevice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthDevice) HealthDevice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final List<HealthDevice> getAllRegisteredDevices() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(HealthDevice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthDevice getRegisteredDevice(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    obtain.writeString(str);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthDevice) HealthDevice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthDevice getRegisteredDeviceByUuid(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    obtain.writeString(str);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthDevice) HealthDevice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String registerDevice(HealthDevice healthDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    if (healthDevice != null) {
                        obtain.writeInt(1);
                        healthDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean changeDeviceName(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final HealthDevice getDeviceByUuid(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    obtain.writeString(str);
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (HealthDevice) HealthDevice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final List<String> getDeviceUuidsBy(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.android.sdk.healthdata.IDeviceManager");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
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
            attachInterface(this, "com.samsung.android.sdk.healthdata.IDeviceManager");
        }

        public static IDeviceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceManager)) {
                return new a(iBinder);
            }
            return (IDeviceManager) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        HealthDevice localDevice = getLocalDevice();
                        parcel2.writeNoException();
                        if (localDevice != null) {
                            parcel2.writeInt(1);
                            localDevice.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        List allRegisteredDevices = getAllRegisteredDevices();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(allRegisteredDevices);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        HealthDevice registeredDevice = getRegisteredDevice(parcel.readString());
                        parcel2.writeNoException();
                        if (registeredDevice != null) {
                            parcel2.writeInt(1);
                            registeredDevice.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 4:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        HealthDevice registeredDeviceByUuid = getRegisteredDeviceByUuid(parcel.readString());
                        parcel2.writeNoException();
                        if (registeredDeviceByUuid != null) {
                            parcel2.writeInt(1);
                            registeredDeviceByUuid.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 5:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        String registerDevice = registerDevice(parcel.readInt() != 0 ? (HealthDevice) HealthDevice.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeString(registerDevice);
                        return true;
                    case 6:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        boolean changeDeviceName = changeDeviceName(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(changeDeviceName ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        HealthDevice deviceByUuid = getDeviceByUuid(parcel.readString());
                        parcel2.writeNoException();
                        if (deviceByUuid != null) {
                            parcel2.writeInt(1);
                            deviceByUuid.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 8:
                        parcel.enforceInterface("com.samsung.android.sdk.healthdata.IDeviceManager");
                        List deviceUuidsBy = getDeviceUuidsBy(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringList(deviceUuidsBy);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.samsung.android.sdk.healthdata.IDeviceManager");
                return true;
            }
        }
    }

    boolean changeDeviceName(String str, String str2) throws RemoteException;

    List<HealthDevice> getAllRegisteredDevices() throws RemoteException;

    HealthDevice getDeviceByUuid(String str) throws RemoteException;

    List<String> getDeviceUuidsBy(String str, int i) throws RemoteException;

    HealthDevice getLocalDevice() throws RemoteException;

    HealthDevice getRegisteredDevice(String str) throws RemoteException;

    HealthDevice getRegisteredDeviceByUuid(String str) throws RemoteException;

    String registerDevice(HealthDevice healthDevice) throws RemoteException;
}
