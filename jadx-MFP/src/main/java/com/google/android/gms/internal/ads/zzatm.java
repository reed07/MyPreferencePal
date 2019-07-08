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
@Class(creator = "StringParcelCreator")
@Reserved({1})
public final class zzatm extends AbstractSafeParcelable {
    public static final Creator<zzatm> CREATOR = new zzatn();
    @Field(id = 2)
    String zzczq;

    @Constructor
    public zzatm(@Param(id = 2) String str) {
        this.zzczq = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzczq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
