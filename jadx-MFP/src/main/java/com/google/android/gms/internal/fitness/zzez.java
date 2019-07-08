package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;

public abstract class zzez extends zzb implements zzey {
    public zzez() {
        super("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzeu) zzc.zza(parcel, zzeu.CREATOR), zzbl.zzd(parcel.readStrongBinder()));
                break;
            case 2:
                zza((FitnessSensorServiceRequest) zzc.zza(parcel, FitnessSensorServiceRequest.CREATOR), zzcr.zzj(parcel.readStrongBinder()));
                break;
            case 3:
                zza((zzew) zzc.zza(parcel, zzew.CREATOR), zzcr.zzj(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        return true;
    }
}
