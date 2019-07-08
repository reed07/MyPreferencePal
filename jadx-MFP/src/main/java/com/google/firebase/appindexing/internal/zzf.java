package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "CallStatusCreator")
public final class zzf extends AbstractSafeParcelable {
    public static final Creator<zzf> CREATOR = new zzg();
    private static final zzf zzep = new zzf(1);
    private static final zzf zzeq = new zzf(2);
    private static final zzf zzer = new zzf(3);
    @Field(id = 1)
    public final int status;

    @Constructor
    public zzf(@Param(id = 1) int i) {
        this.status = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.status);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
