package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzxx extends zzex implements zzxw {
    public zzxx() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    public static zzxw asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        if (queryLocalInterface instanceof zzxw) {
            return (zzxw) queryLocalInterface;
        }
        return new zzxy(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzxl createBannerAdManager = createBannerAdManager(Stub.asInterface(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR), parcel.readString(), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createBannerAdManager);
                break;
            case 2:
                zzxl createInterstitialAdManager = createInterstitialAdManager(Stub.asInterface(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR), parcel.readString(), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createInterstitialAdManager);
                break;
            case 3:
                zzxg createAdLoaderBuilder = createAdLoaderBuilder(Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createAdLoaderBuilder);
                break;
            case 4:
                zzyc mobileAdsSettingsManager = getMobileAdsSettingsManager(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) mobileAdsSettingsManager);
                break;
            case 5:
                zzadf createNativeAdViewDelegate = createNativeAdViewDelegate(Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createNativeAdViewDelegate);
                break;
            case 6:
                zzauw createRewardedVideoAd = createRewardedVideoAd(Stub.asInterface(parcel.readStrongBinder()), zzalh.zzu(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createRewardedVideoAd);
                break;
            case 7:
                zzaoz createInAppPurchaseManager = createInAppPurchaseManager(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createInAppPurchaseManager);
                break;
            case 8:
                zzaop createAdOverlay = createAdOverlay(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createAdOverlay);
                break;
            case 9:
                zzyc mobileAdsSettingsManagerWithClientJarVersion = getMobileAdsSettingsManagerWithClientJarVersion(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) mobileAdsSettingsManagerWithClientJarVersion);
                break;
            case 10:
                zzxl createSearchAdManager = createSearchAdManager(Stub.asInterface(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createSearchAdManager);
                break;
            case 11:
                zzadk createNativeAdViewHolderDelegate = createNativeAdViewHolderDelegate(Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createNativeAdViewHolderDelegate);
                break;
            case 12:
                zzauw createRewardedVideoAdSku = createRewardedVideoAdSku(Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) createRewardedVideoAdSku);
                break;
            default:
                return false;
        }
        return true;
    }
}
