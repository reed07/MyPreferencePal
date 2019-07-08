package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import java.util.List;

public abstract class zzalw extends zzex implements zzalv {
    public zzalw() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
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
                zzadb zzsf = zzsf();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsf);
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
                recordImpression();
                parcel2.writeNoException();
                break;
            case 9:
                zzk(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 10:
                zzl(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 11:
                boolean overrideImpressionRecording = getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, overrideImpressionRecording);
                break;
            case 12:
                boolean overrideClickHandling = getOverrideClickHandling();
                parcel2.writeNoException();
                zzey.writeBoolean(parcel2, overrideClickHandling);
                break;
            case 13:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzey.zzb(parcel2, extras);
                break;
            case 14:
                zzm(Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            case 15:
                IObjectWrapper zzvb = zzvb();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzvb);
                break;
            case 16:
                zzyp videoController = getVideoController();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) videoController);
                break;
            case 19:
                zzacx zzse = zzse();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzse);
                break;
            case 20:
                IObjectWrapper zzvc = zzvc();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzvc);
                break;
            case 21:
                IObjectWrapper zzsd = zzsd();
                parcel2.writeNoException();
                zzey.zza(parcel2, (IInterface) zzsd);
                break;
            case 22:
                zzb(Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()), Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
