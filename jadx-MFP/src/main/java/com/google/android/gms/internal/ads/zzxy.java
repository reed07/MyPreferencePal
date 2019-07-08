package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzxy extends zzew implements zzxw {
    zzxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    public final zzxl createBannerAdManager(IObjectWrapper iObjectWrapper, zzwf zzwf, String str, zzalg zzalg, int i) throws RemoteException {
        zzxl zzxl;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalg);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzxl = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzxl) {
                zzxl = (zzxl) queryLocalInterface;
            } else {
                zzxl = new zzxn(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxl;
    }

    public final zzxl createInterstitialAdManager(IObjectWrapper iObjectWrapper, zzwf zzwf, String str, zzalg zzalg, int i) throws RemoteException {
        zzxl zzxl;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalg);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzxl = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzxl) {
                zzxl = (zzxl) queryLocalInterface;
            } else {
                zzxl = new zzxn(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxl;
    }

    public final zzxg createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzalg zzalg, int i) throws RemoteException {
        zzxg zzxg;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalg);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzxg = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (queryLocalInterface instanceof zzxg) {
                zzxg = (zzxg) queryLocalInterface;
            } else {
                zzxg = new zzxi(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxg;
    }

    public final zzyc getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzyc zzyc;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzyc = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (queryLocalInterface instanceof zzyc) {
                zzyc = (zzyc) queryLocalInterface;
            } else {
                zzyc = new zzye(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzyc;
    }

    public final zzadf createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        zzadf zzk = zzadg.zzk(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzk;
    }

    public final zzauw createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzalg zzalg, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalg);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken);
        zzauw zzab = zzaux.zzab(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzab;
    }

    public final zzaoz createInAppPurchaseManager(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        zzaoz zzz = zzapa.zzz(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzz;
    }

    public final zzaop createAdOverlay(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
        zzaop zzx = zzaoq.zzx(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzx;
    }

    public final zzyc getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        zzyc zzyc;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzyc = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (queryLocalInterface instanceof zzyc) {
                zzyc = (zzyc) queryLocalInterface;
            } else {
                zzyc = new zzye(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzyc;
    }

    public final zzxl createSearchAdManager(IObjectWrapper iObjectWrapper, zzwf zzwf, String str, int i) throws RemoteException {
        zzxl zzxl;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzxl = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzxl) {
                zzxl = (zzxl) queryLocalInterface;
            } else {
                zzxl = new zzxn(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxl;
    }

    public final zzadk createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper3);
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken);
        zzadk zzl = zzadl.zzl(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzl;
    }

    public final zzauw createRewardedVideoAdSku(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken);
        zzauw zzab = zzaux.zzab(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzab;
    }
}
