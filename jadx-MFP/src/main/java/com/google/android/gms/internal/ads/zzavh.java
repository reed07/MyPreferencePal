package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzark
@Class(creator = "RewardedVideoAdRequestParcelCreator")
@Reserved({1})
public final class zzavh extends AbstractSafeParcelable {
    public static final Creator<zzavh> CREATOR = new zzavi();
    @Field(id = 3)
    public final String zzbsn;
    @Field(id = 2)
    public final zzwb zzdwg;

    @Constructor
    public zzavh(@Param(id = 2) zzwb zzwb, @Param(id = 3) String str) {
        this.zzdwg = zzwb;
        this.zzbsn = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdwg, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbsn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
