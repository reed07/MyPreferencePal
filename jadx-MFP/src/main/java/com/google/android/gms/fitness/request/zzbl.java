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

@Class(creator = "UnclaimBleDeviceRequestCreator")
@Reserved({1, 4, 1000})
public final class zzbl extends AbstractSafeParcelable {
    public static final Creator<zzbl> CREATOR = new zzbm();
    @Field(getter = "getDeviceAddress", id = 2)
    private final String deviceAddress;
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzcq zzgj;

    @Constructor
    zzbl(@Param(id = 2) String str, @Param(id = 3) IBinder iBinder) {
        this.deviceAddress = str;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbl(String str, zzcq zzcq) {
        this.deviceAddress = str;
        this.zzgj = zzcq;
    }

    public final String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", new Object[]{this.deviceAddress});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.deviceAddress, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
