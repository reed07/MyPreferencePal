package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzkn implements Creator<zzkm> {
    zzkn() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzkm[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzkm(parcel);
    }
}
