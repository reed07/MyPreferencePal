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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "DataSourcesResultCreator")
@Reserved({1000})
public class DataSourcesResult extends AbstractSafeParcelable implements Result {
    public static final Creator<DataSourcesResult> CREATOR = new zzd();
    @Field(getter = "getDataSources", id = 1)
    private final List<DataSource> zzgm;
    @Field(getter = "getStatus", id = 2)
    private final Status zzir;

    @Constructor
    public DataSourcesResult(@Param(id = 1) List<DataSource> list, @Param(id = 2) Status status) {
        this.zzgm = Collections.unmodifiableList(list);
        this.zzir = status;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataSource> getDataSources(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (DataSource dataSource : this.zzgm) {
            if (dataSource.getDataType().equals(dataType)) {
                arrayList.add(dataSource);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataSourcesResult) {
                DataSourcesResult dataSourcesResult = (DataSourcesResult) obj;
                if (this.zzir.equals(dataSourcesResult.zzir) && Objects.equal(this.zzgm, dataSourcesResult.zzgm)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzgm);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("dataSources", this.zzgm).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataSources(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
