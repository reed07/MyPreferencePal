package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataSource.Builder;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Class(creator = "DataReadResultCreator")
@Reserved({7, 1000})
public class DataReadResult extends AbstractSafeParcelable implements Result {
    public static final Creator<DataReadResult> CREATOR = new zzc();
    @Field(getter = "getRawDataSets", id = 1, type = "java.util.List")
    private final List<DataSet> zzaj;
    @Field(getter = "getUniqueDataSources", id = 6)
    private final List<DataSource> zzav;
    @Field(getter = "getStatus", id = 2)
    private final Status zzir;
    @Field(getter = "getRawBuckets", id = 3, type = "java.util.List")
    private final List<Bucket> zzis;
    @Field(getter = "getBatchCount", id = 5)
    private int zzit;

    @Constructor
    DataReadResult(@Param(id = 1) List<RawDataSet> list, @Param(id = 2) Status status, @Param(id = 3) List<RawBucket> list2, @Param(id = 5) int i, @Param(id = 6) List<DataSource> list3) {
        this.zzir = status;
        this.zzit = i;
        this.zzav = list3;
        this.zzaj = new ArrayList(list.size());
        for (RawDataSet dataSet : list) {
            this.zzaj.add(new DataSet(dataSet, list3));
        }
        this.zzis = new ArrayList(list2.size());
        for (RawBucket bucket : list2) {
            this.zzis.add(new Bucket(bucket, list3));
        }
    }

    private DataReadResult(List<DataSet> list, List<Bucket> list2, Status status) {
        this.zzaj = list;
        this.zzir = status;
        this.zzis = list2;
        this.zzit = 1;
        this.zzav = new ArrayList();
    }

    public static DataReadResult zza(Status status, List<DataType> list, List<DataSource> list2) {
        ArrayList arrayList = new ArrayList();
        for (DataSource create : list2) {
            arrayList.add(DataSet.create(create));
        }
        for (DataType dataType : list) {
            arrayList.add(DataSet.create(new Builder().setType(1).setDataType(dataType).setName("Default").build()));
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zzaj) {
            if (dataType.equals(dataSet.getDataType())) {
                return dataSet;
            }
        }
        return DataSet.create(new Builder().setDataType(dataType).setType(1).build());
    }

    public DataSet getDataSet(DataSource dataSource) {
        for (DataSet dataSet : this.zzaj) {
            if (dataSource.equals(dataSet.getDataSource())) {
                return dataSet;
            }
        }
        return DataSet.create(dataSource);
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    public List<Bucket> getBuckets() {
        return this.zzis;
    }

    public final int zzaa() {
        return this.zzit;
    }

    public final void zzb(DataReadResult dataReadResult) {
        for (DataSet zza : dataReadResult.getDataSets()) {
            zza(zza, this.zzaj);
        }
        for (Bucket bucket : dataReadResult.getBuckets()) {
            Iterator it = this.zzis.iterator();
            while (true) {
                if (!it.hasNext()) {
                    this.zzis.add(bucket);
                    break;
                }
                Bucket bucket2 = (Bucket) it.next();
                if (bucket2.zza(bucket)) {
                    for (DataSet zza2 : bucket.getDataSets()) {
                        zza(zza2, bucket2.getDataSets());
                    }
                }
            }
        }
    }

    private static void zza(DataSet dataSet, List<DataSet> list) {
        for (DataSet dataSet2 : list) {
            if (dataSet2.getDataSource().equals(dataSet.getDataSource())) {
                dataSet2.zza((Iterable<DataPoint>) dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadResult) {
                DataReadResult dataReadResult = (DataReadResult) obj;
                if (this.zzir.equals(dataReadResult.zzir) && Objects.equal(this.zzaj, dataReadResult.zzaj) && Objects.equal(this.zzis, dataReadResult.zzis)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzaj, this.zzis);
    }

    public String toString() {
        Object obj;
        Object obj2;
        ToStringHelper add = Objects.toStringHelper(this).add("status", this.zzir);
        String str = "dataSets";
        if (this.zzaj.size() > 5) {
            int size = this.zzaj.size();
            StringBuilder sb = new StringBuilder(21);
            sb.append(size);
            sb.append(" data sets");
            obj = sb.toString();
        } else {
            obj = this.zzaj;
        }
        ToStringHelper add2 = add.add(str, obj);
        String str2 = "buckets";
        if (this.zzis.size() > 5) {
            int size2 = this.zzis.size();
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append(size2);
            sb2.append(" buckets");
            obj2 = sb2.toString();
        } else {
            obj2 = this.zzis;
        }
        return add2.add(str2, obj2).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        ArrayList arrayList = new ArrayList(this.zzaj.size());
        for (DataSet rawDataSet : this.zzaj) {
            arrayList.add(new RawDataSet(rawDataSet, this.zzav));
        }
        SafeParcelWriter.writeList(parcel, 1, arrayList, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        ArrayList arrayList2 = new ArrayList(this.zzis.size());
        for (Bucket rawBucket : this.zzis) {
            arrayList2.add(new RawBucket(rawBucket, this.zzav));
        }
        SafeParcelWriter.writeList(parcel, 3, arrayList2, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzit);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzav, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
