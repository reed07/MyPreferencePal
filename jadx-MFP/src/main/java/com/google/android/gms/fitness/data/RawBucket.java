package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@KeepName
@Class(creator = "RawBucketCreator")
@Reserved({1000})
public final class RawBucket extends AbstractSafeParcelable {
    public static final Creator<RawBucket> CREATOR = new zzy();
    @Field(id = 5)
    public final List<RawDataSet> zzaj;
    @Field(id = 6)
    public final int zzak;
    @Field(id = 7)
    public final boolean zzal;
    @Field(id = 4)
    public final int zzdv;
    @Field(id = 1)
    public final long zzs;
    @Field(id = 2)
    public final long zzt;
    @Field(id = 3)
    public final Session zzz;

    @Constructor
    public RawBucket(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) Session session, @Param(id = 4) int i, @Param(id = 5) List<RawDataSet> list, @Param(id = 6) int i2, @Param(id = 7) boolean z) {
        this.zzs = j;
        this.zzt = j2;
        this.zzz = session;
        this.zzdv = i;
        this.zzaj = list;
        this.zzak = i2;
        this.zzal = z;
    }

    public RawBucket(Bucket bucket, List<DataSource> list) {
        this.zzs = bucket.getStartTime(TimeUnit.MILLISECONDS);
        this.zzt = bucket.getEndTime(TimeUnit.MILLISECONDS);
        this.zzz = bucket.getSession();
        this.zzdv = bucket.getActivityType();
        this.zzak = bucket.getBucketType();
        this.zzal = bucket.zza();
        List<DataSet> dataSets = bucket.getDataSets();
        this.zzaj = new ArrayList(dataSets.size());
        for (DataSet rawDataSet : dataSets) {
            this.zzaj.add(new RawDataSet(rawDataSet, list));
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawBucket)) {
            return false;
        }
        RawBucket rawBucket = (RawBucket) obj;
        return this.zzs == rawBucket.zzs && this.zzt == rawBucket.zzt && this.zzdv == rawBucket.zzdv && Objects.equal(this.zzaj, rawBucket.zzaj) && this.zzak == rawBucket.zzak && this.zzal == rawBucket.zzal;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), Integer.valueOf(this.zzak));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzs)).add(AbstractEvent.END_TIME, Long.valueOf(this.zzt)).add(AbstractEvent.ACTIVITY, Integer.valueOf(this.zzdv)).add("dataSets", this.zzaj).add("bucketType", Integer.valueOf(this.zzak)).add("serverHasMoreData", Boolean.valueOf(this.zzal)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzz, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdv);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zzaj, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzak);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
