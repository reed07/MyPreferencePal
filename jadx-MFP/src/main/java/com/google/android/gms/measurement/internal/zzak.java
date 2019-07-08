package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzr;
import com.google.android.gms.internal.measurement.zzs;
import java.util.List;

public abstract class zzak extends zzr implements zzaj {
    public zzak() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzag) zzs.zza(parcel, zzag.CREATOR), (zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzfu) zzs.zza(parcel, zzfu.CREATOR), (zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzag) zzs.zza(parcel, zzag.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                List zza = zza((zzk) zzs.zza(parcel, zzk.CREATOR), zzs.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzag) zzs.zza(parcel, zzag.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case 12:
                zza((zzo) zzs.zza(parcel, zzo.CREATOR), (zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zzb((zzo) zzs.zza(parcel, zzo.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                List zza3 = zza(parcel.readString(), parcel.readString(), zzs.zza(parcel), (zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                break;
            case 15:
                List zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzs.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                break;
            case 16:
                List zza5 = zza(parcel.readString(), parcel.readString(), (zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                break;
            case 17:
                List zze = zze(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zze);
                break;
            case 18:
                zzd((zzk) zzs.zza(parcel, zzk.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
