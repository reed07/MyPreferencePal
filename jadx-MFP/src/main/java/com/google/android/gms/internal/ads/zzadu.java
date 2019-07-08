package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzadu extends zzex implements zzadt {
    public zzadu() {
        super("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                IObjectWrapper zzsc = zzsc();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsc);
                break;
            case 3:
                String headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 4:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 5:
                String body = getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                break;
            case 6:
                zzadb zzsf = zzsf();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsf);
                break;
            case 7:
                String callToAction = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(callToAction);
                break;
            case 8:
                String advertiser = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(advertiser);
                break;
            case 9:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzey.zzb(parcel2, extras);
                break;
            case 10:
                destroy();
                parcel2.writeNoException();
                break;
            case 11:
                zzyp videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) videoController);
                break;
            case 12:
                performClick((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                boolean recordImpression = recordImpression((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, recordImpression);
                break;
            case 14:
                reportTouchEvent((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 15:
                zzacx zzse = zzse();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzse);
                break;
            case 16:
                IObjectWrapper zzsd = zzsd();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsd);
                break;
            case 17:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            default:
                return false;
        }
        return true;
    }
}
