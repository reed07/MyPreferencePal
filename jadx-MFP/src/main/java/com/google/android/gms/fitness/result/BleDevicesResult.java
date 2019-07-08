package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "BleDevicesResultCreator")
@Reserved({1000})
public class BleDevicesResult extends AbstractSafeParcelable implements Result {
    public static final Creator<BleDevicesResult> CREATOR = new zza();
    @Field(getter = "getClaimedBleDevices", id = 1)
    private final List<BleDevice> zziq;
    @Field(getter = "getStatus", id = 2)
    private final Status zzir;

    @Constructor
    public BleDevicesResult(@Param(id = 1) List<BleDevice> list, @Param(id = 2) Status status) {
        this.zziq = Collections.unmodifiableList(list);
        this.zzir = status;
    }

    public static BleDevicesResult zzb(Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    public List<BleDevice> getClaimedBleDevices() {
        return this.zziq;
    }

    public List<BleDevice> getClaimedBleDevices(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : this.zziq) {
            if (bleDevice.getDataTypes().contains(dataType)) {
                arrayList.add(bleDevice);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof BleDevicesResult) {
                BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
                if (this.zzir.equals(bleDevicesResult.zzir) && Objects.equal(this.zziq, bleDevicesResult.zziq)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zziq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("bleDevices", this.zziq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getClaimedBleDevices(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
