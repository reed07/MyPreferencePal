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
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "ClaimBleDeviceRequestCreator")
@Reserved({4, 1000})
public final class zze extends AbstractSafeParcelable {
    public static final Creator<zze> CREATOR = new zzf();
    @Field(getter = "getDeviceAddress", id = 1)
    private final String deviceAddress;
    @Field(getter = "getBleDevice", id = 2)
    private final BleDevice zzgi;
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzcq zzgj;

    @Constructor
    zze(@Param(id = 1) String str, @Param(id = 2) BleDevice bleDevice, @Param(id = 3) IBinder iBinder) {
        this.deviceAddress = str;
        this.zzgi = bleDevice;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zze(String str, BleDevice bleDevice, zzcq zzcq) {
        this.deviceAddress = str;
        this.zzgi = bleDevice;
        this.zzgj = zzcq;
    }

    public final String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", new Object[]{this.deviceAddress, this.zzgi});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.deviceAddress, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzgi, i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
