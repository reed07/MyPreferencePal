package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ads.zzhp.zza;

final class zzhr implements Creator<zza> {
    zzhr() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zza(parcel);
    }
}
