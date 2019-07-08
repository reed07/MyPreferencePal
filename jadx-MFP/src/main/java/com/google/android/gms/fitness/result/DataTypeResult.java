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
import com.google.android.gms.fitness.data.DataType;

@Class(creator = "DataTypeResultCreator")
@Reserved({1000})
public class DataTypeResult extends AbstractSafeParcelable implements Result {
    public static final Creator<DataTypeResult> CREATOR = new zze();
    @Field(getter = "getStatus", id = 1)
    private final Status zzir;
    @Field(getter = "getDataType", id = 3)
    private final DataType zzq;

    @Constructor
    public DataTypeResult(@Param(id = 1) Status status, @Param(id = 3) DataType dataType) {
        this.zzir = status;
        this.zzq = dataType;
    }

    public static DataTypeResult zzc(Status status) {
        return new DataTypeResult(status, null);
    }

    public Status getStatus() {
        return this.zzir;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataTypeResult) {
                DataTypeResult dataTypeResult = (DataTypeResult) obj;
                if (this.zzir.equals(dataTypeResult.zzir) && Objects.equal(this.zzq, dataTypeResult.zzq)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("dataType", this.zzq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
