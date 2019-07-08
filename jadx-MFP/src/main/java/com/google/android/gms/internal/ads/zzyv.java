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
@Class(creator = "IconAdOptionsParcelCreator")
@Reserved({1})
public final class zzyv extends AbstractSafeParcelable {
    public static final Creator<zzyv> CREATOR = new zzyw();
    @Field(id = 2)
    public final int zzcly;

    @Constructor
    public zzyv(@Param(id = 2) int i) {
        this.zzcly = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcly);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
