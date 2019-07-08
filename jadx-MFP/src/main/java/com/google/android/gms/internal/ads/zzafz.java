package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@Class(creator = "InstreamAdConfigurationParcelCreator")
@ParametersAreNonnullByDefault
public final class zzafz extends AbstractSafeParcelable {
    public static final Creator<zzafz> CREATOR = new zzaga();
    @Field(id = 1)
    public final int zzdgp;

    @Constructor
    public zzafz(@Param(id = 1) int i) {
        this.zzdgp = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzdgp);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
