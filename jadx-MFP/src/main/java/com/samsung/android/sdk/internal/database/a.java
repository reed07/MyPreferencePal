package com.samsung.android.sdk.internal.database;

import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: BulkCursorNative */
final class a implements IBulkCursor {
    private final IBinder a;
    private Bundle b = null;

    public a(IBinder iBinder) {
        this.a = iBinder;
    }

    public final IBinder asBinder() {
        return this.a;
    }

    public final CursorWindow getWindow(int i) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IBulkCursor.descriptor);
            obtain.writeInt(i);
            this.a.transact(1, obtain, obtain2, 0);
            DatabaseUtils.readExceptionFromParcel(obtain2);
            CursorWindow cursorWindow = null;
            if (obtain2.readInt() == 1) {
                cursorWindow = CursorWindow.newFromParcel(obtain2);
            }
            return cursorWindow;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public final void onMove(int i) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IBulkCursor.descriptor);
            obtain.writeInt(i);
            this.a.transact(4, obtain, obtain2, 0);
            DatabaseUtils.readExceptionFromParcel(obtain2);
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public final void deactivate() throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IBulkCursor.descriptor);
            this.a.transact(2, obtain, obtain2, 0);
            DatabaseUtils.readExceptionFromParcel(obtain2);
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public final void close() throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IBulkCursor.descriptor);
            this.a.transact(7, obtain, obtain2, 0);
            DatabaseUtils.readExceptionFromParcel(obtain2);
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public final int requery() throws RemoteException {
        int i;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IBulkCursor.descriptor);
            boolean transact = this.a.transact(3, obtain, obtain2, 0);
            DatabaseUtils.readExceptionFromParcel(obtain2);
            if (!transact) {
                i = -1;
            } else {
                i = obtain2.readInt();
                this.b = obtain2.readBundle();
            }
            return i;
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public final Bundle getExtras() throws RemoteException {
        if (this.b == null) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(IBulkCursor.descriptor);
                this.a.transact(5, obtain, obtain2, 0);
                DatabaseUtils.readExceptionFromParcel(obtain2);
                this.b = obtain2.readBundle();
            } finally {
                obtain.recycle();
                obtain2.recycle();
            }
        }
        return this.b;
    }

    public final Bundle respond(Bundle bundle) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IBulkCursor.descriptor);
            obtain.writeBundle(bundle);
            this.a.transact(6, obtain, obtain2, 0);
            DatabaseUtils.readExceptionFromParcel(obtain2);
            return obtain2.readBundle();
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
