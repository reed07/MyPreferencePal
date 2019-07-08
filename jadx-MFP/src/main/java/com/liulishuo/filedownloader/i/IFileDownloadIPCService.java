package com.liulishuo.filedownloader.i;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.liulishuo.filedownloader.model.FileDownloadHeader;

public interface IFileDownloadIPCService extends IInterface {

    public static abstract class Stub extends Binder implements IFileDownloadIPCService {

        private static class Proxy implements IFileDownloadIPCService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeStrongBinder(iFileDownloadIPCCallback != null ? iFileDownloadIPCCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeStrongBinder(iFileDownloadIPCCallback != null ? iFileDownloadIPCCallback.asBinder() : null);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public boolean checkDownloading(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
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

            public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    int i4 = 1;
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (fileDownloadHeader != null) {
                        obtain.writeInt(1);
                        fileDownloadHeader.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z3) {
                        i4 = 0;
                    }
                    obtain.writeInt(i4);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean pause(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
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

            public void pauseAllTasks() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
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

            public long getSofar(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getTotal(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte getStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readByte();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    boolean z = false;
                    this.mRemote.transact(11, obtain, obtain2, 0);
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

            public void startForeground(int i, Notification notification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void stopForeground(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(13, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public boolean clearTaskData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(14, obtain, obtain2, 0);
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

            public void clearAllTaskData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                    this.mRemote.transact(15, obtain, obtain2, 0);
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
            attachInterface(this, "com.liulishuo.filedownloader.i.IFileDownloadIPCService");
        }

        public static IFileDownloadIPCService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFileDownloadIPCService)) {
                return new Proxy(iBinder);
            }
            return (IFileDownloadIPCService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = i;
            Parcel parcel3 = parcel;
            Parcel parcel4 = parcel2;
            if (i3 != 1598968902) {
                Notification notification = null;
                boolean z = false;
                switch (i3) {
                    case 1:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        registerCallback(com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 2:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        unregisterCallback(com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 3:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        boolean checkDownloading = checkDownloading(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel4.writeInt(checkDownloading ? 1 : 0);
                        return true;
                    case 4:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        start(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0 ? (FileDownloadHeader) FileDownloadHeader.CREATOR.createFromParcel(parcel3) : null, parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        boolean pause = pause(parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(pause ? 1 : 0);
                        return true;
                    case 6:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        pauseAllTasks();
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        boolean maxNetworkThreadCount = setMaxNetworkThreadCount(parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(maxNetworkThreadCount ? 1 : 0);
                        return true;
                    case 8:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        long sofar = getSofar(parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeLong(sofar);
                        return true;
                    case 9:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        long total = getTotal(parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeLong(total);
                        return true;
                    case 10:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        byte status = getStatus(parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeByte(status);
                        return true;
                    case 11:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        boolean isIdle = isIdle();
                        parcel2.writeNoException();
                        parcel4.writeInt(isIdle ? 1 : 0);
                        return true;
                    case 12:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            notification = (Notification) Notification.CREATOR.createFromParcel(parcel3);
                        }
                        startForeground(readInt, notification);
                        return true;
                    case 13:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        stopForeground(z);
                        return true;
                    case 14:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        boolean clearTaskData = clearTaskData(parcel.readInt());
                        parcel2.writeNoException();
                        parcel4.writeInt(clearTaskData ? 1 : 0);
                        return true;
                    case 15:
                        parcel3.enforceInterface("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                        clearAllTaskData();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel4.writeString("com.liulishuo.filedownloader.i.IFileDownloadIPCService");
                return true;
            }
        }
    }

    boolean checkDownloading(String str, String str2) throws RemoteException;

    void clearAllTaskData() throws RemoteException;

    boolean clearTaskData(int i) throws RemoteException;

    long getSofar(int i) throws RemoteException;

    byte getStatus(int i) throws RemoteException;

    long getTotal(int i) throws RemoteException;

    boolean isIdle() throws RemoteException;

    boolean pause(int i) throws RemoteException;

    void pauseAllTasks() throws RemoteException;

    void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException;

    boolean setMaxNetworkThreadCount(int i) throws RemoteException;

    void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException;

    void startForeground(int i, Notification notification) throws RemoteException;

    void stopForeground(boolean z) throws RemoteException;

    void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException;
}
