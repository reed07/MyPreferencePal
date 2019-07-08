package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzbs implements Creator<zzbr> {
    zzbs() {
    }

    @Deprecated
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbr[i];
    }

    @Deprecated
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzbr(parcel);
    }
}
