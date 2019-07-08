package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

public abstract class zzan extends zzb implements zzam {
    public zzan() {
        super("com.google.android.gms.search.internal.ISearchAuthCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((Status) zzc.zza(parcel, Status.CREATOR), (GoogleNowAuthState) zzc.zza(parcel, GoogleNowAuthState.CREATOR));
                break;
            case 2:
                zzb((Status) zzc.zza(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
