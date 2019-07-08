package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.fitness.zzfa;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Class(creator = "BucketCreator")
@Reserved({1000})
public class Bucket extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    @Field(getter = "getActivityType", id = 4)
    private final int zzai;
    @Field(getter = "getDataSets", id = 5)
    private final List<DataSet> zzaj;
    @Field(getter = "getBucketType", id = 6)
    private final int zzak;
    @Field(getter = "serverHasMoreData", id = 7)
    private boolean zzal;
    @Field(getter = "getStartTimeMillis", id = 1)
    private final long zzs;
    @Field(getter = "getEndTimeMillis", id = 2)
    private final long zzt;
    @Nullable
    @Field(getter = "getSession", id = 3)
    private final Session zzz;

    @Constructor
    Bucket(@Param(id = 1) long j, @Param(id = 2) long j2, @Nullable @Param(id = 3) Session session, @Param(id = 4) int i, @Param(id = 5) List<DataSet> list, @Param(id = 6) int i2, @Param(id = 7) boolean z) {
        this.zzal = false;
        this.zzs = j;
        this.zzt = j2;
        this.zzz = session;
        this.zzai = i;
        this.zzaj = list;
        this.zzak = i2;
        this.zzal = z;
    }

    public static String zza(int i) {
        switch (i) {
            case 0:
                return "none";
            case 1:
                return TimestampAnalyticsHelper.ATTR_TIME;
            case 2:
                return "session";
            case 3:
                return "type";
            case 4:
                return Attributes.SEGMENT;
            case 5:
                return "intervals";
            default:
                return "bug";
        }
    }

    public Bucket(RawBucket rawBucket, List<DataSource> list) {
        long j = rawBucket.zzs;
        long j2 = rawBucket.zzt;
        Session session = rawBucket.zzz;
        int i = rawBucket.zzdv;
        List<RawDataSet> list2 = rawBucket.zzaj;
        ArrayList arrayList = new ArrayList(list2.size());
        for (RawDataSet dataSet : list2) {
            arrayList.add(new DataSet(dataSet, list));
        }
        this(j, j2, session, i, arrayList, rawBucket.zzak, rawBucket.zzal);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    @Nullable
    public Session getSession() {
        return this.zzz;
    }

    public String getActivity() {
        return zzfa.getName(this.zzai);
    }

    public final int getActivityType() {
        return this.zzai;
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    @Nullable
    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zzaj) {
            if (dataSet.getDataType().equals(dataType)) {
                return dataSet;
            }
        }
        return null;
    }

    public int getBucketType() {
        return this.zzak;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        return this.zzs == bucket.zzs && this.zzt == bucket.zzt && this.zzai == bucket.zzai && Objects.equal(this.zzaj, bucket.zzaj) && this.zzak == bucket.zzak && this.zzal == bucket.zzal;
    }

    public final boolean zza(Bucket bucket) {
        return this.zzs == bucket.zzs && this.zzt == bucket.zzt && this.zzai == bucket.zzai && this.zzak == bucket.zzak;
    }

    public final boolean zza() {
        if (this.zzal) {
            return true;
        }
        for (DataSet zza : this.zzaj) {
            if (zza.zza()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), Integer.valueOf(this.zzai), Integer.valueOf(this.zzak));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzs)).add(AbstractEvent.END_TIME, Long.valueOf(this.zzt)).add(AbstractEvent.ACTIVITY, Integer.valueOf(this.zzai)).add("dataSets", this.zzaj).add("bucketType", zza(this.zzak)).add("serverHasMoreData", Boolean.valueOf(this.zzal)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeParcelable(parcel, 3, getSession(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzai);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataSets(), false);
        SafeParcelWriter.writeInt(parcel, 6, getBucketType());
        SafeParcelWriter.writeBoolean(parcel, 7, zza());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
