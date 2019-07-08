package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzch;
import com.google.android.gms.internal.fitness.zzci;

@Class(creator = "ListSubscriptionsRequestCreator")
@Reserved({3, 1000})
public final class zzaj extends AbstractSafeParcelable {
    public static final Creator<zzaj> CREATOR = new zzak();
    @Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzch zzhm;
    @Nullable
    @Field(getter = "getDataType", id = 1)
    private final DataType zzq;

    @Constructor
    zzaj(@Nullable @Param(id = 1) DataType dataType, @Nullable @Param(id = 2) IBinder iBinder) {
        this.zzq = dataType;
        this.zzhm = zzci.zzg(iBinder);
    }

    public zzaj(@Nullable DataType dataType, zzch zzch) {
        this.zzq = dataType;
        this.zzhm = zzch;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzq, i, false);
        zzch zzch = this.zzhm;
        SafeParcelWriter.writeIBinder(parcel, 2, zzch == null ? null : zzch.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
