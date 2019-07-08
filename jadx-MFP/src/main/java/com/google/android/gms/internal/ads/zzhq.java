package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzhq implements Creator<zzhp> {
    zzhq() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzhp[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzhp(parcel);
    }
}
