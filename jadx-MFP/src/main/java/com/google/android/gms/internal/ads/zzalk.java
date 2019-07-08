package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public abstract class zzalk extends zzex implements zzalj {
    public zzalk() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzalm zzalm;
        zzalm zzalm2;
        zzalm zzalm3;
        zzalm zzalm4;
        zzalm zzalm5 = null;
        switch (i) {
            case 1:
                IObjectWrapper asInterface = Stub.asInterface(parcel.readStrongBinder());
                zzwf zzwf = (zzwf) zzey.zza(parcel, zzwf.CREATOR);
                zzwb zzwb = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzalm = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface instanceof zzalm) {
                        zzalm = (zzalm) queryLocalInterface;
                    } else {
                        zzalm = new zzalo(readStrongBinder);
                    }
                }
                zza(asInterface, zzwf, zzwb, readString, zzalm);
                parcel2.writeNoException();
                break;
            case 2:
                IObjectWrapper zzut = zzut();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzut);
                break;
            case 3:
                IObjectWrapper asInterface2 = Stub.asInterface(parcel.readStrongBinder());
                zzwb zzwb2 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                String readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface2 instanceof zzalm) {
                        zzalm5 = (zzalm) queryLocalInterface2;
                    } else {
                        zzalm5 = new zzalo(readStrongBinder2);
                    }
                }
                zza(asInterface2, zzwb2, readString2, zzalm5);
                parcel2.writeNoException();
                break;
            case 4:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 5:
                destroy();
                parcel2.writeNoException();
                break;
            case 6:
                IObjectWrapper asInterface3 = Stub.asInterface(parcel.readStrongBinder());
                zzwf zzwf2 = (zzwf) zzey.zza(parcel, zzwf.CREATOR);
                zzwb zzwb3 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 == null) {
                    zzalm2 = null;
                } else {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface3 instanceof zzalm) {
                        zzalm2 = (zzalm) queryLocalInterface3;
                    } else {
                        zzalm2 = new zzalo(readStrongBinder3);
                    }
                }
                zza(asInterface3, zzwf2, zzwb3, readString3, readString4, zzalm2);
                parcel2.writeNoException();
                break;
            case 7:
                IObjectWrapper asInterface4 = Stub.asInterface(parcel.readStrongBinder());
                zzwb zzwb4 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                String readString5 = parcel.readString();
                String readString6 = parcel.readString();
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 == null) {
                    zzalm3 = null;
                } else {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface4 instanceof zzalm) {
                        zzalm3 = (zzalm) queryLocalInterface4;
                    } else {
                        zzalm3 = new zzalo(readStrongBinder4);
                    }
                }
                zza(asInterface4, zzwb4, readString5, readString6, zzalm3);
                parcel2.writeNoException();
                break;
            case 8:
                pause();
                parcel2.writeNoException();
                break;
            case 9:
                resume();
                parcel2.writeNoException();
                break;
            case 10:
                zza(Stub.asInterface(parcel.readStrongBinder()), (zzwb) zzey.zza(parcel, zzwb.CREATOR), parcel.readString(), zzawa.zzad(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                zzc((zzwb) zzey.zza(parcel, zzwb.CREATOR), parcel.readString());
                parcel2.writeNoException();
                break;
            case 12:
                showVideo();
                parcel2.writeNoException();
                break;
            case 13:
                boolean isInitialized = isInitialized();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, isInitialized);
                break;
            case 14:
                IObjectWrapper asInterface5 = Stub.asInterface(parcel.readStrongBinder());
                zzwb zzwb5 = (zzwb) zzey.zza(parcel, zzwb.CREATOR);
                String readString7 = parcel.readString();
                String readString8 = parcel.readString();
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 == null) {
                    zzalm4 = null;
                } else {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface5 instanceof zzalm) {
                        zzalm4 = (zzalm) queryLocalInterface5;
                    } else {
                        zzalm4 = new zzalo(readStrongBinder5);
                    }
                }
                zza(asInterface5, zzwb5, readString7, readString8, zzalm4, (zzacp) zzey.zza(parcel, zzacp.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 15:
                zzals zzuu = zzuu();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzuu);
                break;
            case 16:
                zzalv zzuv = zzuv();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzuv);
                break;
            case 17:
                Bundle zzuw = zzuw();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzuw);
                break;
            case 18:
                Bundle interstitialAdapterInfo = getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzey.zzb(parcel2, interstitialAdapterInfo);
                break;
            case 19:
                Bundle zzux = zzux();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzux);
                break;
            case 20:
                zza((zzwb) zzey.zza(parcel, zzwb.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 21:
                zzj(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 22:
                boolean zzuy = zzuy();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, zzuy);
                break;
            case 23:
                zza(Stub.asInterface(parcel.readStrongBinder()), zzawa.zzad(parcel.readStrongBinder()), (List<String>) parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            case 24:
                zzadx zzuz = zzuz();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzuz);
                break;
            case 25:
                setImmersiveMode(zzey.zza(parcel));
                parcel2.writeNoException();
                break;
            case 26:
                zzyp videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) videoController);
                break;
            case 27:
                zzaly zzva = zzva();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzva);
                break;
            default:
                return false;
        }
        return true;
    }
}
