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
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "DisableFitRequestCreator")
@Reserved({2, 1000})
public final class zzaa extends AbstractSafeParcelable {
    public static final Creator<zzaa> CREATOR = new zzab();
    @Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    private final zzcq zzgj;

    @Constructor
    zzaa(@Param(id = 1) IBinder iBinder) {
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzaa(zzcq zzcq) {
        this.zzgj = zzcq;
    }

    public final String toString() {
        return String.format("DisableFitRequest", new Object[0]);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzgj.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
