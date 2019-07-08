package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public final class zzall extends zzew implements zzalj {
    zzall(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(1, obtainAndWriteInterfaceToken);
    }

    public final IObjectWrapper zzut() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(3, obtainAndWriteInterfaceToken);
    }

    public final void showInterstitial() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void destroy() throws RemoteException {
        zza(5, obtainAndWriteInterfaceToken());
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwf);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zza(7, obtainAndWriteInterfaceToken);
    }

    public final void pause() throws RemoteException {
        zza(8, obtainAndWriteInterfaceToken());
    }

    public final void resume() throws RemoteException {
        zza(9, obtainAndWriteInterfaceToken());
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzavz zzavz, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzavz);
        obtainAndWriteInterfaceToken.writeString(str2);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final void zzc(zzwb zzwb, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        zza(11, obtainAndWriteInterfaceToken);
    }

    public final void showVideo() throws RemoteException {
        zza(12, obtainAndWriteInterfaceToken());
    }

    public final boolean isInitialized() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(13, obtainAndWriteInterfaceToken());
        boolean zza = zzey.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm, zzacp zzacp, List<String> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzalm);
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzacp);
        obtainAndWriteInterfaceToken.writeStringList(list);
        zza(14, obtainAndWriteInterfaceToken);
    }

    public final zzals zzuu() throws RemoteException {
        zzals zzals;
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzals = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
            if (queryLocalInterface instanceof zzals) {
                zzals = (zzals) queryLocalInterface;
            } else {
                zzals = new zzalu(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzals;
    }

    public final zzalv zzuv() throws RemoteException {
        zzalv zzalv;
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzalv = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
            if (queryLocalInterface instanceof zzalv) {
                zzalv = (zzalv) queryLocalInterface;
            } else {
                zzalv = new zzalx(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzalv;
    }

    public final Bundle zzuw() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle) zzey.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final Bundle getInterstitialAdapterInfo() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(18, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle) zzey.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final Bundle zzux() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle) zzey.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final void zza(zzwb zzwb, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (Parcelable) zzwb);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zza(20, obtainAndWriteInterfaceToken);
    }

    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zza(21, obtainAndWriteInterfaceToken);
    }

    public final boolean zzuy() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(22, obtainAndWriteInterfaceToken());
        boolean zza = zzey.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzavz zzavz, List<String> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzey.zza(obtainAndWriteInterfaceToken, (IInterface) zzavz);
        obtainAndWriteInterfaceToken.writeStringList(list);
        zza(23, obtainAndWriteInterfaceToken);
    }

    public final zzadx zzuz() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(24, obtainAndWriteInterfaceToken());
        zzadx zzm = zzady.zzm(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzm;
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzey.writeBoolean(obtainAndWriteInterfaceToken, z);
        zza(25, obtainAndWriteInterfaceToken);
    }

    public final zzyp getVideoController() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(26, obtainAndWriteInterfaceToken());
        zzyp zzg = zzyq.zzg(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzg;
    }

    public final zzaly zzva() throws RemoteException {
        zzaly zzaly;
        Parcel transactAndReadException = transactAndReadException(27, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzaly = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
            if (queryLocalInterface instanceof zzaly) {
                zzaly = (zzaly) queryLocalInterface;
            } else {
                zzaly = new zzama(readStrongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzaly;
    }
}
