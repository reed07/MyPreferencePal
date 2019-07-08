package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Class(creator = "BleDeviceCreator")
@Reserved({1000})
public class BleDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<BleDevice> CREATOR = new zzd();
    @Field(getter = "getName", id = 2)
    private final String name;
    @Field(getter = "getAddress", id = 1)
    private final String zzaf;
    @Field(getter = "getSupportedProfiles", id = 3)
    private final List<String> zzag;
    @Field(getter = "getDataTypes", id = 4)
    private final List<DataType> zzah;

    @Constructor
    BleDevice(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) List<String> list, @Param(id = 4) List<DataType> list2) {
        this.zzaf = str;
        this.name = str2;
        this.zzag = Collections.unmodifiableList(list);
        this.zzah = Collections.unmodifiableList(list2);
    }

    public String getAddress() {
        return this.zzaf;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getSupportedProfiles() {
        return this.zzag;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BleDevice)) {
            return false;
        }
        BleDevice bleDevice = (BleDevice) obj;
        return this.name.equals(bleDevice.name) && this.zzaf.equals(bleDevice.zzaf) && new HashSet(this.zzag).equals(new HashSet(bleDevice.zzag)) && new HashSet(this.zzah).equals(new HashSet(bleDevice.zzah));
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzaf, this.zzag, this.zzah);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("address", this.zzaf).add("dataTypes", this.zzah).add("supportedProfiles", this.zzag).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAddress(), false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedProfiles(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
