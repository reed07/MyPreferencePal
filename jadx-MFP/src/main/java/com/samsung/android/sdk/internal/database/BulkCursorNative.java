package com.samsung.android.sdk.internal.database;

import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class BulkCursorNative extends Binder implements IBulkCursor {
    public IBinder asBinder() {
        return this;
    }

    public BulkCursorNative() {
        attachInterface(this, IBulkCursor.descriptor);
    }

    public static IBulkCursor asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IBulkCursor iBulkCursor = (IBulkCursor) iBinder.queryLocalInterface(IBulkCursor.descriptor);
        if (iBulkCursor != null) {
            return iBulkCursor;
        }
        return new a(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                parcel.enforceInterface(IBulkCursor.descriptor);
                CursorWindow window = getWindow(parcel.readInt());
                parcel2.writeNoException();
                if (window == null) {
                    parcel2.writeInt(0);
                } else {
                    parcel2.writeInt(1);
                    window.writeToParcel(parcel2, 1);
                }
                return true;
            case 2:
                parcel.enforceInterface(IBulkCursor.descriptor);
                deactivate();
                parcel2.writeNoException();
                return true;
            case 3:
                parcel.enforceInterface(IBulkCursor.descriptor);
                int requery = requery();
                parcel2.writeNoException();
                parcel2.writeInt(requery);
                parcel2.writeBundle(getExtras());
                return true;
            case 4:
                parcel.enforceInterface(IBulkCursor.descriptor);
                onMove(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface(IBulkCursor.descriptor);
                Bundle extras = getExtras();
                parcel2.writeNoException();
                parcel2.writeBundle(extras);
                return true;
            case 6:
                parcel.enforceInterface(IBulkCursor.descriptor);
                Bundle respond = respond(parcel.readBundle());
                parcel2.writeNoException();
                parcel2.writeBundle(respond);
                return true;
            case 7:
                parcel.enforceInterface(IBulkCursor.descriptor);
                close();
                parcel2.writeNoException();
                return true;
            default:
                try {
                    return super.onTransact(i, parcel, parcel2, i2);
                } catch (Exception e) {
                    DatabaseUtils.writeExceptionToParcel(parcel2, e);
                    return true;
                }
        }
    }
}
