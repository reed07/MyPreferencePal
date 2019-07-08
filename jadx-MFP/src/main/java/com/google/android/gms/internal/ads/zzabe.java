package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzabe extends zzex implements zzabd {
    public zzabe() {
        super("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String zzrk = zzrk();
                parcel2.writeNoException();
                parcel2.writeString(zzrk);
                break;
            case 2:
                String content = getContent();
                parcel2.writeNoException();
                parcel2.writeString(content);
                break;
            case 3:
                zzh(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 4:
                recordClick();
                parcel2.writeNoException();
                break;
            case 5:
                recordImpression();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
