package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbk;
import com.google.android.gms.internal.fitness.zzbl;
import java.util.Arrays;
import java.util.List;

@Class(creator = "DataSourcesRequestCreator")
@Reserved({5, 1000})
public class DataSourcesRequest extends AbstractSafeParcelable {
    public static final Creator<DataSourcesRequest> CREATOR = new zzp();
    @Field(getter = "getDataTypes", id = 1)
    private final List<DataType> zzah;
    @Field(getter = "getDataSourceTypes", id = 2)
    private final List<Integer> zzhc;
    @Field(getter = "includeDbOnlySources", id = 3)
    private final boolean zzhd;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    private final zzbk zzhe;

    public static class Builder {
        private boolean zzhd = false;
        /* access modifiers changed from: private */
        public DataType[] zzhf = new DataType[0];
        /* access modifiers changed from: private */
        public int[] zzhg = {0, 1};

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhf = dataTypeArr;
            return this;
        }

        public Builder setDataSourceTypes(int... iArr) {
            this.zzhg = iArr;
            return this;
        }

        public DataSourcesRequest build() {
            boolean z = true;
            Preconditions.checkState(this.zzhf.length > 0, "Must add at least one data type");
            if (this.zzhg.length <= 0) {
                z = false;
            }
            Preconditions.checkState(z, "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }
    }

    @Constructor
    DataSourcesRequest(@Param(id = 1) List<DataType> list, @Param(id = 2) List<Integer> list2, @Param(id = 3) boolean z, @Param(id = 4) IBinder iBinder) {
        this.zzah = list;
        this.zzhc = list2;
        this.zzhd = z;
        this.zzhe = zzbl.zzd(iBinder);
    }

    private DataSourcesRequest(Builder builder) {
        this((List<DataType>) ArrayUtils.toArrayList(builder.zzhf), Arrays.asList(ArrayUtils.toWrapperArray(builder.zzhg)), false, (zzbk) null);
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, zzbk zzbk) {
        this(dataSourcesRequest.zzah, dataSourcesRequest.zzhc, dataSourcesRequest.zzhd, zzbk);
    }

    private DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, @Nullable zzbk zzbk) {
        this.zzah = list;
        this.zzhc = list2;
        this.zzhd = z;
        this.zzhe = zzbk;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public String toString() {
        ToStringHelper add = Objects.toStringHelper(this).add("dataTypes", this.zzah).add("sourceTypes", this.zzhc);
        if (this.zzhd) {
            add.add("includeDbOnlySources", "true");
        }
        return add.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzhc, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzhd);
        zzbk zzbk = this.zzhe;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbk == null ? null : zzbk.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
