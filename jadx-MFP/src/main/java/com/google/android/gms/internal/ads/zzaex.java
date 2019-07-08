package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzaex extends zzex implements zzaew {
    public zzaex() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaet zzaet;
        switch (i) {
            case 2:
                String headline = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            case 3:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            case 4:
                String body = getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                break;
            case 5:
                zzadb zzsb = zzsb();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsb);
                break;
            case 6:
                String callToAction = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(callToAction);
                break;
            case 7:
                String advertiser = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(advertiser);
                break;
            case 8:
                double starRating = getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                break;
            case 9:
                String store = getStore();
                parcel2.writeNoException();
                parcel2.writeString(store);
                break;
            case 10:
                String price = getPrice();
                parcel2.writeNoException();
                parcel2.writeString(price);
                break;
            case 11:
                zzyp videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) videoController);
                break;
            case 12:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            case 13:
                destroy();
                parcel2.writeNoException();
                break;
            case 14:
                zzacx zzse = zzse();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzse);
                break;
            case 15:
                performClick((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 16:
                boolean recordImpression = recordImpression((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, recordImpression);
                break;
            case 17:
                reportTouchEvent((Bundle) zzey.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 18:
                IObjectWrapper zzsc = zzsc();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsc);
                break;
            case 19:
                IObjectWrapper zzsd = zzsd();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsd);
                break;
            case 20:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzey.zzb(parcel2, extras);
                break;
            case 21:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzaet = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
                    if (queryLocalInterface instanceof zzaet) {
                        zzaet = (zzaet) queryLocalInterface;
                    } else {
                        zzaet = new zzaev(readStrongBinder);
                    }
                }
                zza(zzaet);
                parcel2.writeNoException();
                break;
            case 22:
                cancelUnconfirmedClick();
                parcel2.writeNoException();
                break;
            case 23:
                List muteThisAdReasons = getMuteThisAdReasons();
                parcel2.writeNoException();
                parcel2.writeList(muteThisAdReasons);
                break;
            case 24:
                boolean isCustomMuteThisAdEnabled = isCustomMuteThisAdEnabled();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isCustomMuteThisAdEnabled);
                break;
            case 25:
                zza(zzym.zzf(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 26:
                zza(zzyi.zze(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 27:
                zzsi();
                parcel2.writeNoException();
                break;
            case 28:
                recordCustomClickGesture();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
