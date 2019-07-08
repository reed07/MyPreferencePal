package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzacy extends zzex implements zzacx {
    public zzacy() {
        super("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    public static zzacx zzi(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
        if (queryLocalInterface instanceof zzacx) {
            return (zzacx) queryLocalInterface;
        }
        return new zzacz(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                String text = getText();
                parcel2.writeNoException();
                parcel2.writeString(text);
                break;
            case 3:
                List zzro = zzro();
                parcel2.writeNoException();
                parcel2.writeList(zzro);
                break;
            default:
                return false;
        }
        return true;
    }
}
