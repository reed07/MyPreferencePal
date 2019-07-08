package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.fitness.zzer;
import com.google.android.gms.internal.fitness.zzes;

@Class(creator = "ListClaimedBleDevicesRequestCreator")
@Reserved({2, 1000})
public final class zzah extends AbstractSafeParcelable {
    public static final Creator<zzah> CREATOR = new zzai();
    @Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    private final zzer zzhl;

    @Constructor
    zzah(@Param(id = 1) IBinder iBinder) {
        this.zzhl = zzes.zzk(iBinder);
    }

    public zzah(zzer zzer) {
        this.zzhl = zzer;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzhl.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
