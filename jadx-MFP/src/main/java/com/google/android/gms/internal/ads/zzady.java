package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public abstract class zzady extends zzex implements zzadx {
    public zzady() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzadx zzm(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        if (queryLocalInterface instanceof zzadx) {
            return (zzadx) queryLocalInterface;
        }
        return new zzadz(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String zzbq = zzbq(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(zzbq);
                break;
            case 2:
                zzadb zzbr = zzbr(parcel.readString());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzbr);
                break;
            case 3:
                List availableAssetNames = getAvailableAssetNames();
                parcel2.writeNoException();
                parcel2.writeStringList(availableAssetNames);
                break;
            case 4:
                String customTemplateId = getCustomTemplateId();
                parcel2.writeNoException();
                parcel2.writeString(customTemplateId);
                break;
            case 5:
                performClick(parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                recordImpression();
                parcel2.writeNoException();
                break;
            case 7:
                zzyp videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) videoController);
                break;
            case 8:
                destroy();
                parcel2.writeNoException();
                break;
            case 9:
                IObjectWrapper zzsg = zzsg();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsg);
                break;
            case 10:
                boolean zzi = zzi(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, zzi);
                break;
            case 11:
                IObjectWrapper zzsc = zzsc();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsc);
                break;
            default:
                return false;
        }
        return true;
    }
}
