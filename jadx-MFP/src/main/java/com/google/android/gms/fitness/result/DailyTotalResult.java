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
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource.Builder;
import com.google.android.gms.fitness.data.DataType;

@Class(creator = "DailyTotalResultCreator")
@Reserved({1000})
public class DailyTotalResult extends AbstractSafeParcelable implements Result {
    public static final Creator<DailyTotalResult> CREATOR = new zzb();
    @Field(getter = "getTotal", id = 2)
    private final DataSet zzeb;
    @Field(getter = "getStatus", id = 1)
    private final Status zzir;

    @Constructor
    DailyTotalResult(@Param(id = 1) Status status, @Param(id = 2) DataSet dataSet) {
        this.zzir = status;
        this.zzeb = dataSet;
    }

    private DailyTotalResult(DataSet dataSet, Status status) {
        this.zzir = status;
        this.zzeb = dataSet;
    }

    public static DailyTotalResult zza(Status status, DataType dataType) {
        return new DailyTotalResult(DataSet.create(new Builder().setDataType(dataType).setType(1).build()), status);
    }

    @Nullable
    public DataSet getTotal() {
        return this.zzeb;
    }

    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DailyTotalResult) {
                DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
                if (this.zzir.equals(dailyTotalResult.zzir) && Objects.equal(this.zzeb, dailyTotalResult.zzeb)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzeb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("dataPoint", this.zzeb).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getTotal(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
