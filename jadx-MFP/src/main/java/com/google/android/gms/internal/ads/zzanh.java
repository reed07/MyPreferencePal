package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzanh extends zzex implements zzang {
    public zzanh() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzang zzw(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        if (queryLocalInterface instanceof zzang) {
            return (zzang) queryLocalInterface;
        }
        return new zzani(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzanj zzanj;
        zzamy zzamy;
        zzana zzana;
        zzane zzane;
        zzanc zzanc;
        switch (i) {
            case 1:
                IObjectWrapper asInterface = Stub.asInterface(parcel.readStrongBinder());
                String readString = parcel.readString();
                Bundle bundle = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                Bundle bundle2 = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                zzwf zzwf = (zzwf) zzey.zza(parcel, zzwf.CREATOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzanj = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                    if (queryLocalInterface instanceof zzanj) {
                        zzanj = (zzanj) queryLocalInterface;
                    } else {
                        zzanj = new zzank(readStrongBinder);
                    }
                }
                zza(asInterface, readString, bundle, bundle2, zzwf, zzanj);
                parcel2.writeNoException();
                break;
            case 2:
                zzans zzvi = zzvi();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzvi);
                break;
            case 3:
                zzans zzvj = zzvj();
                parcel2.writeNoException();
                zzey.zzb(parcel2, zzvj);
                break;
            case 4:
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                Bundle bundle3 = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                IObjectWrapper asInterface2 = Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 == null) {
                    zzamy = null;
                } else {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                    if (queryLocalInterface2 instanceof zzamy) {
                        zzamy = (zzamy) queryLocalInterface2;
                    } else {
                        zzamy = new zzamz(readStrongBinder2);
                    }
                }
                zza(readString2, readString3, bundle3, asInterface2, zzamy, zzaln.zzv(parcel.readStrongBinder()), (zzwf) zzey.zza(parcel, zzwf.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zzyp videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) videoController);
                break;
            case 6:
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                Bundle bundle4 = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                IObjectWrapper asInterface3 = Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 == null) {
                    zzana = null;
                } else {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                    if (queryLocalInterface3 instanceof zzana) {
                        zzana = (zzana) queryLocalInterface3;
                    } else {
                        zzana = new zzanb(readStrongBinder3);
                    }
                }
                zza(readString4, readString5, bundle4, asInterface3, zzana, zzaln.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 7:
                showInterstitial();
                parcel2.writeNoException();
                break;
            case 8:
                String readString6 = parcel.readString();
                String readString7 = parcel.readString();
                Bundle bundle5 = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                IObjectWrapper asInterface4 = Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 == null) {
                    zzane = null;
                } else {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                    if (queryLocalInterface4 instanceof zzane) {
                        zzane = (zzane) queryLocalInterface4;
                    } else {
                        zzane = new zzanf(readStrongBinder4);
                    }
                }
                zza(readString6, readString7, bundle5, asInterface4, zzane, zzaln.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 9:
                zzvk();
                parcel2.writeNoException();
                break;
            case 10:
                zzn(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 11:
                zza(parcel.createStringArray(), (Bundle[]) parcel.createTypedArray(Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            case 12:
                String readString8 = parcel.readString();
                String readString9 = parcel.readString();
                Bundle bundle6 = (Bundle) zzey.zza(parcel, Bundle.CREATOR);
                IObjectWrapper asInterface5 = Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 == null) {
                    zzanc = null;
                } else {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                    if (queryLocalInterface5 instanceof zzanc) {
                        zzanc = (zzanc) queryLocalInterface5;
                    } else {
                        zzanc = new zzand(readStrongBinder5);
                    }
                }
                zza(readString8, readString9, bundle6, asInterface5, zzanc, zzaln.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
